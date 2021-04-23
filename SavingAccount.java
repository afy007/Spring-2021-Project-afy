
public class SavingAccount_Yoshimoto extends Account_Yoshimoto
{
	private float interestRate;
	
	public SavingAccount_Yoshimoto()
	{
		this("", 0f, 0f, 0f);
	}
	public SavingAccount_Yoshimoto(String accountNum)
	{
		this(accountNum, 0f, 0f, 0f);
	}
	public SavingAccount_Yoshimoto(String accountNum, float startingBalance, float minAmount, float intRate)
	{
		super(accountNum, startingBalance, minAmount);
		interestRate = intRate;
	}
	public float getInterestRate()
	{
		return interestRate;
	}
	public float getInterestAmount()
	{
		return getBalance() * interestRate;
	}
	public float getEndBalance()
	{
		return getBalance() + getInterestAmount();
	}
	public static SavingAccount_Yoshimoto openAccount(String accountNum, float startingBalance, float minAmount, float intRate)
	{
		if (isAccountValid(startingBalance, minAmount))
		{
			System.out.println(String.format( "Account Number:     %18s\n"
											+ "New Account balance:%18.1f\n"
											+ "Account type:       %18s\n"
											+ "Interest rate:      %18.2f%s", accountNum, startingBalance,  "Saving Account", intRate * 100, "%"));
			return new SavingAccount_Yoshimoto(accountNum, startingBalance, minAmount, intRate);
		}
		System.out.println("Invalid open account amount");
		return null;
	}
	@Override
	public String checkBalance()
	{
		return super.checkBalance() + String.format("\nAccount type:       %18s", "Saving Account");
	}
	@Override
	public String deposit(float deposit)
	{
		return String.format("Account type:       %18s\n", "Saving Account") + super.deposit(deposit);
	}
	@Override
	public String withdraw(float withdrawal)
	{
		return String.format("Account type:       %18s\n", "Saving Account") + super.withdraw(withdrawal);
	}
	@Override
	public String printMonthlyStatement()
	{
		return String.format( "Account type:       %18s\n"
							+ "%s\n"
							+ "Interest rate:      %18.2f%s\n"
							+ "Interest amount:    %18.1f\n"
							+ "End balance:        %18.1f", "Saving Account", super.printMonthlyStatement(), interestRate * 100, "%", getInterestAmount(), getEndBalance());
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "\n"
				+ String.format(  "Account type:       %18s\n"
								+ "Interest rate:      %18.2f%s", "Saving Account", interestRate * 100, "%");
	}
	@Override
	public String toFile()
	{
		return super.toFile() + "," + interestRate;
	}
}
