public class Car {
    public int x;
    public int y;
    public Car(){
        x = 1;
        y = 1;
    }
    public Integer distanceFrom(int x, int y) {
        return Math.abs(this.x-x) + Math.abs(this.y-y);
    }
}
