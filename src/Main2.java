import java.util.Random;

public class Main2 {

    public static void main(String[] args) {
        int[] x = randomGenerate();
        int[] y = randomGenerate();
        //Random constant
        int c = 1;

        //Multi-thread adjacesent (where "adjacent" threads scale and add adjacent elements) AND Stopwatch
        for (int numThread = 1; numThread <= 2 * Cores; numThread++) {
            long start = System.nanoTime();
            int[] multiThread = multiThreadA(x, y, c, numThread);
            System.out.println("Number of threads: " + numThread);
            System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000.0)+ "ms");
            System.out.println("");
        }
    }

    //Generates random values for newVector
    private static final int Cores = 6; //Number of Cores
    private static final int Size = 268435456; //ASK MERTZ WHAT IS 1GB AGAIN
    private static int[] randomGenerate() {
        Random randomVector = new Random();
        int[] newVector = new int[Size];

        //Loops over array and generates values
        for (int i = 0; i < Size; i++) {
            newVector[i] = randomVector.nextInt();
        }
        return newVector;
    }

    //Multi-threaded adjacesent
    private static int[] multiThreadA(int[] x, int[] y, int c, int numThread) {
        int[] z = new int [Size]; //Z array
        Thread[] threads = new Thread[numThread]; //Array to hold threads
        for (int i = 0; i < numThread; i++) { //Loop through number of threads
            int start = i * (Size / numThread);
            int finish = (i + 1) * (Size / numThread);
            if (i == numThread - 1) { //Adjusting the last thread to cover the entire array
                finish = Size;
            }
            threads[i] = new myThread(x, y, z, c, start, finish); //Starting and initalizing thread
            threads[i].start();
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