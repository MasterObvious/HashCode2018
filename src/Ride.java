public class Ride {
    public Integer rideNumber;
    public Integer fromX, fromY;
    public Integer toX, toY;
    public Integer earliestStart;
    public Integer latestFinish;

    public Ride(Integer rideNumber, Integer fromX, Integer fromY, Integer toX, Integer toY,
                Integer earliestStart, Integer latestFinish) {
        this.rideNumber = rideNumber;
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
    }
}
