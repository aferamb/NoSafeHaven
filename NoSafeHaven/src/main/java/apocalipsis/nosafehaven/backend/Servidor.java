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
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 * @author 05jan
 */
public class Servidor {

    // privates
    private boolean servidorActivo = false;
    private AtomicInteger numHumRefu = new AtomicInteger(0);
    private AtomicInteger comida = new AtomicInteger(0);

    private AtomicInteger numHumT0 = new AtomicInteger(0);
    private AtomicInteger numHumT1 = new AtomicInteger(0);
    private AtomicInteger numHumT2 = new AtomicInteger(0);
    private AtomicInteger numHumT3 = new AtomicInteger(0);

    private AtomicInteger numHumZR0 = new AtomicInteger(0);
    private AtomicInteger numHumZR1 = new AtomicInteger(0);
    private AtomicInteger numHumZR2 = new AtomicInteger(0);
    private AtomicInteger numHumZR3 = new AtomicInteger(0);

    private AtomicInteger numZomZR0 = new AtomicInteger(0);
    private AtomicInteger numZomZR1 = new AtomicInteger(0); 
    private AtomicInteger numZomZR2 = new AtomicInteger(0);
    private AtomicInteger numZomZR3 = new AtomicInteger(0);
    private String ranking1;
    private String ranking2;   
    private String ranking3;

    Socket clienteSocket;
    private PrintWriter salida;
    private BufferedReader entrada;

    public Servidor() {
        // Constructor vacío
    }

    public void iniciarServidor(int puerto) {
        // Iniciar el servidor y esperar conexiones de clientes
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            // imprimir el puerto y la dirección IP del servidor para la conexión del cliente
            System.out.println("Servidor iniciado en: " + serverSocket.getInetAddress().getHostAddress() + ":" + puerto);
            System.out.println("Servidor iniciado en el puerto: " + puerto);
            System.out.println("Esperando conexión del cliente...");

            clienteSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");

            salida = new PrintWriter(clienteSocket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));

            //   Otra forma de crear el BufferedReader
            //try (BufferedReader tempEntrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {
            //    entrada = tempEntrada;
            //}

            servidorActivo = true;
            // Hilo para escuchar comandos del cliente
            Thread comandos = new Thread(() -> {
                try {
                    String linea;
                    while ((linea = entrada.readLine()) != null) {
                        if (linea.equalsIgnoreCase("PARAR")) {
                            // comando para parar la simulación
                            System.out.println("Simulación pausada por el cliente.");
                        } else if (linea.equalsIgnoreCase("REANUDAR")) {
                            // comanode para reanudar la simulación
                            System.out.println("Simulación reanudada por el cliente.");
                        } else if (linea.equalsIgnoreCase("SALIR")) {
                            salida.close();
                            entrada.close();
                            clienteSocket.close();
                            servidorActivo = false;
                            System.out.println("Servidor detenido por el cliente.");
                            System.out.println("Cliente desconectado.");
                            break;
                        } else {
                            System.out.println("Comando desconocido: " + linea);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Conexión cerrada por el cliente.");
                }
            });
            comandos.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void enviarDatosAlCliente() { // cuidado, cada llamada de actualizar datos llama a este metodo SINCRONIZED
        if (!servidorActivo) {
            System.out.println("Servidor no activo. No se pueden enviar datos.");
            return;
        }
        //try {
            // Construcción del mensaje
            StringBuilder mensaje = new StringBuilder();  // igual guardarlo todo en array list y luego pasarlo a stringbuilder es mejor xd
            mensaje.append("numHumRefu=").append(numHumRefu).append(";");
            mensaje.append("comida=").append(comida).append(";");

            mensaje.append("numHumT0=").append(numHumT0).append(";");
            mensaje.append("numHumT1=").append(numHumT1).append(";");
            mensaje.append("numHumT2=").append(numHumT2).append(";");
            mensaje.append("numHumT3=").append(numHumT3).append(";");

            mensaje.append("numHumZR0=").append(numHumZR0).append(";");
            mensaje.append("numHumZR1=").append(numHumZR1).append(";");
            mensaje.append("numHumZR2=").append(numHumZR2).append(";");
            mensaje.append("numHumZR3=").append(numHumZR3).append(";");

            mensaje.append("numZomZR0=").append(numZomZR0).append(";");
            mensaje.append("numZomZR1=").append(numZomZR1).append(";");
            mensaje.append("numZomZR2=").append(numZomZR2).append(";");
            mensaje.append("numZomZR3=").append(numZomZR3).append(";");

            mensaje.append("ranking1=").append(ranking1).append(";");
            mensaje.append("ranking2=").append(ranking2).append(";");
            mensaje.append("ranking3=").append(ranking3).append(";");

            // Enviar mensaje
            salida.println(mensaje.toString());
            System.out.println("Enviado al cliente: " + mensaje);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }

    public void actualizarDatosRefugio(int numHumanos) {
        numHumRefu.set(numHumanos);
        enviarDatosAlCliente();
    }

    public void actualizarDatosComida(int comida) {
        this.comida.set(comida);
        enviarDatosAlCliente();
    }

    public void actualizarDatosTuneles(int tunel, int numHumanos) {
        switch (tunel) {
            case 0:
                numHumT0.set(numHumanos);
                break;
            case 1:
                numHumT1.set(numHumanos);
                break;
            case 2:
                numHumT2.set(numHumanos);
                break;
            case 3:
                numHumT3.set(numHumanos);
                break;
            default:
                System.out.println("Túnel inválido: " + tunel);
        }
        enviarDatosAlCliente();
    }

    public void actualizarDatosHumanosZonaRiesgo(int zona, int numHumanos) {
        switch (zona) {
            case 0:
                numHumZR0.set(numHumanos);
                break;
            case 1:
                numHumZR1.set(numHumanos);
                break;
            case 2:
                numHumZR2.set(numHumanos);
                break;
            case 3:
                numHumZR3.set(numHumanos);
                break;
            default:
                System.out.println("Zona inválida: " + zona);
        }
        enviarDatosAlCliente();
    }

    public void actualizarDatosZombiesZonaRiesgo(int zona, int numZombies) {
        switch (zona) {
            case 0:
                numZomZR0.set(numZombies);
                break;
            case 1:
                numZomZR1.set(numZombies);
                break;
            case 2:
                numZomZR2.set(numZombies);
                break;
            case 3:
                numZomZR3.set(numZombies);
                break;
            default:
                System.out.println("Zona inválida: " + zona);
        }
        enviarDatosAlCliente();
    }

    public void actualizarRanking(ArrayList<String> ranking) {
        if (ranking.size() == 3) {
            ranking1 = ranking.get(0);
            ranking2 = ranking.get(1);
            ranking3 = ranking.get(2);
        } else {
            ranking1 = "Nadie";
            ranking2 = "Nadie";
            ranking3 = "Nadie";
        }
        enviarDatosAlCliente();
    }
    
    public void enviarComando(String comando) {
        if (salida != null) {
            salida.println(comando);
            System.out.println("Comando enviado desde servidor: " + comando);
        }
    }

    public void desconectar() throws IOException {
        enviarComando("SALIR");
        if (clienteSocket != null){
            salida.close();
            entrada.close();
            clienteSocket.close();
        }
        System.out.println("Cliente desconectado.");
    }
}