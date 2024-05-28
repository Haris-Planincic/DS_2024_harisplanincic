package homework2;

public class Process implements Comparable<Process> {
    private String processName;
    private int priority;
    private int burstTime;
    private int arrivalTime;
    private int remainingTime;

    public Process(String processName, int priority, int burstTime, int arrivalTime) {
        this.processName = processName;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
    public String getProcessName() {
        return processName;
    }
    public int getPriority() {
        return priority;
    }
    public int getBurstTime() {
        return burstTime;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getRemainingTime() {
        return remainingTime;
    }
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
    @Override
    public int compareTo(Process o) {
        if (this.priority != o.priority) {
            return Integer.compare(this.priority, o.priority);
        }
        else {
            return Integer.compare(this.arrivalTime, o.arrivalTime);
        }
    }
}
