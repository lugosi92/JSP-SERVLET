
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FORMULARIO</title>

<style>
    body {
        font-family: Arial, sans-serif;;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .formulario {
    	margin-top: 100px;
        border: 1px solid #ccc;
        border-radius: 10px;
        padding: 20px;
    }

</style>

</head>
<body>
 
  <!-- 
  	1. Los parametros nacen del formulario
  	2. Los parametros son recogidos en el servlet 
  	3. Si su validacion es correcta se abre una sesion y se gurada como atributo
  	4. Si su validacion INCORRECTA se imprime fallo en rojo
  	4. Se recupera el atributo en el formulario
  	 -->
  	 
  	   <!-- Recoger atributos -->
    <%
    	String error = (String) session.getAttribute("error");
    	String errorGeneral = (String) session.getAttribute("errorGeneral");
    
		String nombre = (String) session.getAttribute("nombre");
        <p>${nombre}</p>

    	String clave = (String) session.getAttribute("clave");
    	String genero = (String) session.getAttribute("genero");
    	String[] pais = (String[]) session.getAttribute("pais");
    	String fechaNacimiento = (String) session.getAttribute("fechaNacimiento");
    	String acepto = (String) session.getAttribute("acepto");
    	String comentario = (String) session.getAttribute("comentario");
    %>
    
    <%
    	if(errorGeneral == "true" || errorGeneral == null){
    %>
     <!-- Mostrar error en rojo -->
	    <p style="color: red"><%= error %></p>
 	
	    
 <!-- AÑADIMOS enctype para que se envien los parametros de forma multipart/form-data -->
<form action="EnviaYRecibe" method="post" enctype="multipart/form-data">
  
    <div class = "formulario">
    
        <label for="nombre">Nombre:</label>
        <input  value = "<% if(nombre != null) out.print(nombre); %>"
        type="text" id="nombre" name="nombre">

  		<br></br>
        <label for="clave">Clave:</label>
        <input value = "<% if(clave != null) out.print(clave); %>"
        type="password" id="clave" name="clave">
        
        <br></br>
        <label for="genero">Genero:</label>
        <select name="genero">
        	<option value="">Seleccione un genero</option>
        	<option value="masculino" <%= (genero != null && genero.equals("masculino")) ? "selected" : "" %>>Masculino</option>
			<option value="femenino" <%= (genero != null && genero.equals("femenino")) ? "selected" : "" %>>Femenino</option>
        </select>
        
        
        <br></br>
        <label for="fechaNacimiento">fechaNacimiento:</label>
        <input value = "<% if(fechaNacimiento != null) out.print(fechaNacimiento); %>"
        type="text" id="fechaNacimiento" name="fechaNacimiento">
       
        
        <br></br>
       <label for="pais">Pais:</label>
		<select MULTIPLE name="pais[]">
		    <option value="">Seleccione un país</option>
		    <option value="españa" <%= (pais != null && java.util.Arrays.asList(pais).contains("españa")) ? "selected" : "" %>>España</option>
		    <option value="holanda" <%= (pais != null && java.util.Arrays.asList(pais).contains("holanda")) ? "selected" : "" %>>Holanda</option>
		    <option value="alemania" <%= (pais != null && java.util.Arrays.asList(pais).contains("alemania")) ? "selected" : "" %>>Alemania</option>
		    <option value="belgica" <%= (pais != null && java.util.Arrays.asList(pais).contains("belgica")) ? "selected" : "" %>>Bélgica</option>
		</select>

        
        <br></br>
        <label for="acepto">Acepto:</label>
        <input value = "acepto" <%= (acepto != null && acepto.equals("acepto")) ? "checked" : "" %>
        type="checkBox" id="acepto" name="acepto">
        
        <br></br>
        <label for="comentario">Comentario:</label>
		<textarea id="comentario" name="comentario" rows="4" cols="50">
		    <% if(comentario != null) { out.print(comentario); } %>
		</textarea>

     
     	<br></br>
        <label for="imagen">Imagen:</label>
               <input type="file" id="imagen" name="imagen">
        
     	<br></br>
        <button type="submit">Enviar</button>
       </div>
       
       <%
    	}else{
    	%>
       
       <p style="color: blue; padding: 100px;">Nombre: <%= nombre %></p><br>
       <p style="color: blue; padding: 100px;">Clave: <%= clave %></p><br>
       <p style="color: blue; padding: 100px;">Genero: <%= genero %></p><br>
       <p style="color: blue; padding: 100px;">Fecha Nacimiento: <%= fechaNacimiento %></p><br>
       <p style="color: blue; padding: 100px;">Acpetar: <%= acepto %></p><br>
       <p style="color: blue; padding: 100px;">Comentario: <%= comentario %></p><br>
       <%
    	}
    	%>
       
	</form>
        
</body>
</html>