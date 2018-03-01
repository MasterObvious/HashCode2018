import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args){
        dataTest();
    }

    public static void dataTest(){
        try{
            Data test = new Data(InputType.FILE_C);
            System.out.println(test.rideList);
            Car testCar = new Car(null);
            Map<Car, List<Ride>> testMap = new HashMap<>();
            testMap.put(testCar, test.rideList);
            test.output(testMap);
        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }
}
