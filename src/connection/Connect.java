package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connect {
	private String url="jdbc:mysql://localhost:3307/learning?serverTimezone=UTC";
    private String userName="root";
    private String pwd="";
    private Connection con;
    
    public PreparedStatement connecter(String requete) {
    	PreparedStatement ps=null;
    	try {
			 con = DriverManager.getConnection(url, userName, pwd);
			ps = con.prepareStatement(requete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ps;
    }
    
    public void closeCon() {
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
