package homework1;

import java.io.IOException;
import java.util.Scanner;

public class PhonebookV1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputFilePath = "C:\\Users\\MKP\\Desktop\\raw_phonebook_data.csv";
        String outputFilePath = "C:\\Users\\MKP\\Desktop\\new_phonebook.csv";

        System.out.println("Loading entries...");
        Entry[] entries = null;
        try {
            entries = FileUtils.readFile(inputFilePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("Sorting entries...");
        MergeSort.sort(entries);

        System.out.println("Saving the file...");
        try {
            FileUtils.writeToFile(entries, outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return;
        }

        System.out.println("Ready.");
        while (true) {
            System.out.print("Enter the name that you wish to search for, or -1 to exit: ");
            String input = scanner.nextLine().trim();

            if ("-1".equals(input)) {
                System.out.println("Goodbye");
                break;
            }

            int[] result = BinarySearch.search(entries, input);
            if (result.length == 0) {
                System.out.println("No entries with the given name found.");
            } else {
                int start = result[0];
                int end = result[1];
                System.out.println("Entries found: " + (end - start + 1));
                for (int i = start; i <= end; i++) {
                    Entry entry = entries[i];
                    System.out.println("Name: " + entry.getName());
                    System.out.println("Address: " + entry.getAddress());
                    System.out.println("City: " + entry.getCity());
                    System.out.println("Postal code: " + entry.getPostcode());
                    System.out.println("Country: " + entry.getCountry());
                    System.out.println("Phone number: " + entry.getPhoneNumber());
                    System.out.println();
                }
            }
        }

        scanner.close();
    }
}

