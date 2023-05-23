package Dictionary;

import ADT_HASH.HASH_N;
import ADT_HASH.HASH_N2;
import AVL_Tree.AVL;
import RB_Tree.RB;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CompareTests {
    HASH_N hash_n;
    HASH_N2 hash_n2;
    AVL<Long> avl;
    RB<Long> rb;

    @Test
    void test1(){
        int size = 5000;
        System.out.println("size: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
//        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
//        assertTrue(hash_n2.batchInsert(arr) == size);

        memoryStats();

//        System.out.println("n2 Space: " + hash_n2.getSpace());
//        System.out.println("n Space: " + hash_n.getSpace());
//
//        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
//        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test2(){
        int size = 100;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test3(){
        int size = 500;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test4(){
        int size = 1000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test5(){
        int size = 5000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test6(){
        int size = 10000;
        System.out.println("\nsize: " + size + "\n");
        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }
    @Test
    void test7(){
        int size = 20000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);

//        assertTrue(hash_n.batchInsert(arr) == size);
        for(int i = 0; i < arr.length; i++){
            assertTrue(hash_n.insert(arr[i]));
        }
        assertTrue(hash_n2.batchInsert(arr) == size);

        System.out.println("n2 Space: " + hash_n2.getSpace());
        System.out.println("n Space: " + hash_n.getSpace());

        System.out.println("\nn2 rebuilds: " + hash_n2.getNumOfCollisions());
        System.out.println("n rebuilds: " + hash_n.getNumOfCollisions());
    }

    //---------------------------------------------------------------------------------------
    @Test
    void test8(){
        int size = 10;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }

    @Test
    void test9(){
        int size = 100;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test10(){
        int size = 1000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test11(){
        int size = 5000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);

        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test12(){
        int size = 10000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test13(){
        int size = 20000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
        //hash_n2
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            hash_n2.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test14(){
        int size = 100000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
//        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
//        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
//        //hash_n2
//        start = System.nanoTime();
//        for(int i = 0; i < size; i++){
//            hash_n2.search(arr[i]);
//        }
//        end = System.nanoTime();
//        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }
    @Test
    void test15(){
        int size = 500000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
//        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
//        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
//        //hash_n2
//        start = System.nanoTime();
//        for(int i = 0; i < size; i++){
//            hash_n2.search(arr[i]);
//        }
//        end = System.nanoTime();
//        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }

    @Test
    void test16(){
        int size = 1000000;
        System.out.println("\nsize: " + size + "\n");

        Long[] arr = generateUniqueStrings(size);
        avl = new AVL<>();
        rb = new RB<>();
        hash_n = new HASH_N(size);
//        hash_n2 = new HASH_N2(size);
        for(int i = 0; i < size; i++){
            avl.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            rb.insert(arr[i]);
        }
        for(int i = 0; i < size; i++){
            hash_n.insert(arr[i]);
        }
//        hash_n.batchInsert(arr);
//        hash_n2.batchInsert(arr);


        //hash_n
        long start = System.nanoTime();
        for (int i = 0; i < size; i++){
            hash_n.search(arr[i]);
        }
        long end = System.nanoTime();
        System.out.println("hash_n: " + ((end-start)/size));
//        //hash_n2
//        start = System.nanoTime();
//        for(int i = 0; i < size; i++){
//            hash_n2.search(arr[i]);
//        }
//        end = System.nanoTime();
//        System.out.println("hash_n2: " + ((end-start)/size));
        //avl
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            avl.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("avl: " + ((end-start)/size));
        //rb
        start = System.nanoTime();
        for(int i = 0; i < size; i++){
            rb.search(arr[i]);
        }
        end = System.nanoTime();
        System.out.println("rb: " + ((end-start)/size));

    }

    public static Long[] generateUniqueStrings(int size) {
        int maxLength = 8;
        HashSet<String> uniqueSet = new HashSet<>();
        String[] uniqueStrings = new String[size];

        for (int i = 0; i < size; i++) {
            StringBuilder sb = new StringBuilder();
            while (true) {
                // Generate a random string with maximum length of 8 characters
                for (int j = 0; j < maxLength; j++) {
                    char randomChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
                    sb.append(randomChar);
                }
                String randomString = sb.toString();

                // Check if the string is already in the set
                if (!uniqueSet.contains(randomString)) {
                    uniqueSet.add(randomString);
                    uniqueStrings[i] = randomString;
                    break;
                } else {
                    sb.setLength(0);
                }
            }
        }
        Long[] longArray = new Long[uniqueStrings.length];
        for(int i = 0; i < uniqueStrings.length; i++){
            longArray[i] = Main.stringToLong(uniqueStrings[i]);
        }
        return longArray;
    }

    public void memoryStats() {
        int mb = 1024 * 1024;
        // get Runtime instance
        Runtime instance = Runtime.getRuntime();
        System.out.println("Heap Memory [MB]\n");
        // available memory
        System.out.println("Total Memory: " + instance.totalMemory() / mb);
        // free memory
        System.out.println("Free Memory: " + instance.freeMemory() / mb);
        // used memory
        System.out.println("Used Memory: "
                + (instance.totalMemory() - instance.freeMemory()) / mb);
        // Maximum available memory
        System.out.println("Max Memory: " + instance.maxMemory() / mb);
    }

}