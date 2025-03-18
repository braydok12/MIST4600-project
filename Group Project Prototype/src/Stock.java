
public class Stock {
	
private String ticker;
private String name;
private double price;
private int shares;


public Stock (String ticker, String name, double price, int shares) {
	this.ticker = ticker;
	this.name = name;
	this.price = price;	
	this.shares = shares;
	
	}

public void displayStock() {
    System.out.printf("%-10s %-20s $%-10.2f %-10d\n", ticker, name, price, shares);
	}
}