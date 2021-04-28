package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeL, sizeB);
                System.out.println("sizeL: " + sizeL);
                System.out.println("sizeB: " + sizeB);
            } else if (L.size() != 0 && operationNumber == 2) {
                int lastL = L.removeLast();
                int lastB = B.removeLast();
                assertEquals(lastL,lastB);
                System.out.println("removed lastL: " + lastL);
                System.out.println("removed lastB: " + lastB);
            }
        }
    }

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> noR = new AListNoResizing<>();
        BuggyAList<Integer> b = new BuggyAList<>();

        noR.addLast(10);
        noR.addLast(20);
        noR.addLast(30);
        b.addLast(10);
        b.addLast(20);
        b.addLast(30);
        assertEquals(noR.size(), b.size());
        assertEquals(noR.removeLast(), b.removeLast());
        assertEquals(noR.removeLast(), b.removeLast());
        assertEquals(noR.removeLast(), b.removeLast());
    }
}
