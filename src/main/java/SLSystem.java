import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * User interface class for the public transport rute finding system.
 * @author Simon Gombrii sigo8377 Joakim Ingman join9144
 */

public class SLSystem {

    private SLGraph graph = new SLGraph();
    private Scanner s;
    public static void main(String[] args){
        (new SLSystem()).run();
    }

    public void run(){
        s = new Scanner(System.in);
        init();
        boolean quit = false;
        while(!quit){
            switch(readInt("Välkommen till SL! Vad vill du göra \n1. Sök resa \n2. Avsluta")){
                case 1:
                    String start = readString("Vart ifrån åker du? ");
                    String stop = readString("Vart vill du åka? ");
                    int h = readInt("När vill du åka? \n timme: ");
                    int m = readInt("minut: ");
                    printPath(graph.findRoute(start, stop, formatTime(h,m)));
                    break;
                case 2:
                    quit = true;
                    break;
            }
        }
    }
    private int readInt(String m){
        System.out.print(m);
        int a = s.nextInt();
        s.nextLine();
        return a;
    }
    private String readString(String m){
        System.out.println(m);
        return s.nextLine().toUpperCase();
    }
    private String formatTime(int time){
        return time/60 + ":" + (time%60 < 10? "0"+time%60: time%60);
    }
    private int formatTime(int h, int m){
        return h*60+m;
    }
    private void init(){
        //Add Blue TUBE-stops
        graph.addStop("T1", 0, 11);
        graph.addStop("T2", 3, 13);
        graph.addStop("T3", 8, 14);
        graph.addStop("T4", 15, 15);
        graph.addStop("T5", 20, 15);
        graph.addStop("C", 24, 15);
        graph.addStop("T6", 29, 17);
        graph.addStop("T7", 33, 15);
        graph.addStop("T8", 36, 12);
        graph.addStop("T9", 41, 13);

        //Add stops on Circle TRAM-line
        graph.addStop("O1", 9,19);
        graph.addStop("O2", 13,21);
        graph.addStop("O3", 17,22);
        graph.addStop("O4", 24,23);
        graph.addStop("O5", 29,22);
        graph.addStop("O6", 30,12);
        graph.addStop("O7", 28,9);
        graph.addStop("O8", 23,6);
        graph.addStop("O9", 16,9);
        graph.addStop("O10", 12,10);

        //Add stops on BUS-line
        graph.addStop("B1", 10,0);
        graph.addStop("B2", 13,2);
        graph.addStop("B3", 13,6);
        graph.addStop("B4", 19,12);
        graph.addStop("B5", 24, 19);
        graph.addStop("B6", 25,27);
        graph.addStop("B7", 25,30);
        graph.addStop("B8", 22,35);

        //Add all eastward departures on Blue TUBE-Line
        for(int i = 4; i < 1402;){
            int departure = i;
            int arrival = i+2;
            graph.connect(Departure.TransportType.TUBE,"T1","T2", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+5;
            graph.connect(Departure.TransportType.TUBE,"T2","T3", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+7;
            graph.connect(Departure.TransportType.TUBE,"T3","T4", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+4;
            graph.connect(Departure.TransportType.TUBE,"T4","T5", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+3;
            graph.connect(Departure.TransportType.TUBE,"T5","C", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+5;
            graph.connect(Departure.TransportType.TUBE,"C","T6", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+4;
            graph.connect(Departure.TransportType.TUBE,"T6","T7", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+3;
            graph.connect(Departure.TransportType.TUBE,"T7","T8", departure, arrival, "Blue line E");
            departure = arrival;
            arrival = departure+5;
            graph.connect(Departure.TransportType.TUBE,"T8","T9", departure, arrival, "Blue line E");
            i = i+4;
        }

        //Add all westward departures on Blue TUBE-Line
        for(int i = 0; i < 1402;){
            int departure = i;
            int arrival = i+5;
            graph.connect(Departure.TransportType.TUBE,"T9","T8", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+3;
            graph.connect(Departure.TransportType.TUBE,"T8","T7", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+4;
            graph.connect(Departure.TransportType.TUBE,"T7","T6", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+5;
            graph.connect(Departure.TransportType.TUBE,"T6","C", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+3;
            graph.connect(Departure.TransportType.TUBE,"C","T5", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+4;
            graph.connect(Departure.TransportType.TUBE,"T5","T4", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+7;
            graph.connect(Departure.TransportType.TUBE,"T4","T3", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+5;
            graph.connect(Departure.TransportType.TUBE,"T3","T2", departure, arrival, "Blue line W");
            departure = arrival;
            arrival = departure+2;
            graph.connect(Departure.TransportType.TUBE,"T2","T1", departure, arrival, "Blue line W");
            i = i+4;
        }

        //Add all clockwise departures on Circle TRAM-Line
        for(int i = 8; i < 1335;){
            int departure = i;
            int arrival = i+7;
            graph.connect(Departure.TransportType.TRAM,"O1","O2", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O2","O3", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+14;
            graph.connect(Departure.TransportType.TRAM,"O3","O4", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+10;
            graph.connect(Departure.TransportType.TRAM,"O4","O5", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"O5","T6", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"T6","O6", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O6","O7", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+10;
            graph.connect(Departure.TransportType.TRAM,"O7","O8", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+14;
            graph.connect(Departure.TransportType.TRAM,"O8","O9", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O9","O10", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"O10","T3", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"T3","O1", departure, arrival, "Circle line CCW");
            i = i+10;
        }
        //Add all anti-clockwise departures on Circle TRAM-Line
        for(int i = 4; i < 1335;){
            int departure = i;
            int arrival = i+8;
            graph.connect(Departure.TransportType.TRAM,"O1","T3", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"T3","O10", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O10","O9", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+14;
            graph.connect(Departure.TransportType.TRAM,"O9","O8", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+10;
            graph.connect(Departure.TransportType.TRAM,"O8","O7", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O7","O6", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"O6","T6", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+8;
            graph.connect(Departure.TransportType.TRAM,"T6","O5", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+10;
            graph.connect(Departure.TransportType.TRAM,"O5","O4", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+14;
            graph.connect(Departure.TransportType.TRAM,"O4","O3", departure, arrival, "Circle line CW");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.TRAM,"O3","O2", departure, arrival, "Circle line CCW");
            departure = arrival;
            arrival = departure+7;
            graph.connect(Departure.TransportType.TRAM,"O2","O1", departure, arrival, "Circle line CCW");
            i = i+10;
        }

        //Add all Southward departures on BUS-line
        for(int i = 4; i < 1347;){
            int departure = i;
            int arrival = i+6;
            graph.connect(Departure.TransportType.BUS,"B1","B2", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"B2","B3", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"B3","O9", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"O9","B4", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+15;
            graph.connect(Departure.TransportType.BUS,"B4","C", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"C","B5", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"B5","O4", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"O4","B6", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"B6","B7", departure, arrival, "Bus line S");
            departure = arrival;
            arrival = departure+18;
            graph.connect(Departure.TransportType.BUS,"B7","B8", departure, arrival, "Bus line S");
            i = i+15;
        }
        //Add all Northward departures on BUS-line
        for(int i = 11; i < 1347;){
            int departure = i;
            int arrival = i+18;
            graph.connect(Departure.TransportType.BUS,"B8","B7", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"B7","B6", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"B6","O4", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"O4","B5", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"B5","C", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+15;
            graph.connect(Departure.TransportType.BUS,"C","B4", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"B4","O9", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"O9","B3", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+9;
            graph.connect(Departure.TransportType.BUS,"B3","B2", departure, arrival, "Bus line N");
            departure = arrival;
            arrival = departure+6;
            graph.connect(Departure.TransportType.BUS,"B2","B1", departure, arrival, "Bus line N");
            i = i+15;
        }
    }
    private void printPath(LinkedList<Departure> path){
        String currentLine = "";
        System.out.print(formatTime(path.get(1).getDepartureTime()) + " >> " +
                formatTime(path.getLast().getArrivalTime()) + "\n" +
                "Restid " + (path.getLast().getArrivalTime() - path.get(1).getDepartureTime() +
                " minuter\n" +
                path.getFirst().getNext() + " >> " + path.getLast().getNext()) + "\n");
        Iterator<Departure> iter = path.iterator();
        iter.next();
        while(iter.hasNext()){
            Departure d = iter.next();
            if (!currentLine.equals(d.getLine())){
                currentLine = d.getLine();
                System.out.print(currentLine + "\n " + formatTime(d.getDepartureTime()) + " " + d.getFrom() + "\n");
            }
            System.out.print(" " + formatTime(d.getArrivalTime()) + " " + d.getNext() + "\n");

        }
    }
}
