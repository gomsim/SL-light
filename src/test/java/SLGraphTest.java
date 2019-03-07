import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SLGraphTest {
    SLGraph testGraph = new SLGraph();
    @BeforeEach
    void setUp() {

        //Add stops on B-line
        testGraph.addStop("B1",2,7);
        testGraph.addStop("B2",6,7);
        testGraph.addStop("B3",11,7);
        testGraph.addStop("B4",26,7);
        testGraph.addStop("B5",38,9);

        //Add stops on T-line
        testGraph.addStop("T1",10,1);
        testGraph.addStop("T2",15,3);
        testGraph.addStop("T3",20,5);
        testGraph.addStop("T4",22,10);
        testGraph.addStop("T5",22,14);
        testGraph.addStop("T6",25,17);
        testGraph.addStop("T7",24,22);

        //Add stop where T and B cross
        testGraph.addStop("X1",22,7);

        //Add all departures on T-Line
        //Add all Southgoing departures on T-Line
        for(int i = 12; i < 1396;){
            int departure = i;
            int arrival = i+3;
            testGraph.connect("T1","T2", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+8;
            testGraph.connect("T2","T3", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+1;
            testGraph.connect("T3","X1", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+7;
            testGraph.connect("X1","T4", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+11;
            testGraph.connect("T4","T5", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+3;
            testGraph.connect("T5","T6", departure, arrival, "Trainline S");
            departure = arrival;
            arrival = departure+12;
            testGraph.connect("T6","T7", departure, arrival, "Trainline S");
            i = i+6;
        }

        //Add all Northgoing departures on T-Line
        for(int i = 6; i < 1396;){
            int departure = i;
            int arrival = i+12;
            testGraph.connect("T7","T6", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+3;
            testGraph.connect("T6","T5", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+11;
            testGraph.connect("T5","T4", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+6;
            testGraph.connect("T4","X1", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+1;
            testGraph.connect("X1","T3", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+10;
            testGraph.connect("T3","T2", departure, arrival, "Trainline N");
            departure = arrival;
            arrival = departure+3;
            testGraph.connect("T2","T1", departure, arrival, "Trainline N");
            i = i+6;
        }
        //Add all departures on B-line
        //Add all Eastgoing departures on B-line
        for(int i = 10; i < 1393; ){
            int departure = i;
            int arrival = i+4;
            testGraph.connect("B1","B2", departure, arrival, "BussLine E");
            departure = arrival;
            arrival = departure+6;
            testGraph.connect("B2","B3", departure, arrival, "BussLine E");
            departure = arrival;
            arrival = departure+12;
            testGraph.connect("B3","X1", departure, arrival, "BussLine E");
            departure = arrival;
            arrival = departure+9;
            testGraph.connect("X1","B4", departure, arrival, "BussLine E");
            departure = arrival;
            arrival = departure+17;
            testGraph.connect("B4","B5", departure, arrival, "BussLine E");
            i = i+10;
        }
        //Add all Westgoing departures on B-line
        for(int i = 0; i < 1393; ){
            int departure = i;
            int arrival = i+4;
            testGraph.connect("B5","B4", departure, arrival, "BussLine W");
            departure = arrival;
            arrival = departure+6;
            testGraph.connect("B4","X1", departure, arrival, "BussLine W");
            departure = arrival;
            arrival = departure+12;
            testGraph.connect("X1","B3", departure, arrival, "BussLine W");
            departure = arrival;
            arrival = departure+9;
            testGraph.connect("B3","B2", departure, arrival, "BussLine W");
            departure = arrival;
            arrival = departure+17;
            testGraph.connect("B2","B1", departure, arrival, "BussLine W");
            i = i+10;
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindRoute() {
        testGraph.connect("B3", "T4", (7*60)+11, (7*60)+11+19, "TestLine");
        System.out.println(testGraph.findRoute("B1", "T7", 60*7));

        System.out.println(testGraph.findRoute("B1", "B5", 0));
        assertEquals(true,false);
            /* Test */
    }
}
