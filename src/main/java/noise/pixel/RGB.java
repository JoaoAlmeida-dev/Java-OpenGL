package noise.pixel;

public class RGB {
    private static final int MAXCOLORVALUE = 256;
    private int red;
    private int green;
    private int blue;

    public RGB() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public RGB(int red, int green, int blue) {
        assert red > 0 && red < 256 && green > 0 && green < 256 && blue > 0 && blue < 256;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "{" +
                "r=" + red +
                ", g=" + green +
                ", b=" + blue +
                '}';
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        assert red > 0 && red < 256;
        this.red = red;
    }

    public float getRedPercentage() {
        return (float) red / MAXCOLORVALUE;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        assert green > 0 && green < 256;
        this.green = green;
    }

    public float getGreenPercentage() {
        return (float) green / MAXCOLORVALUE;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        assert blue > 0 && blue < 256;
        this.blue = blue;
    }

    public float getBluePercentage() {
        return (float) blue / MAXCOLORVALUE;
    }
}
