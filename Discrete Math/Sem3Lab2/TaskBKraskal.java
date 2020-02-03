import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskBKraskal {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        long s = in.nextLong();

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long weight = -in.nextLong();
            edges.add(new Edge(weight, from, to, i));
        }

        edges.sort(Comparator.comparingLong(o -> o.weight));
//        System.out.println(edges.stream().map(i -> i.number).collect(Collectors.toList()));
        DSU dsu = new DSU(n);
        Set<Integer> takenEdges = new HashSet<>();
        for (Edge edge : edges) {
            if (dsu.findParent(edge.from) != dsu.findParent(edge.to)) {
                //take the edge
                takenEdges.add(edge.number);
                dsu.unite(edge.from, edge.to);
            }
        }

//        System.out.println(takenEdges);
        long curSum = 0;
        List<Integer> resultEdges = new ArrayList<>();
        for (int i = m - 1; i >= 0; i--) {
            Edge edge = edges.get(i);
            if (curSum - edge.weight <= s && !takenEdges.contains(edge.number)) {
                //take it
                resultEdges.add(edge.number);
                curSum -= edge.weight;
            }
        }

        out.println(resultEdges.size());
        resultEdges.stream().sorted().map(i -> i + 1).forEach(i -> out.print(i + " "));

    }

    class Edge {
        long weight;
        int from;
        int to;
        int number;

        public Edge(long weight, int from, int to, int number) {
            this.weight = weight;
            this.from = from;
            this.to = to;
            this.number = number;
        }
    }


    class DSU {
        private int[] parent;
        private int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int findParent(int v) {
            if (v == parent[v]) {
                return v;
            }
            parent[v] = findParent(parent[v]);
            return parent[v];
        }

        void unite(int v, int u) {
            v = findParent(v);
            u = findParent(u);
            if (u != v) {
                if (rank[v] < rank[u]) {
                    int t = u;
                    u = v;
                    v = t;
                }
                parent[u] = v;
                if (rank[u] == rank[v]) {
                    rank[v]++;
                }
            }
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("destroy.in"));
                out = new PrintWriter(new File("destroy.out"));
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
        new TaskBKraskal().run();
    }
}