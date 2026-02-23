// ...existing code...
package sorting;

import java.util.Arrays;
import java.util.Random;

public class TestSuite
{
    // Run a bunch of basic tests on your sorting methods
    public static void run(int[] testInput, int loops)
    {
        boolean pass = true;

        System.out.println("Running test with input: " + Arrays.toString(testInput));

        BubbleSort b = new BubbleSort();
        pass &= basicTest(b, testInput, loops);

        MergeSort m = new MergeSort();
        pass &= basicTest(m, testInput, loops);

        SelectionSort s = new SelectionSort();
        pass &= basicTest(s, testInput, loops);

        InsertionSort i = new InsertionSort();
        pass &= basicTest(i, testInput, loops);

        if (pass == true)
        {
            System.out.println("--- TEST PASSED! Congrats! ---");
        }
        else
        {
            System.out.println("--- TEST FAILED! :( ---");
        }
    }

    public static boolean basicTest(Sorter s, int[] unsorted, int loops)
    {
        boolean result = false;
        String className = s.getClass().getSimpleName();
        System.out.println("\n--- Testing: " + className + " ---");

        // Save the start time of the test loop.
        long startTime = System.nanoTime();
        for (int x = 0; x < loops; x++) {
            // Copy the unsorted array to avoid sorting it.
            int[] test = unsorted.clone();
            // Sort the test array.
            s.sort(test);
            // Check the results
            result = checkResults(test, unsorted);
        }
        long endTime = System.nanoTime();

        // Compute the duration in seconds.
        double duration = (endTime - startTime) / 1000000000.0;
        // Print results
        System.out.println(className + " took: " + duration + " seconds for " + loops + " loops.");

        return result;
    }

    public static boolean checkResults(int[] sorted, int[] unsorted)
    {
        // Uncomment for debug.
        //System.out.println("    Unsorted: " + Arrays.toString(unsorted));
        //System.out.println("    Sorted:   " + Arrays.toString(sorted));

        // Ensure that the array length is the same.
        if (sorted.length != unsorted.length)
        {
            System.out.println("Error! Mismatching lengths.");
            return false;
        }

        // Traverse the array, looking for elements that are out of order.
        for (int i = 0; i < sorted.length - 1; i++) {
            // If we find a single out of order element, then stop - the array is UNSORTED!
            if (sorted[i] > sorted[i + 1]) {
                System.out.println("Error! sorted[" + i + "] (" + sorted[i] + " > sorted[" + i + 1 + "] (" + sorted[i + 1] + ")");
                return false;
            }
        }
        return true;
    }

    // Generate a random int array of given size with values in [min, max].
    public static int[] generateRandomArray(int size, int min, int max) {
        if (size <= 0) return new int[0];
        if (min > max) { int t = min; min = max; max = t; }
        Random rnd = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rnd.nextInt(max - min + 1) + min;
        }
        return arr;
    }

    // Optional main to run the TestSuite with a generated random array.
    public static void main(String[] args) {
        int size = 20;
        int min = 0;
        int max = 100;
        int loops = 1000;

        if (args.length >= 1) {
            try { size = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }
        if (args.length >= 2) {
            try { loops = Integer.parseInt(args[1]); } catch (NumberFormatException ignored) {}
        }

        int[] testInput = generateRandomArray(size, min, max);
        run(testInput, loops);
    }
}
