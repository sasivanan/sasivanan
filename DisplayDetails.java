package ATM_InternalWorks1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayDetails {
	
	public long displayBalance(long acNumber) throws SQLException, ClassNotFoundException{
		Connection con=ConnectionDatabase.connection();
		String query="select acBalance from bankdetails where acNumber=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setLong(1, acNumber);
		ResultSet rs=ps.executeQuery();
		rs.next();
		return rs.getLong(1);
		
	}
	public void displayBalance(long acNumber,String pass) throws SQLException, ClassNotFoundException{
		Connection con=ConnectionDatabase.connection();
		boolean temp=ContainsAccountNumber.containsAccountNumber(acNumber, pass);
		if(temp)
		{
			String query="select * from BankDetails where acNumber=? AND password=?";
			PreparedStatement  ps = con.prepareStatement(query);
			ps.setLong(1,acNumber);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();		
			rs.next();
			System.out.println("Account current balance is : "+rs.getLong(4));
		}
		return;
	}
	public long getAccountNumber() throws SQLException, ClassNotFoundException{
		Connection con=ConnectionDatabase.connection();
		String query="select MAX(acNumber) From  bankdetails ";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return rs.getLong(1);
		
	}
	
	
}
