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

public class Log {

    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String fecha_archivo = LocalDateTime.now().format(formatoFecha).replace(":", "-").replace(" ", "_");
    private static final String carpeta = "logs/";
    private static final String nombre_archivo = carpeta + "apocalipsis" + fecha_archivo + ".txt";


    /**
     * Escribe un mensaje en un archivo de registro con un timestamp.
     * 
     * Esta función es sincronizada para garantizar que múltiples hilos no escriban
     * en el archivo al mismo tiempo, evitando problemas de concurrencia.
     * 
     * El archivo de registro se abre en modo de adición, por lo que los mensajes
     * nuevos se agregan al final del archivo. Cada mensaje se precede con un
     * timestamp en el formato especificado por la variable `formatoFecha`.
     * 
     * En caso de que ocurra un error de entrada/salida (IOException), este se
     * captura, pero no se realiza ninguna acción adicional en el bloque catch.
     * 
     * @param mensaje El mensaje que se desea escribir en el archivo de registro.
     */
    public synchronized static void escribir(String mensaje) {

        try (PrintWriter out = new PrintWriter(new FileWriter(nombre_archivo, true))) { //try-with-resources para que Java automáticamente cierre el recurso cuando termina el bloque try
            String timestamp = LocalDateTime.now().format(formatoFecha);
            out.println("[" + timestamp + "] " + mensaje);
        } catch (IOException e) {
        } finally {
        }
    }
}
