import java.io.*;
import java.util.*;

public class TaskJTest {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n;
    List<Set<Integer>> ways;
    int parents[];
    int howManyTimes = 0;
    public void solve() {
        long start = System.currentTimeMillis();
        n = 10000;
        ways = new ArrayList<>();
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            ways.add(new HashSet<>());
        }
        for (int i = 1; i < n; i++) {
            int u = i - 1;
            int v = i;
            ways.get(u).add(v);
            ways.get(v).add(u);
        }
        decompose(0, -1);
        long end = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            out.println(parents[i] + 1 + " ");
        }
        out.println();
        out.println(end - start);
        out.println("howMany: " + howManyTimes);

    }

    void decompose(int v, int parent) {
        int c = findCentroid(v, parent);
        for (int child : ways.get(c)) {
            ways.get(child).remove(c);
            decompose(child, c);
        }
    }

    int findCentroid(int v, int parent) {
        int weights[] = new int[n];
        findWeights(v, v, weights);
        int from = v;
        loop: while(true){
            for (int child : ways.get(v)) {
                if (child != from) {
                    if (weights[child] > weights[v] / 2) {
                        from = v;
                        v = child;
                        continue loop;
                    }
                }
            }
            break;
        }
        parents[v] = parent;
        return v;
    }


    int findWeights(int v, int from, int weights[]) {
        int res = 1;
        for (int child : ways.get(v)) {
            if (child != from) {
                res += findWeights(child, v, weights);
            }
        }
        weights[v] = res;
        return res;
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("skyscraper.in"));
                out = new PrintWriter(new File("skyscraper.out"));
            }
            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class FastScanner {
        public BufferedReader br;
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
        new TaskJTest().run();
    }
}