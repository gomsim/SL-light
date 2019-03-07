import java.util.Map;
import java.util.TreeMap;

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
        if (departures.containsValue(d))
            return false;
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

}
