package immersiveMath;

import java.util.Objects;
import immersiveMath.hyperbolicFunctions;

public class Cnumbers {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    // create a new object with the given real and imaginary parts
    public Cnumbers(double real, double imag) {
        re = real;
        im = imag;
    }
    public Cnumbers(double real) {
        re = real;
        im = 0;
    }

    // return a string representation of the invoking immersiveMath.Cnumbers object
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    // return abs/modulus/magnitude
    public double abs() {
        return Math.hypot(re, im);
    }

    // return angle/phase/argument, normalized to be between -pi and pi
    public double phase() {
        return Math.atan2(im, re);
    }

    // return a new immersiveMath.Cnumbers object whose value is (this + b)
    public Cnumbers plus(Cnumbers b) {
        Cnumbers a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Cnumbers(real, imag);
    }

    // return a new immersiveMath.Cnumbers object whose value is (this - b)
    public Cnumbers minus(Cnumbers b) {
        Cnumbers a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Cnumbers(real, imag);
    }

    // return a new immersiveMath.Cnumbers object whose value is (this * b)
    public Cnumbers multiply(Cnumbers b) {
        Cnumbers a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Cnumbers(real, imag);
    }

    // return a new object whose value is (this * alpha)
    public Cnumbers scale(double alpha) {
        return new Cnumbers(alpha * re, alpha * im);
    }

    // return a new immersiveMath.Cnumbers object whose value is the conjugate of this
    public Cnumbers conjugate() {
        return new Cnumbers(re, -im);
    }

    // return a new immersiveMath.Cnumbers object whose value is the reciprocal of this
    public Cnumbers reciprocal() {
        double scale = re*re + im*im;
        return new Cnumbers(re / scale, -im / scale);
    }

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // return a / b
    public Cnumbers divides(Cnumbers b) {
        Cnumbers a = this;
        return a.multiply(b.reciprocal());
    }

    // return a new immersiveMath.Cnumbers object whose value is the complex exponential of this
    public Cnumbers exp() {
        return new Cnumbers(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // return a new immersiveMath.Cnumbers object whose value is the complex sine of this
    public Cnumbers sin() {
        return new Cnumbers(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new immersiveMath.Cnumbers object whose value is the complex cosine of this
    public Cnumbers cos() {
        return new Cnumbers(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // return a new immersiveMath.Cnumbers object whose value is the complex tangent of this
    public Cnumbers tan() {
        return sin().divides(cos());
    }



    // a static version of plus
    public static Cnumbers plus(Cnumbers a, Cnumbers b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Cnumbers sum = new Cnumbers(real, imag);
        return sum;
    }

    // See Section 3.3.
    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Cnumbers that = (Cnumbers) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    // See Section 3.3.
    public int hashCode() {
        return Objects.hash(re, im);
    }

//    // sample client for testing
//    public static void main(String[] args) {
//        immersiveMath.Cnumbers a = new immersiveMath.Cnumbers(5.0, 6.0);
//        immersiveMath.Cnumbers b = new immersiveMath.Cnumbers(-3.0, 4.0);
//
//        StdOut.println("a            = " + a);
//        StdOut.println("b            = " + b);
//        StdOut.println("Re(a)        = " + a.re());
//        StdOut.println("Im(a)        = " + a.im());
//        StdOut.println("b + a        = " + b.plus(a));
//        StdOut.println("a - b        = " + a.minus(b));
//        StdOut.println("a * b        = " + a.multiply(b));
//        StdOut.println("b * a        = " + b.multiply(a));
//        StdOut.println("a / b        = " + a.divides(b));
//        StdOut.println("(a / b) * b  = " + a.divides(b).multiply(b));
//        StdOut.println("conj(a)      = " + a.conjugate());
//        StdOut.println("|a|          = " + a.abs());
//        StdOut.println("tan(a)       = " + a.tan());
//    }

    public double scalarProduct(Cnumbers c) {
    	return re * c.re() + im * c.im();
    }
    public static Cnumbers i(){
    	return new Cnumbers(0,1);
    }
    public double euclideanDistance(Cnumbers x){
        return this.minus(x).abs();
    }
    public double lobachDistance(Cnumbers x){
        // d(x,y) = arth| (x - y) / ( 1 - xy*) where
        // * is conjugation
        return hyperbolicFunctions.arth(this.minus(x).divides(
            (new Cnumbers (1)).minus(this.multiply(x.conjugate()))).abs());
    }
}
