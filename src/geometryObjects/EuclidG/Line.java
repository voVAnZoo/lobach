package geometryObjects.EuclidG;

import immersiveMath.Complex;
import geometryObjects.EuclidObject;

public class Line extends EuclidObject {

	private Complex point;
	private Complex normal;

	public Line(Complex p, Complex norm){
		point = p;
		normal = norm;
	}

	public Line(Complex norm) {
		point = new Complex(0);
		normal = norm;
	}

	public Complex getNormal() {
	    return normal;
	}

	public Complex getPoint() {
	    return point;
	}

	public double euclideanDistance(Complex x){
		return normal.dot(x) - normal.dot(point);
	}

	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Line that = (Line) x;
		if (this.normal != that.getNormal()) return false;
		return euclideanDistance(that.getPoint()) == 0;
	}
}
