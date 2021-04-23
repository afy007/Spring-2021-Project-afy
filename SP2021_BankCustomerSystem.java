import java.util.Scanner;
import java.util.Set;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.InputMismatchException;
import java.io.FileNotFoundException;

public class SP2021_BankCustomerSystem
{
	public static void main(String[] args) 
	{
		String mainMenu = "SP2021_BankCustomerSystem.java\n"
						+ "MAIN MENU\n"
						+ "-------------------------------------------\n"
						+ "1. Work on Data Structure of Bank Customers\n"
						+ "2. Work on One Bank Customer\n"
						+ "0. Exit";
		Scanner kb = new Scanner(System.in);
		int input = -1;
		GenericMyMap<String, BankCustomer> customers = initializeHashMap();
		
		while (input != 0)
		{
			try
			{
				System.out.print(mainMenu + "\nEnter an option 1, 2, or 0 to exit: ");
				input = kb.nextInt();
				kb.nextLine();
				System.out.println();
				if (input == 1)
				{
					workOnCustomerHashMap(customers, kb);
				}
				else if (input == 2)
				{
					System.out.print("Enter the customer's ID: ");
					String id = kb.next();
					System.out.println();
					if (customers.get(id) == null)
						System.out.println("Customer not found\n");
					else
						workOnOneCustomer(customers.get(id), kb);
				}
				else if (input == 0)
				{
					System.out.println("Customer data saved into file \"bankCustomer.csv\"");
					exitOperation(customers);
				}
				else
					System.out.println("Please enter a proper option.\n");
			}
			catch (InputMismatchException wrongInput)
			{
				kb.nextLine();
				System.out.println("\nPlease enter a proper option.\n");
			}
		}
	}
	
	public static void workOnCustomerHashMap(GenericMyMap<String, BankCustomer> customers, Scanner kb)
	{
		String hashMapMenu = "SP2021_BankCustomerSystem.java\n"
							+ "TASKS ON DATA STRUCTURE \n"
							+ "---------------------------------------------------------------------\n"
							+ "1. INSERT - Add new Bank Customer with new Account\n"
							+ "2. GET  - Display One Bank Customer With All Accounts\n"
							+ "3. DELETE - Remove one Bank Customer\r\n"
							+ "4. UPDATE - Modify information of one Bank Customer\n"
							+ "5. SHOWALL - Display all Customers with their accounts\n"
							+ "0. DONE\n"
							+ "Type a number from 1 to 5 or 0 to exit: ";
		int input = -1;
		
		while (input != 0)
		{
			try
			{
				System.out.print(hashMapMenu);
				input = kb.nextInt();
				kb.nextLine();
				System.out.println();
				if (input == 1)
				{
					System.out.println("-----Add a new customer-----");
					System.out.print("Enter a new customer ID: ");
					String id = kb.next();
					kb.nextLine();
					System.out.print("Enter your last name: ");
					String lastN = kb.next();
					kb.nextLine();
					System.out.print("Enter your first name: ");
					String firstN = kb.next();
					kb.nextLine();
					System.out.print("Enter a username: ");
					String userN = kb.next();
					kb.nextLine();
					System.out.print("Enter a password: ");
					String passW = kb.next();
					kb.nextLine();
					System.out.print("Enter your phone number: ");
					String phone = kb.next();
					kb.nextLine();
					while (!phone.matches("\\d{10}"))
					{
						System.out.print("Please enter your phone number as a 10-digit number: ");
						phone = kb.next();
						kb.nextLine();
					}
					System.out.print("Enter your address: ");
					String address = kb.nextLine();
					while (customers.get(id) != null)
					{
						System.out.print("ID has already been taken. Please enter a new customer ID: ");
						id = kb.next();
						kb.nextLine();
					}
					BankCustomer newCustomer = new BankCustomer(id, lastN, firstN, userN, passW, phone, address);
					System.out.println("Please enter information for your first account");
					Account firstAccount = openNewAccount(kb);
					System.out.println();
					newCustomer.addAccount(firstAccount);
					
					System.out.println("New customer added:" + newCustomer);
					customers.put(newCustomer.getKey(), newCustomer);
				}
				else if (input == 2)
				{
					System.out.println("-----Display information for one customer-----");
					System.out.print("Enter the ID of the customer: ");
					String id = kb.next();
					kb.nextLine();
					System.out.println();
					if (customers.get(id) == null)
						System.out.println("The customer does not exist in the system");
					else
						System.out.println(customers.get(id));
				}
				else if (input == 3)
				{
					System.out.println("-----Remove a customer-----");
					System.out.print("Enter the ID of the customer: ");
					String id = kb.next();
					kb.nextLine();
					System.out.println();
					if (customers.get(id) == null)
						System.out.println("The customer does not exist in the system");
					else
					{
						customers.remove(id);
						System.out.println("Deletion successful");
					}
				}
				else if (input == 4)
				{
					System.out.println("-----Update customer information-----");
					System.out.print("Enter the ID of the customer: ");
					String id = kb.next();
					kb.nextLine();
					System.out.println();
					if (customers.get(id) == null)
						System.out.println("The customer does not exist in the system");
					else
					{
						BankCustomer toUpdate = customers.get(id).deepCopy();
						updateCustomerInformation(toUpdate, kb);
						customers.put(toUpdate.getKey(), toUpdate);
					}
				}
				else if (input == 5)
				{
					System.out.println("-----Display all customers-----");
					Set<BankCustomer> allCustomers = customers.values();
					for (BankCustomer customer: allCustomers)
						System.out.println(customer + "\n");
				}
				else if (input != 0)
					System.out.println("Please enter a proper option.");
				System.out.println();
			}
			catch (InputMismatchException wrongInput)
			{
				kb.nextLine();
				System.out.println("\nPlease enter a proper option.\n");
			}
		}
	}
	
