import java.io.*;
import java.util.*;

public class TaskG {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int parents[];
    List<List<Integer>> children;
    int lcadp[][];
    int treeCounter;
    int vertexToTree[];
    int treeIndex[];
    List<Integer> pathFather;

    public void solve() {
        int n = in.nextInt();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        parents = new int[n];
        children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        boolean visited[] = new boolean[n];
        buildTree(0, graph, visited);

        int depths[] = new int[n];
        findDepth(0, depths, 0, children);
        int logn = log2(n);
        lcadp = new int[n][logn + 1];

        for (int v = 0; v < n; v++) {
            lcadp[v][0] = parents[v];
        }

        for (int i = 1; i < logn + 1; i++) {
            for (int v = 0; v < n; v++) {
                lcadp[v][i] = lcadp[lcadp[v][i - 1]][i - 1];
            }
        }


        int weights[] = new int[n];
        countWeights(0, weights);


        vertexToTree = new int[n];
        treeIndex = new int[n];
        treeCounter = 0;
        pathFather = new ArrayList<>();
        pathFather.add(0);
        buildHLD(0, weights, 0);

        List<SegmentTree> hld = new ArrayList<>();
        int segmentTreeWeights[] = new int[treeCounter];
        for (int i = 0; i < n; i++) {
            segmentTreeWeights[vertexToTree[i]]++;
        }
        for (int i = 0; i < treeCounter; i++) {
            hld.add(new SegmentTree(segmentTreeWeights[i]));
        }
        int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            String cmd = in.next();
            if (cmd.equals("+")) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int toAdd = in.nextInt();
                int lca = lca(u, v, depths, logn);
                addFromTo(u, lca, hld, toAdd, true);
                addFromTo(v, lca, hld, toAdd, false);
            } else {
                int x = in.nextInt() - 1;
                out.println(query(x, hld));
            }

        }


    }

    long query(int x, List<SegmentTree> hld) {
        SegmentTree tree = hld.get(vertexToTree[x]);
        return tree.getByIndex(treeIndex[x]);
    }

    void addFromTo(int u, int v, List<SegmentTree> hld, int toAdd, boolean withAnc) {
        //v is the ancestor
        int curTreeIndex = vertexToTree[u];
        SegmentTree curTree;
        while (curTreeIndex != vertexToTree[v]) {
            curTree = hld.get(curTreeIndex);
            curTree.add(0, treeIndex[u], toAdd);
            u = pathFather.get(curTreeIndex);
            curTreeIndex = vertexToTree[u];
        }
        curTree = hld.get(curTreeIndex);
        if (withAnc) {
            curTree.add(treeIndex[v], treeIndex[u], toAdd);
        } else {
            curTree.add(treeIndex[v] + 1, treeIndex[u], toAdd);
        }
    }

    void buildHLD(int v, int weights[], int index) {
        vertexToTree[v] = treeCounter;
        treeIndex[v] = index;
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < children.get(v).size(); i++) {
            if (weights[children.get(v).get(i)] > max) {
                max = weights[children.get(v).get(i)];
                maxIndex = children.get(v).get(i);
            }
        }
        if (max != -1) {
            buildHLD(maxIndex, weights, index + 1);
        } else {
            treeCounter++;
            return;
        }
        for (int i = 0; i < children.get(v).size(); i++) {
            if (children.get(v).get(i) != maxIndex) {
                pathFather.add(v);
                buildHLD(children.get(v).get(i), weights, 0);
            }
        }
    }


    int countWeights(int v, int weights[]) {
        int counter = 1;
        for (int i = 0; i < children.get(v).size(); i++) {
            counter += countWeights(children.get(v).get(i), weights);
        }
        weights[v] = counter;
        return counter;
    }

    void buildTree(int v, List<List<Integer>> graph, boolean visited[]) {
        visited[v] = true;
        for (int i = 0; i < graph.get(v).size(); i++) {
            int kid = graph.get(v).get(i);
            if (!visited[kid]) {
                parents[kid] = v;
                children.get(v).add(kid);
                buildTree(kid, graph, visited);
            }
        }
    }

    int log2(int x) {
        int res = 1;
        int counter = 0;
        while (res < x) {
            res *= 2;
            counter++;
        }
        return counter;
    }

    void findDepth(int v, int[] depth, int level, List<List<Integer>> children) {
        depth[v] = level;
        for (int i = 0; i < children.get(v).size(); i++) {
            findDepth(children.get(v).get(i), depth, level + 1, children);
        }
    }

    int lca(int u, int v, int[] depths, int logn) {

        if (depths[v] > depths[u]) {
            int temp = u;
            u = v;
            v = temp;
        }
        //now u is deeper
        int h = depths[u] - depths[v];
        for (int i = logn; i >= 0; i--) {
            if (1 << i <= h) {
                h -= 1 << i;
                u = lcadp[u][i];
            }
        }
        if (v == u) {
            return (v);
        }

        for (int i = logn; i >= 0; i--) {
            if (lcadp[u][i] != lcadp[v][i]) {
                u = lcadp[u][i];
                v = lcadp[v][i];
            }
        }
        return (parents[u]);

    }


    public static class SegmentTree {
        private int n;
        public int size;
        private Node arr[];

        public SegmentTree(int n) {
            size = n;
            int counter = 1;
            while (counter < n) {
                counter *= 2;
            }
            this.n = counter;
            arr = new Node[2 * this.n - 1];
            for (int i = 0; i < 2 * this.n - 1; i++) {
                arr[i] = new Node();
            }
        }

        public long getByIndex(int x) {
            return sum(0, 0, n - 1, x, x);
        }

        public long sum(int v, int l, int r, int a, int b) {
            if (v >= size - 1 + n) {
                return 0;
            }
            if (l > b || r < a) {
                return 0;
            }
            push(v, l, r);
            if (l >= a && r <= b) {
                return operated(arr[v], l, r);
            }
            int m = l + (r - l) / 2;
            return sum(2 * v + 1, l, m, a, b) + sum(2 * v + 2, m + 1, r, a, b);
        }


        public void add(int l, int r, long toAdd) {
            addImpl(0, 0, n - 1, l, r, toAdd);
        }

        private long addImpl(int v, int l, int r, int a, int b, long toAdd) {
            push(v, l, r);
            if (v > n - 1 + size) {
                return 0;
            }
            if (l > b || r < a) {
                return operated(arr[v], l, r);
            }
            if (l >= a && r <= b) {
                if (arr[v].markedToSet) {
                    arr[v].toSet += toAdd;
                } else {
                    arr[v].toAdd += toAdd;
                }
                return operated(arr[v], l, r);
            }
            int m = l + (r - l) / 2;
            arr[v].value = addImpl(2 * v + 1, l, m, a, b, toAdd) + addImpl(2 * v + 2, m + 1, r, a, b, toAdd);
            return arr[v].value;
        }

        public void set(int l, int r, int toSet) {
            setImpl(0, 0, n - 1, l, r, toSet);
        }

        private long setImpl(int v, int l, int r, int a, int b, long toSet) {
            push(v, l, r);
            if (v > n - 1 + size) {
                return 0;
            }
            if (l > b || r < a) {
                return operated(arr[v], l, r);
            }
            if (l >= a && r <= b) {
                arr[v].markedToSet = true;
                arr[v].toSet = toSet;
                arr[v].toAdd = 0;
                return operated(arr[v], l, r);
            }
            int m = l + (r - l) / 2;
            arr[v].value = setImpl(2 * v + 1, l, m, a, b, toSet) + setImpl(2 * v + 2, m + 1, r, a, b, toSet);
            return arr[v].value;
        }


        void push(int i, int l, int r) {
            if (i >= n - 1) {
                return;
            }
            Node v = arr[i];
            pushChanges(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 1]);
            pushChanges(v.toAdd, v.toSet, v.markedToSet, arr[2 * i + 2]);
            if (v.markedToSet) {
                v.value = (r - l + 1) * v.toSet;
                v.markedToSet = false;
                v.toAdd = 0;
            } else {
                v.value += v.toAdd * (r - l + 1);
                v.toAdd = 0;
            }
        }

        long operated(Node v, int l, int r) {
            if (v.markedToSet) {
                return (r - l + 1) * v.toSet;
            } else {
                return v.value + v.toAdd * (r - l + 1);
            }
        }

        void pushChanges(long toAdd, long toSet, boolean set, Node v) {
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
            long value;
            long toAdd;
            long toSet;
            boolean markedToSet;

            public Node(long value) {
                this.value = value;
            }

            public Node() {
            }
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
        new TaskG().run();
    }
}