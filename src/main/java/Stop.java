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

    /**
     * Adds a new departure from this stop. Departures are held in a TreeMap.
     * @param departTime serves as key in the TreeMap.
     * @param d is the departure object (edge).
     */
    public void addDeparture(int departTime, Departure d) {
        if (departures.containsKey(departTime)){
            departTime++;
            d.setDepTime(d.getDepartureTime()+1);
            d.setArrTime(d.getArrivalTime()+1);
        }
        departures.put(departTime,d);
    }

    /**
     * Removes departure(edge) from this stop.
     * @param departTime is used as key to get desired departure from TreeMap.
     * @return true if the departure existed beforehand.
     */
    public boolean removeDeparture(int departTime){
        if (!departures.containsKey(departTime))
            return false;
        departures.remove(departTime);
        return true;
    }

    public String toString(){
        return name;
    }
}
