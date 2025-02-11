package servidor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class crearCookie
 */
@WebServlet("/crearCookie")
public class crearCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String nombre = (String) session.getAttribute("nombre");
		String valor = request.getParameter("aprendiz");
	
		Cookie cookieJ = new Cookie( nombre,valor);
		cookieJ.setMaxAge(600); 
		response.addCookie(cookieJ);    
		
		
		
		response.sendRedirect("index.jsp");
		
	}

}
