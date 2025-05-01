/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.PantallaPrincipal;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Refugio {

    private AtomicInteger comida = new AtomicInteger(0);
    private AtomicInteger humanos = new AtomicInteger(0);

    private AtomicInteger enZonaComun = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosZonaComun = new CopyOnWriteArrayList<>(); // mejor que colleccion sincronizada mas rapida y eficaz

    private AtomicInteger enCama = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosZonaDescanso = new CopyOnWriteArrayList<>();

    private AtomicInteger enComedor = new AtomicInteger(0);
    private CopyOnWriteArrayList<String> humanosComedor = new CopyOnWriteArrayList<>();

    private Tunel[] tuneles = new Tunel[4]; // Array de túneles
    private Servidor servidor;

    public Refugio(Servidor servidor) {
        for (int i = 0; i < 4; i++) {
            tuneles[i] = new Tunel(i, servidor); // Inicializa cada túnel con su ID y el servidor`
        }
        this.servidor = servidor;
    }

    public synchronized void dejarComida() { 
        comida.addAndGet(2);
        System.out.println("  dejan 2 de comida!! "+comida.get());
        PantallaPrincipal.getInstancia().actualizarComida(comida.get());
        servidor.actualizarDatosComida(comida.get());
        notifyAll();
    }

    public void humanoEntraRefugio(String id) { 
        humanos.incrementAndGet();
        PantallaPrincipal.getInstancia().actualizarHumanos(humanos.get());
        servidor.actualizarDatosRefugio(humanos.get());
        System.out.println(id + " entra al refugio..."+humanos.get());
    }

    public void humanoSaleRefugio(String id) { 
        humanos.decrementAndGet();
        PantallaPrincipal.getInstancia().actualizarHumanos(humanos.get());
        servidor.actualizarDatosRefugio(humanos.get());
        System.out.println(id + " sale del refugio..."+humanos.get());
    }

    public void irZonaComun(String id) { 
        enZonaComun.incrementAndGet();
        humanosZonaComun.add(id);
        PantallaPrincipal.getInstancia().actualizarZonaComun(humanosZonaComun);
    }

    public void salirZonaComun(String id) { 
        enZonaComun.decrementAndGet();
        humanosZonaComun.remove(id);
        PantallaPrincipal.getInstancia().actualizarZonaComun(humanosZonaComun);
    }

    public void irZonaDescanso(String id) { 
        enCama.incrementAndGet();
        humanosZonaDescanso.add(id);
        PantallaPrincipal.getInstancia().actualizarZonaDescanso(humanosZonaDescanso);
    }

    public void salirZonaDescanso(String id) { 
        enCama.decrementAndGet();
        humanosZonaDescanso.remove(id);
        PantallaPrincipal.getInstancia().actualizarZonaDescanso(humanosZonaDescanso);
    }

    public synchronized void irComedor(String id) throws InterruptedException { 
        System.out.println(id+ " llega al comedor..."+comida.get());
        enComedor.incrementAndGet();
        humanosComedor.add(id);
        PantallaPrincipal.getInstancia().actualizarComedor(humanosComedor);
        while (comida.get() == 0) {
            System.out.println(id+ " esta esperando por comida..."+comida.get());
            wait();
        }
        System.out.println(id+ " come..."+comida.get());
        comida.decrementAndGet(); //comer 1 comida
        
    }

    public void salirComedor(String id) { 
        enComedor.decrementAndGet();
        humanosComedor.remove(id);
        PantallaPrincipal.getInstancia().actualizarComedor(humanosComedor);
    }

    public void salirRefugio(int tunel, String idHumano) throws Exception {
        if (tunel >= 0 && tunel < tuneles.length) {

            // Si cambias el orden da las dos siguientes líneas, el humano aparece como que sale o no de la zona común para ir al túnel
            salirZonaComun(idHumano); // Salir de la zona común al salir del refugio    Esta linea se ejecutara una vez que el humano haya entrado al túnel para salir
            tuneles[tunel].salirRefugio(idHumano); // Salir del refugio y entrar
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    public void entrarRefugio(int tunel, String idHumano) throws Exception {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].entrarRefugio(idHumano);

        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

    public void salirTunel(int tunel, String idHumano) throws InterruptedException {
        if (tunel >= 0 && tunel < tuneles.length) {
            tuneles[tunel].salirTunel(idHumano);
        } else {
            throw new IllegalArgumentException("Túnel inválido: " + tunel);
        }
    }

}
