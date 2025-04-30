/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 05jan
 */
public class Humano extends Thread {

    private String id;
    private boolean herido = false;
    private boolean muerto = false;
    private boolean siendoAtacado = false;
    private Ranking r;

    private Refugio refugio;
    private ZonaExterior[] zonas = new ZonaExterior[4];

    private Parada p;

    public Humano(String id, Refugio refugio, ZonaExterior[] zonas, Parada p, Ranking r) {
        this.id = id;
        this.refugio = refugio;
        this.p = p;
        this.zonas = zonas;
        this.r = r;
    }

    @Override
    public void run() {
        while (!muerto) {
            try {
                
                irZonaComun();
                int tunel = (int) (Math.random() * 4); //elegir entre los tuneles 0-3 para salir del refugio
                Log.escribir(id + " sale de la zona comun.");
                System.out.println(id + " sale de la zona comun.");
                salirRefugio(tunel);
                PantallaPrincipal.getInstancia().parar();
                
                zonas[tunel].humanoLlegar(this);
                Log.escribir(id + " busca comida en la zona exterior " + tunel + ".");
                System.out.println(id + " busca comida en la zona exterior " + tunel + ".");
                PantallaPrincipal.getInstancia().parar();

                try { // este sleep es peligroso, si un humano lo ataca se queda tiempo extra en la zona exterior y otr zombien lo puede volver a atacar
                    sleep((int) (Math.random() * 2000 + 3000)); // en exterior 3-5 seg
                    PantallaPrincipal.getInstancia().parar();
                } catch (InterruptedException e) {
                    Log.escribir(id + " fue interrumpido mientras buscaba comida." + e.getMessage());
                    System.out.println(id + " fue interrumpido mientras buscaba comida." + e.getMessage());
                    //PantallaPrincipal.getInstancia().parar();
                    zonas[tunel].humanoAtacado(this);
                    PantallaPrincipal.getInstancia().parar();
                }
                
                if (!muerto) {
                    //si no ha sido atacado, tiene que irse aún de la zona exterior...
                    if (!herido) {
                        PantallaPrincipal.getInstancia().parar();
                        zonas[tunel].humanoIrse(this);
                        Log.escribir(id + " deja la zona exterior " + tunel + ".");
                        System.out.println(id + " deja la zona exterior " + tunel + ".");
                        PantallaPrincipal.getInstancia().parar();

                    }
                    PantallaPrincipal.getInstancia().parar();
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
                    
                    irZonaDescanso(2000);
                    irComedor();
                    if (herido) {
                        irZonaDescanso(3000);
                    }
                } else {
                    //crear zombie 
                    PantallaPrincipal.getInstancia().parar();
                    Log.escribir(id + " ha muerto y se convierte en ZOMBIE.");
                    System.out.println(id + " ha muerto y se convierte en ZOMBIE.");
                    Zombie z = new Zombie(id, zonas, p, r);
                    z.start();
                    //acaba ejecucion. no vuelve a entrar al while."se convierte"
                    
                }

            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
                e.printStackTrace(); // muy importante para saber qué pasó
            } finally {
                // 
            }
        }
    }

    public void irZonaDescanso(int tiempomin) {
        PantallaPrincipal.getInstancia().parar();
        refugio.irZonaDescanso(id);
        Log.escribir(id + " esta herido y entra en la zona de descanso para curarse.");
        System.out.println(id + " esta herido y entra en la zona de descanso para curarse.");
        PantallaPrincipal.getInstancia().parar();
        try {
            sleep((int) (Math.random() * 2000 + tiempomin)); //en zona descanso 3-5 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        PantallaPrincipal.getInstancia().parar();
        herido = false; //se cura si esta herido
        refugio.salirZonaDescanso(id);
        Log.escribir(id + " se ha curado y sale de la zona de descanso.");
        System.out.println(id + " se ha curado y sale de la zona de descanso.");
        PantallaPrincipal.getInstancia().parar();
    }

    public void irComedor() {
        PantallaPrincipal.getInstancia().parar();
        try {
            refugio.irComedor(id);
        } catch (InterruptedException ie) {
            Log.escribir(id + " InterruptedException comedor " + ie.getMessage());
            System.out.println(id + " InterruptedException comedor" + ie.getMessage());
            ie.printStackTrace();
        }
        Log.escribir(id + " entra en el comedor.");
        System.out.println(id + " entra en el comedor.");
        PantallaPrincipal.getInstancia().parar();
        try {
            sleep((int) (Math.random() * 2000 + 3000)); //comiendo 3-5 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        PantallaPrincipal.getInstancia().parar();
        refugio.salirComedor(id);
        Log.escribir(id + " sale del comedor.");
        System.out.println(id + " sale del comedor.");
        PantallaPrincipal.getInstancia().parar();
    }

    public void entrarRefugio(int tunel) {
        try {
            PantallaPrincipal.getInstancia().parar();
            Log.escribir(id + " intenta entrar al refugio por el tunel " + tunel + ".");
            System.out.println(id + " intenta entrar al refugio por el tunel " + tunel + ".");
            refugio.entrarRefugio(tunel, id); //... entra al tunel...
            PantallaPrincipal.getInstancia().parar();
            sleep(1000); //esperar 1 seg cruzar tunel
            PantallaPrincipal.getInstancia().parar();

            refugio.salirTunel(tunel, id); //...llega a dentro del refugio
            Log.escribir(id + " ha entrado al refugio por el tunel " + tunel + ".");
            System.out.println(id + " ha entrado al refugio por el tunel " + tunel + ".");
            PantallaPrincipal.getInstancia().parar();
        } catch (Exception ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirRefugio(int tunel) {
        try {
            PantallaPrincipal.getInstancia().parar();
            Log.escribir(id + " intenta salir del refugio por el tunel " + tunel + ".");
            System.out.println(id + " intenta salir del refugio por el tunel " + tunel + ".");
            refugio.salirRefugio(tunel, id); // "sale" de la zona comun y espera en la entrada del tunel
            PantallaPrincipal.getInstancia().parar();
            sleep(1000); //esperar 1seg cruzar tunel
            PantallaPrincipal.getInstancia().parar();
            refugio.salirTunel(tunel, id); // sale del tunel y llega a la zona exterior
            Log.escribir(id + " ha salido del refugio por el tunel " + tunel + ".");
            System.out.println(id + " ha salido del refugio por el tunel " + tunel + ".");
            PantallaPrincipal.getInstancia().parar();
        } catch (Exception ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void irZonaComun() {
        PantallaPrincipal.getInstancia().parar();
        Log.escribir(id + " entra en la zona comun.");
        System.out.println(id + " entra en la zona comun.");
        refugio.irZonaComun(id);
        PantallaPrincipal.getInstancia().parar();
        try {
            sleep((int) (Math.random() * 1000 + 1000)); //en zona comun 1 a 2 seg
        } catch (InterruptedException ex) {
            Logger.getLogger(Humano.class.getName()).log(Level.SEVERE, null, ex);
        }
        PantallaPrincipal.getInstancia().parar();
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
