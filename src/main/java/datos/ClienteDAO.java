package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

/**
 *
 * @author asael
 */
public class ClienteDAO {

    private static ClienteDAO clienteDAO = null;
    Connection conexion = Conexion.getConexion();

    private ClienteDAO() {
    }

    public static ClienteDAO getClienteDAO() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }

    public List<Cliente> listar() {
        String sql = "SELECT * FROM cliente";
        List<Cliente> clientes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql);  
                ResultSet rs = ps.executeQuery()) {
            clientes = new ArrayList();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setSaldo(rs.getDouble("saldo"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return clientes;
    }

    public Cliente encontrar(Cliente cliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        Cliente c = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cliente.getIdCliente());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setEmail(rs.getString("email"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setSaldo(rs.getDouble("saldo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return c;
    }

    public void insertar(Cliente c) {
        String sql = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) "
                + " VALUES(?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelefono());
            ps.setDouble(5, c.getSaldo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void actualizar(Cliente c) {
        String sql = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, "
                + "saldo=? WHERE id_cliente=?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getTelefono());
            ps.setDouble(5, c.getSaldo());
            ps.setInt(6, c.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void eliminar(Cliente c) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, c.getIdCliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
