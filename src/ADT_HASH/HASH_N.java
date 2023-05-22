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
    int totalNumKeys , numOfCollisions = 0, numOfOuterTableCollisions = 0;
    private final Class<T> type;
    public Class<T> getType() {
        return type;
    }

    public int getNumOfOuterTableCollisions() {
        return numOfOuterTableCollisions;
    }
    public int getSpace(){
        int  sigmaNSquared = 0;
        for(int i = 0; i< entrySizes.length; i++){
            sigmaNSquared += entrySizes[i]*entrySizes[i];
        }
        return sigmaNSquared;
    }

    public int getNumOfCollisions() {
        return numOfCollisions;
    }
//m size of outer table
    public HASH_N(int totalNumKeys, Class<T> type){
        this.totalNumKeys = 1<<((int)Math.ceil(Math.log(totalNumKeys) / Math.log(2)));
        Tables = new Integer[this.totalNumKeys][];
        l2Hash = new UniversalHashing[this.totalNumKeys];
        L1HashEdit();
        entrySizes = new int[this.totalNumKeys];
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
        return sigmaNSquared<4*sigmaN;
    }

    private void L2HashEdit(int i){

        l2Hash[i] = new UniversalHashing(1<<((int)Math.ceil(Math.log(entrySizes[i] * entrySizes[i]) / Math.log(2))));

    }

    private Integer[] changeToInteger(T[] elementToInsert){
        if(getType().getSimpleName().equals("String")){
            Integer []element = new Integer[elementToInsert.length];
            for(int i = 0; i < elementToInsert.length;i++){
                element[i] = elementToInsert[i].hashCode()& Integer.MAX_VALUE;
            }
            return element;
        }
        return (Integer[]) elementToInsert;
    }
    public boolean insert(T elementToInsert){
        T[] temp = (T[]) Array.newInstance(type, 1);
        temp[0] = elementToInsert;
        Integer[] element = changeToInteger(temp);
        return insertionDirector(element) == 1;
    }
    public int batchInsert(T[] elementToInsert){
        Integer [] elementsToBeInserted = changeToInteger(elementToInsert);
        int success = insertionDirector(elementsToBeInserted);
        if(checkCorrectness()) TotalRehash();
        return success;
    }
    private int insertionDirector(Integer[] elementsToInsert){
        int count = 0,noOfInsertions=0;
        for (int i = 0; i < elementsToInsert.length; i++) {
         if(!Search((T)elementsToInsert[i])) {
             entrySizes[this.l1Hash.hash((int)elementsToInsert[i])]++;

         }
         }

        for (int i = 0 ; i<elementsToInsert.length;i++) {
            int outerIndex = this.l1Hash.hash((int)elementsToInsert[i]);
            int innerIndex = 0;
            if(Tables[outerIndex]!= null)
                innerIndex = this.l2Hash[outerIndex].hash((int)elementsToInsert[i]);
            if (Tables[outerIndex] == null) {
                L2HashEdit(outerIndex);
                Integer[] innerTable = new Integer[1<<((int)Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)))];
                innerTable[this.l2Hash[outerIndex].hash((int)elementsToInsert[i])] = elementsToInsert[i];
                Tables[outerIndex] = innerTable;
                count++;


            }
            else if (Search((T)elementsToInsert[i])){
            }

            else if (Tables[outerIndex][innerIndex] == null) {
                Tables[outerIndex][innerIndex] = elementsToInsert[i];
                count++;
            } else {
                count++;
                Rehash(outerIndex, elementsToInsert[i]);
            }
        }

        return count;
    }
    private void TotalRehash(){
        List<Integer> allElements = new ArrayList<Integer>();
        for(int i = 0;i<Tables.length;i++){
            if(Tables[i] != null) {
                for (int j = 0; j < Tables[i].length; j++){
                    if(Tables[i][j] != null)
                        allElements.add(Tables[i][j]);
                }
            }
        }
        do {
            Tables = new Integer[totalNumKeys][];
            entrySizes = new int[totalNumKeys];

            L1HashEdit();

            insertionDirector(allElements.toArray(new Integer[0]));
            numOfOuterTableCollisions++;
        }
        while(!checkCorrectness());

    }
    private void Rehash(int outerIndex , int element){
        Integer[] elementsInInnerTable = new Integer[entrySizes[outerIndex]];
        int currIndex = 0;
        if(Tables[outerIndex] != null){
            for (int i = 0; i < Tables[outerIndex].length; i++) {
                if(Tables[outerIndex][i] != null){
                    elementsInInnerTable[currIndex++] = Tables[outerIndex][i];
                }
            }
        }

        elementsInInnerTable[currIndex] = element;
        Tables[outerIndex] = HandlingInputCornerCases(outerIndex , elementsInInnerTable);
    }
    private Integer[] HandlingInputCornerCases(int outerIndex, Integer[] valueToInsert) {
        boolean collision = true;
        int innerIndex;
        L2HashEdit(outerIndex);
        Integer[] l2Arr=null;
        while(collision){
            l2Arr =new Integer[1<<(int)(Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)))];
            // variable edit
            numOfCollisions++;
            L2HashEdit(outerIndex);
            collision = false;

            for (int k = 0; k < valueToInsert.length; k++) {
                if(valueToInsert[k] == null){
                    continue;
                }
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

            for(int i = 0 ; i <elementToDelete.length ; i++) {
                try{
                int outerIndex = this.l1Hash.hash(elementToDelete[i]);
                if (Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])].equals(elementToDelete[i])) {
                    Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])] = null;
                    entrySizes[outerIndex]--;
                    count++;
                }
            }
                catch (Exception e){
                }
            }


        return count;
    }
    private int changeForSearch(T generic){
        int afterHash;
        if(getType().getSimpleName().equals("String")){
            afterHash=generic.hashCode()& Integer.MAX_VALUE;
        }
        else {
         afterHash = (int)generic;
        }

    return afterHash;
    }

    public boolean Search(T elementToSearch){
        int elementAfterCast = changeForSearch(elementToSearch);
        int outerIndex = this.l1Hash.hash(elementAfterCast);

        if(Tables[outerIndex] != null) {
            int innerIndex = this.l2Hash[outerIndex].hash(elementAfterCast);
            if (Tables[outerIndex][innerIndex]!= null && elementAfterCast == Tables[outerIndex][innerIndex]) {
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }

    }
}
