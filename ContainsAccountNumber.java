package ATM_InternalWorks1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContainsAccountNumber {
	public static boolean containsAccountNumber(long acNumber,String pass) throws SQLException, ClassNotFoundException{
		Connection con=ConnectionDatabase.connection();
		String query="select * from BankDetails where acNumber="+acNumber;
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		if(rs.next()){
			 query="select * from BankDetails where acNumber=? AND password=?";
				PreparedStatement  ps = con.prepareStatement(query);
				ps.setLong(1,acNumber);
				ps.setString(2,pass);
				rs=ps.executeQuery();
			 
			 if(rs.next()){
				 return true;
			 }
			 else{
					System.out.println("Incorrect Password");
			}
			
		}
		return false;
	}
}
