package ADT_HASH;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HASH_NTest {

    @Test
    void test1(){
        HASH_N<Integer>ll =new HASH_N<Integer>((int)1e6,Integer.class);
        Integer[] array= new Integer[1<<(int)Math.ceil(Math.log((int)1e6) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]=i+1;
        }
        ll.batchInsert(array);
    }
    @Test
    void test2(){
        HASH_N<Integer>ll =new HASH_N<Integer>((int)1e6,Integer.class);
        Integer[] array= new Integer[1<<(int)Math.ceil(Math.log((int)1e6) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]=i+1;
        }
        ll.batchInsert(array);
        for(int i=0;i<array.length;i++){
            System.out.println(ll.Search(array[i]));
        }
    }

    @Test
    void test3(){
        HASH_N<Integer>ll =new HASH_N<Integer>((int)1e6,Integer.class);
        Integer[] array= new Integer[1<<(int)(Math.log((int)1e6) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]=i+1;
        }
        ll.batchInsert(array);

        for(int i=0;i<array.length;i++){
            ll.delete(array[i]);
        }

        for(int i=0;i<array.length;i++){
            System.out.println(ll.Search(array[i]));
        }
    }
    @Test
    void test4(){
        HASH_N<Integer>ll =new HASH_N<Integer>((int)1e5,Integer.class);
        ll.insert(6);
        ll.insert(4);
        long start = System.currentTimeMillis();
        assertTrue(ll.insert(10000000));
        Integer[] array= new Integer[1<<(int)(Math.log((int)1e5) / Math.log(2))];
        for(int i=0;i<array.length;i++){
            array[i]=i+8;
        }

        assertEquals(ll.batchInsert(array),array.length);
        assertTrue(ll.Search(10000000));
        assertTrue(ll.Search(8));
        assertFalse(ll.Search((int)1e8));
        System.out.println(System.currentTimeMillis() - start);
    }
    HASH_N hashTable;
    @Test
    public void testInsert() {
        hashTable = new HASH_N<Integer>((int)1e6, Integer.class);
        // Test inserting a key that already exists
        hashTable.insert(5);
        assertFalse(hashTable.insert(5));
        hashTable.delete(5);
        // Test inserting more keys than the size allows
        Integer[] array = new  Integer[(int)1e6];
        for (int i = 1; i <= (int)1e6; i++) {
            array[i-1]=i;
        }
        long start = System.currentTimeMillis();
        assertEquals(hashTable.batchInsert(array),(int)1e6);
        System.out.println("after batch "+ (System.currentTimeMillis() - start));
        assertFalse(hashTable.insert(11));

        System.out.println("outer collisions: "+hashTable.getNumOfCollisions());
        System.out.println("inner collisions: "+hashTable.getNumOfOuterTableCollisions());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}