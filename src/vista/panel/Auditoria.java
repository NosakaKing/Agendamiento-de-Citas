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

import modelo.cls_auditoria;
import modelo.cls_excelreporte;
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

public class Auditoria extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txt_buscar;
	public DefaultTableModel modelo;
	private JTable tablaAud;
	private JComboBox<String> cbCategoria = new JComboBox<String>();
//	public static DefaultTableModel modelo;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private DateChooser dateChooserFechaInicio;
	private DateChooser dateChooserFechaFin;
	cls_auditoria aud = new cls_auditoria();

	/**
	 * Create the panel.
	 */
	public Auditoria() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblSeccionDeReportes = new JLabel("Seccion de Auditoria");
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

		tablaAud = new JTable();
		tablaAud.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					tablaAud.setDefaultEditor(Object.class, null);
				}
			}
		});
		scrollPane.setViewportView(tablaAud);

		JLabel lblVisualizacionDelCliente = new JLabel("Visualizacion de Auditoria");
		lblVisualizacionDelCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelCliente.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelCliente.setBounds(10, 11, 950, 36);
		panelTablaPr.add(lblVisualizacionDelCliente);

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
		lblNewLabel.setIcon(new ImageIcon(Auditoria.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);

		txtFechaInicio = new JTextField();
		txtFechaInicio.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarTabla();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarTabla();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				actualizarTabla();
			}
		});
		txtFechaInicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBorder(null);
		txtFechaInicio.setBackground(new Color(255, 255, 255));
		txtFechaInicio.setBounds(623, 51, 79, 20);
		panelTablaPr.add(txtFechaInicio);

		JLabel lblMFechaInicio = new JLabel("___________");
		lblMFechaInicio.setForeground(Color.GRAY);
		lblMFechaInicio.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMFechaInicio.setBounds(623, 53, 97, 25);
		panelTablaPr.add(lblMFechaInicio);

		txtFechaFin = new JTextField();
		txtFechaFin.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarTabla();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarTabla();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				actualizarTabla();
			}
		});

		txtFechaFin.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaFin.setColumns(10);
		txtFechaFin.setBorder(null);
		txtFechaFin.setBackground(new Color(255, 255, 255));
		txtFechaFin.setBounds(770, 51, 97, 20);
		panelTablaPr.add(txtFechaFin);

		dateChooserFechaInicio = new DateChooser();
		dateChooserFechaInicio.setDateFormat("yyyy-MM-dd");
		dateChooserFechaInicio.setTextRefernce(txtFechaInicio);

		dateChooserFechaFin = new DateChooser();
		dateChooserFechaFin.setDateFormat("yyyy-MM-dd");
		dateChooserFechaFin.setTextRefernce(txtFechaFin);

		JLabel lblMFechaInicio_1 = new JLabel("___________");
		lblMFechaInicio_1.setForeground(Color.GRAY);
		lblMFechaInicio_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMFechaInicio_1.setBounds(770, 53, 97, 25);
		panelTablaPr.add(lblMFechaInicio_1);

		JLabel lblAl = new JLabel("al");
		lblAl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAl.setBounds(730, 52, 19, 20);
		panelTablaPr.add(lblAl);

		cbCategoria = new JComboBox<String>();
		cbCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String elegirAuditoria = (String) cbCategoria.getSelectedItem();
				switch (elegirAuditoria) {
				case "Auditoria de Ingreso":
					llenarTablaIngreso(txtFechaInicio.getText(), txtFechaFin.getText());
					break;
				case "Auditoria de actividades":
					llenarTablaAuditoria(txtFechaInicio.getText(), txtFechaFin.getText());
					break;
				case "Auditoria Personal":
					llenarTablaAuditoriaTabla("personal");
					break;
				case "Auditoria Cliente":
					llenarTablaAuditoriaTabla("cliente");
					break;
				case "Auditoria Servicio":
					llenarTablaAuditoriaTabla("servicio");
					break;
				case "Auditoria Reservacion":
					llenarTablaAuditoriaTabla("reservacion");
					break;
				case "Auditoria Factura":
					llenarTablaAuditoriaTabla("factura");
					break;
				}
			}
		});
		cbCategoria.setBounds(191, 53, 184, 22);
		panelTablaPr.add(cbCategoria);
		cbCategoria.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Auditoria de Ingreso", "Auditoria de actividades", "Auditoria Personal",
						"Auditoria Cliente", "Auditoria Servicio", "Auditoria Reservacion", "Auditoria Factura" }));
		cbCategoria.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbCategoria.setFocusable(false);

		JLabel lblFiltradoPorRango = new JLabel("Filtrado por rango de fechas:");
		lblFiltradoPorRango.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFiltradoPorRango.setBounds(420, 50, 175, 29);
		panelTablaPr.add(lblFiltradoPorRango);
		
		JButton btnExcel = new JButton("Generar Excel");
		btnExcel.setIcon(new ImageIcon(Auditoria.class.getResource("/images/11Reportes.png")));
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elegirAuditoria = (String) cbCategoria.getSelectedItem();
				switch (elegirAuditoria) {
				case "Auditoria de Ingreso":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria de Ingreso");
					break;
				case "Auditoria de actividades":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria de actividades");
					break;
				case "Auditoria Personal":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria Personal");
					break;
				case "Auditoria Cliente":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria Cliente");
					break;
				case "Auditoria Servicio":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria Servicio");
					break;
				case "Auditoria Reservacion":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria Reservacion");
					break;
				case "Auditoria Factura":
					cls_excelreporte.reporte(tablaAud, "Reporte de Auditoria Factura");
					break;
				}
			}
		});
		btnExcel.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnExcel.setBorder(UIManager.getBorder("Button.border"));
		btnExcel.setBackground(new Color(255, 255, 255));
		btnExcel.setBounds(10, 627, 140, 29);
		panelTablaPr.add(btnExcel);

		llenarTablaIngreso("", "");

	}

	private void actualizarTabla() {
		Object selectedItem = cbCategoria.getSelectedItem();
		if (selectedItem != null) {
			String elegirAuditoria = selectedItem.toString();
			switch (elegirAuditoria) {
			case "Auditoria de Ingreso":
				llenarTablaIngreso(txtFechaInicio.getText(), txtFechaFin.getText());
				break;
			case "Auditoria de actividades":
				llenarTablaAuditoria(txtFechaInicio.getText(), txtFechaFin.getText());
			    break;
			}
		}
	}

	private void actualizarTablaBuscar() {
		Object selectedItem = cbCategoria.getSelectedItem();
		if (selectedItem != null) {
			String elegirAuditoria = selectedItem.toString();
			switch (elegirAuditoria) {
			case "Auditoria de Ingreso":
				llenarTablaIngresoBuscar(txt_buscar.getText());
				break;
			case "Auditoria de actividades":
				llenarTablaAuditoriaBuscar(txt_buscar.getText());
				break;
			}
		}
	}

	public void llenarTablaIngreso(String fechaInicio, String fechaFin) {
		if (validarFechas(fechaInicio, fechaFin)) {
			String[] cabeceras = { "ID Ingreso", "ID Personal", "Nombre", "Primer Apellido", "Segundo Apellido",
					"Fecha de Ingreso" };
			modelo = new DefaultTableModel(aud.consultarFechaIngreso(fechaInicio, fechaFin), cabeceras) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false; // No permitir la edición de celdas
				}
			};
			tablaAud.setModel(modelo);
		} else {
			JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor que la fecha de fin.");
		}
	}

	public void llenarTablaAuditoria(String fechaInicio, String fechaFin) {
		if (validarFechas(fechaInicio, fechaFin)) {
			String[] cabeceras = { "ID Auditoria", "ID Personal", "Nombre", "Primer Apellido", "Segundo Apellido",
					"Tabla", "Operacion", "Fecha de Ingreso" };
			modelo = new DefaultTableModel(aud.consultarAuditoriaFecha(fechaInicio, fechaFin), cabeceras) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false; // No permitir la edición de celdas
				}
			};
			tablaAud.setModel(modelo);
		} else {
			JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor que la fecha de fin.");
		}
	}

	public void llenarTablaIngresoBuscar(String idPersonal) {
		String[] cabeceras = { "ID Ingreso", "ID Personal", "Nombre", "Primer Apellido", "Segundo Apellido",
				"Fecha de Ingreso" };
		modelo = new DefaultTableModel(aud.consultarFechaIngresoBuscar(idPersonal), cabeceras) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
		};
		tablaAud.setModel(modelo);
	}

	public void llenarTablaAuditoriaBuscar(String idPersonal) {
		String[] cabeceras = { "ID Auditoria", "ID Personal", "Nombre", "Primer Apellido", "Segundo Apellido", "Tabla",
				"Operacion", "Fecha de Ingreso" };
		modelo = new DefaultTableModel(aud.consultarAuditoriaBuscar(idPersonal), cabeceras) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
		};
		tablaAud.setModel(modelo);
	}

	public void llenarTablaAuditoriaTabla(String tabla) {
		String[] cabeceras = { "ID Auditoria", "ID Personal", "Nombre", "Primer Apellido", "Segundo Apellido", "Tabla",
				"Operacion", "Fecha de Ingreso" };
		modelo = new DefaultTableModel(aud.consultarAuditoriaTabla(tabla), cabeceras) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
		};
		tablaAud.setModel(modelo);
	}

	private boolean validarFechas(String fechaInicio, String fechaFin) {
		if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
			return true;
		}

		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio);
		LocalDate fechaFinDate = LocalDate.parse(fechaFin);

		return !fechaInicioDate.isAfter(fechaFinDate);
	}
}
