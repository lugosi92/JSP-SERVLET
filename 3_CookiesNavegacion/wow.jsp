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

String aprendizElegido = null;
int contadorVisitas = 0;

Cookie[] cookieJ = request.getCookies(); 

for( Cookie cookies : cookieJ) {
	String name = cookies.getName();
	
	if(name.equals(nombre)){
		aprendizElegido = cookies.getValue();
	}
	if(name.equals("contador_" + nombre)){
		contadorVisitas= Integer.parseInt(cookies.getValue());
	}
}
contadorVisitas++;


Cookie contadorCookie = new Cookie("contador_"+nombre, String.valueOf(contadorVisitas));
contadorCookie.setMaxAge(600); 
response.addCookie(contadorCookie);
%>

<h1>BIENVENDIO HECHIZO</h1>

<form action="crearCookie" method="post">

    <input type="radio" name="aprendiz" value="Alfarero" id="Alfarero"
    <%= "Alfarero".equals(aprendizElegido) ? "checked" : "" %>>
    <label for="Alfarero">Alfarero</label>
    
    <input type="radio" name="aprendiz" value="Brujo" id="Brujo"
    <%= "Brujo".equals(aprendizElegido) ? "checked" : "" %>>
    <label for="Brujo">Brujo</label>
    
    <input type="radio" name="aprendiz" value="Curtidor" id="Curtidor"
    <%= "Curtidor".equals(aprendizElegido) ? "checked" : "" %>>
    <label for="Curtidor">Curtidor</label>
    
    <input type="submit" value="Desconectar">
   
   <p> Esta pagina ha sido accedida <b><%= contadorVisitas%></b> veces.</p>
</form>
</body>
</html>