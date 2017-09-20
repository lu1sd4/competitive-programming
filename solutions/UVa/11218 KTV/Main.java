import java.io.*;
import java.util.*;

// UVa 11218 - KTV
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	PriorityQueue<Integer> q;
	int k;

	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int teamc[][] = new int[81][3], sc[] = new int[81], tc = 1, n, max, count;
		boolean complete[];
		while((n = lineAsInt()) != 0){
			max = -1;
			for(int i = 0; i < n; i++){
				readLine();
				teamc[i][0] = nextInt()-1;
				teamc[i][1] = nextInt()-1;
				teamc[i][2] = nextInt()-1;
				sc[i] = nextInt();
			}
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
					for(int k = j + 1; k < n; k++){
						complete = new boolean[9];
						count = 0;
						for(int l = 0; l < 3; l++){
							complete[teamc[i][l]] = true;
							complete[teamc[j][l]] = true;
							complete[teamc[k][l]] = true;							
						}
						for(boolean b : complete)
							if(b) count++;
						if(count == 9)
							max = Math.max(max, sc[i]+sc[j]+sc[k]);
					}
			pw.println("Case "+(tc++)+": "+max);
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
