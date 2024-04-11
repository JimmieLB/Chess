public class Vector2 {
    int x;
    int y;
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void step(int x, int y) {
        this.x += x;
        this.y += y;
    }
    public void step(Vector2 direction) {
        this.x += direction.x;
        this.y += direction.y;
    }

    public Vector2 getStep(int x, int y) {
        Vector2 output = new Vector2(this.x, this.y);
        output.step(x,y);
        return output;
    }

    public Vector2 clone() {
        return new Vector2(this.x, this.y);
    }

    public void goTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector2 other) {
        return other.x == this.x && other.y == this.y;
    }
}
