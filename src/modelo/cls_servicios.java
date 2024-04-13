package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import conexion.cls_conexion;

public class cls_servicios {
	private int idCategoria;
	private String nombreServicio;
	private float precio;
	private String descripcion;
	private String sentencias;
	private String duracion;
	cls_conexion conex = new cls_conexion();
	PreparedStatement st;
	ResultSet datos;
	
	public cls_servicios() {
		this.idCategoria = 0;
		this.nombreServicio = "";
		this.precio = 0;
		this.descripcion = "";
		this.duracion = "";
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNombreServicio() {
		return nombreServicio;
	}
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	
	public boolean insertar() {
		int respuesta = 0;
		try {
			sentencias = "INSERT INTO SERVICIO (ID_CAT, NOMBRE_SERVICIO, PRECIO, DESCRIPCION, DURACION) VALUES (?,?,?,?,?)";
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, this.idCategoria);
			st.setString(2, this.nombreServicio);
			st.setFloat(3, this.precio);
			st.setString(4, this.descripcion);
			st.setString(5, this.duracion);
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
	
	public boolean modificar(int idServicio) {
		int respuesta = 0;
		try {
			sentencias = "UPDATE SERVICIO SET ID_CAT = ?, NOMBRE_SERVICIO = ?, PRECIO = ?, DESCRIPCION = ?, DURACION = ? WHERE ID_SERVICIO = ?";
			st = conex.conectar().prepareStatement(sentencias);
			st.setInt(1, idCategoria);
			st.setString(2, nombreServicio);
			st.setFloat(3, precio);
			st.setString(4, descripcion);
			st.setString(5, duracion);
			st.setInt(6, idServicio);
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
	
	public Object[][] consultar(String servicio){
		Object obj[][] = null;
		try {
			if (servicio.equals("")) {
				this.sentencias = "SELECT S.ID_SERVICIO, C.CATEGORIA, S.NOMBRE_SERVICIO, S.PRECIO, S.DESCRIPCION, S.DURACION FROM SERVICIO AS S JOIN CATEGORIA AS C ON S.ID_CAT = C.ID_CAT";
			} else {
				this.sentencias = "SELECT S.ID_SERVICIO, C.CATEGORIA, S.NOMBRE_SERVICIO, S.PRECIO, S.DESCRIPCION, S.DURACION FROM SERVICIO AS S JOIN CATEGORIA AS C ON S.ID_CAT = C.ID_CAT WHERE S.NOMBRE_SERVICIO LIKE '%" + servicio + "%' OR C.CATEGORIA LIKE '%" + servicio + "%'";
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
			cerrarRecursos(datos, st, null);
		}
		return obj;
	}
	
	public boolean verificarServicio(String servicio) {
		boolean respuesta = false;
		try {
			sentencias = "SELECT * FROM SERVICIO WHERE NOMBRE_SERVICIO = ?";
			st = conex.conectar().prepareStatement(sentencias);
			st.setString(1, servicio);
			ResultSet datos = st.executeQuery();
			if (datos.next()) {
				respuesta = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			cerrarRecursos(datos, null, st);
		}
		return respuesta;
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
