package web;

import datos.ClienteDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;

/**
 *
 * @author asael
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar":
                    insertarCliente(request, response);
                    break;
                case "modificar":
                    modificarCliente(request,response);
                    break;
                    default:
                        enviarInfo(request, response);
            }
        } else {
            enviarInfo(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    editarCliente(request, response);
                    break;
                case "eliminar":
                    eliminarCliente(request, response);
                    break;
                    default:
                        enviarInfo(request, response);
            }
        } else {
            enviarInfo(request, response);
        }
    }

    private double getTotal(List<Cliente> clientes) {
        double total = 0;
        for (Cliente c : clientes) {
            total += c.getSaldo();
        }
        return total;
    }
    
    private void enviarInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = ClienteDAO.getClienteDAO().listar();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", getTotal(clientes));
        response.sendRedirect("clientes.jsp");
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (!saldoString.trim().isEmpty()) {
            saldo = Double.parseDouble(saldoString);
        }
        
        //Crear objeto cliente
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        
        //Insertar objeto en la base de datos
        ClienteDAO.getClienteDAO().insertar(cliente);
        
        //reenviamos los datos
        enviarInfo(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = ClienteDAO.getClienteDAO().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (!saldoString.trim().isEmpty()) {
            saldo = Double.parseDouble(saldoString);
        }
        
        //Crear objeto cliente
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
        
        //Modificamos objeto en la base de datos
        ClienteDAO.getClienteDAO().actualizar(cliente);
        
        //reenviamos los datos
        enviarInfo(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new Cliente(idCliente);
        ClienteDAO.getClienteDAO().eliminar(cliente);
        enviarInfo(request, response);
    }
}