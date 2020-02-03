import java.io.*;
import java.util.*;

public class TaskJ {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final long inf = Long.MAX_VALUE / 2;

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            graph.get(in.nextInt() - 1).add(in.nextInt() - 1);
        }
        boolean visited[] = new boolean[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topSortDfs(i, visited, graph, res);
            }
        }
        int[] grandi = new int[n];
        for (int v : res) {
            grandi[v] = findGrandi(v, graph, grandi);
        }
        Arrays.stream(grandi).forEach(System.out::println);
    }

    int findGrandi(int v, List<List<Integer>> graph, int[] grandi) {
        List<Integer> list = graph.get(v);
        list.sort(Comparator.comparingInt(o -> grandi[o]));
        int smallest = 0;
        int pointer = 0;
        while (pointer < list.size()) {
            while (pointer + 1 < list.size() && (grandi[list.get(pointer)] == grandi[list.get(pointer + 1)])) {
                pointer++;
            }
            if (smallest == grandi[list.get(pointer)]) {
                smallest++;
            } else {
                return smallest;
            }
            pointer++;
        }
        return smallest;
    }

    void topSortDfs(int v, boolean[] visited, List<List<Integer>> graph, List<Integer> res) {
        visited[v] = true;
        for (int u : graph.get(v)) {
            if (!visited[u]) {
                topSortDfs(u, visited, graph, res);
            }
        }
        res.add(v);
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
        new TaskJ().run();
    }
}