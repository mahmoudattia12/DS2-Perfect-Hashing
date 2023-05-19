package ADT_HASH;

import UNIVERSAL.UniversalHashing;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class HASH_N {

    UniversalHashing l1Hash;
    UniversalHashing[] l2Hash;
    int[] entrySizes;
    Integer[][] vectors = null;
    int maxNumKeys = 0, numOfCollisions = 0;

    // make constructor overload for string handling

    public HASH_N(int maxNumKeys){
        this.maxNumKeys = maxNumKeys;
        vectors = new Integer[ maxNumKeys ][];
        instantiateL2Entries();
        L1_H_instance();
    }

    private void instantiateL2Entries() {
        for (int i = 0; i < maxNumKeys; i++) {
            vectors[i] = new Integer[0];
        }
    }

    private void L1_H_instance(){
        l1Hash = new UniversalHashing(maxNumKeys);
    }

//    private void setEntrySizes(int[] insertedKeys){
//        entrySizes = new int[ this.maxNumKeys ];
//        Arrays.fill(entrySizes, 0);
//
//        // previous values.
//        if( vectors != null ) {
//            for (int i = 0; i < maxNumKeys; i++) {
//                for (int j = 0; j < vectors[i].length; j++) {
//                    if( vectors[i][j] == null )continue;
//                    entrySizes[ l1Hash.hash( vectors[i][j] ) ]++;
//                }
//            }
//        }
//
//        // new inserted values.
//        for (int i = 0; i < insertedKeys.length; i++) {
//            entrySizes[ l1Hash.hash( insertedKeys[i] ) ]++;
//        }
//
//
//    }
//
//    private void L2_H_instances(){
//        for (int i = 0; i < maxNumKeys; i++) {
//            l2Hash[i] = new UniversalHashing(entrySizes[i] * entrySizes[i]);
//        }
//    }
//
//
//
//    private void fillNewTable(int[] insertedKeys){
//
//        Integer[][] newVectors = new Integer[ maxNumKeys ][];
//
//        // previous values.
//        int rowEntry, colEntry;
//        if( vectors != null ){
//            for (int i = 0; i < maxNumKeys; i++) {
//                for (int j = 0; j < vectors[i].length; j++) {
//                    if( vectors[i][j] == null )continue;
//                    rowEntry = l1Hash.hash( vectors[i][j] );
//                    colEntry = l2Hash[rowEntry].hash( vectors[i][j] );
//                    // no collisions
//                    if( newVectors[rowEntry][colEntry] == null ){
//                        newVectors[rowEntry][colEntry] = vectors[i][j];
//                    }
//                    // yeh collisions
//                    else {
//                        collisionHandle(rowEntry, newVectors, vectors[i][j]);
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < insertedKeys.length; i++) {
//            rowEntry = l1Hash.hash( insertedKeys[i] );
//            colEntry = l2Hash[rowEntry].hash( insertedKeys[i] );
//            // no collisions
//            if( newVectors[rowEntry][colEntry] == null ){
//                newVectors[rowEntry][colEntry] = insertedKeys[i];
//            }
//            // yeh collisions
//            else {
//                collisionHandle(rowEntry, newVectors, insertedKeys[i]);
//            }
//        }
//
//
//
//    }
//
//    private void collisionHandle(int rowEntry, Integer[][] newVectors, int collisionPivot) {
//        boolean collision = true;
//        int colEntry;
//        while(collision){
//            numOfCollisions++;
//            collision = false;
//            L2HashEdit(rowEntry);
//            Integer[] newInnerTable = new Integer[ vectors[rowEntry].length ];
//            for (int k = 0; k < newInnerTable.length; k++) {
//                if( newVectors[rowEntry][k] == null ) continue;
//                colEntry = l2Hash[rowEntry].hash( newVectors[rowEntry][k] );
//                if( newInnerTable[colEntry] != null ){
//                    collision = true;
//                    break;
//                } else {
//                    newInnerTable[colEntry] = newVectors[rowEntry][k];
//                }
//            }
//
//            colEntry = l2Hash[rowEntry].hash( collisionPivot );
//            if( newInnerTable[colEntry] != null ){
//                collision = true;
//            } else {
//                newInnerTable[colEntry] = collisionPivot;
//            }
//        }
//    }

    private Integer[] l1EntryEdit(int rowEntry, Integer[] valueToInsert) {
        boolean collision = true;
        int colEntry;
        Integer[] l2Arr = new Integer[ valueToInsert.length * valueToInsert.length ];

        numOfCollisions--;
        while(collision){
            // variable edit
            numOfCollisions++;
            collision = false;

            for (int k = 0; k < valueToInsert.length; k++) {
                colEntry = l2Hash[rowEntry].hash( valueToInsert[k] );
                if( l2Arr[colEntry] != null ){

                    // duplicate case
                    if( valueToInsert[k] == l2Arr[colEntry] )continue;

                    // collision case
                    collision = true;
                    break;

                } else {
                    l2Arr[colEntry] = valueToInsert[k];
                }
            }
        }
        return l2Arr;
    }

    private void L2HashEdit(int i){
        l2Hash[i] = new UniversalHashing(entrySizes[i] * entrySizes[i]);
    }

    private TreeSet<Integer> updatedEntriesFun(Integer[] elementToInsert){
        TreeSet<Integer> updatedEntries = new TreeSet<>();
        int rowEntry;
        for (int i = 0; i < elementToInsert.length; i++) {
            rowEntry = l1Hash.hash( elementToInsert[i] );
            updatedEntries.add(rowEntry);
        }
        return updatedEntries;
    }

    private List<Integer>[] getEditedList(Integer[] elementToInsert, TreeSet<Integer> updatedEntries){
        int rowEntry;
        List<Integer>[] arrEdited = new ArrayList[maxNumKeys];
        // previous known data
        for (Integer entry :updatedEntries) {
            for (Integer elem :vectors[entry]) {
                if( elem == null )continue;
                arrEdited[entry].add(elem);
            }
        }

        // new data
        int colEntry;
        for (int i = 0; i < elementToInsert.length; i++) {
            rowEntry = l1Hash.hash( elementToInsert[i] );
            colEntry = l2Hash[rowEntry].hash( elementToInsert[i] );

            // duplicates.
            if( vectors[rowEntry][colEntry] == elementToInsert[i] )continue;
            arrEdited[rowEntry].add(elementToInsert[i]);
        }
        return arrEdited;
    }

    private void insertionDirector(Integer[] elementToInsert){
        // values in updated l1 entries
        TreeSet<Integer> updatedEntries = updatedEntriesFun(elementToInsert);

        List<Integer>[] arrEdited = getEditedList(elementToInsert, updatedEntries);

        // iterate on each one of updated entries.
        for (Integer entry :updatedEntries) {

            // edit entry size
            entrySizes[entry] = arrEdited[entry].size();

            // create l2 hash function.
            L2HashEdit(entry);

            // edit index i in l1 table.
            vectors[entry] = l1EntryEdit(entry, arrEdited[entry].toArray(new Integer[0]));

        }

        // check sigma ni^2 < 2 * n
        if( needRehashing() ){

        }
    }

    public boolean insert(int elementToInsert){
        insertionDirector(new Integer[] { elementToInsert });
        return true;
    }
    public boolean batchInsert(Integer[] elementToInsert){
        insertionDirector(elementToInsert);
        return true;
    }
    public boolean delete(int elementToDelete){return true;}
    public boolean batchDelete(int[] elementToDelete){return true;}

    private boolean needRehashing(){
        long totalEntrySize = 0, curNumKeys = 0;
        for (int i = 0; i < maxNumKeys; i++) {
            totalEntrySize += entrySizes[i] * entrySizes[i];
            curNumKeys += entrySizes[i];
        }
        return totalEntrySize > 2 * curNumKeys;
    }

    // assumptions:
    // N given in constructor is the maximum size for L1 overall time the object is created.
    // the number of insertions and deletions are very small.
    // types available are string and int.

}
