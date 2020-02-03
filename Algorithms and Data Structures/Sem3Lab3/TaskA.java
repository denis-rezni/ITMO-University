import java.io.*;
import java.util.*;

public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    private void solve() {
        String s = in.next();
        int n = s.length();
        int queries = in.nextInt();
        //precalc
        long x = 27449L;
        long m = 1400305337L;
        long[] xs = new long[n];
        fillXs(xs, x, m);
        long[] hashes = new long[n]; //hash s[0..i]
        fillHashes(hashes, s, x, m, n);
        for (int q = 0; q < queries; q++) {
            int l1 = in.nextInt() - 1;
            int r1 = in.nextInt() - 1;
            int l2 = in.nextInt() - 1;
            int r2 = in.nextInt() - 1;
            long hash1 = getHash(l1, r1, hashes, xs, m);
//            System.out.println(s.substring(l1, r1 + 1));
//            System.out.println(hash1);
            long hash2 = getHash(l2, r2, hashes, xs, m);
//            System.out.println(s.substring(l2, r2 + 1));
//            System.out.println(hash2);
            if (hash1 == hash2) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }


    }

    long getHash(int l, int r, long[] hashes, long[] xs, long m) {
        if (l == 0) {
            return hashes[r];
        }
        return (((hashes[r] - Math.multiplyExact(hashes[l - 1], xs[r - l + 1])) % m) + m) % m;
    }

    void fillHashes(long[] hashes, String s, long x, long m, int n) {
        hashes[0] = s.charAt(0);
        for (int i = 1; i < n; i++) {
            hashes[i] = (hashes[i - 1] * x + s.charAt(i)) % m;
        }
    }

    void fillXs(long[] xs, long x, long m) {
        long cur = 1;
        for (int i = 0; i < xs.length; i++) {
            xs[i] = cur;
            cur *= x;
            cur %= m;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("schedule.in"));
                out = new PrintWriter(new File("schedule.out"));
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
        new TaskA().run();
    }
}