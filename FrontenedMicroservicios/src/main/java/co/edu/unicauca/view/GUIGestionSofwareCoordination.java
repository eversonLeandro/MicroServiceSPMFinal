/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.view;

import co.edu.unicauca.access.Factory;
import co.edu.unicauca.access.SessionManager;
import co.edu.unicauca.domain.entities.Company;
import co.edu.unicauca.domain.entities.Project;
import co.edu.unicauca.domain.entities.Student;
import co.edu.unicauca.domain.entities.User;
import co.edu.unicauca.domain.services.CompanyService;
import co.edu.unicauca.domain.services.ProjectService;
import co.edu.unicauca.domain.services.StudentService;
import co.edu.unicauca.infra.Messages;
import co.edu.unicauca.infra.Subject;
import co.edu.unicauca.interfaces.IProjectObserver;
import co.edu.unicauca.interfaces.IRepository;
import co.edu.unicauca.main.Main;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.Color;
import java.awt.Paint;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 *
 * @author Brayan
 */
public class GUIGestionSofwareCoordination extends javax.swing.JFrame implements IProjectObserver {

    ProjectService projectService;
    User usuario;
    List<Project> proyectos;
    List<Company> companies;
    List<Student> students;
    private String ultimoTipoGrafico = "tabla";


    public GUIGestionSofwareCoordination(ProjectService projectService, User usuario) {
        
        initComponents();
        agregarEventos();
        this.projectService = projectService;

        Subject.getInstance().agregarObservador(this);

        this.usuario = usuario;
        proyectos = projectService.listarProyectos();
        llenarCBXperiodo();
        actualizarTablaP(proyectos);
        configurarEventosTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnRegistrarEmpresa = new javax.swing.JButton();
        btnGestionarProyecto = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblProyectos = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblProyectosregistrados = new javax.swing.JLabel();
        cbxPeriodoAcademico = new javax.swing.JComboBox<>();
        btnBarras = new javax.swing.JButton();
        btnPastel = new javax.swing.JButton();
        btnProjects = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtnombrecordinador = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel11.setText("Empresas registradas en el periodo 2025.1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(247, 247, 247));

        jButton1.setBackground(new java.awt.Color(223, 224, 226));
        jButton1.setText("Registrar Estudiante");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnRegistrarEmpresa.setBackground(new java.awt.Color(223, 224, 226));
        btnRegistrarEmpresa.setText("Registrar Empresa");
        btnRegistrarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarEmpresaActionPerformed(evt);
            }
        });

        btnGestionarProyecto.setBackground(new java.awt.Color(223, 224, 226));
        btnGestionarProyecto.setText("Gestionar proyecto");
        btnGestionarProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarProyectoActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(223, 224, 226));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("            Gestion de Proyectos Sofware Academicos de ingenieria de sistemas");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel7.setText("Empresas");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setText("Estudiantes");

        lblProyectos.setText("Proyectos");
        lblProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProyectosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addGap(49, 49, 49)
                .addComponent(jLabel8)
                .addGap(61, 61, 61)
                .addComponent(lblProyectos)
                .addContainerGap(337, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(lblProyectos))
                .addContainerGap())
        );

        jLabel10.setText("Opciones");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titulo", "Empresa", "Fecha de Entrega", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        lblProyectosregistrados.setText("Proyectos registrados");

        cbxPeriodoAcademico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbxPeriodoAcademico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPeriodoAcademicoActionPerformed(evt);
            }
        });

        btnBarras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grafico-de-barras-creciente.png"))); // NOI18N
        btnBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarrasActionPerformed(evt);
            }
        });

        btnPastel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/grafico-de-pastel.png"))); // NOI18N
        btnPastel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPastelActionPerformed(evt);
            }
        });

        btnProjects.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/insert_table_icon_155239.png"))); // NOI18N
        btnProjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProjectsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblProyectosregistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxPeriodoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnProjects, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPastel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPastel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbxPeriodoAcademico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblProyectosregistrados))
                    .addComponent(btnProjects, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        jLabel13.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 204));
        jLabel13.setText("Coordinador de sistema");
        jLabel13.setFocusable(false);

        txtnombrecordinador.setEditable(false);
        txtnombrecordinador.setBackground(new java.awt.Color(247, 247, 247));
        txtnombrecordinador.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtnombrecordinador.setBorder(null);
        txtnombrecordinador.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(54, 54, 54)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtnombrecordinador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(58, 58, 58))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnRegistrarEmpresa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGestionarProyecto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnombrecordinador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(3, 3, 3)
                        .addComponent(btnRegistrarEmpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGestionarProyecto)
                        .addGap(9, 9, 9)
                        .addComponent(btnSalir))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, "card5");
        jPanel4.add(jLabel4, "card3");

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        IRepository studentRepository = Factory.getInstance().getRepository("student");

        //   CompanyService servicecompany = new CompanyService(serviceRepository);
        StudentService servicestudent = new StudentService(studentRepository);

        GUIRegisterStudent instance = new GUIRegisterStudent(servicestudent);
        instance.setExtendedState(JFrame.NORMAL);
        instance.setSize(450, 380); // Ajusta el tamaño a 600x400 píxeles
        instance.setLocationRelativeTo(null); // Centrar en pantalla        
        instance.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtnombrecordinadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombrecordinadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecordinadorActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        SessionManager.setToken(null);
        Main.mostrarLogin();
        this.dispose();

    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRegistrarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarEmpresaActionPerformed
        IRepository CompanyRepository = Factory.getInstance().getRepository("company");

        //   CompanyService servicecompany = new CompanyService(serviceRepository);
        CompanyService servicecompany = new CompanyService(CompanyRepository);

        GUIRegistreCompany instance = new GUIRegistreCompany(servicecompany);
        instance.setExtendedState(JFrame.NORMAL);
        instance.setSize(450, 380); // Ajusta el tamaño a 600x400 píxeles
        instance.setLocationRelativeTo(null); // Centrar en pantalla        
        instance.setVisible(true);
    }//GEN-LAST:event_btnRegistrarEmpresaActionPerformed

    private void lblProyectosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProyectosMouseClicked

    }//GEN-LAST:event_lblProyectosMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked
    private void actualizarVisualizacion() {
        List<Project> filtrados = filtrar();

        actualizarTablaP(filtrados);

        if (filtrados == null || filtrados.isEmpty()) {
            jScrollPane1.setViewportView(null); // Limpia la gráfica si no hay datos
            return;
        }

        // Mostrar el tipo de gráfico actualmente activo
        if (ultimoTipoGrafico.equals("barras")) {
            ChartPanel chartPanel = crearGraficaDeBarrasEstado(filtrados);
            jScrollPane1.setViewportView(chartPanel);
        } else if (ultimoTipoGrafico.equals("pastel")) {
            ChartPanel chartPanel = crearGraficaPastelEstado(filtrados);
            jScrollPane1.setViewportView(chartPanel);

        } else if (ultimoTipoGrafico.equals("tabla")) {
            jScrollPane1.setViewportView(jTable1);
        }
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }
    private void cbxPeriodoAcademicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeriodoAcademicoActionPerformed
        actualizarVisualizacion();


    }//GEN-LAST:event_cbxPeriodoAcademicoActionPerformed

    private void btnBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarrasActionPerformed
        ultimoTipoGrafico = "barras";
        actualizarVisualizacion();

    }//GEN-LAST:event_btnBarrasActionPerformed
    private ChartPanel crearGraficaDeBarrasEstado(List<Project> proyectos) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> contadorPorSector = new HashMap<>();

        for (Project p : proyectos) {
            String estado = p.getEstadoTexto();
            if (estado == null || estado.trim().isEmpty()) {
                estado = "Sin estado";
            }
            contadorPorSector.put(estado, contadorPorSector.getOrDefault(estado, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : contadorPorSector.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), "");
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "Proyectos por Estado",
                "Estado",
                "Cantidad de Proyectos",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        CategoryPlot plot = chart.getCategoryPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        Paint[] colors = {
            new Color(79, 129, 189), // Azul
            new Color(192, 80, 77), // Rojo
            new Color(155, 187, 89), // Verde
            new Color(128, 100, 162), // Morado
            new Color(247, 150, 70), // Naranja
            new Color(75, 172, 198) // Cyan
        };

        for (int i = 0; i < dataset.getRowCount(); i++) {
            renderer.setSeriesPaint(i, colors[i % colors.length]);
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 180));
        return chartPanel;
    }


    private void btnPastelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPastelActionPerformed

        ultimoTipoGrafico = "pastel";
        actualizarVisualizacion();
    }//GEN-LAST:event_btnPastelActionPerformed

    private ChartPanel crearGraficaPastelEstado(List<Project> proyectos) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> contadorPorEstado = new HashMap<>();
        for (Project p : proyectos) {
            String estado = p.getEstadoTexto();
            if (estado == null || estado.trim().isEmpty()) {
                estado = "Sin estado"; 
            }
            contadorPorEstado.put(estado, contadorPorEstado.getOrDefault(estado, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : contadorPorEstado.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        // Crear gráfico de pastel
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de Proyectos por Estado", 
                dataset, // dataset
                true, // incluir leyenda
                true, // tooltips
                false // URLs
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));
        chartPanel.setPreferredSize(new Dimension(300, 180));

        return chartPanel;
    }


    private void btnProjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProjectsActionPerformed

        ultimoTipoGrafico = "tabla";
        this.proyectos = projectService.listarProyectos();
        actualizarTablaP(filtrar());        // Actualiza datos de la tabla
        jScrollPane1.setViewportView(jTable1);  // Muestra la tabla en el JScrollPane
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }//GEN-LAST:event_btnProjectsActionPerformed

    private void btnGestionarProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarProyectoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGestionarProyectoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBarras;
    private javax.swing.JButton btnGestionarProyecto;
    private javax.swing.JButton btnPastel;
    private javax.swing.JButton btnProjects;
    private javax.swing.JButton btnRegistrarEmpresa;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxPeriodoAcademico;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblProyectos;
    private javax.swing.JLabel lblProyectosregistrados;
    private javax.swing.JTextField txtnombrecordinador;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizarProyectos(List<Project> proyectos) {

        this.proyectos = proyectos;
        actualizarTablaP(filtrar());
    }

    public List<Project> filtrar() {
        String periodoSeleccionado = cbxPeriodoAcademico.getSelectedItem().toString();
        List<Project> filtrados = null;

        if (periodoSeleccionado.equalsIgnoreCase("All")) {
            return proyectos;
        } else {
            filtrados = proyectos.stream()
                    .filter(p -> p.getPeriodoAcademico().equalsIgnoreCase(periodoSeleccionado))
                    .collect(Collectors.toList());
            return filtrados;
        }
    }

    private void agregarEventos() {
        lblProyectos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                proyectos = projectService.listarProyectos();
                actualizarTablaP(proyectos);
            }

        });
    }

    private void actualizarTablaP(List<Project> proyectos) {
        txtnombrecordinador.setText(usuario.getUsername());
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla
        model.setColumnIdentifiers(new String[]{"Título", "Empresa", "Fecha Entrega", "Estado"}); // Definir columnas

        if (proyectos == null || proyectos.isEmpty()) {
            Messages.showMessageDialog("No existen proyectos registrados.", "Información");
            return; // Salir del método para no procesar datos vacíos
        }
        for (Project p : proyectos) {

            int meses = 0;
            try {
                meses = Integer.parseInt(p.getTiempoMaximo());
            } catch (NumberFormatException e) {
                // Puedes asignar un valor predeterminado o registrar el error
                meses = 0;
            }
            LocalDate fechaEntrega = LocalDate.now().plusMonths(meses);
            model.addRow(new Object[]{
                p.getNombre(),
                p.getNombreEmpresa(),
                fechaEntrega.toString(),
                p.getEstadoTexto()
            });
        }

    }


    private void actualizarTablaEmpresas(List<Company> companies) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla
        model.setColumnIdentifiers(new String[]{"NIT", "Nombre", "Teléfono", "Nombre Contacto", "Sector"}); // Cambiar encabezados

        if (companies == null || companies.isEmpty()) {
            Messages.showMessageDialog("No existen empresas registradas.", "Información");
            return;
        }

        for (Company c : companies) {
            model.addRow(new Object[]{
                c.getNit(),
                c.getNombre(),
                c.getTelefono(),
                c.getNombrecontaccto(), // Asegúrate que el getter coincida con el nombre real
                c.getSector()
            });
        }
    }

    private void actualizarTablaEstudiantes(List<Student> students) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla
        model.setColumnIdentifiers(new String[]{"Nombre", "Cedula", "Codigo", "Email ", "Telefono"}); // Cambiar encabezados

        if (students == null || students.isEmpty()) {
            Messages.showMessageDialog("No existen empresas registradas.", "Información");
            return;
        }

        for (Student c : students) {
            model.addRow(new Object[]{
                c.getNombre(),
                c.getCedula(),
                c.getCodigo(),
                c.getEmail(),
                c.getTelefono(),});
        }
    }

    private void configurarEventosTabla() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarSeleccionProyecto();
            }
        });
    }

    private void manejarSeleccionProyecto() {
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada == -1) {
            Messages.showMessageDialog("Por favor, seleccione un proyecto.", "Advertencia");
            return;
        }

        // Obtener el proyecto directamente de la lista
        Project proyectoSeleccionado = proyectos.get(filaSeleccionada);

        if (proyectoSeleccionado != null) {
            this.dispose();
            abrirGUICoordinadorProject(proyectoSeleccionado);
        } else {
            Messages.showMessageDialog("Error: No se encontró el proyecto seleccionado.", "Error");
        }
    }

    private void llenarCBXperiodo() {

        LocalDateTime fechaActual = LocalDateTime.now();
        int anioActual = fechaActual.getYear();

        for (int anio = anioActual; anio > 2000; anio--) {
            cbxPeriodoAcademico.addItem(anio + "-2");
            cbxPeriodoAcademico.addItem(anio + "-1");
        }
    }

    private void abrirGUICoordinadorProject(Project p) {
        // Instanciar la GUI del coordinador y mostrarla
        GUIGestionSofwareCoordinationProject instance = new GUIGestionSofwareCoordinationProject(projectService, p,usuario);
        
        instance.pack();
        instance.setExtendedState(JFrame.NORMAL);
        instance.setLocationRelativeTo(null);
        instance.setVisible(true);
    }

}
