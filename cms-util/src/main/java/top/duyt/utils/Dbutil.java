package top.duyt.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dbutil {

	// 声明对象
	public static Connection conn = null;

	/***
	 * 得到数据库的连接对象
	 * 
	 * @return
	 */
	public static Connection getConn() {
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 得到连接对象
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/cms_alvin?user=root&password=root"
							+ "&sessionVariables=FOREIGN_KEY_CHECKS=0&characterEncoding=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回
		return conn;
	}

	/***
	 * 关闭连接对象
	 * 
	 * @param rs
	 * @param pstmt
	 * @param conn
	 */
	public static void closeDB(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			// 判断
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
