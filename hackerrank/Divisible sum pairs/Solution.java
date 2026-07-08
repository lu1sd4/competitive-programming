import java.io.*;
import java.util.*;

//Hackerrank - Divisible sum pairs
public class Solution {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n = nextInt();
		int k = nextInt();
		int[] a = new int[n];
		int ans = 0;
		for(int i = 0; i < n; i++)
			a[i] = nextInt();
		for(int i = 0; i < n; i++)
			for(int j = i + 1; j < n; j++)
				if((a[i] + a[j]) % k == 0)
					ans++;
		pw.println(ans);
		
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
