package pl.sadowski;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.sadowski.interfaces.OutputSaver;

public class DatabaseSaver implements OutputSaver {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/link_databse";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWD = "";
	Connection conn;
	
	DatabaseSaver() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private long insertLog(String message) {

		PreparedStatement stmt = null;
		long id = -1;
		try {
			stmt = conn
					.prepareStatement("insert into log (message) " + "values(?)");

			stmt.setString(1, message);
			stmt.executeUpdate();

			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return id;
	}

	@Override
	public void append(String toSave) {
		insertLog(toSave);
	}

	@Override
	public void append(String[] toSave) {
		for(String s : toSave) {
			insertLog(s);
		}
	}

}
