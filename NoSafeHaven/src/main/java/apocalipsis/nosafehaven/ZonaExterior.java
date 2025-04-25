/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

import java.util.ArrayList;

/**
 *
 * @author cristina
 */
public class ZonaExterior {

    private int ctdzombies = 0;
    private int ctdhumanos = 0;
    private int id;

    private ArrayList<Humano> humanos = new ArrayList<>();
    private ArrayList<Zombie> zombies = new ArrayList<>();

    public ZonaExterior(int id) {
        this.id = id;
    }

    public synchronized void zombieLlegar(Zombie z) {
        ctdzombies++;
        zombies.add(z);
        if (ctdhumanos > 0) {
            zombieAtacar(z);
        }
    }

    public synchronized void zombieAtacar(Zombie z) {
        int humano = (int) (Math.random() * ctdhumanos); //humano en el array en la posicion "humano"
        Humano h = humanos.get(humano);
        
        boolean muerte = false;  //inicializamos a que el humano sobrevive
        if (Math.random() > 0.66) { //1/3 de morir
            muerte = true; //si se cumple, el humano muere y se transforma
            humanoIrse(h); //el humano ha muerto. lo borro para que otro zombie no se piense que siga vivo 
        }
        int tiempoAtaque = (int) (Math.random() * 1000 + 500); //0.5-1.5 seg
        h.serAtacado(muerte, tiempoAtaque);
        z.atacar(muerte, tiempoAtaque);

    }

    public synchronized void zombieIrse(Zombie z) {
        ctdzombies--;
        zombies.remove(z);
    }

    public synchronized void humanoLlegar(Humano h) {
        ctdhumanos++;
        humanos.add(h);

    }

    public synchronized void humanoIrse(Humano h) {
        ctdhumanos--;
        humanos.remove(h);
    }

}
