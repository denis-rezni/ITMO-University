import java.io.*;
import java.util.*;

public class J {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int trueN;
    int n;

    public void solve() throws IOException {
        n = in.nextInt();
        trueN = 1;
        while (trueN < n) {
            trueN *= 2;
        }
        int m = in.nextInt();
        Node arr[] = new Node[2 * trueN - 1];

        for (int i = trueN - 1; i < trueN - 1 + n; i++) {
            arr[i] = new Node(0);
        }
        for (int i = trueN - 1 + n; i < 2 * trueN - 1; i++) {
            arr[i] = new Node(Long.MAX_VALUE);
        }
        for (int i = trueN - 2; i >= 0; i--) {
            arr[i] = new Node(Math.min(arr[2 * i + 1].value, arr[2 * i + 2].value));
        }


        for (int i = 0; i < m; i++) {
            String cmd = in.next();
//            printTree(arr);
            if (cmd.equals("defend")) {
                set(0, 0, trueN - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextInt(), arr);
            } else {
                int left = in.nextInt() - 1;
                int right = in.nextInt() - 1;
                long min = min(0, 0, trueN - 1, left, right, arr);
                out.println(min + " " + (findMinIndex(0, 0, trueN - 1, left, right, min, arr) + 1));
            }
        }

    }


    int findMinIndex(int v, int l, int r, int a, int b, long min, Node arr[]) {
        if (v >= trueN - 1 + n) {
            return -1;
        }
        if (l > b || r < a) {
            return -1;
        }
        push(v, arr);
        if (l >= a && r <= b) {
            if (operated(arr[v]) == min) {
                while (v < trueN - 1) {
                    push(v, arr);
                    if (operated(arr[2 * v + 1]) == min) {
                        v = 2 * v + 1;
                    } else {
                        v = 2 * v + 2;
                    }
                }
                return v - trueN + 1;
            } else {
                return -1;
            }
        }
        int m = l + (r - l) / 2;
        int leftRes = findMinIndex(2 * v + 1, l, m, a, b, min, arr);
        int rightRes = findMinIndex(2 * v + 2, m + 1, r, a, b, min, arr);
        if (leftRes != -1) {
            return leftRes;
        } else if (rightRes != -1) {
            return rightRes;
        } else {
            return -1;
        }
    }

    long min(int v, int l, int r, int a, int b, Node arr[]) {
        if (v >= trueN - 1 + n) {
            return Long.MAX_VALUE;
        }
        if (l > b || r < a) {
            return Long.MAX_VALUE;
        }
        push(v, arr);
        if (l >= a && r <= b) {
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        return Math.min(min(2 * v + 1, l, m, a, b, arr), min(2 * v + 2, m + 1, r, a, b, arr));
    }

    long set(int v, int l, int r, int a, int b, long toSet, Node arr[]) {
        push(v, arr);
        if (v > trueN - 1 + n) {
            return Long.MAX_VALUE;
        }
        if (l > b || r < a) {
            return operated(arr[v]);
        }
        if (l >= a && r <= b) {
            if (arr[v].markedToSet) {
                if (arr[v].toSet > toSet) {
                    return operated(arr[v]);
                }
            }
            arr[v].markedToSet = true;
            arr[v].toSet = toSet;
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        arr[v].value = Math.min(set(2 * v + 1, l, m, a, b, toSet, arr), set(2 * v + 2, m + 1, r, a, b, toSet, arr));
        return arr[v].value;
    }


    void push(int i, Node[] arr) {
        if (i > arr.length / 2 - 1) {
            return;
        }
        Node v = arr[i];
        operateNode(v.toSet, v.markedToSet, arr[2 * i + 1]);
        operateNode(v.toSet, v.markedToSet, arr[2 * i + 2]);
        if (v.markedToSet) {
            if (v.toSet > v.value) {
                v.value = v.toSet;
            }
            v.markedToSet = false;
        }
    }

    long operated(Node v) {
        if (v.markedToSet) {
            if (v.value > v.toSet) {
                return v.value;
            } else {
                return v.toSet;
            }
        } else {
            return v.value;
        }
    }

    void operateNode(long toSet, boolean set, Node v) {
        if (set) {
            if (v.toSet < toSet) {
                v.toSet = toSet;
                v.markedToSet = true;
            }
        }

    }

    private void printTree(Node arr[]) {
        System.out.println();
        int w = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(operated(arr[i]) + " ");
            if (i == w - 2) {
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
    }

    class Node {
        long value;
        long toAdd;
        long toSet;
        boolean markedToSet;

        public Node(long value) {
            this.value = value;
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
        public BufferedReader br;
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
        new J().run();
    }
}