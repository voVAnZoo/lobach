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

        if(a.getCenter().minus(b.getCenter()).abs() < a.getR() + b.getR()) {
            double A = (b.getCenter().re() - a.getCenter().re());
            double B = (b.getCenter().im() - a.getCenter().im());
            double C = (A * A) + (B * B) + (a.getR() * a.getR()) - (b.getR() * b.getR());
            A *= (-2);
            B *= (-2);

            double a2 = (A * A) + (B * B);
            double b2 = (-2) * C * B;
            double c2 = (C * C) - (A * A * a.getR() * a.getR());

            double d = (b2 * b2) - (4 * a2 * c2);

            if (d == 0) {
                double y = -b2 / (2 * a2);
                if (A != 0) {
                    double x = (C - (B * y)) / A;
                    //я хочу спать, О Великая Богиня Иштар, молю,
                    // пусть это код кто-нибудь допишет вместо меня
                    // зарание спасибо! =)
                }
            }
        }

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

        Cnumbers tan = b.getNormal().multiply(Cnumbers.i());
        if(a.getP().minus(b.getPoint()).phase() == tan.phase()){
            out.add(a);
        }

        return out;
    }
}
