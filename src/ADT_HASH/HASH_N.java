package ADT_HASH;

import UNIVERSAL.UniversalHashing;

import java.util.Arrays;

public class HASH_N {

    UniversalHashing l1Hash;
    UniversalHashing[] l2Hash;
    int[] entrySizes;
    Integer[][] vectors = null;
    int totalNumKeys = 0, numOfCollisions = 0;

    // make constructor overload for string handling

    public HASH_N(int totalNumKeys, int[] insertedKeys){
        this.totalNumKeys = totalNumKeys;
    }

    private void L1_H_instance(){
        l1Hash = new UniversalHashing(totalNumKeys);
    }

    private void setEntrySizes(int[] insertedKeys){
        entrySizes = new int[ this.totalNumKeys ];
        Arrays.fill(entrySizes, 0);

        // previous values.
        if( vectors != null ) {
            for (int i = 0; i < totalNumKeys; i++) {
                for (int j = 0; j < vectors[i].length; j++) {
                    if( vectors[i][j] == null )continue;
                    entrySizes[ l1Hash.hash( vectors[i][j] ) ]++;
                }
            }
        }

        // new inserted values.
        for (int i = 0; i < insertedKeys.length; i++) {
            entrySizes[ l1Hash.hash( insertedKeys[i] ) ]++;
        }


    }

    private void L2_H_instances(){
        for (int i = 0; i < totalNumKeys; i++) {
            l2Hash[i] = new UniversalHashing(entrySizes[i] * entrySizes[i]);
        }
    }



    private void fillNewTable(int[] insertedKeys){

        Integer[][] newVectors = new Integer[ totalNumKeys ][];

        // previous values.
        int rowEntry, colEntry;
        if( vectors != null ){
            for (int i = 0; i < totalNumKeys; i++) {
                for (int j = 0; j < vectors[i].length; j++) {
                    if( vectors[i][j] == null )continue;
                    rowEntry = l1Hash.hash( vectors[i][j] );
                    colEntry = l2Hash[rowEntry].hash( vectors[i][j] );
                    // no collisions
                    if( newVectors[rowEntry][colEntry] == null ){
                        newVectors[rowEntry][colEntry] = vectors[i][j];
                    }
                    // yeh collisions
                    else {
                        collisionHandle(rowEntry, newVectors, vectors[i][j]);
                    }
                }
            }
        }



        for (int i = 0; i < insertedKeys.length; i++) {
            rowEntry = l1Hash.hash( insertedKeys[i] );
            colEntry = l2Hash[rowEntry].hash( insertedKeys[i] );
            // no collisions
            if( newVectors[rowEntry][colEntry] == null ){
                newVectors[rowEntry][colEntry] = insertedKeys[i];
            }
            // yeh collisions
            else {
                collisionHandle(rowEntry, newVectors, insertedKeys[i]);
            }
        }



    }

    private void collisionHandle(int rowEntry, Integer[][] newVectors, int collisionPivot) {
        boolean collision = true;
        int colEntry;
        while(collision){
            numOfCollisions++;
            collision = false;
            L2HashEdit(rowEntry);
            Integer[] newInnerTable = new Integer[ vectors[rowEntry].length ];
            for (int k = 0; k < newInnerTable.length; k++) {
                if( newVectors[rowEntry][k] == null ) continue;
                colEntry = l2Hash[rowEntry].hash( newVectors[rowEntry][k] );
                if( newInnerTable[colEntry] != null ){
                    collision = true;
                    break;
                } else {
                    newInnerTable[colEntry] = newVectors[rowEntry][k];
                }
            }

            colEntry = l2Hash[rowEntry].hash( collisionPivot );
            if( newInnerTable[colEntry] != null ){
                collision = true;
            } else {
                newInnerTable[colEntry] = collisionPivot;
            }
        }
    }

    private void L2HashEdit(int i){
        l2Hash[i] = new UniversalHashing(entrySizes[i] * entrySizes[i]);
    }

}
