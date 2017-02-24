import java.io.*;
import java.util.*;

//UVA 401 - Palindromes
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
		
		int[] reverses = new int[400];
		reverses['A'] = 'A';
		reverses['E'] = '3';
		reverses['H'] = 'H';
		reverses['I'] = 'I';
		reverses['J'] = 'L';
		reverses['L'] = 'J';
		reverses['M'] = 'M';
		reverses['O'] = 'O';
		reverses['S'] = '2';
		reverses['T'] = 'T';
		reverses['U'] = 'U';
		reverses['V'] = 'V';
		reverses['W'] = 'W';
		reverses['X'] = 'X';
		reverses['Y'] = 'Y';
		reverses['Z'] = '5';
		reverses['1'] = '1';
		reverses['2'] = 'S';
		reverses['3'] = 'E';
		reverses['5'] = 'Z';
		reverses['8'] = '8';
		
		String line;
		while((line = br.readLine()) != null){
			
			boolean pal = true, mir = true;
			
			for(int i = 0; i < line.length()/2 + 1; i++){
				char cur = line.charAt(i);
				char back = line.charAt(line.length() - i - 1); 
				if(reverses[cur] != back)
					mir = false;
				if(cur != back)
					pal = false;
			}
			pw.print(line+" -- ");
			if(!mir && !pal)
				pw.println("is not a palindrome.");
			if(!mir && pal)
				pw.println("is a regular palindrome.");
			if(mir && !pal)
				pw.println("is a mirrored string.");
			if(mir && pal)
				pw.println("is a mirrored palindrome.");
			
			pw.println();
			
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
