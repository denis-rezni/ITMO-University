import java.io.*;
import java.util.*;

public class TaskE {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        List<Integer> weights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weights.add(in.nextInt());
        }
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            elements.add(i);
        }
        List<Set<Integer>> cycles = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int size = in.nextInt();
            Set<Integer> curSet = new HashSet<>();
            for (int j = 0; j < size; j++) {
                curSet.add(in.nextInt() - 1);
            }
            cycles.add(curSet);
        }
        Collections.sort(elements, Comparator.comparingInt(weights::get).reversed());
        Set<Integer> res = new HashSet<>();
        for (int element : elements) {
            res.add(element);
            if (hasCycleAsSubset(res, cycles)) {
                res.remove(element);
            }
        }
        int sum = 0;
        for (int element : res) {
            sum += weights.get(element);
        }
        out.println(sum);

    }

    boolean hasCycleAsSubset(Set<Integer> set, List<Set<Integer>> cycles) {
        loop:
        for (Set<Integer> cycle : cycles) {
            for (int elem : cycle) {
                if (!set.contains(elem)) {
                    continue loop;
                }
            }
            return true;
        }
        return false;
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("cycles.in"));
                out = new PrintWriter(new File("cycles.out"));
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