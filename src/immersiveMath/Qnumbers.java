package immersiveMath;

public class Qnumbers {

    private int numerator;
    private int denominator;

    public Qnumbers(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public Qnumbers(int numerator){
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator(){
        return denominator;
    }

    public double toDouble(){
        return numerator/denominator;
    }

    public String toString(){
        if (denominator != 1) {
            return numerator + "/" + denominator;
        }else {
            return numerator + "";
        }
    }

    public void normalize(){
        int[] temp = normalize(numerator, denominator);
        numerator = temp[0];
        denominator = temp[1];
    }

    public void add(Qnumbers a){
         numerator = (numerator * a.getDenominator()) + (a.getNumerator() * denominator);
         denominator *= a.getDenominator();
         normalize();
    }

    public void subtract(Qnumbers a){
        numerator = (numerator * a.getDenominator()) - (a.getNumerator() * denominator);
        denominator *= a.getDenominator();
        normalize();
    }

    public void multiply(Qnumbers a){
        numerator *= a.getNumerator();
        denominator *= a.getDenominator();
        normalize();
    }

    public void division(Qnumbers a){
        numerator *= a.getDenominator();
        denominator *= a.getNumerator();
        normalize();
    }

    public static int[] normalize(int numerator, int denominator){
        int gcd = ImmersiveMath.gcd(Math.abs(numerator),Math.abs(denominator));

        numerator /= gcd;
        denominator /= gcd;

        if (denominator < 0){
            denominator *= -1;
            numerator *= -1;
        }

        return new int[]{numerator, denominator};
    }
}