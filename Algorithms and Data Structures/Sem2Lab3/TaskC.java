import java.io.*;
import java.util.*;

public class TaskC {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() {
        int n = in.nextInt();
        int parents[] = new int[n];
        int costs[] = new int[n];
        List<List<Integer>> children = new ArrayList<>();
        for(int i = 0; i < n; i++){
            children.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            int parent = in.nextInt() - 1;
            parents[i] = parent;
            costs[i] = in.nextInt();
            children.get(parent).add(i);
        }

        int depth[] = new int[n];
        findDepth(0, depth, 0, children);

        int logn = log2(n);

        int dp[][] = new int[n][logn + 1];
        int dpMin[][] = new int[n][logn + 1];

        for (int v = 0; v < n; v++) {
            dp[v][0] = parents[v];
            dpMin[v][0] = costs[v];
        }

        for (int i = 1; i < logn + 1; i++) {
            for (int v = 0; v < n; v++) {
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
                dpMin[v][i] = Math.min(dpMin[v][i - 1], dpMin[dp[v][i - 1]][i - 1]);
            }
        }

//        for(int i = 0; i < logn; i++){
//            for(int v = 0; v < n; v++){
//                System.out.print(dp[v][i] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//        for(int i = 0; i < logn; i++){
//            for(int v = 0; v < n; v++){
//                System.out.print(dpMin[v][i] + " ");
//            }
//            System.out.println();
//        }


        int m = in.nextInt();
        for(int rq = 0; rq < m; rq++){
            int min = Integer.MAX_VALUE;
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
                    if(min > dpMin[u][i]){
                        min = dpMin[u][i];
                    }
                    u = dp[u][i];
                }
            }
            if(v == u){
                out.println(min);
                continue;
            }

            for(int i = logn; i >= 0; i--){
                if(dp[u][i]  != dp[v][i]){
                    if(min > dpMin[u][i]){
                        min = dpMin[u][i];
                    }
                    if(min > dpMin[v][i]){
                        min = dpMin[v][i];
                    }
                    u = dp[u][i];
                    v = dp[v][i];
                }
            }

            out.println(Math.min(Math.min(costs[u], costs[v]), min));
        }




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
                in = new FastScanner(new File("minonpath.in"));
                out = new PrintWriter(new File("minonpath.out"));
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
        new TaskC().run();
    }
}