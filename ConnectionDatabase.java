package ATM_InternalWorks1;
import java.sql.*;

public class ConnectionDatabase {
	private static final String url="jdbc:mysql://localhost:3306/atm";
	private static final String pass="root";
	private static final String pass1="";
	public static  Connection connection() throws SQLException{
		
		return DriverManager.getConnection(url,pass,pass1);
	}
	

}
