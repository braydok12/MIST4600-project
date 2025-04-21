import java.util.Scanner;
import java.util.HashMap;


public class Main {
	public static void main(String args[]) {
		// the user will have a HashMap that uses stock tickers for the "keys"
		// and uses "Stock" objects for the "elements"
		
		// stock account
		StockAccount acct = new StockAccount(1000);
		HashMap<String, Stock> stockDatabase = FileStorage.readData(acct);

		Stock.marketSummary(stockDatabase);
		
		// print account
		acct.printAcct(stockDatabase);
		
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
				
				// if stock exists in the market, create a Stock object
				// if stock does NOT exist in the market, create a Crypto object
				
				String ticker;
				String name;
				double price;
				
				System.out.println("\nEnter stock ticker:");
				ticker = scnr.nextLine().toUpperCase();
				
				if(StockPriceGetter.getName(ticker).equals("")) {
					System.out.println("\nEnter stock name:");
					name = scnr.nextLine();
					System.out.println("\nEnter stock price:");
					price = scnr.nextDouble();
					scnr.nextLine();
					ticker = "!" + ticker;
				}else {
					name = StockPriceGetter.getName(ticker);
					price = StockPriceGetter.getPrice(ticker);
				}
				String temp = "";
				for(int i = 0; i < name.length(); i++) {
            		if(name.charAt(i) == ' ') {
            			temp += "-";
            		}else {
            			temp += name.charAt(i);
            		}
            	}
				name = temp;
			
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
				do {
					System.out.println("\nEnter stock ticker:");
					ticker = scnr.nextLine().toUpperCase();
					if(stockDatabase.containsKey(ticker)) {
						break;
					}
					System.out.println("Invalid ticker. Try again");
				}while(true);
				stockDatabase.get(ticker).print();
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
						acct.printAcct(stockDatabase);
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
				Stock.marketSummary(stockDatabase);
				acct.printAcct(stockDatabase);
				break;
			case 'F':
				// Market summary
				Stock.marketSummary(stockDatabase);
				acct.printAcct(stockDatabase);
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
			
			FileStorage.storeData(stockDatabase, acct);
	}
}
