import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Eight {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    BigInteger bigFact[] = new BigInteger[31];
    long c[][] = new long[31][31];

    public void solve() throws IOException {
        int n = in.nextInt();
        int k = in.nextInt();
        for (int i = 0; i <= 30; i++) {
            bigFact[i] = bigFact(i);
        }
        for(int i = 0; i <= 30; i++){
            for(int j = 0; j <= i; j++){
                c[i][j] = bigBinCoef(i, j);
            }
        }


        for(long i = 0; i < c[n][k]; i++){
            out.println(numToComb(i, n, k));
        }
    }

    private String numToComb(long number, int n, int k) {
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j > res[i - 1]) {
                    if ((n - j - k + i) >= 0 && number >= c[n - j - 1][ k - i - 1]) {
                        number -= c[n - j - 1][k - i - 1];
                    } else {
                        res[i] = j;
                        break;
                    }
                }
            }
        }
        return Arrays.stream(res).mapToObj(i -> String.valueOf(i + 1)).collect(Collectors.joining(" "));
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


    private String toBinary(int i, int n) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
            s.append("0");
        }
        s.append(Integer.toBinaryString(i));
        return s.toString();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("choose.in"));
                out = new PrintWriter(new File("choose.out"));
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
        new Eight().run();
    }
}
