package sesiones;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreacionSesion1
 */
@WebServlet("/CreacionSesion")
public class CreacionSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreacionSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//INVALIDAR SI EXISTE ALGUNA SESION
				if(request.getSession(false) != null) {
					request.getSession().invalidate();
				}
				
				//CREAR SESION - RESETEAR
				HttpSession laSesion = request.getSession();	
				
				laSesion.setAttribute("entero", 123);
				laSesion.setAttribute("real", 45.68);
				laSesion.setAttribute("texto", "Hola");
				laSesion.setAttribute("fecha", "12-10-2024");
				
				Map<String, Boolean> semaforo = new HashMap<>();
				semaforo.put("Verde", true);
				semaforo.put("Amarrillo", true);
				semaforo.put("Rojo", false);
				laSesion.setAttribute("semaforo", semaforo);
				
				Punto punto = new Punto(13,42);
				laSesion.setAttribute("punto", punto);
				
				response.sendRedirect("RecuperarSesion");
	}

}
