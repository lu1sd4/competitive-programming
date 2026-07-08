import java.io.*;
import java.util.*;

//UVa 579 - Clock Hands
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		st = new StringTokenizer(next());
		double a = Integer.parseInt(st.nextToken(":")), ans, 
			   b = Integer.parseInt(st.nextToken());
		while(true){
			
			if(a == 0 && b == 0)
				break;
			
			a += b/60;
			if(a == 12) a = 0;
			a *= 5;
			
			ans = Math.abs(a - b) * 6;
			if (ans > 180) ans = 360 - ans;
			
			System.out.printf("%.3f\n", ans);
			
			st = new StringTokenizer(next());
			a = Integer.parseInt(st.nextToken(":"));
		    b = Integer.parseInt(st.nextToken());
			
		}
	}
	
	static String next() throws IOException{
		while(st == null || !st.hasMoreTokens()){
			try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
		}
		return st.nextToken();
	}
	
	static int nextInt() throws IOException{
		return Integer.parseInt(next());
	}
	
}
