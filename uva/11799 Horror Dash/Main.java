import java.io.*;
import java.util.*;


//UVa 11799 - Horror Dash 
class Main {
	
	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		int t = readInt();
		int n, c, max;
		for(int i = 0; i < t; i++){
			n = readInt();
			max = Integer.MIN_VALUE;
			while(n-- > 0){
				c = readInt();
				max = c > max ? c : max;
			}
			System.out.println("Case "+(i+1)+": "+max);
		}
		
	}
	
	static int readInt() throws IOException{
		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return Integer.parseInt(st.nextToken());
	}

}
