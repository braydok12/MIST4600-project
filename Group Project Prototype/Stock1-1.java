import java.util.HashMap;
import java.util.Scanner;

public class Stock {
	
	private String ticker = "";
	private String name = "";
	private double price = 0.0;
	private double lastPrice = 0.0;
	private int shares;
	
	// constructor
	public Stock(String ticker, String name, double price) {
		this.ticker = ticker;
		this.name = name;
		this.price = price;
		lastPrice = price;
		this.shares = 0;
		
	}
	
	public int getShares() {
		return shares;
	}
	
	public void update() {
		// NOT FINISHED
		double pct = ((int) (Math.random() * 1000)) / 100.0;
		pct -= 5.0;
		// 0% to 10% --> -5% to 5%
		lastPrice = price;
		price += price * pct / 100;
	}
	
	public void buy(int shares) {
		this.shares += shares;	
	}
	public boolean sell(int shares) {
		// if the amount of shares to sell are more than the current sales,
		// return false so that another number of shares is input
		if(shares <= this.shares) {
			this.shares -= shares;
			return true;
		}else {
			return false;
		}
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
