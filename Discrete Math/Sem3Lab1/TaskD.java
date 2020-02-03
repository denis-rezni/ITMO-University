import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() {
        int n = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                boolean fromIToJ = line.charAt(j) == '1';
                if (fromIToJ) {
                    graph.get(i).add(j);
                } else {
                    graph.get(j).add(i);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            //topSort from i
            boolean[] visited = new boolean[n];
            List<Integer> topSort = new ArrayList<>();
            dfs(i, visited, topSort, graph);
            if (graph.get(topSort.get(0)).contains(topSort.get(topSort.size() - 1))) {
                Collections.reverse(topSort);
                topSort.stream().map(j -> j + 1).forEach(j -> out.print(j + " "));
                return;
            }
        }
    }

    void dfs(int v, boolean visited[], List<Integer> res, List<List<Integer>> graph) {
        visited[v] = true;
        for (int u : graph.get(v)) {
            if (!visited[u]) {
                dfs(u, visited, res, graph);
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
                in = new FastScanner(new File("guyaury.in"));
                out = new PrintWriter(new File("guyaury.out"));
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