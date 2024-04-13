package modelo;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import java.util.Base64;
import java.util.Properties;

import conexion.cls_conexion;

public class cls_personal {
	private String idPersonal;
	private String tipo;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	private String fechaNacimiento;
	private String genero;
	private String telefono;
	private String direccion;
	private String correo;
	private String clave;
	private String cargo;
	String sentencia;
	PreparedStatement st;
	int numero, suma, resultado;
	cls_conexion conex = new cls_conexion();
	cls_validaciones validaciones = new cls_validaciones();
//Variables para la Encriptacion
	private static final String Algoritmo = "AES";
	String original_valor = "mi_clave_oculta";
//Variables para el envio de correo

	@SuppressWarnings("unused")
	private Properties pro;

	public cls_personal() {

		this.idPersonal = "";
		this.tipo = "";
		this.nombre = "";
		this.primerApellido = "";
		this.segundoApellido = "";
		this.fechaNacimiento = "";
		this.genero = "";
		this.telefono = "";
		this.direccion = "";
		this.correo = "";
		this.clave = "";
		this.cargo = "";
		this.numero = 0;
		this.sentencia = "";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(String idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void Iniciar() {

		pro = new Properties();
	}

	public boolean insertar() {

		int respuesta = 0;
		try {
			if (getIdPersonal().isEmpty() || getNombre().isEmpty() || getPrimerApellido().isEmpty()|| getSegundoApellido().isEmpty() || getFechaNacimiento().isEmpty() || getGenero() == null || getGenero().toString().isEmpty() || getDireccion().isEmpty() || getTelefono().isEmpty() || getCorreo().isEmpty() || getCargo() == null || getCargo().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe completar los datos obligatorios");
			} else {

				if (!validaciones.validarCedula(getIdPersonal())) {
					JOptionPane.showMessageDialog(null, "Cédula inválida");
				} else if (!validaciones.ValidacionEmail(getCorreo())) {
					JOptionPane.showMessageDialog(null, "Correo electrónico inválido");
				} else if (!getCargo().toString().equals("Estilista") && !getCargo().toString().equals("Cosmetologo") &&
		                !validaciones.validacionClave(new String(getClave()))) {
					JOptionPane.showMessageDialog(null, "Clave inválida. No cumple con los requisitos");
				} else if (!validaciones.validarEdad(getFechaNacimiento())) {
					JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida. Debe ser mayor de edad");
				} else {

					String sentencia = "INSERT INTO PERSONAL( ID_PERSONAL, TIPO_DOCUMENTO, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, FECHA_NACIMIENTO, GENERO, TELEFONO, DIRECCION, CORREO, CLAVE, CARGO) values (?,?,?,?,?,?,?,?,?,?,?,?);";
					PreparedStatement st = conex.conectar().prepareStatement(sentencia);
					st.setString(1, this.getIdPersonal());
					st.setString(2, this.getTipo());
					st.setString(3, this.getNombre());
					st.setString(4, this.getPrimerApellido());
					st.setString(5, this.getSegundoApellido());
					st.setString(6, this.getFechaNacimiento());
					st.setString(7, this.getGenero());
					st.setString(8, this.getTelefono());
					st.setString(9, this.getDireccion());
					st.setString(10, this.getCorreo());

					// Encriptar la contraseña antes de insertarla
					String claveEncriptada = Encriptar(this.getClave());
					st.setString(11, claveEncriptada);

					st.setString(12, this.getCargo());
		                
		                respuesta = st.executeUpdate();
		            }
		        }
		    } catch (Exception e) {
		        System.out.println(e);
		    }
		    return respuesta > 0;
		}

	public boolean modificar() {
		int respuesta = 0;
		try {
			if (getIdPersonal().isEmpty() || getNombre().isEmpty() || getPrimerApellido().isEmpty()|| getSegundoApellido().isEmpty() || getFechaNacimiento().isEmpty() || getGenero() == null || getGenero().toString().isEmpty() || getDireccion().isEmpty() || getTelefono().isEmpty() || getCorreo().isEmpty() || getCargo() == null || getCargo().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Debe completar los datos obligatorios");
			} else {
				if (!validaciones.ValidacionEmail(getCorreo())) {
					JOptionPane.showMessageDialog(null, "Correo electrónico inválido");
				} else {
			String sentencia = "UPDATE PERSONAL SET NOMBRE=?, TIPO_DOCUMENTO=?, PRIMER_APELLIDO=?, SEGUNDO_APELLIDO=?, FECHA_NACIMIENTO=?, GENERO=?, TELEFONO=?, DIRECCION=?, CORREO=?, CLAVE=?, CARGO=? WHERE ID_PERSONAL=?";
			PreparedStatement st = conex.conectar().prepareStatement(sentencia);

			st.setString(1, this.getNombre());
			st.setString(2, this.getTipo());
			st.setString(3, this.getPrimerApellido());
			st.setString(4, this.getSegundoApellido());
			st.setString(5, this.getFechaNacimiento());
			st.setString(6, this.getGenero());
			st.setString(7, this.getTelefono());
			st.setString(8, this.getDireccion());
			st.setString(9, this.getCorreo());

			// Encriptar la contraseña antes de insertarla
			String claveEncriptada = Encriptar(this.getClave());
			st.setString(10, claveEncriptada);

			st.setString(11, this.getCargo());
			st.setString(12, this.getIdPersonal());
		

			respuesta = st.executeUpdate();
	            }
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return respuesta > 0;
	}

	public SecretKeySpec generarClave(String llave) {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(llave.toCharArray(), "salt".getBytes(StandardCharsets.UTF_8), 65536, 128);
			try {
				SecretKeySpec secretKeySpec = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), Algoritmo);
				return secretKeySpec;
			} catch (InvalidKeySpecException ex) {
				Logger.getLogger(cls_personal.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(cls_personal.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public String Encriptar(String valor) {
		try {
			SecretKeySpec secretKeySpec = generarClave(original_valor);
			Cipher cipher = Cipher.getInstance(Algoritmo);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

			byte[] valorBytes = valor.getBytes(StandardCharsets.UTF_8);
			byte[] encrypedBytes = cipher.doFinal(valorBytes);
			return Base64.getEncoder().encodeToString(encrypedBytes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String DesEncriptar(String valor) {
		try {
			SecretKeySpec secretKeySpec = generarClave(original_valor);
			Cipher cipher = Cipher.getInstance(Algoritmo);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

			byte[] valorEncriptadoBytes = Base64.getDecoder().decode(valor);
			byte[] desencrypedBytes = cipher.doFinal(valorEncriptadoBytes);
			return new String(desencrypedBytes, StandardCharsets.UTF_8);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean Login(String correo, String clave) {
	    setCorreo(correo);
	    String encriptacion = Encriptar(clave);
	    setClave(encriptacion);

	    try {
	        String sentencia = "SELECT ID_PERSONAL, PRIMER_APELLIDO , SEGUNDO_APELLIDO, CARGO FROM PERSONAL WHERE CORREO = ? AND CLAVE = ? AND CARGO IN ('Gerente', 'Cajero')";
	        PreparedStatement st = conex.conectar().prepareStatement(sentencia);
	        st.setString(1, correo);
	        st.setString(2, encriptacion);
	        ResultSet datos = st.executeQuery();

	        if (datos.next()) {
	            
	            String idPersonal = datos.getString("ID_PERSONAL");
	            String cargo = datos.getString("CARGO");
	            String primerApellido = datos.getString("PRIMER_APELLIDO");
	            String segundoApellido = datos.getString("SEGUNDO_APELLIDO");
	            
	            setIdPersonal(idPersonal);
	            setCargo(cargo);
	            setPrimerApellido(primerApellido);
	            setSegundoApellido(segundoApellido);

	            datos.close();
	            st.close();

	            return true;
	        } else {
	            datos.close();
	            st.close();

	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Error al ejecutar la consulta: " + e.getMessage());
	        return false;
	    }
	}
	

	public String[] consultarDato(String codigo) {
		String fila[] = new String[12];

		try {
			this.sentencia = "SELECT * FROM PERSONAL WHERE ID_PERSONAL= '" + codigo + "'";
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencia);
			datos.beforeFirst();
			while (datos.next()) {
				fila[0] = datos.getObject(1).toString();
				fila[1] = datos.getObject(2).toString();
				fila[2] = datos.getObject(3).toString();
				fila[3] = datos.getObject(4).toString();
				fila[4] = datos.getObject(5).toString();
				fila[5] = datos.getObject(6).toString();
				fila[6] = datos.getObject(7).toString();
				fila[7] = datos.getObject(8).toString();
				fila[8] = datos.getObject(9).toString();
				fila[9] = datos.getObject(10).toString();
				fila[10] = datos.getObject(11).toString();
				fila[11] = datos.getObject(12).toString();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return fila;
	}

	public Object[][] consultarPersonalFecha(String FechaInico, String FechaFin)
	{
		Object obj [][]=null;
		try {
			if (FechaInico.equals("")|| FechaFin.equals(""))
            {
                this.sentencia= "SELECT * FROM PERSONAL";	
            }
            else 
            {
                this.sentencia= "SELECT * FROM PERSONAL  WHERE FECHA_NACIMIENTO BETWEEN '" + FechaInico + "' AND '" + FechaFin + "'";
            }
			Statement st= conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet datos= st.executeQuery(this.sentencia);
			datos.last();
			int nf= datos.getRow();
			obj= new Object[nf][12];
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
				obj[f][10]= datos.getObject(11);
				obj[f][11]= datos.getObject(12);
				
				f++;
			
            }
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}
	
	public Object[][] consultar() {
		Object obj[][] = null;
		try {
			this.sentencia = "Select * from PERSONAL";
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencia);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][12];
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
				obj[f][10] = datos.getObject(11);
				obj[f][11] = datos.getObject(12);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

	public Object[][] consultarPersonal(String Buscar) {
		Object obj[][] = null;
		try {
			if (Buscar.equals("")) {
				this.sentencia = "Select *from PERSONAL";
			} else {
				this.sentencia = "SELECT * FROM PERSONAL WHERE ID_PERSONAL LIKE '%" + Buscar
						+ "%' OR PRIMER_APELLIDO LIKE '%" + Buscar + "%' OR Cargo LIKE '%" + Buscar + "%'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencia);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][12];
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
				obj[f][10] = datos.getObject(11);
				obj[f][11] = datos.getObject(12);
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;

	}
	
	public Object[][] elegirPersonal(String Buscar) {
		Object obj[][] = null;
		try {
			if (Buscar.equals("")) {
				this.sentencia = "Select TIPO_DOCUMENTO, ID_PERSONAL, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, GENERO, TELEFONO,  CORREO, CARGO from PERSONAL WHERE CARGO = 'Estilista' OR CARGO = 'Cosmetologo'";
			} else {
				this.sentencia = "SELECT TIPO_DOCUMENTO, ID_PERSONAL, NOMBRE, PRIMER_APELLIDO, SEGUNDO_APELLIDO, GENERO, TELEFONO,  CORREO, CARGO FROM PERSONAL WHERE  CARGO IN ('Estilista', 'Cosmetologo') AND ID_PERSONAL LIKE '%" + Buscar
						+ "%' OR PRIMER_APELLIDO LIKE '%" + Buscar + "%'";
			}
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencia);
			datos.last();
			int nf = datos.getRow();
			obj = new Object[nf][9];
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
				f++;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;

	}

	public int eliminar() {
	    int respuesta = 0;
	    try {
	        this.sentencia = "DELETE FROM PERSONAL WHERE ID_PERSONAL=?";
	        PreparedStatement st = conex.conectar().prepareStatement(sentencia);
	        st.setString(1, this.getIdPersonal());
	        respuesta = st.executeUpdate();

	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return respuesta;
	}


	public boolean existeCedulaDuplicada(String cedula) {
		boolean respuesta = false;
		try {
			sentencia = "SELECT * FROM PERSONAL WHERE ID_PERSONAL = ?";
			st = conex.conectar().prepareStatement(sentencia);
			st.setString(1, cedula);
			ResultSet datos = st.executeQuery();
			if (datos.next()) {
				respuesta = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return respuesta;
	}
	
	public boolean existeCorreoDuplicado(String correo) {
		boolean respuesta = false;
		try {
			sentencia = "SELECT * FROM PERSONAL WHERE CORREO = ?";
			st = conex.conectar().prepareStatement(sentencia);
			st.setString(1, correo);
			ResultSet datos = st.executeQuery();
			if (datos.next()) {
				respuesta = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return respuesta;
	}
	
	public boolean existeNumeroDuplicado(String telefono) {
		boolean respuesta = false;
		try {
			sentencia = "SELECT * FROM PERSONAL WHERE TELEFONO = ?";
			st = conex.conectar().prepareStatement(sentencia);
			st.setString(1, telefono);
			ResultSet datos = st.executeQuery();
			if (datos.next()) {
				respuesta = true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return respuesta;
	}
	
	public void buscarPersonal(String cedula) {
		try {
			this.sentencia = "SELECT * FROM PERSONAL WHERE ID_PERSONAL = '" + cedula + "'";
			Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet datos = st.executeQuery(this.sentencia);
			datos.beforeFirst();
			while (datos.next()) {
				setIdPersonal(cedula);
				setTipo(datos.getString(2));
				setNombre(datos.getString(3));
				setPrimerApellido(datos.getString(4));
				setSegundoApellido(datos.getString(5));
				setFechaNacimiento(datos.getString(6));
				setGenero(datos.getString(7));
				setTelefono(datos.getString(8));
				setDireccion(datos.getString(9));
				setCorreo(datos.getString(10));
				setClave(datos.getString(11));
				setCargo(datos.getString(12));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
