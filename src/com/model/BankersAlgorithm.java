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

        // Initialize the visited and safeSequence arrays to default values.
        boolean[] visited = new boolean[numberOfProcesses];
        int[] safeSequence = new int[numberOfProcesses];
        Arrays.fill(visited, false);

        // Initialize the work array to the available resources.
        int[] work = Arrays.copyOf(availableResources, numberOfResources);

        while (count < numberOfProcesses) {
            boolean flag = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                Process process = processes.get(i);

                if (!visited[i]) {
                    System.out.println("\nFor Process " + i);
                    int j;
                    for (j = 0; j < numberOfResources; j++) {
                        if (process.getNeed()[j] > work[j]) {
                            System.out.println("     Finish["+i+"] is false and Need > Work so P"+i + " must wait.");
                            break;
                        }
                    }

                    if (j == numberOfResources) {
                        safeSequence[count++] = i;
                        visited[i] = true;
                        flag = true;
                        System.out.print("     Finish["+i+"] is true and Need <= Work so P"+i + " must be kept in the safe sequence.\n     Work = Work + Allocation =  ");

                        for (j = 0; j < numberOfResources; j++) {
                            System.out.print((work[j] + process.getAllocation()[j]) + ", ");
                            work[j] += process.getAllocation()[j];
                        }
                    }
                    // Create a string representation of the Finish array
                    String finishString = "\n     Finish = [";
                    for (int k = 0; k < numberOfProcesses; k++) {
                        finishString += " " + visited[k] + " |";
                    }
                    finishString = finishString.substring(0, finishString.length() - 1) + " ]";
                    System.out.println(finishString);
                }
            }
            if (!flag) {
                break;
            }
        }

        if (count < numberOfProcesses) {
            System.out.println("The System is UnSafe!");
            safeSequence = null; // The system is unsafe, so there is no safe sequence.
        } else {
            String[] ss = new String[numberOfProcesses];
            System.out.println("Following is the SAFE Sequence");
            for (int i = 0; i < numberOfProcesses; i++) {
                System.out.print("P" + safeSequence[i]);
                ss[i] = ("P" + safeSequence[i]);
                if (i != numberOfProcesses - 1)
                    System.out.print(" -> ");
            }
            this.safeSequence = ss;
        }
    }

    public boolean requestResource() {
        // Check if the request can be granted.
        int processIndex = -1;
        Process process = null;
        for (int i = 0; i < processes.size(); i++) {
            if (Arrays.equals(processes.get(i).getAllocation(), requestResource)) {
                processIndex = i;
                process = processes.get(i);
                break;
            }
        }
        if (processIndex == -1) {
            return false; // Process not found.
        }

        for (int i = 0; i < availableResources.length; i++) {
            if (requestResource[i] > availableResources[i]) {
                return false; // Request cannot be granted.
            }
        }

        // Grant the request.
        for (int i = 0; i < availableResources.length; i++) {
            availableResources[i] -= requestResource[i];
            process.getAllocation()[i] += requestResource[i];
            process.getNeed()[i] -= requestResource[i];
        }
        return true;
    }

}
