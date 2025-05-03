/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.ClientePantalla;
import apocalipsis.nosafehaven.frontend.JDialogCliente;

public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        ClientePantalla.getInstancia().setCliente(cliente); //para llamar a desconectar desde la pantalla

        int intentos = 3;
        boolean conectado = false;
        boolean cerrar = false;
        while (intentos > 0 && !conectado && !cerrar) {
            JDialogCliente dialogo = new JDialogCliente(null, true); // modal
            dialogo.setLocationRelativeTo(null);  // centrado
            dialogo.setVisible(true);  // esto bloquea hasta que se cierre
            cerrar = dialogo.isQuiereSalir(); //si se cierra el dialogo, no se ejecuta el resto del código

            if (dialogo.isConfirmado() && !cerrar) { //si se ha confirmado el dialogo y no se ha cerrado
                String direccion = dialogo.getDireccion();
                int puerto = dialogo.getPuerto();

                cliente.conectarAlServidor(direccion, puerto);
                if (cliente.getConectado()) {
                    System.out.println("Perfecto. Conectado");
                    conectado = true;
                } else {

                    System.out.println("No se inició la conexión. Intentos: " + (intentos-1));
                    intentos--;
                }

            } else if (!cerrar) { //si no se ha confirmado el dialogo y no se ha cerrado
                System.out.println("No se inició la conexión. Intentos: " + (intentos-1));
                intentos--;
            }

        }

        
        if (conectado) {
            cliente.iniciarRecepcion();
            ClientePantalla.getInstancia().setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
