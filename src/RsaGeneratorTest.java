import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

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
    
    @Test
    public void GeneratesRsaKeys() {
        IRandomNumberGenerator ranGen = new TestGenerator();
        IRsaGenerator RsaGen = new HomeworkRsaGenerator(ranGen);
        Pair<BigInteger, BigInteger> keys = RsaGen.generateKeys();
        assertTrue(keys != null);
        assertTrue(keys.getKey().isProbablePrime(1));
        assertTrue(keys.getValue().isProbablePrime(1));
    }
    
}
