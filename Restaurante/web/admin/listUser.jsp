<%-- 
    Document   : listUser
    Created on : 29/09/2017, 02:55:13
    Author     : Matheus
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../assets.jsp" %>
        <title>Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Listagem</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/#">Novo</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>Login</th>
                        <th>Senha</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="usuario" items="${ pageContext.request.getAttribute(\"usuarios\") }" >
                        <tr>
                            <td><a href="#">${usuario.login}</a></td>
                            <td>${usuario.senha}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
