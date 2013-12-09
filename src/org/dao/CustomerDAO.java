package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.AppConfig;
import org.model.Customer;

public class CustomerDAO {

	private Connection getConnection() {

		AppConfig config = AppConfig.getInstance();
		String URL = config.getProperty("Rongbay.con");
		String user = config.getProperty("Rongbay.user");
		String password = config.getProperty("Rongbay.password");
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(URL, user, password);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public Customer getCollectionCar(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection con = getConnection();
		Customer customer = null;
		try {
			String query = "select id from user where id = ?";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				customer = new Customer();

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
