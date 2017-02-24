import java.io.*;
import java.util.*;

//UVA 488 - Triangle Wave
public class Main {

	static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	 
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int tc = lineToInt();
		while(tc-- > 0){
			nextLine();
			int amp = lineToInt();
			int freq = lineToInt();
			
			String wave = "";
			for(int i = 1; i <= amp; i++){
				for(int j = 0; j < i; j++)
					wave += i;
				wave += "\n";
			}
			for(int i = amp-1; i > 0; i--){
				for(int j = 0; j < i; j++)
					wave += i;
				if(i > 1) wave += "\n";
			}
			
			if(amp == 1)
				wave = "1";
			
			for(int i = 0; i < freq; i++){
				pw.println(wave);
				if(i < freq-1) pw.println();
			}
			
			if(tc > 0) pw.println();
			
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
