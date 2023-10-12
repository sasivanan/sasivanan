package ATM_InternalWorks1;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class HoldersAccountDetailsDB {
	
	public HoldersAccountDetailsDB(long acNumber) throws SQLException, ClassNotFoundException {
		String tableName="acNumber_"+String.valueOf(acNumber);
		String createTableSQL = "CREATE TABLE IF NOT EXISTS "+tableName+ "("
				+"id INT AUTO_INCREMENT PRIMARY KEY,type VARCHAR(20),"
				+ "amount INT,"
				+"acBalance INT,"
				+"datecol DATE,"
				+"timecol TIME)";
		Connection con=ConnectionDatabase.connection();
		PreparedStatement ps=con.prepareStatement(createTableSQL);
		//ps.setString(1,tableName);
		ps.executeUpdate();
		}

	public HoldersAccountDetailsDB() {
		// TODO Auto-generated constructor stub
	}

	public void insertInto(long acNumber,long amount,String type) throws SQLException, ClassNotFoundException
	{
		Connection con=ConnectionDatabase.connection();
		String tableName="acNumber_"+String.valueOf(acNumber);
		String query="insert into "+tableName+"(type,amount,acBalance,datecol,timecol)values(?,?,?,?,?)";
		DisplayDetails dd=new DisplayDetails();
		long balance=dd.displayBalance(acNumber);
		Time currentTime = new Time(System.currentTimeMillis());
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
		PreparedStatement  ps = con.prepareStatement(query);
		ps.setString(1,type);
		ps.setLong(3,balance);
		ps.setLong(2,amount);
		ps.setDate(4,currentDate);
		ps.setTime(5, currentTime);
		ps.executeUpdate();
		return;
	}
	public void displayDetalis(long acNumber) throws SQLException
	{
		Connection con=ConnectionDatabase.connection();
		String tableName="acNumber_"+String.valueOf(acNumber);
		String query="select * from "+tableName+" ORDER BY id DESC LIMIT 1";
		//PreparedStatement  ps = con.prepareStatement(query);
		Statement st=con.createStatement();
		//ps.setString(1, tableName);
		ResultSet rs=st.executeQuery(query);
		if(rs.next())
		{
			System.out.print(rs.getString(2)+" ");
			System.out.print(rs.getLong(3)+" ");
			System.out.print(rs.getLong(4)+" ");
			System.out.print(rs.getDate(5)+" ");
			System.out.print(rs.getTime(6)+" ");
			System.out.println();
		}
		return;
	}
	public void displayHistory(long acNumber) throws SQLException, ClassNotFoundException
	{
		Connection con=ConnectionDatabase.connection();
		String tableName="acNumber_"+String.valueOf(acNumber);
		String query="select * from "+tableName+" ORDER BY id DESC LIMIT 10";
		//PreparedStatement  ps = con.prepareStatement(query);
		Statement st=con.createStatement();
		//ps.setString(1, tableName);
		ResultSet rs=st.executeQuery(query);
		while(rs.next())
		{
			System.out.print(rs.getString(2)+" ");
			System.out.print(rs.getLong(3)+" ");
			System.out.print(rs.getLong(4)+" ");
			System.out.print(rs.getDate(5)+" ");
			System.out.print(rs.getTime(6)+" ");
			System.out.println();
		}
		return;
	}
}
