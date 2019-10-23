import java.io.*;
import java.util.*;

public class JSecond {
    private FastScanner in;
    private PrintWriter out;
    private boolean systemIO = false;

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        out.print(countTiles((n < m) ? n : m, (n < m) ? m : n));
    }

    private long countTiles(int n, int m) {
        //n < m
        long[][] dp = new long[m][1 << n];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < 1 << n; x++) {
                for (int y = 0; y < 1 << n; y++) {
                    if (isGoodPair(x, y, n)) {
                        dp[i + 1][y] += dp[i][x];
                    }
                }
            }
        }
        return Arrays.stream(dp[m - 1]).sum();
    }

    private boolean isGoodPair(int x, int y, int n) {
        for (int i = 0; i < n - 1; i++) {
            boolean bit1 = (x & (1 << i)) > 0;
            boolean bit2 = (x & (1 << (i + 1))) > 0;
            boolean bit3 = (y & (1 << i)) > 0;
            boolean bit4 = (y & (1 << (i + 1))) > 0;
            if (bit1 == bit2 && bit2 == bit3 && bit3 == bit4) {
                return false;
            }
        }
        return true;
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
        new JSecond().run();
    }
}