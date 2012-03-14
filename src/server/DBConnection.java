package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * A wrapper around the java sql framework
 * 
 * @author Runar B. Olsen <runar.b.olsen@gmail.com>
 */
public class DBConnection {

	private static Logger LOGGER = Logger.getLogger("DBConnection");	

	private Connection connection;
	
	/**
	 * Create a DBConnection
	 * 
	 * @param p parameters with atleast fp.database.url, fp.database.user and
	 * 	fp.database.pass
	 * @throws SQLException on a connection error
	 */
	public DBConnection(Properties p) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
	 			
			connection = DriverManager.getConnection(
				p.getProperty("fp.database.url"),
				p.getProperty("fp.database.user"),
				p.getProperty("fp.database.pass")
			);
		} catch(ClassNotFoundException e) {
			LOGGER.severe("Missing MySQL connector");
			LOGGER.severe(e.toString());
			System.exit(1);
		}
	}
	
	/**
	 * Preforms a query returning the ResultSet
	 * 
	 * @param query
	 * @return
	 */
	public ResultSet preformQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		return st.executeQuery(query);
	}
	
}