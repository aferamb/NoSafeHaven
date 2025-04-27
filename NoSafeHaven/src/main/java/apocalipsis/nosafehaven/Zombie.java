/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

/**
 *
 * @author 05jan
 */
public class Zombie extends Thread {

    private String id;
    private int bodycount = 0;
    private Exterior exterior;

    public Zombie(String id, Exterior exterior) {
        this.id = "Z" + id.substring(1); //id del humano sin la H
        this.exterior = exterior;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int zona = (int) (Math.random() * 4); //seleccionar zona de 0-3
                Log.escribir(id + " se dirige a la zona exterior " + zona + ".");
                System.out.println(id + " se dirige a la zona exterior " + zona + ".");

                Log.escribir(id + " esta buscando cerebros en la zona exterior " + zona + ".");
                System.out.println(id + " está buscando cerebros en la zona exterior " + zona + ".");
                exterior.buscarCerebros(this, zona); // si llega un humano a la zona despueas de que el; zombie revise este no es atacado !!!!!!!!!!!

                sleep((int) (Math.random() * 1000 + 2000)); //dormir 2-3 s
                exterior.acabarZombie(this, zona);
                Log.escribir(id + " abandona la zona exterior " + zona + ".");
                System.out.println(id + " abandona la zona exterior " + zona + ".");
                //vuelve a buscar zona. se podria repetir la misma (ver si seria necesario que fuera distinta a la anterior)

            } catch (InterruptedException ie) {
                Log.escribir(id + " ha sido interrumpido.");
                System.out.println(id + " ha sido interrumpido.");
            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                e.printStackTrace(); // muy importante para saber qué pasó
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
            } finally {
                // Asegúrate de que el zombie siempre sale de la zona exterior
            }
        }

    }

    public void atacar(boolean matado, int tiempo, String hid) {

        try {
            sleep(tiempo);
        } catch (InterruptedException ex) {
        }
        if (matado) {
            bodycount++;
            Log.escribir(id + " ha matado al humano " + hid + ". Total muertos: " + bodycount + ".");
            System.out.println(id + " ha matado al humano " + hid + ". Total muertos: " + bodycount + ".");
        } else {
            Log.escribir(id + " ha atacado y herido al humano " + hid + ".");
            System.out.println(id + " ha atacado y herido al humano " + hid + ".");
        }
    }
}
