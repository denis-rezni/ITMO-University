import java.io.*;
import java.util.*;

public class I {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    boolean[][] covered;
    int n;
    int m;

    public void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();

        covered = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            for (int j = 0; j < m; j++) {
                if (line.charAt(j) == 'X') {
                    covered[i][j] = true;
                }
            }
        }
        out.print(countTiles());

    }


    private long countTiles() {
        long dp[][][] = new long[m + 1][n + 1][1 << n];
//        Arrays.fill(dp[0][0], 1);
//        dp[0][0][0] = 1;

//        dp[0][0][7] = 1;
//        dp[0][0][nec0] = 1;
//        if(nec0 == 0){
//            dp[0][0][0] = 1;
//        } else {
//            for (int x = 1; x < 1 << n; x++) {
//                if ((nec0 | x) == nec0) {
//                    dp[0][0][x] = 1;
//                }
//            }
//        }
//        int startI = 0;
//        int startJ = 0;
//        loop:
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (covered[j][i]) {
//                    startJ++;
//                } else {
//                    break loop;
//                }
//            }
//            if (startJ == n) {
//                startI++;
//                startJ = 0;
//            }
//        }
//        int nec0 = necessary(startI, startJ);
//        System.out.println(nec0);
//        System.out.println("startI: " + startI + " startJ: " + startJ);
//        dp[startI][startJ][0] = 1;

//        for (int x = 0; x < 1 << n; x++) {
//            if ((nec0 | x) == nec0) {
//                dp[startI][startJ][x] = 1;
//            }
//        }
        int nec0 = necessary(0, 0);
        dp[0][0][nec0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int nec = necessary(i, j);
                for (int x = 0; x < 1 << n; x++) {
                    if ((nec & x) >= nec) {
                        if ((x & (1 << j)) > 0) {
                            int y = x - (1 << j);
                            dp[i][j + 1][y | necessary(i, j + 1)] += dp[i][j][x];
                        } else {
                            int y = x + (1 << j);
                            if (i == m - 1 || (i < m - 1 && !covered[j][i + 1])) {
                                dp[i][j + 1][y | necessary(i, j + 1)] += dp[i][j][x];
                            }
                            if (j != n - 1 && ((x & (1 << (j + 1))) == 0)) {
                                y = x + (1 << (j + 1));
                                dp[i][j + 1][y | necessary(i, j + 1)] += dp[i][j][x];
                            }
                        }
                    }
                }
//                System.out.println("i: " + i + " j: " + j + " " + Arrays.stream(dp[i][j]).sum());
                for (int x = 0; x < 1 << n; x++) {
//                    assert dp[i][n][x] == 0;
                    dp[i + 1][0][x] = dp[i][n][x];
                }
//                System.out.println(Arrays.stream(dp[i][j]).sum());
            }
        }
//        System.out.println(Arrays.toString(dp[m][0]));
        return dp[m][0][0];
    }

    private int necessary(int i, int j) {
        int nec = 0;
        if (i != m - 1) {
            for (int j1 = 0; j1 < j; j1++) {
                if (covered[j1][i + 1]) {
                    nec += 1 << j1;
                }
            }
        }
        for (int j1 = j; j1 < n; j1++) {
            if (covered[j1][i]) {
                nec += 1 << j1;

            }
        }
        return nec;
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
        new I().run();
    }
}