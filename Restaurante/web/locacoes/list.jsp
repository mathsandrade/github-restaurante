<%-- 
    Document   : index
    Created on : 21/11/2016, 18:10:06
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ page import="java.text.*,java.util.*" session="false"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="60" />        <%-- COMANDO UTILIZADO PARA DAR REFRESH NA PAGINA DE X EM X TEMPO--%>
        <%@include file="../assets.jsp" %>
        <title>Locações - Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Listagem de Locações</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/locacoes?a=validarPessoa">Novo</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>CPF</th>
                        <th>Nome</th>
                        <th>Numero da Mesa</th>
                        <th>Data/Hora</th>         
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="locacao" items="${pageContext.request.getAttribute(\"locacoes\")}" >
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/locacoes?a=edit&locacao.id=${locacao.id}">${locacao.pessoa.cpf}</a></td>
                            <td>${locacao.pessoa.nome}</td>
                            <td>${locacao.mesa.numero}</td>
                            <td> <fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${locacao.data.time}" type="both"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>