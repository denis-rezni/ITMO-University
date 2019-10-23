import java.io.*;
import java.util.*;

public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int start = in.next().charAt(0) - 'A' + 1;
        //0 - has no nonterm, 1 - has A, 2 - has B, ...
        Three routes[] = new Three[n];
        for (int i = 0; i < n; i++) {
            int from = in.next().charAt(0) - 'A' + 1;
            in.next();
            String res = in.next();
            int letter = res.charAt(0) - 'a';
            int to;
            if(res.length() == 2){
                to = res.charAt(1) - 'A' + 1;
            } else {
                to = 0;
            }
            routes[i] = new Three(from, letter, to);
        }

        int m = in.nextInt();
        for (int req = 0; req < m; req++) {
            String word = in.next();
            boolean dp[][] = new boolean[27][word.length() + 1];
            dp[start][0] = true;
            for (int i = 0; i < word.length(); i++) {
                for (int j = 0; j < n; j++) {
                    Three route = routes[j];
                    if(dp[route.from][i] && route.letter == word.charAt(i) - 'a'){
                        dp[route.to][i + 1] = true;
                    }
                }
            }
            if(dp[0][word.length()]){
                out.println("yes");
            } else {
                out.println("no");
            }
        }

    }


    class Three{
        int from;
        int letter;
        int to;

        public Three(int from, int letter, int to) {
            this.from = from;
            this.letter = letter;
            this.to = to;
        }
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("automaton.in"));
                out = new PrintWriter(new File("automaton.out"));
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
