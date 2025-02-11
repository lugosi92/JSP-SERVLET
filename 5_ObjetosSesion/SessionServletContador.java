package sesiones;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServletContador
 */
@WebServlet("/SessionServletContador")
public class SessionServletContador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServletContador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpSession laSesion = request.getSession();
		
		Integer contador = (Integer) laSesion.getAttribute("contador");
	
		if(contador == null) {
			contador = 1;
		}else {
			contador++;
		}
		
		String id = (String) laSesion.getId();
		Date creacion = new Date(laSesion.getCreationTime());
		Date ultimoAcesso = new Date(laSesion.getLastAccessedTime());
		Integer tiempo = laSesion.getMaxInactiveInterval();
		
		
		laSesion.setAttribute("contador", contador);
		laSesion.setAttribute("id", id);
		laSesion.setAttribute("creacion", creacion);
		laSesion.setAttribute("ultimoAcesso", ultimoAcesso);
		laSesion.setAttribute("tiempo", tiempo);
	}

	

}
