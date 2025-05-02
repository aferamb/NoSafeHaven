/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import apocalipsis.nosafehaven.backend.Cliente;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class ClientePantalla extends javax.swing.JFrame {

    // Instancia estática y volatile para evitar problemas de visibilidad entre hilos
    private static volatile ClientePantalla instancia;

    public static volatile boolean paused = false;

    private Cliente cliente;

    // Constructor privado para evitar la instanciación directa
    private ClientePantalla() {
        initComponents();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Método estático que obtiene la instancia del Singleton
    public static ClientePantalla getInstancia() {
        // Primero se verifica si la instancia ya está creada
        ClientePantalla result = instancia;
        if (result != null) {
            return result; // Si la instancia ya está creada, se retorna
        }

        // Si no, se sincroniza el bloque para garantizar que solo un hilo
        // pueda crear la instancia a la vez
        synchronized (ClientePantalla.class) {
            if (instancia == null) {
                // Doble comprobación: si la instancia aún es null, se crea
                instancia = new ClientePantalla();
            }
            return instancia;
        }
    }

    public void esperarInicioCliente() {
        botonListoConexion.addActionListener(e -> {
        try {
            String direccion = direccionTextField.getText();
            int puerto = Integer.parseInt(puertoTextField.getText());
            if (cliente.getConectado()) {
                JOptionPane.showMessageDialog(this, "Ya estás conectado a un servidor.");
            } else if (direccion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Dirección inválida.");
            } else if (puerto < 1024 || puerto > 65535) {
                JOptionPane.showMessageDialog(this, "Puerto inválido. Debe estar entre 1024 y 65535.");
            } else {
                cliente.conectarAlServidor(direccion, puerto); // Cambia "localhost" y 1234 por la dirección IP y el puerto del servidor al que deseas conectarte
            }
            
        } catch (NumberFormatException nex) {
            JOptionPane.showMessageDialog(this, "Puerto inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al iniciar el servidor: " + ex.getMessage());
        }
    });
    }

    public void apagar() {
        System.exit(0);
    }

    public void actualizarComida(String comida) {
        SwingUtilities.invokeLater(() -> {
            this.comida.setText("Comida: " + comida);
        });
    }

    public void actualizarRefugio(String num) {
        SwingUtilities.invokeLater(() -> {
            this.NumHumanos.setText("Nº humanos: " + num);
        });
    }

    public void actualizarTunel(int tunel, String num) {
        SwingUtilities.invokeLater(() -> {

            switch (tunel) {
                case 0:
                    T1.setText("Nº humanos: " + num);
                    break;
                case 1:
                    T2.setText("Nº humanos: " + num);
                    break;
                case 2:
                    T3.setText("Nº humanos: " + num);
                    break;
                case 3:
                    T4.setText("Nº humanos: " + num);
                    break;
            }
        });
    }

    public void actualizarExteriorHumanos(int exterior, String numH) {
        SwingUtilities.invokeLater(() -> {
            switch (exterior) {
                case 0:
                    NH1.setText("Nº humanos: " + numH);
                    break;
                case 1:
                    NH2.setText("Nº humanos: " + numH);
                    break;
                case 2:
                    NH3.setText("Nº humanos: " + numH);
                    break;
                case 3:
                    NH4.setText("Nº humanos: " + numH);
                    break;
            }
        });
    }

    public void actualizarExteriorZombies(int exterior, String numZ) {
        SwingUtilities.invokeLater(() -> {
            switch (exterior) {
                case 0:
                    NZ1.setText("Nº Zombies: " + numZ);
                    break;
                case 1:
                    NZ2.setText("Nº Zombies: " + numZ);
                    break;
                case 2:
                    NZ3.setText("Nº Zombies: " + numZ);
                    break;
                case 3:
                    NZ4.setText("Nº Zombies: " + numZ);
                    break;
            }
        });
    }

    public void actualizarRanking(int ranking, String valor) {
        SwingUtilities.invokeLater(() -> {
            switch (ranking) {
                case 1:
                    z1.setText(valor);
                    break;
                case 2:
                    z2.setText(valor);
                    break;
                case 3:
                    z3.setText(valor);
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
        comedor = new javax.swing.JPanel();
        NumHumanos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comida = new javax.swing.JTextField();
        velocidad1 = new javax.swing.JButton();
        velocidad10 = new javax.swing.JButton();
        velocidad50 = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        Tuneles = new javax.swing.JPanel();
        Tunel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        T1 = new javax.swing.JTextField();
        Tunel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        T2 = new javax.swing.JTextField();
        Tunel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        T3 = new javax.swing.JTextField();
        Tunel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        T4 = new javax.swing.JTextField();
        Exterior = new javax.swing.JPanel();
        E1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NZ1 = new javax.swing.JTextField();
        NH1 = new javax.swing.JTextField();
        E2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        NZ2 = new javax.swing.JTextField();
        NH2 = new javax.swing.JTextField();
        E3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        NZ3 = new javax.swing.JTextField();
        NH3 = new javax.swing.JTextField();
        E4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        NZ4 = new javax.swing.JTextField();
        NH4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        z1 = new javax.swing.JTextField();
        z2 = new javax.swing.JTextField();
        z3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        direccionTextField = new javax.swing.JTextField();
        puertoTextField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        botonListoConexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(1390, 690));

        comedor.setBackground(new java.awt.Color(255, 255, 204));

        NumHumanos.setEditable(false);
        NumHumanos.setText("Nº humanos:");
        NumHumanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumHumanosActionPerformed(evt);
            }
        });

        jLabel1.setText("Refugio");

        comida.setEditable(false);
        comida.setText("Comida: 0");

        javax.swing.GroupLayout comedorLayout = new javax.swing.GroupLayout(comedor);
        comedor.setLayout(comedorLayout);
        comedorLayout.setHorizontalGroup(
            comedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comedorLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(comedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NumHumanos, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
            .addGroup(comedorLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        comedorLayout.setVerticalGroup(
            comedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, comedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NumHumanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        velocidad1.setText("Velocidad=x1");
        velocidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad1ActionPerformed(evt);
            }
        });

        velocidad10.setText("Velocidad=x10");
        velocidad10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad10ActionPerformed(evt);
            }
        });

        velocidad50.setText("Velocidad=x50");
        velocidad50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad50ActionPerformed(evt);
            }
        });

        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boton1_1.jpg"))); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RefugioLayout = new javax.swing.GroupLayout(Refugio);
        Refugio.setLayout(RefugioLayout);
        RefugioLayout.setHorizontalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefugioLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(velocidad50)
                            .addComponent(velocidad10)
                            .addComponent(velocidad1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        RefugioLayout.setVerticalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(comedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(velocidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(velocidad10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(velocidad50, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Tunel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel8.setText("Tunel 1");

        T1.setEditable(false);
        T1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tunel1Layout = new javax.swing.GroupLayout(Tunel1);
        Tunel1.setLayout(Tunel1Layout);
        Tunel1Layout.setHorizontalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        Tunel1Layout.setVerticalGroup(
            Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        Tunel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel9.setText("Tunel 2");

        T2.setEditable(false);
        T2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tunel2Layout = new javax.swing.GroupLayout(Tunel2);
        Tunel2.setLayout(Tunel2Layout);
        Tunel2Layout.setHorizontalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        Tunel2Layout.setVerticalGroup(
            Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        Tunel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel10.setText("Tunel 3");

        T3.setEditable(false);
        T3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tunel3Layout = new javax.swing.GroupLayout(Tunel3);
        Tunel3.setLayout(Tunel3Layout);
        Tunel3Layout.setHorizontalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        Tunel3Layout.setVerticalGroup(
            Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Tunel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel11.setText("Tunel 4");

        T4.setEditable(false);
        T4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                T4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Tunel4Layout = new javax.swing.GroupLayout(Tunel4);
        Tunel4.setLayout(Tunel4Layout);
        Tunel4Layout.setHorizontalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        Tunel4Layout.setVerticalGroup(
            Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Tunel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TunelesLayout = new javax.swing.GroupLayout(Tuneles);
        Tuneles.setLayout(TunelesLayout);
        TunelesLayout.setHorizontalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TunelesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TunelesLayout.setVerticalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        E1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel2.setText("Zona exterior 1");

        NZ1.setEditable(false);

        NH1.setEditable(false);
        NH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NH1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout E1Layout = new javax.swing.GroupLayout(E1);
        E1.setLayout(E1Layout);
        E1Layout.setHorizontalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E1Layout.createSequentialGroup()
                .addGroup(E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(E1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NZ1)
                            .addComponent(NH1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                    .addGroup(E1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        E1Layout.setVerticalGroup(
            E1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NZ1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        E2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel3.setText("Zona exterior 2");

        NZ2.setEditable(false);

        NH2.setEditable(false);
        NH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NH2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout E2Layout = new javax.swing.GroupLayout(E2);
        E2.setLayout(E2Layout);
        E2Layout.setHorizontalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E2Layout.createSequentialGroup()
                .addGroup(E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(E2Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3))
                    .addGroup(E2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NZ2)
                            .addComponent(NH2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        E2Layout.setVerticalGroup(
            E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NZ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NH2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        E3.setBackground(new java.awt.Color(204, 255, 255));

        jLabel4.setText("Zona exterior 3");

        NZ3.setEditable(false);

        NH3.setEditable(false);
        NH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NH3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout E3Layout = new javax.swing.GroupLayout(E3);
        E3.setLayout(E3Layout);
        E3Layout.setHorizontalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E3Layout.createSequentialGroup()
                .addGroup(E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(E3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NH3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(NZ3)))
                    .addGroup(E3Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        E3Layout.setVerticalGroup(
            E3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NZ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NH3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        E4.setBackground(new java.awt.Color(204, 255, 255));

        jLabel5.setText("Zona exterior 4");

        NZ4.setEditable(false);

        NH4.setEditable(false);
        NH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NH4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout E4Layout = new javax.swing.GroupLayout(E4);
        E4.setLayout(E4Layout);
        E4Layout.setHorizontalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E4Layout.createSequentialGroup()
                .addGroup(E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(E4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NH4, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(NZ4)))
                    .addGroup(E4Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        E4Layout.setVerticalGroup(
            E4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(E4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NZ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NH4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ExteriorLayout = new javax.swing.GroupLayout(Exterior);
        Exterior.setLayout(ExteriorLayout);
        ExteriorLayout.setHorizontalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(E3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(E2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(E1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(E4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        ExteriorLayout.setVerticalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel12.setText("RANKING DE ZOMBIES");

        z1.setEditable(false);

        z2.setEditable(false);
        z2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z2ActionPerformed(evt);
            }
        });

        z3.setEditable(false);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/primero.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segundo.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tercero.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(z3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(z2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(91, 91, 91))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(z2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(z3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jLabel14.setText("Direccion");

        direccionTextField.setText("localhost");
        direccionTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                direccionTextFieldMouseClicked(evt);
            }
        });

        puertoTextField.setText("5000");
        puertoTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puertoTextFieldMouseClicked(evt);
            }
        });

        jLabel15.setText("Puerto");

        botonListoConexion.setText("Listo");
        botonListoConexion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonListoConexionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(puertoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(botonListoConexion))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(491, 491, 491))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(Exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Refugio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Tuneles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(puertoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(botonListoConexion)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed

        paused = !paused;  // Alterna estado de pausa/reanudación
        if (!paused) {
            cliente.enviarComando("REANUDAR");
            stopButton.setBackground(Color.red);
            System.out.println("reanudar");
        } else {
            cliente.enviarComando("PAUSAR");
            stopButton.setBackground(Color.green);
            System.out.println("parandoo");
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void NH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NH1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NH1ActionPerformed

    private void NH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NH2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NH2ActionPerformed

    private void NH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NH3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NH3ActionPerformed

    private void NH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NH4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NH4ActionPerformed

    private void NumHumanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumHumanosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NumHumanosActionPerformed

    private void T1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T1ActionPerformed

    private void T2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T2ActionPerformed

    private void T3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T3ActionPerformed

    private void T4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_T4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_T4ActionPerformed

    private void z2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_z2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (cliente != null) {
            try {
                cliente.desconectar();
            } catch (IOException ex) {
                System.err.println("Error al desconectar cliente: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void velocidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad1ActionPerformed
        System.out.println("velocidad=1");
        cliente.enviarComando("VELOCIDAD=1");
    }//GEN-LAST:event_velocidad1ActionPerformed

    private void velocidad10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad10ActionPerformed
        System.out.println("velocidad=10");
        cliente.enviarComando("VELOCIDAD=10");
    }//GEN-LAST:event_velocidad10ActionPerformed

    private void velocidad50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad50ActionPerformed
        System.out.println("velocidad=50");
        cliente.enviarComando("VELOCIDAD=50");
    }//GEN-LAST:event_velocidad50ActionPerformed

    private void direccionTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_direccionTextFieldMouseClicked
        direccionTextField.setText("");
    }//GEN-LAST:event_direccionTextFieldMouseClicked

    private void puertoTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puertoTextFieldMouseClicked
         puertoTextField.setText("");
    }//GEN-LAST:event_puertoTextFieldMouseClicked

    private void botonListoConexionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonListoConexionMouseClicked
        esperarInicioCliente();
    }//GEN-LAST:event_botonListoConexionMouseClicked

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
            java.util.logging.Logger.getLogger(ClientePantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientePantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientePantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientePantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                ClientePantalla.getInstancia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel E1;
    private javax.swing.JPanel E2;
    private javax.swing.JPanel E3;
    private javax.swing.JPanel E4;
    private javax.swing.JPanel Exterior;
    private javax.swing.JTextField NH1;
    private javax.swing.JTextField NH2;
    private javax.swing.JTextField NH3;
    private javax.swing.JTextField NH4;
    private javax.swing.JTextField NZ1;
    private javax.swing.JTextField NZ2;
    private javax.swing.JTextField NZ3;
    private javax.swing.JTextField NZ4;
    private javax.swing.JTextField NumHumanos;
    private javax.swing.JPanel Refugio;
    private javax.swing.JTextField T1;
    private javax.swing.JTextField T2;
    private javax.swing.JTextField T3;
    private javax.swing.JTextField T4;
    private javax.swing.JPanel Tunel1;
    private javax.swing.JPanel Tunel2;
    private javax.swing.JPanel Tunel3;
    private javax.swing.JPanel Tunel4;
    private javax.swing.JPanel Tuneles;
    private javax.swing.JButton botonListoConexion;
    private javax.swing.JPanel comedor;
    private javax.swing.JTextField comida;
    private javax.swing.JTextField direccionTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField puertoTextField;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton velocidad1;
    private javax.swing.JButton velocidad10;
    private javax.swing.JButton velocidad50;
    private javax.swing.JTextField z1;
    private javax.swing.JTextField z2;
    private javax.swing.JTextField z3;
    // End of variables declaration//GEN-END:variables
}
