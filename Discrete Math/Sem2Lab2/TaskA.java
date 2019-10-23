import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        String toCheck = in.next();
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        Node arr[] = new Node[n];
        for(int i = 0; i < n; i++){
            arr[i] = new Node();
        }
        for(int i = 0; i < k; i++){
            arr[in.nextInt() - 1].isTerminal = true;
        }
        for(int i = 0; i < m; i++){
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            String letter = in.next();
            arr[a].routes.put(letter, b);
        }
        int curState = 0;
        boolean broke = false;
        for(int i = 0; i < toCheck.length(); i++){
            String cur = String.valueOf(toCheck.charAt(i));
            if(arr[curState].routes.containsKey(cur)){
                curState = arr[curState].routes.get(cur);
            } else {
                broke = true;
                break;
            }
        }
        if(arr[curState].isTerminal && !broke){
            out.println("Accepts");
        } else {
            out.println("Rejects");
        }


    }

    class Node{
        Map<String, Integer> routes;
        boolean isTerminal;

        public Node() {
            routes = new HashMap<>();
            isTerminal = false;
        }
    }



    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("problem1.in"));
                out = new PrintWriter(new File("problem1.out"));
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
