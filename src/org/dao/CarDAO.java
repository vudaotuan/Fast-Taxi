package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.AppConfig;
import org.model.Car;

public class CarDAO {

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

	public Collection<Integer> getCollectionCar(int price, int user_lat,
			int user_lon) {
		Collection<Integer> cars = new ArrayList<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection con = getConnection();
		try {
			String query = "select id from car where price = ? and latitude < ? and latitude > ? and longitude > ? and longitude < ?";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, price);
			ps.setInt(2, user_lat + 100);
			ps.setInt(3, user_lat - 100);
			ps.setInt(4, user_lon + 100);
			ps.setInt(5, user_lon - 100);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");

				cars.add(id);

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
		return cars;
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
