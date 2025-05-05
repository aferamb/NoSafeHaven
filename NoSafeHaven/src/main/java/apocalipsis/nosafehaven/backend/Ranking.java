/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.ArrayList;

public class Ranking {

    private ArrayList<String> ranking = new ArrayList<>();
    private Servidor servidor;

    /**
     * Constructor de la clase Ranking.
     *
     * @param servidor Instancia del servidor que se utilizará para actualizar
     * el ranking.
     */
    public Ranking(Servidor servidor) {
        this.servidor = servidor;
    }

    /**
     * Actualiza el ranking con un nuevo registro de puntuación. Si el
     * identificador ya existe en el ranking, se elimina antes de agregar el
     * nuevo valor. Luego, el ranking se ordena de mayor a menor según el
     * bodycount y se limita a los tres primeros puestos. Finalmente, se
     * actualiza el ranking en el servidor y se muestra por consola.
     *
     * @param id El identificador único del jugador.
     * @param bodycount La cantidad de puntos o eliminaciones asociadas al
     * jugador.
     */
    public synchronized void actualizarRanking(String id, int bodycount) {
        // Elimina si ya está
        ranking.removeIf(entry -> entry.startsWith(id + ":"));

        // Añade el nuevo valor
        ranking.add(id + ": " + bodycount);

        // Ordena de mayor a menor
        ranking.sort((a, b) -> Integer.compare(extraerValor(b), extraerValor(a)));

        // Limita a top 3
        if (ranking.size() > 3) {
            ranking.subList(3, ranking.size()).clear();
        }

        servidor.actualizarRanking(ranking);

        // Mostrar
        System.out.println("RANKING:");
        for (String s : ranking) {
            System.out.println(" - " + s);
        }
    }

    /**
     * Extrae el valor numérico de una cadena en el formato "id: valor".
     *
     * @param entrada La cadena de entrada en el formato "id: valor".
     * @return El valor numérico extraído de la cadena.
     */
    private int extraerValor(String entrada) {
        String[] partes = entrada.split(":");
        return Integer.parseInt(partes[1].trim());
    }
}
