import java.io.*;
import java.util.*;

public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() {
        int n = in.nextInt();
        int parents[] = new int[n];
        int root = 0;
        for (int i = 0; i < n; i++) {
            int parent = in.nextInt() - 1;
            if (parent == -1) {
                parents[i] = i;
                root = i;
            } else {
                parents[i] = parent;
            }
        }

        int logn = log2(n);

        int dp[][] = new int[n][logn];

        for(int v = 0; v < n; v++){
            dp[v][0] = parents[v];
        }


        for (int i = 1; i < logn; i++) {
            for (int v = 0; v < n; v++) {
                if(dp[v][i - 1] == -1){
                    dp[v][i] = -1;
                    continue;
                }
                dp[v][i] = dp[dp[v][i - 1]][i - 1];
                if (dp[v][i] == dp[v][i - 1] || dp[v][i - 1] == -1) {
                    dp[v][i] = -1;
                }
            }
        }


//        for(int i = 0; i < logn; i++){
//            for(int v = 0; v < n; v++){
//                System.out.print(dp[v][i] + 1 + " ");
//            }
//            System.out.println();
//        }

        for (int v = 0; v < n; v++) {
            out.print((v + 1) + ": ");
            for (int i = 0; i < logn; i++) {
                if(v != root){
                    if (dp[v][i] != -1) {
                        out.print((dp[v][i] + 1) + " ");
                    } else {
                        break;
                    }
                }
            }
            out.println();
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
        new TaskA().run();
    }
}