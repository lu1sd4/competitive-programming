import java.io.*;
import java.util.*;

public class balloon{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new balloon().solve();
	}
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output"));
		
		String line;
		int n;
		while((line = br.readLine()) != null){
			n = Integer.parseInt(line);
			Set<Integer> cats = new HashSet<>();
			for(int i = 0; i < n; i++){
				int cc = 0;
				char[] linearr = lineAsCharArray();
				for(int j = 0; j < linearr.length; j++)
					cc |= 1 << (linearr[j]-'0');
				cats.add(cc);
			}
			pw.println(cats.size());
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
