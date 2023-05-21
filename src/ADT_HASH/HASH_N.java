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

    public int getNumOfCollisions() {
        return numOfCollisions;
    }

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
                    entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
                    Rehash(outerIndex, elementsToInsert[i]);
            }
//            if(checkCorrectness()) TotalRehash();
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
            Integer[][] newTables = new Integer[totalNumKeys][];
            Integer[][] oldTables = Tables;
            entrySizes = new int[totalNumKeys];
            Tables = newTables;
            L1HashEdit();
            insertionDirector(allElements.toArray(new Integer[0]));
        } while(!checkCorrectness());
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
            l2Arr =new Integer[1<<(int)Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2))];
            // variable edit
            numOfCollisions++;
            L2HashEdit(outerIndex);
            collision = false;

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
                if(Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])].equals(elementToDelete[i])){
                    Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i])] = null;
                    count++;
                }
            }
        }
        catch (Exception e){
        }
        return count;
    }
    public boolean Search(int elementToSearch){
        int outerIndex = this.l1Hash.hash(elementToSearch);
        try {
            if(Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToSearch)].equals(elementToSearch)){
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }
}
