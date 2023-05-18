package ADT_HASH;
import UNIVERSAL.UniversalHashing;

public class HASH_N2 implements IHASH {
    private Integer[] hashTable;
    private int n, M, rehashTries = 0, countInserted = 0;
    UniversalHashing h;
    public HASH_N2(int N){
        n = N;
        M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
        hashTable = new Integer[M];
        h = new UniversalHashing(M);
    }
    public boolean insert(int key){
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
    public int batchInsert(int[] list){
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
    public boolean search(int key){
        int index = h.hash(key);
        return hashTable[index] != null && hashTable[index] == key;
    }
    public boolean delete(int key){
        int index = h.hash(key);
        if(hashTable[index] != null && hashTable[index] == key){
            hashTable[index] = null;
            countInserted--;
            return true;
        }
        return false;
    }
    public int batchDelete(int[] list){
        int countDelete = 0;
        for(int i = 0; i < list.length; i++){
            if(delete(list[i])) countDelete++;
        }
        return countDelete;
    }
    public int getNumberOfRebuild(){
        return rehashTries;
    }


}
