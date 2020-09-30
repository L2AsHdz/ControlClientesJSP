<%-- 
    Document   : listadoClientes
    Created on : 24/09/2020, 10:08:37
    Author     : asael
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_GT" />

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-xl-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Nombre</th>
                                    <th>Saldo</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <!--Iteramos los elementos de la lista de clientes-->
                                <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${cliente.nombre} ${cliente.apellido}</td>
                                        <td><fmt:formatNumber value="${cliente.saldo}" type="currency" /></td>
                                        <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}" 
                                               class="btn btn-secondary">
                                                <i class="fas fa-angle-double-right"></i> Editar
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>	
                    </div>
                </div>
            </div>
            <!--Tarjetas para los totales-->
            <div class="col-xl-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo Total</h3>
                        <h4 class="display-4">
                            <fmt:formatNumber value="${saldoTotal}" type="currency" />
                        </h4>
                    </div>
                </div>

                <div class="card text-center bg-success text-white mb-3">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i> ${totalClientes}
                        </h4>
                    </div>
                </div>	
            </div>

        </div>
    </div>
</section>

<!-Modal para agregar clientes-->
<jsp:include page="/WEB-INF/cliente/AgregarCliente.jsp" />