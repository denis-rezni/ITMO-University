import java.io.*;
import java.util.*;

public class TaskF {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int parents[];
    int toNew[];
    int toOld[];
    int vertexCounter;
    List<List<Integer>> children;
    int lcadp[][];

    public void solve() {
        int n = in.nextInt();
        children = new ArrayList<>();
        for(int i = 0; i < n; i++){
            children.add(new ArrayList<>());
        }
        parents = new int[n];
        toOld = new int[n];
        toNew = new int[n];
        int root = 0;
        for (int i = 0; i < n; i++) {
            int parent = in.nextInt() - 1;
            if(parent > -1){
                children.get(parent).add(i);
                parents[i] = parent;
            } else {
                parents[i] = i;
                root = i;
            }
        }

        vertexCounter = 0;
        countNewNumbers(root);

        int logn = log2(n);
        lcadp = new int[n][logn + 1];
        for (int v = 0; v < n; v++) {
            lcadp[v][0] = parents[v];
        }
        for (int i = 1; i < logn + 1; i++) {
            for (int v = 0; v < n; v++) {
                lcadp[v][i] = lcadp[lcadp[v][i - 1]][i - 1];
            }
        }

        int depths[] = new int[n];
        findDepth(root, depths, 1);

        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int res = 0;
            int lords[] = new int[in.nextInt()];
            for (int j = 0; j < lords.length; j++){
                lords[j] = toNew[in.nextInt() - 1];
            }
            Arrays.sort(lords);
            res += depths[toOld[lords[0]]];
            for (int j = 1; j < lords.length; j++) {
                int newLord = lords[j];
                int oldLord = lords[j - 1];
                res += depths[toOld[newLord]];
                res -= depths[lca(toOld[newLord], toOld[oldLord], depths, logn)];
            }
            out.println(res);
        }

    }

    void countNewNumbers(int q){
        toNew[q] = vertexCounter;
        toOld[vertexCounter] = q;
        vertexCounter++;
        for (int i = 0; i < children.get(q).size(); i++) {
            countNewNumbers(children.get(q).get(i));
        }
    }


    int log2(int x) {
        int res = 1;
        int counter = 0;
        while (res < x) {
            res *= 2;
            counter++;
        }
        return counter;
    }

    void findDepth(int v, int[] depth, int level) {
        depth[v] = level;
        for (int i = 0; i < children.get(v).size(); i++) {
            findDepth(children.get(v).get(i), depth, level + 1);
        }
    }

    int lca(int u, int v, int[] depths, int logn) {

        if (depths[v] > depths[u]) {
            int temp = u;
            u = v;
            v = temp;
        }
        //now u is deeper
        int h = depths[u] - depths[v];
        for (int i = logn; i >= 0; i--) {
            if (1 << i <= h) {
                h -= 1 << i;
                u = lcadp[u][i];
            }
        }
        if (v == u) {
            return (v);
        }

        for (int i = logn; i >= 0; i--) {
            if (lcadp[u][i] != lcadp[v][i]) {
                u = lcadp[u][i];
                v = lcadp[v][i];
            }
        }
        return (parents[u]);

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
        new TaskF().run();
    }
}