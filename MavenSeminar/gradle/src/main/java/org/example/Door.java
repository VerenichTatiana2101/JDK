package org.example;

import lombok.Getter;
import lombok.Setter;

public class Door {
    @Getter
    private static int nextDoorNumber = 0;
    @Getter
    private int num;

    @Getter
    @Setter
    private boolean present = false;

    public Door(boolean present) {
        this.num = nextDoorNumber;
        nextDoorNumber++;
        this.present = present;
    }

    @Override
    public String toString() {
        return "Door " + (getNum() + 1) +
                ", present=" + present;
    }
}
