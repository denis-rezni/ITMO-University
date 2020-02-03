import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class TaskK {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() {
        int n = in.nextInt();
        int root = in.nextInt() - 1;
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        Map<Pair<Integer, Integer>, Integer> edgeIndexes = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            tree.get(from).add(to);
            tree.get(to).add(from);
            edgeIndexes.put(new Pair<>(Math.min(from, to), Math.max(from, to)), i + 1);
        }
        //graph filled
        int[] grandi = new int[n];
        dfs(root, tree, grandi, -1);
//        out.println(Arrays.toString(grandi));
        if (grandi[root] == 0) {
            out.println(2);
            return;
        } else {
            //first wins
            out.println(1);
            findEdge(root, -1, edgeIndexes, tree, grandi, 0);
        }
    }

    void findEdge(int v, int parent, Map<Pair<Integer, Integer>, Integer> edges,
                  List<List<Integer>> graph, int[] grandi, int neededXor) {
        int c = grandi[v];
        int d = neededXor;
        for (int u : graph.get(v)) {
            if (u != parent) {
                int b = grandi[u] + 1;
                if ((b ^ c ^ d) < b) {
                    //we need to make b -> b ^ c ^ d
                    int newNeededXor = (b ^ c ^ d) - 1;
                    if (newNeededXor == -1) {
                        //found the edge
                        Pair<Integer, Integer> edge = new Pair<>(Math.min(v, u), Math.max(v, u));
                        out.println(edges.get(edge));
                        break;
                    } else {
                        findEdge(u, v, edges, graph, grandi, newNeededXor);
                        break;
                    }
                }
            }
        }

    }

    int dfs(int v, List<List<Integer>> tree, int[] grandi, int parent) {
        int res = 0;
        for (int u : tree.get(v)) {
            if (u != parent) {
                res ^= dfs(u, tree, grandi, v) + 1;
            }
        }
        grandi[v] = res;
        return res;
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
        new TaskK().run();
    }
}