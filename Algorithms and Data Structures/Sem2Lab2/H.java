import java.io.*;
import java.util.*;

public class H {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    Node root;

    public void solve() throws IOException {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            if(cmd.equals("+")){
                insert(in.nextInt() - 1, in.nextInt(), in.next().charAt(0) - 97);
            } else if(cmd.equals("-")){
                remove(in.nextInt() - 1, in.nextInt());
            } else {
                out.println(query(in.nextInt() - 1, in.nextInt() - 1));
            }
//            System.out.println("start tree");
//            System.out.println("i: " + i);
//            printTree(root);
//            System.out.println();
//            System.out.println("end tree");
        }


    }

    public class Node {
        Node left;
        Node right;
        Node parent;
        int letter;
        int numberOfLetters;
        int count;
        int mask;


        public Node(int letter, int number) {
            this.letter = letter;
            this.numberOfLetters = number;
            this.count = number;
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
        if (v != null) {
            v.count = sizeOf(v.left) + sizeOf(v.right) + v.numberOfLetters;
        }
    }

    int maskOf(Node v) {
        return (v == null) ? 0 : v.mask;
    }

    void recalcMask(Node v) {
        if (v != null) {
            v.mask = maskOf(v.left) | maskOf(v.right) | (1 << v.letter);
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
        recalcMask(parent);
        recalcMask(child);
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
        recalcCount(cur);
        recalcMask(cur);
        return cur;
    }

    Node[] split(int index, Node v) {
        if (index >= v.count) {
            return new Node[]{root, null};
        }
        Node toSplit = splay(findByIndex(index, v));
        Node left = toSplit.left;
        boolean withParent = false;
        if (left != null) {
            if(index == left.count){
                toSplit.left = null;
                left.parent = null;
            } else {
                Node newParent = new Node(toSplit.letter, index - left.count);
                left.parent = newParent;
                newParent.left = left;
                toSplit.left = null;
                toSplit.numberOfLetters -= (index - left.count);
                withParent = true;
            }
        } else {
            left = new Node(toSplit.letter, index);
            toSplit.numberOfLetters -= index;
        }
        recalcCount(left);
        recalcCount(toSplit);
        recalcMask(left);
        recalcMask(toSplit);
        if (!withParent) {
            return new Node[]{left, toSplit};
        } else {
            recalcCount(left.parent);
            recalcMask(left.parent);
            return new Node[]{left.parent, toSplit};
        }
    }


    void insert(int index, int number, int letter) {
        if (root == null) {
            root = new Node(letter, number);
            return;
        }
        Node arr[] = split(index, root);
        Node toAdd = new Node(letter, number);
        root = merge(merge(arr[0], toAdd), arr[1]);

    }

    void remove(int index, int number){
        Node leftAndRight[] = split(index, root);
//        System.out.println("left tree");
//        printTree(leftAndRight[0]);
//        System.out.println();
//        System.out.println("right tree");
//        printTree(leftAndRight[1]);
//        System.out.println();
        Node toDelAndRight[] = split(number, leftAndRight[1]);
//        System.out.println("toDel");
//        printTree(toDelAndRight[0]);
//        System.out.println();
        root = merge(leftAndRight[0], toDelAndRight[1]);
    }

    int query(int l, int r){
        Node leftAndNeeded[] = split(l, root);
//        System.out.println("first tree");
//        printTree(leftAndNeeded[0]);
//        System.out.println();
//        System.out.println("first-second tree");
//        printTree(leftAndNeeded[1]);
//        System.out.println();
        Node neededAndRight[] = split(r + 1 - l, leftAndNeeded[1]);
//        System.out.println("second tree");
//        printTree(neededAndRight[0]);
//        System.out.println();
//        System.out.println("third tree");
//        printTree(neededAndRight[1]);
//        System.out.println();
        int res = Integer.bitCount(neededAndRight[0].mask);
        root = merge(leftAndNeeded[0], merge(neededAndRight[0], neededAndRight[1]));
        return res;
    }


    Node findByIndex(int index, Node v) {
//        System.out.println("index: " + index + " left: " + sizeOf(v.left) + " num " + v.numberOfLetters);
        if (v == null || (index >= sizeOf(v.left) && index < sizeOf(v.left) + v.numberOfLetters)) {
            return v;
        } else if (index < sizeOf(v.left)) {
            return findByIndex(index, v.left);
        } else {
            return findByIndex(index - sizeOf(v.left) - v.numberOfLetters, v.right);
        }
    }


    void printTree(Node v) {
        if (v.left != null) {
            printTree(v.left);
        }
//        if(v == root){
//            System.out.print("!!!");
//        }
        for(int i = 0; i < v.numberOfLetters; i++){
            System.out.print(v.letter + " ");
        }
//        if(v == root){
//            System.out.print("!!!");
//        }
        if (v.right != null) {
            printTree(v.right);
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
        new H().run();
    }
}