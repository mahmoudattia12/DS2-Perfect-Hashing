package ADT_HASH;

import Dictionary.Main;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.*;

class HASH_N2Test{

//    public Main m = new Main();


    private HASH_N2 hashTable;
    @Test
    public void testInsert() {

        hashTable = new HASH_N2(10);
        // Test inserting a key that already exists
        hashTable.insert(5);
        assertFalse(hashTable.insert(5));
        hashTable.delete(5);
        // Test inserting more keys than the size allows
        for (int i = 1; i <= 10; i++) {
            assertTrue(hashTable.insert(i));
        }
        assertFalse(hashTable.insert(11));
        System.out.println(hashTable.getNumOfCollisions());
    }

    @Test
    public void testDeleteSearch() {
        hashTable = new HASH_N2(10);
        // Test searching deleting a key that doesn't exist
        assertFalse(hashTable.search(5));
        assertFalse(hashTable.delete(5));

        // Test searching & deleting an existing key
        assertTrue(hashTable.insert(10));
        assertTrue(hashTable.search(10));
        assertTrue(hashTable.delete(10));
        assertFalse(hashTable.search(10));

    }

    @Test
    public void testBatchInsertDelete() {
        hashTable = new HASH_N2(20);
        // Test batch inserting keys with some duplicates
        Long[] keys = {(long)7, (long)1, (long)2, (long)8, (long)3, (long)4, (long)5, (long)5, (long)6, (long)7, (long)8, (long)9};
        assertEquals(9, hashTable.batchInsert(keys));
        for(long i : keys){
            assertTrue(hashTable.search(i));
        }
        assertEquals(9, hashTable.batchDelete(keys));
        for(long i : keys){
            assertFalse(hashTable.search(i));
        }
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }
    @Test
    void test1(){
        HASH_N2 hashn2 = new HASH_N2(20);
        //check insert
        assertTrue(hashn2.insert(10));
        assertTrue(hashn2.insert(100));
        assertTrue(hashn2.insert(1000));
        assertFalse(hashn2.insert(10));
        assertFalse(hashn2.insert(100));
        assertFalse(hashn2.insert(1000));
        //check search & delete
        assertTrue(hashn2.search(100));
        assertTrue(hashn2.delete(100));
        assertFalse(hashn2.search(100));
        //check batch insert
        Long[] arr = {(long)1, (long)20, (long)200, (long)2000, (long)30, (long)40, (long)50, (long)10, (long)100, (long)1000};
        assertTrue(hashn2.batchInsert(arr) == 8);
        //check batch delete
        Long[] arr2 = {(long)20, (long)200, (long)1, (long)10, (long)333, (long)444, (long)555, (long)777,(long)8888};
        assertTrue(hashn2.batchDelete(arr2) == 4);
        System.out.println(hashn2.getNumOfCollisions());
        assertTrue(hashn2.getNumOfCollisions() <= 2);
    }

