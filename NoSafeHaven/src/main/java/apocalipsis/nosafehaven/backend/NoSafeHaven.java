/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package apocalipsis.nosafehaven.backend;

/**
 *
 * @author 05jan
 */
public class NoSafeHaven {

    public static void main(String[] args) {
        // Crear el Refugio y el Exterior
        Refugio refugio = new Refugio();
        Exterior exterior = new Exterior();
        
        Zombie zombie = new Zombie("Z00000", exterior); // Crear paciente 0
        zombie.start(); // Arrancar el hilo del Zombie
        Log.escribir("Creando zombie Z00000");
        System.out.println("Creando zombie Z00000");

        // Crear un número de Humanos
        int numHumanos = 30; // Puedes cambiar el número que quieras
        for (int i = 1; i <= numHumanos; i++) {  // Empezar en 1 para que el id sea H00001, y no H00000 para no tener dos zombis con id Z00000
            String id = "H" + String.format("%05d", i); // Formatear el número con ceros a la izquierda
            Humano humano = new Humano(id, refugio, exterior);
            humano.start(); // Arrancar cada hilo de Humano
            Log.escribir("Creando humano " + id);
            System.out.println("Creando humano " + id);
            try {
                Thread.sleep((int) (Math.random() * 500 + 1500)); //en zona comun 0,5 a 2 seg
            } catch (Exception e) {
            }
        }
    }
}
