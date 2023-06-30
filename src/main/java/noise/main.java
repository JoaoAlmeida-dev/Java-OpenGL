package noise;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class main {
    public static void main(String[] args) {
        int size = 16;
        int nOctaves = 5;

        float[] floats = generateSeed(16);
        generateOctaves(floats, size, nOctaves);
    }

    /***
     * Interpolating method
     * @param start start of the interval
     * @param end end of the interval
     * @param count count of output interpolated numbers
     * @return array of interpolated number with specified count
     */
    public static double[] interpolate(double start, double end, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("interpolate: illegal count!");
        }
        double[] array = new double[count + 1];
        for (int i = 0; i <= count; ++i) {
            array[i] = start + i * (end - start) / count;
        }
        return array;
    }


    private static float[] generateOctaves(float[] floats, int size, int nOctaves) {


        float[] seed = new float[size];
        for (int octave = 0; octave < nOctaves; octave++) {
            for (int i = 0; i < size; i++) {
                int sampleRate = size / nOctaves;

            }
        }

        return new float[0];
    }

    private static float[] generateSeed(int size) {
        Random rand = new Random();

        float[] seed = new float[size];
        for (int i = 0; i < size; i++) {
            seed[i] = rand.nextFloat();
        }

        System.out.println(Arrays.toString(seed));
        return seed;

    }


}
