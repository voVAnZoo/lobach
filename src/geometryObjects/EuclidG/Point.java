package geometryObjects.EuclidG;

import geometryObjects.EuclidObject;
import immersiveMath.Complex;

public class Point extends EuclidObject {

    private Complex point;

    public Point(Complex p){
    	this.point = p;
    }

    public Complex getPoint(){
        return point;
    }

    @Override
	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Point that = (Point) x;
		return this.point == that.point;
	}
    
    public double euclideanDistance(Point x){
        return this.getPoint().euclideanDistance(x.getPoint());
    }

    public double lobachDistance(Point x){
        return this.getPoint().lobachDistance(x.getPoint());
    }
}
