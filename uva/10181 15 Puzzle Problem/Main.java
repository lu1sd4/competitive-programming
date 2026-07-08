import java.io.*;
import java.util.*;

// 10181 - 15-Puzzle Problem
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
		
		int n = lineToInt();
		for(int tc = 1; tc <= n; tc++){
			int initialGrid[][] = new int[4][4];
			int ir = 0, ic = 0;
			for(int i = 0; i < 4; i++){
				nextLine();
				for(int j = 0; j < 4; j++){
					int v = readInt();
					initialGrid[i][j] = v;
					if(v == 0){
						ir = i;
						ic = j;
					}
				}
			}
			State initialState = new State(initialGrid, ir, ic, "");
			if(!initialState.isSolvable())
				System.out.println("This puzzle is not solvable.");
			else {
				boolean found = false;
				PriorityQueue<State> pq = new PriorityQueue<>();
				Set<Long> visited = new HashSet<>();
				pq.add(initialState);
				while(!pq.isEmpty()){
					State current = pq.poll();
					if(current.isGoal()){
						found = true;
						System.out.println(current.moves);
						break;
					}
					State next = current.moveRight();
					if(next != null && !visited.contains(next.encode()))
						pq.add(next);
					next = current.moveUp();
					if(next != null && !visited.contains(next.encode()))
						pq.add(next);
					next = current.moveLeft();
					if(next != null && !visited.contains(next.encode()))
						pq.add(next);
					next = current.moveDown();
					if(next != null && !visited.contains(next.encode()))
						pq.add(next);
					visited.add(current.encode());
				}
				if(!found)
					System.out.println("This puzzle is not solvable.");
			}
		}
				
		br.close();
		pw.close();
		
	}
	
	static class State implements Comparable<State>{
		int grid[][];
		int br; // blank tile row
		int bc; // blank column
		String moves; // moves to get to this state
		public State(int board[][], int br, int bc, String moves){
			this.grid = board;
			this.br = br;
			this.bc = bc;
			this.moves = moves;
		}
		int[][] gridCopy(){
			int newGrid[][] = new int[4][4];
			for(int i = 0; i < 4; i++)
				newGrid[i] = Arrays.copyOf(grid[i], 4);
			return newGrid;
		}
		public State moveLeft(){
			if(bc == 0)
				return null;
			int newGrid[][] = new int[4][4];
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 4; ++j) {
					newGrid[i][j] = grid[i][j];
				}
			}
			newGrid[br][bc] = newGrid[br][bc-1];
			newGrid[br][bc-1] = 0;
			return new State(newGrid, br, bc-1, moves+'L');
		}
		public State moveRight(){
			if(bc == 3)
				return null;
			int newGrid[][] = new int[4][4];
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 4; ++j) {
					newGrid[i][j] = grid[i][j];
				}
			}
			newGrid[br][bc] = newGrid[br][bc+1];
			newGrid[br][bc+1] = 0;
			return new State(newGrid, br, bc+1, moves+'R');
		}
		public State moveUp(){
			if(br == 0)
				return null;
			int newGrid[][] = new int[4][4];
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 4; ++j) {
					newGrid[i][j] = grid[i][j];
				}
			}
			newGrid[br][bc] = newGrid[br-1][bc];
			newGrid[br-1][bc] = 0;
			return new State(newGrid, br-1, bc, moves+'U');
		}
		public State moveDown(){
			if(br == 3)
				return null;
			int newGrid[][] = new int[4][4];
			for(int i = 0; i < 4; ++i) {
				for(int j = 0; j < 4; ++j) {
					newGrid[i][j] = grid[i][j];
				}
			}
			newGrid[br][bc] = newGrid[br+1][bc];
			newGrid[br+1][bc] = 0;
			return new State(newGrid, br+1, bc, moves+'D');
		}
		boolean isSolvable(){
			int ivs = 0;
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++){
					if(grid[i][j] == 0) continue;
					int ni = i, nj = j + 1;
					if(j == 3){
						ni = i + 1;
						nj = 0;
					}
					for(; ni < 4; ni++){
						for(; nj < 4; nj++){
							if(grid[ni][nj] == 0) continue;
							if(grid[i][j] > grid[ni][nj])
								ivs++;
						}
						nj = 0;
					}
				}
			//System.out.println(ivs);
			if((4 - br) % 2 == 0)
				return ivs % 2 == 1;
			return ivs % 2 == 0;
		}
		boolean isGoal(){
			int n = 1;
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++){
					if(n == 16) continue;
					if(grid[i][j] != n) return false;
					n++;
				}
			return true;
		}
		int heuristic(){
			int sum = 0;
			for(int i = 0; i < 4; i++)
				for(int j = 0; j < 4; j++){
					if(grid[i][j] == 0)
						continue;
					sum += Math.abs(i - ((grid[i][j]-1)/4));
					sum += Math.abs(j - ((grid[i][j]-1)%4));
				}
			return sum;
		}
		@Override
		public int compareTo(State o) {
			return (heuristic() + moves.length()) - (o.heuristic() + o.moves.length());
		}
		public long encode(){
			long code = 0;
			for (int r = 0; r < 4; r++) { 
				for(int c = 0; c < 4; c++) {
					code <<= 4;
					code += grid[r][c];
				}
			}
			return code;
		}
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