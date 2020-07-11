package geometryObjects.LobachG;

import geometryObjects.EuclidObject;
import geometryObjects.LobachObject;

public class LobachCircle extends LobachObject {
    double r;
    double center;

    double w1;
    double w2;

    public LobachCircle(double r, double center){
        this.r = r;
        this.center = center;
    }


    @Override
    public EuclidObject toEuclidObject() {
        return null;
    }
}
