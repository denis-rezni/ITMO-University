import java.io.*;
import java.util.*;

public class G {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int n = 524288;
    int delta = 200000;

    public void solve() throws IOException {
        int windows = in.nextInt();
        Window[] reqsStart = new Window[windows];
        for (int i = 0; i < windows; i++) {
            reqsStart[i] = new Window(in.nextInt() + delta, in.nextInt() + delta,
                    in.nextInt() + delta, in.nextInt() + delta);
        }
        Arrays.sort(reqsStart, Comparator.comparingInt(o -> o.x1));
        Window[] reqsEnd = reqsStart.clone();
        Arrays.sort(reqsEnd, Comparator.comparingInt(o -> o.x2));

        Node arr[] = new Node[2 * n - 1];
        for (int i = 0; i < 2 * n - 1; i++) {
            arr[i] = new Node(0);
        }
        int startPointer = 0;
        int endPointer = 0;
        int maxWindows = 0;
        int maxX = -1;
        int maxY = -1;
        for (int i = 0; i < 400_001; i++) {
            while (startPointer < windows && reqsStart[startPointer].x1 == i) {
                add(0, 0, n - 1, reqsStart[startPointer].y1, reqsStart[startPointer].y2, 1, arr);
                startPointer++;
            }

            if (arr[0].value > maxWindows) {
                maxWindows = arr[0].value;
                maxX = i;
                maxY = findMaxIndex(maxWindows, arr);
            }
            while(endPointer < windows && reqsEnd[endPointer].x2 == i){
                add(0, 0, n - 1, reqsEnd[endPointer].y1, reqsEnd[endPointer].y2, -1, arr);
                endPointer++;
            }
        }
        System.out.println(maxWindows);
        System.out.println((maxX - 200000) + " " + (maxY - 200000));
    }

    class Window {
        int x1;
        int x2;
        int y1;
        int y2;

        public Window(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    int findMaxIndex(int max, Node arr[]) {
        int v = 0;
        while (v < n - 1) {
            push(v, arr);
            if (operated(arr[2 * v + 1]) == max) {
                v = 2 * v + 1;
            } else {
                v = 2 * v + 2;
            }
        }
        return v - n + 1;
    }


    int add(int v, int l, int r, int a, int b, long toAdd, Node arr[]) {
        push(v, arr);
        if (v > n - 1 + n) {
            return Integer.MIN_VALUE;
        }
        if (l > b || r < a) {
            return operated(arr[v]);
        }
        if (l >= a && r <= b) {
            if (arr[v].markedToSet) {
                arr[v].toSet += toAdd;
            } else {
                arr[v].toAdd += toAdd;
            }
            return operated(arr[v]);
        }
        int m = l + (r - l) / 2;
        arr[v].value = Math.max(add(2 * v + 1, l, m, a, b, toAdd, arr), add(2 * v + 2, m + 1, r, a, b, toAdd, arr));
        return arr[v].value;
    }


    void push(int i, Node[] arr) {
        if (i > arr.length / 2 - 2) {
            return;
        }
        Node v = arr[i];
        operateNode(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 1]);
        operateNode(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 2]);
        if (v.markedToSet) {
            v.value = v.toSet;
            v.markedToSet = false;
            v.toAdd = 0;
        } else {
            v.value += v.toAdd;
            v.toAdd = 0;
        }
    }

    int operated(Node v) {
        if (v.markedToSet) {
            return v.toSet;
        } else {
            return v.value + v.toAdd;
        }
    }

    void operateNode(int toAdd, int toSet, boolean set, Node v) {
        if (set) {
            v.toSet = toSet;
            v.markedToSet = true;
            v.toAdd = 0;
        } else {
            if (v.markedToSet) {
                v.toSet += toAdd;
            } else {
                v.toAdd += toAdd;
            }
        }

    }

    private void printTree(Node arr[]) {
        System.out.println();
        int w = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i].value + " ");
            if (i == w - 2) {
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
    }

    class Node {
        int value;
        int toAdd;
        int toSet;
        boolean markedToSet;

        public Node(int value) {
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
        new G().run();
    }
}