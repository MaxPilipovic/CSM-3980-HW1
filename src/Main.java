import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random constant = new Random();
        int[] x = randomGenerate();
        int[] y = randomGenerate();
        //Random constant
        int c = constant.nextInt();

        //Single thread (one thread does all the multiplications and additions) AND Stopwatch
        long start = System.nanoTime();
        int[] singleThread = singleThread(x, y, c);
        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000.0)+ "ms");
        }

    //Generates random values for newVector
    private static final int Size = 134217728;  //ASK MERTZ WHAT IS 1GB AGAIN //2GB
    private static int[] randomGenerate() {
        Random randomVector = new Random();
        int[] newVector = new int[Size];

        //Loops over array and generates values
        for (int i = 0; i < Size; i++) {
            newVector[i] = randomVector.nextInt();
        }
        return newVector;
    }

    //Single thread
    private static int[] singleThread(int[] x, int[] y,int c) {
        int[] z = new int[Size]; //Z Array
        for (int i = 0; i < Size; i++) { //Looping through array
            z[i] = (x[i] * c) + y[i]; //Formula
        }
        return z;
    }
}
