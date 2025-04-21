import java.util.HashMap;

public class Crypto extends Stock{
	private double shares;
	
	public Crypto(String ticker, String name, double price) {
		super(ticker, name, price);
	}
	
	public void setShares(double s) {
		shares = s;
	}
	
	public double getCryptoShares() {
		return shares;
	}
	
	// make update function different --> riskier
	public void update() {
		double pct = ((int) (Math.random() * 2500)) / 100.0;
		pct -= 12.5;
		// 0% to 25% --> -12.5% to 12.5%
		lastPrice = price;
		price += price * pct / 100;
	}
	
	// shares are a double --> can be a partial shares
	// input is the amount of money to invest (not shares)
	public void buy(HashMap<String, Stock> stockDatabase, StockAccount acct) {
		if(price < acct.getBalance()) {
			price = (double)((int)(price * 100)) / 100.0;
			double inputPrice;
			do {
				System.out.println("\nYou have " + shares + " shares in " + ticker + ".");
				System.out.println("The current price of " + ticker + " is $" + price);
				System.out.println("You have $" + acct.getBalance() + " in your account.");
				System.out.println("Enter the amount of money to invest:");
				inputPrice = scnr.nextDouble();
			}while((acct.getBalance() - inputPrice) < 0);
			shares += round(inputPrice / price);
			acct.withdraw(inputPrice);
			System.out.println("Successfully bought " + round(inputPrice / price) + " shares for $" + inputPrice + "!");
			acct.printAcct(stockDatabase);
			scnr.nextLine();
		}else {
			System.out.println("The current price of " + ticker + " is $" + price);
			System.out.println("You have $" + acct.getBalance() + " in your account.");
			System.out.println("Not enough money to buy a share of " + ticker);
		}
	}
	
	public void sell(HashMap<String, Stock> stockDatabase, StockAccount acct) {
		if(shares != 0) {
			price = (double)((int)(price * 100)) / 100.0;
			double inputPrice;
			System.out.print("\nYou have " + shares + " shares in " + ticker + " to sell ($" + price*shares + ").");
			System.out.println("The current price of " + ticker + " is $" + price);
			System.out.println("You have $" + acct.getBalance() + " in your account.");
			System.out.println("Enter the amount ($) to sell:");
			inputPrice = scnr.nextDouble();
			// loops while there are not enough shares to sell
			// if the user puts a number of shares that is less than or equal to the amount available, the loop breaks
			while(inputPrice / price > shares) {
				System.out.println("\nThere is only: $" + shares*price + " available to sell.");
				System.out.println("\nEnter the amount ($) to sell:");
				inputPrice = scnr.nextDouble();
			}
			acct.deposit(inputPrice);
			System.out.println("Successfully sold " + round(inputPrice/price) + " shares for $" + inputPrice + "!");
			shares -= round(inputPrice/price);
			acct.printAcct(stockDatabase);
			scnr.nextLine();
		}else {
			System.out.println("No shares available to sell.");
		}
	}
	public void print() {
		System.out.printf("%-10s %-15s $%-8.2f [%-5.2f%-5s %s\n", ticker, name, price, ((price - lastPrice) / lastPrice * 100), "%]  " + shares, "shares");
	}
	public String printData() {
		return String.format("%s %s $%.2f %.2f%s%s", ticker, name, price, ((price - lastPrice) / lastPrice * 100), "% " + shares, "\n");
	}
}
