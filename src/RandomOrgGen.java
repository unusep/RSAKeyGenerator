import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

public class RandomOrgGen implements IRandomNumberGenerator {
    private int numStrings = 10;
    private int lenString = 20;
    private Stack<String> results = new Stack<String>();
    private int digitLength = 38;
    
    /**
     * sets the number of strings, numStrings each of length, lenString to request 
     * @param numStrings
     * @param lenString
     */
    public RandomOrgGen(int numStrings, int lenString){
        this.numStrings = numStrings;
        this.lenString = lenString;
    }
    
    public RandomOrgGen(){
        super();
    }
    
    public BigInteger getRandomNumber(){
        String num = "";
        while (num.length() < digitLength){
            if (results.isEmpty()){
                try {
                    URL url = new URL("https://www.random.org/strings/?num=" + numStrings + "&len=" + lenString + "&digits=on&upperalpha=off&loweralpha=off&unique=off&format=plain&rnd=new");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    while ((output = br.readLine()) != null) {
                        results.push(output);
                    }

                    conn.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }    
            } else {
                num += results.pop();
            }
        }
        
        return new BigInteger(num);
    }

    @Override
    public void setLength(int length) {
        this.digitLength = length;
    }
}
