import java.io.*;
import java.util.*;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int MOD = 1_000_000_007;

    public void solve() {
        int n = in.nextInt();
        int startLit = in.next().charAt(0) - 'A';
        List<RuleNotTerm> notTerm = new ArrayList<>();
        List<RuleTerm> term = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String check = in.nextLine();
            if (check.toUpperCase().equals(check)) {
                RuleNotTerm rule = new RuleNotTerm(check.charAt(0) - 'A', check.substring(5));
                if (newRule(rule, notTerm)) {
                    notTerm.add(rule);
                }
            } else {
                term.add(new RuleTerm(check.charAt(0) - 'A', check.charAt(5) - 'a'));
            }
        }
        String word = in.next();
        int l = word.length();
        int dp[][][] = new int[26][l][l];
        for (int i = 0; i < term.size(); i++) {
            RuleTerm rule = term.get(i);
            for (int j = 0; j < l; j++) {
                if (word.charAt(j) - 'a' == rule.to) {
                    dp[rule.from][j][j] = 1;
                }
            }
        }

        for (int seg = 2; seg < l + 1; seg++) {
            for (int ruleIndex = 0; ruleIndex < notTerm.size(); ruleIndex++) {
                RuleNotTerm rule = notTerm.get(ruleIndex);
                int from = rule.from;
                int first = rule.first;
                int second = rule.second;
                for (int start = 0; start < l - seg + 1; start++) {
                    int end = start + seg - 1;
                    for (int k = start; k < end; k++) {
                        dp[from][start][end] = (dp[from][start][end] + (int) (((long) dp[first][start][k] * (long) dp[second][k + 1][end]) % MOD)) % MOD;//fix mb
//                            System.out.println("from: " + from + " start: " + start + " end: " + end + " : " + dp[from][start][end]);
                    }
                }

            }
        }

        out.println(dp[startLit][0][l - 1]);
    }

    boolean newRule(RuleNotTerm rule, List<RuleNotTerm> rules) {
        for (int i = 0; i < rules.size(); i++) {
            RuleNotTerm cur = rules.get(i);
            if (cur.first == rule.first && cur.second == rule.second && cur.from == rule.from) {
                return false;
            }
        }
        return true;
    }


    class RuleNotTerm {
        int from;
        int first;
        int second;

        public RuleNotTerm(int from, String to) {
            this.from = from;
            first = to.charAt(0) - 'A';
            second = to.charAt(1) - 'A';
        }
    }

    class RuleTerm {
        int from;
        int to;

        public RuleTerm(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nfc.in"));
                out = new PrintWriter(new File("nfc.out"));
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
        new TaskD().run();
    }
}
