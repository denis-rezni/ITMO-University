import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] count = new int[101];
		while(in.hasNextInt()) {
			count[in.nextInt()]++;
		}
		for(int i = 0; i < count.length; i++) {
			if(count[i] != 0) {
				for(int j = 0; j < count[i]; j++) {
					System.out.print(i + " ");
				}
			}
		}
		in.close();
	}

}
