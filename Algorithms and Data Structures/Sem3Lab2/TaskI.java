import java.util.*;

public class TaskI {
    void solve() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            solveTest(in);
            System.out.println();
        }
        in.close();
    }

    void solveTest(Scanner in) {
        int n = in.nextInt();
        int m = in.nextInt();
        List<List<Integer>> graphIn = new ArrayList<>();
        List<List<Integer>> graphOut = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graphIn.add(new ArrayList<>());
            graphOut.add(new ArrayList<>());
        }
        Set<Edge> edges = new HashSet<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            if (!edges.contains(new Edge(from, to))) {
                graphOut.get(from).add(to);
                graphIn.get(to).add(from);
            }
            edges.add(new Edge(from, to));
        }

        List<Integer> sinks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graphOut.get(i).isEmpty()) {
                sinks.add(i);
            }
        }
        int[] state = new int[n];
        Arrays.fill(state, 2);
        for (int i = 0; i < sinks.size(); i++) {
            state[sinks.get(i)] = 0;
        }
        //dfs from all the sinks
        for (int i = 0; i < sinks.size(); i++) {
            dfs(sinks.get(i), graphIn, graphOut, state);
        }
        for (int i = 0; i < n; i++) {
            switch (state[i]) {
                case (2):
                    System.out.println("DRAW");
                    break;
                case (1):
                    System.out.println("FIRST");
                    break;
                case (0):
                    System.out.println("SECOND");
                    break;
            }
        }

    }

    class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge)) return false;
            Edge edge = (Edge) o;
            return from == edge.from &&
                    to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    //state - 2 = unknown, 1 = winning, 0 = losing
    void dfs(int v, List<List<Integer>> graphIn, List<List<Integer>> graphOut, int[] state) {
        if (state[v] == 2) {
            int winningCount = 0;
            for (int u : graphOut.get(v)) {
                if (state[u] == 0) {
                    state[v] = 1;
                    break;
                } else if (state[u] == 1) {
                    winningCount++;
                }
            }
            if (winningCount == graphOut.get(v).size()) {
                state[v] = 0;
            }

            if (state[v] == 2) {
                return;
            }
        }

        //we know something, we go further
        for (int u : graphIn.get(v)) {
            if (state[u] == 2) {
                dfs(u, graphIn, graphOut, state);
            }
        }
    }

    public static void main(String[] args) {
        new TaskI().solve();
    }
}
