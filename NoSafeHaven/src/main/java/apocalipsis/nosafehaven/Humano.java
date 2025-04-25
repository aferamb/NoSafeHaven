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
                refugio.irZonaComun();
                sleep((int) (Math.random() * 1000 + 1000)); //en zona comun 1 a 2 seg

                int tunel = (int) (Math.random()*3); //elegir entre los tuneles 0-3 para salir del refugio
                refugio.salirRefugio(tunel);
                sleep(1000); //esperar 1seg cruzar tunel
                refugio.salirTunel(tunel);
                
                
                exterior.buscarComida(this, tunel); //por el tunel se va a una zona concreta
                sleep((int) (Math.random() * 2000 + 3000)); //en exterior 3-5 seg
                

                if (!muerto) {
                    exterior.acabarHumano(this, tunel); //si no ha muerto, se va de la zona exterior...
                    tunel = (int) (Math.random()*3); //cambiar el tunel para entrar
                    refugio.entrarRefugio(tunel); //... entra al tunel...
                    sleep(1000); //esperar 1 seg cruzar tunel
                    refugio.salirTunel(tunel); //...llega a dentro del refugio

                    refugio.dejarComida();
                    //sleep dejando comida?                    

                    refugio.irZonaDescanso();
                    sleep((int) (Math.random() * 2000 + 2000)); //en zona descanso 2-4 seg
                    refugio.salirZonaDescanso();

                    refugio.irComedor();
                    sleep((int) (Math.random() * 2000 + 3000)); //comiendo 3-5 seg
                    refugio.salirComedor();

                    if (herido) {
                        refugio.irZonaDescanso();
                        sleep((int) (Math.random() * 2000 + 3000)); //en zona descanso 3-5 seg
                        herido = false; //se cura
                        refugio.salirZonaDescanso();
                    }

                } else {
                    //crear zombie 
                    Zombie z=new Zombie(id,exterior);
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

}
