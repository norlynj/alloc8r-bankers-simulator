package model;

public class Process {
    private String processName;
    private int[] allocation;
    private int[] maximumClaim;
    private int[] need;

    public Process(String processName, int[] allocation, int[] maximumClaim) {
        this.processName = processName;
        this.allocation = allocation;
        this.maximumClaim = maximumClaim;
        this.need = new int[allocation.length];
        calculateNeed();
    }

    private void calculateNeed() {
        for (int i = 0; i < allocation.length ; i++) {
            this.need[i] = maximumClaim[i]-allocation[i];
        }
    }

    public String getProcessName() {
        return processName;
    }

    public int[] getAllocation() {
        return allocation;
    }

    public int[] getMaximumClaim() {
        return maximumClaim;
    }

    public int[] getNeed() {
        return need;
    }

    public void setAllocation(int[] allocation) {
        this.allocation = allocation;
    }

    public void setNeed(int[] need) {
        this.need = need;
    }
}
