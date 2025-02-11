<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<% 
	String nombre = (String) session.getAttribute("nombre");
	String contenido = (String) session.getAttribute("QueryString");
	%>
	
	<p>Nombre: </p> 
	<%= nombre%>
	
	<p>Contenido: </p> 
	<%= contenido%>
</body>
</html>