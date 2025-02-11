package servidor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class recepciones
 */
@WebServlet("/recepciones")
public class recepciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*https://www.youtube.com/watch?v=36cQjEEvK30
	 * 1 Crear el metodo de conexion (jdbc)
	 * 2 Crear el obejto conexion 
	 * (Eclipse sugiere 2 try-catch para clase y conexion)
	 * 3 Obtenemos los parametro desde donde nacen
	 * 4 Creamos una sentencia prepradas
	 * 5 Resultado
	 * 6 Preparar fallos de conexion
	 */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public recepciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 Para la contraseña:
    		• Debe introducir al menos 12 caracteres.
    		• Sólo puede introducir 20 caracteres.
    		• Debe introducir al menos 1 letra mayúscula.
    		• Debe introducir al menos 1 letra minúscula.
    		• Debe introducir al menos 1 carácter especial.
    		• Se permiten los siguientes caracteres especiales -_/()$%&?!+*#<>=
    		• Debe introducir al menos 1 carácter numérico
		 */
		String nombre = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		String error = " ";
		String errorGeneral = "false";
		
		// 3. Obtener los parámetros
		if(nombre.length() < 6 || nombre.length() > 30) {
			error += "Usuario incorrecto (1)<br>";
			errorGeneral = "true";
		}
		if(nombre.isEmpty()) {
			error += "Usuario incorrecto (2)<br>";
			errorGeneral = "true";
		}
		if(nombre.contains(" ")) {
			error += "Usuario incorrecto (3)<br>";
			errorGeneral = "true";
		}
		
		if(!nombre.matches("[A-Za-z0-9-_]+")) {
			error += "Usuario incorrecto (4)<br>";
			errorGeneral = "true";
		}
		
		//CONTRASEÑAS
		if(clave.length() < 12 || clave.length() > 20) {
			error += "Introduzca una clave valida(1)<br>";
			errorGeneral = "true";
		}
		
		if (!clave.matches(".*[A-Z].*")) {
		    error += "Introduzca una clave valida(2)<br>";
		    errorGeneral = "true";
		}

		if (!clave.matches(".*[a-z].*")) {
		    error += "Introduzca una clave valida(3)<br>";
		    errorGeneral = "true";
		}

		if (!clave.matches(".*[0-9].*")) {
		    error += "Introduzca una clave valida(4)<br>";
		    errorGeneral = "true";
		}

		if (!clave.matches(".*[-_/()$%&?!+*#<>=].*")) {
		    error += "Introduzca una clave valida(5)<br>";
		    errorGeneral = "true";
		}

		
		if(errorGeneral == "true") {
			request.getSession().setAttribute("error", error);
			response.sendRedirect("index.jsp");
		}else {
	
		
		
		
		request.getSession().setAttribute("usuario", nombre);
		request.getSession().setAttribute("clave", clave);

    	
    	try {
            // 1. Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Crear la conexión
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/usuarios", "admin1", "123456");

            
            // 4. Crear la consulta 
            String sql = "SELECT * FROM usuario WHERE nombre = ? AND clave = ?";
            // 5. Crear consulta preparada
            PreparedStatement consulta = conexion.prepareStatement(sql);
            consulta.setString(1, nombre);
            consulta.setString(2, clave);

            // 5. Ejecutar la consulta
            ResultSet resultado = consulta.executeQuery();

            // 6. Procesar el resultado
            if (resultado.next()) {
            	//Guardar sesion
            	HttpSession session = request.getSession();
            	session.setAttribute("nombre", nombre);
            	// Login exitoso
            	 response.sendRedirect("wow.jsp");
            } else {
                // Login fallido
            	response.getWriter().append("Fallo de conexion");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

	}


