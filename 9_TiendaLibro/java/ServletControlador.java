package practica;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

/**
 * Servlet implementation class ServletControlador
 */
@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		LibrosBD.cargarDatos();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recupera la sesion actual o crea una nueva si no existe.
		HttpSession session = request.getSession(true);
		// Recupera el carrito de la sesion actual
		List<ElementoPedido> elCarrito = (ArrayList<ElementoPedido>) session.getAttribute("carrito");
		// Determina a que pagina jsp redirigira
		String nextPage = "";
		String todo = request.getParameter("todo");
		if (todo == null) {
			// Primer acceso- redireccion a order.jsp
			nextPage = "/order.jsp";
		} else if (todo.equals("add")) {
			// Mandado por order.jsp con los parametros idLibro y cantidad.
			// crea un elementoPedido y lo a~nade al carrito
			ElementoPedido nuevoElementoPedido = new ElementoPedido(Integer.parseInt(request.getParameter("idLibro")),
					Integer.parseInt(request.getParameter("cantidad")));
			if (elCarrito == null) { // el carrito esta vaco
				elCarrito = new ArrayList<>();
				elCarrito.add(nuevoElementoPedido);
				// enlaza el carrito con la sesion
				session.setAttribute("carrito", elCarrito);
			} else {
				// Comprueba si el libro esta en el carrito
				// si lo esta actualiza la cantidad
				// si no lo a~nade
				boolean encontrado = false;
				Iterator iter = elCarrito.iterator();
				while (!encontrado && iter.hasNext()) {
					ElementoPedido unElementoPedido = (ElementoPedido) iter.next();
					if (unElementoPedido.getIdLibro() == nuevoElementoPedido.getIdLibro()) {
						unElementoPedido
								.setCantidad(unElementoPedido.getCantidad() + nuevoElementoPedido.getCantidad());
						encontrado = true;
					}
				}
				if (!encontrado) { // Lo a~nade al carrito
					elCarrito.add(nuevoElementoPedido);
				}
			}
			// Vuelve a order.jsp para mas pedidos
			nextPage = "/order.jsp";
		} else if (todo.equals("remove")) {
			// Enviado por order.jsp con el parametro indiceElemento
			// Borra el elemento indiceElemento del carrito
			int indiceCarrito = Integer.parseInt(request.getParameter("indiceElemento"));
			elCarrito.remove(indiceCarrito);
			// Vuelve a order.jsp para mas pedidos
			nextPage = "/order.jsp";
		} else if (todo.equals("checkout")) {
			// Enviado por order.jsp.
			// Calcula el precio total de todos los elementos del carrito
			float precioTotal = 0;
			int cantidadTotalOrdenada = 0;
			for (ElementoPedido item : elCarrito) {
				float precio = item.getPrecio();
				int cantidadOrdenada = item.getCantidad();
				precioTotal += precio * cantidadOrdenada;
				cantidadTotalOrdenada += cantidadOrdenada;
			}
			// Formatea el precio con dos decimales
			StringBuilder sb = new StringBuilder();
			Formatter formatter = new Formatter(sb);
			formatter.format("%.2f", precioTotal);
			// Coloca el precioTotal y la cantidadTotal en el request
			request.setAttribute("precioTotal", sb.toString());
			request.setAttribute("cantidadTotal", cantidadTotalOrdenada + "");
			// Redirige a checkout.jsp
			nextPage = "/checkout.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}
}
