import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] x = randomGenerate();
        int[] y = randomGenerate();
        //Random constant
        int c = 1;

        //Single threaded (one thread does all the multiplications and additions) AND Stopwatch
        long start = System.nanoTime();
        int[] singleThreaded = singleThread(x, y, c);
        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000.0)+ "ms");

        //Multi-threaded adjacesent (where "adjacent" threads scale and add adjacent elements) AND Stopwatch

        //Multi-threaded blocks (where threads break the vector into contiguous block of roughly the same size) AND Stopwatch

        }

    //Generates random values for newVector
    private static final int SIZE = 268405456;
    private static int[] randomGenerate() {
        Random randomVector = new Random();
        int[] newVector = new int[SIZE];

        //Generates random values for newVector
        for (int i = 0; i < SIZE; i++) {
            newVector[i] = randomVector.nextInt();
        }
        return newVector;
    }

    //Multi-threaded adjacesent (where "adjacent" threads scale and add adjacent elements)
    private static int[] multiThreadA(int[] x, int[] y, int c) {
        int[] z = new int [SIZE];
        Thread[] threads = new Thread[numThread];
        for (int i = 0; i < numThread; i++) { //Loop through number of threads
            final int numThreads = i; //Set final thread value to a variable

        }

        return z;
    }

    //Multi-threaded blocks (where threads break the vector into contiguous block of roughly the same size)
    private static int[] multiThreadB(int[] x, int[] y, int c) {
        int[] z = new int [SIZE];
        Thread[] threads = new Thread(numThread);
        for (int i = 0; i < numThread; i++) {

        }

        return z;
    }

    //Single threaded (one thread does all the multiplications and additions)
    private static int[] singleThread(int[] x, int[] y,int c) {
        int[] z = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            z[i] = (x[i] * c) + y[i];
        }
        return z;
    }
}
