import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwentyFour {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;


    public void solve() throws IOException {
        int n = in.nextInt();
        int[] perm = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        out.println(prevPerm(perm.clone(), n));
        out.print(nextPerm(perm.clone(), n));
    }

    private String prevPerm(int[] perm, int n){
        int pointer = n - 1;
        int changeIndex = 0;
        while (pointer > 0) {
            if (perm[pointer] > perm[pointer - 1]) {
                pointer--;
            } else {
                changeIndex = pointer - 1;
                break;
            }
        }
        if (pointer == 0) {
            return Arrays.stream(new int[n]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        }
        int max = pointer;
        for (int j = pointer; j < n; j++) {
            if (perm[max] < perm[j] && perm[j] < perm[changeIndex]) {
                max = j;
            }
        }
        int temp = perm[max];
        perm[max] = perm[changeIndex];
        perm[changeIndex] = temp;
        int j = 0;
        for (int i = pointer; i < (n + pointer) / 2; i++) {
            temp = perm[i];
            perm[i] = perm[n - j - 1];
            perm[n - j - 1] = temp;
            j++;
        }
        return Arrays.stream(perm).mapToObj(String::valueOf).collect(Collectors.joining(" "));

    }

    private String nextPerm(int[] perm, int n) {
        int pointer = n - 1;
        int changeIndex = 0;
        while (pointer > 0) {
            if (perm[pointer] < perm[pointer - 1]) {
                pointer--;
            } else {
                changeIndex = pointer - 1;
                break;
            }
        }
        if (pointer == 0) {
            return Arrays.stream(new int[n]).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        }
        int min = pointer;
        for (int j = pointer; j < n; j++) {
            if (perm[min] > perm[j] && perm[j] > perm[changeIndex]) {
                min = j;
            }
        }
        int temp = perm[min];
        perm[min] = perm[changeIndex];
        perm[changeIndex] = temp;
        int j = 0;
        for (int i = pointer; i < (n + pointer) / 2; i++) {
            temp = perm[i];
            perm[i] = perm[n - j - 1];
            perm[n - j - 1] = temp;
            j++;
        }
        return Arrays.stream(perm).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextperm.in"));
                out = new PrintWriter(new File("nextperm.out"));
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
        new TwentyFour().run();
    }

}
