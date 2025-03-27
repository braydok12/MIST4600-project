mport java.util.Scanner;
import java.util.HashMap;


public class Main {
	public static void main(String args[]) {
		// the user will have a HashMap that uses stock tickers for the "keys"
		// and uses "Stock" objects for the "elements"
		
		HashMap<String, Stock> stockDatabase = new HashMap<String, Stock>();
		
		//adding default stocks
		stockDatabase.put("AAPL", new Stock("AAPL", "Apple", 216.98));
		stockDatabase.put("MSFT", new Stock("MSFT", "Microsoft", 383.27));
		stockDatabase.put("TSLA", new Stock("TSLA", "Tesla", 248.09));
		stockDatabase.put("NVDA", new Stock("NVDA", "Nvidia", 115.74));
		
		// updating stocks
		for(Stock s : stockDatabase.values()) {
			s.update();
		}
		// printing stocks
		stockDatabase.get("AAPL").print();
		stockDatabase.get("MSFT").print();
		stockDatabase.get("TSLA").print();
		stockDatabase.get("NVDA").print();
		
		// stock account
		StockAccount acct = new StockAccount(1000);
		
		// MENU
		Scanner scnr = new Scanner(System.in);
		char menuOp;
		
		do {
			// main menu
			System.out.println("\nSTOCK MENU:");
			System.out.println("A - add new stock to market");
			System.out.println("B - buy/sell stocks");
			System.out.println("C - check specific stock");
			System.out.println("D - deposit/withdraw money");
			System.out.println("E - update stock market");
			System.out.println("F - market summary");
			System.out.println("Q - quit");
			System.out.println("");
			System.out.println("Choose an option");
			
			menuOp = scnr.nextLine().toUpperCase().charAt(0);
			
			switch(menuOp) {
			case 'A':
				// Add new stock
				
				System.out.println("\nEnter stock ticker:");
				String ticker = scnr.nextLine().toUpperCase();
				
				System.out.println("\nEnter stock name:");
				String name = scnr.nextLine();
				
				System.out.println("\nEnter stock price:");
				double price = scnr.nextDouble();
				
				System.out.println("\nEnter number of shares:");
				int shares = scnr.nextInt();
				scnr.nextLine(); 
			
				stockDatabase.put(ticker, new Stock(ticker, name, price));
	            System.out.println("Stock added successfully!");    
				break;
			case 'B':
				// buy or sell stocks
				do {
					System.out.println("\nEnter stock ticker:");
					ticker = scnr.nextLine().toUpperCase();
					if(stockDatabase.containsKey(ticker)) {
						break;
					}
					System.out.println("Invalid ticker. Try again");
				}while(true);
				do {
					System.out.println("\nBUY/SELL MENU:");
					System.out.println("A - Sell shares");
					System.out.println("B - Buy shares");
					System.out.println("R - Return to main menu");
					menuOp = scnr.nextLine().toUpperCase().charAt(0);
					
					switch(menuOp) {
					case 'A':
						// sell stock
						stockDatabase.get(ticker).sell(stockDatabase, acct);
						break;
					case 'B':
						// buy stock
						stockDatabase.get(ticker).buy(stockDatabase, acct);
						break;
					default:
						System.out.println("\nReturning to main menu...");
						break;
					}
				}while(menuOp == 'A' || menuOp == 'B');
				break;
			case 'C':
				// Check specific stock
				
				break;
			case 'D':
				// Deposit/withdraw money
				do {
					System.out.println("\nACCOUNT BALANCE MENU:");
					System.out.println("A - Deposit money");
					System.out.println("B - Withdraw money");
					System.out.println("C - Check balance");
					System.out.println("R - Return to main menu");
					
					menuOp = scnr.nextLine().toUpperCase().charAt(0);
					
					switch(menuOp) {
					case 'A':
						// Deposit money
						System.out.println("Balance is: $" + acct.deposit());
						break;
					case 'B':
						// Withdraw money
						System.out.println("Balance is: $" + acct.withdraw());
						break;
					case 'C':
						// Check balance
						System.out.println("\nBalance is $" + acct.getBalance() + " in cash.");
						break;
					case 'R':
						// Return to main menu
						System.out.println("\nReturning to main menu...");
						break;
					default:
						System.out.println("\nPlease enter a valid input");
					}
				} while(menuOp!='R');
				break;
			case 'E':
				// Update stock market
				for(Stock s : stockDatabase.values()) {
					s.update();
				}
				for(Stock s : stockDatabase.values()) {
					s.print();
				}
				break;
			case 'F':
				// Market summary
				Stock.marketSummary(stockDatabase);
				break;
			case 'Q':
				// Exit program
				System.out.println("\nExiting program...");
				break;
			default:
				System.out.println("\nPlease enter a valid input");
			}
			
			
			} while(menuOp!='Q');
			
			
			scnr.close();
	}
}
