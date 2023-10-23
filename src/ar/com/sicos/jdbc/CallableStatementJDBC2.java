package ar.com.sicos.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementJDBC2 {
	
	public static void main(String[] args) {

		Connection conn = null;
        CallableStatement call = null;
        
        try {
			Class.forName(DBData.JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DBData.DB_URL, DBData.USER, DBData.PASS);

			String sql = "{? = call cuadrado (?)}";
			
			call = conn.prepareCall(sql);
			
			call.registerOutParameter(1, Types.INTEGER);
			
			call.setInt(2, 8);
			
			call.execute();
			
			System.out.println("8 al cuadrado es " + call.getInt(1));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
