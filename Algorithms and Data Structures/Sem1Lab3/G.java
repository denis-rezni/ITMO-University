import java.io.*;
import java.util.*;

public class G {
    private FastScanner in;
    private PrintWriter out;
    private boolean systemIO = true;
    private String seq;
    private int n;
    private String dp[][];

    private void solve() throws IOException {
        seq = in.next();
        n = seq.length();
        dp = new String[n][n];
        out.print(findSub(0, n - 1));
    }

    private String findSub(int l, int r) {
        if (dp[l][r] == null) {
            char c1 = seq.charAt(l);
            char c2 = seq.charAt(r);
            if (l + 1 >= r) {
                if (complements(c1, c2)) {
                    dp[l][r] = c1 + "" + c2;
                    return c1 + "" + c2;
                } else {
                    dp[l][r] = "";
                    return "";
                }
            }

            String max = "";
            if (complements(c1, c2)) {
                max = c1 + findSub(l + 1, r - 1) + c2;
            }
            String leftPlus = findSub(l + 1, r);
            String rightMinus = findSub(l, r - 1);
            if(max.length() < rightMinus.length()){
                max = rightMinus;
            }
            if(max.length() < leftPlus.length()){
                max = leftPlus;
            }
            for (int i = l + 1; i < r; i++) {
                String cur = findSub(l, i) + findSub(i + 1, r);
                if (cur.length() > max.length()) {
                    max = cur;
                }
            }
            dp[l][r] = max;
            return max;

        } else {
            return dp[l][r];
        }
    }

    private boolean complements(char c1, char c2) {
        if (c1 == '[' && c2 == ']') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else return c1 == '(' && c2 == ')';
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
        new G().run();
    }
}
