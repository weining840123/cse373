package heap;

import org.junit.Test;
import edu.princeton.cs.algs4.Stopwatch;
import static org.junit.Assert.*;
import java.util.Random;

public class ArrayHeapMinPQTest {
    /* Be sure to write randomized tests that can handle millions of items. To
     * test for runtime, compare the runtime of NaiveMinPQ vs ArrayHeapMinPQ on
     * a large input of millions of items. */

    @Test
    public void simpleTest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("apple", 10);
        pq.add("banana", 2);
        assertTrue(pq.contains("apple"));
        assertFalse(pq.contains("ba"));
        pq.changePriority("apple", 1);
        pq.add("p1", 0);
        pq.add("p2", 0);
        pq.add("p3", 0);
        pq.add("p4", 0);
        System.out.println(pq.size());
        for (int i = 0; i < 6; i++) {
            System.out.println(pq.removeSmallest());
            System.out.println(pq.size());
        }
    }

    @Test
    public void testInsertRemove() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        pq.add("p1", 1);
        pq.add("p2", 2);
        pq.add("p3", 3);
        // pq.removeSmallest();
        pq.add("p4", -1);
        pq.changePriority("p1", 0);
        pq.changePriority("p2", -2);


        System.out.println(pq.size());
        pq.removeSmallest();
        pq.add("p5", 7);
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            System.out.println(pq.removeSmallest());
        }
    }
    

    @Test
    public void randomizedTest() {
        int seed = 373;
        // long start = System.currentTimeMillis();
        Random random = new Random(seed);

        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            double pri = random.nextInt(500);
            pq.add(String.valueOf(i), i);

        }
        for (int i = 0; i < 100; i++) {
            System.out.println(pq.removeSmallest());
        }

        // long end = System.currentTimeMillis();
        // System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        /*
        Stopwatch sw = new Stopwatch();
        int seed1 = 373;
        Random random2 = new Random(seed1);

        ArrayHeapMinPQ<String> pq2 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10000000; i++) {
            double pri = random2.nextInt(500);
            pq2.add(String.valueOf(i), pri);

        }

        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
        */

    }

    @Test
    public void randomizedRemoveAfterChangePriority() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            pq.add("p" + i, i);
            System.out.println(i + ": " + pq.getSmallest());
        }

        pq.changePriority("p19", -1);
        pq.changePriority("p7", 9);
        pq.changePriority("p1", 4);
        pq.changePriority("p8", -5);
        System.out.println("-----------------------------");
        for (int i = 0; i < 20; i++) {
            System.out.println(i + ": " + pq.removeSmallest());
        }

    }
}
