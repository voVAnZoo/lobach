package geometryObjects;

import geometryObjects.EuclidG.Point;

import java.util.List;

public abstract  class EuclidObject extends GeometryObject {

    public List<Point> intersection (EuclidObject a){
        return Intersection.inter(this, a);
    }

    public List<Point> intersection (LobachObject a){
        return Intersection.inter(this, a);
    }
}
