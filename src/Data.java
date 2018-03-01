import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public int rowNumber;
    public int columnNumber;
    public int vehicleNumber;
    public int rideNumber;
    public int perRideBonus;
    public int stepNumber;

    public List<Ride> rideList = new ArrayList<>();

    public void  Data(String fileInput) throws IOException {
        FileReader fileReader = new FileReader(fileInput);
        BufferedReader br = new BufferedReader(fileReader);

        String line = br.readLine();
        String[] splitData = line.split(" ");
        rowNumber = Integer.valueOf(splitData[0]);
        columnNumber = Integer.valueOf(splitData[1]);
        vehicleNumber = Integer.valueOf(splitData[2]);
        rideNumber = Integer.valueOf(splitData[3]);
        perRideBonus = Integer.valueOf(splitData[4]);
        stepNumber = Integer.valueOf(splitData[5]);

        for (int i = 0; i < rideNumber; i++){
            line = br.readLine();
            splitData = line.split(" ");
            int a = Integer.valueOf(splitData[0]);
            int b = Integer.valueOf(splitData[1]);
            int x = Integer.valueOf(splitData[2]);
            int y = Integer.valueOf(splitData[3]);
            int s = Integer.valueOf(splitData[4]);
            int f = Integer.valueOf(splitData[5]);

            Ride r = new Ride(i, a, b, x, y, s, f);
            rideList.add(r);
        }
    }
}
