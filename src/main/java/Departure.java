public class Departure {

    private String line;
    private Stop next;
    private int arrivalTime;

    public Departure(String line, Stop next, int arrivalTime){
        this.line = line;
        this.next = next;
        this.arrivalTime = arrivalTime;
    }
    public int getCost(int departureTime){
        return arrivalTime - departureTime;
    }
}