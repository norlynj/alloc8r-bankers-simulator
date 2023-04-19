package model;

/*
* Step 1: Calculate need: which is already calculated on the Process object
* Step 2: Determine the safe sequence
*/
public class SafetyAlgorithm extends BankersAlgorithm {
    @Override
    public void simulate() {
        int count=0;
        int m = getAvailableResources().length;
        int n = getProcesses().size();
        int safeSequence[] = new int[n];

        //visited array to find the already allocated process
        boolean visited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        //work array to store the copy of available resources
        int work[] = new int[m];
        for (int i = 0;i < m; i++) {
            work[i] = getAvailableResources()[i];
        }

        while (count < n) {
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                if (visited[i] == false) {
                    System.out.println("\nFor Process " + i);
                    int j;
                    for (j = 0; j < m; j++) {
                        if (getProcesses().get(i).getNeed()[j] > work[j]) {
                            System.out.print("     Finish["+i+"] is false and Need > Work so P"+i + " must wait.");
                            break;
                        }
                    }

                    if (j == m) {
                        safeSequence[count++]=i;
                        visited[i]=true;
                        flag=true;
                        System.out.println("     Finish["+i+"] is true and Need < Work so P"+i + " must be kept in the safe sequence.");

                        for (j = 0;j < m; j++) {
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
        if (count < n) {
            System.out.println("The System is UnSafe!");
        }
        else {
            //System.out.println("The given System is Safe");
            System.out.println("Following is the SAFE Sequence");
            for (int i = 0; i < n; i++) {
                System.out.print("P" + safeSequence[i]);
                if (i != n - 1)
                    System.out.print(" -> ");
            }
        }
    }
}
