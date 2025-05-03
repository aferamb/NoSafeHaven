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

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

/**
 *
 * @author 05jan
 */
public class Servidor {

    Socket clienteSocket;
    private PrintWriter salida;
    private BufferedReader entrada;

    private EstadoPausa estadoPausa;

    private boolean conectado = false;

    private Refugio refugio;

    public Servidor() {
        // Constructor vacío
    }

    public void setEstadopausa(EstadoPausa estadoPausa) {
        this.estadoPausa = estadoPausa;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    public void iniciarServidor(int puerto) {
        // Iniciar el servidor y esperar conexiones de clientes
        //Apertura de Sockets (en la parte del servidor)
        try (ServerSocket serverSocket = new ServerSocket(puerto)) { //Creamos un objeto ServerSocket para que esté atento a las conexiones de clientes potenciales

            // Imprimir el puerto y la dirección IP del servidor para la conexión del cliente
            System.out.println("Servidor iniciado en: " + serverSocket.getInetAddress().getHostAddress() + ":" + puerto); ////////////////////////////?
            System.out.println("Esperando conexión del cliente...");

            //Crear objeto Socket para poder enviar y recibir datos
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
                                refugio.setComida(comida);
                                System.out.println("Comida cambiada a " + comida + " por cliente.");
                            } catch (NumberFormatException e) {
                                System.out.println("Comida inválida.");
                            }
                        } else if (linea.equalsIgnoreCase("SALIR")) {
                            estadoPausa.desconectar();
                            salida.close();
                            entrada.close();
                            clienteSocket.close();
                            conectado = false;
                            PantallaPrincipal.getInstancia().apagar();
                            System.out.println("Servidor detenido por el cliente.");
                            System.out.println("Cliente desconectado.");
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

    public void actualizarDatosRefugio(int numHumanos) {
        salida.println("datanumHumRefu=" + numHumanos);
    }

    public void actualizarDatosComida(int comida) {
        salida.println("datacomida=" + comida);
    }

    public void actualizarDatosHumanosTotal(int numHumanos) {
        salida.println("datanumHumTotal=" + numHumanos);
    }

    public void actualizarDatosZombiesTotal(int numZombies) {
        salida.println("datanumZomTotal=" + numZombies);
    }

    public void actualizarDatosTuneles(int tunel, int numHumanos) {
        salida.println("datanumHumT" + tunel + "=" + numHumanos);
    }

    public void actualizarDatosHumanosZonaRiesgo(int zona, int numHumanos) {
        salida.println("datanumHumZR" + zona + "=" + numHumanos);
    }

    public void actualizarDatosZombiesZonaRiesgo(int zona, int numZombies) {
        salida.println("datanumZomZR" + zona + "=" + numZombies);
    }

    public void actualizarRanking(ArrayList<String> ranking) {
        for (int i = 0; i < ranking.size(); i++) {
            salida.println("dataranking" + (i + 1) + "=" + ranking.get(i));
        }
    }

    public void enviarComando(String comando) {
        if (salida != null) {
            salida.println(comando);
            System.out.println("Comando enviado desde servidor: " + comando);
        }
    }

    public void desconectar() throws IOException {

        if (clienteSocket != null) {
            enviarComando("SALIR");
            estadoPausa.desconectar();
            salida.close();
            entrada.close();
            clienteSocket.close();
            System.out.println("Servidor desconectado.");
        } else {
            System.out.println("No hay cliente.");
        }
    }
}
