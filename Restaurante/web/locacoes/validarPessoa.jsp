<%-- 
    Document   : pessoa
    Created on : 20/11/2016, 21:17:32
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
                <h1 style="text-align: center">Informe seu CPF</h1>
            </div>
            <form action="${pageContext.request.contextPath}/locacoes" method="get">
              <div class="row">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <input type="hidden" name="a" value="new">
                            <label for="pessoa.cpf">CPF:</label><span style="color: red"> ${mensagem}</span>
                            <input type="text" class="form-control cpf" name="pessoa.cpf" value="${pessoa.cpf}" required>
                        </div>
                        <div class="col-sm-6">
                            <label for="locacao.data">Data/Hora:</label>
                            <input id="verificaNome" type="datetime-local" class="form-control" name="locacao.data" required>
                        </div>
                    </div>
              </div>
              <div class="row">
                    <div class="col-sm-1" style="margin-top: 20px">
                       <input type="submit" class="btn btn-success" name="commit" value="Continuar">
                    </div>
              </div>
            </form>
        </div>
    </body>
</html>