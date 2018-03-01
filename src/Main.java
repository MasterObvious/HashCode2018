import java.util.LinkedList;
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

    public static Map<Car, List<Ride>> assignRides(List<Ride> rides, List<Car> cars){
        int timeStep = 0;
        //Get list of available cars
        List<Car> available = new LinkedList<Car>();
        for(Car c: cars){
            if(c.freeFrom <= timeStep){
                available.add(c);
            }
        }


        for(Car c: available){

        }


        return null;
    }
}
