import java.io.*;
import java.util.*;

public class TaskC {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    private void solve() {
        int n = in.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            graph[i] = new int[m];
            for (int j = 0; j < m; j++) {
                graph[i][j] = in.nextInt() - 1;
            }
        }
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            order.add(i);
        }
        order.sort(Comparator.comparingInt(o -> weights[(int) o]).reversed());
        int[] secondPart = new int[n];
        Arrays.fill(secondPart, -1);
        for (int i = 0; i < n; i++) {
            int v = order.get(i);
            boolean[] used = new boolean[n];
            kuhn(v, used, graph, secondPart);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int fromFirst = secondPart[i];
            if (fromFirst != -1) {
                res[fromFirst] = i + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(res[i] + " ");
        }


    }

    boolean kuhn(int v, boolean[] used, int[][] graph, int[] secondPart) {
        if (used[v]) {
            return false;
        }
        used[v] = true;
        for (int to : graph[v]) {
            if (secondPart[to] == -1 || kuhn(secondPart[to], used, graph, secondPart)) {
                secondPart[to] = v;
                return true;
            }

        }
        return false;
    }
    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("matching.in"));
                out = new PrintWriter(new File("matching.out"));
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