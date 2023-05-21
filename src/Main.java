import ADT_HASH.HASH_N;
import ADT_HASH.HASH_N2;
import ADT_HASH.IHASH;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        HASH_N<Integer>ll =new HASH_N<Integer>((int)1e6,Integer.class);
//        Integer[] array= new Integer[1<<(int)Math.ceil(Math.log((int)1e6) / Math.log(2))];
//        for(int i=0;i<array.length;i++){
//            array[i]=i+1;
//        }
//        ll.batchInsert(array);
//
//        for(int i = 0 ; i < 10 ; i++){
//            ll.delete(array.length-i);
//        }
//
//
//        for(int i=0;i<array.length;i++){
//            System.out.println(ll.Search(array[i]));
//        }

        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t  English Dictionary");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        IHASH<String> dictionary;
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
                    dictionary = new HASH_N2(size, String.class);
                    System.out.println("HASH_N2 Table");
                    break;
                }
//                else if (size > 0 && treeType.equalsIgnoreCase("N")) {
////                    dictionary = new RB<String>();
////                    System.out.println("rb");
//                    break;
//                }
                else {
                    System.out.println("Wrong Input");
                }
            } catch (Exception e) {
                System.out.println("Wrong input type");
            }
        }
        //start operations
        while (true){
            System.out.println("The operations you can do with our dictionary:");
            System.out.println("1- Insert\t\t2- Delete\t\t3- Search\t\t4- Batch Insert\t\t5- Batch Delete\t\t6- get number of rebuilds\t\t7- terminate the program");
            System.out.print("Enter the operation order you want to do(1/2/3/4/5/6): ");
            String operation = sc.nextLine();
            if(operation.equals("1")){
                System.out.print("please enter the string key you want to insert: ");
                String key = sc.nextLine();
                if(dictionary.insert(key)){
                    System.out.println("the key was inserted successfully");
                }else{
                    System.out.println("Error!! the key already exists");
                }
            } else if(operation.equals("2")){
                System.out.print("please enter the string key you want to delete: ");
                String key = sc.nextLine();
                if(dictionary.delete(key)){
                    System.out.println("the key was deleted successfully");
                }else{
                    System.out.println("Error!! the key doesn't exist");
                }
            }else if(operation.equals("3")){
                System.out.print("please enter the string key you want to search for: ");
                String key = sc.nextLine();
                if(dictionary.search(key)){
                    System.out.println("the key was found successfully");
                }else{
                    System.out.println("the key wasn't found");
                }
            }else if(operation.equals("4")){
                int nSuccess = 0, nFail = 0;
                while (true) {
                    System.out.print("please enter the file path: ");
                    String filePath = sc.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (dictionary.insert(line)) {
                                nSuccess++;
                            } else {
                                nFail++;
                            }
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }
                System.out.println("The number of successfully newly added strings: " + nSuccess);
                System.out.println("The number of already existing strings: " + nFail);
            }else if(operation.equals("5")){
                int nSuccess = 0, nFail = 0;
                while (true) {
                    System.out.print("please enter the file path: ");
                    String filePath = sc.nextLine();
                    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (dictionary.delete(line)) {
                                nSuccess++;
                            } else {
                                nFail++;
                            }
                        }
                        break;
                    } catch (IOException e) {
                        System.out.println("Please enter a valid file path");
                    }
                }
                System.out.println("The number of successfully deleted strings: " + nSuccess);
                System.out.println("The number of non-existing strings: " + nFail);
            }else if(operation.equals("6")){
                System.out.println("number of tries is: " + dictionary.getNumOfCollisions());
            }else if(operation.equals("7")){
                break;
            }else {
                System.out.println("Wrong input you should enter (1/2/3/4/5/6)");
            }
            System.out.println();
        }

    }
}

