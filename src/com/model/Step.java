package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Step {
    int processNumber;
    String text;
    Process process;
    ArrayList safeSequence;

    int[] newAvailable, newAllocation, newNeed;

    public Step(int processNumber, String text, Process process) {
        this(processNumber, text, process, null);
    }

    public Step(int processNumber, String text, Process process, ArrayList safeSequence) {
        this.processNumber = processNumber;
        this.text = text;
        this.process = process;
        this.safeSequence = safeSequence;
    }

    public int getProcessNumber() {
        return processNumber;
    }

    public String getText() {
        return text;
    }

    public Process getProcess() {
        return process;
    }

    public String getSafeSequence() {
        String[] ss = new String[safeSequence.size()];
        for (int i = 0; i < safeSequence.size(); i++) {
            ss[i] = ("P" + safeSequence.get(i));
        }
        return Utility.arrayToString(ss);
    }

    public void setNewAvailable(int[] newAvailable) {
        this.newAvailable = newAvailable;
    }

    public void setNewAllocation(int[] newAllocation) {
        this.newAllocation = newAllocation;
    }

    public void setNewNeed(int[] newNeed) {
        this.newNeed = newNeed;
    }

    public int[] getNewAvailable() {
        return newAvailable;
    }

    public int[] getNewAllocation() {
        return newAllocation;
    }

    public int[] getNewNeed() {
        return newNeed;
    }
}
