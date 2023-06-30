package noise;

public class Vec2 {
    private float x;
    private float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static float dotProduct(Vec2 vec1, Vec2 vec2) {
        return vec1.getX() * vec2.getY() + vec1.getY() * vec2.getY();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }

    public Vec2 sum(float i) {
        return new Vec2(getX() + i, getY() + i);
    }

    public Vec2 multiply(float i) {
        return new Vec2(getX() * i, getY() * i);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
