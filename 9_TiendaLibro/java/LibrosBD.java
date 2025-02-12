package practica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * LibrosBD Encapsula la comunicación con la base de datos. Almacena títulos,
 * autores y precios en tres arrays.
 */
public class LibrosBD {
	private static final int MAX_SIZE = 6;
	private static String[] titulos = new String[MAX_SIZE];
	private static String[] autores = new String[MAX_SIZE];
	private static float[] precios = new float[MAX_SIZE];

	public static void cargarDatos() {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Paso 1: Cargar el driver JDBC.
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// Paso 2: Conectarse a la base de datos utilizando la clase Connection.
			String userName = "root";
			String password = "";

			// URL de la base de datos (equipo, puerto, base de datos).
			String url = "jdbc:mysql://localhost/tiendalibros";
			conn = DriverManager.getConnection(url, userName, password);

			// Paso 3: Crear una sentencia SQL utilizando un objeto de tipo Statement.
			stmt = conn.createStatement();

			// Paso 4: Ejecutar la sentencia SQL a través del objeto Statement.
			String sqlStr = "SELECT * FROM libros;";
			ResultSet rset = stmt.executeQuery(sqlStr);

			// Paso 5: Procesar el conjunto de registros resultante utilizando ResultSet.
			int count = 0;
			while (rset.next() && count < MAX_SIZE) {
				titulos[count] = rset.getString("titulo"); // Corregido: antes estaba intercambiado con "autor".
				autores[count] = rset.getString("autor");
				precios[count] = (float) rset.getDouble("precio");
				count++;
			}

			// Cerramos los recursos abiertos.
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** Devuelve el número de libros almacenados. */
	public static int tamanyo() {
		return titulos.length;
	}

	/** Devuelve el título del libro con el ID especificado. */
	public static String getTitulo(int idLibro) {
		return titulos[idLibro];
	}

	/** Devuelve el autor del libro con el ID especificado. */
	public static String getAutor(int idLibro) {
		return autores[idLibro];
	}

	/** Devuelve el precio del libro con el ID especificado. */
	public static float getPrecio(int idLibro) {
		return precios[idLibro];
	}
}
