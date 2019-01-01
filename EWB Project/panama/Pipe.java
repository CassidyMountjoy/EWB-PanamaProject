package panama;


public class Pipe {
    /** A Pipe whose name is which houses it connects
     * and of given LENGTH. */
    Pipe(String name, double length) {
        _name = name;
        _length = length;
    }

    @Override
    public String toString() {
        return _name;
    }

    /** Return the length of this road segment. */
    public double length() {
        return _length;
    }

    /** The name given to this segment. */
    private String _name;
    /** The length of this segment. */
    private double _length;

}

