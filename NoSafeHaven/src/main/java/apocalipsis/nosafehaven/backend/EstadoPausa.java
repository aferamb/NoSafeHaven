/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EstadoPausa {

    private final ReentrantLock pauseLock = new ReentrantLock();
    private final Condition pauseCondition = pauseLock.newCondition();
    private volatile boolean paused = false;

    public void pausar() {
        pauseLock.lock();
        try {
            paused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    public void reanudar() {
        pauseLock.lock();
        try {
            paused = false;
            pauseCondition.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    public void parar() { //Método al que llaman humanos y zombies
        if (paused) {
            pauseLock.lock();
            try {
                while (paused) {
                    pauseCondition.await();
                }
            } catch (InterruptedException ie) {
            } finally {
                pauseLock.unlock();
            }
        }
    }

    public boolean estaPausado() {
        return paused;
    }

}
