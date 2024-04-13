package vista.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.cls_cliente;
import modelo.cls_facturacion;
import modelo.cls_personal;
import modelo.cls_reserva;
import vista.Cliente;
import vista.ElegirPersonal;
import vista.ElegirServicio;
import vista.Facturacion;
import vista.swing.DateChooser;

public class Reserva extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JTextField txtCliente;
	public static JTextField txtIdCliente;
	private JTextField txtBuscarRes;
	public static JTextField txtPersonal;
	public static JTextField txtNombreServ;
	private JTextField txtFechaInicio;
	public static JTextField txtIdPersonal;
	public static JTextField txtIdServicio;
	private DateChooser dateChooserFechaRes;
	private DateChooser dateChooserFechaCita;
	private DateChooser dateChooserFechaInicio;
	private DateChooser dateChooserFechaFin;
	private JScrollPane scrollPane_1;
	private JTable tbReservas;
	private JTable tbDetalleReservas;
	private JPanel panelRegistro2;
	DefaultTableModel modelo;
	cls_reserva reserva = new cls_reserva();
	private JButton btnGuardarDetalle;
	private JButton btnEliminarServicio;
	private JButton btnTerminar;
	private JButton btnServicio;
	private JButton btnPersonal;
	private JButton btnCancelarReserva;
	private JButton btnGuardarReserva;
	private JButton btnClientes;
	private JTextField txtIdReservas;
	private JComboBox<String> cbHoraFechaIn;
	private JComboBox<String> cbMinutosFechaIn;
	private JComboBox<String> cbSegundosFechaIn;
	private JComboBox<String> cbEstado;
	private JTextField txtIdDetalleReservas;
	private JButton btnCancelar;
	cls_facturacion fact = new cls_facturacion();
	Facturacion f = new Facturacion();
	cls_cliente c = new cls_cliente();
	cls_personal p = new cls_personal();
	LocalDate fechaEmision = LocalDate.now();
	private JLabel lblFechaYHora_1_1;
	private JLabel lblDescripcionSer_1_1;
	private JLabel lblDuracion_1_1;
	private JLabel lblDuracion_1_2_1;
	private JButton btnAsignarHorario;
	private JTextField txtEstado;

	/**
	 * Create the panel.
	 */
	public Reserva() {

		setBackground(new Color(255, 255, 255));
		setBounds(0, 0, 990, 789);
		setLayout(null);

		JLabel lblSeccionDeReservas = new JLabel("Seccion de Reservas");
		lblSeccionDeReservas.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDeReservas.setBounds(10, 11, 190, 54);
		add(lblSeccionDeReservas);

		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(null);
		panelRegistro.setBackground(new Color(255, 255, 255));
		panelRegistro.setBounds(10, 73, 406, 262);
		add(panelRegistro);

		JLabel lblNuevaReserva = new JLabel("Nueva Reserva");
		lblNuevaReserva.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNuevaReserva.setBounds(24, 11, 139, 36);
		panelRegistro.add(lblNuevaReserva);

		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCliente.setBounds(24, 60, 75, 36);
		panelRegistro.add(lblCliente);

		txtCliente = new JTextField();
		txtCliente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCliente.setColumns(10);
		txtCliente.setBorder(null);
		txtCliente.setBackground(new Color(255, 255, 255));
		txtCliente.setBounds(24, 107, 197, 20);
		txtCliente.setEditable(false);
		panelRegistro.add(txtCliente);

		JLabel lblDuracion = new JLabel("Estado:");
		lblDuracion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDuracion.setBounds(24, 143, 75, 36);
		panelRegistro.add(lblDuracion);

		cbEstado = new JComboBox<String>();
		cbEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"Reservado"}));
		cbEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbEstado.setFocusable(false);
		cbEstado.setBounds(83, 151, 151, 22);
		panelRegistro.add(cbEstado);

		JLabel lblNombreDelProducto_1 = new JLabel("_________________________");
		lblNombreDelProducto_1.setForeground(Color.GRAY);
		lblNombreDelProducto_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDelProducto_1.setBounds(24, 110, 229, 25);
		panelRegistro.add(lblNombreDelProducto_1);

		btnGuardarReserva = new JButton("Guardar Reserva");
		btnGuardarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserva.setIdCliente(txtIdCliente.getText());
				if (reserva.getIdCliente().isEmpty() || txtCliente.equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
				} else {
					reserva.setEstado(cbEstado.getSelectedItem().toString());
					if (reserva.insertarReserva()) {
						JOptionPane.showMessageDialog(null, "Reserva Guardada");
						limpiarCampos();
						llenarTablaReservas("");
						llenarTablaDetalleReservas(0);
					} else {
						JOptionPane.showMessageDialog(null, "Error al Guardar");
					}
				}

			}

		});
		btnGuardarReserva.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGuardarReserva.setBorder(UIManager.getBorder("Button.border"));
		btnGuardarReserva.setBounds(37, 209, 151, 25);
		btnGuardarReserva.setEnabled(false);
		btnGuardarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro.add(btnGuardarReserva);

		txtIdCliente = new JTextField();
		txtIdCliente.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIdCliente.setColumns(10);
		txtIdCliente.setBorder(null);
		txtIdCliente.setBackground(new Color(241, 241, 241));
		txtIdCliente.setBounds(196, 3, 38, 0);
		panelRegistro.add(txtIdCliente);

		btnClientes = new JButton("Agregar Cliente");
		btnClientes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnClientes.setBounds(251, 110, 125, 23);
		btnClientes.setBorder(UIManager.getBorder("Button.border"));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro.add(btnClientes);
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c = new Cliente();
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				btnPersonal.setEnabled(true);
				btnServicio.setEnabled(true);

			}
		});

		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(255, 255, 255));
		panelTablaPr.setBounds(445, 73, 535, 371);
		add(panelTablaPr);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 90, 515, 270);
		panelTablaPr.add(scrollPane);

		tbReservas = new JTable();
		tbReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int fila = tbReservas.getSelectedRow();
					txtIdReservas.setText(tbReservas.getValueAt(fila, 0).toString());
					txtIdCliente.setText(tbReservas.getValueAt(fila, 1).toString());
					txtCliente.setText(tbReservas.getValueAt(fila, 2).toString() + " "
							+ tbReservas.getValueAt(fila, 3).toString());
					cbEstado.setSelectedItem(tbReservas.getValueAt(fila, 6).toString());
					txtEstado.setText(tbReservas.getValueAt(fila, 6).toString());
					activarBotones();
					btnGuardarReserva.setEnabled(false);
					btnClientes.setEnabled(false);
					btnPersonal.setEnabled(false);
					btnServicio.setEnabled(false);
					btnAsignarHorario.setEnabled(false);
					btnGuardarDetalle.setEnabled(false);
					
					
					llenarTablaDetalleReservas(Integer.parseInt(txtIdReservas.getText()));

				}
			}
		});
		scrollPane.setViewportView(tbReservas);

		JLabel lblVisualizacionDeLos = new JLabel("Visualizacion de las Reservas");
		lblVisualizacionDeLos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDeLos.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDeLos.setBounds(10, 11, 454, 36);
		panelTablaPr.add(lblVisualizacionDeLos);

		txtBuscarRes = new JTextField();
		txtBuscarRes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenarTablaReservas(txtBuscarRes.getText());
			}
		});
		txtBuscarRes.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBuscarRes.setColumns(10);
		txtBuscarRes.setBorder(null);
		txtBuscarRes.setBackground(new Color(255, 255, 255));
		txtBuscarRes.setBounds(10, 56, 128, 20);
		panelTablaPr.add(txtBuscarRes);

		JLabel lblMarcaPr_1 = new JLabel("________________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(10, 59, 140, 25);
		panelTablaPr.add(lblMarcaPr_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Reserva.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);

		panelRegistro2 = new JPanel();
		panelRegistro2.setLayout(null);
		panelRegistro2.setBackground(new Color(255, 255, 255));
		panelRegistro2.setBounds(10, 350, 406, 428);
		add(panelRegistro2);

		JLabel lblNuevaReserva_1 = new JLabel("Detalle de Reserva");
		lblNuevaReserva_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNuevaReserva_1.setBounds(24, 11, 173, 36);
		panelRegistro2.add(lblNuevaReserva_1);

		JLabel lblCliente_1 = new JLabel("Personal:");
		lblCliente_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCliente_1.setBounds(24, 60, 66, 36);
		panelRegistro2.add(lblCliente_1);

		txtPersonal = new JTextField();
		txtPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPersonal.setEditable(false);
		txtPersonal.setColumns(10);
		txtPersonal.setBorder(null);
		txtPersonal.setBackground(new Color(255, 255, 255));
		txtPersonal.setBounds(24, 96, 198, 20);
		panelRegistro2.add(txtPersonal);

		JLabel lblFechaYHora_2 = new JLabel("Servicio:");
		lblFechaYHora_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaYHora_2.setBounds(24, 153, 66, 36);
		panelRegistro2.add(lblFechaYHora_2);

		txtNombreServ = new JTextField();
		txtNombreServ.setEditable(false);
		txtNombreServ.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombreServ.setColumns(10);
		txtNombreServ.setBorder(null);
		txtNombreServ.setBackground(new Color(255, 255, 255));
		txtNombreServ.setBounds(24, 201, 173, 20);
		panelRegistro2.add(txtNombreServ);

		lblFechaYHora_1_1 = new JLabel("Fecha de Inicio:");
		lblFechaYHora_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaYHora_1_1.setBounds(24, 253, 109, 36);
		lblFechaYHora_1_1.setVisible(false);
		panelRegistro2.add(lblFechaYHora_1_1);

		JLabel lblNombreDelProducto_1_1 = new JLabel("_________________________");
		lblNombreDelProducto_1_1.setForeground(Color.GRAY);
		lblNombreDelProducto_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDelProducto_1_1.setBounds(24, 99, 212, 25);
		panelRegistro2.add(lblNombreDelProducto_1_1);

		JLabel lblDescripcionSer_2 = new JLabel("________________________");
		lblDescripcionSer_2.setForeground(Color.GRAY);
		lblDescripcionSer_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer_2.setBounds(24, 203, 198, 25);
		panelRegistro2.add(lblDescripcionSer_2);

		btnGuardarDetalle = new JButton("Agregar Servicio");
		btnGuardarDetalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdPersonal.getText().equals("") || txtIdServicio.getText().equals("")
						|| txtFechaInicio.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
					return;
				}
				reserva.setIdPersonal(txtIdPersonal.getText());
				reserva.setHoraInicio(txtFechaInicio.getText() + " " + cbHoraFechaIn.getSelectedItem().toString() + ":"
						+ cbMinutosFechaIn.getSelectedItem().toString() + ":"
						+ cbSegundosFechaIn.getSelectedItem().toString());
				if (reserva.verificarHoraParaReserva(reserva.getHoraInicio(), reserva.calcularHoraFin().toString(),
						reserva.getIdPersonal().toString())) {
					JOptionPane.showMessageDialog(null, "La hora y fecha estan ocupada");
				} else {
					if (reserva.insertarDetalleReserva()) {
						JOptionPane.showMessageDialog(null, "Servicio Agregado");
						llenarTablaDetalleReservas(0);
						llenarTablaReservas("");
						lblFechaYHora_1_1.setVisible(false);
						lblDescripcionSer_1_1.setVisible(false);
						txtFechaInicio.setVisible(false);
						lblDuracion_1_1.setVisible(false);
						lblDuracion_1_2_1.setVisible(false);
						cbHoraFechaIn.setVisible(false);
						cbMinutosFechaIn.setVisible(false);
						cbSegundosFechaIn.setVisible(false);
						btnGuardarDetalle.setVisible(false);
						btnAsignarHorario.setVisible(true);
						btnGuardarReserva.setEnabled(true);

					} else {
						JOptionPane.showMessageDialog(null, "Error al Agregar");
					}
				}
			}
		});
		btnGuardarDetalle.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGuardarDetalle.setBorder(UIManager.getBorder("Button.border"));
		btnGuardarDetalle.setBounds(4, 356, 129, 23);
		btnGuardarDetalle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardarDetalle.setVisible(false);
		panelRegistro2.add(btnGuardarDetalle);

		btnPersonal = new JButton("Agregar Personal");
		btnPersonal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirPersonal ep = new ElegirPersonal();
				ep.setVisible(true);
				ep.setLocationRelativeTo(null);

			}
		});
		btnPersonal.setBounds(246, 99, 139, 23);
		btnPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnPersonal.setBorder(UIManager.getBorder("Button.border"));
		btnPersonal.setEnabled(false);
		btnPersonal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro2.add(btnPersonal);

		txtFechaInicio = new JTextField();
		txtFechaInicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBorder(null);
		txtFechaInicio.setBackground(new Color(255, 255, 255));
		txtFechaInicio.setBounds(24, 302, 75, 20);
		txtFechaInicio.setVisible(false);
		panelRegistro2.add(txtFechaInicio);

		lblDescripcionSer_1_1 = new JLabel("___________");
		lblDescripcionSer_1_1.setForeground(Color.GRAY);
		lblDescripcionSer_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer_1_1.setBounds(24, 305, 98, 20);
		lblDescripcionSer_1_1.setVisible(false);
		panelRegistro2.add(lblDescripcionSer_1_1);

		btnServicio = new JButton("Seleccionar Servicio");
		btnServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ElegirServicio es = new ElegirServicio();
				es.setVisible(true);
				es.setLocationRelativeTo(null);
				reserva.setIdReservacion(reserva.obtenerUltimoIdReservacion() + 1);
				System.out.println(reserva.ObtenerHoraInicio());
				System.out.println(reserva.getIdReservacion());
				btnAsignarHorario.setEnabled(true);

			}
		});
		btnServicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnServicio.setBorder(UIManager.getBorder("Button.border"));
		btnServicio.setBounds(241, 201, 144, 21);
		btnServicio.setEnabled(false);
		btnServicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro2.add(btnServicio);

		txtIdPersonal = new JTextField();
		txtIdPersonal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIdPersonal.setEditable(false);
		txtIdPersonal.setColumns(10);
		txtIdPersonal.setBorder(null);
		txtIdPersonal.setBackground(new Color(241, 241, 241));
		txtIdPersonal.setBounds(22, 46, 0, 20);
		panelRegistro2.add(txtIdPersonal);

		txtIdServicio = new JTextField();
		txtIdServicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIdServicio.setColumns(10);
		txtIdServicio.setBorder(null);
		txtIdServicio.setBackground(new Color(241, 241, 241));
		txtIdServicio.setBounds(24, 96, 0, 20);
		panelRegistro2.add(txtIdServicio);

		cbHoraFechaIn = new JComboBox<String>();
		cbHoraFechaIn.setModel(
				new DefaultComboBoxModel<String>(new String[] {"07", "08", "09",
						"10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));
		cbHoraFechaIn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbHoraFechaIn.setFocusable(false);
		cbHoraFechaIn.setBounds(124, 302, 67, 22);
		cbHoraFechaIn.setVisible(false);
		panelRegistro2.add(cbHoraFechaIn);

		lblDuracion_1_1 = new JLabel(":");
		lblDuracion_1_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDuracion_1_1.setBounds(201, 294, 10, 36);
		lblDuracion_1_1.setVisible(false);
		panelRegistro2.add(lblDuracion_1_1);

		cbMinutosFechaIn = new JComboBox<String>();
		cbMinutosFechaIn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
		cbMinutosFechaIn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbMinutosFechaIn.setFocusable(false);
		cbMinutosFechaIn.setBounds(221, 301, 61, 22);
		cbMinutosFechaIn.setVisible(false);
		panelRegistro2.add(cbMinutosFechaIn);

		cbSegundosFechaIn = new JComboBox<String>();
		cbSegundosFechaIn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cbSegundosFechaIn.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbSegundosFechaIn.setFocusable(false);
		cbSegundosFechaIn.setBounds(312, 301, 61, 22);
		cbSegundosFechaIn.setVisible(false);
		panelRegistro2.add(cbSegundosFechaIn);

		lblDuracion_1_2_1 = new JLabel(":");
		lblDuracion_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDuracion_1_2_1.setBounds(292, 294, 10, 36);
		lblDuracion_1_2_1.setVisible(false);
		panelRegistro2.add(lblDuracion_1_2_1);

		JPanel panelTablaPr_1 = new JPanel();
		panelTablaPr_1.setLayout(null);
		panelTablaPr_1.setBackground(new Color(255, 255, 255));
		panelTablaPr_1.setBounds(445, 455, 535, 323);
		add(panelTablaPr_1);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 515, 218);
		panelTablaPr_1.add(scrollPane_1);

		tbDetalleReservas = new JTable();
		tbDetalleReservas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int fila = tbDetalleReservas.getSelectedRow();
					txtIdDetalleReservas.setText(tbDetalleReservas.getValueAt(fila, 0).toString());
					txtIdPersonal.setText(tbDetalleReservas.getValueAt(fila, 1).toString());
					txtPersonal.setText(tbDetalleReservas.getValueAt(fila, 2).toString() + " "
							+ tbDetalleReservas.getValueAt(fila, 3).toString());
					txtIdServicio.setText(tbDetalleReservas.getValueAt(fila, 4).toString());
					txtNombreServ.setText(tbDetalleReservas.getValueAt(fila, 5).toString());
					txtFechaInicio.setText(tbDetalleReservas.getValueAt(fila, 6).toString());
					btnEliminarServicio.setEnabled(true);
					btnGuardarDetalle.setVisible(false);
					btnAsignarHorario.setEnabled(false);
					btnPersonal.setEnabled(false);
					btnServicio.setEnabled(false);
					btnCancelar.setVisible(true);

				}
			}
		});
		scrollPane_1.setViewportView(tbDetalleReservas);

		JLabel lblVisualizacionDeLos_2 = new JLabel("Visualizacion de los Detalles");
		lblVisualizacionDeLos_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDeLos_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDeLos_2.setBounds(10, 11, 515, 36);
		panelTablaPr_1.add(lblVisualizacionDeLos_2);

		JButton btnGenerarFactura = new JButton("Generar Factura");
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdReservas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione una reserva");
				} else {

					if (txtEstado.getText().equals("Reservado y Fac")) {
						JOptionPane.showMessageDialog(null, "Esta reserva ya tiene una factura asociada");
					} else {
						reserva.setEstado("Reservado y Fac");
						if (reserva.editarEstadoReserva(Integer.parseInt(txtIdReservas.getText()))) {
							System.out.println(txtEstado.getText());
							// Para llenar los campos de la factura
							fact.setFechaEmision(fechaEmision.toString());
							fact.setIdCliente(txtIdCliente.getText());
							fact.setMetodoPago("Efectivo");
							fact.setIva(0.15);
							double subtotal = fact.calcularSubtotal(Integer.parseInt(txtIdReservas.getText()));
							double total = fact.calcularTotal(subtotal, fact.getIva());
							fact.setSubtotal(subtotal);
							fact.setTotal(total);
							f.btnPdf.setVisible(false);
							f.setVisible(true);
							f.setLocationRelativeTo(null);

							// Para llenar los campos de la factura
							f.lblFacturaID.setText(fact.generarIdFactura());
							p.buscarPersonal(cls_facturacion.getpersonalAtendido());
							f.lblPersonal.setText(
									p.getNombre() + " " + p.getPrimerApellido() + " " + p.getSegundoApellido());
							f.txtCedulaCl.setText(txtIdCliente.getText());
							c.buscarCliente(txtIdCliente.getText());
							f.txtNombresCl.setText(c.getNombre());
							f.txtPrimerAp.setText(c.getPrimerApellido());
							f.txtSegundoAp.setText(c.getSegundoApellido());
							f.txtDireccionCl.setText(c.getDireccion());
							f.txtTelCl.setText(c.getTelefono());
							f.txtCorreoCl.setText(c.getCorreo());
							f.txtFechaHora.setText(fact.getFechaEmision());
							f.txtSubTotal.setText(String.valueOf(fact.getSubtotal()));
							f.txtIva.setText(String.valueOf(fact.getIva()));
							
							f.txtTotal.setText(String.valueOf(fact.getTotal()));
							f.txtMetodoPago.setText(fact.getMetodoPago());

							if (fact.insertar()) {
								JOptionPane.showMessageDialog(null, "Registro Guardado Correctamente");
								f.llenarDetalleReservas(Integer.parseInt(txtIdReservas.getText()));
								llenarTablaReservas("");

							} else {
								JOptionPane.showMessageDialog(null, "Error al Guardar");
							}
						}
					}

				}
			}
		});
		btnGenerarFactura.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGenerarFactura.setBorder(UIManager.getBorder("Button.border"));
		btnGenerarFactura.setBounds(375, 279, 150, 25);
		panelTablaPr_1.add(btnGenerarFactura);

		dateChooserFechaRes = new vista.swing.DateChooser();
		dateChooserFechaRes.setBackground(new Color(255, 255, 255));
		dateChooserFechaRes.setForeground(new Color(244, 129, 129));
		dateChooserFechaRes.setDateFormat("yyyy-MM-dd");

		dateChooserFechaCita = new vista.swing.DateChooser();
		dateChooserFechaCita.setBackground(new Color(255, 255, 255));
		dateChooserFechaCita.setForeground(new Color(244, 129, 129));
		dateChooserFechaCita.setDateFormat("yyyy-MM-dd");

		dateChooserFechaInicio = new vista.swing.DateChooser();
		dateChooserFechaInicio.setBackground(new Color(255, 255, 255));
		dateChooserFechaInicio.setForeground(new Color(244, 129, 129));
		dateChooserFechaInicio.setDateFormat("yyyy-MM-dd");
		dateChooserFechaInicio.setTextRefernce(txtFechaInicio);

		btnEliminarServicio = new JButton("Eliminar Servicio");
		btnEliminarServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdDetalleReservas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione un servicio");
				} else {
					if (reserva.eliminarDetalleReserva(Integer.parseInt(txtIdDetalleReservas.getText()))) {
						JOptionPane.showMessageDialog(null, "Servicio Eliminado");
						if (txtIdReservas.getText().equals("")) {
							llenarTablaDetalleReservas(0);
						} else {
							llenarTablaDetalleReservas(Integer.parseInt(txtIdReservas.getText()));
						}

						btnEliminarServicio.setEnabled(false);
						btnAsignarHorario.setEnabled(true);
						btnPersonal.setEnabled(true);
						btnServicio.setEnabled(true);
						btnCancelar.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Error al eliminar");
					}
				}
			}
		});
		btnEliminarServicio.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEliminarServicio.setBorder(UIManager.getBorder("Button.border"));
		btnEliminarServicio.setBounds(149, 356, 133, 23);
		btnEliminarServicio.setEnabled(false);
		btnEliminarServicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro2.add(btnEliminarServicio);

		btnTerminar = new JButton("Terminar");
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				btnGuardarReserva.setEnabled(false);
				btnGuardarDetalle.setEnabled(false);
				btnTerminar.setEnabled(false);
				btnCancelarReserva.setEnabled(false);
				btnClientes.setEnabled(true);
				btnCancelar.setVisible(false);
				btnEliminarServicio.setEnabled(false);
				llenarTablaDetalleReservas(0);
			}
		});
		btnTerminar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnTerminar.setBorder(UIManager.getBorder("Button.border"));
		btnTerminar.setBounds(298, 356, 98, 23);
		btnTerminar.setEnabled(false);
		btnTerminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro2.add(btnTerminar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCamposDetalle();
				btnPersonal.setEnabled(true);
				btnServicio.setEnabled(true);
				btnGuardarDetalle.setVisible(false);
				btnAsignarHorario.setEnabled(true);
				btnEliminarServicio.setEnabled(false);
				btnCancelar.setVisible(false);
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCancelar.setBorder(UIManager.getBorder("Button.border"));
		btnCancelar.setBounds(143, 394, 119, 23);
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setVisible(false);
		panelRegistro2.add(btnCancelar);

		btnAsignarHorario = new JButton("Asignar Horario");
		btnAsignarHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdPersonal.getText().equals("") || txtIdServicio.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione un personal y un servicio");
					return;
				}
				reserva.setIdServicio(Integer.parseInt(txtIdServicio.getText()));
				reserva.setIdPersonal(txtIdPersonal.getText());
				lblFechaYHora_1_1.setVisible(true);
				txtFechaInicio.setVisible(true);
				cbHoraFechaIn.setVisible(true);
				cbMinutosFechaIn.setVisible(true);
				cbSegundosFechaIn.setVisible(true);
				lblDuracion_1_1.setVisible(true);
				lblDuracion_1_2_1.setVisible(true);
				lblDescripcionSer_1_1.setVisible(true);
				btnGuardarDetalle.setVisible(true);
				btnAsignarHorario.setVisible(false);
				btnGuardarDetalle.setEnabled(true);
			}
		});
		btnAsignarHorario.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnAsignarHorario.setBorder(UIManager.getBorder("Button.border"));
		btnAsignarHorario.setBounds(14, 357, 119, 21);
		btnAsignarHorario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro2.add(btnAsignarHorario);

		dateChooserFechaFin = new vista.swing.DateChooser();
		dateChooserFechaFin.setBackground(new Color(255, 255, 255));
		dateChooserFechaFin.setForeground(new Color(244, 129, 129));
		dateChooserFechaFin.setDateFormat("yyyy-MM-dd");

		btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdReservas.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor seleccione una reserva");
				} else {
					if (reserva.cancelarReserva(Integer.parseInt(txtIdReservas.getText()))) {
						JOptionPane.showMessageDialog(null, "Reserva Cancelada");
						llenarTablaReservas("");
					} else {
						JOptionPane.showMessageDialog(null, "Error al cancelar");
					}
				}
			}
		});
		btnCancelarReserva.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCancelarReserva.setBorder(UIManager.getBorder("Button.border"));
		btnCancelarReserva.setBounds(220, 209, 139, 25);
		btnCancelarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelarReserva.setEnabled(false);
		panelRegistro.add(btnCancelarReserva);

		txtIdReservas = new JTextField();
		txtIdReservas.setBounds(261, 149, 0, 19);
		panelRegistro.add(txtIdReservas);
		txtIdReservas.setColumns(10);

		txtIdDetalleReservas = new JTextField();
		txtIdDetalleReservas.setBounds(376, 209, 0, 19);
		panelRegistro.add(txtIdDetalleReservas);
		txtIdDetalleReservas.setColumns(10);

		txtEstado = new JTextField();
		txtEstado.setBounds(67, 135, 0, 19);
		panelRegistro.add(txtEstado);
		txtEstado.setColumns(10);

		llenarTablaReservas("");
		llenarTablaDetalleReservas(0);
	}

	public void llenarTablaReservas(String Busqueda) {
		String[] cabeceras = { "ID", "ID Cliente", "Nombre", "Apellido",
				"Registro de Reserva", "Fecha y Hora de la Cita", "Estado" };
		modelo = new DefaultTableModel(reserva.consultarReserva(Busqueda), cabeceras) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
		};
		tbReservas.setModel(modelo);
		int[] anchos = {15, 60, 80, 50, 60, 60, 90 }; // Definir los anchos deseados para cada columna
	    for (int i = 0; i < tbReservas.getColumnCount(); i++) {
	        TableColumn columna = tbReservas.getColumnModel().getColumn(i);
	        columna.setPreferredWidth(anchos[i]);
	    }
	}

	public void llenarTablaDetalleReservas(int idReserva) {
		String[] cabeceras = { "ID", "Personal", "Nombre", "Apellido", "ID",
				"Servicio", "Hora de inicio", "Hora de Finalización" };
		modelo = new DefaultTableModel(reserva.consultarDetalleReserva(idReserva), cabeceras) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
		};
		tbDetalleReservas.setModel(modelo);
		int[] anchos = {15, 60, 80, 50, 15, 90, 60, 60 }; // Definir los anchos deseados para cada columna
	    for (int i = 0; i < tbDetalleReservas.getColumnCount(); i++) {
	        TableColumn columna = tbDetalleReservas.getColumnModel().getColumn(i);
	        columna.setPreferredWidth(anchos[i]);
	    }
	}
	

	public void activarBotones() {
		btnPersonal.setEnabled(true);
		btnServicio.setEnabled(true);
		btnTerminar.setEnabled(true);
		btnCancelarReserva.setEnabled(true);
		btnAsignarHorario.setEnabled(true);

	}

	public void limpiarCampos() {
		txtIdCliente.setText("");
		txtCliente.setText("");
		cbEstado.setSelectedIndex(0);
		txtIdPersonal.setText("");
		txtPersonal.setText("");
		txtIdServicio.setText("");
		txtNombreServ.setText("");
		txtFechaInicio.setText("");
		cbHoraFechaIn.setSelectedIndex(0);
		cbMinutosFechaIn.setSelectedIndex(0);
		cbSegundosFechaIn.setSelectedIndex(0);
		txtIdReservas.setText("");
		txtIdDetalleReservas.setText("");
		txtEstado.setText("");
	}

	public void limpiarCamposDetalle() {
		txtIdPersonal.setText("");
		txtPersonal.setText("");
		txtIdServicio.setText("");
		txtNombreServ.setText("");
		txtFechaInicio.setText("");
		cbHoraFechaIn.setSelectedIndex(0);
		cbMinutosFechaIn.setSelectedIndex(0);
		cbSegundosFechaIn.setSelectedIndex(0);
		txtIdDetalleReservas.setText("");
	}
}