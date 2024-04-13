package vista.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import modelo.cls_cliente;
import modelo.cls_excelreporte;
import modelo.cls_facturacion;
import modelo.cls_personal;
import modelo.cls_reserva;
import modelo.cls_servicios;
import vista.swing.DateChooser;

import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reporte extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txt_buscar;
    public DefaultTableModel modelo;
    private JTable tbReportes;
    private JTextField txtInicio;
    private JTextField txtFin;
    cls_personal p = new cls_personal();
    cls_cliente c = new cls_cliente();
    cls_servicios servicio = new cls_servicios();
    cls_reserva reserva = new cls_reserva();
    private JComboBox<String> cbReportes;
    cls_facturacion facturacion = new cls_facturacion();
    private DateChooser dateChooserFechaInicio;
    private DateChooser dateChooserFechaFin;

    /**
     * Create the panel.
     */
    public Reporte() {
        setBackground(new Color(255, 255, 255));
        setLayout(null);

        JLabel lblSeccionDeReportes = new JLabel("Seccion de Reportes");
        lblSeccionDeReportes.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblSeccionDeReportes.setBounds(10, 11, 190, 54);
        add(lblSeccionDeReportes);

        JPanel panelTablaPr = new JPanel();
        panelTablaPr.setLayout(null);
        panelTablaPr.setBackground(new Color(255, 255, 255));
        panelTablaPr.setBounds(10, 73, 970, 705);
        add(panelTablaPr);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 90, 950, 525);
        panelTablaPr.add(scrollPane);

        tbReportes = new JTable();
        tbReportes.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    tbReportes.setDefaultEditor(Object.class, null);
                }
            }
        });
        scrollPane.setViewportView(tbReportes);

        JLabel lblVisualizacionDelCliente = new JLabel("Visualizacion de Reportes");
        lblVisualizacionDelCliente.setHorizontalAlignment(SwingConstants.CENTER);
        lblVisualizacionDelCliente.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblVisualizacionDelCliente.setBounds(10, 11, 950, 36);
        panelTablaPr.add(lblVisualizacionDelCliente);

        txt_buscar = new JTextField();
        txt_buscar = new JTextField();
        txt_buscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarTablaBuscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarTablaBuscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarTablaBuscar();
            }
        });
        txt_buscar.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txt_buscar.setColumns(10);
        txt_buscar.setBorder(null);
        txt_buscar.setBackground(new Color(255, 255, 255));
        txt_buscar.setBounds(10, 56, 128, 20);
        panelTablaPr.add(txt_buscar);

        JLabel lblMarcaPr_1 = new JLabel("______________________");
        lblMarcaPr_1.setForeground(Color.GRAY);
        lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblMarcaPr_1.setBounds(10, 59, 140, 25);
        panelTablaPr.add(lblMarcaPr_1);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Reporte.class.getResource("/images/7Buscar.png")));
        lblNewLabel.setBounds(148, 58, 46, 21);
        panelTablaPr.add(lblNewLabel);

        txtInicio = new JTextField();
        txtInicio.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarTablaFechas();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarTablaFechas();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarTablaFechas();
            }
        });
        txtInicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtInicio.setColumns(10);
        txtInicio.setBorder(null);
        txtInicio.setBackground(new Color(255, 255, 255));
        txtInicio.setBounds(623, 51, 79, 20);

        panelTablaPr.add(txtInicio);

        JLabel lblMFechaInicio = new JLabel("___________");
        lblMFechaInicio.setForeground(Color.GRAY);
        lblMFechaInicio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblMFechaInicio.setBounds(623, 53, 97, 25);
        panelTablaPr.add(lblMFechaInicio);

        txtFin = new JTextField();
        txtFin.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtFin.setColumns(10);
        txtFin.setBorder(null);
        txtFin.setBackground(new Color(255, 255, 255));
        txtFin.setBounds(770, 51, 97, 20);
        panelTablaPr.add(txtFin);

        JLabel lblMFechaInicio_1 = new JLabel("___________");
        lblMFechaInicio_1.setForeground(Color.GRAY);
        lblMFechaInicio_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblMFechaInicio_1.setBounds(770, 53, 97, 25);
        panelTablaPr.add(lblMFechaInicio_1);

        JLabel lblAl = new JLabel("al");
        lblAl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblAl.setBounds(730, 52, 19, 20);
        panelTablaPr.add(lblAl);

        cbReportes = new JComboBox<String>();
        cbReportes.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String seleccion = cbReportes.getSelectedItem().toString();
                switch (seleccion) {
                    case "Reporte de Personal":
                        llenarTabla("");
                        break;
                    case "Reporte de Clientes":
                        llenarTablaCientes("");
                        break;
                    case "Reporte de Servcios":
                        llenarTablaServicio("");
                        break;
                    case "Reporte de Reservas":
                        llenarTablaReservas("");
                        break;
                    case "Reporte de Factura":
                        llenarTablaFactura("", "");
                        break;
                }
            }
        });
        cbReportes.setModel(new DefaultComboBoxModel<>(new String[]{"Reporte de Clientes", "Reporte de Personal",
            "Reporte de Servcios", "Reporte de Reservas", "Reporte de Factura"}));
        cbReportes.setFont(new Font("SansSerif", Font.PLAIN, 12));
        cbReportes.setFocusable(false);
        cbReportes.setBounds(191, 53, 184, 22);
        panelTablaPr.add(cbReportes);

        JLabel lblFiltradoPorRango = new JLabel("Filtrado por rango de fechas:");
        lblFiltradoPorRango.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblFiltradoPorRango.setBounds(420, 50, 175, 29);
        panelTablaPr.add(lblFiltradoPorRango);

        JButton btnExcel = new JButton("Generar Excel");
        btnExcel.setIcon(new ImageIcon(Reporte.class.getResource("/images/11Reportes.png")));
        btnExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String seleccion = cbReportes.getSelectedItem().toString();
                switch (seleccion) {
                    case "Reporte de Personal":
                        cls_excelreporte.reporte(tbReportes, "Reporte de Personal");
                        break;
                    case "Reporte de Clientes":
                        cls_excelreporte.reporte(tbReportes, "Reporte de Clientes");
                        break;
                    case "Reporte de Servcios":
                        cls_excelreporte.reporte(tbReportes, "Reporte de Servcios");
                        break;
                    case "Reporte de Reservas":
                        cls_excelreporte.reporte(tbReportes, "Reporte de Reservas");
                        break;
                    case "Reporte de Factura":
                        cls_excelreporte.reporte(tbReportes, "Reporte de Factura");
                        break;
                }

            }
        });
        btnExcel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnExcel.setBorder(UIManager.getBorder("Button.border"));
        btnExcel.setBounds(10, 626, 140, 29);
        panelTablaPr.add(btnExcel);
        dateChooserFechaInicio = new DateChooser();
        dateChooserFechaInicio.setDateFormat("yyyy-MM-dd");
        dateChooserFechaInicio.setTextRefernce(txtInicio);

        dateChooserFechaFin = new DateChooser();
        dateChooserFechaFin.setDateFormat("yyyy-MM-dd");
        dateChooserFechaFin.setTextRefernce(txtFin);
        llenarTablaCientes("");
    }

    public void llenarTabla(String Buscar) {
        String[] cabeceras = {"No", "Tipo identificación", "Nombre", "Primer Apellido", "Segundo Apellido",
            "Fecha de nacimiento", "Genero", "Telefono", "Direccion", "Correo", "Clave", "Cargo"};
        modelo = new DefaultTableModel(p.consultarPersonal(Buscar), cabeceras);
        tbReportes.setModel(modelo);
    }

    public void llenarTablaPersonalFecha(String fechaInicio, String fechaFin) {
        String[] cabeceras = {"No", "Tipo identificación", "Nombre", "Primer Apellido", "Segundo Apellido",
            "Fecha de nacimiento", "Genero", "Telefono", "Direccion", "Correo", "Clave", "Cargo"};
        modelo = new DefaultTableModel(p.consultarPersonalFecha(fechaInicio, fechaFin), cabeceras);
        tbReportes.setModel(modelo);
    }

    public void llenarTablaCientes(String Buscar) {
        String[] cabeceras = {"No", "Tipo de identificación", "Nombre", "Primer Apellido", "Segundo Apellido",
            "Fecha de nacimiento", "Genero", "Direccion", "Correo", "Telefono"};
        modelo = new DefaultTableModel(c.consultarCliente(Buscar), cabeceras) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    public void llenarTablaClientesFecha(String fechaInicio, String FechaFin) {
        String[] cabeceras = {"No", "Tipo de identificación", "Nombre", "Primer Apellido", "Segundo Apellido",
            "Fecha de nacimiento", "Genero", "Direccion", "Correo", "Telefono"};
        modelo = new DefaultTableModel(c.consultarClienteFechaN(fechaInicio, FechaFin), cabeceras) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    public void llenarTablaServicio(String Busqueda) {
        String[] cabeceras = {"ID Servicio", "Categoria", "Nombre del Servicio", "Precio", "Descripcion", "Duracion"};
        modelo = new DefaultTableModel(servicio.consultar(Busqueda), cabeceras) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    public void llenarTablaReservas(String Busqueda) {
        String[] cabeceras = {"ID Reservación", "ID Cliente", "Nombre Cliente", "Apellido Cliente",
            "Registro de Reserva", "Fecha y Hora de la Cita", "Estado"};
        modelo = new DefaultTableModel(reserva.consultarReserva(Busqueda), cabeceras) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    public void llenarTablaReservas(String fechaInicio, String fechaFin) {
        String[] cabeceras = {"ID Reservación", "ID Cliente", "Nombre Cliente", "Apellido Cliente",
            "Registro de Reserva", "Fecha y Hora de la Cita", "Estado"};
        modelo = new DefaultTableModel(reserva.consultarReservaFechaReserva(fechaInicio, fechaFin), cabeceras) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    public void llenarTablaFactura(String fechaInicio, String fechaFin) {
        if (validarFechas(fechaInicio, fechaFin)) {
            String[] cabeceras = {"ID Factura", "ID Cliente", "ID_PERSONAL", "RUC", "Autorizacion SRI",
                "Fecha Emision", "Metodo Pago", "Subtotal", "IVA", "Total"};
            modelo = new DefaultTableModel(facturacion.consultarFacturaFechas(fechaInicio, fechaFin), cabeceras) {
                /**
                 *
                 */
                private static final long serialVersionUID = 1L;

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // No permitir la edición de celdas
                }
            };
            tbReportes.setModel(modelo);
        } else {
            JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor que la fecha de fin.");
        }
    }

    public void llenarTablaFacturaBuscar(String buscar) {
        String[] cabeceras = {"ID Factura", "ID Cliente", "ID_PERSONAL", "RUC", "Autorizacion SRI", "Fecha Emision",
            "Metodo Pago", "Subtotal", "IVA", "Total"};
        modelo = new DefaultTableModel(facturacion.consultarFactura(buscar), cabeceras) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir la edición de celdas
            }
        };
        tbReportes.setModel(modelo);
    }

    private boolean validarFechas(String fechaInicio, String fechaFin) {
        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            return true;
        }

        LocalDate fechaInicioDate = LocalDate.parse(fechaInicio);
        LocalDate fechaFinDate = LocalDate.parse(fechaFin);

        return !fechaInicioDate.isAfter(fechaFinDate);
    }

    private void actualizarTablaBuscar() {
        Object selectedItem = cbReportes.getSelectedItem();
        if (selectedItem != null) {
            String elegirAuditoria = selectedItem.toString();
            switch (elegirAuditoria) {
                case "Reporte de Clientes":
                    llenarTablaCientes(txt_buscar.getText());
                    break;
                case "Reporte de Personal":
                    llenarTabla(txt_buscar.getText());
                    break;
                case "Reporte de Servcios":
                    llenarTablaServicio(txt_buscar.getText());
                    break;
                case "Reporte de Reservas":
                    llenarTablaReservas(txt_buscar.getText());
                    break;
                case "Reporte de Factura":
                    llenarTablaFacturaBuscar(txt_buscar.getText());
                    break;
            }
        }
    }

    private void actualizarTablaFechas() {
        Object selectedItem = cbReportes.getSelectedItem();
        if (selectedItem != null) {
            String elegirAuditoria = selectedItem.toString();
            switch (elegirAuditoria) {
                case "Reporte de Clientes":
                    llenarTablaClientesFecha(txtInicio.getText(), txtFin.getText());
                    break;
                case "Reporte de Personal":
                    llenarTablaPersonalFecha(txtInicio.getText(), txtFin.getText());
                    break;
                case "Reporte de Reservas":
                    llenarTablaReservas(txtInicio.getText(), txtFin.getText());
                    break;
                case "Reporte de Factura":
                    llenarTablaFactura(txtInicio.getText(), txtFin.getText());
                    break;

            }
        }
    }
}
