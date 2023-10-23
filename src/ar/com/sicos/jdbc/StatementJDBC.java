package ar.com.sicos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementJDBC {
	
	public static void main(String[] args) {

		Connection conn = null;
        Statement stmt = null;
        
        try {
			Class.forName(DBData.JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DBData.DB_URL, DBData.USER, DBData.PASS);
			
			stmt = conn.createStatement();
			
			String sql = "SELECT p.nombre nProvincia, " +
								"c.nombre nCiudad " + 
						   "FROM ciudad c, provincia p " +
								"WHERE c.provincia_id = p.id " +
						   "ORDER BY p.NOMBRE,c.NOMBRE";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs != null) {
				String provincia = null;
				while (rs.next()) {
					String aux = rs.getString("nProvincia");
					if (provincia == null || !provincia.equals(aux)) {
						provincia = aux;
						System.out.println("===========================================");
						System.out.println(provincia);
					} 
					System.out.println("======= " + rs.getString("nCiudad"));
				}
				System.out.println("===========================================");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
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
