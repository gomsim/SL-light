/**
 * Class representing departures (edges) from stops (nodes) in SLGraph-class.
 * @author Simon Gombrii sigo8377 Joakim Ingman join9144
 */

public class Departure {

    public enum TransportType {
        TUBE(1),
        BUS(3),
        TRAM(2),
        TEST(0.5);

        double speed;

        TransportType(double speed){
            this.speed = speed;
        }
    }

    private TransportType type;
    private String line;
    private Stop from, next;
    private int departureTime, arrivalTime;

    public Departure(TransportType type, String line, Stop from, Stop next, int departureTime, int arrivalTime){
        this.type = type;
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
    public TransportType getType(){
        return type;
    }
    public String getLine(){
        return line;
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