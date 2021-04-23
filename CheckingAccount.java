
public class CheckingAccount_Yoshimoto extends Account_Yoshimoto
{
	private float serviceFee;
	
	public CheckingAccount_Yoshimoto()
	{
		this("", 0f, 0f, 0f);
	}
	public CheckingAccount_Yoshimoto(String accountNum)
	{
		this(accountNum, 0f, 0f, 0f);
	}
	public CheckingAccount_Yoshimoto(String accountNum, float startingBalance, float minAmount, float fee)
	{
		super(accountNum, startingBalance, minAmount);
		serviceFee = fee;
	}
	public float getServiceFee()
	{
		return serviceFee;
	}
	public float getEndBalance()
	{
		return getBalance() - serviceFee;
	}
	
	public static CheckingAccount_Yoshimoto openAccount(String accountNum, float startingBalance, float minAmount, float fee)
	{
		if (isAccountValid(startingBalance, minAmount))
		{
			System.out.println(String.format( "Account Number:     %18s\n"
											+ "New Account balance:%18.1f\n"
											+ "Account type:       %18s\n"
											+ "Service Fee:        %18.1f", accountNum, startingBalance,  "Checking Account", fee));
			return new CheckingAccount_Yoshimoto(accountNum, startingBalance, minAmount, fee);
		}
		System.out.println("Invalid open account amount");
		return null;
	}
	@Override
	public String checkBalance()
	{
		return super.checkBalance() + String.format("\nAccount type:       %18s", "Checking Account");
	}
	@Override
	public String deposit(float deposit)
	{
		return String.format("Account type:       %18s\n", "Checking Account") + super.deposit(deposit);
	}
	@Override
	public String withdraw(float withdrawal)
	{
		return String.format("Account type:       %18s\n", "Checking Account") + super.withdraw(withdrawal);
	}
	@Override
	public String printMonthlyStatement()
	{
		return String.format( "Account type:       %18s\n"
							+ "%s\n"
							+ "Service Fee:        %18.1f\n"
							+ "End balance:        %18.1f", "Checking Account", super.printMonthlyStatement(), serviceFee, getEndBalance());
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "\n"
				+ String.format(  "Account type:       %18s\n"
								+ "Service Fee:        %18.1f", "Checking Account", serviceFee);
	}
	@Override
	public String toFile()
	{
		return super.toFile() + "," + serviceFee;
	}
}
