import ADT_HASH.HASH_N2;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Main {


    public static void main(String[] args) {
        int[] arr = generateDistinctArray(100);
        HASH_N2 hashn2 = new HASH_N2(arr.length);
        hashn2.batchInsert(arr);
        System.out.println("nTries: " + hashn2.getNumberOfRebuild());
    }

    public static int[] generateDistinctArray(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Array size must be at least 1.");
        }

        int[] array = new int[size];
        Set<Integer> set = new HashSet<>();

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int num = Math.abs(random.nextInt()); // Generate a random number

            // Check if the number is already in the set
            while (set.contains(num)) {
                num = Math.abs(random.nextInt()); // Generate a new random number
            }

            array[i] = num; // Assign the unique number to the array
            set.add(num); // Add the number to the set
        }

        return array;
    }
}


