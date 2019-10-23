import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskC {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int aut[][];
    boolean isTerminal[];
    int alpha = 26;
    boolean hasBadCycle = false;
    int MOD = 1_000_000_007;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        isTerminal = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            isTerminal[in.nextInt()] = true;
        }
        aut = new int[n + 1][alpha];
        ArrayList<ArrayList<Integer>> routesIn = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            routesIn.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut[a][c] = b;
            routesIn.get(b).add(a);
        }
        boolean hasWay[] = new boolean[n + 1];
        boolean visited[] = new boolean[n + 1];
        for (int i = 1; i < n + 1; i++) {
            if (isTerminal[i]) {
                hasRoute(i, visited, hasWay, routesIn);
            }
        }
        int color[] = new int[n + 1];
        List<Integer> order = new ArrayList<>();
        finiteNumWords(1, color, hasWay, order);
        if (hasBadCycle) {
            out.println(-1);
            return;
        }
        int routes[] = new int[n + 1];
        routes[1] = 1;
        Collections.reverse(order);
        for(int x : order){
            for (int i = 0; i < 26; i++) {
                int next = aut[x][i];
                routes[next] += routes[x];
                if(routes[next] >= MOD){
                    routes[next] -= MOD;
                }
            }
        }

//        for (int i = 1; i < n + 1; i++) {
//            System.out.println("routes in i: " + i + " " + routes[i]);
//        }

        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            if(isTerminal[i]){
                res = (res + routes[i]) % MOD;
            }
        }
        out.println(res);


    }

    void hasRoute(int q, boolean[] visited, boolean[] hasWay, List<ArrayList<Integer>> routesIn) {
        hasWay[q] = true;
        visited[q] = true;
        for (int i = 0; i < routesIn.get(q).size(); i++) {
            if (!visited[routesIn.get(q).get(i)]) {
                hasRoute(routesIn.get(q).get(i), visited, hasWay, routesIn);
            }
        }
    }

    void finiteNumWords(int q, int[] color, boolean hasWay[], List<Integer> order) {
        color[q] = 1;
        for (int i = 0; i < alpha; i++) {
            int next = aut[q][i];
            if(next != 0){
                if(color[next] == 1){
                    if(hasWay[next]){
                        hasBadCycle = true;
                        return;
                    }
                }
                if(color[next] == 0){
                    finiteNumWords(next, color, hasWay, order);
                }
            }
        }
        color[q] = 2;
        order.add(q);
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("problem3.in"));
                out = new PrintWriter(new File("problem3.out"));
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

    public static void main(String[] args) {
        new TaskC().run();
    }
}
