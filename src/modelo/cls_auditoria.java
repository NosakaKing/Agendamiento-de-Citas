package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import conexion.cls_conexion;

public class cls_auditoria {
	private String id_Personal;
	private String Tabla;
	private String operacion;
	private String fechaHora;
	cls_conexion conex = new cls_conexion();
	String sentencias;
	PreparedStatement st;
	ResultSet datos;

	public cls_auditoria() {
		this.id_Personal = "";
		this.Tabla = "";
		this.operacion = "";
		this.fechaHora = "";
	}

	public String getId_Personal() {
		return id_Personal;
	}

	public String getTabla() {
		return Tabla;
	}

	public void setTabla(String tabla) {
		Tabla = tabla;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void setId_Personal(String id_Personal) {
		this.id_Personal = id_Personal;
	}

	public boolean insertar() {
		int respuesta = 0;
		try {
			LocalDateTime localDateTime = LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(localDateTime);
			String sentencia = "INSERT INTO HISTORIAL_INGRESO (ID_PERSONAL, REGISTRO_INGRESO) VALUES (?, ?)";
			PreparedStatement st = conex.conectar().prepareStatement(sentencia);
			st.setString(1, id_Personal);
			st.setString(2, timestamp.toString());
			respuesta = st.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(null, null, st); // Cerrar PreparedStatement en el bloque finally
		}
		return respuesta > 0;
	}

	public Object[][] consultarFechaIngreso(String fechaInicio, String fechaFin) {
		Object obj[][] = null;
		try {
			if (fechaInicio.equals("") || fechaFin.equals("")) {
				this.sentencias = "SELECT hg.ID_INGRESO, hg.ID_PERSONAL , pe.NOMBRE , pe.PRIMER_APELLIDO , pe.SEGUNDO_APELLIDO, hg.REGISTRO_INGRESO FROM HISTORIAL_INGRESO hg JOIN PERSONAL pe ON hg.ID_PERSONAL = pe.ID_PERSONAL  ";
			} else {
				this.sentencias = "SELECT hg.ID_INGRESO, hg.ID_PERSONAL , pe.NOMBRE , pe.PRIMER_APELLIDO , pe.SEGUNDO_APELLIDO, hg.REGISTRO_INGRESO FROM HISTORIAL_INGRESO hg JOIN PERSONAL pe ON hg.ID_PERSONAL = pe.ID_PERSONAL WHERE DATE(REGISTRO_INGRESO) BETWEEN"
						+ "'" + fechaInicio + "'" + " AND " + "'" + fechaFin + "'";
			}
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
			cerrarRecursos(datos, st, null); // Cerrar Statement en el bloque finally
		}
		return obj;
	}

	public Object[][] consultarFechaIngresoBuscar(String idPersonal) {
		Object obj[][] = null;
		try {
			if (idPersonal.equals("")) {
				this.sentencias = "SELECT hg.ID_INGRESO, hg.ID_PERSONAL , pe.NOMBRE , pe.PRIMER_APELLIDO , pe.SEGUNDO_APELLIDO, hg.REGISTRO_INGRESO FROM HISTORIAL_INGRESO hg JOIN PERSONAL pe ON hg.ID_PERSONAL = pe.ID_PERSONAL  ";
			} else {
				this.sentencias = "SELECT hg.ID_INGRESO, hg.ID_PERSONAL , pe.NOMBRE , pe.PRIMER_APELLIDO , pe.SEGUNDO_APELLIDO, hg.REGISTRO_INGRESO FROM HISTORIAL_INGRESO hg JOIN PERSONAL pe ON hg.ID_PERSONAL = pe.ID_PERSONAL WHERE hg.ID_PERSONAL LIKE '%"
						+ idPersonal + "%' OR pe.PRIMER_APELLIDO LIKE '%" + idPersonal + "%'";
			}
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
			cerrarRecursos(datos, st, null); // Cerrar Statement en el bloque finally
		}
		return obj;
	}

	public Object[][] consultarAuditoriaFecha(String fechaInicio, String fechaFin) {
		Object obj[][] = null;
		try {
			if (fechaInicio.equals("") || fechaFin.equals("")) {
				this.sentencias = "SELECT ad.ID_AUDITORIA, ad.PERSONAL, pe.NOMBRE, pe.PRIMER_APELLIDO, pe.SEGUNDO_APELLIDO, ad.TABLA, ad.OPERACION, ad.FECHA_HORA FROM AUDITORIA ad JOIN PERSONAL pe ON ad.PERSONAL = pe.ID_PERSONAL";
			} else {
				this.sentencias = "SELECT ad.ID_AUDITORIA, ad.PERSONAL , pe.NOMBRE, pe.PRIMER_APELLIDO, pe.SEGUNDO_APELLIDO, ad.TABLA, ad.OPERACION, ad.FECHA_HORA FROM AUDITORIA ad JOIN PERSONAL pe ON ad.PERSONAL = pe.ID_PERSONAL WHERE DATE(FECHA_HORA) BETWEEN '"
						+ fechaInicio + "' AND '" + fechaFin + "'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
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
			cerrarRecursos(datos, st, null); // Cerrar Statement en el bloque finally
		}
		return obj;
	}

	public Object[][] consultarAuditoriaBuscar(String idPersonal) {
		Object obj[][] = null;
		try {
			if (idPersonal.equals("")) {
				this.sentencias = "SELECT ad.ID_AUDITORIA, ad.PERSONAL, pe.NOMBRE, pe.PRIMER_APELLIDO, pe.SEGUNDO_APELLIDO, ad.TABLA, ad.OPERACION, ad.FECHA_HORA FROM AUDITORIA ad JOIN PERSONAL pe ON ad.PERSONAL = pe.ID_PERSONAL";
			} else {
				this.sentencias = "SELECT ad.ID_AUDITORIA, ad.PERSONAL, pe.NOMBRE, pe.PRIMER_APELLIDO, pe.SEGUNDO_APELLIDO, ad.TABLA, ad.OPERACION, ad.FECHA_HORA FROM AUDITORIA ad JOIN PERSONAL pe ON ad.PERSONAL = pe.ID_PERSONAL WHERE ad.PERSONAL LIKE '%"
						+ idPersonal + "%' OR pe.PRIMER_APELLIDO LIKE '%" + idPersonal + "%'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
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
			cerrarRecursos(datos, st, null); // Cerrar Statement en el bloque finally
		}
		return obj;
	}

	public Object[][] consultarAuditoriaTabla(String tabla) {
		Object obj[][] = null;
		try {
			this.sentencias = "SELECT ad.ID_AUDITORIA, ad.PERSONAL, pe.NOMBRE, pe.PRIMER_APELLIDO, pe.SEGUNDO_APELLIDO, ad.TABLA, ad.OPERACION, ad.FECHA_HORA FROM AUDITORIA ad JOIN PERSONAL pe ON ad.PERSONAL = pe.ID_PERSONAL WHERE ad.TABLA = '"
					+ tabla + "'";
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencias);
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
			cerrarRecursos(datos, st, null); // Cerrar Statement en el bloque finally
		}
		return obj;
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
