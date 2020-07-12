package geometryObjects.LobachG;

import core.Data;
import immersiveMath.Cnumbers;
import geometryObjects.EuclidObject;
import geometryObjects.EuclidG.Circle;
import geometryObjects.LobachObject;

public class LobachCircle extends LobachObject {
    Cnumbers center;
    double r;
    static double R;

    public LobachCircle(Cnumbers center, double r ){
        this.r = r;
        this.center = center;
	R = Data.R;
    }

    @Override
    public EuclidObject toEuclidObject() {
	double c = center.abs();
	double A = (c - R)/(c + R);
	double w1 = R * (1 + A * Math.exp(r))/(1 - A * Math.exp(r));
	double w2 = R * (1 - A * Math.exp(-r))/(1 + A * Math.exp(-r));
	Cnumbers euclidCenter = center.scale((w1 + w2) / (2 * center.abs()));
	double euclidRadius   = Math.abs((w2 - w1) / 2);
        return new Circle(euclidCenter, euclidRadius);
    }
}
