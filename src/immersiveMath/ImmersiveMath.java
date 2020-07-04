package immersiveMath;

public class ImmersiveMath {

    public static int euclideanAlgorithm(int a, int b){
        while (a*b != 0) {
            if (a > b) {
                a %= b;
            }else {
                b %= a;
            }
        }

        return a+b;
    }

    public static int sign(int a){
        if(a == 0){
            return 0;
        }else {
            if(a > 0){
                return 1;
            }else {
                return -1;
            }
        }
    }

    public static int gcd(int a, int b){
        return euclideanAlgorithm(a,b);
    }

    public static int hcf(int a, int b){
        return (a/euclideanAlgorithm(a,b))*b;
    }

    public static Qnumbers sum(Qnumbers a, Qnumbers b){
        return new Qnumbers((a.getNumerator()*b.getDenominator()) + (b.getNumerator()*a.getDenominator()),
                a.getDenominator()*b.getDenominator());
    }

    public static Qnumbers subtract(Qnumbers a, Qnumbers b){
        return new Qnumbers((a.getNumerator()*b.getDenominator()) - (b.getNumerator()*a.getDenominator()),
                a.getDenominator()*b.getDenominator());
    }

    public static Qnumbers multiply(Qnumbers a, Qnumbers b){
        return new Qnumbers(a.getNumerator()*b.getNumerator(),
                a.getDenominator()*b.getDenominator());
    }

    public static Qnumbers division(Qnumbers a, Qnumbers b){
        return new Qnumbers(a.getNumerator()*b.getDenominator(),
                a.getDenominator()*b.getNumerator());
    }

    public static int phi(int n){
        int ans = n;

        for(int i = 2; i * i <= n;++i){
            if (n % i == 0){
                while(n % i == 0) {
                    n /= i;
                }
                ans -= ans / i;
            }
        }

        if (n > 1) {
            ans -= ans / n;
        }

        return ans;
    }

//    public static int combination(int n, int k){
//
//    }
}
