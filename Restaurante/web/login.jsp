<%-- 
    Document   : login
    Created on : 27/09/2017, 23:28:13
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="css/styleLogin.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
</head>

<body>
  <div class="login">
	<h1>Login</h1>
    <form action="${pageContext.request.contextPath}/usuarios" method="POST">
    	<input type="text" name="usuario.login" placeholder="Username" required="required" />
        <input type="password" name="usuario.senha" placeholder="Password" required="required" />
        <button type="submit" class="btn btn-primary btn-block btn-large">Let me in.</button>
    </form>
</div>
  
    <script  src="js/index.js"></script>

</body>
</html>

