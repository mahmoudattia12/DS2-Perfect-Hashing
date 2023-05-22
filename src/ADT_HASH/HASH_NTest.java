package ADT_HASH;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HASH_NTest {
    HASH_N hashTable;
    @Test
    void test1(){
        HASH_N<Integer>hashTable =new HASH_N<Integer>((int)1e4,Integer.class);
        hashTable.insert(1);
        hashTable.insert(5000);
        int size =(int)1e4;
        Integer[] array= new Integer[size];
        for(int i=0;i<array.length;i++){
            array[i]=i+1;
        }
        assertEquals(hashTable.batchInsert(array), (size-2));
        assertTrue(hashTable.Search(1));
        assertTrue(hashTable.Search(5000));
        assertTrue(hashTable.Search(4000));
        assertFalse(hashTable.Search(0));
    }
    @Test
    void test2(){
        HASH_N<Integer>hashTable =new HASH_N<Integer>((int)1e5,Integer.class);
        int size =(int) 1e5;
        Integer[] array= new Integer[size];
        for(int i=0; i < array.length ; i++){
            array[i]=i+1;
        }
        hashTable.batchInsert(array);
        for(int i=0; i < array.length ; i++){
            assertTrue(hashTable.Search(array[i]));
        }
        assertEquals(hashTable.batchDelete(array),size);
        for(int i=0; i < array.length ; i++){
            assertFalse(hashTable.Search(array[i]));
        }
    }

    @Test
    void test3(){
        int size = (int)1e5;
        HASH_N<Integer>hashTable =new HASH_N<Integer>((int)1e5,Integer.class);
        for(int i=0;i<size;i++){
            hashTable.insert(i+1);
        }

        for(int i=0;i<size;i++){
            hashTable.delete(i+1);
        }

        for(int i=0;i<i+1;i++){
            hashTable.Search(i+1);
        }
    }
    @Test
    void test4(){
        HASH_N<Integer>hashTable =new HASH_N<Integer>((int)1e5,Integer.class);
        hashTable.insert(6);
        hashTable.insert(4);
        long start = System.currentTimeMillis();
        assertTrue(hashTable.insert(10000000));
        Integer[] array= new Integer[1<<(int)(Math.log((int)1e5) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]=i+8;
        }

        assertEquals(hashTable.batchInsert(array),array.length);
        assertTrue(hashTable.Search(10000000));
        assertTrue(hashTable.Search(8));
        assertFalse(hashTable.Search((int)1e8));
        System.out.println(System.currentTimeMillis() - start);
    }


    @Test
    void test5(){
        int size = (int)1e4;
        HASH_N<String>hashTable =new HASH_N<>(size,String.class);
        String[] array = generateStrings(size);

       assertEquals(size,hashTable.batchInsert(array));

        for(int i=0;i<array.length;i++){
            assertTrue(hashTable.Search(array[i]));
        }


        assertEquals(0,hashTable.batchInsert(array));

        for(int i=0;i<array.length;i++){
            assertTrue(hashTable.Search(array[i]));
        }
        assertEquals(size,hashTable.batchDelete(array));
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.insert(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.Search(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertTrue(hashTable.delete(array[i]));
        }
        for(int i = 0;i<size;i++){
            assertFalse(hashTable.Search(array[i]));
        }
    }

    @Test
    public void testInsertTime() {
        hashTable = new HASH_N<Integer>((int)1e6, Integer.class);
        // Test inserting a key that already exists
        hashTable.insert(5);
        assertFalse(hashTable.insert(5));
        hashTable.delete(5);
        // Test inserting more keys than the size ahashTableows
        Integer[] array = new  Integer[(int)1e6];
        for (int i = 1; i <= (int)1e6; i++) {
            array[i-1]=i;
        }
        long start = System.currentTimeMillis();
        assertEquals(hashTable.batchInsert(array),(int)1e6);
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

}