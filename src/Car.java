import java.util.*;

public class Car {
    public int x;
    public int y;
    public int freeFrom;
    public PriorityQueue<Ride> sortedRides;

    public Car(Collection<Ride> rides){
        x = 0;
        y = 0;
        freeFrom = 0;
        Comparator<Ride> comparator = new RideComparator();
        sortedRides = new PriorityQueue<>(comparator);
        sortedRides.addAll(rides);
    }
    public void update(Integer timeStep) {
        sortedRides = sortRides(timeStep);
    }
    public Integer distanceFrom(int x, int y) {
        return Math.abs(this.x-x) + Math.abs(this.y-y);
    }
    public Ride getShortestDistanceRide() {
        return sortedRides.peek();
    }

    private PriorityQueue<Ride> sortRides(Integer timeStep) {
        Comparator<Ride> comparator = new RideComparator();
        PriorityQueue<Ride> sorted = new PriorityQueue<>(comparator);

        for(Ride r : sortedRides) {
            if(r.earliestStart - timeStep >= this.distanceFrom(r.fromX, r.fromY))
                sorted.add(r);
        }

        return sorted;
    }
    private class RideComparator implements Comparator<Ride>{
        @Override
        public int compare(Ride o1, Ride o2) {
            return o1.earliestStart-o2.earliestStart;
        }
    }
}

