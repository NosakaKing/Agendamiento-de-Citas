package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import modelo.cls_cliente;
import modelo.cls_validaciones;
import vista.panel.Reserva;
import vista.swing.DateChooser;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Cliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_buscar;
	private JTextField txtNombres;
	private JTextField textField_2;
	private JTextField txtCorreo;
	private JTextField txtPApellido;
	private JTextField txtSApellido;
	private JTextField txtCedula;
	cls_cliente c = new cls_cliente();
	cls_validaciones validaciones = new cls_validaciones();
	private JTextField txt_tlf;
	private JTextField txtFechaNaCl;
	private JTextField txtDireccionCl;
	private DateChooser dateChooserFechaNacCl;
	private JComboBox<String> cbGeneroCl;
	public static DefaultTableModel modelo;
	private JTable tablaCliente;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JScrollPane scrollPane;
	int bandera=0;
	private JComboBox<String> cbTipo;


	/**
	 * Create the frame.
	 */
	public Cliente() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 608);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(241, 241, 241));
		panelTablaPr.setBounds(375, 54, 534, 543);
		contentPane.add(panelTablaPr);
		
		scrollPane = new JScrollPane();
		scrollPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenarTabla("");
				;

			}
		});
		scrollPane.setBounds(10, 112, 514, 367);
		panelTablaPr.add(scrollPane);
		
		tablaCliente = new JTable();
		tablaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCedula.setText(tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0).toString());
				String dato[]= new String [10];
				dato= c.consultarDato(txtCedula.getText());
				cbTipo.setSelectedItem(dato[1]);
				txtNombres.setText(dato[2]);
				txtPApellido.setText(dato[3]);
				txtSApellido.setText(dato[4]);
				txtFechaNaCl.setText(dato[5]);
				cbGeneroCl.setSelectedItem(dato[6]);
				txtDireccionCl.setText(dato[7]);
				txtCorreo.setText(dato[8]);
				txt_tlf.setText(dato[9]);
				bcajas();
				bbotones();
				btnEditar.setEnabled(true);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
		            int fila = tablaCliente.getSelectedRow();
		            String id;
		            String nombre;
		            String apellido;
		            id = tablaCliente.getValueAt(fila, 0).toString();
		            nombre = tablaCliente.getValueAt(fila, 2).toString();
		            apellido = tablaCliente.getValueAt(fila, 3).toString();
	
		            Reserva.txtCliente.setText(apellido + " " + nombre);
		            Reserva.txtIdCliente.setText(id);
		            
		            dispose();
		          }
			}
		});
		scrollPane.setViewportView(tablaCliente);
		// Ajustar el ancho de las columnas del encabezado
		JTableHeader tableHeader = tablaCliente.getTableHeader();
		TableCellRenderer renderer = tableHeader.getDefaultRenderer();
		JLabel label = (JLabel) renderer;
		label.setHorizontalAlignment(JLabel.LEFT);

		// Permitir que las columnas adicionales se vean con la barra de desplazamiento horizontal
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Ajustar el ancho de cada columna al método llenarTabla()
		tablaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JLabel lblVisualizacionDelCliente = new JLabel("Visualizacion del  Cliente");
		lblVisualizacionDelCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelCliente.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelCliente.setBounds(10, 11, 514, 36);
		panelTablaPr.add(lblVisualizacionDelCliente);
		
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
		txt_buscar.setBackground(new Color(241, 241, 241));
		txt_buscar.setBounds(10, 56, 128, 20);
		panelTablaPr.add(txt_buscar);
		
		JLabel lblMarcaPr_1 = new JLabel("________________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(10, 59, 140, 25);
		panelTablaPr.add(lblMarcaPr_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Cliente.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);
		
		JPanel panelRegistro = new JPanel();
		panelRegistro.setLayout(null);
		panelRegistro.setBackground(new Color(241, 241, 241));
		panelRegistro.setBounds(10, 54, 355, 540);
		contentPane.add(panelRegistro);
		
		JLabel lblNuevoCliente = new JLabel("Nuevo Cliente");
		lblNuevoCliente.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNuevoCliente.setBounds(24, 11, 139, 36);
		panelRegistro.add(lblNuevoCliente);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombres.setBounds(24, 146, 68, 36);
		panelRegistro.add(lblNombres);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombres.setColumns(10);
		txtNombres.setBorder(null);
		txtNombres.setBackground(new Color(241, 241, 241));
		txtNombres.setBounds(102, 155, 284, 16);
		validaciones.validarSoloLetras(txtNombres);
		panelRegistro.add(txtNombres);
		
		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrimerApellido.setBounds(24, 189, 139, 36);
		panelRegistro.add(lblPrimerApellido);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTelefono.setBounds(24, 406, 89, 29);
		panelRegistro.add(lblTelefono);
		
		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(24, 267, 139, 36);
		panelRegistro.add(lblCorreoElectronico);
		
		JLabel lblNombreCl = new JLabel("____________________________");
		lblNombreCl.setForeground(Color.GRAY);
		lblNombreCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreCl.setBounds(99, 154, 274, 25);
		panelRegistro.add(lblNombreCl);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bandera == 1) {
				    c.setCedula(txtCedula.getText());
				    c.setTipo(cbTipo.getSelectedItem().toString());
				    c.setNombre(txtNombres.getText());
				    c.setPrimerApellido(txtPApellido.getText());
				    c.setSegundoApellido(txtSApellido.getText());
				    c.setFechaNacimiento(txtFechaNaCl.getText());
				    c.setGenero(cbGeneroCl.getSelectedItem().toString());
				    c.setDireccion(txtDireccionCl.getText());
				    c.setCorreo(txtCorreo.getText());
				    c.setTelefono(txt_tlf.getText());
				    if (c.insertar()) {
				        JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
				        llenarTabla("");
				        limpiarCampos();
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al guardar");
				    }
				} else if (bandera == 2) {
				    c.setCedula(txtCedula.getText());
				    c.setTipo(cbTipo.getSelectedItem().toString());
				    c.setNombre(txtNombres.getText());
				    c.setPrimerApellido(txtPApellido.getText());
				    c.setSegundoApellido(txtSApellido.getText());
				    c.setFechaNacimiento(txtFechaNaCl.getText());
				    c.setGenero(cbGeneroCl.getSelectedItem().toString());
				    c.setDireccion(txtDireccionCl.getText());
				    c.setCorreo(txtCorreo.getText());
				    c.setTelefono(txt_tlf.getText());
				    
				    bcajas();				    
				    if (c.modificar()) {
				        JOptionPane.showMessageDialog(null, "Registro editado correctamente");
				        llenarTabla("");
				        
				        btnGuardar.setEnabled(false);
				        btnEditar.setEnabled(false);
				        btnNuevo.setEnabled(true);
				        limpiarCampos();
				    } else {
				        JOptionPane.showMessageDialog(null, "Error al modificar el registro");
				    }
				}
						 }
							
						
		});
		btnGuardar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGuardar.setBorder(UIManager.getBorder("Button.border"));
		btnGuardar.setBounds(135, 506, 89, 23);
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro.add(btnGuardar);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acajas();
				bbotones();
				btnGuardar.setEnabled(true);
				bandera=1;
			}
		});
		btnNuevo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnNuevo.setBorder(UIManager.getBorder("Button.border"));
		btnNuevo.setBackground(new Color(241, 241, 241));
		btnNuevo.setBounds(10, 506, 89, 23);
		btnNuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		panelRegistro.add(btnNuevo);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCedula.setEnabled(false);
				cbTipo.setEnabled(false);
				txtNombres.setEnabled(true);
				txtPApellido.setEnabled(true);
				txtSApellido.setEnabled(true);
				txtFechaNaCl.setEnabled(false);
				cbGeneroCl.setEnabled(false);
				txtDireccionCl.setEnabled(true);
				txtCorreo.setEnabled(true);
				txt_tlf.setEnabled(true);
				bbotones();
				btnGuardar.setEnabled(true);
				bandera=2;
			}
		});
		btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEditar.setEnabled(false);
		btnEditar.setBorder(UIManager.getBorder("Button.border"));
		btnEditar.setBounds(254, 506, 89, 23);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro.add(btnEditar);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(241, 241, 241));
		textField_2.setBounds(196, 60, 0, 20);
		panelRegistro.add(textField_2);
		
		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSegundoApellido.setBounds(173, 182, 139, 36);
		panelRegistro.add(lblSegundoApellido);
		
		txtCorreo = new JTextField();
		txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCorreo.setColumns(10);
		txtCorreo.setBorder(null);
		txtCorreo.setBackground(new Color(241, 241, 241));
		txtCorreo.setBounds(173, 276, 120, 20);
		panelRegistro.add(txtCorreo);
		
		JLabel lblDescripcionSer_1 = new JLabel("___________________");
		lblDescripcionSer_1.setForeground(Color.GRAY);
		lblDescripcionSer_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer_1.setBounds(173, 279, 173, 25);
		panelRegistro.add(lblDescripcionSer_1);
		
		txt_tlf = new JTextField();
		txt_tlf.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txt_tlf.setColumns(10);
		txt_tlf.setBorder(null);
		txt_tlf.setBackground(new Color(241, 241, 241));
		txt_tlf.setBounds(99, 409, 103, 16);
		validaciones.validarSoloNumerosEnteros(txt_tlf);
		panelRegistro.add(txt_tlf);
		
		JLabel lblTel = new JLabel("_____________");
		lblTel.setForeground(Color.GRAY);
		lblTel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTel.setBounds(99, 410, 120, 25);
		panelRegistro.add(lblTel);
		
		txtPApellido = new JTextField();
		txtPApellido.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPApellido.setColumns(10);
		txtPApellido.setBorder(null);
		txtPApellido.setBackground(new Color(241, 241, 241));
		txtPApellido.setBounds(24, 236, 120, 16);
		validaciones.validarSoloLetras(txtPApellido);
		panelRegistro.add(txtPApellido);
		
		JLabel lblPrCl = new JLabel("_______________");
		lblPrCl.setForeground(Color.GRAY);
		lblPrCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrCl.setBounds(24, 236, 129, 25);
		panelRegistro.add(lblPrCl);
		
		txtSApellido = new JTextField();
		txtSApellido.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSApellido.setColumns(10);
		txtSApellido.setBorder(null);
		txtSApellido.setBackground(new Color(241, 241, 241));
		txtSApellido.setBounds(173, 234, 162, 18);
		validaciones.validarSoloLetras(txtSApellido);
		panelRegistro.add(txtSApellido);
		
		JLabel lblSnApCl = new JLabel("_______________");
		lblSnApCl.setForeground(Color.GRAY);
		lblSnApCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSnApCl.setBounds(173, 236, 173, 25);
		panelRegistro.add(lblSnApCl);
		
		JLabel lblCedula = new JLabel("No.");
		lblCedula.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedula.setBounds(24, 106, 28, 29);
		panelRegistro.add(lblCedula);
		
		txtCedula = new JTextField();
		txtCedula.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCedula.setColumns(10);
		txtCedula.setBorder(null);
		txtCedula.setBackground(new Color(241, 241, 241));
		txtCedula.setBounds(62, 112, 94, 16);
		validaciones.validarSoloNumerosEnteros(txtCedula);
		panelRegistro.add(txtCedula);
		
		JLabel lblCedulaCl = new JLabel("____________");
		lblCedulaCl.setForeground(Color.GRAY);
		lblCedulaCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedulaCl.setBounds(60, 106, 103, 37);
		panelRegistro.add(lblCedulaCl);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblGenero.setBounds(24, 359, 59, 36);
		panelRegistro.add(lblGenero);
		
		cbGeneroCl = new JComboBox<String>();
		cbGeneroCl.setModel(new DefaultComboBoxModel<String>(new String[] {"Femenino", "Masculino"}));
		cbGeneroCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbGeneroCl.setFocusable(false);
		cbGeneroCl.setBounds(94, 367, 103, 22);
		panelRegistro.add(cbGeneroCl);
		
		JLabel lblFechaN = new JLabel("Fecha de Nacimiento:");
		lblFechaN.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaN.setBounds(24, 312, 139, 36);
		panelRegistro.add(lblFechaN);
		
		txtFechaNaCl = new JTextField();
		txtFechaNaCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaNaCl.setColumns(10);
		txtFechaNaCl.setBorder(null);
		txtFechaNaCl.setBackground(new Color(241, 241, 241));
		txtFechaNaCl.setBounds(173, 321, 103, 16);
		panelRegistro.add(txtFechaNaCl);
		
		JLabel lblFechaNa = new JLabel("___________________");
		lblFechaNa.setForeground(Color.GRAY);
		lblFechaNa.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaNa.setBounds(173, 320, 173, 25);
		panelRegistro.add(lblFechaNa);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccion.setBounds(24, 446, 68, 36);
		panelRegistro.add(lblDireccion);
		
		txtDireccionCl = new JTextField();
		txtDireccionCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDireccionCl.setColumns(10);
		txtDireccionCl.setBorder(null);
		txtDireccionCl.setBackground(new Color(241, 241, 241));
		txtDireccionCl.setBounds(99, 455, 284, 16);
		panelRegistro.add(txtDireccionCl);
		
		JLabel lblDireccionP = new JLabel("____________________________");
		lblDireccionP.setForeground(Color.GRAY);
		lblDireccionP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccionP.setBounds(101, 454, 245, 25);
		panelRegistro.add(lblDireccionP);
		
		JLabel lblSeccionDeClientes = new JLabel("Seccion de clientes");
		lblSeccionDeClientes.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDeClientes.setBounds(10, 11, 279, 44);
		contentPane.add(lblSeccionDeClientes);
		
		dateChooserFechaNacCl = new vista.swing.DateChooser();
		dateChooserFechaNacCl.setBounds(24, 355, 262, 206);
		dateChooserFechaNacCl.setBackground(new Color(255,255,255));
		dateChooserFechaNacCl.setForeground(new Color(244,129,129));
		dateChooserFechaNacCl.setDateFormat("yyyy-MM-dd");
		dateChooserFechaNacCl.setTextRefernce(txtFechaNaCl);
		
		JLabel lblTipoIdentificacin = new JLabel("Tipo identificación:");
		lblTipoIdentificacin.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTipoIdentificacin.setBounds(24, 66, 129, 29);
		panelRegistro.add(lblTipoIdentificacin);
		
		cbTipo = new JComboBox<String>();
		cbTipo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbTipo.setModel(new DefaultComboBoxModel<String>(new String[] {"Cédula", "Pasaporte"}));
		cbTipo.setFocusable(false);
		cbTipo.setBounds(173, 66, 103, 20);
		panelRegistro.add(cbTipo);
		
		JLabel lblX = new JLabel("X");
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lblX.setForeground(Color.black);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.LIGHT_GRAY);
			}
		});
		lblX.setForeground(Color.LIGHT_GRAY);
		lblX.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblX.setBounds(899, 11, 22, 24);
		contentPane.add(lblX);

