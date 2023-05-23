package ADT_HASH;

import Dictionary.Main;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HASH_NTest {
    HASH_N hashTable;

    @Test
    public void testInsert() {

        hashTable = new HASH_N(10);
        // Test inserting a key that already exists
        hashTable.insert(5);
        assertFalse(hashTable.insert(5));
        hashTable.delete(5);
        for (int i = 1; i <= 10; i++) {
            assertTrue(hashTable.insert(i));
        }
        System.out.println(hashTable.getNumOfCollisions());
    }

    @Test
    public void testDeleteSearch() {
        hashTable = new HASH_N(10);
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
        hashTable = new HASH_N(20);
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
        System.out.println(hashTable.getNumOfCollisions());
    }
    @Test
    void test1(){
        HASH_N hashTable =new HASH_N((int)1e4);
        assertTrue(hashTable.insert(1));
        assertTrue(hashTable.insert(5000));
        int size =(int)1e4;
        Long[] array= new Long[size];
        for(int i=0;i<array.length;i++){
            array[i]= (long) (i+1);
        }
        assertEquals(hashTable.batchInsert(array), (size-2));
        assertTrue(hashTable.search(1));
        assertTrue(hashTable.search(5000));
        assertTrue(hashTable.search(4000));
        assertFalse(hashTable.search(0));
    }
    @Test
    void test2(){
        HASH_N hashTable =new HASH_N((int)1e5);
        int size =(int) 1e5;
        Long[] array= new Long[size];
        for(int i=0; i < array.length ; i++){
            array[i]= (long) (i+1);
        }
        assertTrue(hashTable.batchInsert(array)== array.length);
        for(int i=0; i < array.length ; i++){
            assertTrue(hashTable.search(array[i]));
        }
        assertEquals(hashTable.batchDelete(array),size);
        for(int i=0; i < array.length ; i++){
            assertFalse(hashTable.search(array[i]));
        }
    }

    @Test
    void test3(){
        int size = (int)1e5;
        HASH_N hashTable =new HASH_N(size);
        for(int i=0;i<size;i++){
            assertTrue(hashTable.insert((long) i+1));
        }

        for(int i=0;i<size;i++){
            assertTrue(hashTable.delete((long)i+1));
        }

        for(int i=0;i<size;i++){
            assertFalse(hashTable.search((long) i+1));
        }
    }
    @Test
    void test4(){
        HASH_N hashTable =new HASH_N((int)1e5);
        assertTrue(hashTable.insert(6));
        assertTrue(hashTable.insert(4));
        long start = System.currentTimeMillis();
        assertTrue(hashTable.insert(10000000));
        Long[] array= new Long[1<<(int)(Math.log((int)1e5) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]= (long) (i+8);
        }

        assertEquals(hashTable.batchInsert(array),array.length);
        assertTrue(hashTable.search(10000000));
        assertTrue(hashTable.search(8));
        assertFalse(hashTable.search((int)1e8));
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    void test5(){
        int size = (int)1e6;
        HASH_N hashTable =new HASH_N(size);
        Long[] array = generateStrings(size);
        long start = System.currentTimeMillis();
        for(int i = 0 ;i < size; i++) {
            hashTable.insert(array[i]);
        }
        System.out.println("after insertion "+ (System.currentTimeMillis() - start));

        System.out.println("inner table collisions: "+hashTable.getNumOfCollisions());
        System.out.println("outer table collisions: "+hashTable.getNumOfOuterTableCollisions());
        HASH_N hashTable1 =new HASH_N(size);
        long start2 = System.currentTimeMillis();
        assertTrue(hashTable1.batchInsert(array) == array.length);
        System.out.println("after batch "+ (System.currentTimeMillis() - start2));

        System.out.println("inner table collisions: "+hashTable1.getNumOfCollisions());
        System.out.println("outer table collisions: "+hashTable1.getNumOfOuterTableCollisions());

        for(int i=0;i<array.length;i++){
            assertTrue(hashTable.search(array[i]));
        }

        assertEquals(0,hashTable.batchInsert(array));

        for(int i=0;i<array.length;i++){
            assertTrue(hashTable.search(array[i]));
        }
        assertEquals(size,hashTable.batchDelete(array));
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.insert(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.search(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.delete(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertFalse(hashTable.search(array[i]));
        }
    }
    //testing main dictionary with n

    @Test
    void test6(){
        int N = 20;
        HASH_N hashTable = new HASH_N(N);
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
    }

    //testing bigger main cli scenario
    @Test
    void test7(){
        int N = 1010;
        HASH_N hashTable = new HASH_N(N);
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
    }

    //much bigger main test
    @Test
    void test8(){
        int N = 10010;
        HASH_N hashTable = new HASH_N(N);
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
    }

    @Test
    public void testInsertTime() {
        int size =(int)1e6;

        hashTable = new HASH_N(size);
        // Test inserting a key that already exists
        hashTable.insert(5);
        assertFalse(hashTable.insert(5));
        hashTable.delete((long)5);
        // Test inserting more keys than the size a hashTableows
        Long[] array = new  Long[size];
        for (int i = 1; i <= size; i++) {
            array[i-1]= (long) i;
        }
        long start = System.currentTimeMillis();
        assertEquals(hashTable.batchInsert(array),size);
        System.out.println("after batch "+ (System.currentTimeMillis() - start));
        assertFalse(hashTable.insert(11));

        System.out.println("inner table collisions: "+hashTable.getNumOfCollisions());
        System.out.println("outer table collisions: "+hashTable.getNumOfOuterTableCollisions());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
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
    private Long[] generateRandomLongs(int size){
        Set<Long> distinctNumbers = new HashSet<>();

        while (distinctNumbers.size() < size) {
            long newNumber = new Random().nextLong();
            distinctNumbers.add(newNumber);
        }
        Long[] distinctNumbersArray = new Long[distinctNumbers.size()];
        distinctNumbers.toArray(distinctNumbersArray);
        return distinctNumbersArray;
    }

}