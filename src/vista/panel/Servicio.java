package vista.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.cls_categoria;
import modelo.cls_servicios;
import modelo.cls_validaciones;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.time.LocalTime;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class Servicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreSer;
	private JTextField txtDescripcion;
	private JTextField txtPrecioSer;
	private JTextField textField_4;
	private JTextField txtBuscarSer;
	private JTable tbServicio;
	DefaultTableModel modelo;
	cls_servicios servicio = new cls_servicios();
	cls_validaciones validar = new cls_validaciones();
	cls_categoria categoria = new cls_categoria();
	private JTextField txtIdServicio;
	private JPanel panelRegistro;
	private JButton btnEditar;
	private JComboBox<String> cbHoraDuracion;
	private JComboBox<String> cbMinutosDuracion;
	private JComboBox<String> cbSegundosDuracion;

	/**
	 * Create the panel.
	 */
	public Servicio() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblSeccionDeServicio = new JLabel("Seccion de Servicio");
		lblSeccionDeServicio.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDeServicio.setBounds(10, 11, 190, 54);
		add(lblSeccionDeServicio);

		panelRegistro = new JPanel();
		panelRegistro.setLayout(null);
		panelRegistro.setBackground(new Color(255, 255, 255));
		panelRegistro.setBounds(10, 73, 406, 618);
		add(panelRegistro);

		JLabel lblNuevoServicio = new JLabel("Nuevo Servicio");
		lblNuevoServicio.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNuevoServicio.setBounds(24, 11, 139, 36);
		panelRegistro.add(lblNuevoServicio);

		JLabel lblNombreDelServicio = new JLabel("Nombre del Servicio:");
		lblNombreDelServicio.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDelServicio.setBounds(24, 60, 150, 36);
		panelRegistro.add(lblNombreDelServicio);

		txtNombreSer = new JTextField();
		txtNombreSer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombreSer.setColumns(10);
		txtNombreSer.setBorder(null);
		txtNombreSer.setBackground(new Color(255, 255, 255));
		txtNombreSer.setBounds(24, 105, 196, 20);
		panelRegistro.add(txtNombreSer);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcion.setBounds(24, 140, 139, 36);
		panelRegistro.add(lblDescripcion);

		txtDescripcion = new JTextField();
		txtDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDescripcion.setColumns(10);
		txtDescripcion.setBorder(null);
		txtDescripcion.setBackground(new Color(255, 255, 255));
		txtDescripcion.setBounds(24, 187, 347, 20);
		panelRegistro.add(txtDescripcion);

		txtPrecioSer = new JTextField();
		txtPrecioSer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPrecioSer.setColumns(10);
		txtPrecioSer.setBorder(null);
		txtPrecioSer.setBackground(new Color(255, 255, 255));
		txtPrecioSer.setBounds(276, 101, 103, 20);
		validar.validarSoloNumerosDecimales(txtPrecioSer);
		panelRegistro.add(txtPrecioSer);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecio.setBounds(276, 55, 56, 36);
		panelRegistro.add(lblPrecio);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCategoria.setBounds(24, 233, 139, 36);
		panelRegistro.add(lblCategoria);

		JLabel lblDuracion = new JLabel("Duracion:");
		lblDuracion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDuracion.setBounds(24, 324, 173, 36);
		panelRegistro.add(lblDuracion);

		JComboBox<String> cbCategoria = new JComboBox<String>();
		cbCategoria.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbCategoria.setFocusable(false);
		cbCategoria.setBounds(24, 280, 196, 22);
		categoria.cargarCategorias(cbCategoria);
		panelRegistro.add(cbCategoria);

		JLabel lblNombreDelProducto_1 = new JLabel("_________________________");
		lblNombreDelProducto_1.setForeground(Color.GRAY);
		lblNombreDelProducto_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreDelProducto_1.setBounds(24, 107, 205, 25);
		panelRegistro.add(lblNombreDelProducto_1);

		JLabel lblDescripcionSer = new JLabel("_________________________________________________________");
		lblDescripcionSer.setForeground(Color.GRAY);
		lblDescripcionSer.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer.setBounds(24, 190, 372, 22);
		panelRegistro.add(lblDescripcionSer);

		JLabel lblPrecioSr = new JLabel("_____________");
		lblPrecioSr.setForeground(Color.GRAY);
		lblPrecioSr.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecioSr.setBounds(276, 105, 120, 20);
		panelRegistro.add(lblPrecioSr);

		cbHoraDuracion = new JComboBox<String>();
		cbHoraDuracion.setModel(new DefaultComboBoxModel<>(new String[] {"00", "01", "02", "03", "04", "05"}));
		cbHoraDuracion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbHoraDuracion.setFocusable(false);
		cbHoraDuracion.setBounds(24, 368, 65, 22);
		panelRegistro.add(cbHoraDuracion);
		
		JLabel lblDuracion_1_1 = new JLabel(":");
		lblDuracion_1_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDuracion_1_1.setBounds(194, 361, 10, 36);
		panelRegistro.add(lblDuracion_1_1);
		
		JLabel lblDuracion_1_2_1 = new JLabel(":");
		lblDuracion_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblDuracion_1_2_1.setBounds(99, 361, 10, 36);
		panelRegistro.add(lblDuracion_1_2_1);
		
		cbMinutosDuracion = new JComboBox<String>();
		cbMinutosDuracion.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cbMinutosDuracion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbMinutosDuracion.setFocusable(false);
		cbMinutosDuracion.setBounds(119, 368, 65, 22);
		panelRegistro.add(cbMinutosDuracion);
		
		cbSegundosDuracion = new JComboBox<String>();
		cbSegundosDuracion.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		cbSegundosDuracion.setFont(new Font("SansSerif", Font.PLAIN, 12));
		cbSegundosDuracion.setFocusable(false);
		cbSegundosDuracion.setBounds(214, 368, 65, 22);
		panelRegistro.add(cbSegundosDuracion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtNombreSer.getText().equals("") || txtDescripcion.getText().equals("")
						|| txtPrecioSer.getText().equals("") ) {
					JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
				} else {
					servicio.setNombreServicio(txtNombreSer.getText());
					servicio.setDescripcion(txtDescripcion.getText());
					servicio.setPrecio(Float.parseFloat(txtPrecioSer.getText()));
					String duracion = cbHoraDuracion.getSelectedItem().toString() + ":" + cbMinutosDuracion.getSelectedItem().toString() + ":" + cbSegundosDuracion.getSelectedItem().toString();
					servicio.setDuracion(duracion);
					servicio.setIdCategoria(categoria.obtenerIdCategoria(cbCategoria.getSelectedItem().toString()));
					if (servicio.verificarServicio(txtNombreSer.getText())) {
						JOptionPane.showMessageDialog(null, "El servicio ya existe");
					} else {
						if(servicio.insertar()) {
							JOptionPane.showMessageDialog(null, "Servicio agregado correctamente");
							llenarTabla("");
							limpiarCampos();
						} else {
							JOptionPane.showMessageDialog(null, "Error al agregar el servicio");
						}
					}

				}
			}
		});
		btnGuardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGuardar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGuardar.setBorder(UIManager.getBorder("Button.border"));
		btnGuardar.setBounds(24, 464, 89, 23);
		panelRegistro.add(btnGuardar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				btnGuardar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnCancelar.setEnabled(false);
			}
		});
		btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCancelar.setBorder(UIManager.getBorder("Button.border"));
		btnCancelar.setBounds(263, 464, 89, 23);
		panelRegistro.add(btnCancelar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String duracion = cbHoraDuracion.getSelectedItem().toString() + ":" + cbMinutosDuracion.getSelectedItem().toString() + ":" + cbSegundosDuracion.getSelectedItem().toString();
				if (txtNombreSer.getText().equals("") || txtDescripcion.getText().equals("")
						|| txtPrecioSer.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
				} else {
					servicio.setNombreServicio(txtNombreSer.getText());
					servicio.setDescripcion(txtDescripcion.getText());
					servicio.setPrecio(Float.parseFloat(txtPrecioSer.getText()));
					servicio.setDuracion(duracion);
					servicio.setIdCategoria(categoria.obtenerIdCategoria(cbCategoria.getSelectedItem().toString()));
						if (servicio.modificar(Integer.parseInt(txtIdServicio.getText()))) {
							JOptionPane.showMessageDialog(null, "Servicio actualizado correctamente");
							llenarTabla("");
							limpiarCampos();
							btnEditar.setEnabled(false);
							btnCancelar.setEnabled(false);
						} else {
							JOptionPane.showMessageDialog(null, "Error al actualizar el servicio");
						}
					}
				}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnEditar.setBorder(UIManager.getBorder("Button.border"));
		btnEditar.setBounds(140, 464, 89, 23);
		panelRegistro.add(btnEditar);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBorder(null);
		textField_4.setBackground(new Color(241, 241, 241));
		textField_4.setBounds(196, 69, 0, 20);
		panelRegistro.add(textField_4);

		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(255, 255, 255));
		panelTablaPr.setBounds(445, 73, 535, 453);
		add(panelTablaPr);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int fila = tbServicio.getSelectedRow();
					if (fila >= 0) {
						txtIdServicio.setText(tbServicio.getValueAt(fila, 0).toString());
						txtNombreSer.setText(tbServicio.getValueAt(fila, 2).toString());
						txtPrecioSer.setText(tbServicio.getValueAt(fila, 3).toString());
						txtDescripcion.setText(tbServicio.getValueAt(fila, 4).toString());
						
						cbCategoria.setSelectedItem(tbServicio.getValueAt(fila, 1).toString());
						btnEditar.setEnabled(true);
						btnCancelar.setEnabled(true);
					}
				}
			}
		});
		scrollPane.setBounds(10, 90, 515, 352);
		panelTablaPr.add(scrollPane);

		tbServicio = new JTable();
		tbServicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int fila = tbServicio.getSelectedRow();
					if (fila >= 0) {
						txtIdServicio.setText(tbServicio.getValueAt(fila, 0).toString());
						txtNombreSer.setText(tbServicio.getValueAt(fila, 2).toString());
						txtPrecioSer.setText(tbServicio.getValueAt(fila, 3).toString());
						txtDescripcion.setText(tbServicio.getValueAt(fila, 4).toString());
						Time duracion = (Time) tbServicio.getValueAt(fila, 5);
						LocalTime duracionLocal = duracion.toLocalTime();
						cbHoraDuracion.setSelectedItem(String.format("%02d", duracionLocal.getHour()));
			            cbMinutosDuracion.setSelectedItem(String.format("%02d", duracionLocal.getMinute()));
			            cbSegundosDuracion.setSelectedItem(String.format("%02d", duracionLocal.getSecond()));	
						cbCategoria.setSelectedItem(tbServicio.getValueAt(fila, 1).toString());
						btnGuardar.setEnabled(false);
						btnEditar.setEnabled(true);
						btnCancelar.setEnabled(true);
					}
				}
			}
		});
		scrollPane.setViewportView(tbServicio);

		JLabel lblVisualizacionDeLos = new JLabel("Visualizacion de los Servicios");
		lblVisualizacionDeLos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDeLos.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDeLos.setBounds(10, 11, 454, 36);
		panelTablaPr.add(lblVisualizacionDeLos);

		txtBuscarSer = new JTextField();
		txtBuscarSer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenarTabla(txtBuscarSer.getText());
			}
		});
		txtBuscarSer.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBuscarSer.setColumns(10);
		txtBuscarSer.setBorder(null);
		txtBuscarSer.setBackground(new Color(255, 255, 255));
		txtBuscarSer.setBounds(10, 56, 128, 20);
		panelTablaPr.add(txtBuscarSer);

		JLabel lblMarcaPr_1 = new JLabel("________________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(10, 59, 140, 25);
		panelTablaPr.add(lblMarcaPr_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Servicio.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);


		JButton btnCategoria = new JButton("Agregar Categoria");
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Categoria = JOptionPane.showInputDialog("Ingrese la nueva categoria");
				if (categoria.existeDuplicado(Categoria)) {
					JOptionPane.showMessageDialog(null, "La categoria ya existe");
				} else {
					categoria.setCategoria(Categoria);
					categoria.insertar();
					categoria.cargarCategorias(cbCategoria);
				}
			}
		});
		btnCategoria.setBounds(235, 279, 136, 25);
		btnCategoria.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCategoria.setBorder(UIManager.getBorder("Button.border"));
		btnCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelRegistro.add(btnCategoria);
		btnEditar.setEnabled(false);
		btnCancelar.setEnabled(false);

		txtIdServicio = new JTextField();
		txtIdServicio.setBounds(224, 341, 0, 19);
		panelRegistro.add(txtIdServicio);
		txtIdServicio.setColumns(10);
		
		
		llenarTabla("");

	}

	public void llenarTabla(String Busqueda) {
		String[] cabeceras = { "ID", "Categoria", "Nombre del Servicio", "Precio", "Descripcion", "Duracion" };
		modelo = new DefaultTableModel(servicio.consultar(Busqueda), cabeceras) {
		private static final long serialVersionUID = 1L;
		@Override
        public boolean isCellEditable(int row, int column) {
            return false; // No permitir la edición de celdas
        }
    };
		tbServicio.setModel(modelo);
		
		int[] anchos = {10, 100, 135, 25, 60, 35}; // Definir los anchos deseados para cada columna
	    for (int i = 0; i < tbServicio.getColumnCount(); i++) {
	        TableColumn columna = tbServicio.getColumnModel().getColumn(i);
	        columna.setPreferredWidth(anchos[i]);
	    }
	}

	public void limpiarCampos() {
		txtNombreSer.setText("");
		txtDescripcion.setText("");
		txtPrecioSer.setText("");
		cbHoraDuracion.setSelectedIndex(0);
		cbMinutosDuracion.setSelectedIndex(0);
		cbSegundosDuracion.setSelectedIndex(0);
		
	}
}