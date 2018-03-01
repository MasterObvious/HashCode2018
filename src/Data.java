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

    private InputType mInType;

    public Data(InputType fileType) throws IOException {

        String fileInput = inputFileFromEnum(fileType);

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

        br.close();

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
        String outputFile = outputFileFromEnum(mInType);

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fileWriter);

        for (Car c : result.keySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(result.get(c).size());
            for (Ride r : result.get(c)){
                sb.append(" ");
                sb.append(r.rideNumber);
            }
            sb.append("\n");
            bw.write(sb.toString());
        }

        bw.close();
    }

    private String inputFileFromEnum(InputType in){
        mInType= in;
        switch(in){
            case FILE_A: return "data/a_example.in";
            case FILE_B: return "data/b_should_be_easy.in";
            case FILE_C: return "data/c_no_hurry.in";
            case FILE_D: return "data/d_metropolis.in";
            case FILE_E: return "data/e_high_bonus.in";
        }
        return null;
    }

    private String outputFileFromEnum(InputType in){
        switch(in){
            case FILE_A: return "output/a_output.txt";
            case FILE_B: return "output/b_output.txt";
            case FILE_C: return "output/c_output.txt";
            case FILE_D: return "output/d_output.txt";
            case FILE_E: return "output/e_output.txt";
        }

        return null;
    }
}
