import java.io.*;
import java.util.*;

public class Six {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int n = in.nextInt();
        int counter = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            String cur = toBinary(i, n);
            if (check(cur)) {
                list.add(cur);
                counter++;
            }
        }
        out.println(counter);
        for(int i = 0; i < list.size(); i++){
            out.println(list.get(i));
        }

    }


    public boolean check(String x) {
        for (int i = 0; i < x.length() - 1; i++) {
            if (x.charAt(i) == x.charAt(i + 1) && x.charAt(i) == '1') {
                return false;
            }
        }
        return true;
    }

    public String toBinary(int i, int n) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
            s.append("0");
        }
        s.append(Integer.toBinaryString(i));
        return s.toString();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("vectors.in"));
                out = new PrintWriter(new File("vectors.out"));
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
        new Eight().run();
    }
}
