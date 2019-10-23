import java.io.*;
import java.util.*;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    List<Integer> parents;
    List<List<Integer>> children;

    public void solve() {
        int n = in.nextInt();
        parents = new ArrayList<>();
        children = new ArrayList<>();
        parents.add(0);
        children.add(new ArrayList<>());
        int dinoCounter = 1;

        List<Integer> depths = new ArrayList<>();
        depths.add(0);

//        List<List<Integer>> dp = new ArrayList<>();
//        dp.add(new ArrayList<>());
//        dp.get(0).add(0);
        int logn = log2(n);
        int dp[][] = new int[n][logn + 1];

//      int dp[][] = new int[n][logn + 1];
//        for (int i = 1; i < logn + 1; i++) {
//            for (int v = 0; v < n; v++) {
//                dp[v][i] = dp[dp[v][i - 1]][i - 1];
//            }
//        }

        List<Integer> bosses = new ArrayList<>();
        bosses.add(0);

        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            if (cmd.equals("+")) {
                int parent = in.nextInt() - 1;
                parents.add(parent);
                children.add(new ArrayList<>());
                children.get(parent).add(dinoCounter);
                depths.add(depths.get(parent) + 1);
                bosses.add(dinoCounter);
                recalc(dinoCounter, dp, logn);
                dinoCounter++;
            } else if (cmd.equals("-")) {
                setEaten(bosses, in.nextInt() - 1);
            } else {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int lca = lca(logn, u, v, dp, depths);
//                System.out.println("lca: " + lca);
                System.out.println(getBoss(lca, bosses) + 1);

            }
        }


    }

    int getBoss(int x, List<Integer> bosses) {
        if(x == bosses.get(x)){
            return x;
        }
        bosses.set(x, getBoss(bosses.get(x), bosses));
        return bosses.get(x);
//        while (bosses.get(x) != x) {
//            x = bosses.get(x);
//        }
//        return x;
    }

    void setEaten(List<Integer> bosses, int v) {
        bosses.set(v, parents.get(v));
    }

    int lca(int logn, int u, int v, int[][] dp, List<Integer> depths) {
        if(depths.get(v) > depths.get(u)){
            int temp = u;
            u = v;
            v = temp;
        }
        //now u is deeper
        int h = depths.get(u) - depths.get(v);
        for (int i = logn; i >= 0; i--) {
            if(1 << i <= h){
                h -= 1 << i;
                u = dp[u][i];
            }
        }
        if(v == u){
            return (v);
        }

        for(int i = logn; i >= 0; i--){
            if(dp[u][i]  != dp[v][i]){
                u = dp[u][i];
                v = dp[v][i];
            }
        }
        return parents.get(u);
    }

    void recalc(int dinoCounter, int[][] dp, int logn) {
        dp[dinoCounter][0] = parents.get(dinoCounter);
        for (int i = 1; i < logn + 1; i++) {
            dp[dinoCounter][i] = dp[dp[dinoCounter][i - 1]][i - 1];
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
        new TaskD().run();
    }
}