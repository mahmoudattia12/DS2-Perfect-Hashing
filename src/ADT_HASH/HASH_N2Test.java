package ADT_HASH;

import org.junit.jupiter.api.Test;

import java.lang.instrument.Instrumentation;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HASH_N2Test {

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
        System.out.println(hashTable.getNumberOfRebuild());
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
        int[] keys = {7, 1, 2, 8, 3, 4, 5, 5, 6, 7, 8, 9};
        assertEquals(9, hashTable.batchInsert(keys));
        for(int i : keys){
            assertTrue(hashTable.search(i));
        }
        assertEquals(9, hashTable.batchDelete(keys));
        for(int i : keys){
            assertFalse(hashTable.search(i));
        }
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
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
        int[] arr = {1, 20, 200, 2000, 30, 40, 50, 10, 100, 1000};
        assertTrue(hashn2.batchInsert(arr) == 8);
        //check batch delete
        int[] arr2 = {20, 200, 1, 10, 333, 444, 555, 777,8888};
        assertTrue(hashn2.batchDelete(arr2) == 4);
        System.out.println(hashn2.getNumberOfRebuild());
        assertTrue(hashn2.getNumberOfRebuild() <= 2);
    }

    @Test
    void test2(){
        HASH_N2 hashTable = new HASH_N2(50);
        int[] keys = {9, 11, 15, 2, 12, 10, 6, 13, 5, 14, 7, 4, 1, 3, 8, 16, 23, 18, 19, 20, 17, 25, 22, 21, 24, 26, 27, 28, 30, 29, 37, 38, 31, 35, 40, 39, 36, 34, 33, 32, 42, 41, 47, 48, 45, 43, 50, 49, 46, 44};
        // Insert keys
        for (int key : keys) {
            assertTrue(hashTable.insert(key));
        }

        // Search for keys
        for (int key : keys) {
            assertTrue(hashTable.search(key));
        }

        // Delete keys
        for (int key : keys) {
            assertTrue(hashTable.delete(key));
        }

        // Verify all keys are no longer present
        for (int key : keys) {
            assertFalse(hashTable.search(key));
        }
        System.out.println(hashTable.getNumberOfRebuild());
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    @Test
    void test3(){
        hashTable = new HASH_N2(1000);
        int[] keys = new int[1000];
        for (int i = 0; i < 1000; i++) {
            keys[i] = i;
        }

        // Insert keys
        for (int key : keys) {
            assertTrue(hashTable.insert(key));
        }

        // Search keys
        for (int key : keys) {
            assertTrue(hashTable.search(key));
        }

        // Delete keys
        for (int key : keys) {
            assertTrue(hashTable.delete(key));
        }

        // Check that keys are no longer present
        for (int key : keys) {
            assertFalse(hashTable.search(key));
        }
        System.out.println(hashTable.getNumberOfRebuild());
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
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
        int[] nonExistingKeys = {6, 7, 8};
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
        int[] remainingKeys = {2, 4};
        assertEquals(2, hashTable.batchDelete(remainingKeys));

        // Verify the hash table is empty
        for (int i = 0; i < 10; i++) {
            assertFalse(hashTable.search(i));
        }

        // Check the number of rehash tries
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    @Test
    void test5(){
        int N = 10000;
        hashTable = new HASH_N2(N);
        int[] arr = new int[N];
        for(int i = -5000; i < 5000; i++){
            arr[i+N/2] = i*5;
        }
        assertEquals(hashTable.batchInsert(arr), N);
        assertFalse(hashTable.insert(5));
        for(int i : arr){
            assertTrue(hashTable.search(i));
        }
        assertEquals(hashTable.batchDelete(arr), N);
        System.out.println(hashTable.getNumberOfRebuild());
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    //testing main dictionary with n2
    @Test
    void test6(){
        int N = 20;
        hashTable = new HASH_N2(N);
        assertTrue(hashTable.insert("cat".hashCode()));
        assertTrue(hashTable.insert("car".hashCode()));
        assertTrue(hashTable.insert("dog".hashCode()));
        assertTrue(hashTable.insert("elephant".hashCode()));
        assertTrue(hashTable.insert("ds".hashCode()));
        assertFalse(hashTable.insert("dog".hashCode()));

        String[] keys = {"a", "aa", "aaa", "b", "bb", "a", "bbb", "c", "cc","b", "ccc", "d", "dd", "ddd", "e","d" ,"ee", "eee"};
        int[] arr = new int[keys.length];
        for(int i = 0; i < keys.length; i++){
            arr[i] = keys[i].hashCode();
        }
        //inserting with batch insert with duplicated keys
        assertTrue(hashTable.batchInsert(arr) == 15);
        //test insert more than the allowed capacity
        assertFalse(hashTable.insert("apple".hashCode()));
        assertFalse(hashTable.insert("banana".hashCode()));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(arr[i]));
        }
        assertFalse(hashTable.search("apple".hashCode()));

        assertTrue(hashTable.delete("aa".hashCode()));
        assertFalse(hashTable.search("aa".hashCode()));

        assertTrue(hashTable.batchDelete(arr) == 14);

        for(int i = 0; i < arr.length; i++){
            assertFalse(hashTable.search(arr[i]));
        }
        assertTrue(hashTable.search("ds".hashCode()));
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    //testing bigger main cli scenario
    @Test
    void test7(){
        int N = 1010;
        hashTable = new HASH_N2(N);
        int[] keys = generateStrings(1000);
        assertTrue(hashTable.batchInsert(keys) == 1000);

        assertTrue(hashTable.insert("apple".hashCode()));
        assertTrue(hashTable.insert("mac".hashCode()));
        assertTrue(hashTable.insert("linux".hashCode()));
        assertTrue(hashTable.insert("windows".hashCode()));
        assertFalse(hashTable.insert("mac".hashCode()));

        assertTrue(hashTable.search("linux".hashCode()));
        assertFalse(hashTable.search("zzz".hashCode()));
        assertTrue(hashTable.delete("mac".hashCode()));
        assertFalse(hashTable.search("mac".hashCode()));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.batchDelete(keys) == 1000);
        for(int i = 0; i < keys.length; i++){
            assertFalse(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.delete("apple".hashCode()));
        assertFalse(hashTable.search("apple".hashCode()));

        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    //much bigger main test
    @Test
    void test8(){
        int N = 10010;
        hashTable = new HASH_N2(N);
        int[] keys = generateStrings(10000);
        assertTrue(hashTable.batchInsert(keys) == 10000);

        assertTrue(hashTable.insert("ds".hashCode()));
        assertTrue(hashTable.insert("os".hashCode()));
        assertTrue(hashTable.insert("control".hashCode()));
        assertTrue(hashTable.insert("paradigms".hashCode()));
        assertFalse(hashTable.insert("os".hashCode()));

        assertTrue(hashTable.search("control".hashCode()));
        assertFalse(hashTable.search("zzz".hashCode()));
        assertTrue(hashTable.delete("os".hashCode()));
        assertFalse(hashTable.search("os".hashCode()));

        for(int i = 0; i < keys.length; i++){
            assertTrue(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.batchDelete(keys) == 10000);
        for(int i = 0; i < keys.length; i++){
            assertFalse(hashTable.search(keys[i]));
        }
        assertTrue(hashTable.delete("control".hashCode()));
        assertFalse(hashTable.search("control".hashCode()));
        System.out.println(hashTable.getNumberOfRebuild());
        assertTrue(hashTable.getNumberOfRebuild() <= 2);
    }

    private int[] generateStrings(int size){
        Set<String> distinctStrings = new HashSet<>();

        while (distinctStrings.size() < size) {
            String newString = UUID.randomUUID().toString();
            distinctStrings.add(newString);
        }
        String[] distinctStringsArray = new String[distinctStrings.size()];
        distinctStrings.toArray(distinctStringsArray);
        int[] keys = new int[distinctStrings.size()];
        for(int i = 0; i < distinctStringsArray.length; i++){
            keys[i] = distinctStringsArray[i].hashCode();
        }
        return keys;
    }
}