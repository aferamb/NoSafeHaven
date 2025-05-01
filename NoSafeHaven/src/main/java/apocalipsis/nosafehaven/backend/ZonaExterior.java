/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ZonaExterior {

    private AtomicInteger ctdzombies = new AtomicInteger(0);
    private AtomicInteger ctdhumanos = new AtomicInteger(0);
    private int id;
    private Servidor servidor;

    private CopyOnWriteArrayList<Humano> humanos = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Zombie> zombies = new CopyOnWriteArrayList<>();

    private CopyOnWriteArrayList<String> listaIDs = new CopyOnWriteArrayList<>(); // IDs de los humanos y zombies en la zona exterior

    public ZonaExterior(int id, Servidor servidor) {
        this.id = id;
        this.servidor = servidor;
    }


    public void zombieLlegar(Zombie z) {
        ctdzombies.incrementAndGet();
        listaIDs.add(z.getid()); //añadimos el id del zombie a la lista de ids
        zombies.add(z);
        PantallaPrincipal.getInstancia().actualizarExterior(id, listaIDs); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosZombiesZonaRiesgo(id, ctdzombies.get()); //actualizo la capacidad de la zona exterior

    }

    public synchronized void zombieAtacar(Zombie z) { // cuidado, si el humano esta herido no se le puede volver a atacar, habra que quitarlo nada mas ser atacado, pero habria que moidificar wl codigo de humano para cuando es atacado no salga dos veces de la zona exterior
        if (ctdhumanos.get() > 0) {
            int humano = (int) (Math.random() * ctdhumanos.get()); //humano en el array en la posicion "humano"
            Humano h = humanos.get(humano);
            
            
            h.setSiendoAtacado(true); //marcar que esta siendo atacado

            boolean muerte = false;  //inicializamos a que el humano sobrevive
            if (Math.random() > 0.66) { //1/3 de morir
                muerte = true; //si se cumple, el humano muere y se transforma
                h.setMuerto(true);

            } else {
                h.setHerido(true);
            }
            humanoIrse(h); //el humano ha muerto. lo borro para que otro zombie no se piense que siga vivo 
            h.interrupt();

            int tiempoAtaque = (int) (Math.random() * 1000 + 500); //0.5-1.5 seg
            z.atacar(muerte, tiempoAtaque, h.getid()); //Se le pasa el id del humano para los logs
            h.setSiendoAtacado(false);
            finAtaque();

        }

    }

    public void zombieIrse(Zombie z) {
        ctdzombies.decrementAndGet();
        listaIDs.remove(z.getid()); //eliminamos el id del zombie de la lista de ids
        zombies.remove(z);
        PantallaPrincipal.getInstancia().actualizarExterior(id, listaIDs); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosZombiesZonaRiesgo(id, ctdzombies.get()); //actualizo la capacidad de la zona exterior
    }

    public void humanoLlegar(Humano h) {
        ctdhumanos.incrementAndGet();
        listaIDs.add(h.getid()); //añadimos el id del humano a la lista de ids
        humanos.add(h);
        PantallaPrincipal.getInstancia().actualizarExterior(id, listaIDs); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosHumanosZonaRiesgo(id, ctdhumanos.get()); //actualizo la capacidad de la zona exterior
    }

    public void humanoIrse(Humano h) {
        ctdhumanos.decrementAndGet();
        listaIDs.remove(h.getid()); //eliminamos el id del humano de la lista de ids
        humanos.remove(h);
        PantallaPrincipal.getInstancia().actualizarExterior(id, listaIDs); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosHumanosZonaRiesgo(id, ctdhumanos.get()); //actualizo la capacidad de la zona exterior
    }
    

    public synchronized void humanoAtacado(Humano h) {
        while (h.isSiendoAtacado()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaExterior.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public synchronized void finAtaque() {
        notifyAll();
        ///////////////////////////////////////////// ver si renta mas meter arriba synchronized(this){notifyAll();}
    }

}