    @Test
    void test2(){
        HASH_N2 hashTable = new HASH_N2(50);
        long[] keys = {9, 11, 15, 2, 12, 10, 6, 13, 5, 14, 7, 4, 1, 3, 8, 16, 23, 18, 19, 20, 17, 25, 22, 21, 24, 26, 27, 28, 30, 29, 37, 38, 31, 35, 40, 39, 36, 34, 33, 32, 42, 41, 47, 48, 45, 43, 50, 49, 46, 44};
        // Insert keys
        for (long key : keys) {
            assertTrue(hashTable.insert(key));
        }

        // Search for keys
        for (long key : keys) {
            assertTrue(hashTable.search(key));
        }

        // Delete keys
        for (long key : keys) {
            assertTrue(hashTable.delete(key));
        }

        // Verify all keys are no longer present
        for (long key : keys) {
            assertFalse(hashTable.search(key));
        }
        System.out.println(hashTable.getNumOfCollisions());
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    @Test
    void test3(){
        HASH_N2 table = new HASH_N2(20000);
        Long[] keys = new Long[20000];
        for (int i = 0; i < 20000; i++) {
            keys[i] = (long)i;
        }

        // Insert keys
        for (long key : keys) {
            assertTrue(table.insert(key));
        }

        // Search keys
        for (long key : keys) {
            assertTrue(table.search(key));
        }

        // Delete keys
        for (long key : keys) {
            assertTrue(table.delete(key));
        }

        // Check that keys are no longer present
        for (long key : keys) {
            assertFalse(table.search(key));
        }
        System.out.println(table.getNumOfCollisions());
        assertTrue(table.getNumOfCollisions() <= 2);
    }

    @Test
    void test4(){
        hashTable = new HASH_N2(5);
        // Insert 5 elements
        assertTrue(hashTable.insert(1));
        assertTrue(hashTable.insert(2));
        assertTrue(hashTable.insert(3));
        assertTrue(hashTable.insert(4));
        assertTrue(hashTable.insert(5));

        // Try to insert more elements than the given size
        assertFalse(hashTable.insert(6));
        assertFalse(hashTable.insert(7));

        // Search for existing and non-existing keys
        assertTrue(hashTable.search(3));
        assertTrue(hashTable.search(5));
        assertFalse(hashTable.search(6));
        assertFalse(hashTable.search(8));

        // Batch delete non-existing keys
        Long[] nonExistingKeys = {(long)6, (long)7, (long)8};
        assertEquals(0, hashTable.batchDelete(nonExistingKeys));

        // Delete existing keys
        assertTrue(hashTable.delete(1));
        assertTrue(hashTable.delete(3));
        assertTrue(hashTable.delete(5));

        // Search for deleted keys
        assertFalse(hashTable.search(1));
        assertFalse(hashTable.search(3));
        assertFalse(hashTable.search(5));

        // Batch delete remaining keys
        Long[] remainingKeys = {(long)2, (long)4};
        assertEquals(2, hashTable.batchDelete(remainingKeys));

        // Verify the hash table is empty
        for (int i = 0; i < 10; i++) {
            assertFalse(hashTable.search(i));
        }

        // Check the number of rehash tries
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    @Test
    void test5(){
        int N = 10000;
        hashTable = new HASH_N2(N);
        Long[] arr = new Long[N];
        for(int i = -5000; i < 5000; i++){
            arr[i+N/2] = (long)i*5;
        }
        assertEquals(hashTable.batchInsert(arr), N);
        assertFalse(hashTable.insert(5));
        for(long i : arr){
            assertTrue(hashTable.search(i));
        }
        assertEquals(hashTable.batchDelete(arr), N);
        System.out.println(hashTable.getNumOfCollisions());
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    //testing main dictionary with n2

    @Test
    void test6(){
        int N = 20;
        HASH_N2 hashTable = new HASH_N2(N);
        assertTrue(hashTable.insert(Main.stringToLong("cat")));
        assertTrue(hashTable.insert(Main.stringToLong("car")));
        assertTrue(hashTable.insert(Main.stringToLong("dog")));
        assertTrue(hashTable.insert(Main.stringToLong("elephant")));
        assertTrue(hashTable.insert(Main.stringToLong("ds")));
        assertFalse(hashTable.insert(Main.stringToLong("dog")));

        String[] keys = {"a", "aa", "aaa", "b", "bb", "a", "bbb", "c", "cc","b", "ccc", "d", "dd", "ddd", "e","d" ,"ee", "eee"};
        Long[] lK = new Long[keys.length];
        for(int i = 0; i < lK.length; i++){
            lK[i] = Main.stringToLong(keys[i]);
        }
        //inserting with batch insert with duplicated keys
        assertTrue(hashTable.batchInsert(lK) == 15);
        //test insert more than the allowed capacity
        assertFalse(hashTable.insert(Main.stringToLong("apple")));
        assertFalse(hashTable.insert(Main.stringToLong("banana")));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(lK[i]));
        }
        assertFalse(hashTable.search(Main.stringToLong("apple")));

        assertTrue(hashTable.delete(Main.stringToLong("aa")));
        assertFalse(hashTable.search(Main.stringToLong("aa")));

        assertTrue(hashTable.batchDelete(lK) == 14);

        for(int i = 0; i < keys.length; i++){
            assertFalse(hashTable.search(lK[i]));
        }
        assertTrue(hashTable.search(Main.stringToLong("ds")));
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    //testing bigger main cli scenario
    @Test
    void test7(){
        int N = 1010;
        HASH_N2 hashTable = new HASH_N2(N);
        Long[] keys = generateStrings(1000);
        assertTrue(hashTable.batchInsert(keys) == 1000);

        assertTrue(hashTable.insert(Main.stringToLong("apple")));
        assertTrue(hashTable.insert(Main.stringToLong("mac")));
        assertTrue(hashTable.insert(Main.stringToLong("linux")));
        assertTrue(hashTable.insert(Main.stringToLong("windows")));
        assertFalse(hashTable.insert(Main.stringToLong("mac")));

        assertTrue(hashTable.search(Main.stringToLong("linux")));
        assertFalse(hashTable.search(Main.stringToLong("zzz")));
        assertTrue(hashTable.delete(Main.stringToLong("mac")));
        assertFalse(hashTable.search(Main.stringToLong("mac")));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.batchDelete(keys) == 1000);
        for(int i = 0; i < keys.length; i++){
            assertFalse(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.delete(Main.stringToLong("apple")));
        assertFalse(hashTable.search(Main.stringToLong("apple")));

        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    //much bigger main test
    @Test
    void test8(){
        int N = 10010;
        HASH_N2 hashTable = new HASH_N2(N);
        Long[] keys = generateStrings(10000);
        assertTrue(hashTable.batchInsert(keys) == 10000);

        assertTrue(hashTable.insert(Main.stringToLong("ds")));
        assertTrue(hashTable.insert(Main.stringToLong("os")));
        assertTrue(hashTable.insert(Main.stringToLong("control")));
        assertTrue(hashTable.insert(Main.stringToLong("paradigms")));
        assertFalse(hashTable.insert(Main.stringToLong("os")));

        assertTrue(hashTable.search(Main.stringToLong("control")));
        assertFalse(hashTable.search(Main.stringToLong("zzz")));
        assertTrue(hashTable.delete(Main.stringToLong("os")));
        assertFalse(hashTable.search(Main.stringToLong("os")));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.batchDelete(keys) == 10000);
        for(int i = 0; i < keys.length; i++){
            assertFalse(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.delete(Main.stringToLong("control")));
        assertFalse(hashTable.search(Main.stringToLong("control")));
        System.out.println(hashTable.getNumOfCollisions());
        assertTrue(hashTable.getNumOfCollisions() <= 2);
    }

    private Long[] generateStrings(int size){
        Set<String> distinctStrings = new HashSet<>();

        while (distinctStrings.size() < size) {
            String newString = UUID.randomUUID().toString();
            String truncatedString = newString.substring(0, Math.min(newString.length(), 8));
            distinctStrings.add(truncatedString);
        }
        String[] distinctStringsArray = new String[distinctStrings.size()];
        distinctStrings.toArray(distinctStringsArray);
        Long[] longArray = new Long[distinctStringsArray.length];
        for(int i = 0; i < distinctStringsArray.length; i++){
            longArray[i] = Main.stringToLong(distinctStringsArray[i]);
        }
        return longArray;
    }
}