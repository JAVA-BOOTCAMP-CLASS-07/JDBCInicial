package ar.com.sicos.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Calendar;

public class CallableStatementJDBC {
	
	public static void main(String[] args) {

		Connection conn = null;
        CallableStatement call = null;
        
        try {
			Class.forName(DBData.JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DBData.DB_URL, DBData.USER, DBData.PASS);
			
			conn.setAutoCommit(false);

			String sql = "{call INSERTAR_PERSONA (?, ?, ?, ?, ?)}";
			
			call = conn.prepareCall(sql);
			
			call.setString(1, "Roberto");
			call.setString(2, "Jimenez");
			call.setInt(3,  40456789);
			call.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			call.setString(5, "LA PLATA");
			
			call.execute();
			
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    }

}
