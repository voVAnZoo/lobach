package geometryObjects;

import geometryObjects.EuclidG.Circle;
import geometryObjects.EuclidG.Line;
import geometryObjects.EuclidG.Point;
import immersiveMath.Complex;

import java.util.List;
import java.util.ArrayList;

public class Intersector {

    public static List<Point> intersection(Object a1, Object b1) throws IntersecException {
        EuclidObject a = null;
        EuclidObject b = null;

        if (a1 instanceof LobachObject) {
            a = ((LobachObject) a1).toEuclidObject();
        }
        if (a1 instanceof EuclidObject) {
            a = (EuclidObject) a1;
        }

        if (b1 instanceof LobachObject) {
            b = ((LobachObject) b1).toEuclidObject();
        }
        if (b1 instanceof EuclidObject) {
            b = (EuclidObject) b1;
        }

        if ((a != null) && (b != null)){
            return intersection(a, b);
        }else {
            throw new IntersecException("Null Object");
        }
    }

    private static List<Point> intersection(EuclidObject a, EuclidObject b) throws IntersecException {
        if(a.equals(b)){
            throw new IntersecException("Objects are equal");
        }
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

    private static List<Point> intersection(Circle a, Circle b){
        List<Point> out = new ArrayList<Point>();

        // let the first circl be the bigest one
        if (a.getRadius() < b.getRadius()){
            Circle tmp = a;
            a = b;
            b = tmp;
        }
        // r1 > r2
        double r1 = a.getRadius();
        double r2 = b.getRadius();
        Complex o1 = a.getCenter();
        Complex o2 = b.getCenter();
        double o1o2 = o1.sub(o2).abs();
        // the o2 lies inside the first circle 
        if (o1o2 < r1){
            if (o1o2 + r2 < r1) {
                return out;
            }
            // contact case
            if (o1o2 + r2 == r1) {
                Complex contactP = o1.add(o2.sub(o1).mul(r1/o1o2));
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
                Complex contactP = o1.add(o2.sub(o1).mul(r1/o1o2));
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
        Complex p1 = o1.add(o2.sub(o1).mul(1/o1o2).mul(new Complex(o1q, h)));
        out.add(new Point(p1));
        Complex p2 = o1.add(o2.sub(o1).mul(1/o1o2).mul(new Complex(o1q, -h)));
        out.add(new Point(p2));
        return out;
    }

    private static List<Point> intersection(Point a, Point b){
        return new ArrayList<Point>();
    }

    private static List<Point> intersection(Line a, Line b){
	    List<Point> out = new ArrayList<Point>();
	    if (a.getNormal().equals(b.getNormal())){
		    return out;
	    }
	    // let tau be the directing vector; tau = n * i
	    // the line equation is p + t tau
	    // ((tau_a t + p_a - p_b),n_b) = 0
	    // t = ((p_b - p_a),n_b)/(tau_a, n_b)
	    // the point of intersection is p_a + tau_a t
	    Complex tau = a.getNormal().mul(Complex.i);
	    double t = b.getPoint().sub(a.getPoint()).dot(b.getNormal())/tau.dot(b.getNormal());
	    out.add(new Point(a.getPoint().add(tau.mul(t))));
	    return out;
    }

    private static List<Point> intersection(Circle a, Point b){
        List<Point> out = new ArrayList<Point>();

        if(b.getPoint().sub(a.getCenter()).abs() == a.getRadius()){
            out.add(b);
        }

        return out;
    }

    private static List<Point> intersection(Circle a, Line b){
        List<Point> out = new ArrayList<Point>();

        //r^2 = |(p-c)|^2 + t(tan_(p-c)_ + _tan_ (p-c)) + t^2 (|tan|^2)
        //p-c = h
        Complex tan = b.getNormal().mul(Complex.i);
        Complex h = b.getPoint().sub(a.getCenter());

        double c2 = (h.abs()*h.abs()) - (a.getRadius()*a.getRadius());
        double b2 = (tan.mul(h.conjugate()).add(tan.conjugate().mul(h))).re();
        double a2 = tan.abs()*tan.abs();

        double d = b2*b2 - (4*a2*c2);

        if(d == 0){
            double t = -b2/(2*a2);
            out.add(new Point(b.getPoint().add(tan.mul(t))));
        }

        if(d > 0){
            double t1 = (-b2 + Math.sqrt(d))/(2*a2);
            double t2 = (-b2 - Math.sqrt(d))/(2*a2);

            out.add(new Point(b.getPoint().add(tan.mul(t1))));
            out.add(new Point(b.getPoint().add(tan.mul(t2))));
        }

        return out;
    }

    private static List<Point> intersection(Point a, Line b){
        List<Point> out = new ArrayList<Point>();

        if(a.getPoint().sub(b.getPoint()).dot(b.getNormal()) == 0){
            out.add(a);
        }
        return out;
    }
}
