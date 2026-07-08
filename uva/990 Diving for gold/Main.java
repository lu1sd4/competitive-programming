import java.io.*;
import java.util.*;

// UVa 990 - Diving for Gold
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
		
		String line;
		int t, w, nt, depth[], time[], value[], dp[][], item;
		Stack<Integer> items;
		while((line = br.readLine()) != null){
			tokenize(line);
			t = nextInt();
			w = nextInt();
			nt = lineAsInt();
			time = new int[nt+1];
			value = new int[nt+1];
			depth = new int[nt+1];
			for(int i = 1; i <= nt; i++){
				readLine();
				depth[i] = nextInt();
				time[i] = 3 * w * depth[i];
				value[i] = nextInt();
			}
			dp = new int[nt+1][t+1];
			for(int i = 0; i <= t; i++)
				dp[0][i] = 0;
			for(int i = 1; i <= nt; i++)
				for(int j = 0; j <= t; j++){
					dp[i][j] = dp[i-1][j];
					if(j >= time[i])
						dp[i][j] = Math.max(dp[i][j], dp[i-1][j-time[i]] + value[i]);
				}
			pw.println(dp[nt][t]);
			items = new Stack<>();
			int j = t;
			for(int i = nt; i > 0; i--)
				if(dp[i][j] > dp[i-1][j]){
					items.push(i);
					j -= time[i];
				}
			pw.println(items.size());
			while(!items.isEmpty()){
				item = items.pop();
				pw.println(depth[item]+" "+value[item]);
			}
			if(br.readLine() == null) break;
			else pw.println();
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
