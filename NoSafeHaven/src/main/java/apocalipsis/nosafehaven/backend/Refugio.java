/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 05jan
 */
public class Refugio {

    private AtomicInteger comida = new AtomicInteger(0);
    private AtomicInteger humanos = new AtomicInteger(0);

    private AtomicInteger enZonaComun = new AtomicInteger(0);
    private ArrayList<String> humanosZonaComun = new ArrayList<>();

    private AtomicInteger enCama = new AtomicInteger(0);
    private ArrayList<String> humanosZonaDescanso = new ArrayList<>();

    private AtomicInteger enComedor = new AtomicInteger(0);
    private ArrayList<String> humanosComedor = new ArrayList<>();

    private Tunel[] tuneles = new Tunel[4]; // Array de túneles

    public Refugio() {
        for (int i = 0; i < 4; i++) {
            tuneles[i] = new Tunel(i);
        }
    }

    public synchronized void dejarComida() { 
        comida.addAndGet(2);
        //PantallaPrincipal.actualizarComida(comida.get());
        notifyAll();
    }

    public void irZonaComun() { 
        enZonaComun.incrementAndGet();
    }

    public void salirZonaComun() { 
        enZonaComun.decrementAndGet();
    }

    public void irZonaDescanso() { 
        enCama.incrementAndGet();
    }

    public void salirZonaDescanso() { 
        enCama.decrementAndGet();
    }

    public synchronized void irComedor() throws InterruptedException { 
        enComedor.incrementAndGet();
        while (comida.get() == 0) {
            wait();
        }
        comida.decrementAndGet(); //comer 1 comida
    }

    public void salirComedor() { 
        enComedor.decrementAndGet();

    }

    public void salirRefugio(int tunel) throws Exception {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].salirRefugio();
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    public void entrarRefugio(int tunel) throws Exception {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].entrarRefugio();
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    public void salirTunel(int tunel) throws InterruptedException {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].salirTunel();
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

}
