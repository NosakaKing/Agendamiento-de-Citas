package modelo;
import javax.swing.*;
import conexion.cls_conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class cls_categoria {
    String sentencias;
    cls_conexion conex = new cls_conexion();
    PreparedStatement st;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void cargarCategorias(JComboBox<String> cbCategoria) {
        ResultSet datos = null; // Declarar ResultSet fuera del try para poder cerrarlo en el finally
        try {
            sentencias = "SELECT CATEGORIA FROM CATEGORIA";
            Statement st = conex.conectar().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            datos = st.executeQuery(this.sentencias);
            cbCategoria.removeAllItems();
            while (datos.next()) {
                cbCategoria.addItem(datos.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las categorias" + e);
        } finally {
            // Cerrar ResultSet y Statement en el bloque finally
            if (datos != null) {
                try {
                    datos.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar ResultSet: " + e);
                }
            }
        }
    }

    public boolean insertar() {
        int respuesta = 0;
        try {
            sentencias = "insert into CATEGORIA (CATEGORIA) values(?)";
            st = conex.conectar().prepareStatement(sentencias);
            st.setString(1, this.getCategoria());
            respuesta = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Cerrar PreparedStatement en el bloque finally
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e);
                }
            }
        }
        return respuesta > 0;
    }

    public boolean existeDuplicado(String categoria) {
        boolean respuesta = false;
        ResultSet datos = null; // Declarar ResultSet fuera del try para poder cerrarlo en el finally
        try {
            sentencias = "SELECT * FROM CATEGORIA WHERE CATEGORIA = ?";
            st = conex.conectar().prepareStatement(sentencias);
            st.setString(1, categoria);
            datos = st.executeQuery();
            if (datos.next()) {
                respuesta = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Cerrar ResultSet y PreparedStatement en el bloque finally
            if (datos != null) {
                try {
                    datos.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar ResultSet: " + e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e);
                }
            }
        }
        return respuesta;
    }

    public int obtenerIdCategoria(String categoria) {
        int id = 0;
        ResultSet datos = null; // Declarar ResultSet fuera del try para poder cerrarlo en el finally
        try {
            sentencias = "SELECT ID_CAT FROM CATEGORIA WHERE CATEGORIA = ?";
            st = conex.conectar().prepareStatement(sentencias);
            st.setString(1, categoria);
            datos = st.executeQuery();
            if (datos.next()) {
                id = datos.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Cerrar ResultSet y PreparedStatement en el bloque finally
            if (datos != null) {
                try {
                    datos.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar ResultSet: " + e);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception e) {
                    System.out.println("Error al cerrar PreparedStatement: " + e);
                }
            }
        }
        return id;
    }
}
