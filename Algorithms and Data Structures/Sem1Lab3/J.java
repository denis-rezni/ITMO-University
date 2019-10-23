import java.io.*;
import java.util.*;

public class J {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n;
    int m;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        out.print(countTiles());

    }

    private long countTiles() {
        long[][][] dp = new long[m + 1][n + 1][1 << (n + 1)];
        dp[0][0][0] = 1;
//        dp[0][0][1] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < 1 << (n + 1); x++) {
                    for (int c = 0; c < 2; c++) {
                        if (j == 0 || x >> (j - 1) != c || x >> j != c || x >> (j + 1) != c) {
                            int y = x;//y[j] = c
                            if(c == 0){
                                y &= ~(1 << j);
                            } else {
                                y |= (1 << j);
                            }
                            dp[i][j + 1][y] += dp[i][j][x];
                        }
                    }
                }
            }
            for (int x = 0; x < 1 << (n + 1); x++) {
                int y = x;//y[n] = 0
                y &= ~(1 << n);
                y = y << 1;
                dp[i + 1][0][y] += dp[i][n][x];
            }
        }
        System.out.println(Arrays.toString(dp[m][0]));
        return Arrays.stream(dp[m][0]).sum();

    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("input.txt"));
                out = new PrintWriter(new File("output.txt"));
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
        new J().run();
    }
}