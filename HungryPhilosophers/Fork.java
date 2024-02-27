public class Fork {
    private boolean used;
    private static int nextForkNumber = 1;
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

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "\nFork " +
                "used=" + used +
                ", num=" + getNum();
    }
}
