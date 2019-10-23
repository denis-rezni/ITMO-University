import java.io.*;
import java.util.*;

public class TaskB {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() {
        int n = in.nextInt();
        int parents[] = new int[n];
        List<List<Integer>> children = new ArrayList<>();
        for(int i = 0; i < n; i++){
            children.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int parent = in.nextInt() - 1;
            parents[i] = parent;
            children.get(parent).add(i);
        }

        int depth[] = new int[n];
        findDepth(0, depth, 0, children);

        int logn = log2(n);

        int dp[][] = new int[n][logn + 1];

        for (int v = 0; v < n; v++) {
            dp[v][0] = parents[v];
        }

        for (int i = 1; i < logn + 1; i++) {
            for (int v = 0; v < n; v++) {
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
            }
        }

        int m = in.nextInt();
        for(int rq = 0; rq < m; rq++){
            int v = in.nextInt() - 1;
            int u = in.nextInt() - 1;
            if(depth[v] > depth[u]){
                int temp = u;
                u = v;
                v = temp;
            }
            //now u is deeper
            int h = depth[u] - depth[v];
            for (int i = logn; i >= 0; i--) {
                if(1 << i <= h){
                    h -= 1 << i;
                    u = dp[u][i];
                }
            }
            if(v == u){
                out.println(v + 1);
                continue;
            }

            for(int i = logn; i >= 0; i--){
                if(dp[u][i]  != dp[v][i]){
                    u = dp[u][i];
                    v = dp[v][i];
                }
            }
            out.println(parents[u] + 1);
        }


//        for(int i = 0; i < logn; i++){
//            for(int v = 0; v < n; v++){
//                System.out.print(dp[v][i] + 1 + " ");
//            }
//            System.out.println();
//        }


    }

    int log2(int x) {
        int res = 1;
        int counter = 0;
        while (res < x) {
            res *= 2;
            counter++;
        }
        return counter;
    }

    void findDepth(int v, int [] depth, int level, List<List<Integer>> children){
        depth[v] = level;
        for(int i = 0; i < children.get(v).size(); i++){
            findDepth(children.get(v).get(i), depth, level + 1, children);
        }
    }


    void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("skyscraper.in"));
                out = new PrintWriter(new File("skyscraper.out"));
            }
            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class FastScanner {
        public static BufferedReader br;
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
        new TaskB().run();
    }
}