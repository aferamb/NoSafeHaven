/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apocalipsis.nosafehaven.frontend;

import apocalipsis.nosafehaven.backend.Cliente;
import java.awt.Color;
import java.io.IOException;

import javax.swing.SwingUtilities;

public final class ClientePantalla extends javax.swing.JFrame {

    // Instancia estática y volatile para evitar problemas de visibilidad entre hilos
    private static volatile ClientePantalla instancia;

    private static volatile boolean paused = false;

    private Cliente cliente;

    /**
     * Constructor privado para evitar la creación de instancias
     */
    private ClientePantalla() {
        initComponents();
    }

    /**
     * Establece el cliente asociado a esta pantalla.
     *
     * @param cliente El objeto Cliente que se desea asociar.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene la instancia única de la clase ClientePantalla utilizando el
     * patrón Singleton. Este método garantiza que solo se cree una única
     * instancia de ClientePantalla, incluso en un entorno multihilo, mediante
     * el uso de sincronización y doble comprobación.
     *
     * @return La instancia única de ClientePantalla.
     */
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

    /**
     * Actualiza el texto de la etiqueta de comida en la interfaz gráfica. Este
     * método asegura que la actualización se realice en el hilo de despacho de
     * eventos de Swing para evitar problemas de concurrencia.
     *
     * @param comida La cantidad o descripción de comida que se mostrará en la
     * etiqueta.
     */
    public void actualizarComida(String comida) {
        SwingUtilities.invokeLater(() -> {
            this.comida.setText("Comida: " + comida);
        });
    }

    /**
     * Actualiza la información del refugio en la interfaz gráfica de usuario.
     *
     * La actualización se realiza en el hilo de la interfaz gráfica (Event
     * Dispatch Thread) utilizando SwingUtilities.invokeLater para garantizar la
     * seguridad en el manejo de la GUI.
     *
     * @param num El número de humanos en el refugio que se mostrará en la
     * etiqueta.
     */
    public void actualizarRefugio(String num) {
        SwingUtilities.invokeLater(() -> {
            this.NumHumanos.setText("Nº humanos: " + num);
        });
    }

    /**
     * Actualiza el número de humanos en la interfaz gráfica de usuario.
     *
     * La actualización se realiza en el hilo de la interfaz gráfica (Event
     * Dispatch Thread) utilizando SwingUtilities.invokeLater para garantizar la
     * seguridad en el manejo de la GUI.
     *
     * @param num El número de humanos que se mostrará en la etiqueta.
     */
    public void actualizarTotalHumanos(String num) {
        SwingUtilities.invokeLater(() -> {
            this.humanos.setText(num);
        });
    }

    /**
     * Actualiza el número de zombies en la interfaz gráfica de usuario.
     *
     * La actualización se realiza en el hilo de la interfaz gráfica (Event
     * Dispatch Thread) utilizando SwingUtilities.invokeLater para garantizar la
     * seguridad en el manejo de la GUI.
     *
     * @param num El número de zombies que se mostrará en la etiqueta.
     */
    public void actualizarTotalZombies(String num) {
        SwingUtilities.invokeLater(() -> {
            this.zombies.setText(num);
        });
    }

    /**
     * Actualiza el número de humanos en un túnel específico.
     *
     * La actualización se realiza en el hilo de la interfaz gráfica (Event
     * Dispatch Thread) utilizando SwingUtilities.invokeLater para garantizar la
     * seguridad en el manejo de la GUI.
     *
     * @param tunel El número del túnel que se actualizará (0-3).
     * @param num El número de humanos que se mostrará en la etiqueta del túnel.
     */
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

    /**
     * Actualiza la información del número de humanos en un exterior específico
     * de la interfaz gráfica.
     *
     * La actualización de la interfaz gráfica se realiza en el hilo de eventos
     * de Swing utilizando {@code SwingUtilities.invokeLater} para garantizar la
     * seguridad en el acceso a los componentes de la interfaz.
     *
     * @param exterior El número del exterior que se actualizará (0-3).
     * @param numH El número de humanos que se mostrará en la etiqueta del
     * exterior.
     */
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

