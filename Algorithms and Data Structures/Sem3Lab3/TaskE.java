import java.io.*;
import java.util.*;

public class TaskE {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    private void solve() {
        String s = in.next();
        int n = s.length();
        int[] prefix = prefix(s);
        if (n % 2 == 0 && prefix[n - 1] >= n / 2
        || n % 2 == 1 && prefix[n - 1] > n / 2) {
            System.out.println(n - prefix[n - 1]);
        } else {
            System.out.println(n);
        }
    }

    Comparator<String> stringComparator = new Comparator<>() {
        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    };

    int[] prefix(String s) {
        int n = s.length();
        int[] pref = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pref[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pref[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pref[i] = j;
        }
        return pref;
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
        new TaskE().run();
    }
}