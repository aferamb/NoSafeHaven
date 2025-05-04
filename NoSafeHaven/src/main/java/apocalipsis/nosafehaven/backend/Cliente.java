/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.ClientePantalla;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    private boolean conectado = false;


    /**
     * Establece una conexión con un servidor utilizando un socket.
     * Este método intenta crear un socket para conectarse al servidor especificado
     * en el host y puerto proporcionados. Si la conexión es exitosa, se inicializan
     * los streams de entrada y salida para la comunicación con el servidor. En caso
     * de error, se captura la excepción y se imprime un mensaje de error.
     *
     * @param host  La dirección del servidor al que se desea conectar.
     * @param puerto El número de puerto en el que el servidor está escuchando.
     */
    public void conectarAlServidor(String host, int puerto) {
        //Apertura de sockets (en la parte cliente)
        try {
            // Crear un socket para conectarse al servidor
            socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            //Creación de streams de entrada (en cliente)
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            conectado = true;

        } catch (IOException e) {
            System.out.println("Error al conectar al servidor: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    /**
     * Inicia un hilo para la recepción de datos desde el servidor.
     * Este método crea un hilo que escucha continuamente los mensajes enviados
     * por el servidor a través de un socket. Dependiendo del contenido del mensaje,
     * se realizan diferentes acciones, como actualizar datos en la interfaz de usuario
     * o cerrar la conexión si se recibe un mensaje de salida.
     * 
     * Comportamiento:
     * - Si el mensaje recibido es "SALIR", se cierran las conexiones y se detiene la aplicación.
     * - Si el mensaje comienza con "data", se interpreta como un conjunto de datos clave-valor
     *   y se actualizan los elementos correspondientes en la interfaz de usuario.
     * - Si el formato del mensaje no es reconocido, se imprime un mensaje de error en la consola.
     * 
     * Excepciones:
     * - Captura y maneja IOException en caso de errores de entrada/salida durante la recepción de datos.
     * 
     * Notas:
     * - Este método utiliza un hilo separado para evitar bloquear el hilo principal de la aplicación.
     * - Asegúrese de que las instancias de ClientePantalla estén correctamente inicializadas antes de llamar a este método.
     */
    public void iniciarRecepcion() {
        Thread receptor = new Thread(() -> {
            System.out.println("Comienza la recepción");
            try {
                String linea;
                while ((linea = entrada.readLine()) != null) {   // cambia null por un mensaje de parada del hilo al cerrar el socket o servidor
                    if (linea.equalsIgnoreCase("SALIR")) {
                        salida.close();
                        entrada.close();
                        socket.close();
                        System.out.println("Conexion detenida por el servidor.");
                        System.exit(0);

                    } else if (linea.length() >= 4 && linea.substring(0, 4).equals("data")) {
                        String datos = linea.substring(4);

                        if (datos.contains("=")) {
                            String[] kv = datos.split("=", 2);
                            String clave = kv[0];
                            String valor = kv[1];

                            if (clave.equals("numHumRefu")) {
                                ClientePantalla.getInstancia().actualizarRefugio(valor);
                            } else if (clave.equals("numHumTotal")) {
                                ClientePantalla.getInstancia().actualizarTotalHumanos(valor);
                            } else if (clave.equals("numZomTotal")) {
                                ClientePantalla.getInstancia().actualizarTotalZombies(valor);
                            } else if (clave.equals("comida")) {
                                ClientePantalla.getInstancia().actualizarComida(valor);

                            } else if (clave.equals("numHumT0")) {
                                ClientePantalla.getInstancia().actualizarTunel(0, valor);
                            } else if (clave.equals("numHumT1")) {
                                ClientePantalla.getInstancia().actualizarTunel(1, valor);
                            } else if (clave.equals("numHumT2")) {
                                ClientePantalla.getInstancia().actualizarTunel(2, valor);
                            } else if (clave.equals("numHumT3")) {
                                ClientePantalla.getInstancia().actualizarTunel(3, valor);

                            } else if (clave.equals("numHumZR0")) {
                                ClientePantalla.getInstancia().actualizarExteriorHumanos(0, valor);
                            } else if (clave.equals("numHumZR1")) {
                                ClientePantalla.getInstancia().actualizarExteriorHumanos(1, valor);
                            } else if (clave.equals("numHumZR2")) {
                                ClientePantalla.getInstancia().actualizarExteriorHumanos(2, valor);
                            } else if (clave.equals("numHumZR3")) {
                                ClientePantalla.getInstancia().actualizarExteriorHumanos(3, valor);

                            } else if (clave.equals("numZomZR0")) {
                                ClientePantalla.getInstancia().actualizarExteriorZombies(0, valor);
                            } else if (clave.equals("numZomZR1")) {
                                ClientePantalla.getInstancia().actualizarExteriorZombies(1, valor);
                            } else if (clave.equals("numZomZR2")) {
                                ClientePantalla.getInstancia().actualizarExteriorZombies(2, valor);
                            } else if (clave.equals("numZomZR3")) {
                                ClientePantalla.getInstancia().actualizarExteriorZombies(3, valor);

                            } else if (clave.equals("ranking1")) {
                                ClientePantalla.getInstancia().actualizarRanking(1, valor);
                            } else if (clave.equals("ranking2")) {
                                ClientePantalla.getInstancia().actualizarRanking(2, valor);
                            } else if (clave.equals("ranking3")) {
                                ClientePantalla.getInstancia().actualizarRanking(3, valor);
                            } else {
                                System.out.println("Clave desconocida: " + clave);
                            }
                        } else {
                            System.out.println("Formato de datos incorrecto: " + datos);
                        }

                    }
                }
            } catch (IOException e) {
                System.out.println("Cerrado");
            }
        }
        );
        receptor.start();
    }

    /**
     * Método para comprobar si el cliente está conectado al servidor.
     * 
     * @return true si el cliente está conectado, false en caso contrario.
     */
    public boolean getConectado() {
        return conectado;
    }


    /**
     * Envía un comando al servidor a través de la salida establecida.
     * Si la salida no es nula, escribe el comando en el flujo de salida
     * y muestra un mensaje en la consola indicando que el comando ha sido enviado.
     *
     * @param comando El comando que se desea enviar al servidor.
     */
    public void enviarComando(String comando) {
        if (salida != null) {
            salida.println(comando);
            System.out.println("Comando enviado desde cliente: " + comando);
        }
    }

    /**
     * Cierra la conexión del cliente con el servidor.
     * Este método envía un comando de salida al servidor y cierra los streams
     * de entrada y salida, así como el socket. Si el socket es nulo, se imprime
     * un mensaje indicando que no se puede desconectar.
     *
     * @throws IOException Si ocurre un error al cerrar los streams o el socket.
     */
    public void desconectar() throws IOException {
        if (socket != null) {
            enviarComando("SALIR");
            salida.close();
            entrada.close();
            socket.close();
            System.out.println("Cliente desconectado.");
        } else {
            System.out.println("Socket null.");
        }

    }
}
