import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class RandomOrgGenTest {

    // checks that I can successfully request a random number from random org
    @Test
    public void GeneratesRsaKeys() {
        IRandomNumberGenerator ranGen = new RandomOrgGen();
        BigInteger result = ranGen.getRandomNumber();
        assertTrue(result != null);
    }
    
}
