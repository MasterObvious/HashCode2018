import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Data {

    public int rowNumber;
    public int columnNumber;
    public int vehicleNumber;
    public int rideNumber;
    public int perRideBonus;
    public int stepNumber;

    public void  Data(String fileInput) throws IOException {
        FileReader fileReader = new FileReader(fileInput);
        BufferedReader br = new BufferedReader(fileReader);

        String firstLine = br.readLine();
        String[] splitData = firstLine.split(" ");
        rowNumber = Integer.valueOf(splitData[0]);
        columnNumber = Integer.valueOf(splitData[1]);
        vehicleNumber = Integer.valueOf(splitData[2]);
        rideNumber = Integer.valueOf(splitData[3]);
        perRideBonus = Integer.valueOf(splitData[4]);
        stepNumber = Integer.valueOf(splitData[5]);
    }
}
