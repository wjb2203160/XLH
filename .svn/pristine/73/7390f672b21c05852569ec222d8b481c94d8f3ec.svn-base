package com.mtr.psn.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DBHelper {

	// 此方法为获取数据库连接
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String driver = "com.mysql.jdbc.Driver"; // 数据库驱动
			String url = "jdbc:MySQL://localhost:3306/pay_state_nuclear_db?useUnicode=true&characterEncoding=utf8";// 数据库
			String user = "root"; // 用户名
			String password = "thomas123"; // 密码
			Class.forName(driver); // 加载数据库驱动
			if (null == conn) {
				conn = DriverManager.getConnection(url, user, password);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can't find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 
	 * 增删改【Add、Del、Update】
	 * 
	 * @param sql
	 * @return int
	 */

	public static int executeNonQuery(String sql) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException err) {
			err.printStackTrace();
			free(null, stmt, conn);
		} finally {
			free(null, stmt, conn);
		}
		return result;

	}

	/**
	 * 增删改【Add、Delete、Update】
	 * 
	 * @param sql
	 * @param obj
	 * @return int
	 */
	public static int executeNonQuery(String sql, Object... obj) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException err) {
			err.printStackTrace();
			free(null, pstmt, conn);
		} finally {
			free(null, pstmt, conn);
		}
		return result;

	}

	/**
	 * 查【Query】
	 * 
	 * @param sql
	 * @return ResultSet
	 */

	public static ResultSet executeQuery(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs, stmt, conn);
		}
		return rs;
	}

	/**
	 * 查【Query】
	 * 
	 * @param sql
	 * @param obj
	 * @return ResultSet
	 */

	public static ResultSet executeQuery(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs, pstmt, conn);
		}
		return rs;
	}

	/**
	 * 
	 * 判断记录是否存在
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return Boolean
	 */

	public static Boolean isExist(String sql) {

		ResultSet rs = null;
		try {
			rs = executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
			return false;
		} finally {
			free(rs);
		}
	}

	/**
	 * 判断记录是否存在
	 * 
	 * @param sql
	 * @return Boolean
	 */

	public static Boolean isExist(String sql, Object... obj) {
		ResultSet rs = null;
		try {
			rs = executeQuery(sql, obj);
			rs.last();
			int count = rs.getRow();
			if (count > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException err) {
			err.printStackTrace();
			free(rs);
			return false;
		} finally {
			free(rs);
		}
	}

	/**
	 * 获取查询记录的总行数
	 * 
	 * @param sql
	 * @return int
	 */

	public static int getCount(String sql) {
		int result = 0;
		ResultSet rs = null;
		try {
			rs = executeQuery(sql);
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			free(rs);
			err.printStackTrace();
		} finally {
			free(rs);
		}
		return result;
	}

	/**
	 * 获取查询记录的总行数
	 * 
	 * @param sql
	 * @param obj
	 * @return int
	 */
	public static int getCount(String sql, Object... obj) {
		int result = 0;
		ResultSet rs = null;
		try {
			rs = executeQuery(sql, obj);
			rs.last();
			result = rs.getRow();
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			free(rs);
		}
		return result;
	}

	/**
	 * 释放【ResultSet】资源
	 * 
	 * @param rs
	 */
	public static void free(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * 释放【Statement】资源
	 * 
	 * @param st
	 */
	public static void free(Statement st) {
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * 释放【Connection】资源
	 * 
	 * @param conn
	 */
	public static void free(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * 
	 * 释放所有数据资源
	 * 
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void free(ResultSet rs, Statement st, Connection conn) {
		free(rs);
		free(st);
		free(conn);
	}

	/**
	 * 增加【Add】
	 * 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		// 添加
		{
			// 第一种情况
			String sql1 = "Insert Into student Values ('201208', '张三', '男', '21')";
			System.out.println(DBHelper.executeNonQuery(sql1));
			// 第二种情况2
			String sql2 = "Insert Into student Values (?,?,?,?)";
			Object[] obj = new Object[] { "201209", "张三", "男", "21" };
			System.out.println(DBHelper.executeNonQuery(sql2, obj));
		}

		// 删除
		{
			// 第一种情况
			String sql1 = "Delete From student Where id='201202'";
			System.out.println(DBHelper.executeNonQuery(sql1));
			// 第二种情况
			String sql2 = "Delete From student Where id=? And name=?";
			Object[] obj = new Object[] { "201208", "张三" };
			System.out.println(DBHelper.executeNonQuery(sql2, obj));
		}

		// 修改
		{
			// 第一种情况
			String sql1 = "Update student Set age='27' Where id='201207'";
			System.out.println(DBHelper.executeNonQuery(sql1));

			// 第二种情况
			String sql2 = "Update student Set name=? Where id=?";
			Object[] obj = new Object[] { "张二", "201201" };
			System.out.println(DBHelper.executeNonQuery(sql2, obj));
		}

		// 查询
		{
			// 第一种情况
			String sql1 = "Select * From student";

			// 第一步：查询
			ResultSet rs1 = DBHelper.executeQuery(sql1);
			// 第二步：输出
			while (rs1.next()) {
				System.out.println("姓名：" + rs1.getString(2));
			}
			// 第三步：关闭
			DBHelper.free(rs1);

			// 第二种情况
			String sql2 = "Select * From student Where name=?";
			Object[] obj = new Object[] { "张三" };
			// 第一步：查询
			ResultSet rs2 = DBHelper.executeQuery(sql2, obj);
			// 第二步：输出
			while (rs2.next()) {
				System.out.println("学号：" + rs2.getString(1));
			}
			// 第三步：关闭
			DBHelper.free(rs2);
		}

		// 查询行数
		{
			// 第一种情况
			String sql1 = "Select * From student";
			System.out.println(DBHelper.getCount(sql1));

			// 第二种情况
			String sql2 = "Select * From student Where name=?";
			Object[] obj = new Object[] { "张三" };
			System.out.println(DBHelper.getCount(sql2, obj));
		}

		// 判断记录存在
		{
			// 第一种情况
			String sql1 = "Select * From student";
			System.out.println(DBHelper.isExist(sql1));

			// 第二种情况
			String sql2 = "Select * From student Where name=?";
			Object[] obj = new Object[] { "张五" };
			System.out.println(DBHelper.isExist(sql2, obj));
		}
	}
}
