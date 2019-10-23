import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class ForTests {
    public static void main(String args[]) {
        int arr1 [][] = new int[5][5];
        Random r = new Random(4);
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                arr1[i][j] = r.nextInt(5);
            }
        }
        Arrays.sort(arr1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for(int i = 0; i < o1.length; i++){
                    if(o1[i] > o2[i]){
                        return 1;
                    } else if(o1[i] < o2[i]){
                        return -1;
                    }
                }
                return 0;
            }
        });
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(arr1[i]));
        }


    }
}
