import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

import javafx.util.Pair;

public class RsaGenerator {

    static final BigInteger LOWER = BigInteger.valueOf(2).pow(127);
    static final BigInteger UPPER = BigInteger.valueOf(2).pow(128).subtract(BigInteger.ONE);
    static final int ACCURACY = 50;
    
    private static BigInteger getRandomNumber(){
        Stack<String> results = new Stack<String>();
        
        if (results.isEmpty()){
            try {
                URL url = new URL("https://www.random.org/strings/?num=10000&len=20&digits=on&upperalpha=off&loweralpha=off&unique=off&format=plain&rnd=new");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));

                String output;
                while ((output = br.readLine()) != null) {
                    results.push(output);
                    System.out.println(output);
                }

                conn.disconnect();

            } catch (Exception e) {

                e.printStackTrace();
            }    
        }
        // assumes results will hold even number add all times
        String num = "";
        num += results.pop();
        num += results.pop();
        return new BigInteger(num);
    }
    
    public static Pair<BigInteger, BigInteger> generateKeys(){
        BigInteger x = getRandomNumber();
        while (!goodPrimeNumber(x, LOWER, UPPER)){
            x = getRandomNumber();
        }
        BigInteger y = getRandomNumber();
        while (!goodPrimeNumber(y, LOWER, UPPER)){
            y = getRandomNumber();
        }
        return new Pair<BigInteger, BigInteger>(x, y);
    }
    
    
    private static boolean goodPrimeNumber(BigInteger x, BigInteger lower, BigInteger upper) {
        return x.compareTo(lower) >= 1 && x.compareTo(upper) <= 1 && isPrime(x); 
    }

    private static boolean isPrime(BigInteger x) {
        if (!(x.mod(BigInteger.valueOf(2)).equals(BigInteger.ONE))){
            return false;
        } else {
            return probablyPrime(x, ACCURACY);
        }
    }

    private static boolean probablyPrime(BigInteger x, int accuracy) {
        for (int i = 0; i < accuracy; i++){
            
        }
    }

    public static void main(String[] args){
        generateKeys();
    }
}
