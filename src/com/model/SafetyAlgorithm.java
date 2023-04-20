package model;

import java.util.Arrays;

/*
* Step 1: Calculate need: which is already calculated on the Process object
* Step 2: Determine the safe sequence
*/
public class SafetyAlgorithm extends BankersAlgorithm {
    @Override
    public void simulate() {
        int count=0;
        int numberOfResources = getAvailableResources().length;
        int numberOfProcesses = getProcesses().size();
        //visited array to find the already allocated process
        boolean visited[] = new boolean[numberOfProcesses];
        int safeSequence[] = new int[numberOfProcesses];

        for (int i = 0; i < numberOfProcesses; i++) {
            visited[i] = false;
        }

        // work = available
        int work[] = Arrays.copyOf(getAvailableResources(), numberOfResources);

        while (count < numberOfProcesses) {
            boolean flag = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                if (visited[i] == false) {
                    System.out.println("\nFor Process " + i);
                    int j;
                    for (j = 0; j < numberOfResources; j++) {
                        if (getProcesses().get(i).getNeed()[j] > work[j]) {
                            System.out.print("     Finish["+i+"] is false and Need > Work so P"+i + " must wait.");
                            break;
                        }
                    }

                    if (j == numberOfResources) {
                        safeSequence[count++]=i;
                        visited[i]=true;
                        flag=true;
                        System.out.println("     Finish["+i+"] is true and Need < Work so P"+i + " must be kept in the safe sequence.");

                        for (j = 0;j < numberOfResources; j++) {
                            System.out.println("     Work = Work + Allocation, so: " + work[j] + " + " + getProcesses().get(i).getAllocation()[j] + " = " + (work[j] + getProcesses().get(i).getAllocation()[j]));
                            work[j] = work[j]+getProcesses().get(i).getAllocation()[j];
                        }
                    }
                }
            }
            if (flag == false) {
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
