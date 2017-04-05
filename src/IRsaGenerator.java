import java.math.BigInteger;

import javafx.util.Pair;

public interface IRsaGenerator {
    public Pair<BigInteger, BigInteger> generateKeys();
}
