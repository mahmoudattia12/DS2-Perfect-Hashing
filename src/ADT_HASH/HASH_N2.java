package ADT_HASH;
import UNIVERSAL.UniversalHashing;

public class HASH_N2 {
    int[] hashTable;
    int n, maxKey;
    UniversalHashing h;
    public HASH_N2(){
        n = 0;
        maxKey = 0;
    }


    public boolean insert(int key){
        n++;
        maxKey = Math.max(key, maxKey);
        int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
        hashTable = new int[M];
        h = new UniversalHashing(M, maxKey);
        boolean collision = false;








        return true;
    }
    private void rehash(int[] prevTable){

    }

//    public void hashN2(){
//        for(int i = 0; i < n; i++){
//            h = new UniverseHashing(hashTable.length, maxKeyLength);
////            int index = h.hash(toHash[i]);
////            if(hashTable[index] == null){
////
////            }
//        }
//    }

}
