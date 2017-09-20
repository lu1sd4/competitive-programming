import java.io.*;
import java.util.*;

// UVa 1103 - Ancient Messages
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int h, w;
	int[][] grid;
	int[] ans;
	int[][] hexToInt = {
								{0,0,0,0},
								{0,0,0,1},
								{0,0,1,0},
								{0,0,1,1},
								{0,1,0,0},
								{0,1,0,1},
								{0,1,1,0},
								{0,1,1,1},
								{1,0,0,0},
								{1,0,0,1},
								{1,0,1,0},
								{1,0,1,1},
								{1,1,0,0},
								{1,1,0,1},
								{1,1,1,0},
								{1,1,1,1}
							};
	int[] dr = {-1,0,0,1};
	int[] dc = {0,-1,1,0};

	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		readLine();
		h = nextInt();
		w = nextInt();
		char line[];
		int lim, l, tc = 1, currenthex[];
		while(h != 0 || w != 0){
			grid = new int[h][w*4];
			for(int i = 0; i < h; i++){
				line = br.readLine().toCharArray();
				for(int j = 0; j < w; j++){
					currenthex = hexToInt[Integer.parseInt(Character.toString(line[j]), 16)];
					lim = (j+1)*4; l = 0;
					for(int k = j*4; k < lim; k++)
						grid[i][k] = currenthex[l++];
				}
			}
			ans = new int[6];
			explore(grid);
			pw.print("Case "+(tc++)+": ");
			while(ans[1]-- > 0)	pw.print('A');
			while(ans[5]-- > 0)	pw.print('D');
			while(ans[3]-- > 0)	pw.print('J');
			while(ans[2]-- > 0)	pw.print('K');
			while(ans[4]-- > 0)	pw.print('S');
			while(ans[0]-- > 0)	pw.print('W');
			pw.println();
			readLine();
			h = nextInt();
			w = nextInt();
		}
		
		br.close();
		pw.close();
	}
	
	void explore(int[][] grid){
		fillbackground();
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[i].length; j++){
				if(grid[i][j] == 1)
					fillform(i,j);
			}
	}
	
	void fillbackground(){
		for(int i = 0; i < grid[0].length; i++)
			if(grid[0][i] == 0)
				floodfill(0,i);
		for(int i = 0; i < grid[h-1].length; i++)
			if(grid[h-1][i] == 0)
				floodfill(h-1,i);
		for(int i = 0; i < grid.length; i++)
			if(grid[i][0] == 0)
				floodfill(i, 0);
		for(int i = 0; i < grid.length; i++)
			if(grid[i][(w*4)-1] == 0)
				floodfill(i, (w*4)-1);
	}
	
	void fillform(int row, int col){
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[]{row,col});
		grid[row][col] = 2;
		int holes = 0, nr, nc;
		Integer[] cp;
		while(!q.isEmpty()){
			cp = q.poll();
			for(int i = 0; i < dc.length; i++){
				nr = cp[0] + dc[i];
				nc = cp[1] + dr[i];
				if(isWithinGrid(nr, nc)){
					if(grid[nr][nc] == 1){
						grid[nr][nc] = 2;
						q.add(new Integer[]{nr, nc});
					} else if(grid[nr][nc] == 0){
						floodfill(nr, nc);
						holes++;
					}
				}
			}
		}
		ans[holes]++;
	}
	
	void floodfill(int row, int col){
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[]{row,col});
		grid[row][col] = -1;
		Integer[] cp;
		int nr, nc;
		while(!q.isEmpty()){
			cp = q.poll();
			for(int i = 0; i < dc.length; i++){
				nr = cp[0] + dc[i];
				nc = cp[1] + dr[i];
				if(isWithinGrid(nr, nc)){
					if(grid[nr][nc] == 0){
						grid[nr][nc] = -1;
						q.add(new Integer[]{nr, nc});
					}
				}
			}
		}
	}
	
	boolean isWithinGrid(int i, int j){
		return i >= 0 && i < h && j >= 0 && j < w*4;
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
