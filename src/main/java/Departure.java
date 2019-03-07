public class Departure {

    private String line;
    private Stop from, next;
    private int departureTime, arrivalTime;

    public Departure(String line, Stop from, Stop next, int departureTime, int arrivalTime){
        this.line = line;
        this.from = from;
        this.next = next;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    public int getArrivalTime(){
        return arrivalTime;
    }
    public int getDepartureTime(){
        return departureTime;
    }
    public int getCost(int departureTime){
        return arrivalTime - departureTime;
    }
    public Stop getNext(){
        return next;
    }
    public Stop getFrom(){
        return from;
    }
    public String toString(){
        return line + ":" + departureTime/60 + ":" + (departureTime%60 < 10? "0"+departureTime%60: departureTime%60) + "->" + next;
    }
}