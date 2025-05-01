/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

/**
 *
 * @author 05jan
 */
public class PruebaComs {
    public static void main(String[] args) throws InterruptedException {
        // Iniciar el servidor en un hilo separado
        Thread hiloServidor = new Thread(() -> {
            Servidor servidor = new Servidor();
            servidor.iniciarServidor(5000);
            servidor.enviarComando("dataEsteEsUnMensajeDeDatos");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt(); // Restore interrupted status
            }
            servidor.enviarComando("SALIR");
        });
        hiloServidor.start();

        // Esperar a que el servidor esté listo
        Thread.sleep(1000);

        // Iniciar el cliente
        Thread hiloCliente = new Thread(() -> {
            try {
                Cliente cliente = new Cliente();
                cliente.conectarAlServidor("localhost", 5000);

                cliente.iniciarRecepcion();
                // Esperar un poco y enviar comandos al servidor
                Thread.sleep(2000);
                cliente.enviarComando("PARAR");

                Thread.sleep(2000);
                cliente.enviarComando("REANUDAR");

                Thread.sleep(2000);
                cliente.enviarComando("dataEsteEsUnMensajeDeDatos");

                Thread.sleep(20000);
                cliente.enviarComando("SALIR");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hiloCliente.start();

        // Esperar que el cliente termine
        hiloCliente.join();
        System.out.println("Fin de la prueba.");
    }
}
