/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class Humano extends Thread {

    private String id;
    private boolean herido = false;
    private boolean muerto = false;
    private boolean siendoAtacado = false;
    private Ranking r;
    private EstadoPausa estadoPausa;

    private Refugio refugio;
    private ZonaExterior[] zonas = new ZonaExterior[4];


    /**
     * Constructor de la clase Humano.
     *
     * @param id           Identificador único del humano.
     * @param refugio      Refugio asociado al humano.
     * @param zonas        Array de zonas exteriores donde el humano puede interactuar.
     * @param r            Ranking asociado al humano.
     * @param estadoPausa  Estado de pausa actual del humano.
     */
    public Humano(String id, Refugio refugio, ZonaExterior[] zonas, Ranking r, EstadoPausa estadoPausa) {
        this.id = id;
        this.refugio = refugio;
        this.zonas = zonas;
        this.r = r;
        this.estadoPausa = estadoPausa;
    }

    /**
     * Método que representa el ciclo de vida de un humano en el refugio durante el apocalipsis.
     * Este método se ejecuta en un hilo separado y controla las acciones del humano, como 
     * buscar comida, interactuar con el refugio y enfrentarse a posibles ataques de zombis.
     * 
     * Comportamiento principal:
     * - El humano entra al refugio y se registra en el contador de humanos.
     * - Se mueve entre diferentes zonas, como la zona común, el exterior y las zonas de descanso.
     * - Busca comida en el exterior, enfrentándose a posibles ataques de zombis.
     * - Si sobrevive, regresa al refugio con o sin comida dependiendo de si fue herido.
     * - Si muere, se convierte en un zombi y se actualizan los contadores correspondientes.
     * 
     * Consideraciones:
     * - El método utiliza pausas controladas por el estado de pausa (`estadoPausa.parar()`).
     * - Se maneja el tiempo de espera en el exterior, que puede ser interrumpido por ataques.
     * - Si el humano es herido, no trae comida al refugio y necesita más tiempo de descanso.
     * - Si el humano muere, se crea un nuevo objeto `Zombie` y se inicia su hilo.
     * 
     * Manejo de errores:
     * - Se capturan excepciones generales para registrar errores en el log y en la consola.
     */
    @Override
    public void run() {
        estadoPausa.parar();
        refugio.unHumanoMas(); //cuando llega un humano, el contador de humanos aumenta
        refugio.humanoEntraRefugio(id); //cuando llega un humano, el contador de humanos aumenta
        while (!muerto) {
            try {

                irZonaComun();
                int tunel = (int) (Math.random() * 4); //elegir entre los tuneles 0-3 para salir del refugio
                Log.escribir(id + " sale de la zona comun.");
                System.out.println(id + " sale de la zona comun.");
                salirRefugio(tunel);
                estadoPausa.parar();

                zonas[tunel].humanoLlegar(this);
                Log.escribir(id + " busca comida en la zona exterior " + tunel + ".");
                System.out.println(id + " busca comida en la zona exterior " + tunel + ".");
                estadoPausa.parar();

                try { 
                    sleep((int) ((Math.random() * 2000 + 3000) / Velocidad.getVelocidad())); // en exterior 3-5 seg
                    estadoPausa.parar();
                } catch (InterruptedException e) {
                    Log.escribir(id + " fue interrumpido mientras buscaba comida." + e.getMessage());
                    System.out.println(id + " fue interrumpido mientras buscaba comida." + e.getMessage());

                    zonas[tunel].humanoAtacado(this);
                    estadoPausa.parar();
                }

                zonas[tunel].humanoIrse(this);

                if (!muerto) {
                    Log.escribir(id + " deja la zona exterior " + tunel + ".");
                    System.out.println(id + " deja la zona exterior " + tunel + ".");

                    estadoPausa.parar();
                    entrarRefugio(tunel);
                    if (!herido) {
                        refugio.dejarComida();
                        Log.escribir(id + " deja comida en el refugio.");
                        System.out.println(id + " deja comida en el refugio.");
                    } else {
                        //si ha sido herido, trae comida
                        Log.escribir(id + " fue herido, no trae comida.");
                        System.out.println(id + " fue herido, no trae comida.");
                    }

                    irZonaDescansoGeneral(2000);
                    irComedor();
                    if (herido) {
                        irZonaDescansoHerido(3000);
                    }
                } else {
                    //crear zombie 
                    estadoPausa.parar();
                    Log.escribir(id + " ha muerto y se convierte en ZOMBIE.");
                    System.out.println(id + " ha muerto y se convierte en ZOMBIE.");
                    Zombie z = new Zombie(id, zonas, r, estadoPausa);
                    refugio.unHumanoMenos(); //cuando un humano muere, el contador de humanos disminuye
                    refugio.unZombieMas(); //cuando un humano muere, el contador de zombis aumenta
                    z.start();
                    //acaba ejecucion. no vuelve a entrar al while."se convierte"

                }

            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
            } finally {
                // 
            }
        }
    }

    /**
     * Permite que un humano entre en una zona de descanso, permanezca allí durante un tiempo
     * determinado y luego salga de la zona de descanso. Durante este proceso, se registran
     * mensajes en el log y en la consola para indicar las acciones realizadas.
     * 
     * Internamenmte s emaneja una InterruptedException para manejar la interrupción del hilo
     *
     * @param tiempomin El tiempo mínimo (en milisegundos) que el humano debe permanecer en la zona de descanso.
     *                  El tiempo real será un valor aleatorio entre 2 y 4 segundos, ajustado por la velocidad del sistema.
     */
    public void irZonaDescansoGeneral(int tiempomin) {
        estadoPausa.parar();
        refugio.irZonaDescanso(id);

        Log.escribir(id + " entra en la zona de descanso.");
        System.out.println(id + " entra en la zona de descanso.");

        estadoPausa.parar();
        try {
            sleep((int) ((Math.random() * 2000 + tiempomin) / Velocidad.getVelocidad())); //en zona descanso 2-4 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();
        refugio.salirZonaDescanso(id);
        Log.escribir(id + " sale de la zona de descanso.");
        System.out.println(id + " sale de la zona de descanso.");
        estadoPausa.parar();
    }

    /**
     * Permite que un humano entre en la zona de descanso para curarse si está herido.
     * Durante este proceso, se registran mensajes en el log y en la consola para indicar
     * las acciones realizadas. El humano espera un tiempo determinado antes de salir de la zona de descanso.
     * 
     * Internamenmte se maneja una InterruptedException para manejar la interrupción del hilo
     *
     * @param tiempomin El tiempo mínimo (en milisegundos) que el humano debe permanecer en la zona de descanso.
     *                  El tiempo real será un valor aleatorio entre 2 y 4 segundos, ajustado por la velocidad del sistema.
     */
    public void irZonaDescansoHerido(int tiempomin) {
        estadoPausa.parar();
        refugio.irZonaDescanso(id);

        Log.escribir(id + " esta herido y entra en la zona de descanso para curarse.");
        System.out.println(id + " esta herido y entra en la zona de descanso para curarse.");

        estadoPausa.parar();
        try {
            sleep((int) ((Math.random() * 2000 + tiempomin) / Velocidad.getVelocidad())); //en zona descanso 3-5 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();
        herido = false; //se cura si esta herido
        PantallaPrincipal.getInstancia().quitarHerido(id);
        refugio.salirZonaDescanso(id);
        Log.escribir(id + " sale de la zona de descanso.");
        System.out.println(id + " sale de la zona de descanso.");
        estadoPausa.parar();
    }

    /**
     * Permite que un humano entre en el comedor, permanezca allí durante un tiempo
     * determinado y luego salga del comedor. Durante este proceso, se registran
     * mensajes en el log y en la consola para indicar las acciones realizadas.
     * 
     * Internamenmte se maneja una InterruptedException para manejar la interrupción del hilo
     */
    public void irComedor() {
        estadoPausa.parar();
        refugio.irComedor(id);
        Log.escribir(id + " entra en el comedor.");
        System.out.println(id + " entra en el comedor.");
        estadoPausa.parar();
        try {
            sleep((int) ((Math.random() * 2000 + 3000) / Velocidad.getVelocidad())); //comiendo 3-5 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();
        refugio.salirComedor(id);
        Log.escribir(id + " sale del comedor.");
        System.out.println(id + " sale del comedor.");
        estadoPausa.parar();
    }

    /**
     * Permite que un humano entre en el refugio a través de un túnel específico.
     * Durante este proceso, se registran mensajes en el log y en la consola para
     * indicar las acciones realizadas. El humano espera un tiempo determinado
     * antes de entrar al refugio.
     *
     * @param tunel El número del túnel por el cual el humano intenta entrar al refugio.
     */
    public void entrarRefugio(int tunel) {
        estadoPausa.parar();
        Log.escribir(id + " intenta entrar al refugio por el tunel " + tunel + ".");
        System.out.println(id + " intenta entrar al refugio por el tunel " + tunel + ".");
        refugio.entrarRefugio(tunel, id); //... entra al tunel...
        estadoPausa.parar();
        try {
            sleep(1000 / Velocidad.getVelocidad()); //esperar 1 seg cruzar tunel
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();

        refugio.salirTunel(tunel, id); //...llega a dentro del refugio
        refugio.humanoEntraRefugio(id); // aumentar el contador de humanos en el refugio
        Log.escribir(id + " ha entrado al refugio por el tunel " + tunel + ".");
        System.out.println(id + " ha entrado al refugio por el tunel " + tunel + ".");
        estadoPausa.parar();
    }

    /**
     * Permite que un humano salga del refugio a través de un túnel específico.
     * Durante este proceso, se registran mensajes en el log y en la consola para
     * indicar las acciones realizadas. El humano espera un tiempo determinado
     * antes de salir completamente del refugio.
     *
     * @param tunel El número del túnel por el cual el humano intenta salir del refugio.
     */
    public void salirRefugio(int tunel) {
        estadoPausa.parar();
        Log.escribir(id + " intenta salir del refugio por el tunel " + tunel + ".");
        System.out.println(id + " intenta salir del refugio por el tunel " + tunel + ".");

        refugio.salirRefugio(tunel, id); // "sale" de la zona comun y espera en la entrada del tunel
        //la llamada a refugio.humanoSalerefugio está dentro del propio metodo salirRefugio para que se refleje mejor en la interfaz 

        estadoPausa.parar();
        try {
            sleep(1000 / Velocidad.getVelocidad()); //esperar 1seg cruzar tunel
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();
        refugio.salirTunel(tunel, id); // sale del tunel y llega a la zona exterior

        Log.escribir(id + " ha salido del refugio por el tunel " + tunel + ".");
        System.out.println(id + " ha salido del refugio por el tunel " + tunel + ".");
        estadoPausa.parar();
    }

    /**
     * Permite que un humano entre en la zona común del refugio, permanezca allí
     * durante un tiempo determinado y luego salga de la zona común. Durante este
     * proceso, se registran mensajes en el log y en la consola para indicar las
     * acciones realizadas.
     * 
     * Internamenmte se maneja una InterruptedException para manejar la interrupción del hilo
     */
    public void irZonaComun() {
        estadoPausa.parar();
        Log.escribir(id + " entra en la zona comun.");
        System.out.println(id + " entra en la zona comun.");
        refugio.irZonaComun(id);
        estadoPausa.parar();
        try {
            sleep((int) ((Math.random() * 1000 + 1000) / Velocidad.getVelocidad())); //en zona comun 1 a 2 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        estadoPausa.parar();
    }

    /**
     * Obtiene el identificador único del humano.
     *
     * @return el identificador único (id) como una cadena de texto.
     */
    public String getid() {
        return id;
    }

    /**
     * Establece el identificador único del humano.
     *
     * @param id el nuevo identificador único (id) como una cadena de texto.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica si el humano está herido.
     *
     * @return {@code true} si el humano está herido, {@code false} en caso contrario.
     */
    public boolean isHerido() {
        return herido;
    }

    /**
     * Establece el estado de herido del humano.
     *
     * @param herido true si el humano está herido, false en caso contrario.
     */
    public void setHerido(boolean herido) {
        this.herido = herido;
    }

    /**
     * Verifica si el humano está muerto.
     *
     * @return {@code true} si el humano está muerto, {@code false} en caso contrario.
     */
    public boolean isMuerto() {
        return muerto;
    }

    /**
     * Establece el estado de muerto del humano.
     *
     * @param muerto true si el humano está muerto, false en caso contrario.
     */
    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    /**
     * Verifica si el humano está siendo atacado.
     *
     * @return {@code true} si el humano está siendo atacado, {@code false} en caso contrario.
     */
    public boolean isSiendoAtacado() {
        return siendoAtacado;
    }

    /**
     * Establece el estado de si el humano está siendo atacado.
     *
     * @param siendoAtacado true si el humano está siendo atacado, false en caso contrario.
     */
    public void setSiendoAtacado(boolean siendoAtacado) {
        this.siendoAtacado = siendoAtacado;
    }


    /**
     * Obtiene el refugio asociado al humano.
     *
     * @return el refugio en el que se encuentra el humano.
     */
    public Refugio getRefugio() {
        return refugio;
    }

    /**
     * Establece el refugio asociado al humano.
     *
     * @param refugio el nuevo refugio en el que se encuentra el humano.
     */
    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

}