	public static void workOnOneCustomer(BankCustomer customer, Scanner kb)
	{
		String customerMenu = "SP2021_BankCustomersSystem.java\n"
							+ "TASKS ON ONE CUSTOMER \n"
							+ "------------------------------------------------------------------------\n"
							+ "1. Open New Account\n"
							+ "2. Display one Account to check balance\n"
							+ "3. Close one Account\n"
							+ "4. Deposit to one Account\n"
							+ "5. Withdraw from one Account\n"
							+ "6. Transfer Money from one account to other account\n"
							+ "7. Print the bank statement of one account\n"
							+ "8. Show all accounts\n"
							+ "0. DONE\n"
							+ "Type a number from 1 to 8 to select task or 0 to exit: ";
		int input = -1;
		
		while (input != 0)
		{
			try
			{
				System.out.print(customerMenu);
				input = kb.nextInt();
				kb.nextLine();
				System.out.println();
				if (input == 1)
				{
					Account newAccount = openNewAccount(kb);
					System.out.println();
					if (newAccount == null)
						continue;
					boolean openSuccess = customer.addAccount(newAccount);
					if (openSuccess)
						System.out.println("A new account has been opened");
					else
						System.out.println("Account number has been taken. No account opened");
				}
				else if (input == 2)
				{
					System.out.println("-----Display one account-----");
					System.out.print("Enter account number: ");
					String acctToDisplay = kb.next();
					kb.nextLine();
					System.out.println();
					System.out.println(customer.displayOneAccount(acctToDisplay));
				}
				else if (input == 3)
				{
					System.out.println("-----Close one account-----");
					System.out.print("Enter account number: ");
					String acctToClose = kb.next();
					kb.nextLine();
					System.out.println();
					System.out.println(customer.closeAccount(acctToClose));
				}
				else if (input == 4)
				{
					System.out.println("-----Deposit to one account-----");
					System.out.print("Enter account number: ");
					String acctToDeposit = kb.next();
					kb.nextLine();
					if (customer.getAccount(acctToDeposit) == null)
					{
						System.out.println();
						System.out.println("Account does not exist");
						System.out.println();
						continue;
					}
					System.out.print("Enter a deposit amount: ");
					float depositAmount = kb.nextFloat();
					kb.nextLine();
					while (depositAmount <= 0)
					{
						System.out.print("Enter a deposit amount (must be greater than 0): ");
						depositAmount = kb.nextFloat();
						kb.nextLine();
					}
					System.out.println();
					System.out.println(customer.depositToAccount(acctToDeposit, depositAmount));
				}
				else if (input == 5)
				{
					System.out.println("-----Withdraw from one account-----");
					System.out.print("Enter account number: ");
					String acctToWithdraw = kb.next();
					kb.nextLine();
					System.out.println();
					if (customer.getAccount(acctToWithdraw) == null)
					{
						System.out.println("Account does not exist");
						System.out.println();
						continue;
					}
					if (!login(customer, kb))
					{
						System.out.println("Invalid username or password");
						System.out.println();
						continue;
					}
					System.out.println("Login verified");
					System.out.println();
					System.out.print("Enter a withdrawal amount: ");
					float withdrawAmount = kb.nextFloat();
					kb.nextLine();
					while (withdrawAmount <= 0)
					{
						System.out.print("Enter a withdrawal amount (must be greater than 0): ");
						withdrawAmount = kb.nextFloat();
						kb.nextLine();
					}
					System.out.println();
					System.out.println(customer.withdrawFromAccount(acctToWithdraw, withdrawAmount));
				}
				else if (input == 6)
				{
					System.out.println("-----Tranfer money from one account to another-----");
					if (!login(customer, kb))
					{
						System.out.println("Invalid username or password");
						System.out.println();
						continue;
					}
					System.out.println("Login verified");
					System.out.println();
					System.out.print("Enter the account number to withdraw from: ");
					String withdrawAcct = kb.next();
					kb.nextLine();
					System.out.print("Enter account number to deposit to: ");
					String depositAcct = kb.next();
					kb.nextLine();
					System.out.print("Enter transfer amount: ");
					float transferAmount = kb.nextFloat();
					kb.nextLine();
					while (transferAmount <= 0)
					{
						System.out.print("Enter transfer amount (must be greater than 0): ");
						transferAmount = kb.nextFloat();
						kb.nextLine();
					}
					System.out.println(customer.transferMoneyBetweenAccounts(withdrawAcct, depositAcct, transferAmount));
				}
				else if (input == 7)
				{
					System.out.println("-----Print the bank statement of one account-----");
					System.out.print("Enter account number: ");
					String acctToState = kb.next();
					kb.nextLine();
					System.out.println();
					System.out.println(customer.displayStatement(acctToState));
				}
				else if (input == 8)
				{
					System.out.println("-----Show all accounts-----");
					System.out.println(customer.displayAllAccounts());
				}
				else if (input != 0)
					System.out.println("Please enter a proper option.");
				System.out.println();
			}
			catch (InputMismatchException wrongInput)
			{
				kb.nextLine();
				System.out.println("\nPlease enter a proper option.\n");
			}
		}
	}
	
