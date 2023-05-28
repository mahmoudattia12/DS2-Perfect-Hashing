package TestFiles;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueStringsGenerator {
    static int N = 1000000;
    public static void main(String[] args) {
         // Set the desired number of unique strings

        try {
            generateUniqueStrings(N);
            System.out.println("Unique strings generated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while generating unique strings: " + e.getMessage());
        }
    }

    private static void generateUniqueStrings(int N) throws IOException {
        Set<String> uniqueStrings = new HashSet<>();

        while (uniqueStrings.size() < N) {
            String randomString = generateRandomString();
            uniqueStrings.add(randomString);
        }

        writeStringsToFile(uniqueStrings);
    }

    private static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            char randomChar = (char) (random.nextInt(94) + 32); // ASCII range: 32-126
            sb.append(randomChar);
        }

        return sb.toString();
    }

    private static void writeStringsToFile(Set<String> strings) throws IOException {
        FileWriter writer = new FileWriter("D:/CSED/level2/2nd semester/Data Structure 2/labs/lab3/DS2-Perfect-Hashing/src/TestFiles/unique_strings"+ N+  ".txt");

        for (String str : strings) {
            writer.write(str);
            writer.write(System.lineSeparator());
        }

        writer.close();
    }
}
