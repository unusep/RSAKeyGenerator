import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;

public class RsaGeneratorTest {
    private class TestGenerator implements IRandomNumberGenerator{
        @Override
        public BigInteger getRandomNumber() {
            BigInteger b = new BigInteger(128, new Random());
            return b;
        }
    }

    IRandomNumberGenerator ranGen;
    
    @Before
    public void initObjects() {
        ranGen = new TestGenerator();
    }
    
    // naively checks if the keys generated are probably prime
    @Test
    public void GeneratesRsaKeys() {
        IRsaGenerator RsaGen = new HomeworkRsaGenerator(ranGen);
        Pair<BigInteger, BigInteger> keys = RsaGen.generateKeys();
        assertTrue(keys != null);
        assertTrue(keys.getKey().isProbablePrime(1));
        assertTrue(keys.getValue().isProbablePrime(1));
    }
    
    @Test
    public void KeysAreWithinRange() {
        int numBits = 128;
        int accuracy = 1;
        IRsaGenerator RsaGen = new HomeworkRsaGenerator(ranGen, numBits, accuracy);
        Pair<BigInteger, BigInteger> keys = RsaGen.generateKeys();

        BigInteger LOWER = BigInteger.valueOf(2).pow(numBits - 1);
        BigInteger UPPER = BigInteger.valueOf(2).pow(numBits).subtract(BigInteger.ONE);
        
        assertTrue(keys.getKey().compareTo(LOWER) >= 1);
        assertTrue(keys.getValue().compareTo(UPPER) <= 1);
    }
    
}
