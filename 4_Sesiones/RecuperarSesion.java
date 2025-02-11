package sesiones;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecuperarSesion
 */
@WebServlet("/RecuperarSesion")
public class RecuperarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession laSesion = request.getSession();

		Integer entero = (Integer) laSesion.getAttribute("entero");
		
		request.setAttribute("entero", entero);
		
		
		response.sendRedirect("mostrar.jsp");
		
		
	}

}
