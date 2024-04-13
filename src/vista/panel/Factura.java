package vista.panel;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import modelo.cls_cliente;
import modelo.cls_facturacion;
import modelo.cls_personal;
import vista.Facturacion;
import vista.swing.DateChooser;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Factura extends JPanel {

	private static final long serialVersionUID = 1L;
	public static DefaultTableModel modelo;
	private JTextField txtFechaInicio;
	private JTable tbFactura;
	private JTextField txtFechaFin;
	private DateChooser dateChooserFechaInicio;
	private DateChooser dateChooserFechaFin;
	cls_facturacion fact = new cls_facturacion();
	Facturacion factura = new Facturacion();
	cls_cliente cliente = new cls_cliente();
	cls_personal p = new cls_personal();

	/**
	 * Create the panel.
	 */
	public Factura() {
		
		JButton btnReporteFactura = new JButton("Reporte Factura");
		btnReporteFactura.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnReporteFactura.setFocusPainted(false);
		btnReporteFactura.setBorderPainted(false);
		btnReporteFactura.setBorder(UIManager.getBorder("Button.border"));
		btnReporteFactura.setBackground(new Color(241, 241, 241));
		btnReporteFactura.setBounds(596, 310, 133, 23);
		factura.getContentPane().add(btnReporteFactura);
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		JLabel lblSeccionDeHistorial = new JLabel("Seccion de Historial de Facturas");
		lblSeccionDeHistorial.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblSeccionDeHistorial.setBounds(10, 11, 292, 54);
		add(lblSeccionDeHistorial);

		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(255, 255, 255));
		panelTablaPr.setBounds(10, 73, 970, 705);
		add(panelTablaPr);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 90, 950, 525);
		panelTablaPr.add(scrollPane);

		tbFactura = new JTable();
		tbFactura.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int fila = tbFactura.getSelectedRow();
					factura.lblFacturaID.setText(tbFactura.getValueAt(fila, 0).toString());
					factura.txtCedulaCl.setText(tbFactura.getValueAt(fila, 1).toString());
					cliente.buscarCliente(tbFactura.getValueAt(fila, 1).toString());
					factura.txtNombresCl.setText(cliente.getNombre());
					factura.txtPrimerAp.setText(cliente.getPrimerApellido());
					factura.txtSegundoAp.setText(cliente.getSegundoApellido());
					factura.txtTelCl.setText(cliente.getTelefono());
					factura.txtCorreoCl.setText(cliente.getCorreo());
					factura.txtDireccionCl.setText(cliente.getDireccion());
					p.buscarPersonal(tbFactura.getValueAt(fila, 2).toString());
					factura.lblPersonal.setText(p.getNombre() + " " + p.getPrimerApellido() + " " + p.getSegundoApellido());
					factura.txtFechaHora.setText(tbFactura.getValueAt(fila, 5).toString());
					factura.txtMetodoPago.setText(tbFactura.getValueAt(fila, 6).toString());
				    factura.txtSubTotal.setText(tbFactura.getValueAt(fila, 7).toString());
				    factura.txtIva.setText(tbFactura.getValueAt(fila, 8).toString());
				    factura.txtTotal.setText(tbFactura.getValueAt(fila, 9).toString());
				    factura.llenarTablaDetalleFactura(tbFactura.getValueAt(fila, 0).toString());
					factura.setVisible(true);
					factura.btnGenerarPdf.setVisible(false);
					factura.setLocationRelativeTo(null);
					
				}

			}
		});
		scrollPane.setViewportView(tbFactura);

		JLabel lblVisualizacionDelPersonal = new JLabel("Visualizacion de Facturas");
		lblVisualizacionDelPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelPersonal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelPersonal.setBounds(10, 11, 903, 36);
		panelTablaPr.add(lblVisualizacionDelPersonal);

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
		txtFechaInicio.setBounds(205, 53, 60, 20);
		panelTablaPr.add(txtFechaInicio);

		JLabel lblMarcaPr_1 = new JLabel("___________");
		lblMarcaPr_1.setForeground(Color.GRAY);
		lblMarcaPr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1.setBounds(205, 55, 80, 25);
		panelTablaPr.add(lblMarcaPr_1);

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
		txtFechaFin.setBounds(362, 53, 128, 20);
		panelTablaPr.add(txtFechaFin);

		dateChooserFechaInicio = new DateChooser();
		dateChooserFechaInicio.setDateFormat("yyyy-MM-dd");
		dateChooserFechaInicio.setTextRefernce(txtFechaInicio);

		dateChooserFechaFin = new DateChooser();
		dateChooserFechaFin.setDateFormat("yyyy-MM-dd");
		dateChooserFechaFin.setTextRefernce(txtFechaFin);

		JLabel lblMarcaPr_1_1 = new JLabel("___________");
		lblMarcaPr_1_1.setForeground(Color.GRAY);
		lblMarcaPr_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarcaPr_1_1.setBounds(362, 55, 140, 25);
		panelTablaPr.add(lblMarcaPr_1_1);

		JLabel lblFitradoPorUn = new JLabel("Fitrado por un rango de fechas:");
		lblFitradoPorUn.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFitradoPorUn.setBounds(10, 52, 193, 31);
		panelTablaPr.add(lblFitradoPorUn);

		JLabel lblAl = new JLabel("al");
		lblAl.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAl.setBounds(300, 52, 25, 25);
		panelTablaPr.add(lblAl);

		JButton btnVaciarCajasDe = new JButton("Ver Todo");
		btnVaciarCajasDe.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVaciarCajasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFechaInicio.setText("");
				txtFechaFin.setText("");
			}
		});
		btnVaciarCajasDe.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnVaciarCajasDe.setBorder(UIManager.getBorder("Button.border"));
		btnVaciarCajasDe.setBackground(new Color(255, 255, 255));
		btnVaciarCajasDe.setBounds(512, 56, 105, 23);
		panelTablaPr.add(btnVaciarCajasDe);

		llenarTablaFactura(txtFechaInicio.getText(), txtFechaFin.getText());

	}

	private void actualizarTabla() {
		llenarTablaFactura(txtFechaInicio.getText(), txtFechaFin.getText());
	}

	public void llenarTablaFactura(String fechaInicio, String fechaFin) {
		if (validarFechas(fechaInicio, fechaFin)) {
			String[] cabeceras = { "ID Factura", "ID Cliente", "ID Personal", "RUC", "Autorizacion SRI", "Fecha Emision",
					"Metodo Pago", "Subtotal", "IVA", "Total" };
			modelo = new DefaultTableModel(fact.consultarFacturaFechas(fechaInicio, fechaFin), cabeceras) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isCellEditable(int row, int column) {
					return false; // No permitir la edición de celdas
				}
			};
			tbFactura.setModel(modelo);
			int[] anchos = {100, 50, 50, 60, 200, 60, 80, 30, 30, 30}; // Definir los anchos deseados para cada columna
		    for (int i = 0; i < tbFactura.getColumnCount(); i++) {
		        TableColumn columna = tbFactura.getColumnModel().getColumn(i);
		        columna.setPreferredWidth(anchos[i]);
		    }
		} else {
			JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser mayor que la fecha de fin.");
		}
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
