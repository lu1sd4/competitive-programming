import java.io.*;
import java.util.*;

// UVa 1241 - Jollybee Tournament
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int ans;
	BitSet bt;
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
				
		int t = lineAsInt(), n, m;
		while(t-- > 0){
			readLine();			
			n = (int)Math.pow(2, nextInt());
			m = nextInt();
			bt = new BitSet(n);
			bt.set(0, n);
			ans = 0;
			readLine();
			while(m-- > 0)
				bt.clear(nextInt()-1);
			wos(0, n-1);
			pw.println(ans);
		}
		
		br.close();
		pw.close();		
	}
	
	boolean wos(int l, int r){
		boolean left, right;
		if(r-l == 1){
			left = bt.get(l);
			right = bt.get(r);
		} else {
			left = wos(l, (l+r)/2);
			right = wos((l+r)/2 + 1, r);
		}
		if(left ^ right)
			ans++;
		return left | right;
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
