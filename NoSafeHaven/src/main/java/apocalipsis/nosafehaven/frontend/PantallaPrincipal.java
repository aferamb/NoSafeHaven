/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import apocalipsis.nosafehaven.backend.Servidor;
import apocalipsis.nosafehaven.backend.Velocidad;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JOptionPane;
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

    public void esperarInicioServidor() {
        botonPuerto.addActionListener(e -> {
        try {
            int puerto = Integer.parseInt(puertoTextField.getText());
            if (servidor.getConectado()) {
                JOptionPane.showMessageDialog(this, "Servidor ya iniciado.");
            } else if (puerto < 1024 || puerto > 65535) {
                JOptionPane.showMessageDialog(this, "Puerto fuera de rango. Debe estar entre 1024 y 65535.");
            } else {
                servidor.iniciarServidor(puerto);
            }
        } catch (NumberFormatException nex) {
            JOptionPane.showMessageDialog(this, "Puerto inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al iniciar el servidor: " + ex.getMessage());
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ZonaComun = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Comedor = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        comida = new javax.swing.JTextField();
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
        puertoTextField = new javax.swing.JTextField();
        botonPuerto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(1390, 690));

        Refugio.setBackground(new java.awt.Color(102, 204, 255));
        Refugio.setForeground(new java.awt.Color(0, 153, 0));

        ZonaComun.setEditable(false);
        ZonaComun.setColumns(20);
        ZonaComun.setRows(5);
        jScrollPane1.setViewportView(ZonaComun);
        ZonaComun.getAccessibleContext().setAccessibleName("zonaComun");

        jLabel1.setText("Zona Común");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(246, 179));

        Comedor.setEditable(false);
        Comedor.setColumns(20);
        Comedor.setRows(5);
        jScrollPane2.setViewportView(Comedor);

        jLabel2.setText("Comedor");

        comida.setText("Comida: 0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 8, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        jPanel4.setPreferredSize(new java.awt.Dimension(246, 179));

        jLabel3.setText("Zona de Descanso");

        ZonaDescanso.setEditable(false);
        ZonaDescanso.setColumns(20);
        ZonaDescanso.setRows(5);
        jScrollPane3.setViewportView(ZonaDescanso);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("REFUGIO");

        counterHumanos.setEditable(false);
        counterHumanos.setText("Humanos:");
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
                        .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(counterHumanos, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
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
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Tuneles.setBackground(new java.awt.Color(153, 102, 255));

        FueraT1.setEditable(false);
        FueraT1.setColumns(20);
        FueraT1.setLineWrap(true);
        FueraT1.setRows(5);
        FueraT1.setWrapStyleWord(true);
        jScrollPane5.setViewportView(FueraT1);

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

        FueraT2.setEditable(false);
        FueraT2.setColumns(20);
        FueraT2.setLineWrap(true);
        FueraT2.setRows(5);
        FueraT2.setWrapStyleWord(true);
        jScrollPane6.setViewportView(FueraT2);

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

        FueraT3.setEditable(false);
        FueraT3.setColumns(20);
        FueraT3.setLineWrap(true);
        FueraT3.setRows(5);
        FueraT3.setWrapStyleWord(true);
        jScrollPane9.setViewportView(FueraT3);

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

        FueraT4.setEditable(false);
        FueraT4.setColumns(20);
        FueraT4.setLineWrap(true);
        FueraT4.setRows(5);
        FueraT4.setWrapStyleWord(true);
        jScrollPane8.setViewportView(FueraT4);

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

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("TUNELES");

        javax.swing.GroupLayout TunelesLayout = new javax.swing.GroupLayout(Tuneles);
        Tuneles.setLayout(TunelesLayout);
        TunelesLayout.setHorizontalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TunelesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(121, 121, 121))
        );
        TunelesLayout.setVerticalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        Exterior.setBackground(new java.awt.Color(255, 255, 102));

        Exterior1.setEditable(false);
        Exterior1.setColumns(20);
        Exterior1.setLineWrap(true);
        Exterior1.setRows(5);
        jScrollPane4.setViewportView(Exterior1);

        Exterior2.setEditable(false);
        Exterior2.setColumns(20);
        Exterior2.setLineWrap(true);
        Exterior2.setRows(5);
        jScrollPane7.setViewportView(Exterior2);

        Exterior3.setEditable(false);
        Exterior3.setColumns(20);
        Exterior3.setLineWrap(true);
        Exterior3.setRows(5);
        jScrollPane10.setViewportView(Exterior3);

        Exterior4.setEditable(false);
        Exterior4.setColumns(20);
        Exterior4.setLineWrap(true);
        Exterior4.setRows(5);
        Exterior4.setPreferredSize(new java.awt.Dimension(180, 100));
        jScrollPane11.setViewportView(Exterior4);

        ExteriorZ1.setEditable(false);
        ExteriorZ1.setColumns(20);
        ExteriorZ1.setLineWrap(true);
        ExteriorZ1.setRows(5);
        jScrollPane12.setViewportView(ExteriorZ1);

        ExteriorZ2.setEditable(false);
        ExteriorZ2.setColumns(20);
        ExteriorZ2.setLineWrap(true);
        ExteriorZ2.setRows(5);
        jScrollPane15.setViewportView(ExteriorZ2);

        ExteriorZ3.setEditable(false);
        ExteriorZ3.setColumns(20);
        ExteriorZ3.setLineWrap(true);
        ExteriorZ3.setRows(5);
        ExteriorZ3.setPreferredSize(new java.awt.Dimension(180, 84));
        jScrollPane18.setViewportView(ExteriorZ3);

        ExteriorZ4.setEditable(false);
        ExteriorZ4.setColumns(20);
        ExteriorZ4.setLineWrap(true);
        ExteriorZ4.setRows(5);
        jScrollPane19.setViewportView(ExteriorZ4);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("ZONA DE RIESGO");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Humanos");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Zombies");

        javax.swing.GroupLayout ExteriorLayout = new javax.swing.GroupLayout(Exterior);
        Exterior.setLayout(ExteriorLayout);
        ExteriorLayout.setHorizontalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(39, 39, 39)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExteriorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(122, 122, 122))
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(77, 77, 77))
        );
        ExteriorLayout.setVerticalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addGap(30, 30, 30)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jScrollPane12))
                .addGap(18, 18, 18)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jScrollPane15))
                .addGap(18, 18, 18)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jScrollPane18))
                .addGap(18, 18, 18)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(ExteriorLayout.createSequentialGroup()
                        .addComponent(jScrollPane19)
                        .addGap(17, 17, 17))))
        );

        puertoTextField.setText("Puerto para conexion");
        puertoTextField.setMinimumSize(new java.awt.Dimension(100, 22));
        puertoTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puertoTextFieldMouseClicked(evt);
            }
        });
        puertoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puertoTextFieldActionPerformed(evt);
            }
        });

        botonPuerto.setText("Listo");
        botonPuerto.setToolTipText("");
        botonPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPuertoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonPuerto)
                    .addComponent(puertoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(puertoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonPuerto)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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

    private void puertoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puertoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puertoTextFieldActionPerformed

    private void botonPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPuertoActionPerformed
        
    }//GEN-LAST:event_botonPuertoActionPerformed

    private void puertoTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puertoTextFieldMouseClicked
        puertoTextField.setText("");
        // Opcionalmente: remover el listener para que no borre más veces
    }//GEN-LAST:event_puertoTextFieldMouseClicked

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
    private javax.swing.JButton botonPuerto;
    private javax.swing.JTextField comida;
    private javax.swing.JTextField counterHumanos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField puertoTextField;
    // End of variables declaration//GEN-END:variables
}
