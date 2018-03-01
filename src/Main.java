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

    public static Map<Car, List<Ride>> assignRides(){
        return null;
    }
}
