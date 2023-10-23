package ar.com.sicos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class PreparedStatementJDBC2 {
	
	public static void main(String[] args) {

		Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
			Class.forName(DBData.JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DBData.DB_URL, DBData.USER, DBData.PASS);

			String sql = "INSERT INTO persona(NOMBRE, APELLIDO, DNI, FECHA_NAC, CIUDAD_ID) " +
							" VALUES (?, ?, ?, ?, (SELECT ID FROM ciudad WHERE NOMBRE = ?))";
			
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, "Juan");
			stmt.setString(2, "Rodriguez");
			stmt.setInt(3, 25364785);
			stmt.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setString(5, "LA PLATA");
						
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs != null) {
				while (rs.next()) {
					int key = rs.getInt(1);
					   System.out.println("Clave generada = " + key);
				}
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
