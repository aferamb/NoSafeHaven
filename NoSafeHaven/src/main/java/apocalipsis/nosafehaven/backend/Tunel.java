/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.concurrent.BrokenBarrierException;

public class Tunel {

    private int humanosDentro = 0;
    private Lock entrando = new ReentrantLock();
    private Condition quiereEntrar = entrando.newCondition(); 
    private Condition quiereSalir = entrando.newCondition();

    private int humanosEntrando = 0;

    private CopyOnWriteArrayList<String> humanosSaliendoRef = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> humanosEntrandoRef = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<String> humanosDentroRef = new CopyOnWriteArrayList<>(); 

    private CyclicBarrier barrera = new CyclicBarrier(3);
    private int id;
    private Servidor servidor;

    /**
     * Constructor de la clase Tunel.
     *
     * @param id
     * @param servidor
     */
    public Tunel(int id, Servidor servidor) {
        this.servidor = servidor;
        this.id = id;
    }

    /**
     * Actualiza el número de humanos presentes en el túnel y notifica al
     * servidor. Este método calcula la cantidad total de humanos que están
     * entrando, dentro y saliendo del túnel, y actualiza esta información en el
     * servidor.
     *
     * Nota: No se utiliza la palabra clave synchronized en este método, ya que
     * las variables involucradas (humanosSaliendoRef, humanosDentroRef y
     * humanosEntrandoRef) son atómicas, y el método llamado en el servidor
     * (actualizarDatosTuneles) ya está sincronizado.
     */
    public void actualizarHumanosEnTunel() {
        servidor.actualizarDatosTuneles(id, humanosSaliendoRef.size() + humanosDentroRef.size() + humanosEntrandoRef.size());
    }

    /**
     * Permite que un humano salga del refugio y actualiza el estado del túnel y
     * del refugio.
     *
     * Este método realiza las siguientes acciones: 1. Añade el identificador
     * del humano a la lista de humanos que están saliendo del refugio. 2.
     * Actualiza la interfaz gráfica para reflejar los cambios en el túnel. 3.
     * Espera a que la barrera se complete (cuando tres hilos alcanzan el punto
     * de sincronización). 4. Bloquea el acceso para garantizar que no haya
     * interferencias mientras se actualizan los estados. 5. Espera hasta que no
     * haya humanos dentro del túnel o entrando al túnel. 6. Incrementa el
     * contador de humanos dentro del túnel y actualiza las listas
     * correspondientes. 7. Actualiza la interfaz gráfica para reflejar los
     * cambios en el túnel y el refugio.
     *
     * @param idHumano El identificador único del humano que desea salir del
     * refugio.
     */
    public void salirRefugio(String idHumano) {
        humanosSaliendoRef.add(idHumano);
        actualizarHumanosEnTunel();
        PantallaPrincipal.getInstancia().actualizarTunel(id, humanosSaliendoRef);
        try {
            barrera.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            System.out.println(ex);
        }
        //cuando ya pasan 3 la barrera
        entrando.lock();
        try {
            while (humanosDentro > 0 || humanosEntrando > 0) {
                try {
                    quiereSalir.await();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
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

    /**
     * Método que permite a un humano intentar entrar al refugio a través del
     * túnel.
     *
     * Este método realiza las siguientes acciones: - Bloquea el acceso
     * concurrente al túnel utilizando un mecanismo de bloqueo. - Incrementa el
     * contador de humanos intentando entrar y añade el identificador del humano
     * a la lista de IDs de humanos entrando. - Actualiza la interfaz gráfica
     * para reflejar los cambios en el estado del túnel. - Espera hasta que no
     * haya humanos dentro del túnel antes de permitir la entrada. - Una vez
     * permitido, decrementa el contador de humanos entrando, incrementa el
     * contador de humanos dentro y actualiza las listas correspondientes. -
     * Actualiza nuevamente la interfaz gráfica para reflejar los cambios en el
     * estado del túnel y el refugio.
     *
     * Este método garantiza la sincronización adecuada para evitar condiciones
     * de carrera y asegurar que los humanos entren al refugio de manera
     * controlada.
     *
     * @param idHumano El identificador único del humano que desea entrar al
     * refugio.
     */
    public void entrarRefugio(String idHumano) {
        entrando.lock();
        try {
            humanosEntrando++;
            humanosEntrandoRef.add(idHumano); //añadimos el id del humano a la lista de ids
            actualizarHumanosEnTunel();
            PantallaPrincipal.getInstancia().actualizarTunelFuera(id, humanosEntrandoRef); //actualizo la pantalla del tunel

            while (humanosDentro > 0) {
                try {
                    quiereEntrar.await();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
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

    /**
     * Método que permite a un humano salir del túnel.
     *
     * Este método realiza las siguientes acciones: - Disminuye el contador de
     * humanos dentro del túnel. - Elimina el identificador del humano de la
     * lista de referencias de humanos dentro del túnel. - Actualiza la
     * información visual del túnel en la pantalla principal, tanto en la
     * sección del refugio como en la sección del túnel. - Si hay humanos
     * esperando para entrar al túnel, les da prioridad y les notifica para que
     * puedan entrar. - Si no hay humanos esperando para entrar, permite que los
     * humanos que desean salir continúen haciéndolo.
     *
     * Este método utiliza un mecanismo de bloqueo para garantizar la
     * sincronización entre los hilos que interactúan con el túnel.
     *
     * @param idHumano El identificador único del humano que desea salir del
     * túnel.
     */
    public void salirTunel(String idHumano) {
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
