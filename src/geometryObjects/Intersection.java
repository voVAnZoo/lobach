package geometryObjects;

import geometryObjects.EuclidG.Circle;
import geometryObjects.EuclidG.Line;
import geometryObjects.EuclidG.Point;
import geometryObjects.LobachG.LobachLine;
import immersiveMath.Cnumbers;

import java.util.List;

public class Intersection {

    public static List<Point> inter(EuclidObject a, EuclidObject b){
        if(a.getClass() == Point.class){
            if(b.getClass() == Point.class){
                return interPP((Point) a, (Point)b);
            }

            if(b.getClass() == Line.class){
                return interPL((Point) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return interCP((Circle) b, (Point) a);
            }
        }

        if(a.getClass() == Line.class){
            if(b.getClass() == Point.class){
                return interPL((Point) b, (Line) a);
            }

            if(b.getClass() == Line.class){
                return interLL((Line) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return interCL((Circle) b, (Line) a);
            }
        }

        if(a.getClass() == Circle.class){
            if(b.getClass() == Point.class){
                return interCP((Circle) a, (Point)b);
            }

            if(b.getClass() == Line.class){
                return interCL((Circle) a, (Line) b);
            }

            if(b.getClass() == Circle.class){
                return interCC((Circle) b, (Circle) a);
            }
        }

        return null;
    }

    public static List<Point> inter(EuclidObject a, LobachObject b){
        return inter(a,b.toEuclidObject());
    }

    public static List<Point> inter(LobachObject a, EuclidObject b){
        return inter(a.toEuclidObject(),b);
    }

    public static List<Point> inter(LobachObject a, LobachObject b){
        return inter(a.toEuclidObject(),b.toEuclidObject());
    }

    public static List<Point> interCC (Circle a, Circle b){
        return null;
    }

    public static List<Point> interPP (Point a, Point b){
        return null;
    }

    public static List<Point> interLL (Line a, Line b){
        return null;
    }

    public static List<Point> interCP (Circle a, Point b){
        return null;
    }

    public static List<Point> interCL (Circle a, Line b){
        return null;
    }

    public static List<Point> interPL (Point a, Line b){
        return null;
    }
}
