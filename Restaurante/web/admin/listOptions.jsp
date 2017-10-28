<%-- 
    Document   : adminOptions
    Created on : 28/09/2017, 19:53:57
    Author     : Matheus
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../assets.jsp" %>
        <title>Admin - Op��es</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Op��es</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin?a=listarUsuarios">Usuarios</a>
        </div>
    </body>
</html>
