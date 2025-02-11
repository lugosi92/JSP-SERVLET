<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%String error = (String) session.getAttribute("error");%>	
	


<p style="color: red"><%= error %></p>


<form action = "recepciones" method = "post">
	
	<label for="usuario">Usuario:</label>
	<input type="text" name="usuario">
	
	<br>
	<label for="clave">Contraseña:</label>
	<input type="text" name="clave">
	
	<br>
	<button type="submit">Enviar</button>
	

	</form>
</body>
</html>