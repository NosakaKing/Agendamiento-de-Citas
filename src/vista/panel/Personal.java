package vista.panel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import modelo.cls_personal;
import modelo.cls_validaciones;
import vista.swing.DateChooser;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Personal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField textField_4;
	private JTextField txt_buscar;
	private JTextField txtCorreo;
	private JTextField txtPrAp;
	private JTextField txtSnAp;
	cls_personal p = new cls_personal();
	cls_validaciones validaciones = new cls_validaciones();
	private JTextField txt_cedu;
	private JTextField txtFechaNac;
	private DateChooser dateChooserFechaN;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JComboBox<String> cbGenero;
	private JPasswordField txtClave;
	private JTable tablaPersonal;
	public static DefaultTableModel modelo;
	private JComboBox<String> cbCargo;
	private JButton btnEditar;
	private JButton btnNuevo;
	private JButton btnGuardar;
	int bandera = 0;
	private JScrollPane scrollPane;
	private JLabel lblClaveP;
	private JLabel lblClave;
	private JComboBox<String> cbTipo;

	/**
	 * Create the panel.
	 */
	public Personal() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenarTabla("");
			}
		});
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(null);
		panelRegistro.setBackground(new Color(255, 255, 255));
		panelRegistro.setBounds(10, 73, 406, 684);
		add(panelRegistro);

		JLabel lblNuevoPersonal = new JLabel("Nuevo Personal");
		lblNuevoPersonal.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNuevoPersonal.setBounds(24, 11, 187, 36);
		panelRegistro.add(lblNuevoPersonal);

		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombres.setBounds(24, 134, 67, 36);
		panelRegistro.add(lblNombres);

		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		txtNombres.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombres.setColumns(10);
		txtNombres.setBorder(null);
		txtNombres.setBackground(new Color(255, 255, 255));
		txtNombres.setBounds(98, 145, 268, 16);
		validaciones.validarSoloLetras(txtNombres);
		panelRegistro.add(txtNombres);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrimerApellido.setBounds(24, 181, 139, 28);
		panelRegistro.add(lblPrimerApellido);

		lblClave = new JLabel("Clave:");
		lblClave.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblClave.setBounds(24, 502, 56, 28);
		panelRegistro.add(lblClave);

		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCargo.setBounds(24, 356, 56, 36);
		panelRegistro.add(lblCargo);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(24, 455, 133, 28);
		panelRegistro.add(lblCorreoElectronico);

		cbCargo = new JComboBox<String>();
		cbCargo.setEnabled(false);
		cbCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbCargo.getSelectedItem().toString().equals("Estilista")
						|| cbCargo.getSelectedItem().toString().equals("Cosmetologo")) {
					txtClave.setVisible(false);
					lblClaveP.setVisible(false);
					lblClave.setVisible(false);
				} else {
					txtClave.setVisible(true);
					lblClaveP.setVisible(true);
					lblClave.setVisible(true);
				}
			}
		});
		cbCargo.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Gerente", "Cajero", "Estilista", "Cosmetologo" }));
		cbCargo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbCargo.setFocusable(false);
		cbCargo.setBounds(101, 366, 151, 20);
		panelRegistro.add(cbCargo);

		JLabel lblNombreDelProducto_1 = new JLabel("_________________________________");
		lblNombreDelProducto_1.setForeground(Color.GRAY);
		lblNombreDelProducto_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDelProducto_1.setBounds(98, 142, 268, 28);
		panelRegistro.add(lblNombreDelProducto_1);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (bandera == 1) {
					p.setIdPersonal(txt_cedu.getText());
					p.setTipo(cbTipo.getSelectedItem().toString());
					p.setNombre(txtNombres.getText());
					p.setPrimerApellido(txtPrAp.getText());
					p.setSegundoApellido(txtSnAp.getText());
					p.setFechaNacimiento(txtFechaNac.getText());
					p.setGenero(cbGenero.getSelectedItem().toString());
					p.setDireccion(txtDireccion.getText());
					p.setTelefono(txtTelefono.getText());
					p.setCorreo(txtCorreo.getText());
					p.setClave(new String(txtClave.getPassword()));
					p.setCargo(cbCargo.getSelectedItem().toString());
					if (p.existeCedulaDuplicada(txt_cedu.getText()) || p.existeCorreoDuplicado(txtCorreo.getText())
							|| p.existeNumeroDuplicado(txtTelefono.getText())) {
						JOptionPane.showMessageDialog(null, "Cedula o correo ya registrados, ingrese nuevos datos");
						return;

					}
					if (p.insertar()) {
						JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
						llenarTabla("");
						limpiarCampos();
						blcajas();
						bbotones();
						btnNuevo.setEnabled(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "No se registro correctamente");
					}
				} else if (bandera == 2) {
					p.setIdPersonal(txt_cedu.getText());
					p.setTipo(cbTipo.getSelectedItem().toString());
					p.setNombre(txtNombres.getText());
					p.setPrimerApellido(txtPrAp.getText());
					p.setSegundoApellido(txtSnAp.getText());
					p.setFechaNacimiento(txtFechaNac.getText());
					p.setGenero(cbGenero.getSelectedItem().toString());
					p.setDireccion(txtDireccion.getText());
					p.setTelefono(txtTelefono.getText());
					p.setCorreo(txtCorreo.getText());
					p.setClave(new String(txtClave.getPassword()));
					p.setCargo(cbCargo.getSelectedItem().toString());

					if (p.modificar()) {
						JOptionPane.showMessageDialog(null, "Registro editado correctamente");
						llenarTabla("");
						lcajas();
						blcajas();
						bbotones();
						btnNuevo.setEnabled(true);
						limpiarCampos();
					} else {
						JOptionPane.showMessageDialog(null, "Error al editar");
					}
				}

			}
		});
		btnGuardar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGuardar.setEnabled(false);
		btnGuardar.setBorder(UIManager.getBorder("Button.border"));
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setBackground(new Color(255, 255, 255));
		btnGuardar.setBounds(140, 631, 89, 23);
		panelRegistro.add(btnGuardar);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acajas();
				bbotones();
				btnGuardar.setEnabled(true);
				bandera = 1;
			}
		});
		btnNuevo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnNuevo.setBorder(UIManager.getBorder("Button.border"));
		btnNuevo.setBackground(new Color(255, 255, 255));
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevo.setBounds(10, 631, 89, 23);
		panelRegistro.add(btnNuevo);

		btnEditar = new JButton("Editar");
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				txt_cedu.setEditable(false);
				cbTipo.setEnabled(false);
				txtNombres.setEditable(true);
				txtPrAp.setEditable(true);
				txtSnAp.setEditable(true);
				cbGenero.setEnabled(false);
				txtFechaNac.setEnabled(false);
				txtTelefono.setEditable(true);
				txtDireccion.setEditable(true);
				txtCorreo.setEditable(true);
				txtClave.setEditable(true);
				cbCargo.setEnabled(true);
				bbotones();
				btnGuardar.setEnabled(true);
				bandera = 2;
			}
		});
		btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEditar.setEnabled(false);
		btnEditar.setBorder(UIManager.getBorder("Button.border"));
		btnEditar.setBackground(new Color(255, 255, 255));
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBounds(282, 631, 89, 23);
		panelRegistro.add(btnEditar);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(new Color(241, 241, 241));
		textField_4.setBounds(196, 60, 0, 20);
		panelRegistro.add(textField_4);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSegundoApellido.setBounds(227, 181, 139, 28);
		panelRegistro.add(lblSegundoApellido);

		txtCorreo = new JTextField();
		txtCorreo.setEditable(false);
		txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(null);
		txtCorreo.setBackground(new Color(255, 255, 255));
		txtCorreo.setBounds(167, 462, 205, 16);
		panelRegistro.add(txtCorreo);

		JLabel lblCorreo = new JLabel("__________________________");
		lblCorreo.setForeground(Color.GRAY);
		lblCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorreo.setBounds(167, 463, 218, 25);
		panelRegistro.add(lblCorreo);

		lblClaveP = new JLabel("_____________");
		lblClaveP.setForeground(Color.GRAY);
		lblClaveP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblClaveP.setBounds(101, 506, 110, 25);
		panelRegistro.add(lblClaveP);

		txtPrAp = new JTextField();
		txtPrAp.setEditable(false);
		txtPrAp.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPrAp.setColumns(10);
		txtPrAp.setBorder(null);
		txtPrAp.setBackground(new Color(255, 255, 255));
		txtPrAp.setBounds(24, 220, 103, 16);
		validaciones.validarSoloLetras(txtPrAp);
		panelRegistro.add(txtPrAp);

		JLabel lblPr = new JLabel("_________________");
		lblPr.setForeground(Color.GRAY);
		lblPr.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPr.setBounds(23, 219, 151, 25);
		panelRegistro.add(lblPr);

		txtSnAp = new JTextField();
		txtSnAp.setEditable(false);
		txtSnAp.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSnAp.setColumns(10);
		txtSnAp.setBorder(null);
		txtSnAp.setBackground(new Color(255, 255, 255));
		txtSnAp.setBounds(227, 218, 139, 20);
		validaciones.validarSoloLetras(txtSnAp);
		panelRegistro.add(txtSnAp);

		JLabel lblSnAp = new JLabel("_________________");
		lblSnAp.setForeground(Color.GRAY);
		lblSnAp.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSnAp.setBounds(227, 220, 139, 25);
		panelRegistro.add(lblSnAp);

		JLabel lblCedula = new JLabel("No.");
		lblCedula.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedula.setBounds(24, 92, 37, 28);
		panelRegistro.add(lblCedula);

		txt_cedu = new JTextField();
		txt_cedu.setEditable(false);
		txt_cedu.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txt_cedu.setColumns(10);
		txt_cedu.setBorder(null);
		txt_cedu.setBackground(new Color(255, 255, 255));
		txt_cedu.setBounds(71, 97, 103, 16);
		validaciones.validarSoloNumerosEnteros(txt_cedu);
		panelRegistro.add(txt_cedu);

		JLabel lblCedulaP = new JLabel("____________");
		lblCedulaP.setForeground(Color.GRAY);
		lblCedulaP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedulaP.setBounds(71, 92, 103, 30);
		panelRegistro.add(lblCedulaP);

		JLabel lblFechaN = new JLabel("Fecha de Nacimiento:");
		lblFechaN.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaN.setBounds(24, 262, 150, 28);
		panelRegistro.add(lblFechaN);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblGenero.setBounds(24, 312, 64, 28);
		panelRegistro.add(lblGenero);

		cbGenero = new JComboBox<String>();
		cbGenero.setEnabled(false);
		cbGenero.setModel(new DefaultComboBoxModel<String>(new String[] { "Femenino", "Masculino" }));
		cbGenero.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbGenero.setFocusable(false);
		cbGenero.setBounds(101, 312, 151, 20);
		panelRegistro.add(cbGenero);

		txtFechaNac = new JTextField();
		txtFechaNac.setEditable(false);
		txtFechaNac.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaNac.setColumns(10);
		txtFechaNac.setBorder(null);
		txtFechaNac.setBackground(new Color(255, 255, 255));
		txtFechaNac.setBounds(177, 264, 133, 20);
		panelRegistro.add(txtFechaNac);

		JLabel lblFechaNa = new JLabel("________________________");
		lblFechaNa.setForeground(Color.GRAY);
		lblFechaNa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaNa.setBounds(173, 266, 198, 25);
		panelRegistro.add(lblFechaNa);

		JLabel lblSeccionDePersonal = new JLabel("Seccion de Personal");
		lblSeccionDePersonal.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDePersonal.setBounds(10, 11, 254, 54);
		add(lblSeccionDePersonal);

		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(255, 255, 255));
		panelTablaPr.setBounds(449, 73, 531, 653);
		add(panelTablaPr);

		scrollPane = new JScrollPane();
		scrollPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				// llenarTabla();
			}
		});
		scrollPane.setBounds(10, 113, 511, 495);
		panelTablaPr.add(scrollPane);

		tablaPersonal = new JTable();
		tablaPersonal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Verificar si hay una fila seleccionada
				int selectedRow = tablaPersonal.getSelectedRow();
				if (selectedRow >= 0) {
					txt_cedu.setText(tablaPersonal.getValueAt(selectedRow, 0).toString());
					String dato[] = new String[12];
					dato = p.consultarDato(txt_cedu.getText());
					cbTipo.setSelectedItem(dato[1]);
					txtNombres.setText(dato[2]);
					txtPrAp.setText(dato[3]);
					txtSnAp.setText(dato[4]);
					txtFechaNac.setText(dato[5]);
					cbGenero.setSelectedItem(dato[6]);
					txtTelefono.setText(dato[7]);
					txtDireccion.setText(dato[8]);
					txtCorreo.setText(dato[9]);
					txtClave.setText(dato[10]);
					cbCargo.setSelectedItem(dato[11]);
					blcajas();
					bbotones();
					btnEditar.setEnabled(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					tablaPersonal.setDefaultEditor(Object.class, null);
				}
			}
		});
		tablaPersonal.setFillsViewportHeight(true);
		tablaPersonal.setBorder(null);
		tablaPersonal.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenarTabla("");
			}
		});

		scrollPane.setViewportView(tablaPersonal);
		JTableHeader tableHeader = tablaPersonal.getTableHeader();
		TableCellRenderer renderer = tableHeader.getDefaultRenderer();
		JLabel label = (JLabel) renderer;
		label.setHorizontalAlignment(JLabel.LEFT);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tablaPersonal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JLabel lblVisualizacionDelPersonal = new JLabel("Visualizacion del Personal");
		lblVisualizacionDelPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelPersonal.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblVisualizacionDelPersonal.setBounds(10, 11, 454, 36);
		panelTablaPr.add(lblVisualizacionDelPersonal);

		txt_buscar = new JTextField();
		txt_buscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenarTabla(txt_buscar.getText());
			}
		});
		txt_buscar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txt_buscar.setColumns(10);
		txt_buscar.setBorder(null);
		txt_buscar.setBackground(new Color(255, 255, 255));
		txt_buscar.setBounds(10, 56, 128, 20);
		panelTablaPr.add(txt_buscar);

		JLabel lblMarcaPr_1 = new JLabel("________________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(10, 59, 140, 25);
		panelTablaPr.add(lblMarcaPr_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Personal.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);

		JButton btnEliminar = new JButton("Eliminar Personal");
		btnEliminar.setBounds(383, 619, 138, 23);
		panelTablaPr.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int botones = JOptionPane.YES_NO_OPTION;
				int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este personal",
						"Warning", botones);
				if (confirmacion == JOptionPane.YES_OPTION)
					;
				{
					p.setIdPersonal(txt_cedu.getText());
					if (p.eliminar() > 0) {
						JOptionPane.showMessageDialog(null, "Registro Eliminado");
						llenarTabla("");
						lcajas();
						blcajas();
						bbotones();
						btnNuevo.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Error al eliminar registros");
						bbotones();
						btnNuevo.setEnabled(true);
					}
				}

			}
		});
		btnEliminar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEliminar.setBorder(UIManager.getBorder("Button.border"));
		btnEliminar.setBackground(new Color(255, 255, 255));
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		dateChooserFechaN = new vista.swing.DateChooser();
		dateChooserFechaN.setBounds(24, 355, 262, 206);
		dateChooserFechaN.setBackground(new Color(255, 255, 255));
		dateChooserFechaN.setForeground(new Color(244, 129, 129));
		dateChooserFechaN.setDateFormat("yyyy-MM-dd");
		dateChooserFechaN.setTextRefernce(txtFechaNac);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccion.setBounds(24, 554, 75, 29);
		panelRegistro.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDireccion.setColumns(10);
		txtDireccion.setBorder(null);
		txtDireccion.setBackground(new Color(255, 255, 255));
		txtDireccion.setBounds(101, 555, 284, 20);
		panelRegistro.add(txtDireccion);

		JLabel lblDireccionP = new JLabel("___________________________________");
		lblDireccionP.setForeground(Color.GRAY);
		lblDireccionP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccionP.setBounds(101, 554, 295, 30);
		panelRegistro.add(lblDireccionP);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTelefono.setBounds(24, 408, 64, 28);
		panelRegistro.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(null);
		txtTelefono.setBackground(new Color(255, 255, 255));
		txtTelefono.setBounds(101, 408, 103, 20);
		validaciones.validarSoloNumerosEnteros(txtTelefono);
		panelRegistro.add(txtTelefono);

		JLabel lblTel = new JLabel("_____________");
		lblTel.setForeground(Color.GRAY);
		lblTel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTel.setBounds(101, 412, 120, 25);
		panelRegistro.add(lblTel);

		txtClave = new JPasswordField();
		txtClave.setEditable(false);
		txtClave.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtClave.setBorder(null);
		txtClave.setBackground(new Color(255, 255, 255));
		txtClave.setBounds(101, 502, 103, 20);
		panelRegistro.add(txtClave);

		JLabel lblNewLabel_1 = new JLabel("Tipo de identificación:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(24, 58, 150, 20);
		panelRegistro.add(lblNewLabel_1);

		cbTipo = new JComboBox<String>();
		cbTipo.setEnabled(false);
		cbTipo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbTipo.setModel(new DefaultComboBoxModel<String>(new String[] { "Cédula", "Pasaporte" }));
		cbTipo.setBounds(206, 58, 140, 22);
		panelRegistro.add(cbTipo);

		llenarTabla("");
	}

	public void llenarTabla(String Buscar) {
		String[] cabeceras = { "No", "Tipo identificación", "Nombre", "Primer Apellido", "Segundo Apellido",
				"Fecha de nacimiento", "Genero", "Telefono", "Direccion", "Correo", "Clave", "Cargo" };
		modelo = new DefaultTableModel(p.consultarPersonal(Buscar), cabeceras);
		tablaPersonal.setModel(modelo);
	}

	public void lcajas() {

		txt_cedu.setText("");
		txtNombres.setText("");
		txtPrAp.setText("");
		txtSnAp.setText("");
		cbGenero.setToolTipText("");
		txtFechaNac.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
		txtClave.setText("");
		cbCargo.setToolTipText("");

	}


	public void acajas() {

		cbTipo.setEnabled(true);
		txt_cedu.setEditable(true);
		txtNombres.setEditable(true);
		txtPrAp.setEditable(true);
		txtSnAp.setEditable(true);
		cbGenero.setEnabled(true);
		txtFechaNac.setEnabled(true);
		txtTelefono.setEditable(true);
		txtDireccion.setEditable(true);
		txtCorreo.setEditable(true);
		txtClave.setEditable(true);
		cbCargo.setEnabled(true);

	}
	
	public void blcajas() {
		cbTipo.setEnabled(false);
		txt_cedu.setEditable(false);
		txtNombres.setEditable(false);
		txtPrAp.setEditable(false);
		txtSnAp.setEditable(false);
		cbGenero.setEnabled(false);
		txtFechaNac.setEnabled(false);
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
		txtCorreo.setEditable(false);
		txtClave.setEditable(false);
		cbCargo.setEnabled(false);
	}

	public void bbotones() {
		btnGuardar.setEnabled(false);
		btnNuevo.setEnabled(false);
		btnEditar.setEnabled(false);

	}
	

	public void limpiarCampos() {
		txt_cedu.setText("");
		txtNombres.setText("");
		txtPrAp.setText("");
		txtSnAp.setText("");
		cbGenero.setToolTipText("");
		txtFechaNac.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
		txtClave.setText("");
		cbCargo.setToolTipText("");

	}
}
