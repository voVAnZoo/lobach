package geometryObjects.EuclidG;

import immersiveMath.Cnumbers;
import geometryObjects.EuclidObject;

public class Line extends EuclidObject {

	private Cnumbers point;
	private Cnumbers normal;

	public Line(Cnumbers p, Cnumbers norm){
		point = p;
		normal = norm;
	}

	public Line(Cnumbers norm) {
		point = new Cnumbers(0);
		normal = norm;
	}

	public Cnumbers getNormal() {
	    return normal;
	}

	public Cnumbers getPoint() {
	    return point;
	}

	public double euclideanDistance(Cnumbers x){
		return normal.scalarProduct(x) - normal.scalarProduct(point);
	}

	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Line that = (Line) x;
		if (this.normal != that.getNormal()) return false;
		return euclideanDistance(that.getPoint()) == 0;
	}
}
