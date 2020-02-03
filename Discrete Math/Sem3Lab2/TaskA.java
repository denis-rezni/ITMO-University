import java.io.*;
import java.util.*;

public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    private void solve() {
        int n = in.nextInt();
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tasks.add(new Task(in.nextInt(), in.nextInt()));
        }
        tasks.sort(Comparator.reverseOrder());
        TreeSet<Integer> times = new TreeSet<>();
        for (int i = 1; i < n + 1; i++) {
            times.add(i);
        }
        int[] timeGiven = new int[n];
        for (int i = 0; i < n; i++) {
            Task curTask = tasks.get(i);
            Integer timeTaken = times.floor(curTask.timeLimit);
            if (timeTaken == null) {
                timeGiven[i] = Integer.MAX_VALUE;
            } else {
                timeGiven[i] = timeTaken;
                times.remove(timeTaken);
            }
        }

//        System.out.println(Arrays.toString(timeGiven));
//        System.out.println(tasks);
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (timeGiven[i] > tasks.get(i).timeLimit) {
                res += tasks.get(i).penalty;
            }
        }
        out.println(res);

    }

    class Task implements Comparable<Task>{
        int timeLimit;
        int penalty;

        public Task(int timeLimit, int penalty) {
            this.timeLimit = timeLimit;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Task o) {
            if (penalty != o.penalty) {
                return Integer.compare(penalty, o.penalty);
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "timeLimit=" + timeLimit +
                    ", penalty=" + penalty +
                    '}';
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("schedule.in"));
                out = new PrintWriter(new File("schedule.out"));
            }
            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }

        String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

    }

    public static void main(String[] arg) {
        new TaskA().run();
    }
}