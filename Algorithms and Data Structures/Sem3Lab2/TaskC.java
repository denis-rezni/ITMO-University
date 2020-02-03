import java.io.*;
import java.util.*;

public class TaskC {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final int inf = Integer.MAX_VALUE / 2;

    private void solve() {
        int n = in.nextInt();
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int weight = in.nextInt();
                if (i == j && weight < 0) {
                    System.out.println("YES");
                    System.out.println(1);
                    System.out.println(i + 1);
                    return;
                } else if (i != j) {
                    edges.add(new Edge(i, j, weight));
                }
            }
        }
        int m = edges.size();
        int[][] dp = new int[n][n + 1];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        parent[0] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = inf;
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }


        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n; j++) {
                dp[j][i] = dp[j][i - 1];
            }
            for (Edge curEdge : edges) {
                int from = curEdge.from;
                int to = curEdge.to;
                int weight = curEdge.weight;
//                dp[to][i] = dp[to][i - 1];
                if (dp[from][i - 1] < inf) {
                    if (dp[from][i - 1] + weight < dp[to][i]) {
                        parent[to] = from;
                        dp[to][i] = dp[from][i - 1] + weight;
//                        System.out.println("NEW: " + (dp[from][i - 1] + weight));
                    }
                }
            }

//            System.out.println();
//            for (int h = 0; h < n; h++) {
//                System.out.println(Arrays.toString(dp[h]));
//            }
//
//            System.out.println();


        }
        //is there a cycle
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
//        System.out.println();
//        System.out.println(Arrays.toString(parent));
        boolean hasCycle = false;
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i][n - 1] != dp[i][n]) {
                hasCycle = true;
                start = i;
            }
        }


        if (hasCycle) {
            out.println("YES");
            List<Integer> cycle = cycle(parent, n, start);
            out.println(cycle.size());
            cycle.stream().map(i -> i + 1).forEach(i -> out.print(i + " "));
            return;
        }

        out.println("NO");
    }

    // -1 -1 4 -1 2 -1 -1
    List<Integer> cycle(int[] parent, int n, int start) {
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        int cur = start;
        while (!set.contains(cur)) {
            set.add(cur);
            list.add(cur);
            cur = parent[cur];
        }
        return list;
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
        new TaskC().run();
    }
}