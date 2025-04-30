/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

/**
 *
 * @author 05jan
 */
public class Humano extends Thread {

    private String id;
    private boolean herido = false;
    private boolean muerto = false;
    private Refugio refugio;
    private Exterior exterior;

    private int tiempoAtaque = 0;

    public Humano(String id, Refugio refugio, Exterior exterior) {
        this.id = id;
        this.refugio = refugio;
        this.exterior = exterior;
    }

    @Override
    public void run() {
        while (!muerto) {
            try {
                Log.escribir(id + " entra en la zona comun.");
                System.out.println(id + " entra en la zona comun.");
                refugio.irZonaComun(id);
                sleep((int) (Math.random() * 1000 + 1000)); //en zona comun 1 a 2 seg
                Log.escribir(id + " sale de la zona comun.");
                System.out.println(id + " sale de la zona comun.");

                int tunel = (int) (Math.random() * 4); //elegir entre los tuneles 0-3 para salir del refugio
                Log.escribir(id + " intenta salir del refugio por el tunel " + tunel + ".");
                System.out.println(id + " intenta salir del refugio por el tunel " + tunel + ".");
                refugio.salirRefugio(tunel, id); // "sale" de la zona comun y espera en la entrada del tunel
                sleep(1000); //esperar 1seg cruzar tunel
                refugio.salirTunel(tunel, id); // sale del tunel y llega a la zona exterior
                Log.escribir(id + " ha salido del refugio por el tunel " + tunel + ".");
                System.out.println(id + " ha salido del refugio por el tunel " + tunel + ".");

                exterior.buscarComida(this, tunel);
                Log.escribir(id + " busca comida en la zona exterior " + tunel + ".");
                System.out.println(id + " busca comida en la zona exterior " + tunel + ".");

                //si le matan, no hace el sleep
                try { // este sleep es peligroso, si un humano lo ataca se queda tiempo extra en la zona exterior y otr zombien lo puede volver a atacar
                    sleep((int) (Math.random() * 2000 + 3000)); // en exterior 3-5 seg
                } catch (InterruptedException e) {
                    Log.escribir(id + " fue interrumpido mientras buscaba comida."+ e.getMessage());
                    System.out.println(id + " fue interrumpido mientras buscaba comida."+ e.getMessage());
                    e.printStackTrace();
                }

                if (herido || muerto) { //si le atacan, simula el tiempo del ataque
                    try {
                        sleep(tiempoAtaque);
                    } catch (InterruptedException ex) {
                        Log.escribir("Error al dormir el hilo en serAtacado: " + ex.getMessage());
                        System.out.println("Error al dormir el hilo en fun serAtacado: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                    tiempoAtaque = 0; //reinicio tiempoataque
                }

                if (!muerto) {
                    
                    // habia un try
                    exterior.acabarHumano(this, tunel); //si no ha muerto, se va de la zona exterior...
                    Log.escribir(id + " deja la zona exterior " + tunel + ".");
                    System.out.println(id + " deja la zona exterior " + tunel + ".");
                    
                    tunel = (int) (Math.random() * 4); //cambiar el tunel para entrar
                    Log.escribir(id + " intenta entrar al refugio por el tunel " + tunel + ".");
                    System.out.println(id + " intenta entrar al refugio por el tunel " + tunel + ".");
                    refugio.entrarRefugio(tunel, id); //... entra al tunel...
                    sleep(1000); //esperar 1 seg cruzar tunel                           ///en pruebas
                    
                    refugio.salirTunel(tunel, id); //...llega a dentro del refugio
                    Log.escribir(id + " ha entrado al refugio por el tunel " + tunel + ".");
                    System.out.println(id + " ha entrado al refugio por el tunel " + tunel + ".");

                    if (!herido) {
                        refugio.dejarComida();
                        Log.escribir(id + " deja comida en el refugio.");
                        System.out.println(id + " deja comida en el refugio.");
                    } else {
                        //si ha sido herido, trae comida
                        Log.escribir(id + " fue herido, no trae comida.");
                        System.out.println(id + " fue herido, no trae comida.");
                    }

                    refugio.irZonaDescanso(id);
                    Log.escribir(id + " entra en la zona de descanso.");
                    System.out.println(id + " entra en la zona de descanso.");
                    sleep((int) (Math.random() * 2000 + 2000)); //en zona descanso 2-4 seg
                    refugio.salirZonaDescanso(id);
                    Log.escribir(id + " sale de la zona de descanso.");
                    System.out.println(id + " sale de la zona de descanso.");

                    try {
                        refugio.irComedor(id);
                    } catch (InterruptedException ie) {
                        Log.escribir(id + " InterruptedException comedor " + ie.getMessage());
                        System.out.println(id + " InterruptedException comedo" + ie.getMessage());
                        ie.printStackTrace();
                    }
                    refugio.irComedor(id);
                    Log.escribir(id + " entra en el comedor.");
                    System.out.println(id + " entra en el comedor.");
                    sleep((int) (Math.random() * 2000 + 3000)); //comiendo 3-5 seg
                    refugio.salirComedor(id);
                    Log.escribir(id + " sale del comedor.");
                    System.out.println(id + " sale del comedor.");

                    if (herido) {
                        refugio.irZonaDescanso(id);
                        Log.escribir(id + " esta herido y entra en la zona de descanso para curarse.");
                        System.out.println(id + " esta herido y entra en la zona de descanso para curarse.");
                        sleep((int) (Math.random() * 2000 + 3000)); //en zona descanso 3-5 seg
                        herido = false; //se cura
                        refugio.salirZonaDescanso(id);
                        Log.escribir(id + " se ha curado y sale de la zona de descanso.");
                        System.out.println(id + " se ha curado y sale de la zona de descanso.");
                    }

                } else {
                    //crear zombie 
                    Log.escribir(id + " ha muerto y se convierte en ZOMBIE.");
                    System.out.println(id + " ha muerto y se convierte en ZOMBIE.");
                    Zombie z = new Zombie(id, exterior);
                    z.start();
                    //acaba ejecucion. no vuelve a entrar al while."se convierte"
                }

            } catch (InterruptedException ie) {
                Log.escribir(id + " ha sido interrumpido." + ie.getMessage());
                System.out.println(id + " ha sido interrumpido. " + ie.getMessage());
            } catch (Exception e) {
                Log.escribir("Error en el hilo de " + id + ": " + e.getMessage());
                System.out.println("Error en el hilo de " + id + ": " + e.getMessage());
                e.printStackTrace(); // muy importante para saber qué pasó
            } finally {
                // 
            }
        }
    }

    public void serAtacado(boolean muerte, int tiempo) {
        if (muerte) {
            muerto = true;
        } else {
            herido = true;
        }
        //try {sleep(tiempo);} catch (InterruptedException ex) {Log.escribir("Error al dormir el hilo en serAtacado: " + ex.getMessage());System.out.println("Error al dormir el hilo en fun serAtacado: " + ex.getMessage());}
        tiempoAtaque = tiempo;
        //this.interrupt(); //interrumpe el hilo para que no se quede esperando en la zona exterior
    }

    public String getid() {
        return id;
    }
}
