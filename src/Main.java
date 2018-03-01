import java.util.*;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String args[]){
        System.out.println("It's not stupid");

        Integer hello = 1;
        synchronized (hello) {
            hello = hello * 2;

            System.out.println(hello);
        }
        System.out.println("hello");
    }

    private static boolean allCarsFree(List<Car> cars, int timeStep){
        boolean allFree = true;
        for(Car c: cars){
            if(c.freeFrom > timeStep){
                allFree = false;
            }
        }
        return allFree;
    }

    public static Map<Car, List<Ride>> assignRides(List<Car> cars){
        int timeStep = 0;
        Map<Car, List<Ride>> assignedRides = new HashMap<>();

        boolean assignedARide = true;
        //iterate timestep until all rides assigned
        while(assignedARide || !allCarsFree(cars, timeStep)) {
            assignedARide = false;

            //Get list of available cars
            List<Car> available = new LinkedList<>();
            for (Car c : cars) {
                if (c.freeFrom <= timeStep) {
                    c.update(timeStep);
                    available.add(c);
                }
            }

            //assign cars to rides until none left available
            while (!available.isEmpty()) {
                //find optimum ride to assign
                int shortestDist = Integer.MAX_VALUE;
                Ride shortestDistRide = null;
                Car carToAssign = null;
                for (Car c : available) {
                    Ride thisShortestDistRide = c.getShortestDistanceRide();
                    if (thisShortestDistRide.length < shortestDist) {
                        shortestDist = thisShortestDistRide.length;
                        shortestDistRide = thisShortestDistRide;
                        carToAssign = c;
                    }
                }
                //assign this ride
                if (shortestDistRide != null) {
                    assignedARide = true;
                    carToAssign.freeFrom = shortestDist +
                            carToAssign.distanceFrom(shortestDistRide.fromX, shortestDistRide.fromY);

                    //Store data to return
                    if(assignedRides.containsKey(carToAssign)){
                        assignedRides.get(carToAssign).add(shortestDistRide);
                    }else{
                        assignedRides.put(carToAssign, new ArrayList<>());
                    }
                } else {
                    //break as all rides assigned
                    break;
                }

                //remove the ride from lists of potential rides for all cars
                for (Car c : cars) {
                    c.sortedRides.remove(shortestDistRide);
                }
            }
        }
        return assignedRides;
    }
}
