import java.io.*;
import java.util.*;

public class dpa01 {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new dpa01().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);

		int maxn = 1001;
		int[] res = new int[maxn];
		Arrays.fill(res, 1);
		for(int i = 2; i <= maxn/2; i++)
			for(int j = i*2; j < maxn; j+=i)
				res[j] += i;
		
		int t = lineAsInt(), n;
		while(t-- > 0){
			n = lineAsInt();
			if(res[n] < n)
				pw.println("deficient");
			else if(res[n] == n)
				pw.println("perfect");
			else
				pw.println("abundant");
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
