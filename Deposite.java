package ATM_InternalWorks1;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deposite {
	public void  deposite(long acNumber,String pass) throws SQLException, ClassNotFoundException
	{
		boolean temp=ContainsAccountNumber.containsAccountNumber(acNumber, pass);
		if(temp)
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter the amount to deposite; ");
			long amount =sc.nextLong();
			sc.nextLine();
			if(amount>0)
			{
				if(amount%100==0||amount%200==0||amount%500==0||amount%2000==0)
				{
					DisplayDetails dd=new DisplayDetails();
					long balance=dd.displayBalance(acNumber);
					
					balance+=amount;
					ATM_CreateDB atmdb=new ATM_CreateDB();
					if(atmdb.updateBalance(acNumber, balance))
					{
						HoldersAccountDetailsDB had=new HoldersAccountDetailsDB(acNumber);
						had.insertInto(acNumber, amount, "Deposite");
						had.displayDetalis(acNumber);
					}
				}
			}
		}
		else
		{
			System.out.println("No such Account found");
		}
		return;
	}

}