	public static Account openNewAccount(Scanner kb)
	{
		Account newAccount = null;
		System.out.print("Enter new account number: ");
		String acctNum = kb.next();
		kb.nextLine();
		System.out.print("Enter a starting balance: ");
		float startingBalance = kb.nextFloat();
		kb.nextLine();
		while (startingBalance < 0)
		{
			System.out.print("Enter a starting balance (must be greater than or equal to 0): ");
			startingBalance = kb.nextFloat();
			kb.nextLine();
		}
		System.out.print("Enter a minimum amount: ");
		float minAmount = kb.nextFloat();
		kb.nextLine();
		while (minAmount < 0)
		{
			System.out.print("Enter a minimum amount (must be greater than or equal to 0): ");
			minAmount = kb.nextFloat();
			kb.nextLine();
		}
		System.out.print("Will the account be a (1) Checking  or  (2) Savings ?\nEnter a 1 or 2: ");
		int accountType = kb.nextInt();
		kb.nextLine();
		while (accountType != 1 && accountType != 2)
		{
			System.out.print("Enter a 1 or 2: ");
			accountType = kb.nextInt();
			kb.nextLine();
		}
		if (accountType == 1)
		{
			System.out.print("Enter a monthly service fee: ");
			float fee = kb.nextFloat();
			kb.nextLine();
			while (fee <= 0)
			{
				System.out.print("Enter a monthly service fee (must be greater than 0): ");
				fee = kb.nextFloat();
				kb.nextLine();
			}
			System.out.println();
			newAccount = CheckingAccount.openAccount(acctNum, startingBalance, minAmount, fee);
			
		}
		else if (accountType == 2)
		{
			System.out.print("Enter a monthly interest rate (as a percent): ");
			float intRate = kb.nextFloat();
			kb.nextLine();
			while (intRate < 0 || intRate > 100)
			{
				System.out.print("Enter a monthly interest rate (must be between 0 and 100, inclusive): ");
				intRate = kb.nextFloat();
				kb.nextLine();
			}
			System.out.println();
			newAccount = SavingAccount.openAccount(acctNum, startingBalance, minAmount, intRate / 100);
		}
		return newAccount;
	}
	
