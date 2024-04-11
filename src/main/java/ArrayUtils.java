import java.lang.reflect.Array;
import java.util.ArrayList;

public class ArrayUtils {
    public static <T> T[] add(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public static Vector2[] VectorListToArray(ArrayList<Vector2> v) {
        Vector2[] output = new Vector2[v.size()];
        for (int i = 0; i < v.size(); i++) {
            output[i] = v.get(i);
        }
        return output;
    }

    public static boolean includes(Vector2[] array, Vector2 vector) {
        for (Vector2 v : array) {
            if (v.x == vector.x && v.y == vector.y) {
                return true;
            }
        }
        return false;
    }
}
