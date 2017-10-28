<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <%@include file="assets.jsp" %>
        <title>Bem Vindo</title>
    </head>
    <body>
        <%@include file="nav.jsp" %>
        <div class="jumbotron" >
            <h3 style="text-align: center">Bem vindo ${usuario.login}</h3>
        </div>
    </body>
</html>