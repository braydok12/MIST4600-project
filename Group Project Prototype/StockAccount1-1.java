import java.util.HashMap;

public class StockAccount {
		
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
		public double deposit(double amount) {
			balance = (double)((int)(balance * 100)) / 100.0;
			balance += amount;
			return balance;
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
		
		public void printAcct(HashMap<String, Stock> stockDatabase) {
			System.out.println("You have $" + balance + " in cash in your account.");
			double value = balance;
			for(Stock s : stockDatabase.values()) {
				value += s.getPrice()*s.getShares();
			}
			value = (double)((int)(value * 100)) / 100.0;
			System.out.println("You have a total value of $" + value + " in your account.");
		}

}
