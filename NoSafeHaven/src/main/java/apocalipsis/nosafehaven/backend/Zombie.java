/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import static java.lang.Thread.sleep;

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

    @Override
    public void run() {
        while (!estadoPausa.estaDesconectado()) {
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
                //vuelve a buscar zona. se podria repetir la misma (ver si seria necesario que fuera distinta a la anterior)

            } catch (InterruptedException ie) {
                Log.escribir(id + " ha sido interrumpido.");
                System.out.println(id + " ha sido interrumpido.");
            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                e.printStackTrace(); // muy importante para saber qué pasó
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
            } finally {
                // Asegúrate de que el zombie siempre sale de la zona exterior
            }
        }
    }

    public String getid() {
        return id;
    }

    public void atacar(boolean matado, int tiempo, String hid) {
        try {
            sleep((int) (tiempo / Velocidad.getVelocidad()));
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
