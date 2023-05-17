import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class Main {


    public static void main(String[] args) {
        HashTableLinear my = new HashTableLinear();
        my.fillT1();
        for(int i = 0; i < my.t1.length; i++){
            System.out.print(my.t1[i]);
        }
        System.out.println();
        my.update();
        for(int i = 0; i < my.prev.length; i++){
            System.out.print(my.prev[i]);
        }
        System.out.println();
        for(int i = 0; i < my.t1.length; i++){
            System.out.print(my.t1[i]);
        }
        System.out.println();
        for(int i = 0; i < my.prev.length; i++){
            System.out.print(my.prev[i]);
        }
        System.out.println();

    }
//    public static BigInteger getNumberId(final String value) {
//        return new BigInteger(value.getBytes(Charset.availableCharsets().get("UTF-8")));
//    }
//    public static String getStringFromNumberId(BigInteger number) {
//        byte[] bytes = number.toByteArray();
//        return new String(bytes, Charset.forName("UTF-8"));
//    }
//
//
//    private static BigDecimal createBigDecimalFromString(String data)
//    {
//        BigDecimal value = BigDecimal.ZERO;
//
//        try
//        {
//            byte[] tmp = data.getBytes("UTF-8");
//            int numBytes = tmp.length;
//            for(int i = numBytes - 1; i >= 0; i--)
//            {
//                BigDecimal exponent = new BigDecimal(256).pow(i);
//                value = value.add(exponent.multiply(new BigDecimal(tmp[i])));
//            }
//        }
//        catch (UnsupportedEncodingException e)
//        {
//        }
//        return value;
//    }
}