/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

public class Velocidad {

    private static volatile int velocidad = 1;

    
    /**
     * Método sincronizado para obtener el valor de la velocidad.
     * 
     * @return el valor actual de la variable estática velocidad.
     */
    public static synchronized int getVelocidad() {
        return velocidad;
    }


    /**
     * Establece de manera sincronizada una nueva velocidad.
     * 
     * @param nuevaVelocidad La nueva velocidad que se desea asignar.
     */
    public static synchronized void setVelocidad(int nuevaVelocidad) {
        velocidad = nuevaVelocidad;
    }

}
