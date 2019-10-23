import java.io.*;
import java.util.*;

public class E {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    public void solve() throws IOException {
        out.print(diff(in.next(), in.next()));
    }

    private int diff(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int d[][] = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1 + 1; i++) {
            for (int j = 0; j < n2 + 1; j++) {
                if (i == 0 && j == 0) {
                    d[i][j] = 0;
                    continue;
                }
                d[i][j] = Integer.MAX_VALUE;
                if (i > 0 && j > 0 && s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                }
                if (i > 0 && j > 0) {
                    d[i][j] = Math.min(d[i][j], d[i - 1][j - 1] + 1);
                }
                if (i > 0) {
                    d[i][j] = Math.min(d[i][j], d[i - 1][j] + 1);
                }
                if (j > 0) {
                    d[i][j] = Math.min(d[i][j], d[i][j - 1] + 1);
                }
            }
        }

        return d[n1][n2];
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("input.txt"));
                out = new PrintWriter(new File("output.txt"));
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
        new E().run();
    }
}