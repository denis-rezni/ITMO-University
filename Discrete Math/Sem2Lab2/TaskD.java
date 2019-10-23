import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskD {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int MOD = 1_000_000_007;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        int dp[][] = new int[l + 1][n];
        Node arr[] = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node();
        }
        for (int i = 0; i < k; i++) {
            arr[in.nextInt() - 1].isTerminal = true;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.next().charAt(0) - 97;
            arr[a].routes.add(new Route(c, b));
        }
        dp[0][0] = 1;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < n; j++) {
                for(int c = 0; c < arr[j].routes.size(); c++){
                    int q =  arr[j].routes.get(c).to;
                    dp[i + 1][q] = (dp[i + 1][q] + dp[i][j]) % MOD;
                }
//                for (int h = 0; h < n; h++) {
//                    for(char symb = 97; symb < 123; symb++) {
//                        String cur = String.valueOf(symb);
//                        if (arr[j].routes.containsKey(cur) && arr[j].routes.get(cur) == h) {
//                            dp[i + 1][h] = (dp[i + 1][h] + dp[i][j]) % MOD;
//                        }
//                    }
//                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i].isTerminal){
                res = (res + dp[l][i]) % MOD;
            }
        }
        out.println(res);


    }

    class Node {
        List<Route> routes;
        boolean isTerminal;

        public Node() {
            routes = new ArrayList<>();
            isTerminal = false;
        }
    }

    class Route{
        int letter;
        int to;

        public Route(int letter, int to) {
            this.letter = letter;
            this.to = to;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("problem4.in"));
                out = new PrintWriter(new File("problem4.out"));
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
