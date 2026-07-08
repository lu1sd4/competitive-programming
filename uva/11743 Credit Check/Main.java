import java.io.*;
import java.util.*;

// UVa 11743 - Credit Check
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
		
		int n = lineAsInt();
		char[] line;
		int sum, ix;
		while(n-- > 0){
			line = lineAsCharArray();
			sum = 0; ix = 0;
			for(int i = 0; i < line.length; i++){
				if(line[i] == ' ') continue;
				if(ix++%2 == 0)
					sum += addDigits((line[i]-'0')*2);
				else
					sum += line[i]-'0';
			}
			pw.println(sum%10 == 0 ? "Valid" : "Invalid");
		}
		
		br.close();
		pw.close();
		
	}
	
	int addDigits(int n){
		if(n < 10)
			return n;
		return n%10 + addDigits(n/10);
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
