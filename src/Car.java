import java.util.*;

public class Car {
    public int x;
    public int y;
    public int freeFrom;
    private PriorityQueue<RideWithDistance> sortedRides;

    public Car(Collection<Ride> rides){
        x = 0;
        y = 0;
        freeFrom = 0;
        Comparator<RideWithDistance> comparator = new RideComparator();
        sortedRides = new PriorityQueue<>(comparator);
        for(Ride r : rides) {
            sortedRides.add(new RideWithDistance(r,this));
        }
    }
    public void update(Integer timeStep) {
        sortedRides = sortRides(timeStep);
    }
    public Integer distanceFrom(int x, int y) {
        return Math.abs(this.x-x) + Math.abs(this.y-y);
    }
    public Integer distanceFrom(Ride ride) {
        return Math.abs(this.x-ride.fromX) + Math.abs(this.y-ride.fromY);
    }
    public Ride getOptimalRide() {
        return sortedRides.peek().ride;
    }

    private PriorityQueue<RideWithDistance> sortRides(Integer timeStep) {
        Comparator<RideWithDistance> comparator1 = new RideComparator();
        PriorityQueue<RideWithDistance> sorted = new PriorityQueue<>(comparator1);

        for(RideWithDistance r : sortedRides) {
            if(r.ride.earliestStart - timeStep >= this.distanceFrom(r.ride.fromX, r.ride.fromY))
                sorted.add(r);
        }

        if(sorted.isEmpty()) {
            Comparator<RideWithDistance> comparator2 = new RideComparator2();
            sorted = new PriorityQueue<>(comparator2);
            for(RideWithDistance r: sortedRides) {
                if(distanceFrom(r.ride.fromX,r.ride.fromY) + r.ride.length+timeStep < r.ride.latestFinish)
                    sorted.add(r);
            }
        }

        return sorted;
    }
    private class RideComparator implements Comparator<RideWithDistance>{
        @Override
        public int compare(RideWithDistance o1, RideWithDistance o2) {
            return o1.ride.earliestStart-o2.ride.earliestStart;
        }
    }
    private class RideWithDistance {
        Integer distance;
        Ride ride;
        public RideWithDistance(Ride ride, Car car) {
            distance = car.distanceFrom(ride);
            this.ride = ride;
        }
    }
    private class RideComparator2 implements Comparator<RideWithDistance>{
        @Override
        public int compare(RideWithDistance o1, RideWithDistance o2) {
            return o1.distance-o2.distance;
        }
    }
}

