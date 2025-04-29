/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

/**
 *
 * @author 05jan
 */
public final class PantallaPrincipal extends javax.swing.JFrame {

     // Instancia estática y volatile para evitar problemas de visibilidad entre hilos
    private static volatile PantallaPrincipal instancia;

    // Constructor privado para evitar la instanciación directa
    private PantallaPrincipal() {
        initComponents();
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


    public void actualizarComida(int comida) {
        SwingUtilities.invokeLater(() -> {
            this.comida.setText("Comida: " + comida);
        });
    }

    public void actualizarZonaComun(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
            }
            ZonaComun.setText(sb.toString());
        });
    }

    public void actualizarComedor(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
            }
            Comedor.setText(sb.toString());
        });
    }

    public void actualizarZonaDescanso(CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
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

    public void actualizarExterior(int exterior, CopyOnWriteArrayList<String> listaIDs) {
        SwingUtilities.invokeLater(() -> {
            StringBuilder sb = new StringBuilder();
            for (String id : listaIDs) {
                sb.append(id).append("\n");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Refugio = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ZonaComun = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Comedor = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        ZonaDescanso = new javax.swing.JTextArea();
        comida = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Tuneles = new javax.swing.JPanel();
        Tunel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        FueraT1 = new javax.swing.JTextArea();
        jScrollPane18 = new javax.swing.JScrollPane();
        MedioT1 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        DentroT1 = new javax.swing.JTextArea();
        Tunel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        FueraT2 = new javax.swing.JTextArea();
        jScrollPane19 = new javax.swing.JScrollPane();
        MedioT2 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        DentroT2 = new javax.swing.JTextArea();
        Tunel3 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        FueraT3 = new javax.swing.JTextArea();
        jScrollPane22 = new javax.swing.JScrollPane();
        MedioT3 = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        DentroT3 = new javax.swing.JTextArea();
        Tunel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        FueraT4 = new javax.swing.JTextArea();
        jScrollPane21 = new javax.swing.JScrollPane();
        MedioT4 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        DentroT4 = new javax.swing.JTextArea();
        Exterior = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Exterior1 = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        Exterior2 = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        Exterior3 = new javax.swing.JTextArea();
        jScrollPane11 = new javax.swing.JScrollPane();
        Exterior4 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1390, 690));

        ZonaComun.setColumns(20);
        ZonaComun.setRows(5);
        jScrollPane1.setViewportView(ZonaComun);
        ZonaComun.getAccessibleContext().setAccessibleName("zonaComun");

        Comedor.setColumns(20);
        Comedor.setRows(5);
        jScrollPane2.setViewportView(Comedor);

        ZonaDescanso.setColumns(20);
        ZonaDescanso.setRows(5);
        jScrollPane3.setViewportView(ZonaDescanso);

        comida.setText("Comida: 0");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RefugioLayout = new javax.swing.GroupLayout(Refugio);
        Refugio.setLayout(RefugioLayout);
        RefugioLayout.setHorizontalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefugioLayout.createSequentialGroup()
                        .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RefugioLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefugioLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(87, 87, 87))))
        );
        RefugioLayout.setVerticalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        FueraT1.setColumns(20);
        FueraT1.setRows(5);
        jScrollPane5.setViewportView(FueraT1);

        MedioT1.setEditable(false);
        MedioT1.setColumns(20);
        MedioT1.setLineWrap(true);
        MedioT1.setRows(5);
        MedioT1.setWrapStyleWord(true);
        jScrollPane18.setViewportView(MedioT1);

        DentroT1.setEditable(false);
        DentroT1.setColumns(20);
        DentroT1.setLineWrap(true);
        DentroT1.setRows(5);
        DentroT1.setWrapStyleWord(true);
        jScrollPane13.setViewportView(DentroT1);

        javax.swing.GroupLayout Tunel1Layout = new javax.swing.GroupLayout(Tunel1);
        Tunel1.setLayout(Tunel1Layout);
        Tunel1Layout.setHorizontalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Tunel1Layout.setVerticalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FueraT2.setColumns(20);
        FueraT2.setRows(5);
        jScrollPane6.setViewportView(FueraT2);

        MedioT2.setEditable(false);
        MedioT2.setColumns(20);
        MedioT2.setLineWrap(true);
        MedioT2.setRows(5);
        MedioT2.setWrapStyleWord(true);
        jScrollPane19.setViewportView(MedioT2);

        DentroT2.setEditable(false);
        DentroT2.setColumns(20);
        DentroT2.setLineWrap(true);
        DentroT2.setRows(5);
        DentroT2.setWrapStyleWord(true);
        jScrollPane14.setViewportView(DentroT2);

        javax.swing.GroupLayout Tunel2Layout = new javax.swing.GroupLayout(Tunel2);
        Tunel2.setLayout(Tunel2Layout);
        Tunel2Layout.setHorizontalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Tunel2Layout.setVerticalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FueraT3.setColumns(20);
        FueraT3.setRows(5);
        jScrollPane9.setViewportView(FueraT3);

        MedioT3.setEditable(false);
        MedioT3.setColumns(20);
        MedioT3.setLineWrap(true);
        MedioT3.setRows(5);
        MedioT3.setWrapStyleWord(true);
        jScrollPane22.setViewportView(MedioT3);

        DentroT3.setEditable(false);
        DentroT3.setColumns(20);
        DentroT3.setLineWrap(true);
        DentroT3.setRows(5);
        DentroT3.setWrapStyleWord(true);
        jScrollPane17.setViewportView(DentroT3);

        javax.swing.GroupLayout Tunel3Layout = new javax.swing.GroupLayout(Tunel3);
        Tunel3.setLayout(Tunel3Layout);
        Tunel3Layout.setHorizontalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Tunel3Layout.setVerticalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FueraT4.setColumns(20);
        FueraT4.setRows(5);
        jScrollPane8.setViewportView(FueraT4);

        MedioT4.setEditable(false);
        MedioT4.setColumns(20);
        MedioT4.setLineWrap(true);
        MedioT4.setRows(5);
        MedioT4.setWrapStyleWord(true);
        jScrollPane21.setViewportView(MedioT4);

        DentroT4.setEditable(false);
        DentroT4.setColumns(20);
        DentroT4.setLineWrap(true);
        DentroT4.setRows(5);
        DentroT4.setWrapStyleWord(true);
        jScrollPane16.setViewportView(DentroT4);

        javax.swing.GroupLayout Tunel4Layout = new javax.swing.GroupLayout(Tunel4);
        Tunel4.setLayout(Tunel4Layout);
        Tunel4Layout.setHorizontalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Tunel4Layout.setVerticalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TunelesLayout = new javax.swing.GroupLayout(Tuneles);
        Tuneles.setLayout(TunelesLayout);
        TunelesLayout.setHorizontalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TunelesLayout.setVerticalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Exterior1.setColumns(20);
        Exterior1.setRows(5);
        jScrollPane4.setViewportView(Exterior1);

        Exterior2.setColumns(20);
        Exterior2.setRows(5);
        jScrollPane7.setViewportView(Exterior2);

        Exterior3.setColumns(20);
        Exterior3.setRows(5);
        jScrollPane10.setViewportView(Exterior3);

        Exterior4.setColumns(20);
        Exterior4.setRows(5);
        jScrollPane11.setViewportView(Exterior4);

        javax.swing.GroupLayout ExteriorLayout = new javax.swing.GroupLayout(Exterior);
        Exterior.setLayout(ExteriorLayout);
        ExteriorLayout.setHorizontalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addComponent(jScrollPane10)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );
        ExteriorLayout.setVerticalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        CopyOnWriteArrayList<String> idsPrueba = new CopyOnWriteArrayList<>();
        idsPrueba.add("H001");
        idsPrueba.add("H002");
        idsPrueba.add("H003");
        
        actualizarZonaComun(idsPrueba);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JTextArea FueraT1;
    private javax.swing.JTextArea FueraT2;
    private javax.swing.JTextArea FueraT3;
    private javax.swing.JTextArea FueraT4;
    private javax.swing.JTextArea MedioT1;
    private javax.swing.JTextArea MedioT2;
    private javax.swing.JTextArea MedioT3;
    private javax.swing.JTextArea MedioT4;
    private javax.swing.JPanel Refugio;
    private javax.swing.JPanel Tunel1;
    private javax.swing.JPanel Tunel2;
    private javax.swing.JPanel Tunel3;
    private javax.swing.JPanel Tunel4;
    private javax.swing.JPanel Tuneles;
    private javax.swing.JTextArea ZonaComun;
    private javax.swing.JTextArea ZonaDescanso;
    private javax.swing.JTextField comida;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    // End of variables declaration//GEN-END:variables
}
