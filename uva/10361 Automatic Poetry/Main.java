import java.io.*;
import java.util.*;

//UVA 10361 - Automatic Poetry
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
		
		int n = lineToInt();
		
		while(n-- > 0){
			
			String ss[] = new String[5];
			for(int i = 0; i < ss.length; i++)
				ss[i] = "";			
			int ns = 0, state = 0;
			char[] l1 = lineAsCharArray();
			char comps[] = {'<', '>'};
			for(char c : l1){
				if(c == comps[state]){
					ns++;
					state = 1 - state;
				}	
				else{
					pw.print(c);
					ss[ns] += c;
				}
			}
			pw.println();
			String l2 = br.readLine();
			l2 = l2.substring(0, l2.length()-3);
			pw.println(l2+ss[3]+ss[2]+ss[1]+ss[4]);
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
