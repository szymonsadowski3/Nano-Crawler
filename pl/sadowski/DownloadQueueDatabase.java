package pl.sadowski;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pl.sadowski.interfaces.DownloadQueue;

public class DownloadQueueDatabase implements DownloadQueue {
	private static final String DB_URL = "jdbc:h2:tcp://localhost/~/link_queue";
	private static final String DB_USER = "admin";
	private static final String DB_PASSWD = "";
	Connection conn;
	
	DownloadQueueDatabase() {
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
					.prepareStatement("insert into queue (link) " + "values(?)");

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
	
	private int getCount() {
		int number = -1;

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("select count(*) from queue");
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				number = rs.getInt(1);
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
		return number;
	}

	@Override
	public void addPage(URL pageURL) {
		insertLink(pageURL.toString());
	}

	@Override
	public boolean isEmpty() {
		return getCount()==0;
	}

	@Override
	public URL getNextPage() {
		String result = getTop1();
		deleteTop1();
		try {
			return new URL(result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private String getTop1() {
		String result = null;
		
		PreparedStatement stmt = null;
		try {
			stmt = conn
					.prepareStatement("SELECT TOP 1 * FROM QUEUE");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result = rs.getString(2);
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
		return result;
	}
	
	private void deleteTop1() {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE TOP 1 FROM QUEUE");
			stmt.executeUpdate();
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
	}
	
	public static void main(String[] args) {
		/*DownloadQueueDatabase d = new DownloadQueueDatabase();
		String found = "www.google.pl";
		String encoded = "";
		try {
			encoded = java.net.URLEncoder.encode(found, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (!encoded.startsWith("http://")) {
			encoded = "http://" + encoded;
		}
		d.insertLink(encoded);
		System.out.println(d.getNextPage());*/
	}

}
