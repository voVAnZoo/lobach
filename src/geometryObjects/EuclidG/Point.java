package geometryObjects.EuclidG;

import geometryObjects.EuclidObject;
import immersiveMath.Cnumbers;

public class Point extends EuclidObject {

    private Cnumbers p;

    public Point(Cnumbers p){
    	this.p = p;
    }

    public Cnumbers getP(){
        return p;
    }

    @Override
	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Point that = (Point) x;
		return this.p == that.p;
	}
    
    public double euclideanDistance(Point x){
        return this.getP().euclideanDistance(x.getP());
    }
    public double lobachDistance(Point x){
        return this.getP().lobachDistance(x.getP());
    }
}
