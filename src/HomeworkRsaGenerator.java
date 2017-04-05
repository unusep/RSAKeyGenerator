import java.math.BigInteger;

import javafx.util.Pair;

public class HomeworkRsaGenerator implements IRsaGenerator {
    private BigInteger LOWER = BigInteger.valueOf(2).pow(127);
    private BigInteger UPPER = BigInteger.valueOf(2).pow(128).subtract(BigInteger.ONE);
    private int ACCURACY = 50;
    
    private IRandomNumberGenerator randomGen;

    public HomeworkRsaGenerator(IRandomNumberGenerator generator){
        this.randomGen = generator;
    }
    
    public HomeworkRsaGenerator(IRandomNumberGenerator generator, int numBits, int accuracy){
        this.LOWER = BigInteger.valueOf(2).pow(numBits-1);
        this.UPPER = BigInteger.valueOf(2).pow(numBits).subtract(BigInteger.ONE);
        this.ACCURACY = accuracy; 
        this.randomGen = generator;
    }
    
    public Pair<BigInteger, BigInteger> generateKeys(){
        BigInteger x = randomGen.getRandomNumber();
        while (!goodPrimeNumber(x, LOWER, UPPER)){
            x = randomGen.getRandomNumber();
        }
        BigInteger y = randomGen.getRandomNumber();
        while (!goodPrimeNumber(y, LOWER, UPPER)){
            y = randomGen.getRandomNumber();
        }
        return new Pair<BigInteger, BigInteger>(x, y);
    }
    
    
    private boolean goodPrimeNumber(BigInteger x, BigInteger lower, BigInteger upper) {
        return x.compareTo(lower) >= 1 && x.compareTo(upper) <= 1 && isProbablyPrime(x); 
    }

    private boolean isProbablyPrime(BigInteger x) {
        return x.isProbablePrime(this.ACCURACY);
    }

}
