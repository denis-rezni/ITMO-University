import java.io.*;
import java.util.*;

public class Nineteen {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    long[][] s;
    private String[] brackets = new String[]{"(", ")", "[", "]"};

    private void solve() throws IOException {
        int n = in.nextInt();
        long k = in.nextLong();
        s = new long[2 * n + 1][n + 1];
        dp(s, n);
        out.print(numberToSeq(n, k));
    }

    private String numberToSeq(int n, long k) {
        int balance = 0;
        StringBuilder res = new StringBuilder();
        Stack<String> stack = new Stack<>();
        stack.push("");
        for (int i = 0; i < 2 * n + 1; i++) {
            for (int j = 0; j < brackets.length; j++) {
                int newBalance = balance + ((j % 2 == 0) ? (1) : (-1));
                if (newBalance >= 0 && newBalance <= n && match(stack.peek(), brackets[j])) {
                    if (k >= s[2 * n - i][newBalance] * (long) Math.pow(2, (2 * n - i - newBalance) / 2)) {
                        k -= s[2 * n - i][newBalance] * (long) Math.pow(2, (2 * n - i - newBalance) / 2);
                    } else {
                        if(j % 2 == 1){
                            stack.pop();
                        } else {
                            stack.push(brackets[j]);
                        }
                        res.append(brackets[j]);
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

    private boolean match(String s1, String s2) {
        if (s1.equals("(") && s2.equals("]")) {
            return false;
        } else return !s1.equals("[") || !s2.equals(")");
    }

    private void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("num2brackets2.in"));
                out = new PrintWriter(new File("num2brackets2.out"));
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
        new Nineteen().run();
    }
}
