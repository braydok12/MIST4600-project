import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		
	Scanner scnr = new Scanner(System.in);
	
		char menuOp;
		StockAccount acct = new StockAccount(0);
		ArrayList<Stock> stockMarket = new ArrayList<>();
		
		// Program menu 
		do {
		
		
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
		
		menuOp = scnr.nextLine().charAt(0);
		
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
		
            stockMarket.add(new Stock(ticker, name, price, shares));
            System.out.println("Stock added successfully!");    
			break;
		case 'B':
			//Buy or sell stocks
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
				
				menuOp = scnr.nextLine().charAt(0);
				
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
			break;
		case 'F':
			// Market summary
			if (stockMarket.isEmpty()) {
				System.out.println("No stocks found...");
			} else {
				System.out.println("\nMarket Summary:");
				System.out.println("");
				System.out.printf("%-10s %-20s %-10s %-10s\n", "Ticker", "Name", "Price", "Shares");
                System.out.println("-----------------------------------------------------");
                for (Stock stock : stockMarket) {
                    stock.displayStock();
                }
			}
			
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


	
	
	


