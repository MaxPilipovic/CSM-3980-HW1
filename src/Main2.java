import java.util.Random;

public class Main2 {

    public static void main(String[] args) {
        Random constant = new Random();
        int[] x = randomGenerate();
        int[] y = randomGenerate();
        //Random constant
        int c = constant.nextInt();

        //Multi-thread adjacesent (where "adjacent" threads scale and add adjacent elements) AND Stopwatch
        for (int numThread = 1; numThread <= 2 * Cores + 4; numThread++) {
            long start = System.nanoTime();
            int[] multiThread = multiThreadA(x, y, c, numThread);
            System.out.println("Number of threads: " + numThread);
            System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000.0)+ "ms");
            System.out.println("");
        }
    }

    //Generates random values for newVector
    private static final int Cores = Runtime.getRuntime().availableProcessors(); //Number of Cores
    private static final int Size = 134217728; //ASK MERTZ WHAT IS 1GB AGAIN
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
        int[] z = new int [Size];
        Thread[] threads = new Thread[numThread];
        for (int threadID = 0; threadID < numThread; threadID++) { //Loop through threads
            int start = threadID;
            threads[threadID] = new myThread(x, y, z, c, start, numThread);
            //threads[i] = new myThread(x, y, z, c, start, finish); //Starting and initalizing thread
            threads[threadID].start();
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
        private final int numThread;
        //private final int finish;

        //Constuctor
        public myThread(int[] x, int[] y, int[] z, int c, int start, int numThread) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.c = c;
            this.start = start;
            this.numThread = numThread;
        }

        //Run method
        public void run() {
            for (int i = start; i < Size; i += numThread) {
                z[i] = (x[i] * c) + y[i];
            }
        }

    }
}
