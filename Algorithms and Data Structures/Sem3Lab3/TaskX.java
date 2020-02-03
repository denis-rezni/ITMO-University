import java.io.*;
import java.util.*;

public class TaskX {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    final long x = 27449L;
    final long m = 1400305337L;
    int n;
    long[] xs;
    long[] hashes;
    String s;
    private void solve() {
        s = "abacaba";
        n = s.length();
        xs = new long[n];
        hashes = new long[n]; //hash s[0..i]
        fillXs();
        fillHashes(s);
        List<Integer> suffixes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            suffixes.add(i);
        }
        Collections.sort(suffixes, byStringComparator);
        for (int index : suffixes) {
            System.out.println(s.substring(index));
        }
    }

    Comparator<Integer> byStringComparator = new Comparator<>() {
        @Override
        public int compare(Integer o1, Integer o2) { //they are probably not null :)
            int first = o1; //start of the first suffix
            int second = o2; //start of the second suffix
            int firstDiffIndex = firstDiffLettersIndex(first, second);
            if (first + firstDiffIndex >= n) {
                return -1;
            }
            if (second + firstDiffIndex >= n) {
                return 1;
            }
            return Character.compare(s.charAt(first + firstDiffIndex),
                    s.charAt(second + firstDiffIndex));//-1 or 1
            //this comparator never returns 0, bc all suffixes are of different length
        }
    };

    int firstDiffLettersIndex(int first, int second) {
        // I want to find the number to add to these numbers, to get the first different char
        int low = -1;
        int high = Math.min(n - first, n - second);
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            long hashS = getHash(first, first + mid);
            long hashT = getHash(second, second + mid);
            if (hashS == hashT) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    long getHash(int l, int r) {
        if (l == 0) {
            return hashes[r];
        }
        return (((hashes[r] - hashes[l - 1] * xs[r - l + 1]) % m) + m) % m;
    }

    void fillHashes(String s) {
        hashes[0] = s.charAt(0);
        for (int i = 1; i < n; i++) {
            hashes[i] = (hashes[i - 1] * x + s.charAt(i)) % m;
        }
    }

    void fillXs() {
        long cur = 1;
        for (int i = 0; i < xs.length; i++) {
            xs[i] = cur;
            cur *= x;
            cur %= m;
        }
    }

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
        new TaskX().run();
    }
}