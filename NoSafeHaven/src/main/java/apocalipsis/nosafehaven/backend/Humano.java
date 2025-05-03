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

    public Humano(String id, Refugio refugio, ZonaExterior[] zonas, Ranking r, EstadoPausa estadoPausa) {
        this.id = id;
        this.refugio = refugio;
        this.zonas = zonas;
        this.r = r;
        this.estadoPausa = estadoPausa;
    }

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

                try { // este sleep es peligroso, si un humano lo ataca se queda tiempo extra en la zona exterior y otr zombien lo puede volver a atacar
                    sleep((int) ((Math.random() * 2000 + 3000) / Velocidad.getVelocidad())); // en exterior 3-5 seg
                    estadoPausa.parar();
                } catch (InterruptedException e) {
                    Log.escribir(id + " fue interrumpido mientras buscaba comida." + e.getMessage());
                    System.out.println(id + " fue interrumpido mientras buscaba comida." + e.getMessage());

                    zonas[tunel].humanoAtacado(this);
                    estadoPausa.parar();
                }
                if (siendoAtacado) {
                    zonas[tunel].humanoAtacado(this);
                }

                if (!muerto) {
                    //si no ha sido atacado, tiene que irse aún de la zona exterior...
                    if (!herido) {
                        zonas[tunel].humanoIrse(this);
                        Log.escribir(id + " deja la zona exterior " + tunel + ".");
                        System.out.println(id + " deja la zona exterior " + tunel + ".");
                        estadoPausa.parar();
                    }
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

    // este metodo es exactamente igual que el anterior, a excepcion de que quita el herido del set de humanos heridos y los prints/logs son distintos
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

    public String getid() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHerido() {
        return herido;
    }

    public void setHerido(boolean herido) {
        this.herido = herido;
    }

    public boolean isMuerto() {
        return muerto;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }

    public boolean isSiendoAtacado() {
        return siendoAtacado;
    }

    public void setSiendoAtacado(boolean siendoAtacado) {
        this.siendoAtacado = siendoAtacado;
    }

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

}
