<%-- 
    Document   : list
    Created on : 10/08/2017, 19:19:49
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
        <title>Mesas - Listagem</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Listagem de Mesas</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/mesas?a=new">Novo</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>Numero</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="mesa" items="${ pageContext.request.getAttribute(\"mesas\") }" >

                        <tr>
                            <td><a href="${pageContext.request.contextPath}/mesas?a=edit&id=${mesa.id}">${mesa.numero}</a></td>
                            <td>${mesa.status}</td>
                        </tr>
                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>