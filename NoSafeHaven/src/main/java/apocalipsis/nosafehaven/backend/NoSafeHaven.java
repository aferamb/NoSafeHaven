/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.JDialogServidor;
import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class NoSafeHaven {

    public static void main(String[] args) {

        try {
            Servidor servidor = new Servidor();
            PantallaPrincipal.getInstancia().setServidor(servidor);

            int intentos = 3;
            boolean conectado = false;
            while (intentos > 0 && !conectado) {
                JDialogServidor dialogo = new JDialogServidor(null, true); // modal
                dialogo.setLocationRelativeTo(null);  // centrado
                dialogo.setVisible(true);  // esto bloquea hasta que se cierre

                if (dialogo.isConfirmado()) {
                    int puerto = dialogo.getPuerto();
                    servidor.iniciarServidor(puerto);
                    if (servidor.getConectado()) {
                        System.out.println("Perfecto. Conectado");
                        conectado = true;
                    } else {
                        System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                        intentos--;
                    }

                } else {
                    System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                    intentos--;
                }

            }

            //servidor.iniciarServidor(5000);

            if (conectado) { //si no está conectado por que hay 2 o más servidores a la vez y no pueden usar el mismo puerto, no ejecuta el resto del código
                PantallaPrincipal.getInstancia().setVisible(true);

                EstadoPausa ep = new EstadoPausa();
                servidor.setEstadopausa(ep);

                Refugio refugio = new Refugio(servidor);

                ZonaExterior[] zonas = new ZonaExterior[4];
                Ranking r = new Ranking(servidor);

                for (int i = 0; i < 4; i++) {
                    zonas[i] = new ZonaExterior(i, servidor);
                }

                ep.parar();
                Zombie zombie = new Zombie("Z00000", zonas, r, ep); // Crear paciente 0
                zombie.start(); // Arrancar el hilo del Zombie
                Log.escribir("Creando zombie Z00000");
                System.out.println("Creando zombie Z00000");

                ep.parar();
                int numHumanos = 1000; // Puedes cambiar el número que quieras
                for (int i = 1; i <= numHumanos; i++) {  // Empezar en 1 para que el id sea H00001, y no H00000 para no tener dos zombis con id Z00000
                    if (ep.estaDesconectado()) {
                        break;
                    } else {
                        String id = "H" + String.format("%05d", i); // Formatear el número con ceros a la izquierda
                        Humano humano = new Humano(id, refugio, zonas, r, ep);
                        humano.start(); // Arrancar cada hilo de Humano
                        Log.escribir("Creando humano " + id);
                        System.out.println("Creando humano " + id);
                        ep.parar();
                        try {
                            Thread.sleep((int) (Math.random() * 500 + 1500)/Velocidad.getVelocidad()); 
                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                System.out.println("no se pudo crear el servidor");
                System.exit(0);
            }
        } catch (Exception e) {
            Log.escribir("Error en el main: " + e.getMessage());
            System.out.println("Error en el main " + e.getMessage());
        }
    }
}
