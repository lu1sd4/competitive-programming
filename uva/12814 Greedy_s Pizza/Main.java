import java.io.*;
import java.util.*;

// UVa 12814 - Greedy's Pizza
public class Main{
	
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
		//pw = new PrintWriter(new File("src/output"));
		
		double result[];
		int c = 0;
		String line;
		while((line = br.readLine()) != null){
			tokenize(line);
			c = 0;
			result = new double[7];
			for(int i = 0; i < result.length; i++){
				if(next().equals("1")){
					c++;
					result[(i+1)%7] += 0.6;
					result[(i+2)%7] += 0.4;
				}
			}
			if(c > 0) result[0] /= c;
			pw.printf(Locale.ROOT, "%.5f", result[0]);			
			for(int i = 1; i < result.length; i++){
				if(c > 0) result[i] /= c;
				pw.printf(Locale.ROOT, " %.5f", result[i]);
			}
			pw.println();
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