    /**
     * Actualiza la información del número de zombies en un exterior específico
     * de la interfaz gráfica.
     *
     * La actualización de la interfaz gráfica se realiza en el hilo de eventos
     * de Swing utilizando {@code SwingUtilities.invokeLater} para garantizar la
     * seguridad en el acceso a los componentes de la interfaz.
     *
     * @param exterior El número del exterior que se actualizará (0-3).
     * @param numZ El número de zombies que se mostrará en la etiqueta del
     * exterior.
     */
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

    /**
     * Actualiza el texto de un componente de la interfaz gráfica basado en el
     * ranking proporcionado.
     *
     * La actualización del texto se realiza en el hilo de la interfaz gráfica
     * (Event Dispatch Thread) utilizando SwingUtilities.invokeLater para
     * garantizar la seguridad en el manejo de la interfaz.
     *
     * @param ranking El número del ranking (1, 2 o 3) que determina qué
     * componente actualizar. Debe ser un valor entero entre 1 y 3.
     * @param valor El texto que se establecerá en el componente correspondiente
     * al ranking.
     */
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Refugio = new javax.swing.JPanel();
        velocidad1 = new javax.swing.JButton();
        velocidad5 = new javax.swing.JButton();
        velocidad10 = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        BotonComidaExtra = new javax.swing.JButton();
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
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        comedor = new javax.swing.JPanel();
        NumHumanos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comida = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        humanos = new javax.swing.JTextField();
        zombies = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setMinimumSize(new java.awt.Dimension(2048, 1152));
        jPanel1.setPreferredSize(new java.awt.Dimension(1390, 690));
        jPanel1.setLayout(null);

        jLayeredPane1.setMinimumSize(new java.awt.Dimension(2048, 1152));

        Refugio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Refugio.setOpaque(false);

