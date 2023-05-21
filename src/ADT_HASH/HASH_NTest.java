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
        Integer[] array= new Integer[1<<(int)Math.ceil(Math.log((int)1e6) / Math.log(2))];
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

}