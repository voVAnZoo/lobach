package geometryObjects;

import geometryObjects.EuclidG.Point;

import java.util.List;

public abstract  class LobachObject extends GeometryObject {

    public abstract EuclidObject toEuclidObject();

    public abstract boolean equals(Object obj);

    public List<Point> intersection (EuclidObject a) throws IntersecException {
        IntersecException e = new IntersecException("Objects are equal");
        if(this.toEuclidObject().equals(a)){
            throw e;
        }
        return GeometryObject.intersection(this.toEuclidObject(), a);
    }

    public List<Point> intersection (LobachObject a) throws IntersecException {
        IntersecException e = new IntersecException("Objects are equal");
        if(this.toEuclidObject().equals(a)){
            throw e;
        }
        return GeometryObject.intersection(this.toEuclidObject(), a.toEuclidObject());
    }
}
