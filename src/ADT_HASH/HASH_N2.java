package ADT_HASH;
import Dictionary.Main;
import UNIVERSAL.UniversalHashing;

import java.lang.reflect.Array;

public class HASH_N2 implements IHASH {
    private Long[] hashTable;
    private int n, M, rehashTries = 0, countInserted = 0;
    UniversalHashing h;

    public HASH_N2(int N){
        n = N;
        M = (int)(1<<(int)(Math.ceil(Math.log(n*n) / Math.log(2))));
        hashTable = new Long[M];
        h = new UniversalHashing(M);
    }
    public boolean insert(long key){
        //can't insert more than the given size
        if(n == countInserted){
            return false;
        }
        int index = h.hash(key);
        if(hashTable[index] != null && hashTable[index] != key){
            rehashTries++;
            return rehash(key);
        }else if(hashTable[index] != null && hashTable[index] == key){
            return false;
        }
        hashTable[index] = key;
        countInserted++;
        return true;
    }
    public int batchInsert(Long[] list){
        int countBatch = 0;
        for(int i = 0; i < list.length && n > countInserted; i++){
            if(insert(list[i])) countBatch++;
        }
        return countBatch;
    }
    private boolean rehash(long newKey){
        Long[] prev = hashTable;
        boolean collision = true, flag = true;
        while (collision){
            collision = false;
            hashTable = new Long[M];
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
    public boolean search(long key){
        int index = h.hash(key);
        return hashTable[index] != null && hashTable[index] == key;
    }
    public boolean delete(long key){
        int index = h.hash(key);
        if(hashTable[index] != null && hashTable[index] == key){
            hashTable[index] = null;
            countInserted--;
            return true;
        }
        return false;
    }
    public int batchDelete(Long[] list){
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
