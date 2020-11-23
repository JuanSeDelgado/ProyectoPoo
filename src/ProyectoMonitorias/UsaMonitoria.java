/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoMonitorias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ¿
 */
public class UsaMonitoria extends javax.swing.JFrame {

    /**
     * Creates new form UsaMonitoria
     */
    LinkedList<Monitoria> listaMonitorias;
    int numeroC = 1;

    public UsaMonitoria() {
        initComponents();
        listaMonitorias = new LinkedList<>();
        setLocationRelativeTo(null);
        promedioLabel.setVisible(false);
        semestreLabel.setVisible(false);
        txt4.setVisible(false);
        txt5.setVisible(false);
        recuperarDatos(listaMonitorias);

    }

    public int sumarUno() {
        if (numeroC < numeroC + 1) {
            numeroC++;
        }
        return numeroC;
    }

    // Despues de guardar una monitoria setea todos los textField vacios, y a su formato inicial
    public void setVoidAll() {
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
        txt6.setText("");
        txt7.setText("");
        txt8.setText("AAAA-MM-DD");
        txt9.setText("AAAA-MM-DD");
        time1.setText("HH:MM:SS");
        time2.setText("HH:MM:SS");
        tipoEstudiante.setSelectedIndex(0);
    }

    private void resizeTable() {
        table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        table1.getColumnModel().getColumn(2).setPreferredWidth(200);
        table1.getColumnModel().getColumn(3).setPreferredWidth(100);
        table1.getColumnModel().getColumn(6).setPreferredWidth(150);
        table1.getColumnModel().getColumn(7).setPreferredWidth(125);
        table1.getColumnModel().getColumn(8).setPreferredWidth(125);
    }

    private void resizeTable2() {
        table2.getColumnModel().getColumn(0).setPreferredWidth(30);
        table2.getColumnModel().getColumn(2).setPreferredWidth(180);
        table2.getColumnModel().getColumn(3).setPreferredWidth(100);
        table2.getColumnModel().getColumn(6).setPreferredWidth(150);
        table2.getColumnModel().getColumn(7).setPreferredWidth(125);
        table2.getColumnModel().getColumn(8).setPreferredWidth(125);
        table2.getColumnModel().getColumn(9).setPreferredWidth(80);
    }

