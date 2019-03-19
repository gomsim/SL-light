import java.util.*;

/**
 * Implementation of graph system capable of finding shortest route using A*.
 * @author Simon Gombrii sigo8377 Joakim Ingman join9144
 */

public class SLGraph {

    private HashMap<String, Stop> graph = new HashMap<>();
    private static final int MAX_WAIT_TIME = 60;

    /**
     * Add a Stop-object as a node in the graph.
     * @param name of the stop in readable format, used as key in the Graph HashMap.
     * @param x coordinate of the stops location in the real world.
     * @param y coordinate of the stops location in the real world.
     * @return true if Stop is added to the graph, false if the graph contains the Stop.
     */
    public boolean addStop(String name, int x, int y){
        if (graph.containsKey(name))
            return false;
        graph.put(name, new Stop(name,x,y));
        return true;
    }

    /**
     * Remove Stop (node) from graph.
     * @param name of the Stop to be removed.
     * @return true if Stop is removed, false if graph does not contain the Stop.
     */
    public Stop remove(String name){
        return graph.remove(name);
    }

    /**
     * Connects two Stops (nodes) with a Departure (edge).
     * @param type of transportation. Bus, Train, Tram.
     * @param start node of the new edge.
     * @param dest-node of the new edge.
     * @param depTime from the start node.
     * @param arrTime to the destination node.
     * @param line name of the line this departure appears along.
     * @return true if the two Stops are connected, false if one of the Stops does not exist in the graph.
     */
    public boolean connect(Departure.TransportType type, String start, String dest, int depTime, int arrTime, String line){
        if (!graph.containsKey(start) || !graph.containsKey(dest))
            return false;
        graph.get(start).addDeparture(depTime,new Departure(type, line,graph.get(start),graph.get(dest),depTime, arrTime));
        return true;
    }

    /**
     * Remove a Departure (edge) between two Stops (nodes).
     * @param start node of the edge to be removed.
     * @param depTime of the node to be removed.
     * @return true if departure is removed, false if graph does not contain the departure.
     */
    public boolean disconnect(String start, int depTime) {
        return graph.containsKey(start) && graph.get(start).removeDeparture(depTime);
    }

    /**
     * Calculates the heuristic cost used by A* algorithm.
     * Using pythagoras theorem to find the linear distance between a stop and the goal of the journey multiplied by the speed of the current transport.
     * @param dep-edge between the current evaluated node and the next node to evaluate.
     * @param goal-node of the trip.
     * @param next node to evaluate in the shortest path algorithm.
     * @return heuristic cost.
     */
    private int heuristic(Departure dep, Stop goal, Stop next){
        return (int)(Math.sqrt(Math.pow(Math.abs(goal.getX()-next.getX()),2) + Math.pow(Math.abs(goal.getY()-next.getY()),2)) * dep.getType().speed);

    }

    /**
     * Find the shortest route in the public transportation graph using an A* algorithm.
     * @param start node of the journey.
     * @param dest-node of the journey.
     * @param time when the journey starts.
     * @return a LinkedList of departures detailing the journey.
     */
    public LinkedList<Departure> findRoute(String start, String dest, int time){
        PriorityQueue<AStarEntry> queue = new PriorityQueue<>();
        HashSet<Stop> visited = new HashSet<>();
        AStarEntry current = new AStarEntry(0, new Departure(null,"Start",null,graph.get(start),time,time), null, 0);
        queue.add(current);

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
                    int priorityCost = heuristic(dep.getValue(),to,graph.get(dest)) +
                            costSoFar;
                    queue.add(new AStarEntry(priorityCost,dep.getValue(), current, costSoFar));
                }
            }
        }
        LinkedList<Departure> path = new LinkedList<>();
        if (visited.contains(graph.get(dest))){
            while(current != null){
                path.addFirst(current.departure);
                current = current.path;
            }
        }
        return path;
    }

    /**
     *
     * @return a String representation of the graph.
     */
    public String toString(){
        return graph.toString();
    }

    /**
     * Inner class used to give Departures (nodes) a priority and keeping track of the path taken for the journey being evaluated.
     * Only used during A* search in findRoute().
     */
    private class AStarEntry implements Comparable<AStarEntry>{

        int priorityCost, costSoFar;
        Departure departure;
        AStarEntry path;

        /**
         * Construct a new AStarEntry.
         * @param priorityCost by the A* heuristic.
         * @param departure edge between two Stops.
         * @param path reference to previous AStarEntry for the current journey.
         * @param costSoFar total cost for this path.
         */
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
