package geometryObjects.EuclidG;

import geometryObjects.EuclidObject;
import immersiveMath.Cnumbers;

public class Circle extends EuclidObject {

	double r;
    Cnumbers center;

    public Circle(Cnumbers C, double R){
    	center = C; 
    	r = R; 
    }

	public double getR() {
		return r;
	}

	public Cnumbers getCenter() {
		return center;
	}

    @Override
	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Circle that = (Circle) x;
		return (that.r == this.r) && (this.center.equals(that.center));
	}

}
