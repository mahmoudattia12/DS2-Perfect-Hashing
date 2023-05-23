package ADT_HASH;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HASH_NTest {
    HASH_N hashTable;
    @Test
    void test1(){
        HASH_N hashTable =new HASH_N((int)1e4);
        hashTable.insert(1);
        hashTable.insert(5000);
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
        hashTable.batchInsert(array);
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
            hashTable.insert(i+1);
        }

        for(int i=0;i<size;i++){
            hashTable.delete((long)i+1);
        }

        for(int i=0;i<i+1;i++){
            hashTable.search((long) i+1);
        }
    }
    @Test
    void test4(){
        HASH_N hashTable =new HASH_N((int)1e5);
        hashTable.insert(6);
        hashTable.insert(4);
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


//    @Test
//    void test5(){
//        int size = 20000;
//        HASH_N hashTable =new HASH_N<>(size);
//        String[] array = generateStrings(size);
//        long start = System.currentTimeMillis();
//    for(int i = 0 ;i < size; i++) {
//        hashTable.insert(array[i]);
//    }
//        System.out.println("after insertion "+ (System.currentTimeMillis() - start));
//
//        System.out.println("inner table collisions: "+hashTable.getNumOfCollisions());
//        System.out.println("outer table collisions: "+hashTable.getNumOfOuterTableCollisions());
//        HASH_N<String>hashTable1 =new HASH_N<>(size,String.class);
//        long start2 = System.currentTimeMillis();
//        hashTable1.batchInsert(array);
//        System.out.println("after batch "+ (System.currentTimeMillis() - start2));
//
//        System.out.println("inner table collisions: "+hashTable1.getNumOfCollisions());
//        System.out.println("outer table collisions: "+hashTable1.getNumOfOuterTableCollisions());
//
////        for(int i=0;i<array.length;i++){
////            assertTrue(hashTable.search(array[i]));
////        }
////
////        assertEquals(0,hashTable.batchInsert(array));
////
////        for(int i=0;i<array.length;i++){
////            assertTrue(hashTable.search(array[i]));
////        }
////        assertEquals(size,hashTable.batchDelete(array));
////        for(int i = 0;i<size;i++){
////            assertTrue(hashTable.insert(array[i]));
////        }
////        for(int i = 0;i<size;i++){
////            assertTrue(hashTable.search(array[i]));
////        }
////        for(int i = 0;i<size;i++){
////            assertTrue(hashTable.delete(array[i]));
////        }
////        for(int i = 0;i<size;i++){
////            assertFalse(hashTable.search(array[i]));
////        }
//    }

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
    private String[] generateStrings(int size){
        Set<String> distinctStrings = new HashSet<>();

        while (distinctStrings.size() < size) {
            String newString = UUID.randomUUID().toString();
            String truncatedString = newString.substring(0, Math.min(newString.length(), 8));
            distinctStrings.add(truncatedString);
        }
        String[] distinctStringsArray = new String[distinctStrings.size()];
        distinctStrings.toArray(distinctStringsArray);
        return distinctStringsArray;
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