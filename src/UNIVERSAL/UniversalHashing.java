package UNIVERSAL;

import java.util.Random;

public class UniversalHashing {
    // h is a random b-by-u 0/1 matrix
    private int[][] h;
    private int hashTableLength;
    private int u = 64; //number of bits for the max inserted key
    private int b;

    //int M = (int)Math.pow(2,Math.ceil(Math.log(n*n) / Math.log(2)));
    //or int M = (int)Math.pow(2,Math.ceil(Math.log(n) / Math.log(2)));
    public UniversalHashing(int M){
        b = (int)(Math.log(M) / Math.log(2));         //number of bits to represent h(x)
        h = new int[b][u];
        hashTableLength = M;
        Random random = new Random();
        for(int i = 0; i < b; i++){
            for(int j = 0; j < u; j++){
                h[i][j] = random.nextInt(2);            //generates 0/1 randomly
            }
        }
    }
    private void printArr(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public int hash(long x){
        int[] xBinaryArr = getBinaryArray(x);
        int[] result = new int[h.length];     //to hold h(x) binary representation
        for(int i = 0; i < h.length; i++){
            int sum = 0;
            for(int j = 0; j < h[0].length; j++){
                sum += h[i][j] * xBinaryArr[j];
            }
            result[i] = sum%2;
        }
        return getInt(result);  //h(x) //------------------------------>
    }
    public int[] getBinaryArray(long number) {
        int[] binaryArray = new int[u];
        for (int i = u - 1; i >= 0; i--) {
            binaryArray[i] = (int) number & 1;
            number = number >> 1;
        }
        return binaryArray;
    }
    private int getInt(int[] arr){
        int res = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            res += arr[i] * (1<< (arr.length-i-1));
        }
        return res;
    }
}
