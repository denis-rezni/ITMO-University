import java.io.*;
import java.util.*;

public class Twenty {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    long[][] s;
    private char[] brackets = new char[]{'(', ')', '[', ']'};

    private void solve() throws IOException {
        String seq = in.next();
        int n = seq.length() / 2;
        s = new long[2 * n + 1][n + 1];
        dp(s, n);
        out.print(seqToNumber(seq));
    }

    private long seqToNumber(String seq) {
        long res = 0;
        int curBalance = 0;
        int n = seq.length() / 2;
        Stack<Character> stack = new Stack<>();
        stack.push(' ');
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; brackets[j] != seq.charAt(i); j++) {
                int newBalance = curBalance + ((j % 2 == 0) ? (1) : (-1));
                if (newBalance <= n && newBalance >= 0 && match(stack.peek(), brackets[j])) {
                    res += s[2 * n - i - 1][newBalance] * (long) Math.pow(2, (2 * n - i - 1 - newBalance) / 2);
                }
            }
            if(seq.charAt(i) == ')' || seq.charAt(i) == ']'){
                stack.pop();
                curBalance--;
            } else {
                stack.push(seq.charAt(i));
                curBalance++;
            }

        }
        return res;
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

    private boolean match(char s1, char s2) {
        if (s1 == '(' && s2 == ']') {
            return false;
        } else return !(s1 == '[') || !(s2 == ')');
    }

    private void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("brackets2num2.in"));
                out = new PrintWriter(new File("brackets2num2.out"));
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
        new Twenty().run();
    }
}
