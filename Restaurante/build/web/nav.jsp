<%-- 
    Document   : nav
    Created on : 14/11/2016, 22:27:18
    Author     : Matheus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>    
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
        <a class="navbar-brand" href="index.jsp"><img src="#" alt="G Logo" /></a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="${pageContext.request.contextPath}/pessoas">Pessoa</a></li>
        <li><a href="${pageContext.request.contextPath}/locacoes">Locações</a></li>
        <li><a href="${pageContext.request.contextPath}/mesas">Mesas</a>
            <c:if test="${sessionScope.usuario.perfilAcesso eq \"admin\"}">
        <li><a href="${pageContext.request.contextPath}/admin">Admnistrador</a></li>
            </c:if>
      </ul>
    </div>
  </div>
</nav>








<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Gominho's</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="${pageContext.request.contextPath}/pessoas">Pessoa</a></li>
      <li><a href="${pageContext.request.contextPath}/locacoes">Locações</a></li>
      <li><a href="${pageContext.request.contextPath}/mesas">Mesas</a>
      <li><a href="${pageContext.request.contextPath}">#Usuarios</a></li>
    </ul>
  </div>
</nav>--%>