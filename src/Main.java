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
        Map<Car,List<Ride>> result = assignRides(carList, data.stepNumber);
        data.output(result);

    }


    public static Map<Car, List<Ride>> assignRides(List<Car> cars, int numTimeSteps){
        Map<Car, List<Ride>> assignedRides = new HashMap<>();

        boolean assignedARide = true;
        //iterate timestep until all rides assigned
        for(int timeStep = 0; timeStep < numTimeSteps; timeStep++) {

            //Get list of available cars
            List<Car> available = new LinkedList<>();
            for (Car c : cars) {
                if(c.freeFrom <= timeStep) {
                    c.update(timeStep);
                    available.add(c);
                }
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
                    Ride thisEarliestStartRide = c.getOptimalRide();
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
                    carToAssign.freeFrom = earliestStartRide.length + Math.max(earliestStartRide.earliestStart, timeStep
                            + carToAssign.distanceFrom(earliestStartRide.fromX, earliestStartRide.fromY));
                    available.remove(carToAssign);
                    carToAssign.x = earliestStartRide.toX;
                    carToAssign.y = earliestStartRide.toY;

                    System.out.println("Assigning ride: " + earliestStartRide.rideNumber);
                    //Store data to return
                    if(assignedRides.containsKey(carToAssign)){
                        assignedRides.get(carToAssign).add(earliestStartRide);
                    }else{
                        List<Ride> carRides =  new ArrayList<>();
                        carRides.add(earliestStartRide);
                        assignedRides.put(carToAssign, carRides);
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
            System.out.println();
        }
        return assignedRides;
    }
}
