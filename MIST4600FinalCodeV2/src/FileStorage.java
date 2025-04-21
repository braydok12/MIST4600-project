import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.nio.file.Paths;

public class FileStorage {

    // Method to get the file path dynamically
    private static String getFilePath() {
        // Get the current working directory and append the relative path to 'data.txt'
        return Paths.get(System.getProperty("user.dir"), "src", "data.txt").toString();
    }

    public static HashMap<String, Stock> readData(StockAccount acct) {
        HashMap<String, Stock> market = new HashMap<>();
        
        try {
            // Use the dynamic file path
            BufferedReader reader = new BufferedReader(new FileReader(getFilePath()));

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    // stock ACCOUNT data line
                    firstLine = false;
                    acct.setBalance(Double.parseDouble(line));
                } else {
                    // Process stock data
                    String[] list = new String[5];
                    for (int i = 0; i < 4; i++) {
                        String temp = "";
                        for (int j = 0; j < line.length(); j++) {
                            if (line.charAt(j) != ' ') {
                                temp += line.charAt(j);
                            } else {
                                line = line.substring(j + 1);
                                list[i] = temp;
                                break;
                            }
                        }
                    }
                    list[4] = line;

                    // Extract price and create stock object
                    double price = Double.parseDouble(list[2].substring(1));
                    if (list[0].charAt(0) == '!') {
                        market.put(list[0], new Crypto(list[0], list[1], price));
                        market.get(list[0]).setShares(Double.parseDouble(list[4]));
                    } else {
                        market.put(list[0], new Stock(list[0], list[1], price));
                        market.get(list[0]).setShares(Integer.parseInt(list[4]));
                    }

                    // Set last price based on percentage change
                    double pct = Double.parseDouble(list[3].substring(0, list[3].length() - 1)) / 100.0;
                    market.get(list[0]).setLastPrice(price / (1 + pct));
                }
            }
            reader.close();
        } catch (Exception e) {
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

            // Use the dynamic file path
            FileWriter writer = new FileWriter(getFilePath(), false);
            writer.write(data);
            writer.close();
            System.out.println("Data saved!");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}

