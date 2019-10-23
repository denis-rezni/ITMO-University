import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwentySix {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;

    private void solve() throws IOException {
        int n;
        int k;
        String first;
        while ((first = in.nextLine()) != null) {
            int numbers[] = Arrays.stream(first.split(" ")).mapToInt(Integer::parseInt).toArray();
            n = numbers[0];
            if (n == 0) {
                break;
            }
            k = numbers[1];
            List<List<Integer>> part = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                part.add(Arrays.stream(in.nextLine().split(" "))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList()));
            }
//            for(int i = 0; i < part.size(); i++){
//                for(int j = 0; j < part.get(i).size(); j++){
//                    System.out.print(part.get(i).get(j) + " ");
//                }
//                System.out.println();
//            }
            out.println(nextPart(part, n));
            in.nextLine();
        }
    }

    private String nextPart(List<List<Integer>> part, int n) {
        List<Integer> was = new ArrayList<>();
        boolean changed = false;
        for (int i = part.size() - 1; i >= 0; i--) {
            if (!was.isEmpty() && was.get(was.size() - 1) > part.get(i).get(part.get(i).size() - 1)) {
                part.get(i).add(was.get(was.size() - 1));
                was.remove(was.size() - 1);
                break;
            }
            int lastJ = part.get(i).size() - 1;
            for (int j = part.get(i).size() - 1; j >= 0; j--) {
                if (!was.isEmpty() && j != 0 && was.get(was.size() - 1) > part.get(i).get(j)) {
                    part.get(i).set(j, was.get(was.size() - 1));//NOT REALLY SURE
                    changed = true;
                    lastJ = j;
                    break;
                }
            }
            if (changed) {
                break;
            }
            was.add(part.get(i).get(lastJ));
            part.get(i).remove(lastJ);
        }
        Collections.sort(was);
        for (int i = 0; i < was.size(); i++) {
            part.add(Collections.singletonList(was.get(i)));
        }
        StringBuilder res = new StringBuilder();
        res.append(n).append(" ").append(part.size()).append("\n");
        for (int i = 0; i < part.size(); i++) {
            for (int j = 0; j < part.get(i).size(); j++) {
                res.append(part.get(i).get(j)).append(" ");
            }
            res.append("\n");
        }
        return res.toString();
    }

    private void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextsetpartition.in"));
                out = new PrintWriter(new File("nextsetpartition.out"));
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
        new TwentySix().run();
    }
}
