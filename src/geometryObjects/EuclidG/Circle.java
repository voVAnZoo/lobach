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
}
