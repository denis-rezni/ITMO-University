import java.io.*;
import java.util.*;

public class K {
    FastScanner in;
    PrintWriter out;
    boolean systemIO = true;
    int trueN;

    public void solve() throws IOException {
        int n = in.nextInt();
        trueN = 1;
        while (trueN < n) {
            trueN *= 2;
        }
        int m = in.nextInt();
        int arr[] = new int[trueN * 2 - 1];
        Arrays.fill(arr, 0, trueN - 1 + n, 1);

        for(int i = trueN - 2; i >= 0; i--){
            arr[i] = arr[2 * i + 1] + arr[2 * i + 2];
        }

        for (int i = 0; i < m; i++) {
//            printTree(arr);
            if (in.next().equals("enter")) {
                int place = in.nextInt() - 1;
                if(isFree(place, arr)){
                    setX(place, 0, arr);
                    out.println(place + 1);
                } else {
                    int freePlacesBeforeCurrent = sum(0, 0, trueN - 1, 0, place, arr);
                    int allPlaces = arr[0];
                    int newPlace;
                    if(freePlacesBeforeCurrent + 1 > allPlaces){
                        newPlace = findKthFreePlace(1, arr);
                    } else {
                        newPlace = findKthFreePlace(freePlacesBeforeCurrent + 1, arr);
                    }
                    setX(newPlace, 0, arr);
                    out.println(newPlace + 1);
                }
            } else {
                setX(in.nextInt() - 1, 1, arr);
            }


        }

    }

    int findKthFreePlace(int k, int arr[]){
        int i = 0;
        while(i * 2 + 1 < trueN * 2 - 1){
            if(k > arr[2 * i + 1]){
                k -= arr[2 * i + 1];
                i = 2 * i + 2;
            } else {
                i = 2 * i + 1;
            }
        }
        return i - trueN + 1;
    }

    int sum(int v, int l, int r, int a, int b, int arr[]) {
        if (l > b || r < a) {
            return 0;
        }
        if (l >= a && r <= b) {
            return arr[v];
        }
        int m = l + (r - l) / 2;
        return sum(v * 2 + 1, l, m, a, b, arr) + sum(v * 2 + 2, m + 1, r, a, b, arr);
    }

    boolean isFree(int i, int arr[]){
        return arr[trueN - 1 + i] == 1;
    }


    void setX(int i, int x, int arr[]) {
        i = trueN - 1 + i;
        arr[i] = x;
        while (i != 0) {
            i = (i - 1) / 2;
            arr[i] = arr[2 * i + 1] + arr[2 * i + 2];
        }
    }


    private void printTree(int arr[]) {
        System.out.println();
        int w = 2;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if (i == w - 2) {
                w *= 2;
                System.out.println();
            }
        }
        System.out.println();
    }


    public void run() {
        try {
            if (systemIO) {
                in = new FastScanner(System.in);
                out = new PrintWriter(System.out);
            } else {
                in = new FastScanner(new File("parking.in"));
                out = new PrintWriter(new File("parking.out"));
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
        new K().run();
    }
}