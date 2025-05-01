/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.util.ArrayList;


public class Ranking {

    private ArrayList<String> ranking = new ArrayList<>();
    private Servidor servidor;

    public Ranking(Servidor servidor) {
        this.servidor = servidor;
    }

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

    private int extraerValor(String entrada) {
        String[] partes = entrada.split(":");
        return Integer.parseInt(partes[1].trim());
    }
}
