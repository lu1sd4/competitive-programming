import java.io.*;
import java.util.*;

// UVa 861 - Little Bishops
public class Main {
	
	BufferedReader br;
	StringTokenizer st = new StringTokenizer("");
	PrintWriter pw;
	
	public static void main(String[] args) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		long[] whiteColumns = new long[9];
		long[] blackColumns = new long[9];
		
		for(int i = 1; i < 9; i++){
			whiteColumns[i] = i % 2 != 0 ? i : whiteColumns[i-1];
			blackColumns[i] = i % 2 != 0 ? (i+1) : blackColumns[i-1];
		}
		
		long white[][] = new long[9][65];
		for(int i = 1; i < 65; i++)
			white[0][i] = 0;
		for(int i = 0; i < 9; i++)
			white[i][0] = 1;
		for(int i = 1; i < 9; i++)
			for(int j = 1; j <= i; j++)
				white[i][j] = white[i-1][j] + white[i-1][j-1] * (whiteColumns[i] - (j-1));
		
		long black[][] = new long[9][65];
		for(int i = 1; i < 65; i++)
			black[0][i] = 0;
		for(int i = 0; i < 9; i++)
			black[i][0] = 1;
		for(int i = 1; i < 9; i++)
			for(int j = 1; j <= i; j++)
				black[i][j] = black[i-1][j] + black[i-1][j-1] * (blackColumns[i] - (j-1));
		
		long[][] answers = new long[9][65];
		
		answers[0] = Arrays.copyOf(white[0], white[0].length);
		
		for(int i = 1; i < 9; i++)
			for(int j = 0; j < 65; j++)
				for(int k = 0; k <= j; k++)
					answers[i][j] += white[i][k] * black[i-1][j - k];
		
		nextLine();
		int n = readInt(), k = readInt();
		while(n != 0 || k != 0){
			
			pw.println(answers[n][k]);
			
			nextLine();
			n = readInt();
			k = readInt();
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