import java.util.*;

public class SLGraph {

    HashMap<String, Stop> graph = new HashMap<String, Stop>();
    private static final int MAX_WAIT_TIME = 60;

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
        return graph.get(start).addDeparture(depTime,new Departure(line,graph.get(start),graph.get(dest),depTime, arrTime));
    }
    public boolean disconnect(String start, String dest, int depTime){
        if (!graph.containsKey(start))
            return false;
        return graph.get(start).removeDeparture(dest, depTime);
    }
    private int heuristic(Stop goal, Stop next){
        return (int)Math.sqrt(Math.pow(Math.abs(goal.getX()-next.getX()),2) + Math.pow(Math.abs(goal.getY()-next.getY()),2));

    }
    public List<Departure> findRoute(String start, String dest, int time){
        PriorityQueue<AStarEntry> queue = new PriorityQueue<>();
        HashSet<Stop> visited = new HashSet<>();
        AStarEntry current = new AStarEntry(0, new Departure("Start",null,graph.get(start),time,time), null);
        queue.add(current);
        visited.add(current.departure.getNext());

        while (!queue.isEmpty() && !current.departure.getNext().equals(graph.get(dest))){
            current = queue.poll();
            System.out.println("\nAt: " + current);
            for (Map.Entry<Integer,Departure> dep: current.departure.getNext().getDepartures().tailMap(current.departure.getArrivalTime()).entrySet()){
                Stop to = dep.getValue().getNext();
                if (dep.getKey() - current.departure.getArrivalTime() > MAX_WAIT_TIME)
                    break;
                if (!visited.contains(to)) {
                    int weight = heuristic(to,graph.get(dest)) +
                            dep.getValue().getCost(dep.getKey()) +
                            (dep.getKey()-current.departure.getArrivalTime() +
                                    current.cost);
                    System.out.print("("+weight + " to " + dep.getValue().getNext() + ") ");
                    queue.add(new AStarEntry(weight,dep.getValue(), current));
                    visited.add(dep.getValue().getNext());
                }
            }
        }
        System.out.println();
        LinkedList<Departure> path = new LinkedList<>();
        if (visited.contains(graph.get(dest))){
            while(current != null){
                path.addFirst(current.departure);
                current = current.path;
            }
        }
        return path;
    }

    public String toString(){
        return graph.toString();
    }

    private class AStarEntry implements Comparable<AStarEntry>{

        int cost;
        Departure departure;
        AStarEntry path;

        public AStarEntry(int cost, Departure departure, AStarEntry path){
            this.cost = cost;
            this.departure = departure;
            this.path = path;
        }

        public int compareTo(AStarEntry other){
            return cost - other.cost;
        }
        public String toString(){
            return departure.getNext().toString();
        }
    }
}
