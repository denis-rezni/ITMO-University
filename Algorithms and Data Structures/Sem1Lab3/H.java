import java.io.*;
import java.util.*;

public class H {
    private FastScanner in;
    private PrintWriter out;
    private boolean systemIO = true;

    private void solve() throws IOException {
        int n = in.nextInt();
        int ways[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ways[i][j] = in.nextInt();
            }
        }
        Pair dp[][] = new Pair[n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][(1 << i) | (1 << j)].minCost = ways[i][j];
            }
        }


        for (int i = 3; i <= n; i++) {//number of ones
            for (int ver = 0; ver < n; ver++) {
                for (int mask = 0; mask < 1 << n; mask++) {
                    if (Integer.bitCount(mask) == i && (mask & (1 << ver)) > 0) {
                        int min = Integer.MAX_VALUE;
                        int minPrev = n + 1;
                        for (int k = 0; k < n; k++) {
                            if (((mask & (1 << k)) > 0) && k != ver) {
                                if (dp[k][mask - (1 << ver)].minCost + ways[k][ver] < min) {
                                    min = dp[k][mask - (1 << ver)].minCost + ways[k][ver];
                                    minPrev = k;
                                }
                            }
                        }
                        dp[ver][mask].minCost = min;
                        dp[ver][mask].prev = minPrev;
                    }
                }
            }
        }
        Pair minPair = dp[0][(1 << n) - 1];
        int minIndex = 0;
        for (int i = 1; i < n; i++) {
            if (minPair.minCost > dp[i][(1 << n) - 1].minCost) {
                minPair = dp[i][(1 << n) - 1];
                minIndex = i;
            }
        }
        int counter = (1 << n) - 1 - (1 << minIndex);
        out.println(minPair.minCost);
        out.print(minIndex + 1 + " ");
        if (counter <= 0) {
            return;
        }
        Pair cur = minPair;
        while (true) {
            if (Integer.bitCount(counter) == 1) {
                out.print(Integer.toBinaryString(counter).length());
                break;
            }
            out.print(cur.prev + 1 + " ");
            counter -= 1 << cur.prev;
            cur = dp[cur.prev][counter + (1 << cur.prev)];
        }

    }

    private class Pair {
        int minCost;
        int prev;

        public Pair(int minCost, int prev) {
            this.minCost = minCost;
            this.prev = prev;
        }
    }

    private void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nice.in"));
                out = new PrintWriter(new File("nice.out"));
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
        new H().run();
    }
}
