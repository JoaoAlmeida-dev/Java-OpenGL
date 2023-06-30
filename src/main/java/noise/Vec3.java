package noise;

public class Vec3 {
    private float x;
    private float y;
    private float z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static float dotProduct(Vec3 vec1, Vec3 vec2) {
        return vec1.getX() * vec2.getY() + vec1.getY() * vec2.getY() + vec1.getZ() * vec2.getZ();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ')';
    }

    public Vec3 sum(float i) {
        return new Vec3(getX() + i, getY() + i, getZ() + i);
    }

    public Vec3 multiply(float i) {
        return new Vec3(getX() * i, getY() * i, getZ() * i);
    }

    public Float[] toArray() {
        return new Float[]{getX(), getY(), getZ()};
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
