import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskF {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    boolean isTerminal2[];
    boolean isTerminal1[];
    int aut1[][];
    int aut2[][];

    public void solve() throws IOException {
        int n1 = in.nextInt();
        int m1 = in.nextInt();
        int k1 = in.nextInt();
        isTerminal1 = new boolean[n1 + 1];
        for (int i = 0; i < k1; i++) {
            isTerminal1[in.nextInt()] = true;
        }
        aut1 = new int[n1 + 1][26];
        for (int i = 0; i < m1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut1[a][c] = b;
        }

        int n2 = in.nextInt();
        int m2 = in.nextInt();
        int k2 = in.nextInt();
        isTerminal2 = new boolean[n2 + 1];
        for (int i = 0; i < k2; i++) {
            isTerminal2[in.nextInt()] = true;
        }
        aut2 = new int[n2 + 1][26];
        for (int i = 0; i < m2; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.next().charAt(0) - 97;
            aut2[a][c] = b;
        }

        if(dfs(1, 1, new boolean[n1 + 1], new boolean[n2 + 1])){
            out.println("YES");
        } else {
            out.println("NO");
        }




    }

    boolean dfs(int u, int v, boolean visited1[], boolean visited2[]){
        visited1[u] = true;
        visited2[v] = true;
        if(isTerminal1[u] != isTerminal2[v]){
            return false;
        }
        boolean res = true;
        for(int i = 0; i < 26; i++){
            int newU = aut1[u][i];
            int newV = aut2[v][i];
//            System.out.println("i: " + i + " newU: " + newU + " newV: " + newV);
            if((newU == 0 && newV != 0) || (newU != 0 && newV == 0)){
                return false;
            }
            if(visited1[newU] ^ visited2[newV]){
                return false;
            }
            if(!visited1[newU] && !visited2[newV]){
                res = res & dfs(newU, newV, visited1, visited2);
            }
        }
        return res;
    }



    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("isomorphism.in"));
                out = new PrintWriter(new File("isomorphism.out"));
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
        new TaskF().run();
    }
}
