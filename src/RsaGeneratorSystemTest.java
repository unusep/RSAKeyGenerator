import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

import javafx.util.Pair;

public class RsaGeneratorSystemTest {

    IRandomNumberGenerator ranGen;
    int NUMBITS;
    int ACCURACY;
    BigInteger LOWER;
    BigInteger UPPER;
    
    @Before
    public void init() {
        ranGen = new RandomOrgGen();
        NUMBITS = 128;
        ACCURACY = 1;
        LOWER = BigInteger.valueOf(2).pow(NUMBITS - 1);
        UPPER = BigInteger.valueOf(2).pow(NUMBITS).subtract(BigInteger.ONE);
    }
    
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
