package ADT_HASH;
import UNIVERSAL.UniversalHashing;

import java.lang.reflect.Array;

public class HASH_N2 <T extends Comparable<T>> implements IHASH<T> {
    private Integer[] hashTable;
    private int n, M, rehashTries = 0, countInserted = 0;
    UniversalHashing h;

    private final Class<T> type;
    public Class<T> getType() {
        return type;
    }
    public HASH_N2(int N, Class<T> type){
        n = N;
        System.out.println("N: " + n);
        M = (int)(1<<(int)(Math.ceil(Math.log(n*n) / Math.log(2))));
        hashTable = new Integer[M];
        h = new UniversalHashing(M);
        this.type = type;
    }

    private int changeToInteger(T elementToInsert){
        if(getType().getSimpleName().equals("String")){
            int element = elementToInsert.hashCode();
            return element;
        }
        return (Integer) elementToInsert;
    }

    public boolean insert(T key){
        //can't insert more than the given size
        if(n == countInserted){
            return false;
        }
        int element = changeToInteger(key);
        int index = h.hash(element);
        if(hashTable[index] != null && hashTable[index] != element){
            rehashTries++;
            return rehash(element);
        }else if(hashTable[index] != null && hashTable[index] == element){
            return false;
        }
        hashTable[index] = element;
        countInserted++;
        return true;
    }
    public int batchInsert(T[] list){
        int countBatch = 0;
        for(int i = 0; i < list.length && n > countInserted; i++){
            if(insert(list[i])) countBatch++;
        }
        return countBatch;
    }
    private boolean rehash(int newKey){
        Integer[] prev = hashTable;
        boolean collision = true, flag = true;
        while (collision){
            collision = false;
            hashTable = new Integer[M];
            h = new UniversalHashing(M);
            for(int i = 0; i < prev.length; i++){
                if(prev[i] != null){
                    int index = h.hash(prev[i]);
                    if(hashTable[index] != null&& hashTable[index] != prev[i]){
                        collision = true;
                        rehashTries++;
                        break;
                    }
                    hashTable[index] = prev[i];
                }
            }
            if(!collision){
                int index = h.hash(newKey);
                if(hashTable[index] != null && hashTable[index] != newKey){
                    collision = true;
                    rehashTries++;
                }else if(hashTable[index] != null && hashTable[index] == newKey){
                    flag =  false;
                }else{
                    countInserted++;
                    hashTable[index] = newKey;
                }
            }
        }
        return flag;
    }
    public boolean search(T key){
        int element = changeToInteger(key);
        int index = h.hash(element);
        return hashTable[index] != null && hashTable[index] == element;
    }
    public boolean delete(T key){
        int element = changeToInteger(key);
        int index = h.hash(element);
        if(hashTable[index] != null && hashTable[index] == element){
            hashTable[index] = null;
            countInserted--;
            return true;
        }
        return false;
    }
    public int batchDelete(T[] list){
        int countDelete = 0;
        for(int i = 0; i < list.length; i++){
            if(delete(list[i])) countDelete++;
        }
        return countDelete;
    }
    public int getNumOfCollisions(){
        return rehashTries;
    }

    public int getSpace(){
        return M;
    }
}
