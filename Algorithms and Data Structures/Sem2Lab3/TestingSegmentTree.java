import java.util.Scanner;

public class TestingSegmentTree {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        SegmentTree tree = new SegmentTree(m);
        for (int i = 0; i < n; i++) {
            String cmd = in.next();
            if (cmd.equals("+")) {
                int l = in.nextInt() - 1;
                int r = in.nextInt() - 1;
                int toAdd = in.nextInt();
                tree.add(l, r, toAdd);
            } else if (cmd.equals("?")) {
                int index = in.nextInt() - 1;
                System.out.println(tree.getByIndex(index));
            } else {
                tree.printEveryIndex();
            }
        }
        in.close();
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

        public long getSum() {
            return operated(arr[0], 0, n - 1);
        }

        public void printEveryIndex() {
            System.out.println();
            for (int i = 0; i < size; i++) {
                System.out.print(getByIndex(i) + " ");
            }
            System.out.println();
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
//            System.out.println("v: " + v + " l: " + l + " r: " + r);
            if (v > n - 1 + size) {
//                System.out.println("case1");
                return 0;
            }
            if (l > b || r < a) {
//                System.out.println("case2");
                return operated(arr[v], l, r);
            }
            if (l >= a && r <= b) {
                if (arr[v].markedToSet) {
                    arr[v].toSet += toAdd;
                } else {
                    arr[v].toAdd += toAdd;
                }
//                System.out.println("case3");
                return operated(arr[v], l, r);
            }
            int m = l + (r - l) / 2;
//            System.out.println("case4");
            arr[v].value = addImpl(2 * v + 1, l, m, a, b, toAdd) + addImpl(2 * v + 2, m + 1, r, a, b, toAdd);
//            System.out.println("index: " + v + " value: " + arr[v].value);
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

}
