import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskE {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;
    int MOD = 1_000_000_007;
    int alpha = 26;

    public void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int l = in.nextInt();
        Node arr[] = new Node[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = new Node();
        }
        boolean routesNFA[][][] = new boolean[n][26][n];
        boolean isTerminalNFA[] = new boolean[n];
        for (int i = 0; i < k; i++) {
            isTerminalNFA[in.nextInt() - 1] = true;
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int c = in.next().charAt(0) - 97;
            routesNFA[a][c][b] = true;
        }
        HashSet<String> set = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        HashMap<String, Integer> stringToInt = new HashMap<>();
        int toIntCounter = 0;
        String one = one(n);
        queue.addFirst(one);
        set.add(one);
        stringToInt.put(one, toIntCounter++);
        arr[stringToInt.get(one)].isTerminal = containsTerminal(queue.getFirst(), isTerminalNFA);
        while(queue.size() != 0){
            String cur = queue.pollLast();
            int from = stringToInt.get(cur);
            for(int i = 0; i < alpha; i++){
                boolean curSet[] = new boolean[n];
                boolean hasTrue = false;
                for(int j = 0; j < n; j++){
                    if(cur.charAt(j) == '1'){
                        for(int h = 0; h < n; h++){
                            if(routesNFA[j][i][h]){
                                curSet[h] = true;
                                hasTrue = true;
                            }
                        }
                    }
                }
                if(hasTrue){
                    String curSetStr = setToString(curSet);
//                    System.out.println("cur: " + cur + " letter: " + i + " curStr: " + curSetStr);
                    if(!set.contains(curSetStr)){
                        set.add(curSetStr);
                        stringToInt.put(curSetStr, toIntCounter++);
                        arr[stringToInt.get(curSetStr)].isTerminal = containsTerminal(curSetStr, isTerminalNFA);
                        queue.addFirst(curSetStr);
                    }
                    arr[from].routes.add(new Route(i, stringToInt.get(curSetStr)));
                }

            }
        }


//        for(String s : set){
//            System.out.println(s);
//        }

        n = toIntCounter;
        int dp[][] = new int[l + 1][n];//!!!!!!!!!!!!!!!!!!!!!!!!!!;

        dp[0][0] = 1;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < n; j++) {
                for(int c = 0; c < arr[j].routes.size(); c++){
                    int q =  arr[j].routes.get(c).to;
                    dp[i + 1][q] = (dp[i + 1][q] + dp[i][j]) % MOD;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if(arr[i].isTerminal){
                res = (res + dp[l][i]) % MOD;
            }
        }
        out.println(res);


//        for (int i = 0; i < 2; i++) {
//            Node cur = arr[i];
//            System.out.println("i: " + i);
//            for (int j = 0; j < cur.routes.size(); j++) {
//                System.out.println(i + " " + cur.routes.get(j).letter + " " + cur.routes.get(j).to);
//            }
//        }

    }

    boolean containsTerminal(String s, boolean isTerminal[]){
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1' && isTerminal[i]){
                return true;
            }
        }
        return false;
    }

    String setToString(boolean curSet[]){
        StringBuilder s = new StringBuilder();
        for (boolean aCurSet : curSet) {
            s.append(aCurSet ? 1 : 0);
        }
        return s.toString();
    }

    String one(int n) {
        StringBuilder res = new StringBuilder("1");
        for (int i = 0; i < n - 1; i++) {
            res.append(0);
        }
        return res.toString();
    }


    class Node {
        List<Route> routes;
        boolean isTerminal;

        public Node() {
            routes = new ArrayList<>();
            isTerminal = false;
        }
    }

    class Route{
        int letter;
        int to;

        public Route(int letter, int to) {
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
                in = new FastScanner(new File("problem5.in"));
                out = new PrintWriter(new File("problem5.out"));
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
