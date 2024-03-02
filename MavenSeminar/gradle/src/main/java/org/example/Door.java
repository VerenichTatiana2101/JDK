package org.example;

public class Door {
    private static int nextDoorNumber = 0;
    private int num;
    private boolean present = false;

    public Door(boolean present) {
        this.num = nextDoorNumber;
        nextDoorNumber++;
        this.present = present;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "Door " + (getNum() + 1) +
                ", present=" + present;
    }


}
