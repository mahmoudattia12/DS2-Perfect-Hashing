package ADT_HASH;
import UNIVERSAL.UniversalHashing;

public class HASH_N2 {
    public Integer[] hashTable;
    public int n, rehashTries = 0;
    UniversalHashing h;
    public HASH_N2(){
        n = 0;
    }
    //initialize with a list
    public HASH_N2(int[] toHashList){
        System.out.print("batched count: ");
        int c = batchInsert(toHashList);
        System.out.println(c);
//        n= toHashList.length;
//        int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
//        hashTable = new Integer[M];
//        h = new UniversalHashing(M);
    }

    //batch insert
    public int batchInsert(int[] list){
        int count = 0;
        if(n == 0){
            n= list.length;
            boolean repeat = true;
            while (repeat){
                repeat = false;
                count = 0;
                int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
                hashTable = new Integer[M];
                h = new UniversalHashing(M);
                for(int i = 0; i < list.length; i++){
                    int index = h.hash(list[i]);
                    if(hashTable[index] != null&& hashTable[index] != list[i]){
                        repeat = true;
                        rehashTries++;
                        break;
                    }else if(hashTable[index] != null && hashTable[index] == list[i]){
//                        n--;
                    }else{
                        hashTable[index] = list[i];
                        count++;
                    }
                }
            }


        }else{
            Integer[] prev = hashTable;
            //still not implemented

        }
        return count;

    }

    //insert in N2 method
    public boolean insert(int key){
        n++;
        if(n == 1){
            int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
            hashTable = new Integer[M];
            h = new UniversalHashing(M);
            int index = h.hash(key);
            hashTable[index] = key;
            return true;
        }

        int index = h.hash(key);
        if(hashTable[index] != null && hashTable[index] != key){
            rehashTries++;
            return rehash(hashTable, key);
        }else if(hashTable[index] != null && hashTable[index] == key){
//            n--;
            return false;
        }
        hashTable[index] = key;
        return true;
    }


    private boolean rehash(Integer[] currTable, int newKey){
        Integer[] prev = currTable;
        boolean repeat = true;
        boolean flag = false;
        while (repeat){
            repeat = false;
            int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
            hashTable = new Integer[M];
            h = new UniversalHashing(M);
            for(int i = 0; i < prev.length; i++){
                if(prev[i] != null){
                    int index = h.hash(prev[i]);
                    if(hashTable[index] != null&& hashTable[index] != prev[i]){
                        repeat = true;
                        rehashTries++;
                        break;
                    }
                    hashTable[index] = prev[i];
                }
            }
            if(!repeat){
                int index = h.hash(newKey);
                if(hashTable[index] != null && hashTable[index] != newKey){
                    repeat = true;
                    rehashTries++;
                }else if(hashTable[index] != null && hashTable[index] == newKey){
//                    n--;
                    flag =  false;
                }else{
                    hashTable[index] = newKey;
                    flag = true;
                }
            }
        }
        return flag;
    }

}
