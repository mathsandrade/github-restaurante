<%-- 
    Document   : form
    Created on : 10/08/2017, 19:19:19
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form method="POST" action="${pageContext.request.contextPath}/mesas?a=${action}" >
    <input type="hidden" name="id" value="${mesa.id}">
    <div class="container">    
        <div class="row">
            <div class="form-group">
                <div class="col-sm-2">
                    <label for="mesa.numero">Numero:</label><span style="color: red">${mensagem}</span>
                    <input type="text" class="form-control" name="mesa.numero" value="${mesa.numero}" required>
                </div>
                <div class="col-sm-2">
                    <c:if test="${action eq \"update\"}">
                        <label for="mesa.status">Status:</label>
                        <select class="form-control" name="mesa.status">
                            <option value="DISPONIVEL">DISPONIVEL</option>
                            <option value="OCUPADA">OCUPADA</option>
                        </select>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row">
        <div class="col-sm-1" style="margin-top: 20px">
            <input type="submit" class="btn btn-success" name="commit" value="Salvar">
        </div>
        <div class="col-sm-1" style="margin-top: 20px">
            <c:if test="${action eq \"update\"}">
                 <a class="btn btn-danger" href="${pageContext.request.contextPath}/mesas?a=delete&id=${mesa.id}">Deletar</a>
            </c:if>
        </div>
        </div>        
    </div>
</form>