import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] fib;
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		
		fib = new int[40][2];
		fib[0] = new int[]{0,1};
		fib[1] = new int[]{1,1};
		
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0){
			int x = Integer.parseInt(br.readLine());
			int ans[] = fib(x);
			System.out.println("fib("+x+") = "+(ans[1]-1)+" calls = "+ans[0]);
		}
		
	}	
	
	static int[] fib(int n){
		
		if(fib[n][1] != 0) return fib[n];
		
		int[] f1 = fib(n-1);
		int[] f2 = fib(n-2);
		
		
		fib[n] = new int[]{f1[0]+f2[0], 1+f1[1]+f2[1]};
		
		return fib[n];
		
	}
	
	
	
}
