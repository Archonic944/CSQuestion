import utils.IntPair;
import utils.Pair;

public class DecimalToFraction {
    public static void main(String[] args) {
        System.out.println(toFraction(0.2));
        System.out.println(toFraction(0.375));
        System.out.println(toFraction((9/70.0))); //this one doesn't work, because of floating point inaccuracies. output: (23,179)
    }

    /**
     * Decimal to fraction. Fraction will be within 1/10000th of the decimal.
     * Returns a pair in the format (numberator, denominator)
     */
    static IntPair toFraction(double decimal){
        if(decimal == 0.0) return null;
        int denominator = 0;
        double mod;
        do{
            denominator++;
            mod = decimal % (1.0/denominator);
        }while(mod > 0.0001);
        int numerator = (int) (decimal / (1.0/denominator));
        return new IntPair(numerator, denominator);
    }
}
