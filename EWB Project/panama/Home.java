package panama;
import static java.lang.Math.sqrt;
/** Represents a home on the map
 * @author Cassidy Mountjoy
 */
public class Home {
    Home(int ID, double latitude, double longitude) {
        _lat = latitude;
        _long = longitude;
        _name = ID;

    }

    /** Returns latitude of home.
     */
    public double get_lat() {
        return _lat;
    }

    /** Returns longitude of home.
     */
    public double get_long() {
        return _long;
    }

    /** Returns ID of home.
     */
    public int get_name() {
        return _name;
    }

    /** Returns the distance from
     *  this to another home.
     */
    public double distanceto(Home to) {
        double dx = _long - to.get_long();
        double dy = _lat - to.get_lat();
        return sqrt(dx * dx + dy * dy);
    }

private double _lat;
    private double _long;
    private int _name;
}
