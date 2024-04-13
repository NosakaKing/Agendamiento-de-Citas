package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexion.cls_conexion;

public class cls_facturacion {

	private String idFactura;
	private String idCliente;
	private String fechaEmision;
	private String metodoPago;
	private static String personalAtendido;
	private double subtotal;
	private double descuento;
	private double iva;
	private double total;
	private int idReservacion;
	private final String nombreEm = "Centro Estetico Brii";
	private final String direccionEm = "Calle: Via a Quevedo ";
	private final String correoEm = "centroesteticobrii@gmail.com";
	private final String ruc = "1723103733001";
	private final String autorizacionSRI = "1303202401172310373300120010010000000021900332914";
	private int i;
	Object[][] matrizDetalleFactura;
	cls_conexion conex = new cls_conexion();
	String sentencias;
	PreparedStatement st;

	public cls_facturacion() {
		this.idFactura = "";
		this.idCliente = "";
		this.fechaEmision = "";
		this.metodoPago = "";
		this.subtotal = 0;
		this.descuento = 0;
		this.iva = 0;
		this.total = 0;
	}

	public String getNombreEm() {
		return nombreEm;
	}

	public String getDireccionEm() {
		return direccionEm;
	}

	public String getCorreoEm() {
		return correoEm;
	}

	public static String getpersonalAtendido() {
		return personalAtendido;
	}

	public static void setpersonalAtendido(String personalAtendido) {
		cls_facturacion.personalAtendido = personalAtendido;
	}

	public int getIdReservacion() {
		return idReservacion;
	}

	public void setIdReservacion(int idReservacion) {
		this.idReservacion = idReservacion;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String getRuc() {
		return ruc;
	}

	public String getAutorizacionSRI() {
		return autorizacionSRI;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getTotal() {
	    return Math.round(total * 100.0) / 100.0;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String generarIdFactura() {
		try {
			String sentencia = "SELECT MAX(CAST(SUBSTRING(ID_FACTURA, 11, 9) AS UNSIGNED)) AS ULTIMO_NUMERO FROM FACTURA";
			PreparedStatement st = conex.conectar().prepareStatement(sentencia);
			ResultSet resultSet = st.executeQuery();
			int ultimoNumero = 0;
			if (resultSet.next()) {
				ultimoNumero = resultSet.getInt("ULTIMO_NUMERO");
			}

			ultimoNumero++;

			String nuevoIDFactura = String.format("000-000-%09d", ultimoNumero);
			return nuevoIDFactura;
		} catch (Exception e) {
			// Registra la excepción
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertar() {
		int respuesta = 0;
		idFactura = generarIdFactura();
		try {
			String sentencia = "INSERT INTO FACTURA (ID_FACTURA , ID_CLIENTE, PERSONAL_ATENDIDO ,RUC, AUTORIZACION_SRI, FECHA_EMISION, METODO_PAGO, SUBTOTAL, IVA, TOTAL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = conex.conectar().prepareStatement(sentencia);
			st.setString(1, this.idFactura);
			st.setString(2, this.idCliente);
			st.setString(3, personalAtendido);
			st.setString(4, this.ruc);
			st.setString(5, this.autorizacionSRI);
			st.setString(6, this.fechaEmision);
			st.setString(7, this.metodoPago);
			st.setDouble(8, this.subtotal);
			st.setDouble(9, this.iva);
			st.setDouble(10, this.total);
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Object[][] consultarDetalleReserva(int idReservacion) {
		Object obj[][] = null;
		try {
			this.sentencias = "SELECT DR.ID_DETALLE_RESERVA, S.NOMBRE_SERVICIO, S.PRECIO FROM DETALLE_RESERVA AS DR JOIN SERVICIO AS S ON DR.ID_SERVICIO = S.ID_SERVICIO WHERE DR.ID_RESERVACION = ?";
			PreparedStatement st = conex.conectar().prepareStatement(sentencias, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			st.setInt(1, idReservacion);
			ResultSet datos = st.executeQuery();
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][3];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	public double calcularSubtotal(int idReservacion) {
		double subtotal = 0;
		try {
			this.sentencias = "SELECT SUM(S.PRECIO) FROM DETALLE_RESERVA AS DR JOIN SERVICIO AS S ON DR.ID_SERVICIO = S.ID_SERVICIO WHERE DR.ID_RESERVACION = ?";
			PreparedStatement st = conex.conectar().prepareStatement(sentencias, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			st.setInt(1, idReservacion);
			ResultSet datos = st.executeQuery();
			if (datos.next()) {
				subtotal = datos.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return subtotal;
	}

	public double calcularTotal(double subtotal, double iva) {
		double total = 0;
		total = subtotal * (1 + iva);
		return total;
	}

	public Object[][] consultarFacturaFechas(String fechaInicio, String fechaFin) {
		Object obj[][] = null;
		try {
			if (fechaInicio.equals("") || fechaFin.equals("")) {
				this.sentencias = "SELECT * FROM FACTURA";
			} else {
				this.sentencias = "SELECT * FROM FACTURA WHERE FECHA_EMISION BETWEEN" + "'" + fechaInicio + "'"
						+ " AND " + "'" + fechaFin + "'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][10];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				obj[f][6] = datos.getObject(7);
				obj[f][7] = datos.getObject(8);
				obj[f][8] = datos.getObject(9);
				obj[f][9] = datos.getObject(10);

				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	public Object[][] consultarFactura(String buscar) {
		Object obj[][] = null;
		try {
			if (buscar.equals("")) {
				this.sentencias = "SELECT * FROM FACTURA";
			} else {
				this.sentencias = "SELECT * FROM FACTURA WHERE ID_FACTURA LIKE '%" + buscar + "%' OR ID_CLIENTE LIKE '%"
						+ buscar + "%' OR PERSONAL_ATENDIDO LIKE '%" + buscar + "%'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][10];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				obj[f][6] = datos.getObject(7);
				obj[f][7] = datos.getObject(8);
				obj[f][8] = datos.getObject(9);
				obj[f][9] = datos.getObject(10);

				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
	
	public Object[][] llenarDetalleFactura(String idFactura) {
		Object obj[][] = null;
		try {
			this.sentencias = "SELECT DR.ID_DETALLE_RESERVA, S.NOMBRE_SERVICIO, DP.PRECIO_UNITARIO FROM DETALLE_PEDIDO AS DP JOIN DETALLE_RESERVA AS DR ON DP.ID_DETALLE_RESERVA = DR.ID_DETALLE_RESERVA JOIN SERVICIO AS S ON DR.ID_SERVICIO = S.ID_SERVICIO WHERE DP.ID_FACTURA = ?";
			PreparedStatement st = conex.conectar().prepareStatement(sentencias, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			st.setString(1, idFactura);
			ResultSet datos = st.executeQuery();
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][3];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;	
	}

	public boolean insertarDetalleFactura(String idFactura) {
		int respuesta = 0;
		try {
			String sentencia = "INSERT INTO DETALLE_PEDIDO (ID_FACTURA, ID_DETALLE_RESERVA, PRECIO_UNITARIO) VALUES (?, ?, ?)";
			PreparedStatement st = conex.conectar().prepareStatement(sentencia);
			for (i = 0; i < matrizDetalleFactura.length; i++) {
				st.setString(1, idFactura);
				st.setInt(2, Integer.parseInt(matrizDetalleFactura[i][0].toString()));
				st.setDouble(3, Double.parseDouble(matrizDetalleFactura[i][2].toString()));
				respuesta = st.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setMatrizDetalleFactura(Object[][] matrizDetalleFactura) {
		this.matrizDetalleFactura = matrizDetalleFactura;
	}

}
