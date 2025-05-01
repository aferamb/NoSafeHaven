/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;


public class NoSafeHaven {
/////////////////////////////////////////////////////////////////////////TODO: aumentar con boton velocidad de creacion de humanos!!
    public static void main(String[] args) {
        
        PantallaPrincipal.getInstancia().setVisible(true);
        try{

            Servidor servidor = new Servidor();
            //servidor.iniciarServidor(5000);

            Cliente cliente = new Cliente();
            cliente.run();

            Refugio refugio = new Refugio(servidor);

            ZonaExterior[] zonas = new ZonaExterior[4];
            Ranking r = new Ranking(servidor);

            for (int i = 0; i < 4; i++) {
                zonas[i] = new ZonaExterior(i, servidor);
            }

            PantallaPrincipal.getInstancia().parar();
            Zombie zombie = new Zombie("Z00000", zonas, r); // Crear paciente 0
            zombie.start(); // Arrancar el hilo del Zombie
            Log.escribir("Creando zombie Z00000");
            System.out.println("Creando zombie Z00000");
            PantallaPrincipal.getInstancia().parar();
            // Crear un número de Humanos
            int numHumanos = 1000; // Puedes cambiar el número que quieras
            for (int i = 1; i <= numHumanos; i++) {  // Empezar en 1 para que el id sea H00001, y no H00000 para no tener dos zombis con id Z00000
                String id = "H" + String.format("%05d", i); // Formatear el número con ceros a la izquierda
                Humano humano = new Humano(id, refugio, zonas, r);
                humano.start(); // Arrancar cada hilo de Humano
                Log.escribir("Creando humano " + id);
                System.out.println("Creando humano " + id);
                PantallaPrincipal.getInstancia().parar();
                try {
                    Thread.sleep((int) (Math.random() * 500 + 1500)); //en zona comun 0,5 a 2 seg
                } catch (Exception e) {
                }
            }
        }catch (Exception e){
            Log.escribir("Error en el main: " + e.getMessage());
            System.out.println("Error en el main " + e.getMessage());
        }finally{
            //nada xd
        }
    }
}
