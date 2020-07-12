package geometryObjects;

import geometryObjects.EuclidG.Circle;
import geometryObjects.EuclidG.Line;
import geometryObjects.EuclidG.Point;

import java.util.List;

public class GeometryObject {
    public static List<Point> intersection(EuclidObject a, EuclidObject b){
        if(a.getClass() == Point.class){
            if(b.getClass() == Point.class){
                return intersection((Point) a, (Point)b);
            }

            if(b.getClass() == Line.class){
                return intersection((Point) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return intersection((Circle) b, (Point) a);
            }
        }

        if(a.getClass() == Line.class){
            if(b.getClass() == Point.class){
                return intersection((Point) b, (Line) a);
            }

            if(b.getClass() == Line.class){
                return intersection((Line) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return intersection((Circle) b, (Line) a);
            }
        }

        if(a.getClass() == Circle.class){
            if(b.getClass() == Point.class){
                return intersection((Circle) a, (Point)b);
            }

            if(b.getClass() == Line.class){
                return intersection((Circle) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return intersection((Circle) b, (Circle) a);
            }
        }

        return null;
    }

    public static List<Point> intersection(Circle a, Circle b){
        return null;
    }

    public static List<Point> intersection(Point a, Point b){
        return null;
    }

    public static List<Point> intersection(Line a, Line b){
        return null;
    }

    public static List<Point> intersection(Circle a, Point b){
        return null;
    }

    public static List<Point> intersection(Circle a, Line b){
        return null;
    }

    public static List<Point> intersection(Point a, Line b){
        return null;
    }
}
