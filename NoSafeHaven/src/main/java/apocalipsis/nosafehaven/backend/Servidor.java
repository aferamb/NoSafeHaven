/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    Socket clienteSocket;
    private PrintWriter salida;
    private BufferedReader entrada;

    private EstadoPausa estadoPausa;
    ServerSocket serverSocket;

    private boolean conectado = false;

    private Refugio refugio;

    /**
     * Constructor de la clase Servidor.
     */
    public Servidor() {
        // Constructor vacío
    }

    /**
     * Establece el estado de pausa del servidor.
     *
     * @param estadoPausa El nuevo estado de pausa que se desea asignar.
     */
    public void setEstadopausa(EstadoPausa estadoPausa) {
        this.estadoPausa = estadoPausa;
    }

    /**
     * Devuelve el estado de conexión del servidor.
     *
     * @return true si el servidor está conectado, false en caso contrario.
     */
    public boolean getConectado() {
        return conectado;
    }

    /**
     * Establece el refugio asociado al servidor.
     *
     * @param refugio El refugio que se desea asociar al servidor.
     */
    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    /**
     * Inicia el servidor en el puerto especificado y espera conexiones de clientes.
     * Una vez conectado un cliente, permite la comunicación bidireccional y escucha
     * comandos enviados por el cliente para controlar el estado del servidor.
     *
     * Comandos soportados por el cliente:
     * - "PAUSAR": Pausa el estado del servidor.
     * - "REANUDAR": Reanuda el estado del servidor.
     * - "VELOCIDAD=<valor>": Cambia la velocidad a un valor específico.
     * - "COMIDA_EXTRA=<valor>": Añade una cantidad específica de comida al refugio.
     * - "SALIR": Detiene el servidor y cierra la conexión con el cliente.
     *
     * Manejo de excepciones:
     * - Si ocurre un error al abrir el socket o al comunicarse con el cliente,
     *   se imprime el error en la consola.
     *
     * Notas:
     * - El servidor imprime información sobre su estado y los comandos recibidos
     *   en la consola.
     * - Si el cliente envía un comando desconocido, se notifica en la consola.
     * - El servidor se detiene si el cliente envía el comando "SALIR".
     * 
     * @param puerto El puerto en el que el servidor escuchará las conexiones de clientes.
     */
    public void iniciarServidor(int puerto) {
        // Iniciar el servidor y esperar conexiones de clientes
        //Apertura de Sockets (en la parte del servidor)
        
        try {
            // Si en este caso utilizasemos un try with resources donde inicializamos el socket del servidor, el socket se cerraría automáticamente al salir del bloque try habiendo creado la conexion con el cliente.
            // Esto crearia canales de comunicacion con el cliente una vez cerrado el socket, y se podria reutilizar el socket del servidor para crear nuevas conexiones con otros clientes.

            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en: " + serverSocket.getInetAddress().getHostAddress() + ":" + puerto); ////////////////////////////?
            System.out.println("Esperando conexión del cliente...");
            clienteSocket = serverSocket.accept();

            System.out.println("Cliente conectado.");
            conectado = true;

            salida = new PrintWriter(clienteSocket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            // Hilo para escuchar comandos del cliente
            Thread comandos = new Thread(() -> {
                try {
                    String linea;
                    while ((linea = entrada.readLine()) != null) {
                        if (linea.equalsIgnoreCase("PAUSAR")) {
                            estadoPausa.pausar();
                            System.out.println("Pausado por cliente.");
                        } else if (linea.equalsIgnoreCase("REANUDAR")) {
                            estadoPausa.reanudar();
                            System.out.println("Reanudado por cliente.");
                        } else if (linea.startsWith("VELOCIDAD=")) {
                            try {
                                int v = Integer.parseInt(linea.split("=")[1]);
                                Velocidad.setVelocidad(v);
                                System.out.println("Velocidad cambiada a " + v + " por cliente.");
                            } catch (NumberFormatException e) {
                                System.out.println("Velocidad inválida.");
                            }
                        } else if (linea.startsWith("COMIDA_EXTRA=")) {
                            try {
                                int comida = Integer.parseInt(linea.split("=")[1]);
                                refugio.addComida(comida);
                            } catch (NumberFormatException e) {
                                System.out.println("Comida inválida.");
                            }
                        } else if (linea.equalsIgnoreCase("SALIR")) {
                            salida.close();
                            entrada.close();
                            clienteSocket.close();
                            conectado = false;
                            System.out.println("Servidor detenido por el cliente.");
                            System.out.println("Cliente desconectado.");
                            System.exit(0);
                            break;
                        } else {
                            System.out.println("Comando desconocido: " + linea);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Cerrado");
                }
            });
            comandos.start();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    /**
     * Actualiza los datos del refugio enviando el número de humanos presentes.
     *
     * @param numHumanos El número de humanos en el refugio que se desea actualizar.
     */
    public void actualizarDatosRefugio(int numHumanos) {
        salida.println("datanumHumRefu=" + numHumanos);
    }

    /**
     * Actualiza los datos de la comida enviando la cantidad de comida disponible.
     *
     * @param comida La cantidad de comida que se desea actualizar.
     */
    public void actualizarDatosComida(int comida) {
        salida.println("datacomida=" + comida);
    }


    /**
     * Actualiza y envía el número total de humanos al cliente.
     *
     * @param numHumanos El número total de humanos que se desea actualizar.
     */
    public void actualizarDatosHumanosTotal(int numHumanos) {
        salida.println("datanumHumTotal=" + numHumanos);
    }

    /**
     * Actualiza y envía el número total de zombies al cliente.
     *
     * @param numZombies El número total de zombies que se desea actualizar.
     */
    public void actualizarDatosZombiesTotal(int numZombies) {
        salida.println("datanumZomTotal=" + numZombies);
    }


    /**
     * Actualiza los datos relacionados con un túnel específico, enviando la información
     * sobre el número de humanos presentes en dicho túnel a través de la salida.
     *
     * @param tunel El identificador del túnel que se desea actualizar.
     * @param numHumanos El número de humanos presentes en el túnel especificado.
     */
    public void actualizarDatosTuneles(int tunel, int numHumanos) {
        salida.println("datanumHumT" + tunel + "=" + numHumanos);
    }

    /**
     * Actualiza la cantidad de humanos en una zona de riesgo específica y envía
     * esta información a través de la salida.
     *
     * @param zona       El identificador de la zona de riesgo que se desea actualizar.
     * @param numHumanos El número de humanos presentes en la zona de riesgo.
     */
    public void actualizarDatosHumanosZonaRiesgo(int zona, int numHumanos) {
        salida.println("datanumHumZR" + zona + "=" + numHumanos);
    }

    /**
     * Actualiza los datos del número de zombis en una zona de riesgo específica.
     *
     * @param zona El identificador de la zona de riesgo donde se actualizarán los datos.
     * @param numZombies El número de zombis presentes en la zona de riesgo especificada.
     */
    public void actualizarDatosZombiesZonaRiesgo(int zona, int numZombies) {
        salida.println("datanumZomZR" + zona + "=" + numZombies);
    }

    /**
     * Actualiza el ranking enviando los datos a través de la salida.
     * Por cada elemento en la lista de ranking, se envía una línea de texto
     * con el formato "datarankingN=valor", donde N es la posición (comenzando en 1)
     * y valor es el elemento correspondiente de la lista.
     *
     * @param ranking Una lista de cadenas que representa el ranking a actualizar.
     */
    public void actualizarRanking(ArrayList<String> ranking) {
        for (int i = 0; i < ranking.size(); i++) {
            salida.println("dataranking" + (i + 1) + "=" + ranking.get(i));
        }
    }

    /**
     * Envía un comando al cliente a través del flujo de salida.
     * Si el flujo de salida no es nulo, escribe el comando en el flujo
     * y muestra un mensaje en la consola indicando que el comando fue enviado.
     *
     * @param comando El comando que se enviará al cliente.
     */
    public void enviarComando(String comando) {
        if (salida != null) {
            salida.println(comando);
            System.out.println("Comando enviado desde servidor: " + comando);
        }
    }

    /**
     * Desconecta el servidor cerrando las conexiones con el cliente.
     * 
     * Este método envía un comando de salida al cliente, cierra los flujos de entrada
     * y salida, y finalmente cierra el socket del cliente y del servdor. Si no hay un cliente conectado,
     * se notifica mediante un mensaje en la consola.
     * 
     * @throws IOException Si ocurre un error al cerrar los flujos o el socket.
     */
    public void desconectar() throws IOException {
        if (clienteSocket != null) {
            enviarComando("SALIR");
            salida.close();
            entrada.close();
            clienteSocket.close();
            serverSocket.close();
            System.out.println("Servidor desconectado.");
        } else {
            System.out.println("No hay cliente.");
        }
    }
}
