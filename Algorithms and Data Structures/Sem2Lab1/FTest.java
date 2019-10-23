import java.util.Arrays;
import java.util.Scanner;

public class FTest {

    public static void main(String args[]){
        Scanner in  = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int u = in.nextInt();
        int v = in.nextInt();
        int arr[] = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = a;
            a = (23 * a + 21563) % 16714589;
        }
        for(int i = 1; i <= m; i++){
            int ans;
            if(u > v){
                ans = Arrays.stream(arr).limit(u).skip(v - 1).min().getAsInt();
            } else {
                ans = Arrays.stream(arr).limit(v).skip(u - 1).min().getAsInt();
            }
            System.out.println("u: " + u + " v: " + v);
            System.out.println(ans);
            u = ((17 * u + 751 + ans + 2 * i) % n) + 1;
            v = ((13 * v + 593 + ans + 5 * i) % n) + 1;
        }

    }
}
