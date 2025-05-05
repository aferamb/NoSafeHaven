/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class ZonaExterior {

    private AtomicInteger ctdzombies = new AtomicInteger(0);
    private AtomicInteger ctdhumanos = new AtomicInteger(0);
    private int id;
    private Servidor servidor;

    private CopyOnWriteArrayList<Humano> humanos = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Zombie> zombies = new CopyOnWriteArrayList<>();

    private CopyOnWriteArrayList<String> listaIDsHumanos = new CopyOnWriteArrayList<>(); // IDs de los humanos y zombies en la zona exterior
    private CopyOnWriteArrayList<String> listaIDsZombies = new CopyOnWriteArrayList<>(); // IDs de los humanos y zombies en la zona exterior

    /**
     * Constructor de la clase ZonaExterior.
     *
     * @param id Identificador único de la zona exterior.
     * @param servidor Instancia del servidor asociada a la zona exterior.
     */
    public ZonaExterior(int id, Servidor servidor) {
        this.id = id;
        this.servidor = servidor;
    }

    /**
     * Método que gestiona la llegada de un zombie a la zona exterior.
     *
     * Este método realiza las siguientes acciones: - Incrementa el contador de
     * zombies en la zona exterior. - Añade el ID del zombie a la lista de IDs
     * de zombies presentes en la zona. - Agrega el objeto Zombie a la lista de
     * zombies. - Actualiza la interfaz gráfica de la pantalla principal para
     * reflejar los cambios en los zombies presentes en la zona exterior. -
     * Notifica al servidor para actualizar los datos de capacidad de la zona de
     * riesgo.
     *
     * @param z El objeto Zombie que está llegando a la zona exterior.
     */
    public void zombieLlegar(Zombie z) {
        ctdzombies.incrementAndGet();
        listaIDsZombies.add(z.getid()); //añadimos el id del zombie a la lista de ids
        zombies.add(z);
        PantallaPrincipal.getInstancia().actualizarExteriorZombies(id, listaIDsZombies); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosZombiesZonaRiesgo(id, ctdzombies.get()); //actualizo la capacidad de la zona exterior
    }

    /**
     * Método sincronizado que permite a un zombie atacar a un humano en la zona
     * exterior. Si un humano es atacado, se marca como herido o muerto, y se
     * elimina de la lista de humanos disponibles para evitar ataques repetidos.
     * Además, se actualizan las interfaces y datos relacionados con la zona
     * exterior.
     *
     * Detalles del proceso: - Si hay humanos disponibles, se selecciona uno al
     * azar. - El humano seleccionado es marcado como "siendo atacado". - Existe
     * una probabilidad de 1/3 de que el humano muera; si no, será marcado como
     * herido. - El humano atacado es eliminado de la lista de humanos y su ID
     * es removido de la lista de IDs. - Se notifica al zombie sobre el
     * resultado del ataque (muerte o supervivencia del humano). - Se actualizan
     * las interfaces gráficas y los datos del servidor para reflejar los
     * cambios.
     *
     * Nota: Este método asume que un humano herido no puede ser atacado
     * nuevamente, por lo que es eliminado inmediatamente después de ser
     * atacado.
     *
     * @param z El objeto Zombie que está realizando el ataque.
     */
    public synchronized void zombieAtacar(Zombie z) {
        if (ctdhumanos.get() > 0) {
            int humano = (int) (Math.random() * ctdhumanos.get()); //humano en el array en la posicion "humano"
            Humano h = humanos.get(humano);

            h.setSiendoAtacado(true); //marcar que esta siendo atacado

            boolean muerte = false;  //inicializamos a que el humano sobrevive
            if (Math.random() > 0.66) { //1/3 de morir
                muerte = true; //si se cumple, el humano muere y se transforma
                h.setMuerto(true);
            } else {
                h.setHerido(true);
            }
            //el humano ha sido atacadoa. Se borra para que otro zombie no le ataque
            h.interrupt();
            ctdhumanos.decrementAndGet();
            listaIDsHumanos.remove(h.getid()); //eliminamos el id del humano de la lista de ids
            humanos.remove(h);

            z.atacar(muerte, h.getid()); //Se le pasa el id del humano para los logs
            h.setSiendoAtacado(false);
            PantallaPrincipal.getInstancia().actualizarExteriorHumanos(id, listaIDsHumanos); //actualizo la pantalla de la zona exterior
            servidor.actualizarDatosHumanosZonaRiesgo(id, ctdhumanos.get()); //actualizo la capacidad de la zona exterior

            notifyAll();
        }
    }

    /**
     * Método que gestiona la salida de un zombie de la zona exterior.
     *
     * Este método realiza las siguientes acciones: - Decrementa el contador de
     * zombies en la zona exterior. - Elimina el ID del zombie de la lista de
     * IDs de zombies presentes en la zona. - Elimina el objeto Zombie de la
     * lista de zombies. - Actualiza la interfaz gráfica de la pantalla
     * principal para reflejar los cambios en los zombies presentes en la zona
     * exterior. - Notifica al servidor para actualizar los datos de capacidad
     * de la zona de riesgo.
     *
     * @param z El objeto Zombie que está saliendo de la zona exterior.
     */
    public void zombieIrse(Zombie z) {
        ctdzombies.decrementAndGet();
        listaIDsZombies.remove(z.getid()); //eliminamos el id del zombie de la lista de ids
        zombies.remove(z);
        PantallaPrincipal.getInstancia().actualizarExteriorZombies(id, listaIDsZombies); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosZombiesZonaRiesgo(id, ctdzombies.get()); //actualizo la capacidad de la zona exterior
    }

    /**
     * Método que gestiona la llegada de un humano a la zona exterior.
     *
     * Este método realiza las siguientes acciones: - Incrementa el contador de
     * humanos en la zona exterior. - Añade el ID del humano a la lista de IDs
     * de humanos presentes en la zona. - Agrega el objeto Humano a la lista de
     * humanos. - Actualiza la interfaz gráfica de la pantalla principal para
     * reflejar los cambios en los humanos presentes en la zona exterior. -
     * Notifica al servidor para actualizar los datos de capacidad de la zona de
     * riesgo.
     *
     * @param h El objeto Humano que está llegando a la zona exterior.
     */
    public void humanoLlegar(Humano h) {
        ctdhumanos.incrementAndGet();
        listaIDsHumanos.add(h.getid()); //añadimos el id del humano a la lista de ids
        humanos.add(h);
        PantallaPrincipal.getInstancia().actualizarExteriorHumanos(id, listaIDsHumanos); //actualizo la pantalla de la zona exterior
        servidor.actualizarDatosHumanosZonaRiesgo(id, ctdhumanos.get()); //actualizo la capacidad de la zona exterior
    }

    /**
     * Permite que un humano abandone la zona exterior si cumple con ciertas
     * condiciones.
     *
     * El método realiza las siguientes acciones: - Verifica que el humano no
     * esté muerto, herido ni siendo atacado. - Si cumple las condiciones,
     * decrementa el contador de humanos en la zona. - Elimina el ID del humano
     * de la lista de IDs y lo remueve de la lista de humanos. - Actualiza la
     * interfaz gráfica para reflejar los cambios en la zona exterior. -
     * Notifica al servidor para actualizar los datos de la zona de riesgo.
     *
     * Si el humano está siendo atacado, se llama al método `humanoAtacado(h)`.
     *
     * @param h El objeto Humano que está abandonando la zona exterior.
     */
    public void humanoIrse(Humano h) {
        if (!h.isMuerto() && !h.isHerido() && !h.isSiendoAtacado()) {
            ctdhumanos.decrementAndGet();
            listaIDsHumanos.remove(h.getid()); //eliminamos el id del humano de la lista de ids
            humanos.remove(h);
            PantallaPrincipal.getInstancia().actualizarExteriorHumanos(id, listaIDsHumanos); //actualizo la pantalla de la zona exterior
            servidor.actualizarDatosHumanosZonaRiesgo(id, ctdhumanos.get()); //actualizo la capacidad de la zona exterior
        } else if (h.isSiendoAtacado()) {
            humanoAtacado(h);
        }
    }

    /**
     * Método que permite a un humano esperar si está siendo atacado.
     *
     * Este método utiliza un bucle `while` para verificar si el humano está
     * siendo atacado. Si es así, el hilo se pone en espera hasta que se
     * notifique que el ataque ha finalizado.
     * @param h el humano que debe esperar. 
     */
    public synchronized void humanoAtacado(Humano h) {
        while (h.isSiendoAtacado()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

}
