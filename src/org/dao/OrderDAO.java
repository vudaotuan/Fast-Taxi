package org.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.AppConfig;
import org.model.Order;

public class OrderDAO {

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
	// status = 0 hoặc 1 (order đang treo hoặc đang được xử lý)
	public boolean is_exist_order_not_finish(int user_id){
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean flag = false;

		Connection con = getConnection();
		try {
			String query = "select id from order where user_id = ? and ( status = 0 or status = 1) limit 1";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, user_id);

			rs = ps.executeQuery();

			while (rs.next()) {
				flag = true;

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
		return flag;
	}

	// Kiểm tra xem có các order nào đang pending hoặc processing không?
	public int insert_request_order(Order order) {
		PreparedStatement ps = null;
		Integer rs = null;

		Connection con = getConnection();
		try {
			String query = "insert into order(user_id, user_latitude, user_longitude, status) values (?, ?, ?, 0)";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, order.getUser_id());
			ps.setInt(2, order.getUser_latitude());
			ps.setInt(3, order.getUser_longitude());

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
	
	public Order get_order(int user_id, int status) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		Connection con = getConnection();
		Order order = null;
		try {
			String query = "select user_id,user_latitude,user_longitude from order where user_id = ? and status = ?";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, user_id);
			ps.setInt(2, status);

			rs = ps.executeQuery();
			
			while (rs.next()){
				order = new Order();
				order.setUser_id(rs.getInt("user_id"));
				order.setUser_latitude(rs.getInt("user_latitude"));
				order.setUser_longitude(rs.getInt("user_longitude"));
				order.setStatus(0);
			}

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
		return order;
	}
	
	public int update_order_taxi(Order order) {
		PreparedStatement ps = null;
		Integer rs = null;

		Connection con = getConnection();
		try {
			String query = "update order where user_id = ? and status = 0 set taxi_id = ?";

			// logger.info(query);

			ps = con.prepareStatement(query);

			ps.setInt(1, order.getUser_id());
			ps.setInt(2, order.getCar_id());

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
