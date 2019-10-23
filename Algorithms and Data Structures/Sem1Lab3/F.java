import java.io.*;
import java.util.*;

public class F {
    private FastScanner in;
    private PrintWriter out;
    private boolean systemIO = true;

    private void solve() throws IOException {
        int n = in.nextInt();
        if (n == 0) {
            out.println(0);
            out.print("0 0");
            return;
        }
        if (n == 1) {
            int cost = in.nextInt();
            out.println(cost);
            if (cost > 100) {
                out.print("1 0");
            } else {
                out.print("0 0");
            }
            return;
        }
        int days[] = new int[n];
        for (int i = 0; i < n; i++) {
            days[i] = in.nextInt();
        }


        int[][] dp = new int[n + 1][n];
        Arrays.fill(dp[0], Integer.MAX_VALUE / 2);
        dp[0][0] = 0;
        //dp[i][j] = i days passed, j coupons left
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (days[i - 1] > 100) {
                    if (j == 0) {
                        dp[i][j] = dp[i - 1][j + 1];
                    } else if (j == n - 1) {
                        dp[i][j] = dp[i - 1][j - 1] + days[i - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + days[i - 1], dp[i - 1][j + 1]);
                    }

                } else {
                    if (j == n - 1) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] + days[i - 1], dp[i - 1][j + 1]);
                    }
                }
            }
        }
//        for (int i = 0; i < n + 1; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        int minIndex = 0;

        for (int i = 0; i < n; i++) {
            if (dp[n][minIndex] >= dp[n][i]) {
                minIndex = i;
            }
        }
        out.println(dp[n][minIndex]);
        out.print(minIndex + " ");
        List<Integer> coupDays = new ArrayList<>();
        int cntI = n;
        int cntJ = minIndex;
        while (cntI != 0) {
            if (days[cntI - 1] > 100) {
                if (cntJ == 0) {
                    coupDays.add(cntI - 1);
                    cntJ++;
                } else {
                    if (cntJ == n - 1) {
                        cntJ--;
                    } else {
                        if (dp[cntI - 1][cntJ - 1] + days[cntI - 1] <= dp[cntI - 1][cntJ + 1]) {
                            cntJ--;
                        } else {
                            coupDays.add(cntI - 1);
                            cntJ++;
                        }
                    }
                }
            } else {
                if (cntJ != n - 1) {
                    if (dp[cntI - 1][cntJ] + days[cntI - 1] > dp[cntI - 1][cntJ + 1]) {
                        coupDays.add(cntI - 1);
                        cntJ++;
                    }
                }
            }
            cntI--;
        }
        out.println(coupDays.size());
        for (int i = coupDays.size() - 1; i >= 0; i--) {
            out.println(coupDays.get(i) + 1 + " ");
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
        new F().run();
    }
}