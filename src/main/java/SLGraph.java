import java.util.HashMap;
import java.util.List;

public class SLGraph {

    HashMap<String, Stop> graph = new HashMap<String, Stop>();

    public SLGraph(){

    }

    public boolean addStop(String name, int id, int x, int y){
        return false;
    }
    public boolean remove(String name){
        return false;
    }
    public boolean connect(String start, String dest, int depTime, int arrTime){
        return false;
    }
    public boolean disconnect(String start, String dest, int depTime){
        return false;
    }
    public List<Stop> findRoute(String start, String dest, int time){
        return null;
    }
}
