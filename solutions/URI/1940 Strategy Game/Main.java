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
		st = new StringTokenizer(br.readLine());
		int j = Integer.parseInt(st.nextToken()), r = Integer.parseInt(st.nextToken());
		int[] scores = new int[j+1];
		st = new StringTokenizer(br.readLine());
		int s;
		for(int i = 0; i < r; i++){
			for(int k = 1; k < scores.length; k++){
				s = Integer.parseInt(st.nextToken());
				scores[k] += s;
			}
		}
		int max = 1;
		for(int i = 1; i < scores.length; i++)
			if(scores[i] >= scores[max]) max = i;
		System.out.println(max);
	}	
	
	
	
}
