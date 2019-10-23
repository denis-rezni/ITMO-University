import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskB {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        String toCheck = in.next();
        int l = toCheck.length();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean dp[][] = new boolean[l + 1][n];
        boolean routes[][][] = new boolean[n][26][n];
        boolean isTerminal[] = new boolean[n];
        for(int i = 0; i < k; i++){
            isTerminal[in.nextInt() - 1] = true;
        }
        for(int i = 0; i < m; i++){
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.next().charAt(0) - 97;
            routes[a][c][b] = true;
        }
        dp[0][0] = true;
        for(int i = 0; i < l; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j]){
                    for(int h = 0; h < n; h++){
                        if(routes[j][toCheck.charAt(i) - 97][h]){
                            dp[i + 1][h] = true;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            if(dp[l][i] && isTerminal[i]){
                out.println("Accepts");
                return;
            }
        }
        out.println("Rejects");


    }




    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("problem2.in"));
                out = new PrintWriter(new File("problem2.out"));
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
        new TaskB().run();
    }
}
