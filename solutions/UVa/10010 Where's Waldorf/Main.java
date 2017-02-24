import java.io.*;
import java.util.*;

//UVA 10010 - Where's Waldorf?
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
		
		int[] dirs = {-1,0,1};
		
		int tc = lineToInt();
		while(tc-- > 0){
			br.readLine();
			nextLine();
			int m = readInt(), n = readInt();
			char[][] grid = new char[m][n];
			String line;
			for(int i = 0; i < m; i++){
				line = br.readLine();
				grid[i] = line.toLowerCase().toCharArray();
			}
			int k = lineToInt();
			Queue<String> words = new LinkedList<>();
			while(k-- > 0)
				words.add(br.readLine().toLowerCase());
			while(!words.isEmpty()){
				char[] word = words.poll().toCharArray();
				boolean found = false;
				for(int i = 0; i < m && !found ; i++){
					for(int j = 0; j < n && !found ; j++){
						if(grid[i][j] == word[0]){
							for(int diri : dirs){
								if(found) break;
								for(int dirj : dirs){
									if(found) break;
									if(diri == 0 && dirj == 0) continue;
									int ci = i, cj = j, cl = 0;
									while(!found && ci >= 0 && ci < m && cj >= 0 && cj < n && grid[ci][cj] == word[cl]){
										ci += diri;
										cj += dirj;
										if(cl == word.length - 1){
											found = true;
											pw.println((i+1)+" "+(j+1));
										}
										cl++;
									}
								}
							}
						}
					}
				}
			}
			if(tc >= 1) pw.println();
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
