/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

import java.util.logging.Level;
import java.util.logging.Logger;

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
                int zona = (int) (Math.random()*4); //seleccionar zona de 0-3
                exterior.buscarCerebros(this, zona);
                sleep((int) (Math.random()*1000 + 2000)); //dormir 2-3 s
                exterior.acabarZombie(this, zona);
                //vuelve a buscar zona. se podria repetir la misma (ver si seria necesario que fuera distinta a la anterior)

            } catch (InterruptedException ie) {
            }
        }

    }

    public void atacar(boolean matado, int tiempo) {
        
        try {
            sleep(tiempo);
        } catch (InterruptedException ex) {
        }
        if (matado) {
            bodycount++;
        }
    }
}
