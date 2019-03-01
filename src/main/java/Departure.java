public class Departure {
    private Stop next;
    private int arrivalTime;

    public Departure(Stop next, int arrivalTime){
        this.next = next;
        this.arrivalTime = arrivalTime;
    }
    public int getCost(int departureTime){
        return arrivalTime - departureTime;
    }
}