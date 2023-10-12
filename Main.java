package ATM_InternalWorks1;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Scanner sc=new Scanner(System.in);
		 while(true){
			 System.out.println("1.Create account:");
			 System.out.println("2.Balance Enquriy:");
			 System.out.println("3.Withdraw:");
			 System.out.println("4.Deposite:");
			 System.out.println("5.Change password");
			 System.out.println("6.History");
			 System.out.println("7.Exit");
			 System.out.print("Enter your choice: ");
			 int chocie=sc.nextInt();
			 sc.nextLine();
			 if(chocie==1){
				 String name=sc.nextLine();
				String pass=sc.nextLine();
				long amount=sc.nextLong();
				ATM_Create	atm =new ATM_Create(name,pass,amount);
			}
			 else if(chocie!=7){
				 System.out.print("Enter account number: ");
				 long acNumber=sc.nextLong();
				 sc.nextLine();
				 System.out.print("Enter password: ");
				 String pass=sc.nextLine();
				 ATM_Create atm=new ATM_Create();
				 switch(chocie){
						case 2:
							atm.getAccountBalance(acNumber, pass);
							break;
						case 3:
							atm.withdraw(acNumber, pass);
							break;
						case 4:
							atm.deposite(acNumber, pass);
							break;
						case 5:
							atm.setPassword(acNumber, pass);
							break;
						case 6:
							atm.accountHistory(acNumber, pass);
							break;
						default:
							System.out.println("Invalid choices");
				}
			}
			else
			{
					System.exit(0);
			}
			
		 }
	}
}
