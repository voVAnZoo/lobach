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
		Cnumbers l = I.minus(K).times(new Cnumbers(0,1));

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
	public double radius(){
		// Euclidean straight line case 
		if (center.abs() < R) return Double.POSITIVE_INFINITY;
		// If circles with center O and radius R
		// is orthogonal to circle with center O' and radius R',
		// R^2 + R'^2 = OO'^2
		return Math.sqrt(center.abs()*center.abs() - R*R);
	}
	public double euclideanDistance(Cnumbers x){
		// Euclidean straight line case 
		if (center.abs() < R) return center.scalarProduct(x);
		// sqrt((x - x_0)^2 + (y - y_0)^2) - radius
		return center.minus(x).abs() - radius();
	}

}
