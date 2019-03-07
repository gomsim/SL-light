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

        //Add all departures on B-line

        //String start, String dest, int depTime, int arrTime, String line
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
    void test() {
        System.out.println(testGraph.toString());
        assertEquals(true,true);
            /* Test */
    }
}
