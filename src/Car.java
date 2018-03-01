public class Car {
    public int x;
    public int y;
    public int freeFrom;
    public Car(){
        x = 1;
        y = 1;
        freeFrom = 0;
    }
    public Integer distanceFrom(int x, int y) {
        return Math.abs(this.x-x) + Math.abs(this.y-y);
    }
}
