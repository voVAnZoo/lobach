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
	public Line(Cnumbers norm){
		point = new Cnumbers(0);
		normal = norm;
	}

}
