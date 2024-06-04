package homework3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhonebookV2 {
    public static void main(String[] args) {
        try {
            RedBlackTree<Entry> phonebook = FileUtils.readFile("C:\\Users\\MKP\\Desktop\\raw_phonebook_data.csv");

            int[] edgeCounts = phonebook.countRedAndBlackEdges();
            System.out.println("Total black edges: " + edgeCounts[0]);
            System.out.println("Total red edges: " + edgeCounts[1]);

            Scanner scanner = new Scanner(System.in);
            String userInput;

            while (true) {
                System.out.println("Enter a 'Surname, Name' to search for an entry or enter -1 to exit:");
                userInput = scanner.nextLine().trim();

                if (userInput.equals("-1")) {
                    System.out.println("Exiting the program.");
                    break;
                }

                ArrayList<Entry> searchResult = phonebook.get(userInput);

                if (searchResult != null && !searchResult.isEmpty()) {
                    System.out.println("Entries found: " + searchResult.size());
                    int[] searchEdgeCounts = phonebook.countRedAndBlackEdges();
                    System.out.println("Black edges: " + searchEdgeCounts[0]);
                    System.out.println("Red edges: " + searchEdgeCounts[1]);
                    for (Entry entry : searchResult) {
                        System.out.println(entry);
                    }
                } else {
                    System.out.println("Entry not found.");
                }
            }

            scanner.close();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}

