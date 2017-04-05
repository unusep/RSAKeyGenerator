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
    int NUMBITS;
    int ACCURACY;
    BigInteger LOWER;
    BigInteger UPPER;
    
    @Before
    public void init() {
        ranGen = new TestGenerator();
        NUMBITS = 128;
        ACCURACY = 1;
        LOWER = BigInteger.valueOf(2).pow(NUMBITS - 1);
        UPPER = BigInteger.valueOf(2).pow(NUMBITS).subtract(BigInteger.ONE);
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
        IRsaGenerator RsaGen = new HomeworkRsaGenerator(ranGen, NUMBITS, ACCURACY);
        Pair<BigInteger, BigInteger> keys = RsaGen.generateKeys();
        assertTrue(keys.getKey().compareTo(LOWER) >= 1);
        assertTrue(keys.getValue().compareTo(UPPER) <= 1);
    }
    
}
