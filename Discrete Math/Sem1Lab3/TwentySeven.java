import java.io.*;
import java.util.*;

public class TwentySeven {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;


    public void solve() throws IOException {
        String seq = in.next();
        out.print(nextSeq(seq));
    }

    private String nextSeq(String seq) {
        int close = 0;
        int open = 0;
        int pointer = seq.length() - 1;
        while (pointer >= 0) {
            if (seq.charAt(pointer) == '(') {
                open++;
                if (open < close) {//==> balance > 0
                    break;
                }
            } else {
                close++;
            }
            pointer--;
        }
        if(pointer == -1){
            return "-";
        }
        StringBuilder res = new StringBuilder(seq.substring(0, pointer));
        res.append(")");
        for(int i = 0; i < open; i++){
            res.append("(");
        }
        for(int i = 1; i < close; i++){
            res.append(")");
        }
        return res.toString();
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextbrackets.in"));
                out = new PrintWriter(new File("nextbrackets.out"));
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
        new TwentySeven().run();
    }

}
