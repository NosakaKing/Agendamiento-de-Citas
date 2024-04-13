package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.cls_facturacion;
import modelo.cls_personal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Facturacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTable tbPedido;
	public JTextField txtNombresCl;
	public JTextField txtCedulaCl;
	public JTextField txtPrimerAp;
	public JTextField txtSegundoAp;
	public JLabel lblFacturaID;
	public JTextField txtTelCl;
	public JTextField txtCorreoCl;
	public JTextField txtDireccionCl;
	public JTextField txtFechaHora;
	public JTextField txtIva;
	public JTextField txtSubTotal;
	public JTextField txtTotal;
	public JTextField txtMetodoPago;
	DefaultTableModel modelo;
	cls_facturacion fact = new cls_facturacion();
	cls_personal per = new cls_personal();

	public JTextField txtIdReserva;
	public JLabel lblPersonal;

	/**
	 * Create the frame.
	 */
	public Facturacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(100, 100, 920, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(241, 241, 241));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFacturacion = new JLabel("Factura N");
		lblFacturacion.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblFacturacion.setBounds(10, 11, 92, 54);
		contentPane.add(lblFacturacion);

		JPanel panelTablaPr = new JPanel();
		panelTablaPr.setLayout(null);
		panelTablaPr.setBackground(new Color(241, 241, 241));
		panelTablaPr.setBounds(10, 60, 877, 650);
		contentPane.add(panelTablaPr);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 58, 857, 222);
		panelTablaPr.add(scrollPane);

		tbPedido = new JTable();
		scrollPane.setViewportView(tbPedido);

		JLabel lblVisualizacionDelPedido = new JLabel("Vista Previa de Factura");
		lblVisualizacionDelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizacionDelPedido.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblVisualizacionDelPedido.setBounds(10, 11, 857, 36);
		panelTablaPr.add(lblVisualizacionDelPedido);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(148, 58, 46, 21);
		panelTablaPr.add(lblNewLabel);

		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombres.setBounds(10, 369, 139, 36);
		panelTablaPr.add(lblNombres);

		txtNombresCl = new JTextField();
		txtNombresCl.setEditable(false);
		txtNombresCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNombresCl.setColumns(10);
		txtNombresCl.setBorder(null);
		txtNombresCl.setBackground(new Color(241, 241, 241));
		txtNombresCl.setBounds(10, 414, 139, 20);
		panelTablaPr.add(txtNombresCl);

		JLabel lblNombreCl = new JLabel("__________________");
		lblNombreCl.setForeground(Color.GRAY);
		lblNombreCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNombreCl.setBounds(10, 416, 151, 25);
		panelTablaPr.add(lblNombreCl);

		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedula.setBounds(10, 291, 139, 36);
		panelTablaPr.add(lblCedula);

		txtCedulaCl = new JTextField();
		txtCedulaCl.setEditable(false);
		txtCedulaCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCedulaCl.setColumns(10);
		txtCedulaCl.setBorder(null);
		txtCedulaCl.setBackground(new Color(241, 241, 241));
		txtCedulaCl.setBounds(10, 336, 103, 20);
		panelTablaPr.add(txtCedulaCl);

		JLabel lblCedulaCl = new JLabel("____________");
		lblCedulaCl.setForeground(Color.GRAY);
		lblCedulaCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCedulaCl.setBounds(10, 338, 96, 25);
		panelTablaPr.add(lblCedulaCl);

		JLabel lblPrimerApellido = new JLabel("Primer Apellido:");
		lblPrimerApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrimerApellido.setBounds(10, 456, 139, 36);
		panelTablaPr.add(lblPrimerApellido);

		txtPrimerAp = new JTextField();
		txtPrimerAp.setEditable(false);
		txtPrimerAp.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtPrimerAp.setColumns(10);
		txtPrimerAp.setBorder(null);
		txtPrimerAp.setBackground(new Color(241, 241, 241));
		txtPrimerAp.setBounds(10, 499, 103, 20);
		panelTablaPr.add(txtPrimerAp);

		JLabel lblPrCl = new JLabel("_____________");
		lblPrCl.setForeground(Color.GRAY);
		lblPrCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrCl.setBounds(10, 502, 120, 25);
		panelTablaPr.add(lblPrCl);

		JLabel lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSegundoApellido.setBounds(224, 456, 139, 36);
		panelTablaPr.add(lblSegundoApellido);

		txtSegundoAp = new JTextField();
		txtSegundoAp.setEditable(false);
		txtSegundoAp.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSegundoAp.setColumns(10);
		txtSegundoAp.setBorder(null);
		txtSegundoAp.setBackground(new Color(241, 241, 241));
		txtSegundoAp.setBounds(224, 499, 114, 20);
		panelTablaPr.add(txtSegundoAp);

		JLabel lblSnApCl = new JLabel("_____________");
		lblSnApCl.setForeground(Color.GRAY);
		lblSnApCl.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSnApCl.setBounds(224, 502, 114, 25);
		panelTablaPr.add(lblSnApCl);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTelefono.setBounds(224, 286, 89, 36);
		panelTablaPr.add(lblTelefono);

		txtTelCl = new JTextField();
		txtTelCl.setEditable(false);
		txtTelCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtTelCl.setColumns(10);
		txtTelCl.setBorder(null);
		txtTelCl.setBackground(new Color(241, 241, 241));
		txtTelCl.setBounds(224, 333, 103, 20);
		panelTablaPr.add(txtTelCl);

		JLabel lblTel = new JLabel("_____________");
		lblTel.setForeground(Color.GRAY);
		lblTel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTel.setBounds(224, 336, 120, 25);
		panelTablaPr.add(lblTel);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico:");
		lblCorreoElectronico.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblCorreoElectronico.setBounds(224, 369, 173, 36);
		panelTablaPr.add(lblCorreoElectronico);

		txtCorreoCl = new JTextField();
		txtCorreoCl.setEditable(false);
		txtCorreoCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCorreoCl.setColumns(10);
		txtCorreoCl.setBorder(null);
		txtCorreoCl.setBackground(new Color(241, 241, 241));
		txtCorreoCl.setBounds(224, 414, 182, 20);
		panelTablaPr.add(txtCorreoCl);

		JLabel lblDescripcionSer_1 = new JLabel("_______________________");
		lblDescripcionSer_1.setForeground(Color.GRAY);
		lblDescripcionSer_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer_1.setBounds(224, 416, 200, 25);
		panelTablaPr.add(lblDescripcionSer_1);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccion.setBounds(10, 552, 173, 36);
		panelTablaPr.add(lblDireccion);

		txtDireccionCl = new JTextField();
		txtDireccionCl.setEditable(false);
		txtDireccionCl.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtDireccionCl.setColumns(10);
		txtDireccionCl.setBorder(null);
		txtDireccionCl.setBackground(new Color(241, 241, 241));
		txtDireccionCl.setBounds(87, 558, 284, 20);
		panelTablaPr.add(txtDireccionCl);

		JLabel lblDireccionP = new JLabel("___________________________________");
		lblDireccionP.setForeground(Color.GRAY);
		lblDireccionP.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDireccionP.setBounds(87, 560, 295, 25);
		panelTablaPr.add(lblDireccionP);

		JLabel lblFechaYHora = new JLabel("Fecha:");
		lblFechaYHora.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblFechaYHora.setBounds(661, 291, 72, 36);
		panelTablaPr.add(lblFechaYHora);

		txtFechaHora = new JTextField();
		txtFechaHora.setEditable(false);
		txtFechaHora.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtFechaHora.setColumns(10);
		txtFechaHora.setBorder(null);
		txtFechaHora.setBackground(new Color(241, 241, 241));
		txtFechaHora.setBounds(716, 290, 103, 20);
		panelTablaPr.add(txtFechaHora);

		JLabel lblDescripcionSer_1_1 = new JLabel("_______________");
		lblDescripcionSer_1_1.setForeground(Color.GRAY);
		lblDescripcionSer_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescripcionSer_1_1.setBounds(716, 293, 126, 25);
		panelTablaPr.add(lblDescripcionSer_1_1);

		btnGenerarPdf = new JButton("Guardar y Generar PDF");
		btnGenerarPdf.setIcon(new ImageIcon(Facturacion.class.getResource("/images/10PImprimir.png")));
		btnGenerarPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarMatrizTabla();
				fact.setMatrizDetalleFactura(matrizTabla);
				if (fact.insertarDetalleFactura(lblFacturaID.getText())) {
					JOptionPane.showMessageDialog(null, "Factura generada con éxito");
					pdf();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Error al generar la factura");
				}
			}
		});
		btnGenerarPdf.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnGenerarPdf.setBorder(UIManager.getBorder("Button.border"));
		btnGenerarPdf.setBounds(661, 605, 192, 34);
		panelTablaPr.add(btnGenerarPdf);

		btnPdf = new JButton("Generar PDF");
		btnPdf.setIcon(new ImageIcon(Facturacion.class.getResource("/images/10PImprimir.png")));
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
				dispose();
			}
		});
		btnPdf.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnPdf.setBorder(UIManager.getBorder("Button.border"));
		btnPdf.setBackground(new Color(241, 241, 241));
		btnPdf.setBounds(702, 605, 151, 35);
		panelTablaPr.add(btnPdf);
		
		txtIva = new JTextField();
		txtIva.setEditable(false);
		txtIva.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtIva.setColumns(10);
		txtIva.setBorder(null);
		txtIva.setBackground(new Color(241, 241, 241));
		txtIva.setBounds(722, 344, 103, 20);
		panelTablaPr.add(txtIva);

		JLabel lblDescuento = new JLabel("Iva:");
		lblDescuento.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescuento.setBounds(691, 338, 89, 36);
		panelTablaPr.add(lblDescuento);

		JLabel lblPrecioSr = new JLabel("_____________");
		lblPrecioSr.setForeground(Color.GRAY);
		lblPrecioSr.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecioSr.setBounds(722, 347, 120, 25);
		panelTablaPr.add(lblPrecioSr);

		txtSubTotal = new JTextField();
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSubTotal.setColumns(10);
		txtSubTotal.setBorder(null);
		txtSubTotal.setBackground(new Color(241, 241, 241));
		txtSubTotal.setBounds(722, 402, 103, 20);
		panelTablaPr.add(txtSubTotal);

		JLabel lblDescuento_1 = new JLabel("Subtotal:");
		lblDescuento_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescuento_1.setBounds(660, 396, 120, 36);
		panelTablaPr.add(lblDescuento_1);

		JLabel lblPrecioSr_1 = new JLabel("_____________");
		lblPrecioSr_1.setForeground(Color.GRAY);
		lblPrecioSr_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecioSr_1.setBounds(722, 405, 120, 25);
		panelTablaPr.add(lblPrecioSr_1);

		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtTotal.setColumns(10);
		txtTotal.setBorder(null);
		txtTotal.setBackground(new Color(241, 241, 241));
		txtTotal.setBounds(722, 462, 103, 20);
		panelTablaPr.add(txtTotal);

		JLabel lblDescuento_1_1 = new JLabel("Total:");
		lblDescuento_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDescuento_1_1.setBounds(677, 456, 103, 36);
		panelTablaPr.add(lblDescuento_1_1);

		JLabel lblPrecioSr_1_1 = new JLabel("_____________");
		lblPrecioSr_1_1.setForeground(Color.GRAY);
		lblPrecioSr_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecioSr_1_1.setBounds(722, 465, 120, 25);
		panelTablaPr.add(lblPrecioSr_1_1);

		txtMetodoPago = new JTextField();
		txtMetodoPago.setEditable(false);
		txtMetodoPago.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtMetodoPago.setColumns(10);
		txtMetodoPago.setBorder(null);
		txtMetodoPago.setBackground(new Color(241, 241, 241));
		txtMetodoPago.setBounds(109, 607, 103, 20);
		panelTablaPr.add(txtMetodoPago);

		JLabel lblMetodoPago = new JLabel("Metodo Pago:");
		lblMetodoPago.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMetodoPago.setBounds(10, 603, 95, 36);
		panelTablaPr.add(lblMetodoPago);

		JLabel lblPrecioSr_2 = new JLabel("_____________");
		lblPrecioSr_2.setForeground(Color.GRAY);
		lblPrecioSr_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPrecioSr_2.setBounds(109, 609, 120, 25);
		panelTablaPr.add(lblPrecioSr_2);

		txtIdReserva = new JTextField();
		txtIdReserva.setBounds(444, 563, 0, 19);
		panelTablaPr.add(txtIdReserva);
		txtIdReserva.setColumns(10);

		lblFacturaID = new JLabel("");
		lblFacturaID.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblFacturaID.setBounds(99, 11, 450, 54);
		contentPane.add(lblFacturaID);

		JLabel lblAtendidoPor = new JLabel("Atendido por:");
		lblAtendidoPor.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblAtendidoPor.setBounds(440, 11, 128, 54);
		contentPane.add(lblAtendidoPor);

		lblPersonal = new JLabel("");
		lblPersonal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblPersonal.setBounds(559, 11, 319, 54);
		contentPane.add(lblPersonal);

	}

	public void llenarDetalleReservas(int idReserva) {
		String[] cabeceras = { "Num Detalle", "Servicio", "SubTotal" };
		modelo = new DefaultTableModel(fact.consultarDetalleReserva(idReserva), cabeceras);
		tbPedido.setModel(modelo);

	}

	public void llenarTablaDetalleFactura(String idFactura) {
		String[] cabeceras = { "ID", "Nombre Servicio",  "Precio Unitario" };
		modelo = new DefaultTableModel(fact.llenarDetalleFactura(idFactura), cabeceras) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // No permitir la edición de celdas
			}
			
		};
		tbPedido.setModel(modelo);
	}
	public void pdf() {
		try {
			FileOutputStream archivo;
			String idFactura = lblFacturaID.getText();
			File file = new File("src/factura/factura " + idFactura + ".pdf");
			archivo = new FileOutputStream(file);
			Document doc = new Document();
			PdfWriter.getInstance(doc, archivo);
			doc.open();
			Image img = Image.getInstance("src/images/LogoEmpresa.png");
			Paragraph fecha = new Paragraph();
			@SuppressWarnings("unused")
			com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD,
					BaseColor.BLACK);
			fecha.add(Chunk.NEWLINE);
			String nombrePersonal = lblPersonal.getText();
			fecha.add("Factura:  " + idFactura + "\nAtendido por: " + nombrePersonal + "\nFecha: "
					+ txtFechaHora.getText() + "\n\n");
			PdfPTable Encabezado = new PdfPTable(4);
			Encabezado.setWidthPercentage(100);
			Encabezado.getDefaultCell().setBorder(0);
			float[] ColummnaEncabezado = new float[] { 70f, 10f, 40f, 20f };
			Encabezado.setWidths(ColummnaEncabezado);
			Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
			Encabezado.addCell("Ruc: " + fact.getRuc() + "\nAutorizacion SRI: " + fact.getAutorizacionSRI()
					+ "\nNombre: " + fact.getNombreEm() + "\nDireccion: " + fact.getDireccionEm() + "\nCorreo: "
					+ fact.getCorreoEm());
			Encabezado.addCell("");
			Encabezado.addCell(fecha);
			Encabezado.addCell(img);
			doc.add(Encabezado);

			Paragraph cli = new Paragraph();
			cli.add(Chunk.NEWLINE);
			cli.add("Datos del cliente:" + "\n");
			doc.add(cli);

			PdfPTable tablacli = new PdfPTable(1);
			tablacli.setWidthPercentage(100);
			tablacli.getDefaultCell().setBorder(0);
			float[] Columnacli = new float[] { 70f };
			tablacli.setWidths(Columnacli);
			tablacli.setHorizontalAlignment(Element.ALIGN_LEFT);

			String Cedula = txtCedulaCl.getText();
			String nombresCompletos = txtSegundoAp.getText() + " " + txtPrimerAp.getText() + " "
					+ txtNombresCl.getText();
			String telefono = txtTelCl.getText();
			String correo = txtCorreoCl.getText();
			String direccion = txtDireccionCl.getText();
			tablacli.addCell("Cedula/Pasaporte: " + Cedula + "\nCliente: " + nombresCompletos + "\nTeléfono: "
					+ telefono + "\nCorreo: " + correo + "\nDirección: " + direccion + "\nMetodo de pago: " + "Efectivo"
					+ "\n\n");
			doc.add(tablacli);

			// Agregar la tabla tbPedido al documento PDF
			PdfPTable tablaFacPe = new PdfPTable(3); // 3 columnas en la tabla PDF
			tablaFacPe.setWidthPercentage(100);
			tablaFacPe.getDefaultCell().setBorder(0);
			float[] ColumnaFacPe = new float[] { 15f, 35f, 18f }; // Ajuste de tamaños
			tablaFacPe.setWidths(ColumnaFacPe);

			// Agregar encabezados de columna a la tabla del PDF
			for (int i = 0; i < modelo.getColumnCount(); i++) {
				PdfPCell cell = new PdfPCell(new Phrase(modelo.getColumnName(i),
						new com.itextpdf.text.Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.WHITE)));
				cell.setBackgroundColor(BaseColor.MAGENTA);
				tablaFacPe.addCell(cell);
			}

			// Agregar filas de la tabla al documento PDF
			for (int i = 0; i < modelo.getRowCount(); i++) {
				for (int j = 0; j < modelo.getColumnCount(); j++) {
					PdfPCell cell = new PdfPCell(new Phrase(modelo.getValueAt(i, j).toString()));
					cell.setBorder(0); // Eliminar bordes
					tablaFacPe.addCell(cell);
				}
			}

			doc.add(tablaFacPe);
			Paragraph info = new Paragraph();
			info.add(Chunk.NEWLINE);
			info.add("Subtotal: " + txtSubTotal.getText() + "\nIVA: "
					+ txtIva.getText() + "\nTotal: " + txtTotal.getText() + "\n\n");
			info.setAlignment(Element.ALIGN_RIGHT);
			doc.add(info);

			Paragraph msj = new Paragraph();
			msj.add(Chunk.NEWLINE);
			msj.add("Gracias por su compra :)");
			msj.setAlignment(Element.ALIGN_CENTER);
			doc.add(msj);

			doc.close();
			archivo.close();
			Desktop.getDesktop().open(file);

		} catch (DocumentException | IOException e) {
			System.out.println(e.toString());

		}
	}
	Object[][] matrizTabla;
	public JButton btnGenerarPdf;
	public JButton btnPdf;

	public void llenarMatrizTabla() {
		matrizTabla = new Object[tbPedido.getRowCount()][tbPedido.getColumnCount()];
		for (int i = 0; i < tbPedido.getRowCount(); i++) {
			for (int j = 0; j < tbPedido.getColumnCount(); j++) {
				matrizTabla[i][j] = tbPedido.getValueAt(i, j);
			}
		}
	}
	
}