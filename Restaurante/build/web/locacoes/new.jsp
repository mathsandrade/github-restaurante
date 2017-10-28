<%-- 
    Document   : new
    Created on : 20/11/2016, 19:57:21
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
                <h1 style="text-align: center">Inicio de uma Locação</h1>
            </div>
            <form action="${pageContext.request.contextPath}/locacoes" method="post">
                <input type="hidden" name="pessoa.id" value="${sessionScope["locacao"].pessoa.id}">
                <input type="hidden" name="a" value="create">
                    <div class="row">
                        <div class="col-sm-2">
                            <div class="form-group">
                                <label for="mesa.id">Mesas Disponiveis: </label>
                                    <select class="form-control" name="mesa.id">

                                        <c:forEach var="mesa" items="${mesas}">

                                        <option value="${mesa.id}">${mesa.numero}</option>

                                        </c:forEach>

                                    </select>
                            </div>
                        </div>
                    </div>
                <input type="submit" class="btn btn-success" name="commit" value="Salvar" onclick="NProgress.start()">
            </form>
        </div>
    </body>
</html>