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
		Cnumbers t = (new Cnumbers (K.scalarProduct(I) - R*R)).scale(1 / K.scalarProduct(l));
		center = J.minus(l.times(t));
		// System.out.println("(K,I) = " + K.scalarProduct(I));
		// System.out.println("R = " + R);
		// System.out.println("(K,I) - R^2= " + ((K.scalarProduct(I)) - R*R));
		// System.out.println("J = " + J.toString());
		// System.out.println("t = " + t.toString());
		// System.out.println("center = " + center.toString());
	}

	public String toString() {
		return center.toString();
	}
}
