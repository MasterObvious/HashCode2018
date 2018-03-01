import java.util.*;

public class Car {
    public int x;
    public int y;
    public int freeFrom;
    private LinkedList<RideWithDistance> sortedRides;

    public Car(Collection<Ride> rides){
        x = 0;
        y = 0;
        freeFrom = 0;
        Comparator<RideWithDistance> comparator = new RideComparator();
        sortedRides = new LinkedList<>();
        for(Ride r : rides) {
            sortedRides.add(new RideWithDistance(r,this));
        }
        Collections.sort(sortedRides, comparator);
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
        RideWithDistance r = sortedRides.peek();
        if(r == null)
            return null;
        return r.ride;
    }

    private LinkedList<RideWithDistance> sortRides(Integer timeStep) {
        Comparator<RideWithDistance> comparator1 = new RideComparator();
        LinkedList<RideWithDistance> sorted = new LinkedList<>();

        for(RideWithDistance r : sortedRides) {
            if(r.ride.earliestStart - timeStep >= this.distanceFrom(r.ride.fromX, r.ride.fromY))
                sorted.add(r);
        }

        for(RideWithDistance r : sorted) {
            sortedRides.remove(r);
        }
        Collections.sort(sorted, comparator1);

        LinkedList<RideWithDistance> sorted2 = new LinkedList<>();
        Comparator<RideWithDistance> comparator2 = new RideComparator2();
        for(RideWithDistance r: sortedRides) {
            if(distanceFrom(r.ride.fromX,r.ride.fromY) + r.ride.length+timeStep < r.ride.latestFinish)
                sorted.add(r);
        }
        Collections.sort(sorted2, comparator2);
        sorted.addAll(sorted2);

        return sorted;
    }
    public void removeRide(Ride r) {
        for(RideWithDistance rWD : sortedRides) {
            if(rWD.ride.rideNumber == r.rideNumber) {
                sortedRides.remove(rWD);
                break;
            }
        }
    }
    private class RideComparator implements Comparator<RideWithDistance>{
        @Override
        public int compare(RideWithDistance o1, RideWithDistance o2) {
            return (int)((1.0/(double)o1.ride.length) * o1.ride.earliestStart - (1.0/(double)o2.ride.length) * o2.ride.earliestStart);
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
            return (int)((1.0/(double)o1.ride.length) * o1.distance - (1.0/(double)o2.ride.length) * o2.distance);
        }
    }
}

