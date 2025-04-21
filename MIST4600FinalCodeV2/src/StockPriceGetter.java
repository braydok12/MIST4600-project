import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockPriceGetter {
    private static final String API_KEY = "cvdj6q9r01qm9khl9dc0cvdj6q9r01qm9khl9dcg";  // API key (finnhub)
    // cvdj6q9r01qm9khl9dc0cvdj6q9r01qm9khl9dcg <-- finnhub key
    // 5C86LTTU22ZP2IH9 <-- alpha vantage key
    
    public static double getPct(String symbol) {
String urlString = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + API_KEY;
        
        try {
            URL url = new URL(urlString);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            String data = response.toString();
            
            String pct = "";
            int i = data.indexOf("dp") + 4;
            // dp is % change
            while(data.charAt(i) != ',') {
            	pct += data.charAt(i);
            	i++;
            }
            double pctDouble = Double.parseDouble(pct);
            return pctDouble;
            
        } catch (Exception e) {
        	System.out.println("Error fetching stock data: " + e.getMessage());
            return 0.0;
        }
    }
    
    public static double getPrice(String symbol) {
    	String urlString = "https://finnhub.io/api/v1/quote?symbol=" + symbol + "&token=" + API_KEY;
        
        try {
            URL url = new URL(urlString);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            
            String data = response.toString();
            
            String price = "";
            int i = data.indexOf('c') + 3;
            // c is closing price
            while(data.charAt(i) != ',') {
            	price += data.charAt(i);
            	i++;
            }
            double priceDouble = Double.parseDouble(price);
            return priceDouble;
            
        } catch (Exception e) {
        	System.out.println("Error fetching stock data: " + e.getMessage());
            return 0.0;
        }
    }
    
    public static String getName(String symbol) {
    // DISPLAY STOCK NAME
    	try{
	    	String profileUrl = "https://finnhub.io/api/v1/stock/profile2?symbol=" + symbol + "&token=" + API_KEY;
	        URL url1 = new URL(profileUrl);
	        HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
	        conn1.setRequestMethod("GET");
	
	        BufferedReader reader1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
	        StringBuilder response1 = new StringBuilder();
	        String line1;
	        while ((line1 = reader1.readLine()) != null) {
	            response1.append(line1);
	        }
	        reader1.close();
	
	        String profileData = response1.toString();
	        String name = "";
	        int nameStart = profileData.indexOf("\"name\":\"") + 8;
	        while (nameStart < profileData.length() && profileData.charAt(nameStart) != '\"') {
	            name += profileData.charAt(nameStart);
	            nameStart++;
	        }
	        return name;
    	} catch (Exception e) {
            return "Error fetching stock data: " + e.getMessage();
        }
    	
    }
}

