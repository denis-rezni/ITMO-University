import java.io.*;
import java.util.*;

public class TaskI {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int alpha = 26;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        boolean isTerminal[] = new boolean[n + 1];
//        Set<Integer>[][] inverse = new HashSet<>[][];
        ArrayList<HashSet<Integer>> pi = new ArrayList<>();
        pi.add(new HashSet<>());//terminal
        pi.add(new HashSet<>());//not terminal
        for (int i = 0; i < k; i++) {
            isTerminal[in.nextInt()] = true;
        }
        int aut[][] = new int[n + 1][alpha];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut[a][c] = b;
        }
        for (int i = 0; i < n + 1; i++) {
            pi.get(isTerminal[i] ? 0 : 1).add(i);
        }
        HashSet<Integer> set;
        if (pi.get(0).size() > pi.get(1).size()) {
            set = new HashSet<>(pi.get(1));
        } else {
            set = new HashSet<>(pi.get(0));
        }
        ArrayList<Splitter> list = new ArrayList<>();
        for (int i = 0; i < alpha; i++) {
            list.add(new Splitter(new HashSet<>(set), i));
        }
        ArrayList<HashSet<Integer>> piNew = new ArrayList<>();
        while (list.size() != 0) {
            Splitter cur = list.remove(list.size() - 1);
            for (int i = 0; i < pi.size(); i++) {
                ArrayList<HashSet<Integer>> refined = refine(cur, pi.get(i), aut);
                if(refined.get(0).size() == 0 || refined.get(1).size() == 0){
                    piNew.add(pi.get(i));
                    continue;
                }
                piNew.add(refined.get(0));
                piNew.add(refined.get(1));
                for(int c = 0; c < alpha; c++){

                }

            }
        }

    }

    ArrayList<HashSet<Integer>> refine(Splitter p, HashSet<Integer> r, int aut[][]) {
        HashSet<Integer> contains = new HashSet<>();
        HashSet<Integer> containsNot = new HashSet<>();
        for(int i : r){
            if(p.list.contains(aut[i][p.letter])){
                contains.add(i);
            } else {
                containsNot.add(i);
            }
        }
        ArrayList<HashSet<Integer>> res = new ArrayList<>();
        res.add(contains);
        res.add(containsNot);
        return res;
    }

    class Splitter {
        HashSet<Integer> list;
        int letter;

        public Splitter(HashSet<Integer> list, int letter) {
            this.list = list;
            this.letter = letter;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("fastminimization.in"));
                out = new PrintWriter(new File("fastminimization.out"));
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
        new TaskI().run();
    }
}
