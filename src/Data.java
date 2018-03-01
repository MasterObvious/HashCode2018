import java.io.*;
import java.util.*;

public class Data {

    public int rowNumber;
    public int columnNumber;
    public int vehicleNumber;
    public int rideNumber;
    public int perRideBonus;
    public int stepNumber;

    public List<Ride> rideList = new ArrayList<>();

    private String mInputFile;

    public void  Data(String fileInput) throws IOException {
        mInputFile = fileInput;

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

        Collections.sort(rideList, new Comparator<Ride>(){
            @Override
            public int compare(Ride o1, Ride o2) {
                if (o1.earliestStart == o2.earliestStart){
                    return Integer.compare(o1.length, o2.length);
                }else{
                    return Integer.compare(o1.earliestStart, o2.earliestStart);
                }
            }
        });
    }

    public void output(Map<Car, List<Ride>> result) throws IOException {
        String outputFile = "";
        if(mInputFile.contains("a_")){
            outputFile = "output/a_output.txt";
        }else if (mInputFile.contains("b_")){
            outputFile = "output/b_output.txt";
        }else if (mInputFile.contains("c_")){
            outputFile = "output/b_output.txt";
        }else if (mInputFile.contains("d_")){
            outputFile = "output/b_output.txt";
        }else if (mInputFile.contains("e_")){
            outputFile = "output/b_output.txt";
        }

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fileWriter);


    }
}
