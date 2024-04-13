package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import conexion.cls_conexion;

public class cls_reserva {
	private String idCliente;
	private String idPersonal;

	private String fechaHoraCita;
	private String estado;
	private int idServicio;
	private int idReservacion;
	private String horaInicio;
	LocalTime duracionServicio;
	cls_conexion conex = new cls_conexion();
	PreparedStatement st;
	ResultSet rs;
	String sentencias;

	public cls_reserva() {
		idCliente = "";
		idPersonal = "";

		fechaHoraCita = "";
		estado = "";
		idServicio = 0;
		idReservacion = 0;
		horaInicio = "";
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getFechaHoraCita() {
		return fechaHoraCita;
	}

	public void setFechaHoraCita(String fechaHoraCita) {
		this.fechaHoraCita = fechaHoraCita;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public int getIdReservacion() {
		return idReservacion;
	}

	public void setIdReservacion(int idReservacion) {
		this.idReservacion = idReservacion;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public boolean insertarReserva() {
		int respuesta = 0;
		@SuppressWarnings("unused")
		int respuesta2 = 0;
		try {
			this.fechaHoraCita = ObtenerHoraInicio();
			sentencias = "INSERT INTO RESERVACION (ID_CLIENTE,FECHA_HORA_RES, FECHA_HORA_CITA, ESTADO) VALUES (?,?,?,?)";
			st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, this.idCliente);
			st.setString(2, LocalDateTime.now().toString());
			st.setString(3, this.fechaHoraCita);
			st.setString(4, this.estado);
			respuesta = st.executeUpdate();
			sentencias = "UPDATE DETALLE_RESERVA SET ID_RESERVACION =? WHERE ID_RESERVACION=?";
			PreparedStatement st2 = conex.conectar().prepareStatement(sentencias);
			System.out.println(this.idReservacion);
			st2.setInt(1, this.idReservacion);
			st2.setInt(2, 0);
			respuesta2 = st2.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Error al insertar reserva");
		} finally {
			cerrarRecursos(rs, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public LocalTime getDuracionServicio() {
		sentencias = "SELECT DURACION FROM SERVICIO WHERE ID_SERVICIO = ?";
		try {
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, this.idServicio);
			rs = st.executeQuery();
			while (rs.next()) {
				duracionServicio = LocalTime.parse(rs.getString("DURACION"));
			}
		} catch (Exception e) {
			System.out.println(e + "Error al obtener duracion de servicio");
		} finally {
			cerrarRecursos(rs, null, st);
		}
		return duracionServicio;
	}

	public LocalDateTime calcularHoraFin() {
		LocalDateTime horaFin = LocalDateTime.parse(this.horaInicio,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		horaFin = horaFin.plusHours(getDuracionServicio().getHour()).plusMinutes(getDuracionServicio().getMinute());
		return horaFin;
	}

	public boolean insertarDetalleReserva() {
		int respuesta = 0;
		String horaFin = calcularHoraFin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		try {

			sentencias = "INSERT INTO DETALLE_RESERVA (ID_RESERVACION, ID_PERSONAL, ID_SERVICIO, HORA_INICIO, HORA_FIN) VALUES (?,?,?,?,?)";
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, 0);
			st.setString(2, this.idPersonal);
			st.setInt(3, this.idServicio);
			st.setString(4, this.horaInicio);
			st.setString(5, horaFin);
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Error al insertar detalle reserva");
		} finally {
			cerrarRecursos(null, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String ObtenerHoraInicio() {
		sentencias = "SELECT MIN(HORA_INICIO) AS HORA_INICIO FROM DETALLE_RESERVA WHERE ID_RESERVACION = ?";
		try {
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, 0);
			rs = st.executeQuery();
			while (rs.next()) {
				horaInicio = rs.getString("HORA_INICIO");
			}
		} catch (Exception e) {
			System.out.println(e + "Error al obtener hora de inicio");
		} finally {
			cerrarRecursos(rs, null, st);
		}
		return horaInicio;
	}

	public void insertarHoraCita(String horaInicio, String idReservacion) {
		sentencias = "UPDATE RESERVACION SET FECHA_HORA_CITA = ? WHERE ID_RESERVACION = ?";
		try {
			st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, horaInicio);
			st.setInt(2, Integer.parseInt(idReservacion));
			st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Error al insertar hora de cita");
		} finally {
			cerrarRecursos(null, null, st);
		}
	}

	public Object[][] consultarReserva(String consulta) {
		Object obj[][] = null;
		try {
			if (consulta.equals("")) {
				this.sentencias = "SELECT R.ID_RESERVACION, R.ID_CLIENTE, C.NOMBRE, C.PRIMER_APELLIDO, R.FECHA_HORA_RES, R.FECHA_HORA_CITA, R.ESTADO FROM RESERVACION AS R JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE WHERE R.ESTADO NOT LIKE 'Cancelada' ORDER BY R.FECHA_HORA_RES DESC";
			} else {
				this.sentencias = "SELECT R.ID_RESERVACION, R.ID_CLIENTE, C.NOMBRE, C.PRIMER_APELLIDO, R.FECHA_HORA_RES, R.FECHA_HORA_CITA, R.ESTADO FROM RESERVACION AS R JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE WHERE (C.NOMBRE LIKE '%"
						+ consulta + "%' OR C.PRIMER_APELLIDO LIKE '%" + consulta + "%' OR R.ESTADO LIKE '%" + consulta
						+ "%' OR R.ID_CLIENTE LIKE '%" + consulta
						+ "%') AND R.ESTADO NOT LIKE 'Cancelada' AND ORDER BY R.FECHA_HORA_RES DESC";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][7];
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
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(rs, st, null);
		}
		return obj;
	}

	public Object[][] consultarReservaFechaReserva(String fechaInicio, String fechaFin) {
		Object obj[][] = null;
		try {
			if (fechaFin.equals("") || fechaInicio.equals("")) {
				this.sentencias = "SELECT R.ID_RESERVACION, R.ID_CLIENTE, C.NOMBRE, C.PRIMER_APELLIDO, R.FECHA_HORA_RES, R.FECHA_HORA_CITA, R.ESTADO FROM RESERVACION AS R JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE WHERE R.ESTADO NOT LIKE 'Cancelada'";
			} else {
				this.sentencias = "SELECT R.ID_RESERVACION, R.ID_CLIENTE, C.NOMBRE, C.PRIMER_APELLIDO, R.FECHA_HORA_RES, R.FECHA_HORA_CITA, R.ESTADO FROM RESERVACION AS R JOIN CLIENTE AS C ON R.ID_CLIENTE = C.ID_CLIENTE WHERE R.FECHA_HORA_RES BETWEEN"
						+ "'" + fechaInicio + "'" + " AND " + "'" + fechaFin + "' AND R.ESTADO NOT LIKE 'Cancelada'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][7];
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
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(rs, st, null);
		}
		return obj;
	}

	public Object[][] consultarReservaCalendario() {
		Object obj[][] = null;
		try {

			this.sentencias = "SELECT ID_RESERVACION, ID_CLIENTE, FECHA_HORA_RES, DATE_FORMAT(FECHA_HORA_CITA, '%d-%m-%Y') AS FECHA_CITA, ESTADO, FECHA_HORA_CITA FROM RESERVACION WHERE ESTADO NOT LIKE 'Cancelada'";

			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][6];
			datos.beforeFirst();
			int f = 0;
			while (datos.next()) {
				obj[f][0] = datos.getObject(1);
				obj[f][1] = datos.getObject(2);
				obj[f][2] = datos.getObject(3);
				obj[f][3] = datos.getObject(4);
				obj[f][4] = datos.getObject(5);
				obj[f][5] = datos.getObject(6);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(rs, st, null);
		}
		return obj;
	}

	public Object[][] consultarDetalleReserva(int idReservacion) {
		Object obj[][] = null;
		try {
			this.sentencias = "SELECT DR.ID_DETALLE_RESERVA,  DR.ID_PERSONAL ,P.NOMBRE, P.PRIMER_APELLIDO, DR.ID_SERVICIO ,S.NOMBRE_SERVICIO, DR.HORA_INICIO, DR.HORA_FIN FROM DETALLE_RESERVA AS DR JOIN PERSONAL AS P ON DR.ID_PERSONAL = P.ID_PERSONAL JOIN SERVICIO AS S ON DR.ID_SERVICIO = S.ID_SERVICIO WHERE DR.ID_RESERVACION = ?";
			PreparedStatement st = conex.conectar().prepareStatement(sentencias, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			st.setInt(1, idReservacion);
			ResultSet datos = st.executeQuery();
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][8];
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
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(rs, st, null);
		}
		return obj;
	}

	public boolean cancelarReserva(int idReservacion) {
		int respuesta = 0;
		try {
			sentencias = "UPDATE RESERVACION SET ESTADO = 'Cancelada' WHERE ID_RESERVACION = ?";
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, idReservacion);
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(null, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verificarHoraParaReserva(String horaInicio, String horaFin, String idPersonal) {
		int respuesta = 0;
		try {
			sentencias = "SELECT COUNT(*) AS CANTIDAD FROM DETALLE_RESERVA WHERE ID_PERSONAL = ? AND ((HORA_INICIO BETWEEN ? AND ?) OR (HORA_FIN BETWEEN ? AND ?))";
			st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, idPersonal);
			st.setString(2, horaInicio);
			st.setString(3, horaFin);
			st.setString(4, horaInicio);
			st.setString(5, horaFin);
			rs = st.executeQuery();
			while (rs.next()) {
				respuesta = rs.getInt("CANTIDAD");
			}
		} catch (Exception e) {
			System.out.println(e + "Error al verificar hora para reserva");
		} finally {
			cerrarRecursos(rs, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			System.out.println("Fecha libre");
			return false;
		}
	}

	public boolean eliminarDetalleReserva(int idDetalleReserva) {
		int respuesta = 0;
		try {
			sentencias = "DELETE FROM DETALLE_RESERVA WHERE ID_DETALLE_RESERVA = ?";
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, idDetalleReserva);
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Error al eliminar detalle reserva");
		} finally {
			cerrarRecursos(null, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int obtenerUltimoIdReservacion() {
		int idReservacion = 0;
		try {
			sentencias = "SELECT MAX(ID_RESERVACION) AS ID_RESERVACION FROM RESERVACION";
			st = conex.conectar().prepareStatement(sentencias);
			rs = st.executeQuery();
			while (rs.next()) {
				idReservacion = rs.getInt("ID_RESERVACION");
			}
		} catch (Exception e) {
			System.out.println(e + "Error al obtener ultimo id de reservacion");
		} finally {
			cerrarRecursos(rs, null, st);
		}
		return idReservacion;
	}

	public boolean editarEstadoReserva(int idReservacion) {
		int respuesta = 0;
		try {
			sentencias = "UPDATE RESERVACION SET ESTADO = ? WHERE ID_RESERVACION = ?";
			st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, this.estado);
			st.setInt(2, idReservacion);
			respuesta = st.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Error al editar estado de reserva");
		} finally {
			cerrarRecursos(null, null, st);
		}
		if (respuesta > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void cerrarRecursos(ResultSet rs, Statement stmt, PreparedStatement pstmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("Error al cerrar recursos: " + e);
		}
	}
}
