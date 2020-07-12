package geometryObjects;

import geometryObjects.EuclidG.Point;

import java.util.List;

public abstract  class LobachObject extends GeometryObject {

    public abstract EuclidObject toEuclidObject();

    public List<Point> intersection (EuclidObject a){
        return Intersection.inter(this, a);
    }

    public List<Point> intersection (LobachObject a){
        return Intersection.inter(this, a);
    }
}
