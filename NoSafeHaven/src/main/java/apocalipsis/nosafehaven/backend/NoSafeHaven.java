/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.JDialogServidor;
import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class NoSafeHaven {

    /**
     * Método principal de la aplicación NoSafeHaven.
     * 
     * Este método inicializa el servidor, gestiona los intentos de conexión y, 
     * si la conexión es exitosa, configura y lanza los elementos principales 
     * del juego/aplicación, incluyendo zonas exteriores, refugio, ranking, 
     * humanos y el paciente cero (zombie inicial).
     * 
     * Flujo principal:
     * - Intenta conectar el servidor hasta un máximo de 3 intentos o hasta que 
     *   el usuario decida salir.
     * - Si la conexión es exitosa, se inicializan los componentes principales 
     *   del juego y se crean los hilos correspondientes para los humanos y 
     *   el zombie inicial.
     * - Si la conexión falla, se termina la ejecución del programa.
     * 
     * Manejo de errores:
     * - Captura excepciones generales para registrar errores en el log y 
     *   mostrarlos en la consola.
     * 
     * Detalles de implementación:
     * - Utiliza un diálogo modal para que el usuario configure el puerto del 
     *   servidor.
     * - Implementa un sistema de pausa (EstadoPausa) para sincronizar la 
     *   creación de entidades.
     * - Los humanos y el zombie inicial se ejecutan en hilos separados.
     * 
     * Notas:
     * - El número de humanos creados puede ser configurado modificando la 
     *   variable `numHumanos`.
     * - La velocidad de creación de humanos está influenciada por la clase 
     *   `Velocidad`.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {

        try {
            Servidor servidor = new Servidor();
            PantallaPrincipal.getInstancia().setServidor(servidor);

            int intentos = 3;
            boolean conectado = false;
            boolean cerrar = false;
            while (intentos > 0 && !conectado && !cerrar) {
                JDialogServidor dialogo = new JDialogServidor(null, true); // modal
                dialogo.setLocationRelativeTo(null);  // centrado
                dialogo.setVisible(true);  // esto bloquea hasta que se cierre
                cerrar = dialogo.isQuiereSalir(); //si se cierra el dialogo, no se ejecuta el resto del código

                if (dialogo.isConfirmado() && !cerrar) { //si se ha confirmado el dialogo y no se ha cerrado
                    int puerto = dialogo.getPuerto();
                    servidor.iniciarServidor(puerto);
                    if (servidor.getConectado()) {
                        System.out.println("Perfecto. Conectado");
                        conectado = true;
                    } else {
                        System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                        intentos--;
                    }

                } else if (!cerrar) { //si no se ha confirmado el dialogo y no se ha cerrado
                    System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                    intentos--;
                }
            }

            if (conectado) { //si no está conectado por que hay 2 o más servidores a la vez y no pueden usar el mismo puerto, no ejecuta el resto del código
                PantallaPrincipal.getInstancia().setVisible(true);

                EstadoPausa ep = new EstadoPausa();
                servidor.setEstadopausa(ep);

                Refugio refugio = new Refugio(servidor);
                servidor.setRefugio(refugio); // Asignar el refugio al servidor, para que pueda acceder a él en las conexiones pertinentes

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
                int numHumanos = 10000; // Puedes cambiar el número que quieras
                for (int i = 1; i <= numHumanos; i++) {  // Empezar en 1 para que el id sea H00001, y no H00000 para no tener dos zombis con id Z00000
                    String id = "H" + String.format("%05d", i); // Formatear el número con ceros a la izquierda
                    Humano humano = new Humano(id, refugio, zonas, r, ep);
                    humano.start(); // Arrancar cada hilo de Humano
                    Log.escribir("Creando humano " + id);
                    System.out.println("Creando humano " + id);
                    ep.parar();
                    try {
                        Thread.sleep((int) (Math.random() * 500 + 1500) / Velocidad.getVelocidad());
                    } catch (Exception e) {
                         System.out.println(e.getMessage());
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
