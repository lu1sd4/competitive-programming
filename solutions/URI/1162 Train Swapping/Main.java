import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		int t = Integer.parseInt(br.readLine());
		int[] arr;
		while(t-- > 0){
			int l = Integer.parseInt(br.readLine());
			arr = new int[l];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < arr.length; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			int ans = 0;
			boolean sorted = false;
			while(!sorted){
				sorted = true;
				for(int i = 0; i < arr.length-1; i++){
					if(arr[i] > arr[i+1]){
						ans++;
						int a = arr[i];
						arr[i] = arr[i+1];
						arr[i+1] = a;
						sorted = false;
					}
				}
			}
			System.out.println("Optimal train swapping takes "+ans+" swaps.");
		}
		
	}
	
	static int lineasint() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	
	static int tokenasint() throws IOException{
		return Integer.parseInt(st.nextToken());
	}
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	static void nextLine() throws IOException{
		st = new StringTokenizer(br.readLine());
	}
	
}
