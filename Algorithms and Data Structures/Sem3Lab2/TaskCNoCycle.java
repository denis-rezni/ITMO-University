import java.io.*;
import java.util.*;

public class TaskCNoCycle {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final int inf = 100000;

    public void solve() {
        int n = in.nextInt();
        int[][] table = new int[n][n];
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = in.nextInt();
                if (weight < inf) {
                    edges.add(new Edge(i, j, weight));
                }
            }
        }
        int m = edges.size();
        int[] parents = new int[n];
        int[] dist = new int[n];
//        for (int i = 1; i < n; i++) {
//            dist[i] = inf;
//        }
        int start = -1;//needed only on the last iteration
        for (int it = 0; it < n; it++) {
            //n iterations of Ford-Bellman
            start = -1;
            for (Edge edge : edges) {
                //looping through all edges in the table
//              if (dist[from] < inf) { // checking whether from is even available
                if (dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    parents[edge.to] = edge.from;
                    start = edge.to;
                }
//                    }
            }
        }
        if (start == -1) {
            out.println("NO");
            return;
        }
        out.println("YES");
        for (int i = 0; i < n; i++) {
            //getting to the cycle
            start = parents[start];
        }
        List<Integer> cycle = new ArrayList<>();
        HashSet<Integer> inCycle = new HashSet<>();
        int cur = start;
        while (!inCycle.contains(cur)) {
            inCycle.add(cur);
            cycle.add(cur + 1);
            cur = parents[cur];
        }
        out.println(cycle.size());
        for (int i = cycle.size() - 1; i >= 0; i--) {
            out.print(cycle.get(i) + " ");
        }
//        cycle.forEach(i -> out.print(i + " "));
    }


    class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("segments.in"));
                out = new PrintWriter(new File("segments.out"));
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
        new TaskCNoCycle().run();
    }
}