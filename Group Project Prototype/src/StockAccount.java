
public class StockAccount {
		
		// Private variables
		private double balance;
		
		// Constructor
		StockAccount(double balance) {
			this.balance = balance;
		}
		
		// Mutators
		double deposit(double amount) {
			balance += amount;
			return balance;
		}
		
		double withdraw(double amount) {
			balance -= amount;
			return balance;
		}
		
		// Accessors
		
		double getBalance() {
			return balance;
		}

}
