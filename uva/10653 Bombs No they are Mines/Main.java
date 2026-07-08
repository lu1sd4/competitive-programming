import java.io.*;
import java.util.*;

// UVa 10653 - Bombs! NO they are Mines!!
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	static class Point{
		int a;
		int b;
		public Point(int a, int b){
			this.a = a;
			this.b = b;
		}
	}
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int grid[][];
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		readLine();
		int R = nextInt(), C = nextInt(), rb, ri, nbs, ci, xt, yt;
		Queue<Point> q;
		
		while(R != 0 && C != 0){
						
			// Read grid
			grid = new int[R][C];
			// Encode safe points as -1 in grid
			for(int[] r : grid)
				Arrays.fill(r, -1);
			rb = lineAsInt();
			for(int i = 0; i < rb; i++){
				readLine();
				ri = nextInt(); nbs = nextInt();
				for(int j = 0; j < nbs; j++){
					ci = nextInt();
					// Encode bombs in grid as -2
					grid[ri][ci] = -2;
				}
			}
			// Read start and end points
			readLine();
			Point s = new Point(nextInt(), nextInt());
			readLine();
			xt = nextInt(); yt = nextInt();
			// BFS in grid from starting point to finishing point
			// Initialize stuff
			q = new LinkedList<>();
			q.add(s);
			grid[s.a][s.b] = 0;
			// For possible movements
			int dx[] = {1,-1,0,0};
			int dy[] = {0,0,1,-1};			
			// BFS loop
			int tx, ty;
			while(grid[xt][yt] == -1){
				Point c = q.poll();
				for(int i = 0; i < 4; i++){
					tx = c.a + dx[i];
					ty = c.b + dy[i];
					if(withinBoundaries(tx, ty) && grid[tx][ty] == -1){
						grid[tx][ty] = grid[c.a][c.b] + 1;
						q.add(new Point(tx, ty));
					}
				}
			}
			pw.println(grid[xt][yt]);
			readLine();
			R = nextInt(); C = nextInt();
		}
		
		br.close();
		pw.close();
		
	}
	
	boolean withinBoundaries(int i, int j){
		return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
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
    
    double nextDouble() throws Exception{
    	return Double.parseDouble(next());
    }

    long nextLong() throws Exception{
        return Long.parseLong(next());
    }

    int nextInt() throws Exception{
        return Integer.parseInt(next());
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
