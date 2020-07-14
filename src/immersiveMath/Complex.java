package immersiveMath;

import java.util.Objects;

public class Complex {
    private final double re;   // the real part
    private final double im;   // the imaginary part

    public static final Complex i = new Complex(0,1);

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    public Complex(double real) {
        re = real;
        im = 0;
    }

    // return a string representation of the invoking immersiveMath.Complex object
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

    // return a new immersiveMath.Complex object whose value is (this + b)
    public Complex add(Complex b) {
        Complex a = this;             // invoking object
        double real = a.re + b.re;
        double imag = a.im + b.im;
        return new Complex(real, imag);
    }

    // return a new immersiveMath.Complex object whose value is (this - b)
    public Complex sub(Complex b) {
        Complex a = this;
        double real = a.re - b.re;
        double imag = a.im - b.im;
        return new Complex(real, imag);
    }

    // return a new immersiveMath.Complex object whose value is (this * b)
    public Complex mul(Complex b) {
        Complex a = this;
        double real = a.re * b.re - a.im * b.im;
        double imag = a.re * b.im + a.im * b.re;
        return new Complex(real, imag);
    }

    // return a new object whose value is (this * alpha)
    public Complex mul(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    // return a new immersiveMath.Complex object whose value is the conjugate of this
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    // return a new immersiveMath.Complex object whose value is the reciprocate of this
    public Complex reciprocate() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // return a / b
    public Complex div(Complex b) {
        Complex a = this;
        return a.mul(b.reciprocate());
    }

    // return a new immersiveMath.Complex object whose value is the complex exponential of this
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    // return a new immersiveMath.Complex object whose value is the complex sine of this
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    // return a new immersiveMath.Complex object whose value is the complex cosine of this
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    // return a new immersiveMath.Complex object whose value is the complex tangent of this
    public Complex tan() {
        return sin().div(cos());
    }

    // a static version of add
    public static Complex add(Complex a, Complex b) {
        double real = a.re + b.re;
        double imag = a.im + b.im;
        Complex sum = new Complex(real, imag);
        return sum;
    }

    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.re == that.re) && (this.im == that.im);
    }

    public int hashCode() {
        return Objects.hash(re, im);
    }

    //scalar product
    public double dot(Complex c) {
    	return re * c.re() + im * c.im();
    }

    public double euclideanDistance(Complex x){
        return this.sub(x).abs();
    }

    public double lobachDistance(Complex x){
        // d(x,y) = arth| (x - y) / ( 1 - xy*) where
        // * is conjugation
        return ImmersiveMath.arth(this.sub(x).div(
            (new Complex(1)).sub(this.mul(x.conjugate()))).abs());
    }
}
