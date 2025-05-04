/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.ClientePantalla;
import apocalipsis.nosafehaven.frontend.JDialogCliente;

public class MainCliente {

    /**
     * Método principal que gestiona la conexión de un cliente a un servidor.
     * 
     * Este método realiza los siguientes pasos:
     * 1. Crea una instancia de Cliente y la asocia a la pantalla principal.
     * 2. Intenta conectar al servidor mediante un diálogo modal que solicita la dirección y el puerto.
     * 3. Permite hasta 3 intentos de conexión antes de cerrar la aplicación.
     * 4. Si la conexión es exitosa, inicia la recepción de datos y muestra la interfaz principal.
     * 5. Si no se logra conectar o el usuario decide salir, la aplicación se cierra.
     * 
     * Detalles del flujo:
     * - Se utiliza un diálogo modal (JDialogCliente) para obtener los datos de conexión.
     * - Si el usuario confirma los datos, se intenta conectar al servidor.
     * - Si la conexión falla, se reduce el número de intentos restantes.
     * - Si el usuario decide salir, el programa termina.
     * 
     * Notas:
     * - El método bloquea la ejecución mientras el diálogo está abierto.
     * - Si la conexión es exitosa, se inicia la recepción de datos en un hilo separado.
     * 
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
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
                    System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                    intentos--;
                }

            } else if (!cerrar) { //si no se ha confirmado el dialogo y no se ha cerrado
                System.out.println("No se inició la conexión. Intentos: " + (intentos - 1));
                intentos--;
            } else {
                System.out.println("Cerrando...");
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
