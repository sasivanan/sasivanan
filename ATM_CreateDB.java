package ATM_InternalWorks1;

import java.sql.*;
import java.util.Scanner;

public class ATM_CreateDB {
	
	public void insertIntoDB(Object obj) throws ClassNotFoundException 
	{
		try {
			
		Connection con=ConnectionDatabase.connection();
		ATM_Create atm=(ATM_Create)obj;
		String query="insert into bankdetails values(?,?,?,?)";
		//con.setAutoCommit(false);
		
		PreparedStatement  ps = con.prepareStatement(query);
		ps.setLong(1,atm.getAccountNumber());
		ps.setString(2, atm.getAccountHolderName());
		ps.setString(3,atm.getPassword());
		ps.setLong(4,atm.getAccountBalance());
		
		int row=ps.executeUpdate();
		if(row==1)
		{
			HoldersAccountDetailsDB had=new HoldersAccountDetailsDB(atm.getAccountNumber());
			had.insertInto(atm.getAccountNumber(),atm.getAccountBalance(), "Deposite");
			had.displayDetalis(atm.getAccountNumber());
			System.out.println("Account Created Successfully");
		}
		}
		catch(SQLException e)
		{
			System.out.println("Error in inserting data into database");
		}
		return;
	}
	public void changePass(long acNumber,String pass) throws ClassNotFoundException 
	{
		try {
			boolean temp=ContainsAccountNumber.containsAccountNumber(acNumber,pass);
			if(temp)
			{	
				Connection con=ConnectionDatabase.connection();
				Statement st=con.createStatement();
				Scanner sc=new Scanner(System.in);
				System.out.print("Enter your new password:");
				String str1=sc.nextLine();
				System.out.print("Reenter your password:");
				String str2=sc.nextLine();
				
				if(str1.equals(str2))
				{
					String query="update bankdetails set password=? where acNumber=?";
					PreparedStatement  ps = con.prepareStatement(query);
					ps.setString(1,str1);
					ps.setLong(2,acNumber);
					int row=ps.executeUpdate();
					if(row==1)
					{
						System.out.println("Password Changed");
					}
				}
				else {
					System.out.println("Passwords not same");
					this.changePass(acNumber, pass);
				}
			}
			else
			{
				System.out.println("No such Account found");
			}
			return;
		}
		catch(SQLException e)
		{
			System.out.println("Error in inserting data into database");
		}
		return;
	}
	public boolean updateBalance(long acNumber,long amount) throws ClassNotFoundException 
	{
		try {
			
		Connection con=ConnectionDatabase.connection();
		String query="update bankdetails set acBalance=? where acNumber=?";
		PreparedStatement  ps = con.prepareStatement(query);
		ps.setLong(1,amount);
		ps.setLong(2,acNumber);
		int row=ps.executeUpdate();
		return row>0?true:false;
		}
		catch(SQLException e)
		{
			System.out.println("Error in inserting data into database");
		}
		return false;
	}
}
