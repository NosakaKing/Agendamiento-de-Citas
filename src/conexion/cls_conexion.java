package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class cls_conexion {
    private String db = "centro_estetico";
    private String usu = "root";
    private String cla = "Namiswam-1";
    private String url = "jdbc:mysql://localhost:3306/" + db;
    private Connection conexion = null;

    public cls_conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usu, cla);
            if (conexion != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error de conexión.");
            }
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public Connection conectar() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
