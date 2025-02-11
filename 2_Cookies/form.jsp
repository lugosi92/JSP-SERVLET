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


<form action ="creaCookie" method="post">

<div class = "formulario">
	  <label for="nombre">Nombre:</label>
        <input  
        type="text" id="nombre" name="nombre">

  		<br></br>
        <label for="clave">Clave:</label>
        <input 
        type="password" id="clave" name="clave">
        
       <br></br>
       <label for="pais">Pais:</label>
		<input 
        type="text" id="pais" name="pais">
        
        <br></br>
        <button type="submit">Enviar</button>
      
</div>
</form>
</body>
</html>