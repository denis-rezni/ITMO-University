import java.io.*;
import java.util.*;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final int inf = 1_000_000_000;

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int start = in.nextInt() - 1;
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int weight = in.nextInt();
            edges.add(new Edge(from, to, weight));
        }


        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dist[i][0] = inf;
        }
        dist[start][0] = 0;

        //just k edges, not <= k
        for (int i = 1; i < k + 1; i++) {
            for (int j = 0; j < n; j++) {
                dist[j][i] = inf;
            }
            for (Edge curEdge : edges) {
                int from = curEdge.from;
                int to = curEdge.to;
                int weight = curEdge.weight;
                if (dist[from][i - 1] < inf) {
                    if (dist[from][i - 1] + weight < dist[to][i]) {
                        dist[to][i] = dist[from][i - 1] + weight;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i][k] < inf) {
                out.println(dist[i][k]);
            } else {
                out.println(-1);
            }
        }

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
        new TaskD().run();
    }
}