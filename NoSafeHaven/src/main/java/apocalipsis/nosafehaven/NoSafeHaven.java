/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package apocalipsis.nosafehaven;

/**
 *
 * @author 05jan
 */
public class NoSafeHaven {

    public static void main(String[] args) {
        // Crear el Refugio y el Exterior
        Refugio refugio = new Refugio();
        Exterior exterior = new Exterior();
        
        // Crear un número de Humanos
        int numHumanos = 10; // Puedes cambiar el número que quieras
        for (int i = 0; i < numHumanos; i++) {
            Humano humano = new Humano("Humano-" + i, refugio, exterior);
            humano.start(); // Arrancar cada hilo de Humano
            System.out.println("Creando humano");
            try {
                Thread.sleep((int) (Math.random() * 500 + 1500)); //en zona comun 1 a 2 seg
            } catch (Exception e) {
            }
        }

        // Nota: Los Zombis se generarán automáticamente cuando un humano muera
        // por lo tanto aquí no creamos zombies manualmente.

        // El programa no termina nunca porque los humanos y zombis son hilos que funcionan "para siempre" (hasta morir o eternamente en el caso de los zombis).
    }
}