	public static void updateCustomerInformation(BankCustomer customer, Scanner kb)
	{
		String customerUpdateMenu = 	"-----Select which information to update-----\n"
									+ 	"1. NAME\n"
									+ 	"2. USERNAME AND PASSWORD\n"
									+ 	"3. PHONE NUMBER\n"
									+ 	"4. ADDRESS\n"
									+ 	"0. DONE\n"
									+ 	"Type a number from 1 to 4 or 0 to exit: ";
		int input = -1;
		
		while (input != 0)
		{
			try
			{
				System.out.print(customerUpdateMenu);
				input = kb.nextInt();
				kb.nextLine();
				System.out.println();
				if (input == 1)
				{
					System.out.print("Enter your last name: ");
					String lastN = kb.next();
					kb.nextLine();
					System.out.print("Enter your first name: ");
					String firstN = kb.next();
					kb.nextLine();
					customer.setName(lastN, firstN);
					System.out.println("Name updated");
				}
				else if (input == 2)
				{
					System.out.print("Enter your new username: ");
					String newUser = kb.next();
					kb.nextLine();
					System.out.print("Enter your new password: ");
					String newPass = kb.next();
					kb.nextLine();
					customer.setLogin(newUser, newPass);
					System.out.println("Login information updated");
				}
				else if (input == 3)
				{
					System.out.print("Enter your new phone number: ");
					String newPhone = kb.next();
					kb.nextLine();
					while (!newPhone.matches("\\d{10}"))
					{
						System.out.print("Please enter your phone number as a 10-digit number: ");
						newPhone = kb.next();
						kb.nextLine();
					}
					customer.setPhoneNumber(newPhone);
					System.out.println("Phone number updated");
				}
				else if (input == 4)
				{
					System.out.print("Enter your new address: ");
					String newAddress = kb.nextLine();
					customer.setAddress(newAddress);
					System.out.println("Address updated");
				}
				else if (input != 0)
					System.out.println("Please enter a proper option.");
				System.out.println();
			}
			catch (InputMismatchException wrongInput)
			{
				kb.nextLine();
				System.out.println("\nPlease enter a proper option.\n");
			}
		}
		
		System.out.println("Update successful");
	}
	
	public static boolean login(BankCustomer customer, Scanner kb)
	{
		System.out.println("Please verify your login");
		System.out.print("Enter username: ");
		String userN = kb.next();
		kb.nextLine();
		System.out.print("Enter password: ");
		String passW = kb.next();
		kb.nextLine();
		return customer.verifyLogin(userN, passW);
	}
	
	public static void exitOperation(GenericMyMap<String, BankCustomer> customers)
	{
		try (FileWriter fw = new FileWriter(new File("bankCustomer.csv")))
		{
			PrintWriter writeTo = new PrintWriter(fw);
			Set<BankCustomer> customerInfo = customers.values();
			for (BankCustomer customer: customerInfo)
			{
				writeTo.println(customer.toFile());
			}
		}
		catch (IOException e)
		{
			System.out.println("IOException thrown");
		}
	}
	
	public static GenericMyMap<String, BankCustomer> initializeHashMap()
	{
		GenericMyMap<String, BankCustomer> customers = new GenericMyHashMap<>();
		try (Scanner readFile = new Scanner(new File("bankCustomer.csv")))
		{
			while (readFile.hasNext())
			{
				String line = readFile.nextLine();
				String[] data = line.split(",");
				BankCustomer newCustomer = new BankCustomer(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
				for (int i = 7; i < data.length; i += 4)
				{
					if (!data[i].equals(""))
					{
						String accountNum = data[i];
						float startingBalance = Float.parseFloat(data[i + 1]);
						float minAmount = Float.parseFloat(data[i + 2]);
						float determiningInfo = Float.parseFloat(data[i + 3]);
						if (determiningInfo < 1)
							newCustomer.addAccount(new SavingAccount(accountNum, startingBalance, minAmount, determiningInfo));
						else
							newCustomer.addAccount(new CheckingAccount(accountNum, startingBalance, minAmount, determiningInfo));
					}
				}
				customers.put(newCustomer.getKey(), newCustomer);
			}
		}
		catch (FileNotFoundException noFile)
		{
			System.out.println("File \"bankCustomer.csv\" not found");
		}
		catch (IndexOutOfBoundsException badFile)
		{
			System.out.println("File \"bankCustomer.csv\" cannot be used to obtain customer data");
		}
		return customers;
	}
}
