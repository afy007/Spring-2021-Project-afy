
public class Account_Yoshimoto
{
	private String accountNumber;
	private float balance;
	private float minimumAmount;
	
	public Account_Yoshimoto()
	{
		this("", 0f, 0f);
	}
	public Account_Yoshimoto(String accountNum)
	{
		this(accountNum, 0f, 0f);
	}
	public Account_Yoshimoto(String accountNum, float startingBalance, float minAmount)
	{
		accountNumber = accountNum;
		balance = startingBalance;
		minimumAmount = minAmount;
	}
	
	public String getAccountNumber()
	{
		return accountNumber;
	}
	public float getBalance()
	{
		return balance;
	}
	public float getMinimumAmount()
	{
		return minimumAmount;
	}
	public static Account_Yoshimoto openAccount(String accountNum, float startingBalance, float minAmount)
	{
		if (isAccountValid(startingBalance, minAmount))
		{
			System.out.println(String.format( "Account Number:     %18s\n"
											+ "New Account balance:%18.1f", accountNum, startingBalance));
			return new Account_Yoshimoto(accountNum, startingBalance, minAmount);
		}
		System.out.println("Invalid open account amount");
		return null;
	}
	protected static boolean isAccountValid(float balance, float minimumAmount)
	{
		return balance >= minimumAmount;
	}
	public String checkBalance()
	{
		return String.format( "Account Number:     %18s\n"
							+ "Current balance:    %18.1f", accountNumber, balance);
	}
	public String deposit(float deposit)
	{
		return String.format( "Account Number:     %18s\n"
							+ "Current balance:    %18.1f\n"
							+ "Deposit amount:     %18.1f\n"
							+ "New Balance:        %18.1f", accountNumber, balance, deposit, balance += deposit);
	}
	public String withdraw(float withdrawal)
	{
		if (checkWithdrawalLimit(withdrawal))
			return String.format( "Account Number:     %18s\n"
								+ "Current balance:    %18.1f\n"
								+ "Withdraw amount:    %18.1f\n"
								+ "New Balance:        %18.1f", accountNumber, balance, withdrawal, balance -= withdrawal);
		return String.format( "Account Number:     %18s\n"
							+ "Current balance:    %18.1f\n"
							+ "Withdraw amount:    %18.1f - denied", accountNumber, balance, withdrawal);
	}
	private boolean checkWithdrawalLimit(float money)
	{
		return balance - money >= getMinimumAmount();
	}
	public String printMonthlyStatement()
	{
		return String.format( "Account Number:     %18s\n"
							+ "Balance:            %18.1f", accountNumber, balance);
	}
	
	@Override
	public String toString()
	{
		return  String.format("Account Number:     %18s\n"
							+ "Balance:            %18.1f", accountNumber, balance);
	}
	public String toFile()
	{
		return accountNumber + "," + balance + "," + minimumAmount;
	}
}
