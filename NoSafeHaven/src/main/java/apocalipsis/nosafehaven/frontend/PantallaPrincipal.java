/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import apocalipsis.nosafehaven.backend.Servidor;
import java.awt.Color;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public final class PantallaPrincipal extends javax.swing.JFrame {

    private Servidor servidor; //para desconectar al pulsar cerrar

    private Set<String> heridos = new HashSet(); // Conjunto para almacenar los IDs de los heridos
    private static final ReentrantLock heridosLock = new ReentrantLock();

    SimpleAttributeSet estiloNormal = new SimpleAttributeSet();
    SimpleAttributeSet estiloHerido = new SimpleAttributeSet();

    // Instancia estática y volatile para evitar problemas de visibilidad entre hilos
    private static volatile PantallaPrincipal instancia;

    /**
     * Constructor privado de la clase PantallaPrincipal. Inicializa los
     * componentes de la interfaz gráfica de usuario. Este constructor está
     * diseñado para ser utilizado internamente y no permite la creación directa
     * de instancias desde fuera de la clase.
     */
    private PantallaPrincipal() {
        initComponents();
    }

    /**
     * Establece el servidor que será utilizado por la aplicación.
     *
     * @param servidor El objeto de tipo Servidor que se asignará a esta
     * instancia.
     */
    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    /**
     * Método estático para obtener la instancia única de la clase
     * PantallaPrincipal. Implementa el patrón Singleton con doble verificación
     * para garantizar que solo se cree una instancia de la clase, incluso en un
     * entorno multihilo.
     *
     * @return La instancia única de PantallaPrincipal.
     */
    public static PantallaPrincipal getInstancia() {
        // Primero se verifica si la instancia ya está creada
        PantallaPrincipal result = instancia;
        if (result != null) {
            return result; // Si la instancia ya está creada, se retorna
        }

        // Si no, se sincroniza el bloque para garantizar que solo un hilo
        // pueda crear la instancia a la vez
        synchronized (PantallaPrincipal.class) {
            if (instancia == null) {
                // Doble comprobación: si la instancia aún es null, se crea
                instancia = new PantallaPrincipal();
            }
            return instancia;
        }
    }

    /**
     * Actualiza la etiqueta de comida en la interfaz gráfica con el valor
     * proporcionado. Este método asegura que la actualización se realice en el
     * hilo de eventos de Swing.
     *
     * @param comida El nuevo valor de comida que se mostrará en la etiqueta.
     */
    public void actualizarComida(int comida) {
        SwingUtilities.invokeLater(() -> {
            this.comida.setText("Comida: " + comida);
        });
    }

    /**
     * Actualiza el contador de humanos en la interfaz gráfica con el valor
     * proporcionado. Este método asegura que la actualización se realice en el
     * hilo de eventos de Swing.
     *
     * @param humanos El nuevo valor de humanos que se mostrará en el contador.
     */
    public void actualizarHumanos(int humanos) {
        SwingUtilities.invokeLater(() -> {
            this.counterHumanos.setText("Humanos: " + humanos);
        });
    }

    /**
     * Actualiza el contenido de la zona común en la interfaz gráfica con una
     * lista de IDs.
     *
     * La actualización de la interfaz gráfica se realiza en el hilo de eventos
     * de Swing utilizando SwingUtilities.invokeLater para garantizar la
     * seguridad en el acceso a componentes de la interfaz.
     *
     * @param listaIDs La lista de IDs que se mostrará en la zona común. Se
     * espera que la lista contenga los IDs de los humanos presentes en la zona
     * común. La lista se mostrará en la interfaz gráfica, separando los IDs por
     * espacios y agregando un salto de línea cada 4 IDs.
     */
    public void actualizarZonaComun(CopyOnWriteArrayList<String> listaIDs) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String id : listaIDs) {
            sb.append(id);
            count++;
            if (count % 4 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        SwingUtilities.invokeLater(() -> {
            ZonaComun.setText(sb.toString());
        });
    }

    /**
     * Actualiza el contenido del componente "Comedor" con una lista de IDs
     * proporcionada.
     *
     * @param listaIDs Una lista segura para subprocesos (CopyOnWriteArrayList)
     * que contiene los IDs que se mostrarán en el componente "Comedor". Cada ID
     * se mostrará en una línea separada.
     */
    public void actualizarComedor(CopyOnWriteArrayList<String> listaIDs) {
        actualizarTextPaneConColor(Comedor, listaIDs, heridos, 4); // 1 ID por línea
    }

    /**
     * Actualiza el contenido del componente "Zona de Descanso" con una lista de
     * IDs proporcionada.
     *
     * @param listaIDs Una lista segura para subprocesos (CopyOnWriteArrayList)
     * que contiene los IDs que se mostrarán en el componente "Zona de
     * Descanso". Cada ID se mostrará en una línea separada.
     */
    public void actualizarZonaDescanso(CopyOnWriteArrayList<String> listaIDs) {
        actualizarTextPaneConColor(ZonaDescanso, listaIDs, heridos, 4); // 4 IDs por línea
    }

    /**
     * Actualiza el contenido de un túnel específico con una lista de IDs
     * proporcionada. Dependiendo del túnel especificado, se actualiza el
     * componente gráfico correspondiente.
     *
     * @param tunel El número del túnel a actualizar (0, 1, 2 o 3).
     * @param listaIDs Una lista de IDs que se utilizarán para actualizar el
     * contenido del túnel.
     */
    public void actualizarTunel(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        switch (tunel) {
            case 0:
                actualizarTextPaneConColor(DentroT1, listaIDs, heridos, 1);
                break;
            case 1:
                actualizarTextPaneConColor(DentroT2, listaIDs, heridos, 1);
                break;
            case 2:
                actualizarTextPaneConColor(DentroT3, listaIDs, heridos, 1);
                break;
            case 3:
                actualizarTextPaneConColor(DentroT4, listaIDs, heridos, 1);
                break;
        }
    }

    /**
     * Actualiza el contenido del túnel medio con una lista de IDs
     * proporcionada. Dependiendo del túnel especificado, se actualiza el
     * componente gráfico correspondiente.
     *
     * @param tunel El número del túnel a actualizar (0, 1, 2 o 3).
     * @param listaIDs Una lista de IDs que se utilizarán para actualizar el
     * contenido del túnel medio.
     */
    public void actualizarTunelMedio(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        switch (tunel) {
            case 0:
                actualizarTextPaneConColor(MedioT1, listaIDs, heridos, 1);
                break;
            case 1:
                actualizarTextPaneConColor(MedioT2, listaIDs, heridos, 1);
                break;
            case 2:
                actualizarTextPaneConColor(MedioT3, listaIDs, heridos, 1);
                break;
            case 3:
                actualizarTextPaneConColor(MedioT4, listaIDs, heridos, 1);
                break;
        }
    }

    /**
     * Actualiza el contenido del túnel fuera con una lista de IDs
     * proporcionada. Dependiendo del túnel especificado, se actualiza el
     * componente gráfico correspondiente.
     *
     * @param tunel El número del túnel a actualizar (0, 1, 2 o 3).
     * @param listaIDs Una lista de IDs que se utilizarán para actualizar el
     * contenido del túnel fuera.
     */
    public void actualizarTunelFuera(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        switch (tunel) {
            case 0:
                actualizarTextPaneConColor(FueraT1, listaIDs, heridos, 1);
                break;
            case 1:
                actualizarTextPaneConColor(FueraT2, listaIDs, heridos, 1);
                break;
            case 2:
                actualizarTextPaneConColor(FueraT3, listaIDs, heridos, 1);
                break;
            case 3:
                actualizarTextPaneConColor(FueraT4, listaIDs, heridos, 1);
                break;
        }
    }

    /**
     * Actualiza el contenido de los exteriores con una lista de IDs
     * proporcionada. Dependiendo del exterior especificado, se actualiza el
     * componente gráfico correspondiente.
     *
     * @param exterior El número del exterior a actualizar (0, 1, 2 o 3).
     * @param listaIDs Una lista de IDs que se utilizarán para actualizar el
     * contenido del exterior.
     */
    public void actualizarExteriorHumanos(int exterior, CopyOnWriteArrayList<String> listaIDs) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String id : listaIDs) {
            sb.append(id);
            count++;
            if (count % 3 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        SwingUtilities.invokeLater(() -> {
            switch (exterior) {
                case 0:
                    Exterior1.setText(sb.toString());
                    break;
                case 1:
                    Exterior2.setText(sb.toString());
                    break;
                case 2:
                    Exterior3.setText(sb.toString());
                    break;
                case 3:
                    Exterior4.setText(sb.toString());
                    break;
            }
        });
    }

    /**
     * Actualiza el contenido de los exteriores con una lista de IDs de zombis
     * proporcionada. Dependiendo del exterior especificado, se actualiza el
     * componente gráfico correspondiente.
     *
     * @param exterior El número del exterior a actualizar (0, 1, 2 o 3).
     * @param listaIDs Una lista de IDs que se utilizarán para actualizar el
     * contenido del exterior.
     */
    public void actualizarExteriorZombies(int exterior, CopyOnWriteArrayList<String> listaIDs) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String id : listaIDs) {
            sb.append(id);
            count++;
            if (count % 3 == 0) {
                sb.append("\n");
            } else {
                sb.append(" ");
            }
        }
        SwingUtilities.invokeLater(() -> {
            switch (exterior) {
                case 0:
                    ExteriorZ1.setText(sb.toString());
                    break;
                case 1:
                    ExteriorZ2.setText(sb.toString());
                    break;
                case 2:
                    ExteriorZ3.setText(sb.toString());
                    break;
                case 3:
                    ExteriorZ4.setText(sb.toString());
                    break;
            }
        });
    }

    /**
     * Agrega un identificador de herido a la lista de heridos de manera segura
     * utilizando un bloqueo para garantizar la sincronización en un entorno
     * concurrente.
     *
     * @param id El identificador único del herido que se desea agregar a la
     * lista.
     */
    public void addHerido(String id) {
        heridosLock.lock();
        try {
            heridos.add(id);
        } finally {
            heridosLock.unlock();
        }
    }

    /**
     * Elimina un identificador de herido de la lista de heridos de manera
     * segura utilizando un bloqueo para garantizar la sincronización en un
     * entorno concurrente.
     *
     * @param id El identificador único del herido que se desea eliminar de la
     * lista.
     */
    public void quitarHerido(String id) {
        heridosLock.lock();
        try {
            heridos.remove(id);
        } finally {
            heridosLock.unlock();
        }
    }

    /**
     * Actualiza el contenido de un JTextPane con una lista de IDs, aplicando
     * colores específicos según si los IDs están en un conjunto de IDs heridos.
     * Además, organiza los IDs en líneas según el número especificado por
     * parámetro.
     *
     * @param textPane El JTextPane que se actualizará con el contenido.
     * @param listaIDs Una lista de IDs que se mostrarán en el JTextPane.
     * @param idsHeridos Un conjunto de IDs que serán resaltados en color rojo.
     * @param porLinea Número de IDs que se mostrarán por línea. Si es 1, los
     * IDs se separarán por espacios; si es mayor que 1, se insertará un salto
     * de línea después de cada grupo de IDs.
     */
    private void actualizarTextPaneConColor(JTextPane textPane, CopyOnWriteArrayList<String> listaIDs, Set<String> idsHeridos, int porLinea) {
        // Crear contenido completo fuera del EDT
        javax.swing.text.StyledDocument nuevoDoc = new javax.swing.text.DefaultStyledDocument();

        StyleConstants.setForeground(estiloNormal, Color.BLACK);
        StyleConstants.setForeground(estiloHerido, Color.RED);

        int count = 0;
        for (String id : listaIDs) {
            SimpleAttributeSet estilo = idsHeridos.contains(id) ? estiloHerido : estiloNormal;
            try {
                nuevoDoc.insertString(nuevoDoc.getLength(), id, estilo);
                count++;
                if (porLinea == 1 && count % porLinea == 0) {
                    nuevoDoc.insertString(nuevoDoc.getLength(), " ", estiloNormal);
                } else if (porLinea > 0 && count % porLinea == 0) {
                    nuevoDoc.insertString(nuevoDoc.getLength(), "\n", estiloNormal);
                } else {
                    nuevoDoc.insertString(nuevoDoc.getLength(), " ", estiloNormal);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        // Reemplazar el documento completo en el EDT
        SwingUtilities.invokeLater(() -> textPane.setDocument(nuevoDoc));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Refugio = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        ZonaComun = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comida = new javax.swing.JTextField();
        jScrollPane24 = new javax.swing.JScrollPane();
        Comedor = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ZonaDescanso = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        counterHumanos = new javax.swing.JTextField();
        Tuneles = new javax.swing.JPanel();
        Tunel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MedioT1 = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        DentroT1 = new javax.swing.JTextPane();
        jScrollPane16 = new javax.swing.JScrollPane();
        FueraT1 = new javax.swing.JTextPane();
        Tunel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        MedioT2 = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        DentroT2 = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        FueraT2 = new javax.swing.JTextPane();
        Tunel3 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        MedioT3 = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        DentroT3 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        FueraT3 = new javax.swing.JTextPane();
        Tunel4 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        MedioT4 = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        DentroT4 = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        FueraT4 = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        Exterior = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Exterior1 = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        Exterior2 = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        Exterior3 = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        Exterior4 = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        ExteriorZ1 = new javax.swing.JTextPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        ExteriorZ2 = new javax.swing.JTextPane();
        jScrollPane25 = new javax.swing.JScrollPane();
        ExteriorZ3 = new javax.swing.JTextPane();
        jScrollPane26 = new javax.swing.JScrollPane();
        ExteriorZ4 = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(2048, 1152));
        setPreferredSize(new java.awt.Dimension(2048, 1152));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setMinimumSize(new java.awt.Dimension(2048, 1152));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(2048, 1152));
        jPanel1.setLayout(null);

        jLayeredPane1.setMinimumSize(new java.awt.Dimension(2048, 1152));
        jLayeredPane1.setOpaque(true);

        Refugio.setBackground(new java.awt.Color(102, 204, 255));
        Refugio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Refugio.setForeground(new java.awt.Color(0, 153, 0));
        Refugio.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(111, 124, 123));

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel1.setText("Zona Común");

        ZonaComun.setEditable(false);
        ZonaComun.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane20.setViewportView(ZonaComun);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(74, 74, 74))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane20)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(111, 124, 123));
        jPanel3.setPreferredSize(new java.awt.Dimension(246, 179));

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel2.setText("Comedor");

        comida.setBackground(new java.awt.Color(204, 204, 204));
        comida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        comida.setText("Comida: 0");

        Comedor.setEditable(false);
        Comedor.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane24.setViewportView(Comedor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane24))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(111, 124, 123));
        jPanel4.setPreferredSize(new java.awt.Dimension(246, 179));

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel3.setText("Zona de Descanso");

        ZonaDescanso.setEditable(false);
        ZonaDescanso.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane1.setViewportView(ZonaDescanso);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(50, 50, 50))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REFUGIO");

        counterHumanos.setEditable(false);
        counterHumanos.setBackground(new java.awt.Color(204, 204, 204));
        counterHumanos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        counterHumanos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        counterHumanos.setText("Humanos: 0");
        counterHumanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                counterHumanosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RefugioLayout = new javax.swing.GroupLayout(Refugio);
        Refugio.setLayout(RefugioLayout);
        RefugioLayout.setHorizontalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(counterHumanos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        RefugioLayout.setVerticalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(counterHumanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(Refugio, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Refugio);
        Refugio.setBounds(210, 140, 291, 620);

        Tuneles.setBackground(new java.awt.Color(153, 102, 255));
        Tuneles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Tuneles.setOpaque(false);

        Tunel1.setBackground(new java.awt.Color(111, 124, 123));

        MedioT1.setEditable(false);
        jScrollPane2.setViewportView(MedioT1);

        DentroT1.setEditable(false);
        DentroT1.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane21.setViewportView(DentroT1);

        FueraT1.setEditable(false);
        FueraT1.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane16.setViewportView(FueraT1);

        javax.swing.GroupLayout Tunel1Layout = new javax.swing.GroupLayout(Tunel1);
        Tunel1.setLayout(Tunel1Layout);
        Tunel1Layout.setHorizontalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        Tunel1Layout.setVerticalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGroup(Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tunel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane21))
                    .addGroup(Tunel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(Tunel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane16)))
                .addContainerGap())
        );

        Tunel2.setBackground(new java.awt.Color(111, 124, 123));

        MedioT2.setEditable(false);
        jScrollPane3.setViewportView(MedioT2);

        DentroT2.setEditable(false);
        DentroT2.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane13.setViewportView(DentroT2);

        FueraT2.setEditable(false);
        FueraT2.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane5.setViewportView(FueraT2);

        javax.swing.GroupLayout Tunel2Layout = new javax.swing.GroupLayout(Tunel2);
        Tunel2.setLayout(Tunel2Layout);
        Tunel2Layout.setHorizontalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        Tunel2Layout.setVerticalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(Tunel2Layout.createSequentialGroup()
                        .addGroup(Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Tunel3.setBackground(new java.awt.Color(111, 124, 123));

        MedioT3.setEditable(false);
        jScrollPane22.setViewportView(MedioT3);

        DentroT3.setEditable(false);
        DentroT3.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane14.setViewportView(DentroT3);

        FueraT3.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane6.setViewportView(FueraT3);

        javax.swing.GroupLayout Tunel3Layout = new javax.swing.GroupLayout(Tunel3);
        Tunel3.setLayout(Tunel3Layout);
        Tunel3Layout.setHorizontalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        Tunel3Layout.setVerticalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addGroup(Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Tunel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane14))
                    .addGroup(Tunel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(Tunel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6)))
                .addContainerGap())
        );

        Tunel4.setBackground(new java.awt.Color(111, 124, 123));

        MedioT4.setEditable(false);
        jScrollPane23.setViewportView(MedioT4);

        DentroT4.setEditable(false);
        DentroT4.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane17.setViewportView(DentroT4);

        FueraT4.setEditable(false);
        FueraT4.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane8.setViewportView(FueraT4);

        javax.swing.GroupLayout Tunel4Layout = new javax.swing.GroupLayout(Tunel4);
        Tunel4.setLayout(Tunel4Layout);
        Tunel4Layout.setHorizontalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        Tunel4Layout.setVerticalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17)
                    .addComponent(jScrollPane8))
                .addContainerGap())
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TUNELES");

        javax.swing.GroupLayout TunelesLayout = new javax.swing.GroupLayout(Tuneles);
        Tuneles.setLayout(TunelesLayout);
        TunelesLayout.setHorizontalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TunelesLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TunelesLayout.createSequentialGroup()
                        .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );
        TunelesLayout.setVerticalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jLayeredPane1.setLayer(Tuneles, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Tuneles);
        Tuneles.setBounds(550, 140, 347, 620);

        Exterior.setBackground(new java.awt.Color(255, 255, 102));
        Exterior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Exterior.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ZONA DE RIESGO");

        jLabel7.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Humanos");

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Zombies");

        Exterior1.setEditable(false);
        Exterior1.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane9.setViewportView(Exterior1);

        Exterior2.setEditable(false);
        Exterior2.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane4.setViewportView(Exterior2);

        Exterior3.setEditable(false);
        Exterior3.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane7.setViewportView(Exterior3);

        Exterior4.setEditable(false);
        Exterior4.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane10.setViewportView(Exterior4);

        ExteriorZ1.setEditable(false);
        ExteriorZ1.setMaximumSize(new java.awt.Dimension(62, 20));
        ExteriorZ1.setMinimumSize(new java.awt.Dimension(62, 20));
        jScrollPane11.setViewportView(ExteriorZ1);

        ExteriorZ2.setEditable(false);
        ExteriorZ2.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane12.setViewportView(ExteriorZ2);

        ExteriorZ3.setEditable(false);
        ExteriorZ3.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane25.setViewportView(ExteriorZ3);

        ExteriorZ4.setEditable(false);
        ExteriorZ4.setMaximumSize(new java.awt.Dimension(62, 20));
        jScrollPane26.setViewportView(ExteriorZ4);

        javax.swing.GroupLayout ExteriorLayout = new javax.swing.GroupLayout(Exterior);
        Exterior.setLayout(ExteriorLayout);
        ExteriorLayout.setHorizontalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(64, 64, 64))
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ExteriorLayout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane11))
                            .addGroup(ExteriorLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane12))
                            .addGroup(ExteriorLayout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane25))))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel6)
                        .addGap(0, 79, Short.MAX_VALUE))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane26)))
                .addContainerGap())
        );
        ExteriorLayout.setVerticalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExteriorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jScrollPane11))
                .addGap(30, 30, 30)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jScrollPane7))
                .addGap(30, 30, 30)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jScrollPane26))
                .addGap(33, 33, 33))
        );

        jLayeredPane1.setLayer(Exterior, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Exterior);
        Exterior.setBounds(940, 140, 390, 620);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apocalipse2.jpg"))); // NOI18N
        jLayeredPane1.add(jLabel15);
        jLabel15.setBounds(-190, -60, 2048, 1152);

        jLabel17.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("NO SAFE HAVEN");
        jLayeredPane1.setLayer(jLabel17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jLabel17);
        jLabel17.setBounds(540, 70, 390, 60);

        jPanel1.add(jLayeredPane1);
        jLayeredPane1.setBounds(-8, -11, 1700, 1380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1158, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void counterHumanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_counterHumanosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_counterHumanosActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (servidor != null) {
            try {
                servidor.desconectar();
            } catch (IOException ex) {
                System.err.println("Error al desconectar servidor: " + ex.getMessage());
            }
        }
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PantallaPrincipal.getInstancia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Comedor;
    private javax.swing.JTextPane DentroT1;
    private javax.swing.JTextPane DentroT2;
    private javax.swing.JTextPane DentroT3;
    private javax.swing.JTextPane DentroT4;
    private javax.swing.JPanel Exterior;
    private javax.swing.JTextPane Exterior1;
    private javax.swing.JTextPane Exterior2;
    private javax.swing.JTextPane Exterior3;
    private javax.swing.JTextPane Exterior4;
    private javax.swing.JTextPane ExteriorZ1;
    private javax.swing.JTextPane ExteriorZ2;
    private javax.swing.JTextPane ExteriorZ3;
    private javax.swing.JTextPane ExteriorZ4;
    private javax.swing.JTextPane FueraT1;
    private javax.swing.JTextPane FueraT2;
    private javax.swing.JTextPane FueraT3;
    private javax.swing.JTextPane FueraT4;
    private javax.swing.JTextPane MedioT1;
    private javax.swing.JTextPane MedioT2;
    private javax.swing.JTextPane MedioT3;
    private javax.swing.JTextPane MedioT4;
    private javax.swing.JPanel Refugio;
    private javax.swing.JPanel Tunel1;
    private javax.swing.JPanel Tunel2;
    private javax.swing.JPanel Tunel3;
    private javax.swing.JPanel Tunel4;
    private javax.swing.JPanel Tuneles;
    private javax.swing.JTextPane ZonaComun;
    private javax.swing.JTextPane ZonaDescanso;
    private javax.swing.JTextField comida;
    private javax.swing.JTextField counterHumanos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
