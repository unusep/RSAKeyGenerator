import java.math.BigInteger;

public interface IRandomNumberGenerator {
    public void setLength(int length);
    public BigInteger getRandomNumber();
}