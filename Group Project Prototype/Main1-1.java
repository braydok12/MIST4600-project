import java.util.Scanner;
import java.util.HashMap;


public class Main {
	public static void main(String args[]) {
		// the user will have a HashMap that uses stock tickers for the "keys"
		// and uses "Stock" objects for the "elements"
		
		HashMap<String, Stock> stockDatabase = new HashMap<String, Stock>();
		
		//adding default stocks
		stockDatabase.put("APPL", new Stock("APPL", "Apple", 216.98));
		stockDatabase.put("MSFT", new Stock("MSFT", "Microsoft", 383.27));
		stockDatabase.put("TSLA", new Stock("TSLA", "Tesla", 248.09));
		stockDatabase.put("NVDA", new Stock("NVDA", "Nvidia", 115.74));
		
		// updating stocks
		for(Stock s : stockDatabase.values()) {
			s.update();
		}
		// printing stocks
		stockDatabase.get("APPL").print();
		stockDatabase.get("MSFT").print();
		stockDatabase.get("TSLA").print();
		stockDatabase.get("NVDA").print();
		
		// stock account
		StockAccount acct = new StockAccount(0);
		
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
				}while(true);
				do {
					System.out.println("\nBUY/SELL MENU:");
					System.out.println("A - Sell shares");
					System.out.println("B - Buy shares");
					System.out.println("R - Return to main menu");
					menuOp = scnr.nextLine().toUpperCase().charAt(0);
					
					switch(menuOp) {
					case 'A':
						// sell stocks
						System.out.println("\n" + ticker + " has " + stockDatabase.get(ticker).getShares() + " shares available.");
						System.out.println("Enter the number of shares to sell:");
						shares = scnr.nextInt();
						// loops while there are not enough shares to sell
						// if the user puts a number of shares that is less than or equal to the amount available, the loop breaks
						while(!(stockDatabase.get(ticker).sell(shares))) {
							System.out.println("\nThere are only: " + stockDatabase.get(ticker).getShares() + " shares available to sell.");
							System.out.println("\nEnter the number of shares to sell:");
							shares = scnr.nextInt();
						}
						System.out.println("Successfully sold " + shares + " shares!");
						scnr.nextLine();
						break;
					case 'B':
						// buy stocks
						System.out.println("\n" + ticker + " has " + stockDatabase.get(ticker).getShares() + " shares available.");
						System.out.println("Enter the number of shares to buy:");
						shares = scnr.nextInt();
						stockDatabase.get(ticker).buy(shares);
						System.out.println("Successfully bought " + shares + " shares!");
						scnr.nextLine();
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
						System.out.println("\nHow much money would you like to deposit?");
						 double amount = scnr.nextDouble();
						 scnr.nextLine();
						 while (amount <= 0) {
						        System.out.println("Invalid amount");
						        amount = scnr.nextDouble();
						        scnr.nextLine(); 
						    }
						 acct.deposit(amount);
						 System.out.println("Deposited $" + amount);
						 break;
					case 'B':
						// Withdraw money
						System.out.println("\nHow much money would you like to withdraw?");
						amount = scnr.nextDouble();
						 scnr.nextLine();
						 while (amount <= 0) {
						        System.out.println("Invalid amount");
						        amount = scnr.nextDouble();
						        scnr.nextLine();
						    }
						 
						    if (amount  > acct.getBalance()) {
						        System.out.println("Insufficient balance");
						    } else {
						        acct.withdraw(amount);
						        System.out.println("Withdrew $" + amount);
						    }
						    
						break;
					case 'C':
						// Check balance
						System.out.println("\nBalance is $" + acct.getBalance());
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
