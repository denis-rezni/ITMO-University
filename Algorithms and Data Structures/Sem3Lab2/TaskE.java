import java.io.*;
import java.util.*;

public class TaskE {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final long inf = Long.MAX_VALUE;

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int start = in.nextInt() - 1;
        List<Edge> edges = new ArrayList<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long weight = in.nextLong();
            edges.add(new Edge(from, to, weight));
            graph.get(from).add(to);
        }


        long[][] dist = new long[n][n + 1];
        for (int i = 0; i < n; i++) {
            dist[i][0] = inf;
        }
        dist[start][0] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n; j++) {
                dist[j][i] = dist[j][i - 1];
            }
            for (Edge curEdge : edges) {
                int from = curEdge.from;
                int to = curEdge.to;
                long weight = curEdge.weight;
                if (dist[from][i - 1] < inf) {
                    if (dist[from][i - 1] + weight < dist[to][i]) {
                        dist[to][i] = dist[from][i - 1] + weight;
                    }
                }
            }
        }

        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (dist[i][n - 1] != dist[i][n]) {
                //on negative cycle
                dfs(i, visited, graph);
            }
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                System.out.println("-");
            } else if (dist[i][n - 1] == inf) {
                System.out.println("*");
            } else {
                System.out.println(dist[i][n - 1]);
            }
        }


    }

    void dfs (int v, boolean[] visited, List<List<Integer>> graph) {
        visited[v] = true;
        for (int u : graph.get(v)) {
            if (!visited[u]) {
                dfs(u, visited, graph);
            }
        }
    }


    class Edge {
        int from;
        int to;
        long weight;

        public Edge(int from, int to, long weight) {
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
        new TaskE().run();
    }
}