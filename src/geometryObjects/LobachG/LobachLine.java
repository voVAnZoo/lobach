package geometryObjects.LobachG;

import core.Data;
import immersiveMath.Cnumbers;


class LobachLine {
	// Class of line in Lobachevskian plane.
	// We use the Poincare disk model.
	// A line is Euclidean circle with center out of plane (1)
	// or Euclidean straight line passing zero point (2). 
	// (1) characterized by its center
	// (2) characterized by vector with length lower than radius of the disc 

	private Cnumbers center; 
	private static double R;

	public LobachLine(Cnumbers c){
		R = Data.R;
		center = c;
	}

	// draw a straight line through 2 points
	public LobachLine(Cnumbers I, Cnumbers K){
		R = Data.R;
		// J is a center of segment IK
		Cnumbers J = I.plus(K).scale(0.5);
		// l is a perpendicular bisector to the segment IK
		Cnumbers l = I.minus(K).multiply(Cnumbers.i());

		// Euclidean straight line passing zero point case 
		if (K.scalarProduct(l) == 0){
			center = l.scale(R/2/l.abs());
			return;
		}

		// t = [(I,K) - R^2]/(K,l)
		double t = (R*R - K.scalarProduct(I)) / K.scalarProduct(l) / 2;
		center = J.plus(l.scale(t));
	}

	public String toString() {
		return center.toString();
	}
	public boolean isEuclideanLine() { 
		return center.abs() < R;
	}
	public double radius(){
		if (isEuclideanLine()) return Double.POSITIVE_INFINITY;
		// If circles with center O and radius R
		// is orthogonal to circle with center O' and radius R',
		// R^2 + R'^2 = OO'^2
		return Math.sqrt(center.abs()*center.abs() - R*R);
	}
	public double euclideanDistance(Cnumbers x){
		if (isEuclideanLine()) return center.scalarProduct(x);
		// sqrt((x - x_0)^2 + (y - y_0)^2) - radius
		return center.minus(x).abs() - radius();
	}
	public Cnumbers reflect(Cnumbers x){
		if (isEuclideanLine()){
			// x -> x - (x,n)n/n^2 where
			// n = center
			return x.minus(center.scale(x.scalarProduct(center)/(center.abs()*center.abs())));
		} 
		// x -> (c + r^2/(x - c)*) where
		// * is conjugation,
		// r is a radius
		return center.plus(x.minus(center).reciprocal().conjugate().scale(radius()*radius()));
	}	
	// normal passing through the point x
	public LobachLine normal(Cnumbers x) {
		// if line doesn't contain x, the normal is line xx', where
		// x and x' are symmetric with respect to the line
		if (euclideanDistance(x) != 0) return new LobachLine(x,reflect(x));
		if (isEuclideanLine()){
			// if x is zero, normal is a euclidean straight line
			if (x.equals(new Cnumbers(0))) return new LobachLine(center.multiply(Cnumbers.i()));
			// otherwise let N be a center of normal 
			// line OX contains N, and |NX| equals to the radius of normal
			// let OX = t NX
			// disc of lobachevsky plate is orthogonal to the normal:
			// (t+1)^2 |OX|^2 = |ON|^2 = |NX|^2 + R^2 = t^2 |OX|^2 + R^2
			// so t = (R^2 - |OX|^2)/(2|OX|^2)
			// ON = OX + lt where l = OX
			Cnumbers l = x;
		}
		// let N be a center of normal 
		// tangent contains N, and |NX| equals to the radius of normal
		// let l be direction vector of the tangent
		Cnumbers l = x.minus(center).multiply(Cnumbers.i());
		// disc of lobachevsky plate is orthogonal to the normal:
		// |ON|^2 = |NX|^2 + R^2
		// ... so t = (R^2 - |OX|^2)/(2(OX,l))
		// ON = OX + lt 
		double t = (R*R - x.abs()*x.abs())/(2*x.scalarProduct(l));
		return new LobachLine(x.plus(l.scale(t)));
	}
}