llenarTabla("");
}

	public void llenarTabla(String Buscar) {
	    String[] cabeceras = {"No","Tipo de identificación", "Nombre", "Primer Apellido", "Segundo Apellido", "Fecha de nacimiento", "Genero", "Direccion", "Correo", "Telefono"};
	    modelo = new DefaultTableModel(c.consultarCliente(Buscar), cabeceras) {
			private static final long serialVersionUID = 1L;
			@Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No permitir la edición de celdas
	        }
	    };
	    tablaCliente.setModel(modelo);
	}

public void lcajas()
{

	txtCedula.setText("");
	txtNombres.setText("");
	txtPApellido.setText("");
	txtSApellido.setText("");
	txtFechaNaCl.setToolTipText("");
	txtDireccionCl.setText("");
	txtCorreo.setText("");
	txt_tlf.setText("");
	txtCorreo.setText("");
	
}
public void bcajas()
{
	cbTipo.setEnabled(false);
	txtCedula.setEnabled(false);
	txtNombres.setEnabled(false);
	txtPApellido.setEnabled(false);
	txtSApellido.setEnabled(false);
	txtFechaNaCl.setEnabled(false);
	cbGeneroCl.setEnabled(false);
	txtDireccionCl.setEnabled(false);
	txtCorreo.setEnabled(false);
	txt_tlf.setEnabled(false);
}
public void acajas()
{
	cbTipo.setEnabled(true);
	txtCedula.setEnabled(true);
	txtNombres.setEnabled(true);
	txtPApellido.setEnabled(true);
	txtSApellido.setEnabled(true);
	txtFechaNaCl.setEnabled(true);
	cbGeneroCl.setEnabled(true);
	txtDireccionCl.setEnabled(true);
	txtCorreo.setEnabled(true);
	txt_tlf.setEnabled(true);

}
public void bbotones()
{
	btnGuardar.setEnabled(false);
	btnNuevo.setEnabled(false);
	btnEditar.setEnabled(false);

}

public void limpiarCampos() {
	
	txtCedula.setText("");
	txtNombres.setText("");
	txtPApellido.setText("");
	txtSApellido.setText("");
	txtFechaNaCl.setToolTipText("");
	txtDireccionCl.setText("");
	txtCorreo.setText("");
	txt_tlf.setText("");
	txtCorreo.setText("");

}

}

