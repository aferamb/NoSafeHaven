/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import apocalipsis.nosafehaven.backend.Servidor;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

public final class PantallaPrincipal extends javax.swing.JFrame {

    private Servidor servidor; //para desconectar al pulsar cerrar

    // Instancia estática y volatile para evitar problemas de visibilidad entre hilos
    private static volatile PantallaPrincipal instancia;

    // Constructor privado para evitar la instanciación directa
    private PantallaPrincipal() {
        initComponents();
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    // Método estático que obtiene la instancia del Singleton
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

    public void apagar() {
        System.exit(0);
    }

    public void actualizarComida(int comida) {
        SwingUtilities.invokeLater(() -> {
            this.comida.setText("Comida: " + comida);
        });
    }

    public void actualizarHumanos(int humanos) {
        SwingUtilities.invokeLater(() -> {
            this.counterHumanos.setText("Humanos: " + humanos);
        });
    }

    public void actualizarZonaComun(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
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
            ZonaComun.setText(sb.toString());
        });
    }

    public void actualizarComedor(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
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
            Comedor.setText(sb.toString());
        });
    }

    public void actualizarZonaDescanso(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
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
            ZonaDescanso.setText(sb.toString());
        });
    }

    public void actualizarTunel(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
            }
            switch (tunel) {
                case 0:
                    DentroT1.setText(sb.toString());
                    break;
                case 1:
                    DentroT2.setText(sb.toString());
                    break;
                case 2:
                    DentroT3.setText(sb.toString());
                    break;
                case 3:
                    DentroT4.setText(sb.toString());
                    break;
            }
        });
    }

    public void actualizarTunelMedio(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id); //.append("\n")
            }
            switch (tunel) {
                case 0:
                    MedioT1.setText(sb.toString());
                    break;
                case 1:
                    MedioT2.setText(sb.toString());
                    break;
                case 2:
                    MedioT3.setText(sb.toString());
                    break;
                case 3:
                    MedioT4.setText(sb.toString());
                    break;
            }
        });
    }

    public void actualizarTunelFuera(int tunel, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
            }
            switch (tunel) {
                case 0:
                    FueraT1.setText(sb.toString());
                    break;
                case 1:
                    FueraT2.setText(sb.toString());
                    break;
                case 2:
                    FueraT3.setText(sb.toString());
                    break;
                case 3:
                    FueraT4.setText(sb.toString());
                    break;
            }
        });
    }

    public void actualizarExteriorHumanos(int exterior, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
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

    public void actualizarExteriorZombies(int exterior, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
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
        jScrollPane21 = new javax.swing.JScrollPane();
        ZonaComun = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comida = new javax.swing.JTextField();
        jScrollPane20 = new javax.swing.JScrollPane();
        Comedor = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ZonaDescanso = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        counterHumanos = new javax.swing.JTextField();
        Tuneles = new javax.swing.JPanel();
        Tunel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        FueraT1 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        DentroT1 = new javax.swing.JTextArea();
        MedioT1 = new javax.swing.JTextField();
        Tunel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        FueraT2 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        DentroT2 = new javax.swing.JTextArea();
        MedioT2 = new javax.swing.JTextField();
        Tunel3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        FueraT3 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        DentroT3 = new javax.swing.JTextArea();
        MedioT3 = new javax.swing.JTextField();
        Tunel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        FueraT4 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        DentroT4 = new javax.swing.JTextArea();
        MedioT4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Exterior = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Exterior1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        Exterior2 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        Exterior3 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        Exterior4 = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        ExteriorZ1 = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        ExteriorZ2 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        ExteriorZ3 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        ExteriorZ4 = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
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
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(2048, 1152));

        Refugio.setBackground(new java.awt.Color(102, 204, 255));
        Refugio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Refugio.setForeground(new java.awt.Color(0, 153, 0));
        Refugio.setOpaque(false);

        jPanel2.setBackground(new java.awt.Color(111, 124, 123));

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel1.setText("Zona Común");

        ZonaComun.setEditable(false);
        ZonaComun.setBackground(new java.awt.Color(204, 204, 204));
        ZonaComun.setColumns(20);
        ZonaComun.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ZonaComun.setRows(5);
        jScrollPane21.setViewportView(ZonaComun);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        Comedor.setBackground(new java.awt.Color(204, 204, 204));
        Comedor.setColumns(20);
        Comedor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Comedor.setRows(5);
        jScrollPane20.setViewportView(Comedor);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(111, 124, 123));
        jPanel4.setPreferredSize(new java.awt.Dimension(246, 179));

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel3.setText("Zona de Descanso");

        ZonaDescanso.setEditable(false);
        ZonaDescanso.setBackground(new java.awt.Color(204, 204, 204));
        ZonaDescanso.setColumns(20);
        ZonaDescanso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ZonaDescanso.setRows(5);
        jScrollPane3.setViewportView(ZonaDescanso);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        Refugio.setBounds(210, 140, 289, 620);

        Tuneles.setBackground(new java.awt.Color(153, 102, 255));
        Tuneles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Tuneles.setOpaque(false);

        Tunel1.setBackground(new java.awt.Color(111, 124, 123));

        FueraT1.setEditable(false);
        FueraT1.setBackground(new java.awt.Color(204, 204, 204));
        FueraT1.setColumns(20);
        FueraT1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FueraT1.setLineWrap(true);
        FueraT1.setRows(5);
        FueraT1.setWrapStyleWord(true);
        jScrollPane5.setViewportView(FueraT1);

        DentroT1.setEditable(false);
        DentroT1.setBackground(new java.awt.Color(204, 204, 204));
        DentroT1.setColumns(20);
        DentroT1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DentroT1.setLineWrap(true);
        DentroT1.setRows(5);
        DentroT1.setWrapStyleWord(true);
        jScrollPane13.setViewportView(DentroT1);

        MedioT1.setBackground(new java.awt.Color(204, 204, 204));
        MedioT1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MedioT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout Tunel1Layout = new javax.swing.GroupLayout(Tunel1);
        Tunel1.setLayout(Tunel1Layout);
        Tunel1Layout.setHorizontalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MedioT1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        Tunel1Layout.setVerticalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MedioT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tunel2.setBackground(new java.awt.Color(111, 124, 123));

        FueraT2.setEditable(false);
        FueraT2.setBackground(new java.awt.Color(204, 204, 204));
        FueraT2.setColumns(20);
        FueraT2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FueraT2.setLineWrap(true);
        FueraT2.setRows(5);
        FueraT2.setWrapStyleWord(true);
        jScrollPane6.setViewportView(FueraT2);

        DentroT2.setEditable(false);
        DentroT2.setBackground(new java.awt.Color(204, 204, 204));
        DentroT2.setColumns(20);
        DentroT2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DentroT2.setLineWrap(true);
        DentroT2.setRows(5);
        DentroT2.setWrapStyleWord(true);
        jScrollPane14.setViewportView(DentroT2);

        MedioT2.setBackground(new java.awt.Color(204, 204, 204));
        MedioT2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MedioT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout Tunel2Layout = new javax.swing.GroupLayout(Tunel2);
        Tunel2.setLayout(Tunel2Layout);
        Tunel2Layout.setHorizontalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MedioT2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        Tunel2Layout.setVerticalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MedioT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tunel3.setBackground(new java.awt.Color(111, 124, 123));

        FueraT3.setEditable(false);
        FueraT3.setBackground(new java.awt.Color(204, 204, 204));
        FueraT3.setColumns(20);
        FueraT3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FueraT3.setLineWrap(true);
        FueraT3.setRows(5);
        FueraT3.setWrapStyleWord(true);
        jScrollPane9.setViewportView(FueraT3);

        DentroT3.setEditable(false);
        DentroT3.setBackground(new java.awt.Color(204, 204, 204));
        DentroT3.setColumns(20);
        DentroT3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DentroT3.setLineWrap(true);
        DentroT3.setRows(5);
        DentroT3.setWrapStyleWord(true);
        jScrollPane17.setViewportView(DentroT3);

        MedioT3.setBackground(new java.awt.Color(204, 204, 204));
        MedioT3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MedioT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout Tunel3Layout = new javax.swing.GroupLayout(Tunel3);
        Tunel3.setLayout(Tunel3Layout);
        Tunel3Layout.setHorizontalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MedioT3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        Tunel3Layout.setVerticalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MedioT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tunel4.setBackground(new java.awt.Color(111, 124, 123));

        FueraT4.setEditable(false);
        FueraT4.setBackground(new java.awt.Color(204, 204, 204));
        FueraT4.setColumns(20);
        FueraT4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        FueraT4.setLineWrap(true);
        FueraT4.setRows(5);
        FueraT4.setWrapStyleWord(true);
        jScrollPane8.setViewportView(FueraT4);

        DentroT4.setEditable(false);
        DentroT4.setBackground(new java.awt.Color(204, 204, 204));
        DentroT4.setColumns(20);
        DentroT4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DentroT4.setLineWrap(true);
        DentroT4.setRows(5);
        DentroT4.setWrapStyleWord(true);
        jScrollPane16.setViewportView(DentroT4);

        MedioT4.setBackground(new java.awt.Color(204, 204, 204));
        MedioT4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        MedioT4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout Tunel4Layout = new javax.swing.GroupLayout(Tunel4);
        Tunel4.setLayout(Tunel4Layout);
        Tunel4Layout.setHorizontalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MedioT4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );
        Tunel4Layout.setVerticalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MedioT4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("TUNELES");

        javax.swing.GroupLayout TunelesLayout = new javax.swing.GroupLayout(Tuneles);
        Tuneles.setLayout(TunelesLayout);
        TunelesLayout.setHorizontalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
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

        Exterior1.setEditable(false);
        Exterior1.setBackground(new java.awt.Color(204, 204, 204));
        Exterior1.setColumns(20);
        Exterior1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Exterior1.setLineWrap(true);
        Exterior1.setRows(5);
        jScrollPane4.setViewportView(Exterior1);

        Exterior2.setEditable(false);
        Exterior2.setBackground(new java.awt.Color(204, 204, 204));
        Exterior2.setColumns(20);
        Exterior2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Exterior2.setLineWrap(true);
        Exterior2.setRows(5);
        jScrollPane7.setViewportView(Exterior2);

        Exterior3.setEditable(false);
        Exterior3.setBackground(new java.awt.Color(204, 204, 204));
        Exterior3.setColumns(20);
        Exterior3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Exterior3.setLineWrap(true);
        Exterior3.setRows(5);
        jScrollPane10.setViewportView(Exterior3);

        Exterior4.setEditable(false);
        Exterior4.setBackground(new java.awt.Color(204, 204, 204));
        Exterior4.setColumns(20);
        Exterior4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        Exterior4.setLineWrap(true);
        Exterior4.setRows(5);
        jScrollPane11.setViewportView(Exterior4);

        ExteriorZ1.setEditable(false);
        ExteriorZ1.setBackground(new java.awt.Color(204, 204, 204));
        ExteriorZ1.setColumns(20);
        ExteriorZ1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ExteriorZ1.setLineWrap(true);
        ExteriorZ1.setRows(5);
        jScrollPane12.setViewportView(ExteriorZ1);

        ExteriorZ2.setEditable(false);
        ExteriorZ2.setBackground(new java.awt.Color(204, 204, 204));
        ExteriorZ2.setColumns(20);
        ExteriorZ2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ExteriorZ2.setLineWrap(true);
        ExteriorZ2.setRows(5);
        jScrollPane15.setViewportView(ExteriorZ2);

        ExteriorZ3.setEditable(false);
        ExteriorZ3.setBackground(new java.awt.Color(204, 204, 204));
        ExteriorZ3.setColumns(20);
        ExteriorZ3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ExteriorZ3.setLineWrap(true);
        ExteriorZ3.setRows(5);
        jScrollPane18.setViewportView(ExteriorZ3);

        ExteriorZ4.setEditable(false);
        ExteriorZ4.setBackground(new java.awt.Color(204, 204, 204));
        ExteriorZ4.setColumns(20);
        ExteriorZ4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ExteriorZ4.setLineWrap(true);
        ExteriorZ4.setRows(5);
        jScrollPane19.setViewportView(ExteriorZ4);

        jLabel6.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ZONA DE RIESGO");

        jLabel7.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Humanos");

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Zombies");

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
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel6))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private javax.swing.JTextArea Comedor;
    private javax.swing.JTextArea DentroT1;
    private javax.swing.JTextArea DentroT2;
    private javax.swing.JTextArea DentroT3;
    private javax.swing.JTextArea DentroT4;
    private javax.swing.JPanel Exterior;
    private javax.swing.JTextArea Exterior1;
    private javax.swing.JTextArea Exterior2;
    private javax.swing.JTextArea Exterior3;
    private javax.swing.JTextArea Exterior4;
    private javax.swing.JTextArea ExteriorZ1;
    private javax.swing.JTextArea ExteriorZ2;
    private javax.swing.JTextArea ExteriorZ3;
    private javax.swing.JTextArea ExteriorZ4;
    private javax.swing.JTextArea FueraT1;
    private javax.swing.JTextArea FueraT2;
    private javax.swing.JTextArea FueraT3;
    private javax.swing.JTextArea FueraT4;
    private javax.swing.JTextField MedioT1;
    private javax.swing.JTextField MedioT2;
    private javax.swing.JTextField MedioT3;
    private javax.swing.JTextField MedioT4;
    private javax.swing.JPanel Refugio;
    private javax.swing.JPanel Tunel1;
    private javax.swing.JPanel Tunel2;
    private javax.swing.JPanel Tunel3;
    private javax.swing.JPanel Tunel4;
    private javax.swing.JPanel Tuneles;
    private javax.swing.JTextArea ZonaComun;
    private javax.swing.JTextArea ZonaDescanso;
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
