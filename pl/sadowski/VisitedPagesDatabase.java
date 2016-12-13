package pl.sadowski;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.sadowski.interfaces.VisitedPages;

public class VisitedPagesDatabase implements VisitedPages {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/link_visited";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWD = "";
	Connection conn;
	
	public VisitedPagesDatabase() {
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
	
	private long insertLink(String link) {

		PreparedStatement stmt = null;
		long id = -1;
		try {
			stmt = conn
					.prepareStatement("insert into visited (link) " + "values(?)");

			stmt.setString(1, link);
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
	
	private boolean containsLink(String link) {
		boolean found = false;
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("select * from visited where link like ?");
			stmt.setString(1, link);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				found = true;
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
		return found;
	}

	@Override
	public boolean pageAlreadyVisited(URL pageURL) {
		return containsLink(pageURL.toString());
	}

	@Override
	public void addVisitedPage(URL pageURL) {
		insertLink(pageURL.toString());
	}

}
