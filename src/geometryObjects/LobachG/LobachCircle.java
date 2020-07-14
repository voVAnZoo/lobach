package geometryObjects.LobachG;

import core.Data;
import immersiveMath.Complex;
import geometryObjects.EuclidObject;
import geometryObjects.EuclidG.Circle;
import geometryObjects.LobachObject;

public class LobachCircle extends LobachObject {
    Complex center;
    double radius;
    static double planeRadius;

    public LobachCircle(Complex center, double r ){
        this.radius = r;
        this.center = center;
	    planeRadius = Data.R;
    }

    @Override
    public EuclidObject toEuclidObject() {
	    double c = center.abs();
	    double A = (c - planeRadius)/(c + planeRadius);
	    double w1 = planeRadius * (1 + A * Math.exp(radius))/(1 - A * Math.exp(radius));
	    double w2 = planeRadius * (1 - A * Math.exp(-radius))/(1 + A * Math.exp(-radius));
	    Complex euclidCenter = center.mul((w1 + w2) / (2 * center.abs()));
	    double euclidRadius   = Math.abs((w2 - w1) / 2);
        return new Circle(euclidCenter, euclidRadius);
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
