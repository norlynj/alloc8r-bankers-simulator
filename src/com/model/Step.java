package model;

import java.util.ArrayList;
import java.util.Arrays;

public class Step {
    int processNumber;
    boolean modifyState;
    String text;
    Process process;
    ArrayList safeSequence;

    int[] newAvailable, newAllocation, newNeed;

    public Step(int processNumber, String text, Process process) {
        this(processNumber, text, process, false, null);
    }

    public Step(int processNumber, String text, Process process, boolean modifyState) {
        this(processNumber, text, process, modifyState, null);
    }

    public Step(int processNumber, String text, Process process, boolean modifyState, ArrayList safeSequence) {
        this.processNumber = processNumber;
        this.text = text;
        this.process = process;
        this.safeSequence = safeSequence;
        this.modifyState = modifyState;
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

    public boolean isModifyState() {
        return modifyState;
    }
}
