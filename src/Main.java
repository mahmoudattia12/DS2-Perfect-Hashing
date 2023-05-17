import ADT_HASH.HASH_N2;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Random;


public class Main {


    public static void main(String[] args) {
        HASH_N2 hashn2 = new HASH_N2();
        Random random = new Random();
        for(int i = 0; i < 1000; i++){
            System.out.println(hashn2.insert(random.nextInt()));

        }

        System.out.println("n : " +hashn2.n);
        System.out.println("tries: " + hashn2.rehashTries);
        System.out.println("table size" + hashn2.hashTable.length);


    }

}
