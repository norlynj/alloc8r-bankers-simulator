package model;

import java.util.ArrayList;
import java.util.List;

public abstract class BankersAlgorithm {
    private final List<Process> processes;
    private int resourcesNumber;
    private int[] availableResources;
    private int[] requestResource;



    public BankersAlgorithm() {
        this.processes = new ArrayList<>();
    }

    public boolean add(Process process) {
        return processes.add(process);
    }

    public void setResourcesNumber(int num) {
        this.resourcesNumber = num;
    }

    public void setAvailableResources(int[] availableResources) {
        this.availableResources = availableResources;
    }

    public void setRequestResource(int[] requestResource) {
        this.requestResource = requestResource;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public int getResourcesNumber() {
        return resourcesNumber;
    }

    public int[] getAvailableResources() {
        return availableResources;
    }

    public int[] getRequestResource() {
        return requestResource;
    }


    public abstract void simulate();

}
