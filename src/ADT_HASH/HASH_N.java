package ADT_HASH;
import UNIVERSAL.UniversalHashing;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HASH_N implements IHASH {
    UniversalHashing l1Hash;
    final UniversalHashing[] l2Hash;
    Long[] elementsInInnerTable ;
    int[] entrySizes;

    Long[] l2Arr=null;

    Long[][] Tables = null;
    int totalNumKeys , numOfCollisions = 0, numOfOuterTableCollisions = 0;


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
    public HASH_N(int totalNumKeys){
        this.totalNumKeys = 1<<((int)Math.ceil(Math.log(totalNumKeys) / Math.log(2)));
        Tables = new Long[this.totalNumKeys][];
        l2Hash = new UniversalHashing[this.totalNumKeys];
        L1HashEdit();
        entrySizes = new int[this.totalNumKeys];
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

    private void L2HashEdit(int outerIndex){

        l2Hash[outerIndex] = new UniversalHashing(1<<(int)(Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2))));

    }


    public boolean insert(long elementToInsert){
        return insertionDirector(new Long[]{elementToInsert}) == 1;
    }

    public int batchInsert(Long[] elementToInsert){
        int success = insertionDirector((elementToInsert));
//        if(checkCorrectness()) TotalRehash();
        return success;
    }


    private int insertionDirector(Long[] elementsToInsert){
        int count = 0;
        for (int i = 0; i < elementsToInsert.length; i++) {
            if(!search(elementsToInsert[i])) {
                entrySizes[this.l1Hash.hash(elementsToInsert[i])]++;
            }
        }

        for (int i = 0 ; i<elementsToInsert.length;i++) {
            int outerIndex = this.l1Hash.hash(elementsToInsert[i]);
            int innerIndex = 0;
            if(Tables[outerIndex]!= null)
                innerIndex = this.l2Hash[outerIndex].hash(elementsToInsert[i]);
            if (Tables[outerIndex] == null) {
                L2HashEdit(outerIndex);
                Long[] innerTable = new Long[1<<((int)Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)))];
                innerTable[this.l2Hash[outerIndex].hash(elementsToInsert[i])] = elementsToInsert[i];
                Tables[outerIndex] = innerTable;
                count++;
            }
            else if (search(elementsToInsert[i])){
            }

            else if (Tables[outerIndex][innerIndex] == null) {
                Tables[outerIndex][innerIndex] = elementsToInsert[i];
                count++;
            } else {
                count++;
                RehashDirector(outerIndex, elementsToInsert[i]);
            }
        }

        return count;
    }
    private void TotalRehash(){
        List<Long> allElements = new ArrayList<>();
        for(int i = 0;i<Tables.length;i++){
            if(Tables[i] != null) {
                for (int j = 0; j < Tables[i].length; j++){
                    if(Tables[i][j] != null)
                        allElements.add(Tables[i][j].longValue());
                }
            }
        }
        do {
            Tables = new Long[totalNumKeys][];
            entrySizes = new int[totalNumKeys];
            L1HashEdit();
            insertionDirector(allElements.toArray(new Long[0]));
            numOfOuterTableCollisions++;
        }
        while(!checkCorrectness());
    }
    private void RehashDirector(int outerIndex, Long element){
        elementsInInnerTable = new Long[entrySizes[outerIndex]];
        int currIndex = 0;
        if(Tables[outerIndex] != null){
            for (int i = 0; i < Tables[outerIndex].length; i++) {
                if(Tables[outerIndex][i] != null){
                    elementsInInnerTable[currIndex++] = Tables[outerIndex][i];
                }
            }
        }
        elementsInInnerTable[currIndex] = element;
        int logVal = 1<<(int)(Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)));
        while(true){
            if(Rehash(outerIndex,logVal)){
                Tables[outerIndex]=l2Arr;
                break;
            }
        }
    }
    private boolean Rehash(int outerIndex,int logVal){
        int innerIndex;
        // variable edit
        numOfCollisions++;
        L2HashEdit(outerIndex);
        l2Arr =new Long[logVal];
        for (int k = 0; k < elementsInInnerTable .length; k++) {
            if(elementsInInnerTable[k] == null){
                continue;
            }
            innerIndex = l2Hash[outerIndex].hash(elementsInInnerTable[k].longValue());
            if( (l2Arr[innerIndex] != null )){
                return false;
            } else {
                l2Arr[innerIndex] = elementsInInnerTable [k];
            }
        }
        return true;
    }

    public boolean delete(long elementToDelete){
        Long[] elementsToDelete =  {elementToDelete};

        return deleteDirector(elementsToDelete) == 1;
    }
    public int batchDelete(Long[] elementsToDelete){
        return deleteDirector(elementsToDelete);
    }
    private int deleteDirector(Long[] elementToDelete){
        int count = 0;

        for(int i = 0 ; i <elementToDelete.length ; i++) {
            try{
                int outerIndex = this.l1Hash.hash(elementToDelete[i].longValue());
                if (Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i].longValue())].equals(elementToDelete[i])) {
                    Tables[outerIndex][this.l2Hash[outerIndex].hash(elementToDelete[i].longValue())] = null;
                    entrySizes[outerIndex]--;
                    count++;
                }
            }
            catch (Exception e){
            }
        }


        return count;
    }


    public boolean search(long elementToSearch){

        int outerIndex = this.l1Hash.hash(elementToSearch);

        if(Tables[outerIndex] != null) {
            int innerIndex = this.l2Hash[outerIndex].hash(elementToSearch);
            if (Tables[outerIndex][innerIndex] != null && elementToSearch == Tables[outerIndex][innerIndex]) {
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

//    private Long[] HandlingInputCornerCases(int outerIndex, Long[] valueToInsert) {
//        boolean collision = true;
//        int innerIndex;
//        L2HashEdit(outerIndex);
//        Long[] l2Arr=null;
//        while(collision){
//            l2Arr =new Long[1<<(int)(Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)))];
//            // variable edit
//            numOfCollisions++;
//            L2HashEdit(outerIndex);
//            collision = false;
//
//            for (int k = 0; k < valueToInsert.length; k++) {
//                if(valueToInsert[k] == null){
//                    continue;
//                }
//                innerIndex = l2Hash[outerIndex].hash(valueToInsert[k].longValue());
//                if( (l2Arr[innerIndex] != null )){
//                    // collision case
//                    collision = true;
//                    break;
//                } else {
//                    l2Arr[innerIndex] = valueToInsert[k];
//                }
//            }
//        }
//        return l2Arr;
//    }

    //    public int batchInsert(Long[] elementToInsert){
//        int success=0;
//        for(int i = 0;i<elementToInsert.length;i++){
//            if(insert(elementToInsert[i]))success++;
//        }
//        return success;
//    }

//    private int insertionDirector(long elementsToInsert){
//        int count = 0;
//
//            if(!search(elementsToInsert)) {
//                entrySizes[this.l1Hash.hash(elementsToInsert)]++;
//            }
//            else{
//                return 0;
//            }
//
//            int outerIndex = this.l1Hash.hash(elementsToInsert);
//            int innerIndex = 0;
//
//            if(Tables[outerIndex]!= null)
//                innerIndex = this.l2Hash[outerIndex].hash(elementsToInsert);
//
//            if (Tables[outerIndex] == null) {
//                L2HashEdit(outerIndex);
//                Long[] innerTable = new Long[1<<((int)Math.ceil(Math.log(entrySizes[outerIndex] * entrySizes[outerIndex]) / Math.log(2)))];
//                innerTable[this.l2Hash[outerIndex].hash(elementsToInsert)] = elementsToInsert;
//                Tables[outerIndex] = innerTable;
//                count++;
//            }
//            else if (search(elementsToInsert)){
//            }
//
//            else if (Tables[outerIndex][innerIndex] == null) {
//                Tables[outerIndex][innerIndex] = elementsToInsert;
//                count++;
//            } else {
//                count++;
//                RehashDirector(outerIndex,elementsToInsert);
//            }
//
//
//        return count;
//    }

    //    private long changeToInteger(String elementToInsert){
//        if(getType().getSimpleName().equals("String")){
//            Integer element = elementToInsert.hashCode();
//            return element;
//        }
//        return (Integer) elementToInsert;
//    }
//    public boolean insert(long elementToInsert){
//        return insertionDirector(elementToInsert) == 1;
//    }
}