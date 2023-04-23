package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankersAlgorithm {
    private final List<Process> processes;
    private int resourcesNumber;
    private int[] availableResources;
    private int[] requestResource;

    private String[] safeSequence;
    private ArrayList<Step> safeSequenceSteps;
    private ArrayList<Step> requestSequenceSteps;



    public BankersAlgorithm() {
        this.processes = new ArrayList<>();
    }

    public void add(Process process) {
        processes.add(process);
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

    public int[] getAvailableResources() {
        return availableResources;
    }

    public int[] getRequestResource() {
        return requestResource;
    }

    public String[] getSafeSequence() {
        return safeSequence;
    }


    public void calculateSafeSequence() {
        int count = 0;
        int numberOfResources = availableResources.length;
        int numberOfProcesses = processes.size();

        safeSequenceSteps = new ArrayList<>();

        // Initialize the visited and safeSequence arrays to default values.
        boolean[] visited = new boolean[numberOfProcesses];
        int[] safeSequence = new int[numberOfProcesses];
        Arrays.fill(visited, false);

        // Initialize the work array to the available resources.
        int[] work = Arrays.copyOf(availableResources, numberOfResources);
        while (count < numberOfProcesses) {
            boolean flag = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                StringBuilder processText = new StringBuilder();
                Process process = processes.get(i);

                if (!visited[i]) {
                    processText.append("For Process " + i);
                    int j;
                    ArrayList<Integer> currentSafeSequence = new ArrayList<>();
                    for (j = 0; j < numberOfResources; j++) {
                        if (process.getNeed()[j] > work[j]) {
                            processText.append("     <br>Finish["+i+"] is false and Need > Work so P"+i + " must wait.");
                            break;
                        }
                    }

                    if (j == numberOfResources) {
                        safeSequence[count++] = i;
                        visited[i] = true;
                        flag = true;
                        currentSafeSequence.add(i);
                        processText.append("     <br>Finish["+i+"] is true and Need <= Work so P"+i + " must be kept in the safe sequence.<br>     Work = Work + Allocation =  ");

                        for (j = 0; j < numberOfResources; j++) {
                            work[j] += process.getAllocation()[j];
                        }
                        processText.append(Arrays.toString(work));
                    }
                    // Create a string representation of the Finish array
                    String finishString = "<br>     Finish = [";
                    for (int k = 0; k < numberOfProcesses; k++) {
                        finishString += " " + visited[k] + " |";
                    }
                    finishString = finishString.substring(0, finishString.length() - 1) + " ]";
                    processText.append(finishString);
                    Step step = new Step(i, processText.toString(), process, true, currentSafeSequence);
                    safeSequenceSteps.add(step);
                    step.setNewAvailable(Arrays.copyOf(work, numberOfResources));
                    processText.setLength(0);
                }
            }
            if (!flag) {
                break;
            }
        }

        if (count >= numberOfProcesses) {
            String[] ss = new String[numberOfProcesses];
            for (int i = 0; i < numberOfProcesses; i++) {
                ss[i] = ("P" + safeSequence[i]);
            }
            this.safeSequence = ss;
        }
    }


    public boolean requestResource() {
        // Find the first process whose need can be satisfied by the request.
        requestSequenceSteps = new ArrayList<>();
        int matchingProcessIndex = -1;
        StringBuilder processText = new StringBuilder();
        Process process = null;
        Process nextProcess = null;
        for (int i = 0; i < processes.size(); i++) {
            int[] need = processes.get(i).getNeed();
            boolean needSatisfied = Arrays.compare(requestResource, need) <= 0;
            processText.append("For Process "+ i+" Request ≤ Need : " + needSatisfied);
            requestSequenceSteps.add(new Step(i, processText.toString(), process));
            processText.setLength(0);
            if (needSatisfied) {
                matchingProcessIndex = i;
                process = processes.get(i);
                nextProcess = processes.get(i+1);
                break;
            }
        }
        if (process == null) {
            // Request cannot be granted.
            requestSequenceSteps.add(new Step(-1, "Request can't be granted", process));
            return false;
        }

        processText.setLength(0);
        processText.append("Request ≤ Work :");
        for (int i = 0; i < availableResources.length; i++) {
            if (requestResource[i] > availableResources[i]) {
                processText.append("<br>" + Arrays.toString(requestResource) + " ≤ " + Arrays.toString(availableResources) + " is false.<br>Request can't be granted.");
                requestSequenceSteps.add(new Step(matchingProcessIndex, processText.toString(), process));
                processText.setLength(0);
                return false; // Request cannot be granted.
            }
        }
        processText.append("<br>" + Arrays.toString(requestResource) + " <= " + Arrays.toString(availableResources) + " is true. \nWe grant the request.");


        // Grant the request.
        int[] available = new int[availableResources.length];
        int[] alloc = new int[availableResources.length];
        int[] need = new int[availableResources.length];
        for (int i = 0; i < availableResources.length; i++) {
            available[i] = availableResources[i] - requestResource[i];
            alloc[i] = nextProcess.getAllocation()[i] + requestResource[i];
            need[i] = nextProcess.getNeed()[i] + requestResource[i];
        }

        processText.append("<br>Modify the state");
        processText.append("<br>available  = ").append(Arrays.toString(availableResources)).append(" - ").append(Arrays.toString(requestResource)).append(" = ").append(Arrays.toString(available));
        processText.append("<br>allocation = ").append(Arrays.toString(nextProcess.getAllocation())).append(" + ").append(Arrays.toString(requestResource)).append(" = ").append(Arrays.toString(alloc));
        processText.append("<br>need = ").append(Arrays.toString(nextProcess.getNeed())).append(" + ").append(Arrays.toString(requestResource)).append(" = ").append(Arrays.toString(need));

        Step s = new Step(matchingProcessIndex, processText.toString(), process);
        requestSequenceSteps.add(new Step(matchingProcessIndex, processText.toString(), process, true));
        s.setNewAllocation(alloc);
        s.setNewAvailable(available);
        s.setNewNeed(need);
        return true;

    }

    public void modifyStateFromRequest(int processNum) {
        Process nextProcess = processes.get(processNum+1);
        for (int i = 0; i < availableResources.length; i++) {
            availableResources[i] -= requestResource[i];
            nextProcess.getAllocation()[i] += requestResource[i];
            nextProcess.getNeed()[i] += requestResource[i];
        }
    }

    public ArrayList<Step> getSafeSequenceSteps() {
        return safeSequenceSteps;
    }

    public ArrayList<Step> getRequestSequenceSteps() {
        return requestSequenceSteps;
    }
}
