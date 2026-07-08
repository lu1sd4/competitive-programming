import java.io.*;
import java.util.*;
import java.math.*;

//UVA 10161 - Ant on a Chessboard
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
		
		int n;
		while((n = lineToInt()) != 0){
			int b = (int)(Math.ceil(Math.sqrt(n)));
			int a = b - 1;
			int top = b*b;
			int bottom = a*a + 1;
			int half = bottom + (top-bottom)/2;
			int coord1 = b;
			int coord2 = coord1;
			if(n > half)
				coord2 = top - n + 1;
			else if(n < half)
				coord2 = n - bottom + 1;
			if(b%2==0){
				if(n >= half)
					pw.println(coord1+" "+coord2);
				else
					pw.println(coord2+" "+coord1);
			} else {
				if(n >= half)
					pw.println(coord2+" "+coord1);
				else
					pw.println(coord1+" "+coord2);
			}
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
