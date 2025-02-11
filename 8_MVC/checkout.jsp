
<%-- P´agina de Checkout --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, practica.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Libreria MVC Checkout</title>
</head>
<body>
<h2>Libreria MVC - Checkout</h2>
<hr /><br />
<p><strong>Has comprado los siguientes libros:</strong></p>
<table border="1" cellspacing="0" cellpadding="5">
<tr>
<th>T´ıtulo</th>
<th>Autor</th>
<th>Precio</th>
<th>Cantidad</th>
</tr>
<%
// Scriptlet 1: Muestra los elementos del carrito
List<ElementoPedido> cesta =
(List<ElementoPedido>) session.getAttribute("carrito");
for (ElementoPedido item : cesta) {
%>
<tr>
<td><%= item.getTitulo()%></td>
<td><%= item.getAutor()%></td>
<td align="right"><%= item.getPrecio()%></td>
<td align="right"><%= item.getCantidad()%></td>
</tr>
<%
} // for
session.invalidate();
%>
<tr>
<th align="right" colspan="2">Total</th>
<th align="right"><%= request.getAttribute("precioTotal")%></th>
<th align="right"><%= request.getAttribute("cantidadTotal")%></th>
</tr>
</table>
<br />
<a href="shopping">Pulsa aqu´ı para comprar m´as libros</a>
</body>
</html>