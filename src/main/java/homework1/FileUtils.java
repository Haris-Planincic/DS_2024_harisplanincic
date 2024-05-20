package homework1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static Entry[] readFile(String filePath) throws FileNotFoundException {
        List<Entry> entries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Entry entry = new Entry(values[0], values[1], values[2], values[3], values[4], values[5]);
                entries.add(entry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries.toArray(new Entry[0]);
    }

    public static void writeToFile(Entry[] entries, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Surname, Name;Street Address;City;Postcode;Country;Phone Number\n"); // header
            for (Entry entry : entries) {
                bw.write(String.format("%s;%s;%s;%s;%s;%s\n",
                        entry.getName(),
                        entry.getAddress(),
                        entry.getCity(),
                        entry.getPostcode(),
                        entry.getCountry(),
                        entry.getPhoneNumber()));
            }
        }
    }
}
