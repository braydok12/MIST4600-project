import java.util.HashMap;
import java.util.Scanner;

public class StockAccount {
		Scanner scnr = new Scanner(System.in);
		// Private variables
		private double balance;
		
		// create a HashMap that contains the number of shares and ticker
		// allows for possibility of multiple accounts
		// private HashMap<String, Integer> account = new HashMap<String, Integer>();
		
		// Constructor
		public StockAccount(double balance) {
			this.balance = balance;
		}
		
		// Mutators
		public double deposit() {
			System.out.println("\nHow much money would you like to deposit?");
			double amount = scnr.nextDouble();
			scnr.nextLine();
			while (amount <= 0) {
				System.out.println("Invalid amount");
		        amount = scnr.nextDouble();
		        scnr.nextLine(); 
		    }
			System.out.println("Deposited $" + amount);
			return this.deposit(amount);
		}
		public double deposit(double amount) {
			balance = (double)((int)(balance * 100)) / 100.0;
			balance += amount;
			return balance;
		}
		public double withdraw() {
			System.out.println("\nBalance is $" + balance);
			System.out.println("How much money would you like to withdraw?");
			double amount = scnr.nextDouble();
			scnr.nextLine();
			while (true) {
				if (amount > balance) {
			        System.out.println("Insufficient balance");
			        System.out.println("Balance is $" + balance);
			    } else if (amount <= 0) {
			    	System.out.println("Invalid amount");
			    } else {
			    	break;
			    }
				amount = scnr.nextDouble();
		        scnr.nextLine();
			}
			System.out.println("Withdrew $" + amount);
		    return this.withdraw(amount);
		}
		public double withdraw(double amount) {
			
			balance = (double)((int)(balance * 100)) / 100.0;
			balance -= amount;
			return balance;
		}
		
		// Accessors
		
		public double getBalance() {
			balance = (double)((int)(balance * 100)) / 100.0;
			return balance;
		}
		public void setBalance(double b) {
			balance = b;
		}
		
		public void printAcct(HashMap<String, Stock> stockDatabase) {
			balance = (double)((int)(balance * 100)) / 100.0;
			System.out.println("\nYou have $" + balance + " in cash in your account.");
			double value = balance;
			for(Stock s : stockDatabase.values()) {
				// change to include Crypto objects
				if(s instanceof Crypto) {
					value += s.getPrice()*((Crypto) s).getCryptoShares();
				}else {
					value += s.getPrice()*s.getShares();
				}
			}
			value = (double)((int)(value * 100)) / 100.0;
			System.out.println("You have a total value of $" + value + " in your account.");
		}
}
