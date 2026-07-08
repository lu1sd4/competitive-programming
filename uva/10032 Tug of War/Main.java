import java.io.*;
import java.util.*;

// UVa 10032 - Tug of War
public class Main {
	
	BufferedReader br;
	StringTokenizer st = new StringTokenizer("");
	PrintWriter pw;
	
	public static void main(String[] args) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int weights[];
		long dp[];
		
		int tc = lineToInt();
		for(int t = 1; t <= tc; t++){
			br.readLine();
			int n = lineToInt(), sum = 0;
			weights = new int[102];
			for(int i = 0; i < n; i++){
				weights[i] = lineToInt();
				sum += weights[i];
			}
			
			int hs = sum/2;
			
			dp = new long[hs+1];
			dp[0] = 1L;
			
			for(int i = 0; i < n; i++)
				for(int j = sum/2; j >= weights[i]; j--)
					dp[j] |= dp[j - weights[i]]<<1L;

			int hn = n/2;
			
			if(n%2 == 1)
	            while(dp[hs] << ~hn >= 0 && dp[hs] << ~(hn+1) >= 0)
	                hs--;
	        else
	            while(dp[hs] << ~hn >= 0)
	            	hs--;
			
			pw.println(hs+" "+(sum-hs));
			if(t <= tc-1) pw.println();
		}
		
		br.close();
		pw.close();
		
	}
	
	char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineToInt() throws IOException{
        return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }

    long readLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int readInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    void nextLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
	
}