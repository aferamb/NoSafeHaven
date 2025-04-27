/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author cristina
 */
public class ZonaExterior {

    private AtomicInteger ctdzombies = new AtomicInteger(0);
    private AtomicInteger ctdhumanos = new AtomicInteger(0);
    private int id;

    private ArrayList<Humano> humanos = new ArrayList<>();
    private ArrayList<Zombie> zombies = new ArrayList<>();

    public ZonaExterior(int id) {
        this.id = id;
    }

    public synchronized void zombieLlegar(Zombie z) {
        ctdzombies.incrementAndGet();
        zombies.add(z);
        if (ctdhumanos.get() > 0) {
            zombieAtacar(z); // debido a esto se podria bloquear este metodo durante el tiempo de ataque del zombie
        }
    }

    public synchronized void zombieAtacar(Zombie z) { // cuidado, si el humano esta herido no se le puede volver a atacar, habra que quitarlo nada mas ser atacado, pero habria que moidificar wl codigo de humano para cuando es atacado no salga dos veces de la zona exterior
        int humano = (int) (Math.random() * ctdhumanos.get()); //humano en el array en la posicion "humano"
        Humano h = humanos.get(humano);
        
        boolean muerte = false;  //inicializamos a que el humano sobrevive
        if (Math.random() > 0.66) { //1/3 de morir
            muerte = true; //si se cumple, el humano muere y se transforma
            humanoIrse(h); //el humano ha muerto. lo borro para que otro zombie no se piense que siga vivo 
        }
        int tiempoAtaque = (int) (Math.random() * 1000 + 500); //0.5-1.5 seg
        z.atacar(muerte, tiempoAtaque, h.getid()); //Se le pasa el id del humano para los logs
        h.serAtacado(muerte, tiempoAtaque);

    }

    public synchronized void zombieIrse(Zombie z) {
        ctdzombies.decrementAndGet();
        zombies.remove(z);
    }

    public synchronized void humanoLlegar(Humano h) {
        ctdhumanos.incrementAndGet();
        humanos.add(h);

    }

    public synchronized void humanoIrse(Humano h) {
        ctdhumanos.decrementAndGet();
        humanos.remove(h);
    }

}
