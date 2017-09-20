import java.io.*;
import java.util.*;

// UVa 562 - Dividing coins
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}

	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int n = lineAsInt(), v[], m, maxm, sum;
		boolean dp[][];
		while(n-- > 0){
			m = lineAsInt();
			v = new int[m+1];
			sum = 0;
			readLine();
			for(int i = 1; i <= m; i++){
				v[i] = nextInt();
				sum += v[i];
			}
			if(m == 1)
				pw.println(v[m]);
			else if(m == 0)
				pw.println("0");
			else {
				maxm = sum/2;
				// dp[i][j] - can reach [i] money by using up to [j] first coins?
				dp = new boolean[maxm+1][m+1];
				// dp[0][i] = true - can always reach 0 money
				for(int i = 0; i < m; i++)
					dp[0][i] = true;
				// dp[i][0] = false - cant reach money without using coins
				for(int i = 1; i <= maxm; i++){
					for(int j = 1; j <= m; j++){
						dp[i][j] = dp[i][j-1];
						if(i >= v[j])
							dp[i][j] |= dp[i-v[j]][j-1];
					}
				}
				for(int i = maxm; i >= 0; i--)
					if(dp[i][m]){
						pw.println((int)Math.abs(sum-i-i));
						break;
					}
			}
		}
		
		br.close();
		pw.close();
	}
	
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineAsInt() throws IOException{
    	return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }
    
    char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }

    long nextLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int nextInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void readLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
    
    void skipLine() throws Exception{
    	br.readLine();
    }
	
}
