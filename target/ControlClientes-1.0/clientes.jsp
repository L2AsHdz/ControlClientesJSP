<%-- 
    Document   : clientes
    Created on : 24/09/2020, 02:21:36
    Author     : asael
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!--CSS-->
        <jsp:include page="/WEB-INF/extras/extrasCSS.jsp"/>

        <title>Control de clientes</title>
    </head>
    <body>
        
        <!--Cabecero-->
        <jsp:include page="/WEB-INF/commonPages/header.jsp"/>
        
        <!--Botones de navegacion-->
        <jsp:include page="/WEB-INF/commonPages/botonesNavegacion.jsp"/>
        
        <!--Listado Clientes-->
        <jsp:include page="/WEB-INF/cliente/listadoClientes.jsp"/>
        
        
        <!--Botones de navegacion-->
        <jsp:include page="/WEB-INF/commonPages/footer.jsp"/>
        
       <!--JS--> 
        <jsp:include page="/WEB-INF/extras/extrasJS.jsp"/>
    </body>
</html>