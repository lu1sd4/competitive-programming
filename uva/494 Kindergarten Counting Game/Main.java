import java.io.*;
import java.util.*;

//UVA 494 - Kindergarten Counting Game
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
		
		String line;
		while((line = br.readLine()) != null){
			char[] chars = line.toCharArray();
			// State machine
			// 0 - waiting for beginning of sequence
			// 1 - Going through sequence
			int state = 0;
			int sum = 0;
			for(int i = 0; i < chars.length; i++){
				switch(state){
					case 0:
						if(Character.isLetter(chars[i])){
							sum++;
							state = 1;
						}
						break;
					
					case 1:
						if(!Character.isLetter(chars[i]))
							state = 0;
						break;
				}
			}
			pw.println(sum);
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
