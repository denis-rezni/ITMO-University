import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TwentyThree {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = false;

    public void solve() throws IOException {
        int arr[] = Arrays.stream(in.next().split("")).mapToInt(Integer::parseInt).toArray();
        int n = arr.length;
        out.println(prevVec(arr.clone(), n));
        out.print(nextVec(arr.clone(), n));
    }

    private String nextVec(int arr[], int n) {
        int pointer = n - 1;
        while (pointer > -2) {
            if (pointer == -1) {
                return "-";
            }
            if (arr[pointer] == 1) {
                arr[pointer] = 0;
                pointer--;
            } else {
                arr[pointer] = 1;
                break;
            }
        }
        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    private String prevVec(int arr[], int n) {
        int pointer = n - 1;
        while (pointer > -2) {
            if (pointer == -1) {
                return "-";
            }
            if (arr[pointer] == 0) {
                arr[pointer] = 1;
                pointer--;
            } else {
                arr[pointer] = 0;
                break;
            }
        }
        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining());
    }

    public String toBinary(int i, int n) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < n - Integer.toBinaryString(i).length(); j++) {
            s.append("0");
        }
        s.append(Integer.toBinaryString(i));
        return s.toString();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("nextvector.in"));
                out = new PrintWriter(new File("nextvector.out"));
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
        new TwentyThree().run();
    }
}
