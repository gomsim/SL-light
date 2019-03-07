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
        AStarEntry current = new AStarEntry(0, new Departure("Start",null,graph.get(start),time,time), null, 0);
        queue.add(current);
        //visited.add(current.departure.getNext());

        while (!queue.isEmpty() && !current.departure.getNext().equals(graph.get(dest))){
            do {
                current = queue.poll();
            }while (visited.contains(current.departure.getNext()));
            visited.add(current.departure.getNext());
            System.out.println("\nAt: " + current);
            for (Map.Entry<Integer,Departure> dep: current.departure.getNext().getDepartures().tailMap(current.departure.getArrivalTime()).entrySet()){
                Stop to = dep.getValue().getNext();
                if (dep.getKey() - current.departure.getArrivalTime() > MAX_WAIT_TIME)
                    break;
                if (!visited.contains(to)) {
                    int costSoFar = dep.getValue().getCost(dep.getKey()) +
                            (dep.getKey()-current.departure.getArrivalTime() +
                                    current.costSoFar);
                    int priorityCost = heuristic(to,graph.get(dest)) +
                            costSoFar;
                    System.out.print("("+priorityCost + " " + costSoFar + " to " + dep.getValue().getNext() + ") ");
                    queue.add(new AStarEntry(priorityCost,dep.getValue(), current, costSoFar));
                    //visited.add(dep.getValue().getNext());
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

        int priorityCost, costSoFar;
        Departure departure;
        AStarEntry path;

        public AStarEntry(int priorityCost, Departure departure, AStarEntry path, int costSoFar){
            this.priorityCost = priorityCost;
            this.costSoFar = costSoFar;
            this.departure = departure;
            this.path = path;
        }

        public int compareTo(AStarEntry other){
            return priorityCost - other.priorityCost;
        }
        public String toString(){
            return departure.getNext().toString();
        }
    }
}
