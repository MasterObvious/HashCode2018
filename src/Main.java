import java.io.IOException;
import java.util.*;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws IOException {
        Data data = new Data(InputType.FILE_A);
        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < data.vehicleNumber; i++){
            carList.add(new Car(data.rideList));
        }
        Map<Car,List<Ride>> result = assignRides(carList);
        data.output(result);

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
                c.update(timeStep);
                available.add(c);
            }
            System.out.println("Time Step: " + timeStep);
            System.out.println("Number Available: " + available.size());

            //assign cars to rides until none left available
            while (!available.isEmpty()) {
                //find optimum ride to assign
                int earliestRideTime = Integer.MAX_VALUE;
                Ride earliestStartRide = null;
                Car carToAssign = null;
                for (Car c : available) {
                    Ride thisEarliestStartRide = c.getShortestDistanceRide();
                    //handle empty list of rides
                    if(thisEarliestStartRide == null){
                        continue;
                    }
                    //TODO: consider what to do in ties
                    if (thisEarliestStartRide.earliestStart < earliestRideTime) {
                        earliestRideTime = thisEarliestStartRide.earliestStart;
                        earliestStartRide = thisEarliestStartRide;
                        carToAssign = c;
                    }
                }
                //assign this ride
                if (earliestStartRide != null) {
                    assignedARide = true;
                    carToAssign.freeFrom = earliestStartRide.length +
                            carToAssign.distanceFrom(earliestStartRide.fromX, earliestStartRide.fromY);

                    //Store data to return
                    if(assignedRides.containsKey(carToAssign)){
                        assignedRides.get(carToAssign).add(earliestStartRide);
                    }else{
                        assignedRides.put(carToAssign, new ArrayList<>());
                    }
                } else {
                    //break as all rides assigned
                    break;
                }
                //remove the ride from lists of potential rides for all cars
                for (Car c : cars) {
                    c.sortedRides.remove(earliestStartRide);
                }
            }
            timeStep++;

            System.out.println();
        }
        return assignedRides;
    }
}
