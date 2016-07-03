import java.io.*;
import java.util.*;

//HackerRank - Save the prisoner!
public class Solution {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int t = nextInt();
		int n, m, s;
		while(t-- > 0){
			
			n = nextInt();
			m = nextInt();
			s = nextInt();
			
			int r = (m + s - 1) % n;
			if(r == 0) r = n;
			
			pw.println(r);
			
			
		}
		
		br.close();
		pw.close();
		
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
