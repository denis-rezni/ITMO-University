import java.io.*;
import java.util.*;

public class TaskH {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int startPos = in.nextInt() - 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            graph.get(from).add(to);
        }
        boolean[] visited = new boolean[n];
        List<Integer> topSortList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                topSort(i, graph, topSortList, visited);
            }
        }
        boolean[] isWinning = new boolean[n];
        for (int v : topSortList) {
            //has route to losing = the vertex is winning
            for (int u : graph.get(v)) {
                if (!isWinning[u]) {
                    isWinning[v] = true;
                    break;
                }
            }
        }
        if (isWinning[startPos]) {
            out.println("First player wins");
        } else {
            out.println("Second player wins");
        }



    }

    void topSort(int v, List<List<Integer>> graph, List<Integer> res, boolean visited[]) {
        visited[v] = true;
        for (int u : graph.get(v)) {
            if (!visited[u]) {
                topSort(u, graph, res, visited);
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
                in = new FastScanner(new File("game.in"));
                out = new PrintWriter(new File("game.out"));
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
        new TaskH().run();
    }
}