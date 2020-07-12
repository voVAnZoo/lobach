package geometryObjects.EuclidG;

import geometryObjects.EuclidObject;
import immersiveMath.Cnumbers;

public class Point extends EuclidObject {
	Cnumbers p;
	public boolean equals(Object x) {
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Point that = (Point) x;
		return this.p == that.p; 
	}
}
