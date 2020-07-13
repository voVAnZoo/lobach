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
        List<Point> out = new ArrayList<Point>();

        // let the first circl be the bigest one
        if (a.getR() < b.getR()){
            Circle tmp = a;
            a = b;
            b = tmp;
        }
        // r1 > r2
        double r1 = a.getR();
        double r2 = b.getR();
        Cnumbers o1 = a.getCenter();
        Cnumbers o2 = b.getCenter();
        double o1o2 = o1.minus(o2).abs();
        // the o2 lies inside the first circle 
        if (o1o2 < r1){
            if (o1o2 + r2 < r1) {
                return out;
            }
            // contact case
            if (o1o2 + r2 == r1) {
                Cnumbers contactP = o1.plus(o2.minus(o1).scale(r1/o1o2));
                out.add(new Point(contactP));
                return out;
            }
        }
        else {
            if (o1o2 > r1 + r2) {
                return out;
            }
            // contact case
            if (o1o2 == r1 + r2) {
                Cnumbers contactP = o1.plus(o2.minus(o1).scale(r1/o1o2));
                out.add(new Point(contactP));
                return out;
            }
        }
        if (o1o2 == 0) {
            return out;
        }

        // q is the point of intersection of radical axis and o1o2
        // oq = (o1o2^2 + r1^2 - r2^2) / (2 o1o2)
        double o1q = (o1o2*o1o2 + r1*r1 - r2*r2) / (2 * o1o2);
        // h is a heigh of intersection points on the radical axis
        double h = Math.sqrt(r1*r1 - o1q*o1q);
        // the points of intersection are o1 + (o2-o1)/o1o2 (o1q +- i h)
        Cnumbers p1 = o1.plus(o2.minus(o1).scale(1/o1o2).multiply(new Cnumbers(o1q, h)));
        out.add(new Point(p1));
        Cnumbers p2 = o1.plus(o2.minus(o1).scale(1/o1o2).multiply(new Cnumbers(o1q, -h)));
        out.add(new Point(p2));
        return out;
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
        List<Point> out = new ArrayList<Point>();

        if(b.getP().minus(a.getCenter()).abs() == a.getR()){
            out.add(b);
        }

        return out;
    }

    public static List<Point> intersection(Circle a, Line b){
        List<Point> out = new ArrayList<Point>();

        //r^2 = |(p-c)|^2 + t(tan_(p-c)_ + _tan_ (p-c)) + t^2 (|tan|^2)
        //p-c = h
        Cnumbers tan = b.getNormal().multiply(Cnumbers.i());
        Cnumbers h = b.getPoint().minus(a.getCenter());

        double c2 = (h.abs()*h.abs()) - (a.getR()*a.getR());
        double b2 = (tan.multiply(h.conjugate()).plus(tan.conjugate().multiply(h))).re();
        double a2 = tan.abs()*tan.abs();

        double d = b2*b2 - (4*a2*c2);

        if(d == 0){
            double t = -b2/(2*a2);
            out.add(new Point(b.getPoint().plus(tan.scale(t))));
        }

        if(d > 0){
            double t1 = (-b2 + Math.sqrt(d))/(2*a2);
            double t2 = (-b2 - Math.sqrt(d))/(2*a2);

            out.add(new Point(b.getPoint().plus(tan.scale(t1))));
            out.add(new Point(b.getPoint().plus(tan.scale(t2))));
        }

        return out;
    }

    public static List<Point> intersection(Point a, Line b){
        List<Point> out = new ArrayList<Point>();

        if(a.getP().minus(b.getPoint()).scalarProduct(b.getNormal()) == 0){
            out.add(a);
        }
        return out;
    }
}
