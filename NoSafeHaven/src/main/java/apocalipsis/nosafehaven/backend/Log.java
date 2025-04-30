/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apocalipsis.nosafehaven.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Log {

    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String fecha_archivo = LocalDateTime.now().format(formatoFecha).replace(":", "-").replace(" ", "_");
    private static final String nombre_archivo = "apocalipsis" + fecha_archivo + ".txt";
    private static final Lock lock = new ReentrantLock(); // Protege el acceso concurrente

    public synchronized static void escribir(String mensaje) {
        
        try (PrintWriter out = new PrintWriter(new FileWriter(nombre_archivo, true))) { //try-with-resources para que Java automáticamente cierre el recurso cuando termina el bloque try
            String timestamp = LocalDateTime.now().format(formatoFecha);
            out.println("[" + timestamp + "] " + mensaje);
        } catch (IOException e) {
        } finally {
        }
    }
}