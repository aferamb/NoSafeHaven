/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

/**
 *
 * @author 05jan
 */
public class Refugio {

    private int comida = 0;
    private int humanos = 0;

    private int enZonaComun = 0;
    private int enCama = 0;
    private int enComedor = 0;

    private Tunel[] tuneles = new Tunel[4]; // Array de túneles

    public Refugio() {
        for (int i = 0; i < 4; i++) {
            tuneles[i] = new Tunel(i);
        }
    }

    public synchronized void dejarComida() { //bloquea el refugio entero. usar lock??????
        comida += 2;
        notifyAll();
    }

    public synchronized void irZonaComun() { //bloquea el refugio entero
        enZonaComun++;
    }

    public synchronized void salirZonaComun() { //bloquea el refugio entero
        enZonaComun--;
    }

    public synchronized void irZonaDescanso() { //bloquea el refugio entero
        enCama++;
    }

    public synchronized void salirZonaDescanso() { //bloquea el refugio entero
        enCama--;
    }

    public synchronized void irComedor() throws InterruptedException { //bloquea el refugio entero
        enComedor++;
        while (comida == 0) {
            wait();
        }
        comida--;
    }

    public synchronized void salirComedor() { //bloquea el refugio entero
        enComedor--;

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
