/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import apocalipsis.nosafehaven.frontend.ClientePantalla;
import javax.swing.SwingUtilities;


public class MainCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Cliente cliente = new Cliente();
        ClientePantalla.getInstancia().setCliente(cliente); //para llamar a desconectar desde la pantalla
        cliente.start(); // porque Cliente extiende Thread
        
        SwingUtilities.invokeLater(() -> {
            ClientePantalla.getInstancia().setVisible(true);
        });
        
    }
}
