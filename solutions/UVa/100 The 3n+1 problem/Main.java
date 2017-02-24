import java.io.*;
import java.util.*;

//UVA 100 - The 3n+1 problem
public class Main {

	static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	 
	int cache[] = new int[1000001];
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		cache[1] = 1;
		
		String line;
		int l,r;
		while((line = br.readLine()) != null){
			tokenize(line);
			l = readInt();
			r = readInt();
			int step = l < r ? 1 : -1;
			int maxcyclelength = Integer.MIN_VALUE;
			for(int i = l; i != r + step; i += step){
				int n = i;
				int cyclelength = getcyclelength(n);
				cache[i] = cyclelength;
				maxcyclelength = Math.max(cyclelength, maxcyclelength);
			}
			pw.println(l+" "+r+" "+maxcyclelength);
		}
		
		br.close();
		pw.close();
		
	}
	
	int getcyclelength(int n){
		if(n <= 1000000 && cache[n] != 0)
			return cache[n];
		if(n % 2 != 0)
			return getcyclelength(3*n + 1) + 1;
		return getcyclelength(n/2) + 1;	
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
