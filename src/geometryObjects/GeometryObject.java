package geometryObjects;

import geometryObjects.EuclidG.Circle;
import geometryObjects.EuclidG.Line;
import geometryObjects.EuclidG.Point;
import immersiveMath.Cnumbers;

import java.util.List;
import java.util.ArrayList;

public class GeometryObject {

    public static List<Point> intersection(EuclidObject a, EuclidObject b) {
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
                return intersection((Line) b, (Line) a);
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
                return intersection((Circle) a, (Circle) b);
            }
        }

        return null;
    }

    public static List<Point> intersection(Circle a, Circle b){
        return null;
    }

    public static List<Point> intersection(Point a, Point b){
        return new ArrayList<Point>();
    }

    public static List<Point> intersection(Line a, Line b){
	List<Point> out = new ArrayList<Point>();
    	if (a.getNormal().equals(b.getNormal())){
		return out; 
	}
	// let tau be the directing vector; tau = n * i 
	// the line equation is p + t tau
	// ((tau_a t + p_a - p_b),n_b) = 0
	// t = ((p_b - p_a),n_b)/(tau_a, n_b)
	// the point of intersection is p_a + tau_a t
	Cnumbers tau = a.getNormal().multiply(Cnumbers.i());
	double t = b.getPoint().minus(a.getPoint()).scalarProduct(b.getNormal())/tau.scalarProduct(b.getNormal());
	out.add(new Point(a.getPoint().plus(tau.scale(t))));
	return out;
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
