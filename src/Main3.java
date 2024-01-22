import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

public class Main3 {

    public static void main(String[] args) {
        Random constant = new Random();
        int[] x = randomGenerate();
        int[] y = randomGenerate();
        //Random constant
        int c = constant.nextInt(100);

        System.out.println("Cores: " + Runtime.getRuntime().availableProcessors());
        //Multi-thread blocks (where threads break the vector into contiguous block of roughly the same size) AND Stopwatch
        for (int numThread = 1; numThread <= 2 * Cores + 4; numThread++) {
            long start = System.nanoTime();
            int[] multiThread = multiThreadB(x, y, c, numThread);
            System.out.println("Number of threads: " + numThread);
            System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000.0)+ "ms");
            System.out.println("");
        }
    }

    //Generates random values for newVector
    private static final int Cores = Runtime.getRuntime().availableProcessors();
    private static final int Size = 536870912; //ASK MERTZ WHAT IS 1GB AGAIN
    private static int[] randomGenerate() {
        Random randomVector = new Random();
        int[] newVector = new int[Size];

        //Loops over array and generates values
        for (int i = 0; i < Size; i++) {
            newVector[i] = randomVector.nextInt();
        }
        return newVector;
    }

    //Multi-threaded blocks method, Creates array to hold threads, calculates remainder and initializes thread
    private static int[] multiThreadB(int[] x, int[] y, int c, int numThread) {
        int[] z = new int [Size];
        Thread[] threads = new Thread[numThread];
        int blocks = (Size / numThread); //Caculates size of block
        int blockRemainder = (Size % numThread); //Calculates remainder
        int start = 0; //Start of the block for each thread
        int finish; //Finish of the block for each thread
        for (int i = 0; i < numThread; i++) { //Loop over number of threads
            if (blockRemainder > 0) {
                finish = start + blocks + 1; //Processes extra element
                blockRemainder--;
            } else {
                finish = start + blocks;
            }
            threads[i] = new myThread(x, y, z, c, start, finish); //Creates new Thread
            threads[i].start(); //Intializes
            start = finish; //Starts next thread
        }
        for (int i = 0; i < threads.length; i++) { //Loop to wait for each thread to finish and begin next
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Something is not working");
            }
        }

        return z;
    }

    private static class myThread extends Thread {
        private final int[] x;
        private final int[] y;
        private final int[] z;
        private final int c;
        private final int start;
        private final int finish;

        //Constuctor
        public myThread(int[] x, int[] y, int[] z, int c, int start, int finish) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.c = c;
            this.start = start;
            this.finish = finish;
        }

        //Run method
        public void run() {
            for (int i = start; i < finish; i++) {
                z[i] = (x[i] * c) + y[i];
            }
        }

    }
}