    public void almacenarDatos(LinkedList<Monitoria> monitorias) {

        try {

            String nombreArchivo = "datos.txt";
            PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter(nombreArchivo)));
            String linea = "";
            for (Monitoria obj : monitorias) {
                if (obj.getSuEstudiante() instanceof EstudiantePostgrado) {
                    EstudiantePostgrado getPost = (EstudiantePostgrado) obj.getSuEstudiante();
                    linea = "1" + "--" + obj.getConsecutivo() + "--" + obj.getSuEstudiante().getCodigo() + "--"
                            + obj.getSuEstudiante().getNombre() + "--" + obj.getSuEstudiante().getPrograma() + "--"
                            + getPost.getSemestre() + "--" + obj.getMateria() + "--" + obj.getTema() + "--"
                            + obj.getFechaInicio() + "--" + obj.getFechaFinal();
                    salida.println(linea);
                    //JOptionPane.showMessageDialog(null, "exito");
                }
                if (obj.getSuEstudiante() instanceof EstudiantePregrado) {
                    EstudiantePregrado getPre = (EstudiantePregrado) obj.getSuEstudiante();
                    linea = "2" + "--" + obj.getConsecutivo() + "--" + obj.getSuEstudiante().getCodigo() + "--"
                            + obj.getSuEstudiante().getNombre() + "--" + obj.getSuEstudiante().getPrograma() + "--"
                            + getPre.getPromedio() + "--" + obj.getMateria() + "--" + obj.getTema() + "--"
                            + obj.getFechaInicio() + "--" + obj.getFechaFinal();
                    salida.println(linea);
                    //JOptionPane.showMessageDialog(null, "exito");
                }
            }
            salida.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "error al guardar los datos  " + e.toString());
        }
    }

    public void recuperarDatos(LinkedList<Monitoria> monitorias) {

        try {
            String nombreArchivo = "datos.txt";
            BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo));
            String[] datos;
            String linea, nombre, codigo, programa, materia, consecutivo, tema;
            int semestre;
            double promedio;
            LocalDateTime fechaInicio, fechaFinal;

            while ((linea = entrada.readLine()) != null) {

                datos = linea.split("--");

                if (datos[0].equalsIgnoreCase("1")) {
                    consecutivo = datos[1];
                    codigo = datos[2];
                    nombre = datos[3];
                    programa = datos[4];
                    semestre = Integer.parseInt(datos[5]);
                    materia = datos[6];
                    tema = datos[7];
                    fechaInicio = LocalDateTime.parse(datos[8]);
                    fechaFinal = LocalDateTime.parse(datos[9]);
                    monitorias.add(new Monitoria(materia, tema, consecutivo, fechaInicio, fechaFinal, new EstudiantePostgrado(semestre, nombre, programa, codigo)));

                }
                if (datos[0].equalsIgnoreCase("2")) {
                    consecutivo = datos[1];
                    codigo = datos[2];
                    nombre = datos[3];
                    programa = datos[4];
                    promedio = Double.parseDouble(datos[5]);
                    materia = datos[6];
                    tema = datos[7];
                    fechaInicio = LocalDateTime.parse(datos[8]);
                    fechaFinal = LocalDateTime.parse(datos[9]);
                    monitorias.add(new Monitoria(materia, tema, consecutivo, fechaInicio, fechaFinal, new EstudiantePregrado(promedio, nombre, programa, codigo)));

                }

            }
            continuarConsecutivo(monitorias);
            entrada.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al recuperar los datos " + e.toString());
        }
    }

    public void continuarConsecutivo(LinkedList<Monitoria> mAux) {

        int sMayor = Integer.parseInt(mAux.get(0).getConsecutivo());
        for (int i = 1; i < mAux.size(); i++) {

            int actual = Integer.parseInt(mAux.get(i).getConsecutivo());

            if (actual > sMayor) {

                sMayor = actual;
            }

        }
        numeroC = sMayor + 1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        txt2 = new javax.swing.JTextField();
        txt3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tipoEstudiante = new javax.swing.JComboBox<>();
        promedioLabel = new javax.swing.JLabel();
        txt4 = new javax.swing.JTextField();
        semestreLabel = new javax.swing.JLabel();
        txt5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt6 = new javax.swing.JTextField();
        txt7 = new javax.swing.JTextField();
        txt8 = new javax.swing.JTextField();
        txt9 = new javax.swing.JTextField();
        BuscarEstudiante = new javax.swing.JButton();
        GuardarMonitoria = new javax.swing.JButton();
        time1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        time2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        ConsultarMonitorias = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtConsultar = new javax.swing.JTextField();
        ConsultarCodigo = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Estudiante");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Programa:");

        txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt1KeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Tipo Estudiante");

        tipoEstudiante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-", "Pregrado", "PostGrado" }));
        tipoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoEstudianteActionPerformed(evt);
            }
        });

        promedioLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        promedioLabel.setText("Promedio:");
        promedioLabel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                promedioLabelComponentHidden(evt);
            }
        });

        txt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt4KeyTyped(evt);
            }
        });

        semestreLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        semestreLabel.setText("Semestre:");

        txt5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt5ActionPerformed(evt);
            }
        });
        txt5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt5KeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Monitoria");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Materia:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tema:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Fecha de Inicio:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Fecha de Fin:");

        txt8.setText("AAAA-MM-DD");

        txt9.setText("AAAA-MM-DD");

        BuscarEstudiante.setText("Buscar Estudiante");
        BuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarEstudianteActionPerformed(evt);
            }
        });

        GuardarMonitoria.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        GuardarMonitoria.setText("Guardar Monitoria");
        GuardarMonitoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarMonitoriaActionPerformed(evt);
            }
        });

        time1.setText("HH:MM:SS");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Hora");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Hora");

        time2.setText("HH:MM:SS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(promedioLabel)
                            .addComponent(semestreLabel))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(tipoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GuardarMonitoria)
                        .addGap(161, 161, 161))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(250, 250, 250)
                                .addComponent(BuscarEstudiante)
                                .addGap(88, 88, 88)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt6)
                                    .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(473, 473, 473)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(time2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(39, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(291, 291, 291))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BuscarEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tipoEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(promedioLabel)
                            .addComponent(txt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(semestreLabel)
                            .addComponent(txt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(time2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(GuardarMonitoria)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresar Monitoria", jPanel1);

        ConsultarMonitorias.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ConsultarMonitorias.setText("Consultar Todas Las Monitorias");
        ConsultarMonitorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarMonitoriasActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Presione el botón para visualizar todas las monitorias registradas hasta el momento.");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Codigo", "Nombre", "Programa", "Promedio", "Semestre", "Asignatura/Tema", "Dia/Hora(Inicio)", "Dia/Hora(Final)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table1);
        if (table1.getColumnModel().getColumnCount() > 0) {
            table1.getColumnModel().getColumn(0).setResizable(false);
            table1.getColumnModel().getColumn(1).setResizable(false);
            table1.getColumnModel().getColumn(2).setResizable(false);
            table1.getColumnModel().getColumn(3).setResizable(false);
            table1.getColumnModel().getColumn(4).setResizable(false);
            table1.getColumnModel().getColumn(5).setResizable(false);
            table1.getColumnModel().getColumn(6).setResizable(false);
            table1.getColumnModel().getColumn(7).setResizable(false);
            table1.getColumnModel().getColumn(8).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ConsultarMonitorias)
                .addGap(292, 292, 292))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(ConsultarMonitorias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar Monitorias", jPanel2);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Codigo", "Nombre", "Programa", "Promedio", "Semestre", "Asignatura/Tema", "Dia/Hora(Inicio)", "Dia/Hora(Final)", "Duración"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table2.getTableHeader().setReorderingAllowed(false);
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setResizable(false);
            table2.getColumnModel().getColumn(1).setResizable(false);
            table2.getColumnModel().getColumn(2).setResizable(false);
            table2.getColumnModel().getColumn(3).setResizable(false);
            table2.getColumnModel().getColumn(4).setResizable(false);
            table2.getColumnModel().getColumn(5).setResizable(false);
            table2.getColumnModel().getColumn(6).setResizable(false);
            table2.getColumnModel().getColumn(7).setResizable(false);
            table2.getColumnModel().getColumn(8).setResizable(false);
        }

        jLabel14.setText("Digite el codigo del estudiante del cual desea consultar las monitorias a las que a asistido: ");

        txtConsultar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsultarKeyTyped(evt);
            }
        });

        ConsultarCodigo.setText("Consultar");
        ConsultarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarCodigoActionPerformed(evt);
            }
        });

        jLabel15.setText("Tiempo total de monitorias:");

        jTextField1.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addComponent(ConsultarCodigo))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 309, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ConsultarCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98))
        );

        jTabbedPane1.addTab("Consultar Monitorias De Un Estudiante", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void promedioLabelComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_promedioLabelComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_promedioLabelComponentHidden

    private void txt5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt5ActionPerformed

    private void BuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarEstudianteActionPerformed
        // TODO add your handling code here:
        String codigo = txt1.getText();
        boolean t = true;

        for (int i = 0; i < listaMonitorias.size(); i++) {

            if (codigo.equals(listaMonitorias.get(i).getSuEstudiante().getCodigo())) {
                t = true;
                txt2.setText(listaMonitorias.get(i).getSuEstudiante().getNombre());
                txt3.setText(listaMonitorias.get(i).getSuEstudiante().getPrograma());

                if (listaMonitorias.get(i).getSuEstudiante() instanceof EstudiantePregrado) {
                    tipoEstudiante.setSelectedIndex(1);
                    promedioLabel.setVisible(true);
                    txt4.setVisible(true);
                    EstudiantePregrado getPre = (EstudiantePregrado) listaMonitorias.get(i).getSuEstudiante();
                    txt4.setText(getPre.getPromedio() + "");
                } else if (listaMonitorias.get(i).getSuEstudiante() instanceof EstudiantePostgrado) {
                    tipoEstudiante.setSelectedIndex(2);
                    semestreLabel.setVisible(true);
                    txt5.setVisible(true);
                    EstudiantePostgrado getPost = (EstudiantePostgrado) listaMonitorias.get(i).getSuEstudiante();
                    txt5.setText(getPost.getSemestre() + "");

                }
                break;

            } else {
                t = false;

            }
        }
        if (t == false) {

            JOptionPane.showMessageDialog(null, "El estudiante buscado no ha sido registrado anteriormente");

        }


    }//GEN-LAST:event_BuscarEstudianteActionPerformed

    private void tipoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoEstudianteActionPerformed
        // TODO add your handling code here:
        if (tipoEstudiante.getSelectedIndex() == 1) {
            promedioLabel.setVisible(true);
            txt4.setVisible(true);
            semestreLabel.setVisible(false);
            txt5.setVisible(false);
        } else if (tipoEstudiante.getSelectedIndex() == 2) {
            semestreLabel.setVisible(true);
            txt5.setVisible(true);
            promedioLabel.setVisible(false);
            txt4.setVisible(false);
        }

    }//GEN-LAST:event_tipoEstudianteActionPerformed

    private void GuardarMonitoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarMonitoriaActionPerformed
        // TODO add your handling code here:
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFinal = null;

        String nombre = txt2.getText();
        String codigo = txt1.getText();
        String programa = txt3.getText();
        String materia = txt6.getText();
        String tema = txt7.getText();

        double promedio = 0;
        int semestre = 0;

        if ("".equals(nombre) || "".equals(codigo) || "".equals(programa) || "".equals(materia) || "".equals(tema) || fechaFinal == null || fechaInicio == null) {

            JOptionPane.showMessageDialog(null, "Verifique los campos vacios");
        }

        //Try-Catch para el formato de la fecha y hora
        try {
            String fechaI1 = txt8.getText();
            String timeI1 = "T" + time1.getText();
            String fechaI2 = fechaI1 + timeI1;

            String fechaF1 = txt9.getText();
            String time2G = "T" + time2.getText();
            String fechaF2 = fechaF1 + time2G;

            fechaInicio = LocalDateTime.parse(fechaI2);
            fechaFinal = LocalDateTime.parse(fechaF2);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Ingrese la fecha usando el siguiente formato AAAA-MM-DD y para la hora HH:MM:SS");

        }

        //Condicional if para tomar valor de promedio o semestre dependiendo si escoge un estudiante de post o pre grado
        if (promedioLabel.isVisible()) {
            try {
                promedio = Double.parseDouble(txt4.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Porfavor digite un promedio");
            }
        } else if (semestreLabel.isVisible()) {
            try {
                semestre = Integer.parseInt(txt5.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Porfavor Digite un semestre");
            }
        }

        if ((tipoEstudiante.getSelectedIndex() == 1) && !"".equals(nombre) && !"".equals(codigo) && !"".equals(programa) && !"".equals(materia) && !"".equals(tema) && !(fechaFinal==null)&& !(fechaInicio==null) ) {
            EstudiantePregrado estudiantePre = new EstudiantePregrado(promedio, nombre, programa, codigo);
            Monitoria monitoria = new Monitoria(materia, tema, numeroC + "", fechaInicio, fechaFinal, estudiantePre);
            listaMonitorias.add(monitoria);
            setVoidAll();
            sumarUno();
            JOptionPane.showMessageDialog(null, "Se ha guardado correctamente");
        } else if ((tipoEstudiante.getSelectedIndex() == 2) && !"".equals(nombre) && !"".equals(codigo) && !"".equals(programa) && !"".equals(materia) && !"".equals(tema)) {
            EstudiantePostgrado estudiantePost = new EstudiantePostgrado(semestre, nombre, programa, codigo);
            Monitoria monitoria = new Monitoria(materia, tema, numeroC + "", fechaInicio, fechaFinal, estudiantePost);
            listaMonitorias.add(monitoria);
            setVoidAll();
            sumarUno();
            JOptionPane.showMessageDialog(null, "Se ha guardado correctamente");
        } else if (tipoEstudiante.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Porfavor seleccione un tipo de estudiante");
        }
        almacenarDatos(listaMonitorias);
        if (tipoEstudiante.getSelectedIndex() == 0) {
            promedioLabel.setVisible(false);
            txt4.setVisible(false);
            semestreLabel.setVisible(false);
            txt5.setVisible(false);
        }


    }//GEN-LAST:event_GuardarMonitoriaActionPerformed


    private void txt4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt4KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && (c < '.' || c > '.') && (c != '\b')) {
            evt.consume();
        }

    }//GEN-LAST:event_txt4KeyTyped

    private void txt5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt5KeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && (c < '.' || c > '.') && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt5KeyTyped

    private void txt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt1KeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txt1KeyTyped

    private void ConsultarMonitoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarMonitoriasActionPerformed
        // TODO add your handling code here:

        Collections.sort(listaMonitorias, (x, y) -> x.getSuEstudiante().getCodigo().compareToIgnoreCase(y.getSuEstudiante().getCodigo()));

        String matriz[][] = new String[listaMonitorias.size()][9];

        for (int i = 0; i < listaMonitorias.size(); i++) {

            matriz[i][0] = listaMonitorias.get(i).getConsecutivo();
            matriz[i][1] = listaMonitorias.get(i).getSuEstudiante().getCodigo();
            matriz[i][2] = listaMonitorias.get(i).getSuEstudiante().getNombre();
            matriz[i][3] = listaMonitorias.get(i).getSuEstudiante().getPrograma();

            if (listaMonitorias.get(i).getSuEstudiante() instanceof EstudiantePregrado) {
                EstudiantePregrado getPre = (EstudiantePregrado) listaMonitorias.get(i).getSuEstudiante();
                matriz[i][4] = getPre.getPromedio() + "";

            }
            if (listaMonitorias.get(i).getSuEstudiante() instanceof EstudiantePostgrado) {
                EstudiantePostgrado getPost = (EstudiantePostgrado) listaMonitorias.get(i).getSuEstudiante();
                matriz[i][5] = getPost.getSemestre() + "";
            }

            matriz[i][6] = listaMonitorias.get(i).getMateria() + " / " + listaMonitorias.get(i).getTema();
            matriz[i][7] = listaMonitorias.get(i).getFechaInicio() + "";
            matriz[i][8] = listaMonitorias.get(i).getFechaFinal() + "";

        }

        table1.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                new String[]{
                    "No.", "Codigo", "Nombre", "Programa", "Promedio", "Semestre", "Asignatura/Tema", "Dia/Hora(Inicio)", "Dia/Hora(Final)"
                }
        ));

        resizeTable();


    }//GEN-LAST:event_ConsultarMonitoriasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:

        resizeTable();
        resizeTable2();


    }//GEN-LAST:event_formWindowOpened

    private void ConsultarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarCodigoActionPerformed
        // TODO add your handling code here:
        int c = 0;
        int tiempoTotal = 0;
        LinkedList<Monitoria> aux = new LinkedList<>();
        String codigo = txtConsultar.getText();
        if (codigo.equalsIgnoreCase("")) {

            JOptionPane.showMessageDialog(null, "Por favor ingrese un codigo");
        }

        for (int j = 0; j < listaMonitorias.size(); j++) {
            if (codigo.equalsIgnoreCase(listaMonitorias.get(j).getSuEstudiante().getCodigo())) {
                c++;

                aux.add(listaMonitorias.get(j));

            }
        }

        for (Monitoria obj : aux) {
            tiempoTotal += obj.tiempoMonitoria();
        }
        jTextField1.setText(tiempoTotal + " Minutos");

        String matriz2[][] = new String[aux.size()][10];

        for (int i = 0; i < aux.size(); i++) {

            matriz2[i][0] = aux.get(i).getConsecutivo();
            matriz2[i][1] = aux.get(i).getSuEstudiante().getCodigo();
            matriz2[i][2] = aux.get(i).getSuEstudiante().getNombre();
            matriz2[i][3] = aux.get(i).getSuEstudiante().getPrograma();

            if (aux.get(i).getSuEstudiante() instanceof EstudiantePregrado) {

                EstudiantePregrado getPre = (EstudiantePregrado) aux.get(i).getSuEstudiante();
                matriz2[i][4] = getPre.getPromedio() + "";

            }
            if (aux.get(i).getSuEstudiante() instanceof EstudiantePostgrado) {

                EstudiantePostgrado getPost = (EstudiantePostgrado) aux.get(i).getSuEstudiante();
                matriz2[i][5] = getPost.getSemestre() + "";
            }

            matriz2[i][6] = aux.get(i).getMateria() + " / " + aux.get(i).getTema();
            matriz2[i][7] = aux.get(i).getFechaInicio() + "";
            matriz2[i][8] = aux.get(i).getFechaFinal() + "";
            matriz2[i][9] = aux.get(i).tiempoMonitoria() + "";

        }

        table2.setModel(new javax.swing.table.DefaultTableModel(
                matriz2,
                new String[]{
                    "No.", "Codigo", "Nombre", "Programa", "Promedio", "Semestre", "Asignatura/Tema", "Dia/Hora(Inicio)", "Dia/Hora(Final)", "Duración"
                }
        ));

        resizeTable2();


    }//GEN-LAST:event_ConsultarCodigoActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_table2MouseClicked

    private void txtConsultarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultarKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if ((c < '0' || c > '9') && (c != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtConsultarKeyTyped

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
            java.util.logging.Logger.getLogger(UsaMonitoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsaMonitoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsaMonitoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsaMonitoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsaMonitoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarEstudiante;
    private javax.swing.JButton ConsultarCodigo;
    private javax.swing.JButton ConsultarMonitorias;
    private javax.swing.JButton GuardarMonitoria;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel promedioLabel;
    private javax.swing.JLabel semestreLabel;
    private javax.swing.JTable table1;
    private javax.swing.JTable table2;
    private javax.swing.JTextField time1;
    private javax.swing.JTextField time2;
    private javax.swing.JComboBox<String> tipoEstudiante;
    private javax.swing.JTextField txt1;
    private javax.swing.JTextField txt2;
    private javax.swing.JTextField txt3;
    private javax.swing.JTextField txt4;
    private javax.swing.JTextField txt5;
    private javax.swing.JTextField txt6;
    private javax.swing.JTextField txt7;
    private javax.swing.JTextField txt8;
    private javax.swing.JTextField txt9;
    private javax.swing.JTextField txtConsultar;
    // End of variables declaration//GEN-END:variables

}
