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
        int[] arr = generateDistinctArray(10000);
        arr[0] = 5; arr[1] = 5;
        HASH_N2 hashn2 = new HASH_N2(arr);
//        Random random = new Random();
//        for(int i = 0; i < 1000; i++){
//            System.out.println(hashn2.insert(random.nextInt()));
//
//        }

        System.out.println("n : " +hashn2.n);
        System.out.println("tries: " + hashn2.rehashTries);
        System.out.println("table size: " + hashn2.hashTable.length);


    }

    public static int[] generateDistinctArray(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Array size must be at least 1.");
        }

        int[] array = new int[size];
        Set<Integer> set = new HashSet<>();

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int num = random.nextInt(); // Generate a random number

            // Check if the number is already in the set
            while (set.contains(num)) {
                num = random.nextInt(); // Generate a new random number
            }

            array[i] = num; // Assign the unique number to the array
            set.add(num); // Add the number to the set
        }

        return array;
    }
}


