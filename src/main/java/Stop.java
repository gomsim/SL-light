import java.util.TreeMap;

/**
 * Class representing stops (nodes) in SLGraph-class.
 * @author Simon Gombrii sigo8377 Joakim Ingman join9144
 */

public class Stop {
    private String name;
    private double x,y;
    private TreeMap<Integer, Departure> departures = new TreeMap<>();

    public Stop(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public TreeMap<Integer, Departure> getDepartures() {
        return departures;
    }

    public int hashCode(){
        return name.hashCode();
    }
    public boolean equals(Object other){
        if (!(other instanceof Stop))
            return false;
        Stop otherStop = (Stop)other;
        return otherStop.name.equals(name);
    }

    public boolean addDeparture(int departTime, Departure d) {
        if (departures.containsValue(d)) //ANVÄNDER JAVAS STANDARD-equals och hashcode
            return false;
        if (departures.containsKey(departTime))
            departTime++;
        departures.put(departTime,d);
        return true;
    }
    public boolean removeDeparture(String destination, int departTime){
        if (!departures.containsKey(departTime))
            return false;
        departures.remove(departTime);
        return true;
    }
    public Departure getNextDeparture(int arrivalTime){
        return departures.ceilingEntry(arrivalTime).getValue();
    }

    public String toString(){
        return name;
    }

    //FOR TESTING
    public void printDepartures(){
        for (Departure d : departures.values()){
            if(d.getNext().toString().equals("T4"))
                System.out.print(d.getFrom() + ": " + d.getDepartureTime() + " to " + d.getNext() + ": " + d.getArrivalTime() + "\n");
        }
    }
}
