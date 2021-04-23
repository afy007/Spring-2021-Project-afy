import java.util.LinkedList;

public class BankCustomer_Yoshimoto implements Comparable<BankCustomer_Yoshimoto>
{
	private String customerID;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private String phone;
	private String address;
	private LinkedList<Account_Yoshimoto> listAccount;
	
	public BankCustomer_Yoshimoto()
	{
		this("", "", "", "", "", "", "");
	}
	public BankCustomer_Yoshimoto(String id)
	{
		this(id, "", "", "", "", "", "");
	}
	public BankCustomer_Yoshimoto(String id, String lastN, String firstN, String userN, String passW, String phoneNum, String ad)
	{
		customerID = id;
		lastName = lastN;
		firstName = firstN;
		username = userN;
		password = passW;
		phone = phoneNum;
		address = ad;
		listAccount = new LinkedList<>();
	}
	public String getKey()
	{
		return customerID;
	}
	public String getName()
	{
		return firstName + " " + lastName;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getAddress()
	{
		return address;
	}
	
	public boolean addAccount(Account_Yoshimoto acct)
	{
		boolean acctNumberNotTaken = true;
		for (Account_Yoshimoto a: listAccount)
			if (a.getAccountNumber().equals(acct.getAccountNumber()))
			{
				acctNumberNotTaken = false;
				break;
			}
		if (acctNumberNotTaken)
			listAccount.add(acct);
		return acctNumberNotTaken;
	}
	/** Returns reference to account of specified account number */
	public Account_Yoshimoto getAccount(String acctNum)
	{
		for (Account_Yoshimoto acct: listAccount)
			if (acctNum.equals(acct.getAccountNumber()))
				return acct;
		return null;
	}
	public boolean verifyLogin(String userNGuess, String passWGuess)
	{
		if (userNGuess.equals(username) && passWGuess.equals(password))
			return true;
		return false;
	}
	public String displayOneAccount(String acctNum)
	{
		if (getAccount(acctNum) != null)
			return getAccount(acctNum).toString();
		return "Account does not exist";
	}
	public String closeAccount(String acctNum)
	{
		boolean closed = listAccount.remove(getAccount(acctNum));
		if (closed)
			return "Closed account successfully";
		else
			return "Account does not exist";
	}
	public String depositToAccount(String acctNum, float depositAmount)
	{
		Account_Yoshimoto acct = getAccount(acctNum);
		if (acct == null)
			return "Account does not exist";
		return acct.deposit(depositAmount);
	}
	public String withdrawFromAccount(String acctNum, float withdrawAmount)
	{
		Account_Yoshimoto acct = getAccount(acctNum);
		if (acct == null)
			return "Account does not exist";
		return acct.withdraw(withdrawAmount);
	}
	public String transferMoneyBetweenAccounts(String fromAcct, String toAcct, float transferAmount)
	{
		if (getAccount(fromAcct) == null || getAccount(toAcct) == null)
			return "Account does not exist";
		String withdrawStatement = getAccount(fromAcct).withdraw(transferAmount);
		if (withdrawStatement.substring((withdrawStatement.length() - 6)).equals("denied"))
			return "Money transfer unsuccessful";
		getAccount(toAcct).deposit(transferAmount);
		return "Money transfer successful";
	}
	public String displayStatement(String acctNum)
	{
		if (getAccount(acctNum) == null)
			return "Account does not exist";
		return getAccount(acctNum).printMonthlyStatement();
	}
	public String displayAllAccounts()
	{
		StringBuilder str = new StringBuilder();
		int counter = 0;
		for (Account_Yoshimoto acct: listAccount)
		{
			if (counter == listAccount.size() - 1)
				str.append(acct);
			else
				str.append(acct + "\n\n");
			counter++;
		}
		return str.toString();
	}
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder("Customer ID:     " + customerID + "\n"
											+ "Customer name:   " + firstName + " " + lastName + "\n"
											+ "User name:       " + username + "\n"
											+ "Password:        " + password + "\n"
											+ "Phone:           " + phone + "\n"
											+ "Address:         " + address + "\n"
											+ "List Accounts:"  + "\n");
		int counter = 0;
		for (Account_Yoshimoto acct: listAccount)
		{
			if (counter == listAccount.size() - 1)
				str.append(acct);
			else
				str.append(acct + "\n");
			counter++;
		}
		return str.toString();
	}
	public String toFile()
	{
		StringBuilder str = new StringBuilder(customerID + "," + lastName + "," + firstName + "," + username + "," + password + "," + phone + "," + address);
		for (Account_Yoshimoto acct: listAccount)
			str.append("," + acct.toFile());
		return str.toString();
	}
	public void setName(String last, String first)
	{
		lastName = last;
		firstName = first;
	}
	public void setLogin(String userN, String passW)
	{
		username = userN;
		password = passW;
	}
	public void setPhoneNumber(String phoneNum)
	{
		phone = phoneNum;
	}
	public void setAddress(String newAddress)
	{
		address = newAddress;
	}
	public int compareTo(String otherKey)
	{
		return customerID.compareTo(otherKey);
	}
	@Override
	public int compareTo(BankCustomer_Yoshimoto o)
	{
		return customerID.compareTo(o.getKey());
	}
	
	public BankCustomer_Yoshimoto deepCopy()
	{
		BankCustomer_Yoshimoto deepCopy = new BankCustomer_Yoshimoto(customerID, lastName, firstName, username, password, phone, address);
		for (Account_Yoshimoto acct: listAccount)
			deepCopy.addAccount(acct);
		return deepCopy;
	}
}
