import java.util.PriorityQueue;

public class Car {
    public int x;
    public int y;
    public int freeFrom;
    public PriorityQueue<Ride> sortedRides;

    public Car(List<Ride> rides){
        x = 1;
        y = 1;
        freeFrom = 0;
        sortedRides = sortRides(rides);
    }
    public Integer distanceFrom(int x, int y) {
        return Math.abs(this.x-x) + Math.abs(this.y-y);
    }
}
