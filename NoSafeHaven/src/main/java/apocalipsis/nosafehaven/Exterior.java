/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven;

/**
 *
 * @author 05jan
 */
public class Exterior {

    private ZonaExterior[] zonas = new ZonaExterior[4];

    public Exterior() {
        for (int i = 0; i < 4; i++) {
            zonas[i] = new ZonaExterior(i);
        }
    }

    public void buscarComida(Humano h, int zona) {
        if (zona >= 0 && zona < zonas.length) {
            zonas[zona].humanoLlegar(h);
        } else {
            throw new IllegalArgumentException("Zona inválida: " + zona);
        }
    }

    public void buscarCerebros(Zombie z, int zona) {
        if (zona >= 0 && zona < zonas.length) {
            zonas[zona].zombieLlegar(z);
        } else {
            throw new IllegalArgumentException("Zona inválida: " + zona);
        }
    }

    public void acabarZombie(Zombie z, int zona) {
        if (zona >= 0 && zona < zonas.length) {
            zonas[zona].zombieIrse(z);
        } else {
            throw new IllegalArgumentException("Zona inválida: " + zona);
        }
    }

    public void acabarHumano(Humano h, int zona) {
        if (zona >= 0 && zona < zonas.length) {
            zonas[zona].humanoIrse(h);
        } else {
            throw new IllegalArgumentException("Zona inválida: " + zona);
        }
    }
}
