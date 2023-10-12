package ATM_InternalWorks1;

import java.sql.SQLException;

public class ATM_Create {

	private String accountHolderName;
	private Long accountNumber;
	private String password;
	private Long accountBalance;
	
	ATM_Create(String accountHolderName,String password, Long accountBalance) throws SQLException, ClassNotFoundException {
		
		this.accountHolderName = accountHolderName;
		DisplayDetails dd=new DisplayDetails();
		this.accountNumber = dd.getAccountNumber()+1;
		this.password = password;
		this.accountBalance = accountBalance;
		ATM_CreateDB atmdb=new ATM_CreateDB();
		atmdb.insertIntoDB(this);
		
	}
	


	public ATM_Create() {
	
	}

	protected String getAccountHolderName() {
		return accountHolderName;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(long acNumber,String password) throws ClassNotFoundException {
		ATM_CreateDB atm1=new ATM_CreateDB();
		atm1.changePass(acNumber, password);
		return;
	}

	protected long getAccountBalance() {
		return accountBalance ;
	}
	protected void getAccountBalance(long acNumber,String pass) throws SQLException, ClassNotFoundException {
		DisplayDetails dd=new DisplayDetails();
		dd.displayBalance(acNumber, pass);
		return ;
	}

	protected Long getAccountNumber() {
		return accountNumber;
	}
	protected void withdraw(long acNumber,String pass) throws SQLException, ClassNotFoundException
	{
		Withdraw wd=new Withdraw();
		wd.withdraw(acNumber, pass);
		return;
	}
	protected void deposite(long acNumber,String pass) throws SQLException, ClassNotFoundException
	{
		Deposite dp=new Deposite();
		dp.deposite(acNumber, pass);
		return;
	}
	protected void accountHistory(long acNumber,String pass) throws SQLException, ClassNotFoundException
	{
		boolean temp=ContainsAccountNumber.containsAccountNumber(acNumber, pass);
		if(temp)
		{
			HoldersAccountDetailsDB had=new HoldersAccountDetailsDB();
			had.displayHistory(acNumber);
		}
		else
		{
			System.out.println("No such Account found");
		}
		return;
		
	}
	
}
