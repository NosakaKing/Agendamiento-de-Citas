package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import modelo.cls_reserva;
import modelo.cls_servicios;
import vista.panel.Reserva;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JTable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ElegirServicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscarSer;
	cls_servicios servicio = new cls_servicios();
	public static DefaultTableModel modelo;
	private JTable tbServicios;
	private JScrollPane scrollPane;



	/**
	 * Create the frame.
	 */
	public ElegirServicio() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 931, 544);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(241, 241, 241));
		panelTablaPr.setBounds(28, 76, 877, 453);
		contentPane.add(panelTablaPr);
		
		scrollPane = new JScrollPane();
		scrollPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenarTabla("");
				;

			}
		});
		scrollPane.setBounds(10, 90, 857, 352);
		panelTablaPr.add(scrollPane);
		
		tbServicios = new JTable();
		tbServicios.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
		            int fila = tbServicios.getSelectedRow();
		            String id;
		            String nombre;
		            id = tbServicios.getValueAt(fila, 0).toString();
		            nombre = tbServicios.getValueAt(fila, 2).toString();
		            cls_reserva r = new cls_reserva();
		            r.setIdServicio(Integer.parseInt(id));
		            Reserva.txtIdServicio.setText(id);
		            Reserva.txtNombreServ.setText(nombre);
		            
		            dispose();
		          }
			}
		});
		scrollPane.setViewportView(tbServicios);
		// Ajustar el ancho de las columnas del encabezado
		JTableHeader tableHeader = tbServicios.getTableHeader();
		TableCellRenderer renderer = tableHeader.getDefaultRenderer();
		JLabel label = (JLabel) renderer;
		label.setHorizontalAlignment(JLabel.LEFT);

		// Permitir que las columnas adicionales se vean con la barra de desplazamiento horizontal
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// Ajustar el ancho de cada columna al método llenarTabla()
		tbServicios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		JLabel lblVisualizacionDelCliente = new JLabel("Visualizacion de los Servicios");
		lblVisualizacionDelCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelCliente.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelCliente.setBounds(10, 11, 857, 36);
		panelTablaPr.add(lblVisualizacionDelCliente);
		
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
		txtBuscarSer.setBackground(new Color(241, 241, 241));
		txtBuscarSer.setBounds(10, 56, 128, 20);
		panelTablaPr.add(txtBuscarSer);
		
		JLabel lblMarcaPr_1 = new JLabel("________________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(10, 59, 140, 20);
		panelTablaPr.add(lblMarcaPr_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ElegirServicio.class.getResource("/images/7Buscar.png")));
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);
		
		JLabel lblSeccionDeClientes = new JLabel("Elegir Servicio");
		lblSeccionDeClientes.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDeClientes.setBounds(28, 11, 190, 54);
		contentPane.add(lblSeccionDeClientes);
		
		
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

	public void llenarTabla(String Busqueda) {
		String[] cabeceras = { "ID Servicio", "Categoria", "Nombre del Servicio", "Precio", "Descripcion", "Duracion" };
		modelo = new DefaultTableModel(servicio.consultar(Busqueda), cabeceras);
		
		tbServicios.setModel(modelo);
		
		int[] anchos = {100, 100, 200, 80, 300, 100}; // Definir los anchos deseados para cada columna
	    for (int i = 0; i < tbServicios.getColumnCount(); i++) {
	        TableColumn columna = tbServicios.getColumnModel().getColumn(i);
	        columna.setPreferredWidth(anchos[i]);
	    }
	}



}

