/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 05jan
 */
public class Tunel {

    private int humanos = 0;
    private Semaphore dentro;
    private CyclicBarrier barrera;
    private int id;

    public Tunel(int id) {
        this.id = id;
        this.dentro = new Semaphore(1, true);
        this.barrera = new CyclicBarrier(3);
    }

    public int getHumanos() {
        return humanos;
    }

    public void setHumanos(int humanos) {
        this.humanos = humanos;
    }

    public Semaphore getDentro() {
        return dentro;
    }

    public void setDentro(Semaphore dentro) {
        this.dentro = dentro;
    }

    public CyclicBarrier getBarrera() {
        return barrera;
    }

    public void setBarrera(CyclicBarrier barrera) {
        this.barrera = barrera;
    }

    private void salirRefugio() throws Exception {
        barrera.await();
    }

    @Override
    public String toString() {
        return "Tunel{" + "humanos=" + humanos + ", dentro=" + dentro + ", barrera=" + barrera + '}';
    }

}
