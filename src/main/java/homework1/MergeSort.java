package homework1;

import java.util.Comparator;

public class MergeSort {
    public static void sort(Entry[] entries) {
        if (entries.length <= 1) {
            return;
        }
        Entry[] temp = new Entry[entries.length];
        mergeSort(entries, temp, 0, entries.length - 1);
    }
    private static void mergeSort(Entry[] entries, Entry[] temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;
        mergeSort(entries, temp, leftStart, middle);
        mergeSort(entries, temp, middle + 1, rightEnd);
        mergeHalves(entries, temp, leftStart, rightEnd);
    }

    private static void mergeHalves(Entry[] entries, Entry[] temp, int leftStart, int rightEnd) {
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (entries[left].compareTo(entries[right]) <= 0) {
                temp[index] = entries[left];
                left++;
            } else {
                temp[index] = entries[right];
                right++;
            }
            index++;
        }

        System.arraycopy(entries, left, temp, index, leftEnd - left + 1);
        System.arraycopy(entries, right, temp, index, rightEnd - right + 1);
        System.arraycopy(temp, leftStart, entries, leftStart, size);
    }

}
