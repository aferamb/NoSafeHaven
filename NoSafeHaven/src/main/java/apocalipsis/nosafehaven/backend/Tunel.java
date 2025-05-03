/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class Tunel {

    private int humanosDentro = 0;
    private Lock entrando = new ReentrantLock();
    private Condition quiereEntrar = entrando.newCondition(); //prioridad
    private Condition quiereSalir = entrando.newCondition();
    
    private int humanosEntrando = 0;

    private CopyOnWriteArrayList<String> humanosSaliendoRef = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> humanosEntrandoRef = new CopyOnWriteArrayList<>(); //mejor que colleccion sincronizada mas rapida y eficaz
    private CopyOnWriteArrayList<String> humanosDentroRef = new CopyOnWriteArrayList<>(); //mejor que colleccion sincronizada mas rapida y eficaz

    private CyclicBarrier barrera = new CyclicBarrier(3);
    private int id;
    private Servidor servidor;

    public Tunel(int id, Servidor servidor) {
        this.servidor = servidor;
        this.id = id;
    }

    public void actualizarHumanosEnTunel() { // no creo que necesite synchronized, ya que lasvariables aqui y en destino son atomicas y el metodo llamado en servidor es synchronized
        //humanosTotalesEnTunel.set(humanosSaliendoRef.size() + humanosDentroRef.size() + humanosEntrandoRef.size());
        //servidor.actualizarDatosTuneles(id, humanosTotalesEnTunel.get());
        servidor.actualizarDatosTuneles(id, humanosSaliendoRef.size() + humanosDentroRef.size() + humanosEntrandoRef.size());
    }

    public void salirRefugio(String idHumano) throws Exception {
        
        humanosSaliendoRef.add(idHumano);
        actualizarHumanosEnTunel();
        PantallaPrincipal.getInstancia().actualizarTunel(id, humanosSaliendoRef);
        barrera.await();
        //cuando ya pasan 3 la barrera
        entrando.lock();
        try {
            while (humanosDentro > 0 || humanosEntrando > 0) {
                quiereSalir.await();
            }
            humanosDentro++;
            humanosSaliendoRef.remove(idHumano); //eliminamos el id del humano de la lista de ids
            humanosDentroRef.add(idHumano); //añadimos el id del humano a la lista de ids
            PantallaPrincipal.getInstancia().actualizarTunel(id, humanosSaliendoRef); //actualizo la pantalla del tunel
            PantallaPrincipal.getInstancia().actualizarTunelMedio(id, humanosDentroRef); //actualizo la pantalla del refugio
        } finally {
            entrando.unlock();
        }
    }

    public void entrarRefugio(String idHumano) throws InterruptedException {
        entrando.lock();
        try {
                humanosEntrando++; 
                humanosEntrandoRef.add(idHumano); //añadimos el id del humano a la lista de ids
                actualizarHumanosEnTunel();
                PantallaPrincipal.getInstancia().actualizarTunelFuera(id, humanosEntrandoRef); //actualizo la pantalla del tunel
            
            while (humanosDentro > 0) {
                quiereEntrar.await();
            }
            humanosEntrando--;
            humanosDentro++;
            
            humanosEntrandoRef.remove(idHumano); //eliminamos el id del humano de la lista de ids
            humanosDentroRef.add(idHumano); //añadimos el id del humano a la lista de ids
            PantallaPrincipal.getInstancia().actualizarTunelFuera(id, humanosEntrandoRef); //actualizo la pantalla del tunel
            PantallaPrincipal.getInstancia().actualizarTunelMedio(id, humanosDentroRef); //actualizo la pantalla del refugio
        } finally {
            entrando.unlock();
        }
    }

    public void salirTunel(String idHumano) throws InterruptedException {
        //Thread.sleep(1000); //poner fuera de tunel

        entrando.lock();
        try {
            humanosDentro--;
            humanosDentroRef.remove(idHumano); //eliminamos el id del humano de la lista de ids
            actualizarHumanosEnTunel();
            PantallaPrincipal.getInstancia().actualizarTunelMedio(id, humanosDentroRef); //actualizo la pantalla del refugio
            PantallaPrincipal.getInstancia().actualizarTunelFuera(id, humanosEntrandoRef); //actualizo la pantalla del tunel
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
