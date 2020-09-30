package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asael
 */
public class Conexion {
    private static Connection conexion = null;
    private final String url = "jdbc:mariadb://localhost:3306/ControlClientes";
    private final String usuario = "root";
    private final String password = "As_Hdz";

    private Conexion() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexion establecida");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("No se pudo conectar");
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConexion() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }
}
