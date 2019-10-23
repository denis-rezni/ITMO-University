import java.io.*;
import java.util.*;

public class K {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n;

    public void solve() throws IOException {
        n = in.nextInt();
        int w = in.nextInt();
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = in.nextInt();
        }
        Pair[] dp = new Pair[1 << n];
        for (int i = 0; i < 1 << n; i++) {
            dp[i] = new Pair(Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
        }
        for (int i = 0; i < n; i++) {
            dp[1 << i] = new Pair(0, weights[i]);
        }

        for (int i = 1; i < 1 << n; i++) {//subsets
            for (int j = 0; j < n; j++) {//new cow to add to subset
                if ((i & (1 << j)) == 0) {//assert the cow is new
                    int newMask = i + (1 << j);//mask with a new cow
                    if (dp[i].curW + weights[j] <= w) {//can we fill the cow in this lift?
                        if (dp[newMask].lifts > dp[i].lifts) {
                            dp[newMask].curW = dp[i].curW + weights[j];
                            dp[newMask].lifts = dp[i].lifts;
                            dp[newMask].prev = i;
                        } else if (dp[i].lifts == dp[newMask].lifts) {
                            if (dp[i].curW + weights[j] < dp[newMask].curW) {
                                dp[newMask].curW = dp[i].curW + weights[j];
                                dp[newMask].prev = i;
                            }
                        }
                    } else {
                        if (dp[i].lifts + 1 < dp[newMask].lifts) {
                            dp[newMask].lifts = dp[i].lifts + 1;
                            dp[newMask].curW = weights[j];
                            dp[newMask].prev = i;
                        }
                    }
                }
            }
        }
        out.println(dp[(1 << n) - 1].lifts + 1);
        int counter = (1 << n) - 1;
        ArrayList<Integer> lift = new ArrayList<>();
        while(counter != 0){
            lift.add(Integer.toBinaryString(counter - dp[counter].prev).length());
            if(dp[counter].lifts != dp[dp[counter].prev].lifts){
                out.print(lift.size() + " ");
                for(int i = 0; i < lift.size(); i++){
                    out.print(lift.get(i) + " ");
                }
                out.println();
                lift = new ArrayList<>();
            }
            counter = dp[counter].prev;
        }

    }

    private class Pair {
        int lifts;
        int curW;
        int prev;
        public Pair(int lifts, int curWeight) {
            this.lifts = lifts;
            this.curW = curWeight;
        }
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
        new K().run();
    }
}