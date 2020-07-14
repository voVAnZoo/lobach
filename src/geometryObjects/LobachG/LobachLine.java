package geometryObjects.LobachG;

import core.Data;
import geometryObjects.EuclidObject;
import geometryObjects.LobachObject;
import geometryObjects.EuclidG.Line;
import geometryObjects.EuclidG.Circle;
import immersiveMath.Complex;


public class LobachLine extends LobachObject {
	// Class of line in Lobachevskian plane.
	// We use the Poincare disk model.
	// A line is Euclidean circle with center out of plane (1)
	// or Euclidean straight line passing zero point (2). 
	// (1) characterized by its center
	// (2) characterized by vector with length lower than getRadius of the disc

	private Complex center;
	private static double planeRadius;
	private static double radius;

	public LobachLine(Complex c){
		planeRadius = Data.R;
		center = c;
		if (isEuclideanLine()){
			radius = Double.POSITIVE_INFINITY;
		}else {
			// If circles with center O and getRadius planeRadius
			// is orthogonal to circle with center O' and getRadius planeRadius',
			// planeRadius^2 + planeRadius'^2 = OO'^2
			radius = Math.sqrt(center.abs() * center.abs() - planeRadius * planeRadius);
		}
	}

	// draw a straight line through 2 points
	public LobachLine(Complex I, Complex K){
		planeRadius = Data.R;
		// J is a center of segment IK
		Complex J = I.add(K).mul(0.5);
		// l is a perpendicular bisector to the segment IK
		Complex l = I.sub(K).mul(Complex.i);

		// Euclidean straight line passing zero point case 
		if (K.dot(l) == 0){
			center = l.mul(planeRadius /2/l.abs());
			return;
		}

		// t = [(I,K) - planeRadius^2]/(K,l)
		double t = (planeRadius * planeRadius - K.dot(I)) / K.dot(l) / 2;
		center = J.add(l.mul(t));

		if (isEuclideanLine()){
			radius = Double.POSITIVE_INFINITY;
		}else {
			// If circles with center O and getRadius planeRadius
			// is orthogonal to circle with center O' and getRadius planeRadius',
			// planeRadius^2 + planeRadius'^2 = OO'^2
			radius = Math.sqrt(center.abs() * center.abs() - planeRadius * planeRadius);
		}
	}

	public String toString() {
		return center.toString();
	}

	public boolean isEuclideanLine() { 
		return center.abs() < planeRadius;
	}

	public double getRadius(){
		return radius;
	}

	public double euclideanDistance(Complex x){
		if (isEuclideanLine()) return center.dot(x);
		// sqrt((x - x_0)^2 + (y - y_0)^2) - getRadius
		return center.sub(x).abs() - getRadius();
	}

	public Complex reflect(Complex x){
		if (isEuclideanLine()){
			// x -> x - (x,n)n/n^2 where
			// n = center
			return x.sub(center.mul(x.dot(center)/(center.abs()*center.abs())));
		} 
		// x -> (c + radius^2/(x - c)*) where
		// * is conjugation,
		// radius is a getRadius
		return center.add(x.sub(center).reciprocate().conjugate().mul(getRadius()* getRadius()));
	}

	// normal passing through the point x
	public LobachLine normal(Complex x) {
		// if line doesn't contain x, the normal is line xx', where
		// x and x' are symmetric with respect to the line
		if (euclideanDistance(x) != 0) return new LobachLine(x,reflect(x));
		if (isEuclideanLine()){
			// if x is zero, normal is a euclidean straight line
			if (x.equals(new Complex(0))) return new LobachLine(center.mul(Complex.i));
			// otherwise let N be a center of normal 
			// line OX contains N, and |NX| equals to the getRadius of normal
			// let OX = t NX
			// disc of lobachevsky plate is orthogonal to the normal:
			// (t+1)^2 |OX|^2 = |ON|^2 = |NX|^2 + planeRadius^2 = t^2 |OX|^2 + planeRadius^2
			// so t = (planeRadius^2 - |OX|^2)/(2|OX|^2)
			// ON = OX + lt where l = OX
			Complex l = x;
		}
		// let N be a center of normal 
		// tangent contains N, and |NX| equals to the getRadius of normal
		// let l be direction vector of the tangent
		Complex l = x.sub(center).mul(Complex.i);
		// disc of lobachevsky plate is orthogonal to the normal:
		// |ON|^2 = |NX|^2 + planeRadius^2
		// ... so t = (planeRadius^2 - |OX|^2)/(2(OX,l))
		// ON = OX + lt 
		double t = (planeRadius * planeRadius - x.abs()*x.abs())/(2*x.dot(l));
		return new LobachLine(x.add(l.mul(t)));
	}

	@Override
	public EuclidObject toEuclidObject() {
		if (isEuclideanLine()){
			return new Line(center);
		}
		return new Circle(center, getRadius());
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
