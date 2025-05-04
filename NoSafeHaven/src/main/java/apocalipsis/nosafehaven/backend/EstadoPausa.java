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


    /**
     * Pausa la ejecución del programa estableciendo el estado de pausa en verdadero.
     * Utiliza un bloqueo para garantizar la seguridad en entornos concurrentes.
     * 
     * Este método asegura que el acceso al estado de pausa sea seguro 
     * al utilizar un mecanismo de bloqueo (lock) para evitar condiciones de carrera.
     * 
     * El bloqueo se adquiere antes de modificar el estado y se libera 
     * inmediatamente después, incluso si ocurre una excepción.
     */
    public void pausar() {
        pauseLock.lock();
        try {
            paused = true;
        } finally {
            pauseLock.unlock();
        }
    }

    /**
     * Reanuda la ejecución de un proceso pausado.
     * 
     * Este método desbloquea el estado de pausa estableciendo la variable 
     * `paused` en falso y señalando a todas las condiciones en espera 
     * que el proceso puede continuar. Utiliza un bloqueo para garantizar 
     * que la operación sea segura en un entorno concurrente.
     * 
     * Es importante asegurarse de que este método sea llamado únicamente 
     * cuando sea seguro reanudar el proceso, ya que notifica a todos los 
     * hilos en espera.
     */
    public void reanudar() {
        pauseLock.lock();
        try {
            paused = false;
            pauseCondition.signalAll();
        } finally {
            pauseLock.unlock();
        }
    }

    /**
     * Método que permite a los humanos y zombies pausar su ejecución.
     * 
     * Este método utiliza un bloqueo para garantizar la seguridad en entornos 
     * concurrentes. Si el estado de pausa es verdadero, el hilo actual se 
     * bloqueará hasta que se reanude la ejecución.
     * 
     * Es importante asegurarse de que este método sea llamado únicamente 
     * cuando sea seguro pausar el proceso, ya que puede bloquear el hilo actual.
     */
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
    
    /**
     * Método que verifica si el programa está en estado de pausa.
     * 
     * Este método devuelve un valor booleano que indica si el estado de 
     * pausa es verdadero o falso.
     * 
     * @return true si el programa está en pausa, false en caso contrario.
     */
    public boolean estaPausado() {
        return paused;
    }

}
