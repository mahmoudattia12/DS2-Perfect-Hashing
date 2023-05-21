package ADT_HASH;
import UNIVERSAL.UniversalHashing;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HASH_N <T extends Comparable>  {
    UniversalHashing l1Hash;
    UniversalHashing[] l2Hash;
    int[] entrySizes;
    Integer[][] Tables = null;
    int totalNumKeys , numOfCollisions = 0;
    private final Class<T> type;
    public Class<T> getType() {
        return type;
    }
    public HASH_N(int totalNumKeys, Class<T> type){
        this.totalNumKeys = totalNumKeys;
        Tables = new Integer[totalNumKeys][];
        l2Hash = new UniversalHashing[totalNumKeys];
        L1HashEdit();
        entrySizes = new int[totalNumKeys];
        this.type = type;
    }
    private void L1HashEdit(){
        l1Hash = new UniversalHashing(totalNumKeys);
    }
    private boolean checkCorrectness(){
        boolean flag = true;
        int sigmaN = 0, sigmaNSquared = 0;
        for(int i = 0; i< entrySizes.length; i++){
            sigmaN += entrySizes[i];
            sigmaNSquared += entrySizes[i]*entrySizes[i];
        }
        return sigmaNSquared<2*sigmaN;
    }
    private void L2HashEdit(int i){
        l2Hash[i] = new UniversalHashing(entrySizes[i] * entrySizes[i]);
    }
    private Integer[] changeToInteger(T[] elementToInsert){

        if(getType().getSimpleName().equals("String")){
            Integer []element = new Integer[elementToInsert.length];
            for(int i = 0; i < elementToInsert.length;i++){
                element[i] = elementToInsert.hashCode();
            }
            return element;
        }
        return (Integer[]) elementToInsert;
    }
    private boolean insert(T elementToInsert){
        T[] temp = (T[]) Array.newInstance(type, 1);
        temp[0] = elementToInsert;
        Integer[] element = changeToInteger(temp);
        return insertionDirector(element) == 1;
    }
    public int batchInsert(T[] elementToInsert){
        Integer [] elementsToBeInserted = changeToInteger(elementToInsert);
        return insertionDirector(elementsToBeInserted);

    }
    private int insertionDirector(Integer[] elementsToInsert){
        int count = 0;
//        for (int i = 0; i < elementsToInsert.length; i++)
//            entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;

        for (int i = 0 ; i<elementsToInsert.length;i++) {
            int outerIndex = this.l1Hash.hash(elementsToInsert[i]);
            if (Tables[outerIndex] == null) {
                entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
                Rehash(outerIndex, elementsToInsert[i]);
            } else if (elementsToInsert[i].equals(Tables[outerIndex][this.l2Hash[outerIndex].hash(elementsToInsert[i])])) ;

            else if (Tables[outerIndex][this.l2Hash[outerIndex].hash(elementsToInsert[i])] == null) {
                Tables[outerIndex][this.l2Hash[outerIndex].hash(elementsToInsert[i])] = elementsToInsert[i];
                count++;
                entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
            } else {
                if(checkCorrectness()) {
                    entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
                    Rehash(outerIndex, elementsToInsert[i]);
                }
                else {
                    entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
                    TotalRehash(elementsToInsert[i]);
                }
            }
        }
        return count;
    }
    private void TotalRehash(Integer elem){
        List<Integer> allElements = new ArrayList<Integer>();

        for(int i = 0;i<Tables.length;i++){
            if(Tables[i] != null) {
                for (int j = 0; j < Tables[i].length; j++){
                    if(Tables[i][j] != null)
                        allElements.add(Tables[i][j]);
                }
            }
        }
        allElements.add(elem);

        do {
            Integer[][] newTables = new Integer[totalNumKeys][];
            Integer[][] oldTables = Tables;
            entrySizes = new int[totalNumKeys];
            Tables = newTables;
            L1HashEdit();
            insertionDirector((Integer[])allElements.toArray());
        } while(!checkCorrectness());
    }
    private void Rehash(int outerIndex , int element){
        if(Tables[outerIndex]!=null){
        System.out.println(entrySizes[outerIndex]);
        System.out.println(Tables[outerIndex].length);}
        Integer[] elementsInInnerTable = new Integer[entrySizes[outerIndex]];
        int currIndex = 0;
        if(Tables[outerIndex] != null){
            for (int i = 0; i < Tables[outerIndex].length; i++) {
                if(Tables[outerIndex][i] != null){
                    elementsInInnerTable[currIndex++] = Tables[outerIndex][i];
                }
            }
        }
        if(Tables[outerIndex]!=null){
            System.out.println(currIndex);}

        elementsInInnerTable[currIndex] = element;
        Tables[outerIndex] = HandlingInputCornerCases(outerIndex , elementsInInnerTable);
    }
    private Integer[] HandlingInputCornerCases(int outerIndex, Integer[] valueToInsert) {
        boolean collision = true;
        int innerIndex;
        Integer[] l2Arr = new Integer[ entrySizes[outerIndex] * entrySizes[outerIndex] ];
        L2HashEdit(outerIndex);
        while(collision){
            System.out.println("hena");

            // variable edit
            numOfCollisions++;
            L2HashEdit(outerIndex);
            collision = false;
            System.out.println(outerIndex);
            System.out.println("valueToInsert"+valueToInsert.length);
            for(int i=0;i<Tables.length;i++){
                if(Tables[i]!=null) {
                    for (int j = 0; j < Tables[i].length; j++) {
                        System.out.print(Tables[i][j] + " ");
                    }
                    System.out.println();
                }
            }
            for(int i=0;i<valueToInsert.length;i++){
                System.out.print("value: "+valueToInsert[i]+" ");
            }
            System.out.println();
            for(int i=0;i<entrySizes.length;i++){
                System.out.print("size: "+entrySizes[i]+" ");
            }
            System.out.println();
            System.out.println("inner level size"+ entrySizes[outerIndex]*entrySizes[outerIndex]);
            System.out.println();
            for (int k = 0; k < valueToInsert.length; k++) {
                innerIndex = l2Hash[outerIndex].hash(valueToInsert[k]);

                if( l2Arr[innerIndex] != null ){
                    // collision case
                    collision = true;
                    break;

                } else {
                    l2Arr[innerIndex] = valueToInsert[k];
                }
            }
        }
        return l2Arr;
    }
    public boolean delete(T elementToDelete){
        T[] temp = (T[]) Array.newInstance(type, 1);
        temp[0] = elementToDelete;
        Integer[] element = changeToInteger(temp);
        return deleteDirector(element) == 1;
    }
    public int batchDelete(T[] elementToDelete){
        Integer [] elementsToBeDeleted = changeToInteger(elementToDelete);
        return deleteDirector(elementsToBeDeleted);
    }
    private int deleteDirector(Integer[] elementToDelete){
        int count = 0;
        try {
            for(int i = 0 ; i <elementToDelete.length ; i++){
                int outerIndex = this.l1Hash.hash(elementToDelete[i]);
                if(elementToDelete[i] == Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])]){
                    Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])] = null;
                    count++;
                }
            }
        }
        catch (Exception e){}
        return count;
    }
    public boolean Search(int elementToSearch){
        int outerIndex = this.l1Hash.hash(elementToSearch);
        try {
            if(elementToSearch == Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToSearch)]){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
