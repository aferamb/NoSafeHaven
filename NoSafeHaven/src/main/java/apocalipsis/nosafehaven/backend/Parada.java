/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cristina
 */
public class Parada {

    private static boolean parar = false;
    private static boolean cambio = false;
    private Lock lockParar = new ReentrantLock();
    private Condition estaParado = lockParar.newCondition();

    public void pararr() {
        lockParar.lock();
        try {
            while (parar) {
                try {
                    estaParado.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Parada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            lockParar.unlock();
        }

    }

    public void restaurar() {
        lockParar.lock();
        try {
            estaParado.signalAll();
        } finally {
            lockParar.unlock();
        }

    }

    public void estaParadop() {
        if (parar) {
            pararr();
        } 
    }

    public static void setParada(boolean p) {
        parar = p;
    }

}
