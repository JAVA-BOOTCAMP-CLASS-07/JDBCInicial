package ar.com.sicos.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PreparedStatementJDBC {
	
	public static void main(String[] args) {

		Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
			Class.forName(DBData.JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DBData.DB_URL, DBData.USER, DBData.PASS);

			String sql = "SELECT per.nombre, " +
								"per.apellido, " +
								"per.dni, " +
								"per.fecha_nac, " +
								"c.nombre nCiudad " + 
						   "FROM persona per, " + 
								"ciudad c, " + 
						   		"provincia p " + 
						  "WHERE per.ciudad_id = c.id " + 
						   	"and c.PROVINCIA_ID = p.id " + 
						    "and p.nombre = ? " +
						   	"and c.nombre = ?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, "BUENOS AIRES");
			stmt.setString(2, "LA PLATA");
						
			ResultSet rs = stmt.executeQuery();
			
			DateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
					
			if (rs != null) {
				while (rs.next()) {
					System.out.println("--------------------------------------------------");
					System.out.println("Nombre y Apellido: " + rs.getString("nombre") + " " + rs.getString("apellido"));
					System.out.println("DNI: " + rs.getInt("dni"));
					System.out.println("Fecha Nacimiento: " + sf.format(rs.getDate("fecha_nac")));
					System.out.println("Ciudad: " + rs.getString("nCiudad"));
				}
				System.out.println("--------------------------------------------------");
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
