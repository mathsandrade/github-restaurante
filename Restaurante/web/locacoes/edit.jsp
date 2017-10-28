<%-- 
    Document   : edit
    Created on : 21/11/2016, 18:13:09
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../assets.jsp" %>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Cancelamento de Locações</h1>
            </div>
            <form method="POST" action="${pageContext.request.contextPath}/locacoes?a=${action}" >
                <input type="hidden" name="locacao.id" value="${locacao.id}">
                <input type="hidden" name="mesa.id" value="${locacao.mesa.id}">
                <input type="hidden" name="pessoa.id" value="${locacao.pessoa.id}">
                <input type="hidden" name="locacao.data" value="${locacao.data}">
                <div class="form-group">
                    <label for="pessoa.nome">Nome:</label>
                    <input type="text" class="form-control" name="pessoa.nome" value="${locacao.pessoa.nome}" readOnly>
                </div>
                <div class="form-group">
                    <label for="pessoa.cpf">CPF:</label>
                    <input type="text" class="form-control" name="pessoa.cpf" value="${locacao.pessoa.cpf}" readOnly>
                </div>
                <div class="form-group">
                    <label for="mesa.numero">Numero Mesa:</label>
                    <input type="text" class="form-control" name="mesa.numero" value="${locacao.mesa.numero}" readOnly>
                </div>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/locacoes?a=cancelar&locacao.id=${locacao.id}">Cancelar</a>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/locacoes?a=concluir&locacao.id=${locacao.id}">Concluir</a>
        </div>
    </body>
</html>