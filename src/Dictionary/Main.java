package Dictionary;

import ADT_HASH.HASH_N;
import ADT_HASH.HASH_N2;
import ADT_HASH.IHASH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  English Dictionary");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        IHASH dictionary;
        Scanner sc = new Scanner(System.in);
        //initialization
        while (true) {
            try {
                System.out.print("Enter the Hash Table type (N2/N): ");
                String treeType = sc.nextLine();
                System.out.print("Enter the table size: ");
                String s = sc.nextLine();
                int size = Integer.parseInt(s);
                if (size > 0 && treeType.equalsIgnoreCase("N2")) {
                    dictionary = new HASH_N2(size);
                    System.out.println("HASH_N2 Table");
                    break;
                }else if (size > 0 && treeType.equalsIgnoreCase("N")) {
                    dictionary = new HASH_N(size);
                    System.out.println("HASH_N Table");
                    break;
                } else {
                    System.out.println("Wrong Input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input type");
            }
        }
        //start operations
        while (true) {
            try {
                System.out.println("The operations you can do with our dictionary:");
                System.out.println("1- Insert\t\t2- Delete\t\t3- Search\t\t4- Batch Insert\t\t5- Batch Delete\t\t6- get number of rebuilds\t\t7- terminate the program");
                System.out.print("Enter the operation order you want to do(1/2/3/4/5/6): ");
                String operation = sc.nextLine();
                if (operation.equals("1")) {
                    System.out.print("please enter the string key you want to insert: ");
                    String key = sc.nextLine();
                    if (dictionary.insert(stringToLong(key))) {
                        System.out.println("the key was inserted successfully");
                    } else {
                        System.out.println("Error!! the key already exists");
                    }
                } else if (operation.equals("2")) {
                    System.out.print("please enter the string key you want to delete: ");
                    String key = sc.nextLine();
                    if (dictionary.delete(stringToLong(key))) {
                        System.out.println("the key was deleted successfully");
                    } else {
                        System.out.println("Error!! the key doesn't exist");
                    }
                } else if (operation.equals("3")) {
                    System.out.print("please enter the string key you want to search for: ");
                    String key = sc.nextLine();
                    if (dictionary.search(stringToLong(key))) {
                        System.out.println("the key was found successfully");
                    } else {
                        System.out.println("the key wasn't found");
                    }
                } else if (operation.equals("4")) {
                    ArrayList<Long> lines = new ArrayList<>();
                    while (true) {
                        System.out.print("please enter the file path: ");
                        String filePath = sc.nextLine();
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                lines.add(stringToLong(line));
                            }
                            break;
                        } catch (IOException e) {
                            System.out.println("Please enter a valid file path");
                        }
                    }
                    int count = dictionary.batchInsert(lines.toArray(new Long[lines.size()]));
                    System.out.println("The number of successfully newly added strings: " + count);
                    System.out.println("The number of already existing strings: " + (lines.size() - count));
                } else if (operation.equals("5")) {
                    ArrayList<Long> lines = new ArrayList<>();
                    while (true) {
                        System.out.print("please enter the file path: ");
                        String filePath = sc.nextLine();
                        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                lines.add(stringToLong(line));
                            }
                            break;
                        } catch (IOException e) {
                            System.out.println("Please enter a valid file path");
                        }
                    }
                    int count = dictionary.batchDelete(lines.toArray(new Long[lines.size()]));
                    System.out.println("The number of successfully deleted strings: " + count);
                    System.out.println("The number of non-existing strings: " + (lines.size() - count));
                } else if (operation.equals("6")) {
                    System.out.println("number of tries is: " + dictionary.getNumOfCollisions());
                } else if (operation.equals("7")) {
                    break;
                } else {
                    System.out.println("Wrong input you should enter (1/2/3/4/5/6)");
                }
                System.out.println();
            }catch(Exception d){
                System.out.println("wrong input!!!");
            }
        }
    }

    public static Long stringToLong(String str) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i);
            String binaryStr = Integer.toBinaryString(ascii);
            binary.append(binaryStr);
        }
        return Long.parseLong(binary.toString(), 2);
    }
//e: Wmp?a=V4
//    public static long stringToLong(final String k) {
//        final long FNV_64_INIT = 0xcbf29ce484222325L;
//        final long FNV_64_PRIME = 0x100000001b3L;
//        long rv = FNV_64_INIT;
//        final int len = k.length();
//        for(int i = 0; i < len; i++) {
//            rv ^= k.charAt(i);
//            rv *= FNV_64_PRIME;
//        }
//        return rv;
//    }
}