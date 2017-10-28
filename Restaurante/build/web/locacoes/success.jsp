<%-- 
    Document   : success
    Created on : 20/11/2016, 21:53:21
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
        <title>Locação Concluida!</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="jumbotron" >
            <h3 style="text-align: center">Locação  efetuada com sucesso!</h3>
        </div>
        <a class="btn btn-default" href="${pageContext.request.contextPath}/locacoes" style="background-position: center">Voltar</a>
    </body>
</html>
