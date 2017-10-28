<%-- 
    Document   : index
    Created on : 14/11/2016, 20:45:49
    Author     : Matheus
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../assets.jsp" %>
        <title>Pessoas - Listagem</title>
    </head>
    <body>
        <%@include file="../nav.jsp" %>
        <div class="container">
            <div class="jumbotron">
                <h1 style="text-align: center">Listagem de Pessoas</h1>
            </div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/pessoas?a=new">Novo</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Idade</th>
                        <th>Telefone</th>
                        <th>Rua</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pessoa" items="${ pageContext.request.getAttribute(\"pessoas\") }" >

                        <tr>
                            <td><a href="${pageContext.request.contextPath}/pessoas?a=edit&id=${pessoa.id}">${pessoa.nome}</a></td>
                            <td>${pessoa.idade}</td>
                            <td>${pessoa.telefone}</td>
                            <td>${pessoa.endereco.rua}</td>
                        </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
