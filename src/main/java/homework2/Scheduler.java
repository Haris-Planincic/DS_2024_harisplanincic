package homework2;

import java.util.ArrayList;
import java.util.Iterator;

public class Scheduler {
    public static void scheduleAndRun (ArrayList<Process> processes) {
        ProcessQueue pq = new ProcessQueue();
        int currentTime = 0;
        Process runningProcess = null;

        while (!pq.isEmpty() || !processes.isEmpty() || runningProcess != null ) {
            for (Iterator<Process> iterator = processes.iterator(); iterator.hasNext(); ) {
                Process process = iterator.next();
                if(process.getArrivalTime() <= currentTime) {
                    pq.addProcess(process);
                    iterator.remove();
                }
            }
            if (runningProcess != null || runningProcess.getRemainingTime() <= 0) {
                runningProcess = pq.runNextProcess();
            } else {
                Process nextProcess = pq.peekNextProcess();
                if (nextProcess != null && nextProcess.compareTo(runningProcess) < 0) {
                    pq.addProcess(runningProcess);
                    runningProcess = pq.runNextProcess();
                }
            }
            if (runningProcess != null) {
                System.out.println("t: " + currentTime + " | " + runningProcess.getProcessName());
                runningProcess.setRemainingTime(runningProcess.getRemainingTime() - 1);
            }
            currentTime++;
        }
        System.out.println("Total time: " + (currentTime-1));

    }
    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 1,4,0));
        processes.add(new Process("P2", 2,3,0));
        processes.add(new Process("P3", 1,7,6));
        processes.add(new Process("P4", 3,4,11));
        processes.add(new Process("P5", 2,2,12));
    }
}
