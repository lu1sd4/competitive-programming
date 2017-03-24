import java.io.*;
import java.util.*;
import java.math.*;

// UVa 572 - Oil Deposits
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
		
		String line = br.readLine();
		tokenize(line);
		int m = readInt(), n = readInt(), deps;
		while(m != 0){
			deps =  0;
			grid = new char[m][n];
			for(int i = 0; i < m; i++)
				grid[i] = lineAsCharArray();
			
			for(int i = 0; i < m; i++)
				for(int j = 0; j < n; j++){
					if(grid[i][j] == '@'){
						deps++;
						explore(i, j);
					}
				}
			
			pw.println(deps);
			
			line = br.readLine();
			tokenize(line);
			m = readInt(); n = readInt();
		}
		br.close();
		pw.close();
	}
	
	void explore(int di, int dj){
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[]{di, dj});
		grid[di][dj] = ' ';
		while(!q.isEmpty()){
			Integer[] cp = q.poll();
			for(int nbx : nbs)
				for(int nby : nbs){
					if(nbx == 0 && nby == 0) continue;
					int nx = cp[0] + nbx, ny = cp[1] + nby;
					if(withinBoundaries(nx,ny) && grid[nx][ny] == '@'){
						grid[nx][ny] = ' ';
						q.add(new Integer[]{nx,ny});
					}
				}
		}
	}
	
	boolean withinBoundaries(int i, int j){
		return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length; 
	}
	
	int[] nbs = {-1,0,1};
	char grid[][];
	
	char nextDir(char dir, char com){
		return nextOrientation.get(""+dir+com);
	}
	
	Map<String, Character> nextOrientation = new HashMap<>();
	Map<Character, Integer[]> nextMove = new HashMap<>();
	
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
