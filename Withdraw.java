package ATM_InternalWorks1;

import java.sql.SQLException;
import java.util.Scanner;

public class Withdraw {
	public void withdraw(long acNumber,String pass) throws SQLException, ClassNotFoundException
	{
		boolean temp=ContainsAccountNumber.containsAccountNumber(acNumber, pass);
		if(temp)
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter the withdraw anount:");
			long amount =sc.nextLong();
			sc.nextLine();
			if((amount>0&& amount<20000)&&((amount%100==0||amount%200==0||amount%500==0||amount%2000==0)))
			{
				DisplayDetails dd=new DisplayDetails();
				long balance=dd.displayBalance(acNumber);
				
				if(balance>amount)
				{
					balance-=amount;
					ATM_CreateDB atmdb=new ATM_CreateDB();
					if(atmdb.updateBalance(acNumber, balance))
					{
						HoldersAccountDetailsDB had=new HoldersAccountDetailsDB(acNumber);
						had.insertInto(acNumber, amount, "Withdraw");
						had.displayDetalis(acNumber);
					}
				}
				else
				{
					System.out.println("Over the limit");
				}
			}
			else
			{
				System.out.println("Please Enter the Multiples of 100,200,500,20000");
				this.withdraw(acNumber, pass);
			}
		}
		else
		{
			System.out.println("No such Account found");
		}
		return;
	}

}
