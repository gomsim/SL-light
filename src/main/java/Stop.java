import java.util.Map;
import java.util.TreeMap;

public class Stop {
    private String name;
    private int id;
    private double x,y;
    private TreeMap<Integer, Departure> departures = new TreeMap<>();

    public Stop(String name, int id, double x, double y){
        this.name = name;

        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Map<Integer, Departure> getDepartures() {
        return departures;
    }

    public void addDeparture(int departTime, Departure d) {
        departures.put(departTime,d);
    }
    public Departure getNextDeparture(int arrivalTime){
        return departures.ceilingEntry(arrivalTime).getValue();
    }

}
