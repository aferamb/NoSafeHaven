/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

public class Velocidad {

    private static volatile int velocidad = 1;

    public static synchronized int getVelocidad() {
        return velocidad;
    }

    public static synchronized void setVelocidad(int nuevaVelocidad) {
        velocidad = nuevaVelocidad;
    }

}
