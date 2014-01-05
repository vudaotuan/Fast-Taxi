package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.AppConfig;
import org.model.User;

public class UserDAO {

	private Connection getConnection() {

		// AppConfig config = AppConfig.getInstance();
		Connection con = null;

		try {
			Context ctx = new InitialContext();

			// Look up our data source
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/Database");

			// Allocate and use a connection from the pool
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			// e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// e.printStackTrace();
		}
		return con;
	}
	
	public int createUser(String username, String password) {
		PreparedStatement ps = null;
		int rs = 0;

		Connection con = getConnection();
		try {
			String query = "insert into user(username, password) values (?, ?)";

			// logger.info(query);
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeUpdate();

			ps.close();
			ps = null;

			con.close();
			con = null;

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("selectItems:" + e.getMessage());
		} finally {
			finallySQL(con, ps, null);
		}
		return rs;
	}

	public User getUserByName(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection con = getConnection();
		User user = null;
		try {
			String query = "select id, username, password from user where username = ? and password = ?";

			// logger.info(query);
			ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"),
						rs.getString("password"), rs.getInt("id"),
						rs.getInt("id"), rs.getInt("id"));

			}

			rs.close();

			rs = null;

			ps.close();
			ps = null;

			con.close();
			con = null;

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("selectItems:" + e.getMessage());
		} finally {
			finallySQL(con, ps, rs);
		}
		return user;
	}

	public User getCollectionCar(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection con = getConnection();
		User customer = null;
		try {
			String query = "select id from user where id = ?";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				customer = new User();

			}

			rs.close();

			rs = null;

			ps.close();
			ps = null;

			con.close();
			con = null;

		} catch (SQLException e) {
			e.printStackTrace();
			// logger.error("selectItems:" + e.getMessage());
		} finally {
			finallySQL(con, ps, rs);
		}
		return customer;
	}

	public void finallySQL(Connection con, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			}
			ps = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
			}
			con = null;
		}
	}

}
