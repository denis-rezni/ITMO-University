import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Sixteen {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    BigInteger bigFact[] = new BigInteger[31];
    long c[][] = new long[31][31];

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] comb = Arrays.stream(in.nextLine().split(" ")).mapToInt(i -> Integer.parseInt(i) - 1).toArray();
        for (int i = 0; i <= 30; i++) {
            bigFact[i] = bigFact(i);
        }
        for (int i = 0; i <= 30; i++) {
            for (int j = 0; j <= i; j++) {
                c[i][j] = bigBinCoef(i, j);
            }
        }
        out.print(combToNumber(comb, n));
    }

    private long combToNumber(int[] comb, int n) {
        int k = comb.length;
        long res = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < comb[i]; j++) {
                if (i == 0 || j > comb[i - 1]) {
                    if ((n - j - k + i) >= 0) {
                        res += c[n - j - 1][k - i - 1];
                    }
                }
            }
        }
        return res;
    }

    private BigInteger bigFact(long x) {
        BigInteger res = BigInteger.ONE;
        for (int i = 2; i <= x; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    private long bigBinCoef(int n, int k) {
        BigInteger res = bigFact[n].divide(bigFact[k]).divide(bigFact[n - k]);
        return res.longValue();
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("choose2num.in"));
                out = new PrintWriter(new File("choose2num.out"));
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
        new Sixteen().run();
    }

}
