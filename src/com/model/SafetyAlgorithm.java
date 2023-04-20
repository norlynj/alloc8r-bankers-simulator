package model;

import java.util.Arrays;

/*
* Step 1: Calculate need: which is already calculated on the Process object
* Step 2: Determine the safe sequence
*/
public class SafetyAlgorithm extends BankersAlgorithm {
    public void simulate() {
        int count=0;
        int numberOfResources = getAvailableResources().length;
        int numberOfProcesses = getProcesses().size();

        // Initialize the visited and safeSequence arrays to default values.
        boolean[] visited = new boolean[numberOfProcesses];
        int[] safeSequence = new int[numberOfProcesses];
        Arrays.fill(visited, false);

        // Initialize the work array to the available resources.
        int[] work = Arrays.copyOf(getAvailableResources(), numberOfResources);

        while (count < numberOfProcesses) {
            boolean flag = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                Process process = getProcesses().get(i);

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
                        safeSequence[count++]=i;
                        visited[i]=true;
                        flag=true;
                        System.out.print("     Finish["+i+"] is true and Need <= Work so P"+i + " must be kept in the safe sequence.\n     Work = Work + Allocation =  ");

                        for (j = 0;j < numberOfResources; j++) {
                            System.out.print((work[j] + process.getAllocation()[j]) + ", ");
                            work[j] = work[j]+process.getAllocation()[j];
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
        }
        else {
            String[] ss = new String[numberOfProcesses];
            System.out.println("Following is the SAFE Sequence");
            for (int i = 0; i < numberOfProcesses; i++) {
                ss[i] = ("P" + safeSequence[i]);
                System.out.print("P" + safeSequence[i]);
                if (i != numberOfProcesses - 1)
                    System.out.print(" -> ");
            }
            setSafeSequence(ss);
        }
    }
}
