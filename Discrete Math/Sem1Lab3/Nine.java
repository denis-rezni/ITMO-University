import java.io.*;
import java.util.*;

public class Nine {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    long[][] s;

    private void solve() throws IOException {
        int n = in.nextInt();
        s = new long[2 * n + 1][n + 1];
        dp(s, n);
        for (long i = 0; i < s[2 * n][0]; i++){
            out.println(numberToSeq(n, i));
        }
    }

    private String numberToSeq(int n, long k) {
        int balance = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 1; j > -2; j -= 2) {
                int newBalance = balance + j;
                if (newBalance >= 0 && newBalance <= n) {
                    if (k >= s[2 * n - i][newBalance]) {
                        k -= s[2 * n - i][newBalance];
                    } else {
                        if (j == 1) {
                            res.append("(");
                        } else {
                            res.append(")");
                        }
                        balance = newBalance;
                        break;
                    }
                }
            }
        }
        return res.toString();
    }

    private void dp(long[][] s, int n) {
        s[0][0] = 1;
        for (int i = 1; i < 2 * n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (j == 0) {
                    s[i][j] = s[i - 1][j + 1];
                } else if (j == n) {
                    s[i][j] = s[i - 1][j - 1];
                } else {
                    s[i][j] = s[i - 1][j - 1] + s[i - 1][j + 1];
                }
            }
        }
    }

    private void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("brackets.in"));
                out = new PrintWriter(new File("brackets.out"));
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
        new Nine().run();
    }
}
