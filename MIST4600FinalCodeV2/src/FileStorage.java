import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileStorage {
	
	public static HashMap<String, Stock> readData(StockAccount acct) {
		HashMap<String, Stock> market = new HashMap<String, Stock>();
		// read each line from data.txt
			// add each variable to a temporary variable
		// create a stock object for each line
		// add each stock object to the market variable
		
		try {
            // FileReader Class used
        	BufferedReader reader = new BufferedReader(new FileReader("/Users/brayd/eclipse-workspace/FinalProject/src/data.txt"));

            String line;
            boolean firstLine = true;
            // Using read method
            while ((line = reader.readLine()) != null) {
            	if(firstLine) {
            		// stock ACCOUNT data line
            		firstLine = false;
            		acct.setBalance(Double.parseDouble(line));
            	}
            	else {
            		// each stock data line
	            	String[] list = new String[5];
	            	for(int i = 0; i < 4; i++) {
	            		String temp = "";
		            	for(int j = 0; j < line.length(); j++) {
		            		if(line.charAt(j) != ' ') {
		            			temp += line.charAt(j);
		            		}else {
		            			//System.out.println(temp +"-");
		            			line = line.substring(j+1);
		            			//System.out.println(line);
		            			list[i] = temp;
		            			break;
		            		}
		            	}
	            	}
	            	list[4] = line;
	            	// price
	            	double price = Double.parseDouble(list[2].substring(1));
	            	// append this list to the ArrayList of Stock objects
	            	if(list[0].charAt(0) == '!') {
	            		// '!' determines a crypto object
	            		market.put(list[0], new Crypto(list[0], list[1], price));
	            		// set number of shares
		            	market.get(list[0]).setShares(Double.parseDouble(list[4]));
	            	}else {
	            		market.put(list[0], new Stock(list[0], list[1], price));
	            		// set number of shares
		            	market.get(list[0]).setShares(Integer.parseInt(list[4]));
	            	}
	            	
	            	// set lastPrice
	            	double pct = Double.parseDouble(list[3].substring(0, list[3].length()-1)) / 100.0;
	            	market.get(list[0]).setLastPrice(price / (1 + pct));
            	}
            }
            // Close method called
            reader.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
		
		return market;
	}
	
	public static void storeData(HashMap<String, Stock> market, StockAccount acct) {
		String data = "";
		try {
			if (market.isEmpty()) {
				data += "No stocks found...";
			} else {
				data += String.format(acct.getBalance() + "\n");
	            for (Stock s : market.values()) {
	                data += s.printData();
	            }
			}
	    	// true makes it append to the file
	        FileWriter writer = new FileWriter("/Users/brayd/eclipse-workspace/FinalProject/src/data.txt" , false);
	        writer.write(data);
	        writer.close();
	        System.out.println("Data saved!");
	    } catch (IOException e) {
	        System.out.println("Error writing to file: " + e.getMessage());
	    }
	}
}
