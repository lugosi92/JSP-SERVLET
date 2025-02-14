<%-- P´agina de Ordenes --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" import="java.util.*, practica.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pedido a la Librer´ıa MVC</title>
</head>
<body>
<h2>Librer´ıa MVC</h2>
<hr /><br />
<p><strong>Elige un libro y la cantidad:</strong></p>
<form name="AnyadirForm" action="shopping" method="POST">
<input type="hidden" name="todo" value="add">
T´ıtulo: <select name=idLibro>
<%
// Scriptlet 1: Carga los libros en el select.
for (int i = 0; i < LibrosBD.tamanyo(); i++) {
out.println("<option value=’" + i + "’>");
out.println(LibrosBD.getTitulo(i) + " | " + LibrosBD.getAutor(i)
+ " | " + LibrosBD.getPrecio(i));
out.println("</option>");
}
%>
</select>
Cantidad: <input type="text" name="cantidad" size="10" value="1">
<input type="submit" value="A~nadir a la cesta">
</form>
<br /><hr /><br />
<%
// Scriptlet 2: Chequea si la cesta esta vacia.
List<ElementoPedido> cesta =
(List<ElementoPedido>) session.getAttribute("carrito");
if (cesta != null && cesta.size() > 0) {
%>
<p><strong>Tu cesta contiene:</strong></p>
<table border="1" cellspacing="0" cellpadding="5">
<tr>
<th>T´ıtulo</th>
<th>Autor</th>
<th>Precio</th>
<th>Cantidad</th>
<th>&nbsp;</th>
</tr>
<%
// Scriptlet 3: Muestra los libros del carrito.
for (int i = 0; i < cesta.size(); i++) {
ElementoPedido elementoPedido = cesta.get(i);
%>
<tr>
<form name="borrarForm" action="shopping" method="POST">
<input type="hidden" name="todo" value="remove">
<input type="hidden" name="indiceElemento" value="<%= i %>">
<td><%= elementoPedido.getTitulo() %></td>
<td><%= elementoPedido.getAutor() %></td>
<td align="right"><%= elementoPedido.getPrecio() %></td>
<td align="right"><%= elementoPedido.getCantidad() %></td>
<td><input type="submit" value="Eliminar de la cesta"></td>
</form>
</tr>
<%
}
%>
</table><br />
<form name="checkoutForm" action="shopping" method="POST">
<input type="hidden" name="todo" value="checkout">
<input type="submit" value="Checkout">
</form>
<%
} // if
%>
</body>
</html>