        velocidad1.setBackground(new java.awt.Color(51, 204, 0));
        velocidad1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        velocidad1.setText("Velocidad=x1");
        velocidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad1ActionPerformed(evt);
            }
        });

        velocidad5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        velocidad5.setText("Velocidad=x5");
        velocidad5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad5ActionPerformed(evt);
            }
        });

        velocidad10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        velocidad10.setText("Velocidad=x10");
        velocidad10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocidad10ActionPerformed(evt);
            }
        });

        stopButton.setBackground(new java.awt.Color(204, 0, 0));
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/boton5.jpg"))); // NOI18N
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Panel de control");

        BotonComidaExtra.setBackground(new java.awt.Color(204, 204, 204));
        BotonComidaExtra.setText("+ 50 Comida");
        BotonComidaExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonComidaExtraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RefugioLayout = new javax.swing.GroupLayout(Refugio);
        Refugio.setLayout(RefugioLayout);
        RefugioLayout.setHorizontalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefugioLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(BotonComidaExtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(velocidad1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(velocidad10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(velocidad5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        RefugioLayout.setVerticalGroup(
            RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefugioLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RefugioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(RefugioLayout.createSequentialGroup()
                        .addComponent(velocidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velocidad5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(velocidad10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonComidaExtra)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(Refugio, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Refugio);
        Refugio.setBounds(240, 80, 280, 230);

        Tuneles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Tuneles.setOpaque(false);

        Tunel1.setBackground(new java.awt.Color(111, 124, 123));

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel8.setText("Tunel 1");

        T1.setEditable(false);
        T1.setBackground(new java.awt.Color(204, 204, 204));
        T1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        T1.setText("Nº humanos: 0");
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
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(Tunel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel1Layout.createSequentialGroup()
                        .addComponent(T1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
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

        Tunel2.setBackground(new java.awt.Color(111, 124, 123));

        jLabel9.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel9.setText("Tunel 2");

        T2.setEditable(false);
        T2.setBackground(new java.awt.Color(204, 204, 204));
        T2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        T2.setText("Nº humanos: 0");
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
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(Tunel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel2Layout.createSequentialGroup()
                        .addComponent(T2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
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

        Tunel3.setBackground(new java.awt.Color(111, 124, 123));

        jLabel10.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel10.setText("Tunel 3");

        T3.setEditable(false);
        T3.setBackground(new java.awt.Color(204, 204, 204));
        T3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        T3.setText("Nº humanos: 0");
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
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(Tunel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel3Layout.createSequentialGroup()
                        .addComponent(T3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
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

        Tunel4.setBackground(new java.awt.Color(111, 124, 123));

        jLabel11.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel11.setText("Tunel 4");

        T4.setEditable(false);
        T4.setBackground(new java.awt.Color(204, 204, 204));
        T4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        T4.setText("Nº humanos: 0");
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
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(Tunel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Tunel4Layout.createSequentialGroup()
                        .addComponent(T4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45))))
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
            .addGroup(TunelesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        TunelesLayout.setVerticalGroup(
            TunelesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TunelesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Tunel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(Tunel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Tunel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(Tunel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jLayeredPane1.setLayer(Tuneles, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Tuneles);
        Tuneles.setBounds(620, 240, 300, 510);

        Exterior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        Exterior.setOpaque(false);

        E1.setBackground(new java.awt.Color(111, 124, 123));

        jLabel2.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel2.setText("Zona exterior 1");

        NZ1.setEditable(false);
        NZ1.setBackground(new java.awt.Color(204, 204, 204));
        NZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NZ1.setText("Nº  Zombies: 0");

        NH1.setEditable(false);
        NH1.setBackground(new java.awt.Color(204, 204, 204));
        NH1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NH1.setText("Nº humanos: 0");
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
                            .addComponent(NZ1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(NH1)))
                    .addGroup(E1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel2)))
                .addContainerGap(20, Short.MAX_VALUE))
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

        E2.setBackground(new java.awt.Color(111, 124, 123));

        jLabel3.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel3.setText("Zona exterior 2");

        NZ2.setEditable(false);
        NZ2.setBackground(new java.awt.Color(204, 204, 204));
        NZ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NZ2.setText("Nº  Zombies: 0");

        NH2.setEditable(false);
        NH2.setBackground(new java.awt.Color(204, 204, 204));
        NH2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NH2.setText("Nº humanos: 0");
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
                        .addGap(14, 14, 14)
                        .addGroup(E2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NZ2, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(NH2)))
                    .addGroup(E2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        E3.setBackground(new java.awt.Color(111, 124, 123));

        jLabel4.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel4.setText("Zona exterior 3");

        NZ3.setEditable(false);
        NZ3.setBackground(new java.awt.Color(204, 204, 204));
        NZ3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NZ3.setText("Nº  Zombies: 0");

        NH3.setEditable(false);
        NH3.setBackground(new java.awt.Color(204, 204, 204));
        NH3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NH3.setText("Nº humanos: 0");
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
                            .addComponent(NH3)
                            .addComponent(NZ3, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                    .addGroup(E3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
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

        E4.setBackground(new java.awt.Color(111, 124, 123));

        jLabel5.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel5.setText("Zona exterior 4");

        NZ4.setEditable(false);
        NZ4.setBackground(new java.awt.Color(204, 204, 204));
        NZ4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NZ4.setText("Nº  Zombies: 0");

        NH4.setEditable(false);
        NH4.setBackground(new java.awt.Color(204, 204, 204));
        NH4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NH4.setText("Nº humanos: 0");
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
                            .addComponent(NH4)
                            .addComponent(NZ4, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                    .addGroup(E4Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel5)))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(E3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(E1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        ExteriorLayout.setVerticalGroup(
            ExteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExteriorLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(E1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(E4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(Exterior, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(Exterior);
        Exterior.setBounds(1010, 240, 270, 510);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        jPanel2.setOpaque(false);

        jLabel12.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("RANKING DE ZOMBIES");

        z1.setEditable(false);
        z1.setBackground(new java.awt.Color(204, 204, 204));
        z1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        z2.setEditable(false);
        z2.setBackground(new java.awt.Color(204, 204, 204));
        z2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        z2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z2ActionPerformed(evt);
            }
        });

        z3.setEditable(false);
        z3.setBackground(new java.awt.Color(204, 204, 204));
        z3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/primero.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/segundo.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tercero.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(z2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(z3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(z1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(z2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(z3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jLayeredPane1.setLayer(jPanel2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPanel2);
        jPanel2.setBounds(240, 470, 280, 280);

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apocalipse2.jpg"))); // NOI18N
        jLayeredPane1.add(jLabel15);
        jLabel15.setBounds(-190, -40, 2048, 1152);

        jLabel17.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));
        jLabel17.setText("NO SAFE HAVEN");
        jLayeredPane1.setLayer(jLabel17, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jLabel17);
        jLabel17.setBounds(580, 120, 390, 60);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        jPanel3.setOpaque(false);

        comedor.setBackground(new java.awt.Color(111, 124, 123));

        NumHumanos.setEditable(false);
        NumHumanos.setBackground(new java.awt.Color(204, 204, 204));
        NumHumanos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumHumanos.setText("Nº humanos:");
        NumHumanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumHumanosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel1.setText("Refugio");

        comida.setEditable(false);
        comida.setBackground(new java.awt.Color(204, 204, 204));
        comida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(comedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(comedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(jPanel3, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPanel3);
        jPanel3.setBounds(240, 320, 280, 140);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 3));
        jPanel4.setOpaque(false);

        jLabel18.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Humanos VS  Zombies");

        humanos.setEditable(false);
        humanos.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        humanos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        humanos.setText("0");

        zombies.setEditable(false);
        zombies.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        zombies.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        zombies.setText("1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addContainerGap(51, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(humanos, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(zombies, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(humanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zombies, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(jPanel4, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPanel4);
        jPanel4.setBounds(1010, 110, 270, 100);

        jPanel1.add(jLayeredPane1);
        jLayeredPane1.setBounds(0, -2, 2048, 1160);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 3067, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1417, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void velocidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad1ActionPerformed
        velocidad1.setBackground(Color.GREEN);
        velocidad5.setBackground(Color.WHITE);
        velocidad10.setBackground(Color.WHITE);
        System.out.println("velocidad=1");
        cliente.enviarComando("VELOCIDAD=1");
    }//GEN-LAST:event_velocidad1ActionPerformed

    private void velocidad5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad5ActionPerformed
        velocidad1.setBackground(Color.WHITE);
        velocidad5.setBackground(Color.GREEN);
        velocidad10.setBackground(Color.WHITE);
        System.out.println("velocidad=5");
        cliente.enviarComando("VELOCIDAD=5");
    }//GEN-LAST:event_velocidad5ActionPerformed

    private void velocidad10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocidad10ActionPerformed
        velocidad1.setBackground(Color.WHITE);
        velocidad5.setBackground(Color.WHITE);
        velocidad10.setBackground(Color.GREEN);
        System.out.println("velocidad=10");
        cliente.enviarComando("VELOCIDAD=10");
    }//GEN-LAST:event_velocidad10ActionPerformed

    private void BotonComidaExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonComidaExtraActionPerformed
        cliente.enviarComando("COMIDA_EXTRA=50");
    }//GEN-LAST:event_BotonComidaExtraActionPerformed

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
    private javax.swing.JButton BotonComidaExtra;
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
    private javax.swing.JPanel comedor;
    private javax.swing.JTextField comida;
    private javax.swing.JTextField humanos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton stopButton;
    private javax.swing.JButton velocidad1;
    private javax.swing.JButton velocidad10;
    private javax.swing.JButton velocidad5;
    private javax.swing.JTextField z1;
    private javax.swing.JTextField z2;
    private javax.swing.JTextField z3;
    private javax.swing.JTextField zombies;
    // End of variables declaration//GEN-END:variables
}
