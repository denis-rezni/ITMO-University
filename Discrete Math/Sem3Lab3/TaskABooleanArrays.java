import java.io.*;
import java.util.*;

public class TaskABooleanArrays {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), i);
        }

        boolean[] resultEdges = new boolean[m];
        while (true) {
            //build the graph
            List<List<Integer>> graphOut = new ArrayList<>();
            buildGraph(edges, graphOut, resultEdges, m, n);
            boolean[] findingPathFrom = findFindingPathFrom(n, edges, resultEdges, m);
            boolean[] findingPathTo = findFindingPathTo(edges, resultEdges, m);
            boolean[] shortestPath = shortestPath(findingPathFrom, findingPathTo, graphOut, m);
            if (shortestPath.length == 0) {
                //gg
                break;
            } else {
                resultEdges = xor(resultEdges, shortestPath, m);
            }
        }
        int counter = 0;
        for (int i = 0; i < m; i++) {
            if (resultEdges[i]) {
                counter++;
            }
        }
        out.println(counter);
        for (int i = 0; i < m; i++) {
            if (resultEdges[i]) {
                out.print((i + 1) + " ");
            }
        }
    }

    boolean[] xor(boolean[] set1, boolean[] set2, int m) {
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            res[i] = set1[i] ^ set2[i];
        }
        return res;
    }

    boolean[] shortestPath(boolean[] fromSet, boolean[] toSet, List<List<Integer>> graphOut, int n) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (fromSet[i]) {
                queue.addLast(i);
            }
        }

        boolean[] visited = new boolean[n];
        int end = -1;

        loop:
        while (!queue.isEmpty()) {
            int v = queue.pollFirst();
            if (toSet[v]) {
                end = v;
                break;
            }
            for (int u : graphOut.get(v)) {
                if (!visited[u]) {
                    visited[u] = true;
                    parents[u] = v;
                    if (toSet[u]) {
                        end = u;
                        break loop;
                    } else {
                        queue.addLast(u);
                    }
                }
            }
        }

        if (end == -1) {
            //gg
            return new boolean[0];
        } else {
            boolean[] res = new boolean[n];
            while (!fromSet[end]) {
                res[end] = true;
                end = parents[end];
            }
            res[end] = true;
            return res;
        }
    }

    boolean[] findFindingPathTo(Edge[] edges, boolean[] resultEdges, int m) {
        boolean[] colors = new boolean[101];
        for (int i = 0; i < resultEdges.length; i++) {
            if (resultEdges[i]) {
                colors[edges[i].color] = true;
            }
        }
        boolean[] res = new boolean[m];
        for (Edge toAddToRes : edges) {
            if (!resultEdges[toAddToRes.index] && !colors[toAddToRes.color]) {
                res[toAddToRes.index] = true;
            }
        }
        return res;
    }

    boolean[] findFindingPathFrom(int n, Edge[] edges, boolean[] resultEdges, int m) {
        DSU checkingCyclesDSU = new DSU(n);
        for (int i = 0; i < m; i++) {
            if (resultEdges[i]) {
                checkingCyclesDSU.unite(edges[i].from, edges[i].to);
            }
        }
        boolean[] res = new boolean[m];
        for (Edge toAddToRes : edges) {
            if (!resultEdges[toAddToRes.index]) {
                if (checkingCyclesDSU.findParent(toAddToRes.from)
                        != checkingCyclesDSU.findParent(toAddToRes.to)) {
                    res[toAddToRes.index] = true;
                }
            }
        }
        return res;
    }

    void buildGraph(Edge[] edges, List<List<Integer>> graphOut, boolean[] resultEdges, int m, int n) {
        for (int i = 0; i < m; i++) {
            graphOut.add(new ArrayList<>());
        }

        List<Edge> resultEdgesList = new ArrayList<>();
        List<Edge> notResultEdgesList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (resultEdges[i]) {
                resultEdgesList.add(edges[i]);
            } else {
                notResultEdgesList.add(edges[i]);
            }
        }

        for (Edge edge : resultEdgesList) {
            DSU dsu = new DSU(n);
            //building dsu with these result edges
            for (Edge toAdd : resultEdgesList) {
                if (!toAdd.equals(edge)) {
                    //adding it to the DSU
                    dsu.unite(toAdd.from, toAdd.to);
                }
            }
            //now this dsu can check cycles, bc it represents components
            for (Edge edgeTo : notResultEdgesList) {
                if (dsu.findParent(edgeTo.from) != dsu.findParent(edgeTo.to)) {
                    //no cycle
                    //adding the edge: edge -> edgeTo
                    graphOut.get(edge.index).add(edgeTo.index);
                }
            }
            //filled edges from first matroid
            //now elaborating on the second one

            //filling in all the colors, except of the edge's one
            boolean[] colors = new boolean[101];
            for (Edge toAdd : resultEdgesList) {
                if (!toAdd.equals(edge)) {
                    colors[toAdd.color] = true;
                }
            }

            //looping over second part
            for (Edge edgeFrom : notResultEdgesList) {
                if (!colors[edgeFrom.color]) {
                    //adding the edge: edgeFrom -> edge
                    graphOut.get(edgeFrom.index).add(edge.index);
                }
            }

            //fiiled the graph
        }
    }

    static class Edge {
        int to;
        int from;
        int color;
        int index;

        public Edge(int to, int from, int color, int index) {
            this.to = to;
            this.from = from;
            this.color = color;
            this.index = index;
        }

        @Override
        public String toString() {
            return "from " + from + " to " + to + " index: " + index;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return to == edge.to &&
                    from == edge.from &&
                    color == edge.color &&
                    index == edge.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }


    static class DSU {
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
                in = new FastScanner(new File("rainbow.in"));
                out = new PrintWriter(new File("rainbow.out"));
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
        new TaskABooleanArrays().run();
    }
}