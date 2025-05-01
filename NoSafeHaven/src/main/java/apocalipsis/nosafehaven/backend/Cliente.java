/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import java.util.Scanner;

/**
 *
 * @author 05jan
 */
public class Cliente extends Thread { // el tread es porque no sabia como hacer para que el cliente se quedara escuchando al servidor sin crear hilos a parte

    private Socket socket;
    private PrintWriter salida;
    private BufferedReader entrada;
    //private Scanner scanner = new Scanner(System.in);


    public void run() {
        try {
            conectarAlServidor("localhost", 5000);
        } catch (IOException e) {
            System.out.println("Error al conectar al servidor: " + e.getMessage());
        }
    }

    public void conectarAlServidor(String host, int puerto) throws IOException {
        socket = new Socket(host, puerto);
        System.out.println("Conectado al servidor en " + host + ":" + puerto);

        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(socket.getOutputStream(), true);

        iniciarRecepcion();
    }

    public void iniciarRecepcion() {
        Thread receptor = new Thread(() -> {
            try {
                String linea;
                while ((linea = entrada.readLine()) != null) {   // cambia null por un mensaje de parada del hilo al cerrar el socket o servidor
                    if (linea.equalsIgnoreCase("SALIR")) {
                        salida.close();
                        entrada.close();
                        socket.close();
                        System.out.println("conexion detenido por el servidor.");
                        System.out.println(" desconectado.");
                        break;
                    }else if (linea.length() >= 4 && linea.substring(0, 4).equals("data")) {
                        String datos = linea.substring(4);
                    
                        System.out.println("\n--- Datos recibidos ---");
                        String[] partes = datos.split(";");

                        for (String parte : partes) {
                            if (parte.contains("=")) {
                                String[] kv = parte.split("=", 2);
                                String clave = kv[0];
                                String valor = kv[1];

                                if (clave.equals("numHumRefu")) {
                                    
                                } else if (clave.equals("comida")) {
                                    
                                } else if (clave.equals("numHumT0")) {
                                    
                                } else if (clave.equals("numHumT1")) {
                                    
                                } else if (clave.equals("numHumT2")) {
                                    
                                } else if (clave.equals("numHumT3")) {
                                    
                                } else if (clave.equals("numHumZR0")) {
                                    
                                } else if (clave.equals("numHumZR1")) {
                                    
                                } else if (clave.equals("numHumZR2")) {
                                    
                                } else if (clave.equals("numHumZR3")) {
                                    
                                } else if (clave.equals("numZomZR0")) {
                                    
                                } else if (clave.equals("numZomZR1")) {
                                    
                                } else if (clave.equals("numZomZR2")) {
                                    
                                } else if (clave.equals("numZomZR3")) {
                                    
                                } else if (clave.equals("ranking1")) {
                                    
                                } else if (clave.equals("ranking2")) {
                                    
                                } else if (clave.equals("ranking3")) {
                                    
                                } else {
                                    System.out.println("Clave desconocida: " + clave);
                                }
                            } else {
                                System.out.println("Formato de datos incorrecto: " + parte);
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Conexión cerrada por el servidor.");
            }
        });
        receptor.start();
    }

    public void enviarComando(String comando) {
        if (salida != null) {
            salida.println(comando);
            System.out.println("Comando enviado desde cliente: " + comando);
        }
    }

    public void desconectar() throws IOException {
        enviarComando("SALIR");
        if (socket != null){
            salida.close();
            entrada.close();
            socket.close();
        }
        System.out.println("Cliente desconectado.");
    }
}