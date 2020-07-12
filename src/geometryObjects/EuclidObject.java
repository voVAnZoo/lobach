package geometryObjects;

import geometryObjects.EuclidG.Point;

import java.util.List;

public abstract  class EuclidObject extends GeometryObject {

    public abstract boolean equals(Object obj);

    public List<Point> intersection (EuclidObject a) throws IntersecException{
        IntersecException e = new IntersecException("Objects are equal");
        if (this.equals(a)){
            throw e;
        }
        return GeometryObject.intersection(this, a);
    }

    public List<Point> intersection (LobachObject a) throws IntersecException{
        IntersecException e = new IntersecException("Objects are equal");
        if (this.equals(a.toEuclidObject())){
            throw e;
        }
        return GeometryObject.intersection(this, a.toEuclidObject());
    }
}
