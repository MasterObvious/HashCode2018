public class Ride {
    public int rideNumber;
    public int fromX, fromY;
    public int toX, toY;
    public int earliestStart;
    public int latestFinish;
    public int length;

    public Ride(int rideNumber, int fromX, int fromY, int toX, int toY,
                int earliestStart, int latestFinish) {
        this.rideNumber = rideNumber;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;

        this.length = Math.abs(this.fromX - this.toX) + Math.abs(this.fromY - this.toY);
    }
}
