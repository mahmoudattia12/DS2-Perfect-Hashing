package ADT_HASH;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HASH_N2Test {

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
    }


}