/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tunel {

    private int humanosDentro = 0;
    private Lock entrando = new ReentrantLock();
    private Condition quiereEntrar = entrando.newCondition(); //prioridad
    private Condition quiereSalir = entrando.newCondition();
    //private int dentro;
    private int humanosEntrando;

    private CyclicBarrier barrera = new CyclicBarrier(3);
    private int id;

    public Tunel(int id) {
        this.id = id;
    }

    public void salirRefugio() throws Exception {
        barrera.await();
        //cuando ya pasan 3 la barrera
        entrando.lock();
        try {
            while (humanosDentro > 0 || humanosEntrando > 0) {
                quiereSalir.await();
            }
            humanosDentro++;
        } finally {
            entrando.unlock();
        }
    }

    public void entrarRefugio() throws InterruptedException {
        entrando.lock();
        try {
            if (humanosDentro > 0) {
                humanosEntrando++; //evitar que se aumente cada bucle del while. solo se incrementa una vez
            }
            while (humanosDentro > 0) {
                quiereEntrar.await();
            }
            humanosDentro++;
            humanosEntrando--;
        } finally {
            entrando.unlock();
        }
    }

    public void salirTunel() throws InterruptedException {
        //Thread.sleep(1000); //poner fuera de tunel

        entrando.lock();
        try {
            humanosDentro--;
            if (humanosEntrando > 0) {
                // Si siguen quedando humanos intentando entrar, priorizamos su entrada
                quiereEntrar.signalAll();
            } else {
                // Si ya no quedan, permitimos que los que salen sigan
                quiereSalir.signalAll();
            }
        } finally {
            entrando.unlock();
        }

    }

}
