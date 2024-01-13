import java.util.Scanner;

public class mainCounter {
    private static final int minThread = 1;
    private static final int maxThread = 12; //1 to 2c. where c is number of cores //6 cores on my pc
    private static final int minSize = 1;
    private static final int maxSize = 999999;
    private static final int counterMinimum = 1;
    private static final int counterMaximum = 6;

    public static void main(String[] args ) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select type of Counter to test. (Options are 1. Unsyncronized Counter, 2. Synchronized Counter, 3. Semaphore Counter, 4.Reentrant Lock" +
                " 5. ReentrantReadWriteLock 6. Atomic Integer Counter");
        int counterNumber = Reader(scanner, counterMinimum, counterMaximum);

        System.out.println("Enter number of threads");
        int threadNumber = Reader(scanner, minThread, maxThread);

        System.out.println("Enter size of counter");
        int counterSize = Reader(scanner, minSize, maxSize);



    }

    private static int Reader(Scanner scanner, int x, int y ) {
        while (true) {
            System.out.println("Enter a number between: " + x + " and " + y);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                if (input >= x && input <= y) {
                    return input;
                }
                else {
                    System.out.println("That is not a valid number");
                    scanner.next();
                }
            }
        }
    }
}
