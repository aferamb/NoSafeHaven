/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import static java.lang.Thread.sleep;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;

public class Zombie extends Thread {

    private String id;
    private int bodycount = 0;
    private ZonaExterior[] zonas = new ZonaExterior[4];
    private Ranking r;
    private EstadoPausa estadoPausa;

    public Zombie(String id, ZonaExterior[] zonas, Ranking r, EstadoPausa estadoPausa) {
        this.id = "Z" + id.substring(1); //id del humano sin la H
        this.zonas = zonas;
        this.r = r;
        this.estadoPausa = estadoPausa;
    }

    /**
     * Método que representa el comportamiento de un hilo de tipo Zombie.
     * El zombie selecciona aleatoriamente una zona exterior para buscar cerebros,
     * realiza acciones como llegar, atacar y abandonar la zona, y luego repite el proceso.
     * 
     * El método incluye pausas controladas por el objeto `estadoPausa` y simula
     * tiempos de espera aleatorios para representar el comportamiento del zombie.
     * 
     * Maneja las siguientes excepciones:
     * - InterruptedException: Si el hilo es interrumpido durante su ejecución.
     * - Exception: Para capturar y registrar cualquier otro error inesperado.
     * 
     * Las acciones del zombie se registran en el log y se imprimen en la consola.
     */
    @Override
    public void run() {
        while (true) {
            try {
                int zona = (int) (Math.random() * 4); //seleccionar zona de 0-3

                Log.escribir(id + " esta buscando cerebros en la zona exterior " + zona + ".");
                System.out.println(id + " está buscando cerebros en la zona exterior " + zona + ".");
                estadoPausa.parar();
                zonas[zona].zombieLlegar(this);
                estadoPausa.parar();
                zonas[zona].zombieAtacar(this);
                estadoPausa.parar();
                sleep((int) ((Math.random() * 1000 + 2000) / Velocidad.getVelocidad())); //dormir 2-3 s
                estadoPausa.parar();
                zonas[zona].zombieIrse(this);
                estadoPausa.parar();
                Log.escribir(id + " abandona la zona exterior " + zona + ".");
                System.out.println(id + " abandona la zona exterior " + zona + ".");
                //vuelve a buscar zona

            } catch (InterruptedException ie) {
                Log.escribir(id + " ha sido interrumpido.");
                System.out.println(id + " ha sido interrumpido.");
            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                e.printStackTrace(); // muy importante para saber qué pasó
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene el identificador único del zombie.
     *
     * @return el identificador (id) del zombie como una cadena de texto.
     */
    public String getid() {
        return id;
    }

    /**
     * Realiza un ataque de un zombie a un humano.
     * 
     * El método simula un ataque de un zombie a un humano. Si el humano es herido,
     * se registra en la pantalla principal. Si el humano es matado, se incrementa
     * el contador de muertes del zombie, se actualiza el ranking y se registra el
     * evento en el log. El tiempo del ataque es aleatorio y depende de la velocidad
     * configurada.
     * 
     * @param matado indica si el humano ha sido matado o herido.
     * @param hid    el identificador del humano atacado.
     */
    public void atacar(boolean matado, String hid) {
        PantallaPrincipal.getInstancia().addHerido(hid);
        int tiempoAtaque = (int) (Math.random() * 1000 + 500); //0.5-1.5 seg
        try {
            sleep((int) (tiempoAtaque / Velocidad.getVelocidad()));
        } catch (InterruptedException ex) {
        }
        if (matado) {
            bodycount++;
            r.actualizarRanking(id, bodycount);
            Log.escribir(id + " ha matado al humano " + hid + ". Total muertos: " + bodycount + ".");
            System.out.println(id + " ha matado al humano " + hid + ". Total muertos: " + bodycount + ".");
        } else {
            Log.escribir(id + " ha atacado y herido al humano " + hid + ".");
            System.out.println(id + " ha atacado y herido al humano " + hid + ".");
        }
    }

}
