package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {

	private static DataSource ds;
	public static Connection getConn() throws SQLException {
		return getInstance().getConnection();
	}

	private static DataSource getInstance() {
		if (ds == null) {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/workshop");

			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return ds;
	}

	public static PreparedStatement getPreparedStatement(String sql, String[] columns) {
		try {
			return getConn().prepareStatement(sql, columns);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			return getConn().prepareStatement(sql);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
