import java.io.*;
import java.util.*;

public class Four {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        HashSet<String> set = new HashSet<>();
        String last = new String(toBinary(0, n));
        out.println(last.toString());
        set.add(last.toString());
        for (int i = 0; i < (1 << n); i++) {
            String first = generateNext(last.toString(), 1);
            String second = generateNext(last.toString(), 0);
            if(!set.contains(first)){
                out.println(first);
                set.add(first);
                last = first;
            } else if(!set.contains(second)){
                out.println(second);
                set.add(second);
                last = second;
            }
        }
    }

    public static String toBinary(int i, int n) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
            s.append("0");
        }
        s.append(Integer.toBinaryString(i));
        return s.toString();
    }

    public String generateNext(String s, int last){
        StringBuilder res = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            res.append(s.charAt(i));
        }
        res.append(last);
        return res.toString();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                    in = new FastScanner(new File("chaincode.in"));
                out = new PrintWriter(new File("chaincode.out"));
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
        new Four().run();
    }
}