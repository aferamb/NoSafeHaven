/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Refugio {

    private AtomicInteger comida = new AtomicInteger(0);
    private AtomicInteger humanos = new AtomicInteger(0);
    private AtomicInteger humanosTotales = new AtomicInteger(0); // Total de humanos que han pasado por el refugio
    private AtomicInteger zombiesTotales = new AtomicInteger(1); // Total de zombies que han pasado por el refugio

    private AtomicInteger enZonaComun = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosZonaComun = new CopyOnWriteArrayList<>(); // mejor que colleccion sincronizada mas rapida y eficaz

    private AtomicInteger enCama = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosZonaDescanso = new CopyOnWriteArrayList<>();

    private AtomicInteger enComedor = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosComedor = new CopyOnWriteArrayList<>();

    private Tunel[] tuneles = new Tunel[4]; // Array de túneles
    private Servidor servidor;

    private Lock comidaLock = new ReentrantLock();
    private Condition esperarComida = comidaLock.newCondition();

    /**
     * Constructor de la clase Refugio. Inicializa los túneles y el servidor.
     *
     * @param servidor El servidor asociado al refugio.
     */
    public Refugio(Servidor servidor) {
        for (int i = 0; i < 4; i++) {
            tuneles[i] = new Tunel(i, servidor); // Inicializa cada túnel con su ID y el servidor`
        }
        this.servidor = servidor;
    }

    /**
     * Metodo para aumentar el contador de humanos totales. p Se invoca cuando
     * un humano entra al refugio. Y se actualiza el servidor y la pantalla
     * principal.
     */
    public void unHumanoMas() {
        humanosTotales.incrementAndGet();
        System.out.println("  Un humano más en el mundo... " + humanosTotales.get());
        Log.escribir("  Un humano más en el mundo... " + humanosTotales.get());
        servidor.actualizarDatosHumanosTotal(humanosTotales.get());
    }

    /**
     * Reduce en uno el número total de humanos totales. Este método decrementa
     * el contador de humanos totales, imprime un mensaje en la consola
     * indicando la nueva cantidad de humanos y registra esta información en el
     * log del sistema. Además, actualiza los datos del servidor relacionados
     * con el total de humanos.
     */
    public void unHumanoMenos() {
        humanosTotales.decrementAndGet();
        System.out.println("  Un humano menos en el mundo... " + humanosTotales.get());
        Log.escribir("  Un humano menos en el mundo... " + humanosTotales.get());
        servidor.actualizarDatosHumanosTotal(humanosTotales.get());
    }

    /**
     * Reduce en uno el contador total de zombies y actualiza la información
     * relacionada.
     *
     * Este método decrementa el número total de zombies utilizando un contador
     * atómico, imprime un mensaje en la consola indicando la nueva cantidad de
     * zombies, registra el evento en el log del sistema y notifica al servidor
     * para actualizar los datos relacionados con el total de zombies.
     */
    public void unZombieMenos() {
        zombiesTotales.decrementAndGet();
        System.out.println("  Un zombie menos en el mundo... " + zombiesTotales.get());
        Log.escribir("  Un zombie menos en el mundo... " + zombiesTotales.get());
        servidor.actualizarDatosZombiesTotal(zombiesTotales.get());
    }

    /**
     * Aumenta en uno el contador total de zombies y actualiza la información
     * relacionada.
     *
     * Este método incrementa el número total de zombies utilizando un contador
     * atómico, imprime un mensaje en la consola indicando la nueva cantidad de
     * zombies, registra el evento en el log del sistema y notifica al servidor
     * para actualizar los datos relacionados con el total de zombies.
     */
    public void unZombieMas() {
        zombiesTotales.incrementAndGet();
        System.out.println("  Un zombie más en el mundo... " + zombiesTotales.get());
        Log.escribir("  Un zombie más en el mundo... " + zombiesTotales.get());
        servidor.actualizarDatosZombiesTotal(zombiesTotales.get());
    }

    /**
     * Aumenta la cantidad de comida en el refugio.
     *
     * Este método incrementa la cantidad de comida utilizando un contador
     * atómico, imprime un mensaje en la consola indicando la nueva cantidad de
     * comida, registra el evento en el log del sistema y notifica al servidor
     * para actualizar los datos relacionados con la comida.
     *
     * @param comidaExtra La cantidad de comida a agregar al refugio.
     */
    public void addComida(int comidaExtra) {
        comidaLock.lock();
        try {
            int c = comida.addAndGet(comidaExtra);
            System.out.println("  Llegan raciones de refugios aliados: " + comidaExtra + " de comida!! Ahora hay: " + c);
            Log.escribir("  Llegan raciones de refugios aliados: " + comidaExtra + " de comida!!  " + c);
            PantallaPrincipal.getInstancia().actualizarComida(c);
            servidor.actualizarDatosComida(c);
            esperarComida.signalAll();
        } finally {
            comidaLock.unlock();
        }
    }

    /**
     * Aumenta la cantidad de comida en el refugio.
     *
     * Este método incrementa la cantidad de comida en 2 utilizando un contador
     * atómico, imprime un mensaje en la consola indicando la nueva cantidad de
     * comida, registra el evento en el log del sistema y notifica al servidor
     * para actualizar los datos relacionados con la comida. También notifica a
     * los hilos en espera para que puedan continuar su ejecución.
     */
    public void dejarComida() {
        comidaLock.lock();
        try {
            int c = comida.addAndGet(2);
            System.out.println("  dejan 2 de comida!! " + c);
            PantallaPrincipal.getInstancia().actualizarComida(c);
            servidor.actualizarDatosComida(c);
            esperarComida.signalAll();
        } finally {
            comidaLock.unlock();
        }
    }

    /**
     * Método que registra la entrada de un humano al refugio.
     *
     * Incrementa el contador de humanos en el refugio, actualiza la interfaz
     * gráfica para reflejar el nuevo número de humanos y notifica al servidor
     * sobre el cambio en los datos del refugio. Finalmente, imprime un mensaje
     * en la consola indicando que el humano ha entrado al refugio junto con el
     * número actual de humanos.
     *
     * @param id Identificador del humano que entra al refugio. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * entrada del humano.
     */
    public void humanoEntraRefugio(String id) {
        humanos.incrementAndGet();
        PantallaPrincipal.getInstancia().actualizarHumanos(humanos.get());
        servidor.actualizarDatosRefugio(humanos.get());
        System.out.println(id + " entra al refugio..." + humanos.get());
    }

    /**
     * Método que registra la salida de un humano del refugio.
     *
     * Este método decrementa el contador de humanos en el refugio, actualiza la
     * interfaz gráfica para reflejar el nuevo número de humanos y notifica al
     * servidor sobre el cambio en los datos del refugio. Finalmente, imprime un
     * mensaje en la consola indicando que el humano ha salido del refugio junto
     * con el número actual de humanos.
     *
     * @param id Identificador del humano que sale del refugio. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * salida del humano.
     */
    public void humanoSaleRefugio(String id) {
        humanos.decrementAndGet();
        PantallaPrincipal.getInstancia().actualizarHumanos(humanos.get());
        servidor.actualizarDatosRefugio(humanos.get());
        System.out.println(id + " sale del refugio..." + humanos.get());
    }

    /**
     * Método que registra la entrada de un humano a la zona común del refugio.
     *
     * Este método incrementa el contador de humanos en la zona común, actualiza
     * la interfaz gráfica para reflejar el nuevo número de humanos en la zona
     * común.
     *
     * @param id Identificador del humano que entra a la zona común. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * entrada del humano.
     */
    public void irZonaComun(String id) {
        enZonaComun.incrementAndGet();
        humanosZonaComun.add(id);
        PantallaPrincipal.getInstancia().actualizarZonaComun(humanosZonaComun);
    }

    /**
     * Método que registra la salida de un humano de la zona común del refugio.
     *
     * Este método decrementa el contador de humanos en la zona común, actualiza
     * la interfaz gráfica para reflejar el nuevo número de humanos en la zona
     * común.
     *
     * @param id Identificador del humano que sale de la zona común. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * salida del humano.
     */
    public void salirZonaComun(String id) {
        enZonaComun.decrementAndGet();
        humanosZonaComun.remove(id);
        PantallaPrincipal.getInstancia().actualizarZonaComun(humanosZonaComun);
    }

    /**
     * Método que registra la entrada de un humano a la zona de descanso del
     * refugio.
     *
     * Este método incrementa el contador de humanos en la zona de descanso,
     * actualiza la interfaz gráfica para reflejar el nuevo número de humanos en
     * la zona de descanso.
     *
     * @param id Identificador del humano que entra a la zona de descanso. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * entrada del humano.
     */
    public void irZonaDescanso(String id) {
        enCama.incrementAndGet();
        humanosZonaDescanso.add(id);
        PantallaPrincipal.getInstancia().actualizarZonaDescanso(humanosZonaDescanso);
    }

    /**
     * Método que registra la salida de un humano de la zona de descanso del
     * refugio.
     *
     * Este método decrementa el contador de humanos en la zona de descanso,
     * actualiza la interfaz gráfica para reflejar el nuevo número de humanos en
     * la zona de descanso.
     *
     * @param id Identificador del humano que sale de la zona de descanso. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * salida del humano.
     */
    public void salirZonaDescanso(String id) {
        enCama.decrementAndGet();
        humanosZonaDescanso.remove(id);
        PantallaPrincipal.getInstancia().actualizarZonaDescanso(humanosZonaDescanso);
    }

    /**
     * Método que registra la entrada de un humano al comedor del refugio.
     *
     * Este método sincronizado incrementa el contador de humanos en el comedor,
     * actualiza la interfaz gráfica para reflejar el nuevo número de humanos en
     * el comedor y notifica a los demás hilos si hay comida disponible. Si no
     * hay comida, el hilo se bloquea hasta que haya comida disponible.
     *
     * @param id Identificador del humano que entra al comedor. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * entrada del humano.
     */
    public void irComedor(String id) {
        comidaLock.lock();
        try {
            System.out.println(id + " llega al comedor...Queda: " + comida.get());
            enComedor.incrementAndGet();
            humanosComedor.add(id);
            PantallaPrincipal.getInstancia().actualizarComedor(humanosComedor);
            while (comida.get() == 0) {
                System.out.println(id + " esta esperando por comida... Queda: " + comida.get());
                try {
                    esperarComida.await();
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            int c = comida.decrementAndGet(); //comer 1 comida
            System.out.println(id + " come... Queda: " + c);
            PantallaPrincipal.getInstancia().actualizarComida(c);
            servidor.actualizarDatosComida(c);

        } finally {
            comidaLock.unlock();
        }

    }

    /**
     * Método que registra la salida de un humano del comedor del refugio.
     *
     * Este método decrementa el contador de humanos en el comedor y actualiza
     * la interfaz gráfica para reflejar el nuevo número de humanos en el
     * comedor.
     *
     * @param id Identificador del humano que sale del comedor. Este
     * identificador se utiliza para registrar y mostrar información sobre la
     * salida del humano.
     */
    public void salirComedor(String id) {
        enComedor.decrementAndGet();
        humanosComedor.remove(id);
        PantallaPrincipal.getInstancia().actualizarComedor(humanosComedor);
    }

    /**
     * Permite que un humano salga del refugio a través de un túnel específico.
     *
     * Este método realiza las siguientes acciones en orden: 1. El humano sale
     * de la zona común del refugio. 2. Se actualiza el contador de humanos
     * dentro del refugio. 3. El humano sale del refugio a través del túnel
     * especificado.
     *
     * @param tunel El índice del túnel por el cual el humano saldrá. Debe estar
     * dentro del rango válido de túneles (0 <= túnel < tuneles.length). 
     * @param idHumano El identificador único del humano que saldrá del refugio.
     * @throws IllegalArgumentException Si el índice del túnel es inválido.
     *
     */
    public void salirRefugio(int tunel, String idHumano) {
        if (tunel >= 0 && tunel < tuneles.length) {
            // Si cambias el orden da las dos siguientes líneas, el humano aparece como que sale o no de la zona común para ir al túnel
            salirZonaComun(idHumano); // Salir de la zona común al salir del refugio
            humanoSaleRefugio(idHumano); // disminuir el contador de humanos en el refugio
            tuneles[tunel].salirRefugio(idHumano); // Salir del refugio y entrar
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    /**
     * Permite que un humano entre al refugio a través de un túnel específico.
     *
     * @param tunel El índice del túnel por el cual el humano entrará. Debe
     * estar dentro del rango válido de túneles (0 <= túnel < tuneles.length).
     * @param idHumano El identificador único del humano que entrará al refugio.
     * @throws IllegalArgumentException Si el índice del túnel es inválido.
     */
    public void entrarRefugio(int tunel, String idHumano) {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].entrarRefugio(idHumano);

        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    /**
     * Permite que un humano salga de un túnel específico.
     *
     * @param tunel El índice del túnel por el cual el humano saldrá. Debe estar
     * dentro del rango válido de túneles (0 <= túnel < tuneles.length). 
     * @param idHumano El identificador único del humano que saldrá del túnel. 
     * @throws IllegalArgumentException Si el índice del túnel es inválido.
     */
    public void salirTunel(int tunel, String idHumano) {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].salirTunel(idHumano);
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

}
