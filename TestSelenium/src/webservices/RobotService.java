package webservices;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pruebasRobots.RobotPC;
import webservices.Resultado;;

@RestController
public class RobotService {

	private static final String HOST_BD = "localhost";
	private static final String NOMBRE_BD = "testSelenium";
	private static final String USER_BD = "admin";
	private static final String PWD_BD = "pwd";
	
	private static final String observacionesOK = new String();
	private static final String observacionesERROR = new String();

	protected Connection conectarBD(String host, String nombreBD, String user, String pwd)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager
				.getConnection("jdbc:mysql://" + host + "/" + nombreBD + "?user=" + user + "&password=" + pwd);
	}

	@GetMapping(path = "/aniadirCarrito")
	@ResponseBody
	public ResponseEntity<String> aniadirCarrito(@RequestParam("busqueda") String cadenaBusqueda) throws Exception {
		Connection con = null;
		try {

			// TODO Conectar BD
			con = conectarBD(HOST_BD, NOMBRE_BD, USER_BD, PWD_BD);

			// TODO Llamar robot!
			RobotPC r = new RobotPC();
			r.ejecutarRobot(new String[] { "C:\\Users\\Teknei\\Desktop\\Java\\TestSelenium\\resources\\properties",
					cadenaBusqueda });

			contarOk(con, cadenaBusqueda, Resultado.ok, new String(""));
			contarError(con, cadenaBusqueda, Resultado.error, new String(""));
			insertStats(con, cadenaBusqueda, Resultado.ok, new String(""));

			return ResponseEntity.ok()
					.body("TODO aniadir al carrito el primer producto que salga por '" + cadenaBusqueda + "'. "
							+ "Contar error = "+contarError(con, cadenaBusqueda, Resultado.error, observacionesERROR)+". "
									+ " Contar ok = "+contarOk(con, cadenaBusqueda, Resultado.ok, observacionesOK));
		}

		catch (Exception e) {

			contarOk(con, cadenaBusqueda, Resultado.ok, e.getMessage());
			contarError(con, cadenaBusqueda, Resultado.error, e.getMessage());
			insertStats(con, cadenaBusqueda, Resultado.error, e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo no fue bien :( " + e);
		}

		finally {
			if (con != null) {
				con.close();
				con = null;
			}
		}
	}

	private String contarError(Connection con, String cadenaBusqueda, Resultado resultado, String observacionesERROR)
			throws Exception {
		// TODO Auto-generated method stub

		PreparedStatement stmt = null;

		try {

			stmt = con.prepareStatement(
					"SELECT count(*) FROM stats " + "WHERE busqueda = ? AND resultado = 'error'");

			int paramIndex = 1;

			stmt.setString(paramIndex++, cadenaBusqueda);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Resultado ERROR con busqueda " + cadenaBusqueda + " = " + rs.getString(1));
				observacionesERROR = rs.getString(1);
			}

			return observacionesERROR;
		}

		catch (Exception e) {

			throw new Exception("Error al realizar la busqueda", e);

		}

		finally {

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

		}

	}

	private String contarOk(Connection con, String cadenaBusqueda, Resultado resultado, String observacionesOK)
			throws Exception {
		// TODO Auto-generated method stub

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"SELECT count(*) FROM stats " + "WHERE busqueda = ? AND resultado = 'ok'");

			int paramIndex = 1;

			stmt.setString(paramIndex++, cadenaBusqueda);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println("Resultado OK con busqueda " + cadenaBusqueda + " = " + rs.getString(1));

				observacionesOK = rs.getString(1);
			}

			return observacionesOK;
			
		}

		catch (Exception e) {

			throw new Exception("Error al realizar la busqueda", e);

		}

		finally {

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

		}

	}

	private void insertStats(Connection con, String cadenaBusqueda, Resultado resultado, String observaciones)
			throws Exception {
		// TODO Auto-generated method stub

		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO stats(busqueda, resultado, observaciones)" + " VALUES (?,?,?)");

			int paramIndex = 1;

			stmt.setString(paramIndex++, cadenaBusqueda); // 1
			stmt.setString(paramIndex++, resultado.toString()); // 2
			stmt.setString(paramIndex++, observaciones); // 3

			int numFilas = stmt.executeUpdate();
			System.out.println("Filas Insertadas = " + numFilas);

		}

		catch (Exception e) {

			throw new Exception("Error en INSERT stats", e);

		}

		finally {

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

		}

	}
}
