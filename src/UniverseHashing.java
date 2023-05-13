import java.util.Random;

public class UniverseHashing {
    // h is a random b-by-u 0/1 matrix
    private int[][] h;
    private int hashTableLength;
    public UniverseHashing(int M, int maxKey){
        int u = Integer.SIZE - Integer.numberOfLeadingZeros(maxKey); //number of bits for the max inserted key
        int b = ( int)Math.ceil(Math.log(M) / Math.log(2));         //number of bits to represent h(x)
        h = new int[b][u];
        hashTableLength = M;
        Random random = new Random();
        for(int i = 0; i < b; i++){
            for(int j = 0; j < u; j++){
                h[i][j] = random.nextInt(2);            //generates 0/1
            }
        }
    }

    public int hash(int x){
        int[] xBinaryArr = getBinaryArray(x);
        int[] result = new int[h.length];     //to hold h(x) binary representation
        for(int i = 0; i < h.length; i++){
            int sum = 0;
            for(int j = 0; j < h[0].length; j++){
                sum += h[i][j] * xBinaryArr[j];
            }
            result[i] = sum%2;
        }
        return getInt(result)%hashTableLength;  //h(x)
    }
    private int[] getBinaryArray(int number) {
        int numBits = (int) (Math.log(number) / Math.log(2)) + 1;
        int[] binaryArray = new int[numBits];
        for (int i = numBits - 1; i >= 0; i--) {
            binaryArray[i] = number & 1;
            number = number >> 1;
        }
        return binaryArray;
    }
    private int getInt(int[] arr){
        int res = 0;
        for(int i = arr.length - 1; i >= 0; i--){
            res += arr[i] * Math.pow(2, arr.length-i-1);
        }
        return res;
    }
}
