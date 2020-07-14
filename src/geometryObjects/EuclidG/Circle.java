package geometryObjects.EuclidG;

import geometryObjects.EuclidObject;
import immersiveMath.Complex;

public class Circle extends EuclidObject {

	double radius;
    Complex center;

    public Circle(Complex C, double R){
    	center = C; 
    	radius = R;
    }

	public double getRadius() {
		return radius;
	}

	public Complex getCenter() {
		return center;
	}

    @Override
	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Circle that = (Circle) x;
		return (that.radius == this.radius) && (this.center.equals(that.center));
	}

}
