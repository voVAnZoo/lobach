package geometryObjects;

public class IntersecException extends Exception {

    public IntersecException(String crash){
        super(crash);
    }

    public IntersecException(){
        super();
    }
}
