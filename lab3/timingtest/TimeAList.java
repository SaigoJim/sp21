package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    private static void testAList(int N) {
       AList<Integer> L = new AList<>();
       for(int i = 0; i < N; i += 1) {
           L.addLast(i);
       }
    }
    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        // Construct 3 data
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int N = 1000;
        for (int i = 0; i < 8; i += 1) {
            Ns.addLast(N);
            opCounts.addLast(N);
            Stopwatch sw = new Stopwatch();
            testAList(N);
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
            N = N * 2;
        }
        printTimingTable(Ns, times, opCounts);
    }
}
