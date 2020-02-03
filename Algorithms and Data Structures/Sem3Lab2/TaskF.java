import java.io.*;
import java.util.*;

public class TaskF {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final long inf = Long.MAX_VALUE / 2;

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            int weight = in.nextInt();
            graph.get(from).add(new Pair(to, weight));
            graph.get(to).add(new Pair(from, weight));
        }
        int a = in.nextInt() - 1;
        int b = in.nextInt() - 1;
        int c = in.nextInt() - 1;
        long[] distFromA = distToAll(a, n, graph);
        long[] distFromB = distToAll(b, n, graph);
        long[] distFromC = distToAll(c, n, graph);
        long res = Math.min(
                Math.min(
                        Math.min(distFromA[b] + distFromB[c], distFromA[c] + distFromC[b])
                        , distFromB[c] + distFromC[a]
                ),
                Math.min(
                        Math.min(distFromB[a] + distFromA[c], distFromC[a] + distFromA[b])
                        , distFromC[b] + distFromB[a]
                )
        );
        if (res >= inf) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    long[] distToAll(int start, int n, List<List<Pair>> graph) {
        long[] dist = new long[n];
        for (int i = 0; i < n; i++) {
            dist[i] = (start == i) ? 0 : inf;
        }
        TreeSet<Integer> queue = new TreeSet<>((o1, o2) -> {
            if (dist[o1] > dist[o2]) {
                return 1;
            } else if (dist[o1] < dist[o2]) {
                return -1;
            } else {
                return Integer.compare(o1, o2);
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        for (int step = 0; step < n; step++) {
            int v = queue.pollFirst();
            for (int i = 0; i < graph.get(v).size(); i++) {
                Pair edge = graph.get(v).get(i);
                if (dist[v] + edge.weight < dist[edge.to]) {
                    //relaxation
                    queue.remove(edge.to);
                    dist[edge.to] = dist[v] + edge.weight;
                    queue.add(edge.to);
                }
            }
        }
        return dist;
    }


    class Pair {
        int to;
        int weight;

        public Pair(int to, int weight) {
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
        new TaskF().run();
    }
}