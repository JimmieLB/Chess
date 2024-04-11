import java.util.ArrayList;

public class LocationArray {
    ArrayList<Vector2> locations;

    public LocationArray() {
        locations = new ArrayList<Vector2>();
    }

    public void add(Vector2 location) {
        locations.add(location.clone());
    }

    public Vector2[] toArray() {
        Vector2[] output = new Vector2[locations.size()];
        for (int i= 0; i < locations.size(); i++) {
            output[i] = locations.get(i);
        }
        return output;
    }
}
