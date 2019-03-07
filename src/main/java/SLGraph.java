import java.util.HashMap;
import java.util.List;

public class SLGraph {

    HashMap<String, Stop> graph = new HashMap<String, Stop>();

    public SLGraph(){

    }

    public boolean addStop(String name, int x, int y){
        if (graph.containsKey(name))
            return false;
        graph.put(name, new Stop(name,x,y));
        return true;
    }
    public Stop remove(String name){
        return graph.remove(name);
    }
    public boolean connect(String start, String dest, int depTime, int arrTime, String line){
        if (!graph.containsKey(start) || !graph.containsKey(dest))
            return false;
        return graph.get(start).addDeparture(depTime,new Departure(line,graph.get(dest),arrTime));
    }
    public boolean disconnect(String start, String dest, int depTime){
        if (!graph.containsKey(start))
            return false;
        return graph.get(start).removeDeparture(dest, depTime);
    }
    public List<Stop> findRoute(String start, String dest, int time){
        return null;
    }
}
