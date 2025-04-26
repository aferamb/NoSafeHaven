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
        this.id = id;
        this.exterior = exterior;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int zona = (int) (Math.random() * 4); //seleccionar zona de 0-3
                Log.escribir("Z" + id + " se dirige a la zona exterior " + zona + ".");
                exterior.buscarCerebros(this, zona);
                Log.escribir("Z" + id + " está buscando cerebros en la zona exterior " + zona + ".");
                sleep((int) (Math.random() * 1000 + 2000)); //dormir 2-3 s
                exterior.acabarZombie(this, zona);
                Log.escribir("Z" + id + " abandona la zona exterior " + zona + ".");
                //vuelve a buscar zona. se podria repetir la misma (ver si seria necesario que fuera distinta a la anterior)

            } catch (InterruptedException ie) {
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
            Log.escribir("Z" + id + " ha matado al humano H" + hid + ". Total muertos: " + bodycount + ".");
        } else {
            Log.escribir("Z" + id + " ha atacado y herido al humano H" + hid + ".");
        }
    }
}
