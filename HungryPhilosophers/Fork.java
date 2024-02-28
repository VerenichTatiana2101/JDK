public class Fork {
    private volatile boolean used;
    private static int nextForkNumber = 0;
    private int num;

    public Fork(boolean used) {
        this.used = used;
        this.num = nextForkNumber;
        nextForkNumber++;
    }

    public int getNum() {
        return num;
    }

    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "\nFork " +
                "used=" + used +
                ", num=" + getNum();
    }
}
