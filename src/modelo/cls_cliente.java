    package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import conexion.cls_conexion;

public class cls_cliente {
	private String cedula;
	private String tipo;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String fechaNacimiento;
	private String genero;
	private String direccion;
	private String correo;
	private String telefono;
	private String sentencia;
	cls_validaciones validaciones = new cls_validaciones();
	cls_conexion conex = new cls_conexion();

	public cls_cliente() {
		this.tipo= "";
		this.cedula = "";
		this.nombre = "";
		this.primerApellido = "";
		this.segundoApellido = "";
		this.fechaNacimiento = "";
		this.genero = "";
		this.direccion = "";
		this.correo = "";
		this.telefono = "";
	}

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSentencia() {
		return sentencia;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public boolean insertar() {
	    int respuesta = 0;
	    try {
	        if (getCedula().isEmpty() || getNombre().isEmpty() || getPrimerApellido().isEmpty() || getSegundoApellido().isEmpty() || getFechaNacimiento().isEmpty() || getGenero() == null || getDireccion().isEmpty() || getCorreo().isEmpty() || getTelefono().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Debe completar los datos obligatorios");
	        } else {
	            cls_validaciones validaciones = new cls_validaciones();
	            if (!validaciones.validarCedula(getCedula())) {
	                JOptionPane.showMessageDialog(null, "Cédula inválida");
	            } else if (!validaciones.ValidacionEmail(getCorreo())) {
	                JOptionPane.showMessageDialog(null, "Correo electrónico inválido");
	            } else if (!validaciones.validarEdad(getFechaNacimiento())) {
					JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Debe ser mayor de edad");
	            } else {
	                String sentencia = "INSERT INTO CLIENTE(ID_CLIENTE, TIPO_DOCUMENTO, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, FECHA_NACIMIENTO, GENERO, DIRECCION, CORREO, TELEFONO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement st = conex.conectar().prepareStatement(sentencia);
	                st.setString(1, getCedula());
	                st.setString(2, getTipo());
	                st.setString(3, getNombre());
	                st.setString(4, getPrimerApellido());
	                st.setString(5, getSegundoApellido());
	                st.setString(6, getFechaNacimiento());
	                st.setString(7, getGenero());
	                st.setString(8, getDireccion());
	                st.setString(9, getCorreo());
	                st.setString(10, getTelefono());
	                respuesta = st.executeUpdate();
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return respuesta > 0;
	}
	public String [] consultarDato(String codigo) {
		String fila[]= new String[10];
		
		try {
		    this.sentencia= "select ID_CLIENTE,TIPO_DOCUMENTO,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,FECHA_NACIMIENTO,GENERO,DIRECCION,CORREO,TELEFONO from CLIENTE where ID_CLIENTE = '" + codigo + "'";
			Statement st= conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet datos= st.executeQuery(this.sentencia);
			datos.beforeFirst();
			while(datos.next())
			{
			    fila[0]= datos.getObject(1).toString();
			    fila[1]= datos.getObject(2).toString();
			    fila[2]= datos.getObject(3).toString();
			    fila[3]= datos.getObject(4).toString();
			    fila[4]= datos.getObject(5).toString();
			    fila[5]= datos.getObject(6).toString();
			    fila[6]= datos.getObject(7).toString();
			    fila[7]= datos.getObject(8).toString();
			    fila[8]= datos.getObject(9).toString();
			    fila[9]= datos.getObject(10).toString();
			    
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return fila;
	}
	public Object[][] consultarClienteFechaN(String FechaInico, String FechaFin)
	{
		Object obj [][]=null;
		try {
			if (FechaInico.equals("")|| FechaFin.equals(""))
            {
                this.sentencia= "SELECT * FROM CLIENTE WHERE ID_CLIENTE NOT LIKE '0'";	
            }
            else 
            {
                this.sentencia= "SELECT * FROM CLIENTE  WHERE FECHA_NACIMIENTO BETWEEN '" + FechaInico + "' AND '" + FechaFin + "' AND ID_CLIENTE NOT LIKE '0'";
            }
			Statement st= conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet datos= st.executeQuery(this.sentencia);
			datos.last();
			int nf= datos.getRow();
			obj= new Object[nf][10];
			datos.beforeFirst();
			int f=0;
			while(datos.next())
			{
				obj[f][0]= datos.getObject(1);
				obj[f][1]= datos.getObject(2);
				obj[f][2]= datos.getObject(3);
				obj[f][3]= datos.getObject(4);
				obj[f][4]= datos.getObject(5);
				obj[f][5]= datos.getObject(6);
				obj[f][6]= datos.getObject(7);
				obj[f][7]= datos.getObject(8);
				obj[f][8]= datos.getObject(9);
				obj[f][9]= datos.getObject(10);
				f++;
			
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
public Object[][] consultarCliente(String Buscar)
{
	Object obj [][]=null;
	try {
		if (Buscar.equals(""))
		{
			this.sentencia= "SELECT * FROM CLIENTE WHERE ID_CLIENTE NOT LIKE '0'";	
		}
		else 
		{
			this.sentencia= "SELECT * FROM CLIENTE  WHERE ID_CLIENTE LIKE '%" + Buscar + "%' OR PRIMER_APELLIDO LIKE '%" + Buscar + "%' AND ID_CLIENTE NOT LIKE '0'";
		}
		Statement st= conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet datos= st.executeQuery(this.sentencia);
		datos.last();
		int nf= datos.getRow();
		obj= new Object[nf][10];
		datos.beforeFirst();
		int f=0;
		while(datos.next())
		{
			obj[f][0]= datos.getObject(1);
			obj[f][1]= datos.getObject(2);
			obj[f][2]= datos.getObject(3);
			obj[f][3]= datos.getObject(4);
			obj[f][4]= datos.getObject(5);
			obj[f][5]= datos.getObject(6);
			obj[f][6]= datos.getObject(7);
			obj[f][7]= datos.getObject(8);
			obj[f][8]= datos.getObject(9);
			obj[f][9]= datos.getObject(10);
			f++;
		}
	} catch (Exception e) {
		System.out.println(e);
	}
	return obj;
}

public void buscarCliente(String cedula) {
	try {
		this.sentencia = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = '" + cedula + "'";
		Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet datos = st.executeQuery(this.sentencia);
		datos.beforeFirst();
		while (datos.next()) {
			setCedula(datos.getString(1));
			setTipo(datos.getString(2));
			setNombre(datos.getString(3));
			setPrimerApellido(datos.getString(4));
			setSegundoApellido(datos.getString(5));
			setFechaNacimiento(datos.getString(6));
			setGenero(datos.getString(7));
			setDireccion(datos.getString(8));
			setCorreo(datos.getString(9));
			setTelefono(datos.getString(10));
		}
	} catch (Exception e) {
		System.out.println(e);
	}
}

public boolean modificar() {
	int respuesta = 0;
	try {
		if (getCedula().isEmpty() || getNombre().isEmpty() || getPrimerApellido().isEmpty() || getSegundoApellido().isEmpty() || getFechaNacimiento().isEmpty() || getGenero() == null || getDireccion().isEmpty() || getCorreo().isEmpty() || getTelefono().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe completar los datos obligatorios");
		} else {
			if (!validaciones.ValidacionEmail(getCorreo())) {
				JOptionPane.showMessageDialog(null, "Correo electrónico inválido");
			} else {
		this.sentencia = "update CLIENTE set TIPO_DOCUMENTO=?, NOMBRE=?, PRIMER_APELLIDO=?, SEGUNDO_APELLIDO=?, FECHA_NACIMIENTO=?, GENERO=?, DIRECCION=?, CORREO=?, TELEFONO=? where ID_CLIENTE = ?";
		PreparedStatement st = conex.conectar().prepareStatement(sentencia);
	
		st.setString(1, this.getTipo());
		st.setString(2, this.getNombre());
		st.setString(3, this.getPrimerApellido());
		st.setString(4, this.getSegundoApellido());
		st.setString(5, this.getFechaNacimiento());
		st.setString(6, this.getGenero());
		st.setString(7, this.getDireccion());
		st.setString(8, this.getCorreo());
		st.setString(9, this.getTelefono());
		st.setString(10,this.getCedula());
		respuesta = st.executeUpdate();
		}
    }
} catch (Exception e) {
    System.out.println(e);
}
return respuesta > 0;
}}