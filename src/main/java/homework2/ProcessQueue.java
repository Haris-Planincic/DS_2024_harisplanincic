package homework2;

import java.io.PipedOutputStream;
import java.util.ArrayList;

public class ProcessQueue {
    private ArrayList<Process> p;
    public ProcessQueue() {
        p = new ArrayList<>();
    }
    public void addProcess(Process process) {
        p.add(process);
        swim(p.size()-1);
    }
    public Process runNextProcess() {
        if(isEmpty()) {
            return null;
        }
        Process nextProcess = p.get(0);
        p.set(0, p.get(p.size()-1));
        p.remove(p.size()-1);
        sink(0);
        return nextProcess;
    }
    public Process peekNextProcess() {
        if(isEmpty()) {
            return null;
        }
        return p.get(0);
    }
    public boolean isEmpty() {
        return p.isEmpty();
    }
    private void swim(int i) {
        while (i > 0 && less(i, (i - 1) * 2)) {
            swap(i, (i - 1) * 2);
            i = (i - 1) / 2;
        }
    }
    private void sink(int i) {
        while(2 * i + 1 < p.size()) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;
            int smallerChild = leftChild;
            if (rightChild < p.size() && less(rightChild, leftChild)) {
                smallerChild = rightChild;
            }
            if (!less(smallerChild, i)) {
                break;
            }
            swap (i, smallerChild);
            i = smallerChild;
        }
    }
    private boolean less(int i, int j) {
        Process p1 = p.get(i);
        Process p2 = p.get(j);
        if(p1.getPriority() != p2.getPriority()) {
            return p1.getPriority() < p2.getPriority();
        } else {
           return p1.getArrivalTime() < p2.getArrivalTime();
        }
    }
    private void swap(int i, int j) {
        Process k = p.get(i);
        p.set(i, p.get(j));
        p.set(j, k);
    }
}
