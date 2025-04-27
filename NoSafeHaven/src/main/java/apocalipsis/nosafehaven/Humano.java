/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

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
                refugio.irZonaComun();
                sleep((int) (Math.random() * 1000 + 1000)); //en zona comun 1 a 2 seg
                Log.escribir(id + " sale de la zona comun.");
                System.out.println(id + " sale de la zona comun.");

                int tunel = (int) (Math.random() * 3); //elegir entre los tuneles 0-3 para salir del refugio
                Log.escribir(id + " intenta salir del refugio por el tunel " + tunel + ".");
                System.out.println(id + " intenta salir del refugio por el tunel " + tunel + ".");
                refugio.salirRefugio(tunel);
                sleep(1000); //esperar 1seg cruzar tunel
                refugio.salirTunel(tunel);
                Log.escribir(id + " ha salido del refugio por el tunel " + tunel + ".");
                System.out.println(id + " ha salido del refugio por el tunel " + tunel + ".");

                exterior.buscarComida(this, tunel);
                Log.escribir(id + " busca comida en la zona exterior " + tunel + ".");
                System.out.println(id + " busca comida en la zona exterior " + tunel + ".");
                try { // este sleep espeligroso, si un humano lo atataca se queda tiempo xtra en la zona exterior y otr zombien lo puede volver a atacar
                    sleep((int) (Math.random() * 2000 + 3000)); // en exterior 3-5 seg
                } catch (InterruptedException e) {
                    Log.escribir(id + " fue interrumpido mientras buscaba comida.");
                    System.out.println(id + " fue interrumpido mientras buscaba comida.");
                }

                if (!muerto) {
                    exterior.acabarHumano(this, tunel); //si no ha muerto, se va de la zona exterior...
                    Log.escribir(id + " deja la zona exterior " + tunel + ".");
                    System.out.println(id + " deja la zona exterior " + tunel + ".");
                    tunel = (int) (Math.random() * 3); //cambiar el tunel para entrar
                    Log.escribir(id + " intenta entrar al refugio por el tunel " + tunel + ".");
                    System.out.println(id + " intenta entrar al refugio por el tunel " + tunel + ".");
                    refugio.entrarRefugio(tunel); //... entra al tunel...
                    sleep(1000); //esperar 1 seg cruzar tunel
                    refugio.salirTunel(tunel); //...llega a dentro del refugio
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

                    refugio.irZonaDescanso();
                    Log.escribir(id + " entra en la zona de descanso.");
                    System.out.println(id + " entra en la zona de descanso.");
                    sleep((int) (Math.random() * 2000 + 2000)); //en zona descanso 2-4 seg
                    refugio.salirZonaDescanso();
                    Log.escribir(id + " sale de la zona de descanso.");
                    System.out.println(id + " sale de la zona de descanso.");

                    refugio.irComedor();
                    Log.escribir(id + " entra en el comedor.");
                    System.out.println(id + " entra en el comedor.");
                    sleep((int) (Math.random() * 2000 + 3000)); //comiendo 3-5 seg
                    refugio.salirComedor();
                    Log.escribir(id + " sale del comedor.");
                    System.out.println(id + " sale del comedor.");

                    if (herido) {
                        refugio.irZonaDescanso();
                        Log.escribir(id + " esta herido y entra en la zona de descanso para curarse.");
                        System.out.println(id + " esta herido y entra en la zona de descanso para curarse.");
                        sleep((int) (Math.random() * 2000 + 3000)); //en zona descanso 3-5 seg
                        herido = false; //se cura
                        refugio.salirZonaDescanso();
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
                Log.escribir(id + " ha sido interrumpido.");
                System.out.println(id + " ha sido interrumpido.");
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
        try {
            sleep(tiempo);
        } catch (InterruptedException ex) {
            Log.escribir("Error al dormir el hilo en serAtacado: " + ex.getMessage());
            System.out.println("Error al dormir el hilo en fun serAtacado: " + ex.getMessage());
        }
        if (muerte) {
            muerto = true;
        } else {
            herido = true;
        }

    }

    public String getid() {
        return id;
    }
}
