<%-- 
    Document   : form
    Created on : 28/09/2017, 19:56:37
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST" action="${pageContext.request.contextPath}/admin?a=${action}" >
    <%--<input type="hidden" name="id" value="${usuario.id}">--%>
    <div class="container">    
        <div class="row">
            <div class="form-group">
                <div class="col-sm-2">
                    <label for="usuario.login">Login:</label>
                    <input type="text" class="form-control" name="usuario.login" value="${usuario.login}" required>
                </div>
                <div class="col-sm-2">
                    <label for="usuario.senha">Senha:</label>
                    <input type="text" class="form-control" name="usuario.senha" value="${usuario.senha}" required>
                </div>
            </div>
        </div>
        <div class="row">
        <div class="col-sm-1" style="margin-top: 20px">
            <input type="submit" class="btn btn-success" name="commit" value="Salvar">
        </div>
        <div class="col-sm-1" style="margin-top: 20px">
            <c:if test="${action eq \"update\"}">
                 <a class="btn btn-danger" href="${pageContext.request.contextPath}/mesas?a=delete&id=${usuario.id}">Deletar</a>
            </c:if>
        </div>
        </div>        
    </div>
</form>
