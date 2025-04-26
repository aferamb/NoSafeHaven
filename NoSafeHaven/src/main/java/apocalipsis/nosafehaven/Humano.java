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
                Log.escribir("H" + id + " entra en la zona común.");
                refugio.irZonaComun();
                sleep((int) (Math.random() * 1000 + 1000)); //en zona comun 1 a 2 seg
                Log.escribir("H" + id + " sale de la zona común.");

                int tunel = (int) (Math.random() * 3); //elegir entre los tuneles 0-3 para salir del refugio
                Log.escribir("H" + id + " intenta salir del refugio por el túnel " + tunel + ".");
                refugio.salirRefugio(tunel);
                sleep(1000); //esperar 1seg cruzar tunel
                refugio.salirTunel(tunel);
                Log.escribir("H" + id + " ha salido del refugio por el túnel " + tunel + ".");

                exterior.buscarComida(this, tunel); //por el tunel se va a una zona concreta
                Log.escribir("H" + id + " busca comida en la zona exterior " + tunel + ".");
                sleep((int) (Math.random() * 2000 + 3000)); //en exterior 3-5 seg

                if (!muerto) {
                    exterior.acabarHumano(this, tunel); //si no ha muerto, se va de la zona exterior...
                    Log.escribir("H" + id + " deja la zona exterior " + tunel + ".");
                    tunel = (int) (Math.random() * 3); //cambiar el tunel para entrar
                    Log.escribir("H" + id + " intenta entrar al refugio por el túnel " + tunel + ".");
                    refugio.entrarRefugio(tunel); //... entra al tunel...
                    sleep(1000); //esperar 1 seg cruzar tunel
                    refugio.salirTunel(tunel); //...llega a dentro del refugio
                    Log.escribir("H" + id + " ha entrado al refugio por el túnel " + tunel + ".");

                    refugio.dejarComida();
                    Log.escribir("H" + id + " deja comida en el refugio.");
                    //sleep dejando comida?                    

                    refugio.irZonaDescanso();
                    Log.escribir("H" + id + " entra en la zona de descanso.");
                    sleep((int) (Math.random() * 2000 + 2000)); //en zona descanso 2-4 seg
                    refugio.salirZonaDescanso();
                    Log.escribir("H" + id + " sale de la zona de descanso.");

                    refugio.irComedor();
                    Log.escribir("H" + id + " entra en el comedor.");
                    sleep((int) (Math.random() * 2000 + 3000)); //comiendo 3-5 seg
                    refugio.salirComedor();
                    Log.escribir("H" + id + " sale del comedor.");

                    if (herido) {
                        refugio.irZonaDescanso();
                        Log.escribir("H" + id + " está herido y entra en la zona de descanso para curarse.");
                        sleep((int) (Math.random() * 2000 + 3000)); //en zona descanso 3-5 seg
                        herido = false; //se cura
                        refugio.salirZonaDescanso();
                        Log.escribir("H" + id + " se ha curado y sale de la zona de descanso.");
                    }

                } else {
                    //crear zombie 
                    Log.escribir("H" + id + " ha muerto y se convierte en ZOMBIE.");
                    Zombie z = new Zombie(id, exterior);
                    z.start();
                    //acaba ejecucion. no vuelve a entrar al while."se convierte"
                }

            } catch (Exception e) {
            }
        }
    }

    public void serAtacado(boolean muerte, int tiempo) {
        try {
            sleep(tiempo);
        } catch (InterruptedException ex) {
        }
        if (muerte) {
            muerto = true;
        } else {
            herido = true;
        }

    }

    //////////////////////////////// Getters y setters:
    public String getid() { //la i no esta en may porque sobreescribe un metodo de thread-
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

    public Refugio getRefugio() {
        return refugio;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

    public Exterior getExterior() {
        return exterior;
    }

    public void setExterior(Exterior exterior) {
        this.exterior = exterior;
    }

}
