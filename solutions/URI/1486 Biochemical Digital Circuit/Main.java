import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		
		String line;
		int p, n, c, picks[], enabled[];
		while(true){
			st = new StringTokenizer(br.readLine());
			p = tokenasint();
			n = tokenasint();
			c = tokenasint();
			if(p == 0 && n == 0 && c == 0)
				break;
			picks = new int[p];
			enabled = new int[p];
			int ans = 0;
			Arrays.fill(enabled, 1);
			while(n-- > 0){
				line = br.readLine();
				st = new StringTokenizer(line);
				for(int i = 0 ; i < picks.length; i++){
					int x = Integer.parseInt(st.nextToken());
					if(x == 1 && enabled[i] == 1){
						picks[i]++;
						if(picks[i]>=c){
							ans++;
							enabled[i] = 0;
						}
					} else if(x == 0){
						picks[i] = 0;
						enabled[i] = 1;
					}
				}
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
