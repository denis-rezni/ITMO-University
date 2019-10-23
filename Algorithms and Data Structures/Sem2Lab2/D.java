import java.io.*;
import java.util.*;

public class D {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    Node root;

    public void solve() throws IOException {
        int n = in.nextInt();
        in.nextInt();
        for (int i = 0; i < n; i++) {
            insert(in.nextInt() - 1, i + 1);
//            System.out.println("startTree");
//            printTree(root, 0);
//            System.out.println();
//            System.out.println("endTree");
        }
        out.println(root.count);
        printTreeRes(root);

    }

    public class Node {
        Node left;
        Node right;
        Node parent;
        int value;
        int count;
        int firstNotZeros;

        public Node(int value) {
            this.value = value;
            this.count = 1;
            this.firstNotZeros = (value == 0) ? 0 : 1;
        }


    }

    void setParent(Node child, Node parent) {
        if (child != null) {
            child.parent = parent;
        }
    }

    int sizeOf(Node node) {
        return node == null ? 0 : node.count;
    }

    void recalcCount(Node v) {
        if (v != null)
            v.count = sizeOf(v.left) + sizeOf(v.right) + 1;
    }

    void recalcZeros(Node node) {
        if(node == null){
            return;
        }
        if (node.left == null && node.right == null) {
            node.firstNotZeros = (node.value == 0) ? 0 : 1;
            return;
        }
        if (node.left == null) {
            if (node.value == 0) {
                node.firstNotZeros = 0;
            } else {
                node.firstNotZeros = node.right.firstNotZeros + 1;
            }
            return;
        }
        if (node.right == null) {
            if(node.left.firstNotZeros == node.left.count){
                node.firstNotZeros = node.left.firstNotZeros + ((node.value == 0) ? 0 : 1);
            } else {
                node.firstNotZeros = node.left.firstNotZeros;
            }
            return;
        }

        if (node.left.count == node.left.firstNotZeros) {
            if (node.value == 0) {
                node.firstNotZeros = node.left.count;
            } else {
                node.firstNotZeros = node.left.count + node.right.firstNotZeros + 1;
            }
        } else {
            node.firstNotZeros = node.left.firstNotZeros;
        }
    }

    void rotate(Node parent, Node child) {
        Node grandParent = parent.parent;
        if (grandParent != null) {
            if (grandParent.left == parent) {
                grandParent.left = child;
            } else {
                grandParent.right = child;
            }
        }
        if (parent.left == child) {
            parent.left = child.right;
            child.right = parent;
        } else {
            parent.right = child.left;
            child.left = parent;
        }
        setParent(child.left, child);
        setParent(child.right, child);
        setParent(parent.left, parent);
        setParent(parent.right, parent);
        recalcCount(parent);
        recalcCount(child);
        recalcZeros(parent);
        recalcZeros(child);
        child.parent = grandParent;

    }

    Node splay(Node v) {
        if (v.parent == null) {
            root = v;
            return v;
        }
        Node parent = v.parent;
        Node grandParent = parent.parent;
        if (grandParent == null) {
            rotate(parent, v);
            root = v;
            return v;
        } else {
            if (grandParent.left == parent && parent.left == v) {
                rotate(grandParent, parent);
                rotate(parent, v);
            } else if (grandParent.right == parent && parent.right == v) {
                rotate(grandParent, parent);
                rotate(parent, v);
            } else {
                rotate(parent, v);
                rotate(grandParent, v);
            }
            return splay(v);
        }
    }


    Node merge(Node left, Node right) {
        if (right == null) {
            return left;
        }
        Node cur = right;
        while (cur.left != null) {
            cur = cur.left;
        }
        splay(cur);
        cur.left = left;
        setParent(left, cur);
        if (left != null) {
            cur.count += left.count;
        }
        return cur;
    }

    Node[] split(int index, Node v) {
        Node toSplit = splay(findByIndex(index, v));
        Node left = toSplit.left;
        if (left != null) {
            toSplit.left.parent = null;
            toSplit.left = null;
        }
        recalcCount(left);
        recalcCount(toSplit);
        recalcZeros(left);
        recalcZeros(toSplit);
        return new Node[]{left, toSplit};
    }

    void insertEmpty() {
        Node maxIndex = splay(findByIndex(root.count - 1, root));
        maxIndex.right = new Node(0);
        setParent(maxIndex.right, maxIndex);
        root = splay(maxIndex.right);
    }


    void insert(int index, int value) {
        //0 elements
        if (root == null) {
            if (index == 0) {
                root = new Node(value);
                return;
            } else {
                root = new Node(0);
            }
        }
        //more
        if (index > sizeOf(root) - 1) {
            while (index > sizeOf(root) - 1) {
                insertEmpty();
            }
        }

        Node arr[] = split(index, root);


        if (arr[1].value == 0) {
            arr[1].value = value;
            root = merge(arr[0], arr[1]);
        } else {
            Node toAdd = new Node(value);
            root = merge(merge(arr[0], toAdd), arr[1]);
            deleteNextZero(toAdd);
        }

    }

    void deleteNextZero(Node v){
        Node cur = v;
        while(v.right == null || v.right.firstNotZeros == v.right.count){
            cur = cur.parent;
            if(cur.parent == null){
                break;
            }
        }
        if(cur.parent == null && (cur.right == null || cur.right.count == cur.right.firstNotZeros)){
            return;
        }
        delete(sizeOf(cur.left) + 1 + cur.right.firstNotZeros);
    }

    void delete(int index) {
        Node leftAndRight[] = split(index, root);
        Node toDelAndRight[] = split(1, leftAndRight[1]);
        root = merge(leftAndRight[0], toDelAndRight[1]);
    }


    Node findByIndex(int index, Node v) {
        if (v == null || index == ((v.left == null) ? 0 : v.left.count)) {
            return v;
        } else if (index < ((v.left == null) ? 0 : v.left.count)) {
            return findByIndex(index, v.left);
        } else {
            return findByIndex(index - ((v.left == null) ? 1 : v.left.count + 1), v.right);
        }
    }

    void printTree(Node v, int i) {
        if (v.left != null) {
            printTree(v.left, i + 1);
        }
        if (v.right != null) {
            printTree(v.right, i + 1);
        }
    }

    void printTreeRes(Node v) {
        if (v.left != null) {
            printTreeRes(v.left);
        }
        out.print(v.value + " ");
        if (v.right != null) {
            printTreeRes(v.right);
        }
    }

    void run() {
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

    static class FastScanner {
        public static BufferedReader br;
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
        new D().run();
    }
}