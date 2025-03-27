import java.util.HashMap;
import java.util.Scanner;

public class Stock {
	Scanner scnr = new Scanner(System.in);
	
	private String ticker = "";
	private String name = "";
	private double price = 0.0;
	private double lastPrice = 0.0;
	private double buyPrice = 0.0;
	private int shares;
	
	// constructor
	public Stock(String ticker, String name, double price) {
		this.ticker = ticker;
		this.name = name;
		this.price = price;
		lastPrice = price;
		this.shares = 0;
	}
	
	public double getPrice() {
		return (double)((int)(price * 100)) / 100.0;
	}
	public double getBPrice() {
		return (double)((int)(buyPrice * 100)) / 100.0;
	}
	public int getShares() {
		return shares;
	}
	public String getName() {
		return name;
	}
	
	public void update() {
		// NOT FINISHED
		double pct = ((int) (Math.random() * 1000)) / 100.0;
		pct -= 5.0;
		// 0% to 10% --> -5% to 5%
		lastPrice = price;
		price += price * pct / 100;
	}
	public void buy(HashMap<String, Stock> stockDatabase, StockAccount acct) {
		if(price < acct.getBalance()) {
			price = (double)((int)(price * 100)) / 100.0;
			int inputShares;
			do {
				System.out.println("\nYou have " + shares + " shares in " + ticker + ".");
				System.out.println("The current price of " + ticker + " is $" + price);
				System.out.println("You have $" + acct.getBalance() + " in your account.");
				System.out.println("Enter the number of shares to buy:");
				inputShares = scnr.nextInt();
			}while((acct.getBalance() - inputShares*price) < 0);
			shares += inputShares;
			acct.withdraw(inputShares*price);
			System.out.println("Successfully bought " + inputShares + " shares for $" + inputShares*price + "!");
			acct.printAcct(stockDatabase);
			scnr.nextLine();
		}else {
			System.out.println("The current price of " + ticker + " is $" + price);
			System.out.println("You have $" + acct.getBalance() + " in your account.");
			System.out.println("Not enough money to buy a share of " + ticker);
		}
	}
	
	public void sell(HashMap<String, Stock> stockDatabase, StockAccount acct) {
		price = (double)((int)(price * 100)) / 100.0;
		int inputShares;
		System.out.print("\nYou have " + shares + " shares in " + ticker + " to sell. ");
		System.out.println("The current price of " + ticker + " is $" + price);
		System.out.println("You have $" + acct.getBalance() + " in your account.");
		System.out.println("Enter the number of shares to sell:");
		inputShares = scnr.nextInt();
		// loops while there are not enough shares to sell
		// if the user puts a number of shares that is less than or equal to the amount available, the loop breaks
		while(inputShares > shares) {
			System.out.println("\nThere are only: " + shares + " shares available to sell.");
			System.out.println("\nEnter the number of shares to sell:");
			inputShares = scnr.nextInt();
		}
		acct.deposit(inputShares*price);
		System.out.println("Successfully sold " + inputShares + " shares for $" + inputShares*price + "!");
		shares -= inputShares;
		acct.printAcct(stockDatabase);
		scnr.nextLine();
	}
	
	public static void marketSummary(HashMap<String, Stock> market) {
		if (market.isEmpty()) {
			System.out.println("No stocks found...");
		} else {
			System.out.println("\nMarket Summary:");
			System.out.println("");
			System.out.printf("%-10s %-15s %-10s %-10s %-10s\n", "Ticker", "Name", "Price", "Change", "Shares");
            System.out.println("-----------------------------------------------------");
            for (Stock s : market.values()) {
                s.print();
            }
		}
	}
	
	public void print() {
		System.out.printf("%-10s %-15s $%-8.2f [%-5.2f%-5s %s\n", ticker, name, price, ((price - lastPrice) / lastPrice * 100), "%]  " + shares, "shares");
	}
}
