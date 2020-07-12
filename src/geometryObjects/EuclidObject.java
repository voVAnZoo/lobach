package geometryObjects;

import geometryObjects.EuclidG.Point;

import java.util.List;

public abstract  class EuclidObject extends GeometryObject {

    public List<Point> intersection (EuclidObject a){
        return GeometryObject.intersection(this, a);
    }

    public List<Point> intersection (LobachObject a){
        return GeometryObject.intersection(this, a.toEuclidObject());
    }
}
