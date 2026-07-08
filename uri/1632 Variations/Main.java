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
		int ans;
		while(t-- > 0){
			String line = br.readLine();
			ans = 1;
			for(char x : line.toCharArray()){
				if(x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I' || x == 'o' || x == 'O' || x == 's' || x == 'S')
					ans *= 3;
				else
					ans *= 2;
			}
			System.out.println(ans);
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
