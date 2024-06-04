package homework3;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static RedBlackTree<Entry> readFile(String filePath) throws FileNotFoundException {
        RedBlackTree<Entry> redBlackTree = new RedBlackTree<>();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");

            if (parts.length == 7) {
                String surname = parts[0];
                String name = parts[1];
                String streetAddress = parts[2];
                String city = parts[3];
                String postcode = parts[4];
                String country = parts[5];
                String phoneNumber = parts[6];

                Entry entry = new Entry(surname, name, streetAddress, city, postcode, country, phoneNumber);
                redBlackTree.put(surname + ", " + name, entry);
            }
        }

        scanner.close();
        return redBlackTree;
    }
}
