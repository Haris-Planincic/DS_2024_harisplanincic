package homework1;

public class BinarySearch {
    public static int[] search(Entry[] entries, String searchableName) {
        int firstOccurrence = findFirstOccurrence(entries, searchableName);
        if (firstOccurrence == -1) {
            return new int[]{};
        }

        int start = firstOccurrence;
        int end = firstOccurrence;

        while (start > 0 && entries[start - 1].getName().equals(searchableName)) {
            start--;
        }

        while (end < entries.length - 1 && entries[end + 1].getName().equals(searchableName)) {
            end++;
        }

        return new int[]{start, end};
    }
    private static int findFirstOccurrence(Entry[] entries, String searchableName) {
        int low = 0;
        int high = entries.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = entries[mid].getName().compareTo(searchableName);

            if (comparison < 0) {
                low = mid + 1;
            } else if (comparison > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
