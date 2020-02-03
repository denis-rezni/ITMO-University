import java.io.*;
import java.util.*;

@SuppressWarnings("ConstantConditions")
public class TaskA {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    private void solve() {
        int n = in.nextInt();
        boolean[][] hasEdge = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            for (int j = 0; j < line.length(); j++) {
                hasEdge[i][j] = line.charAt(j) == '1';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                hasEdge[j][i] = hasEdge[i][j];
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println();
//            for (int j = 0; j < n; j++) {
//                System.out.print(((hasEdge[i][j]) ? 1 : 0) + " ");
//            }
//        }
//        System.out.println();

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < n * (n - 1); i++) {
            int atZero = deque.pollFirst();
            int atOne = deque.pollFirst();
            boolean flag = !hasEdge[atZero][atOne];
            deque.addFirst(atOne);
            deque.addFirst(atZero);
            if (flag) {
                int[] arr = dequeToIntArray(deque);
                int pointer = 2;
                while (!hasEdge[arr[0]][arr[pointer]]
                        || (/*pointer + 1 < arr.length && */ !hasEdge[arr[1]][arr[pointer + 1]])) {
                    pointer++;
                }
                swap(1, pointer, arr);
                for (int j = 0; j < arr.length; j++) {
                    deque.addLast(arr[j]);
                }
            }
            deque.addLast(deque.getFirst());
            deque.pollFirst();
        }

        Arrays.stream(dequeToIntArray(deque)).map(i -> i + 1).forEach(i -> out.print(i + " "));
    }

    void swap(int from, int to, int[] arr) {
        for (int i = from; i <= (from + to) / 2; i++) {
            int t = arr[i];
            arr[i] = arr[to - i + from];
            arr[to - i + from] = t;
        }
    }

    private int[] dequeToIntArray(Deque<Integer> deque) {
        int n = deque.size();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = deque.pollFirst();
        }
        return res;
    }




    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("fullham.in"));
                out = new PrintWriter(new File("fullham.out"));
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