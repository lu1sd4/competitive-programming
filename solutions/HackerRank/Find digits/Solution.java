import java.io.*;
import java.util.*;

//Hackerrank - Find Digits

public class Solution {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int t = nextInt();
		int n, num, ans;
		while(t-- > 0){
			num = n = nextInt();
			ans = 0;
			while(num > 0){
				int digit = num % 10;
				if(digit != 0)
					if(n % digit == 0)
						ans++;
				num /= 10;
			}
			pw.println(ans);
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
