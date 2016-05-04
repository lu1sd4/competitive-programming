import java.io.*;
import java.util.*;

//UVa 10443 - Rock
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		char[][] grid, copy;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int r, c, n;
		String line;
		while(t-- > 0){
			line = br.readLine();
			st = new StringTokenizer(line);
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			grid = new char[r][c];
			copy = new char[r][c];
			for(int i = 0; i < grid.length; i++){
				line = br.readLine();
				for(int j = 0; j < grid[i].length; j++)
					grid[i][j] = line.charAt(j);
			}
			boolean w;
			while(n-- > 0){
				for(int i = 0; i < grid.length; i++){
					for(int j = 0; j < grid[i].length; j++){
						w = false;
						
						if(i-1 >= 0) //up
							if(win(grid[i-1][j], grid[i][j])) w = true;
						
						if(i+1 < grid.length) //down
							if(win(grid[i+1][j], grid[i][j])) w = true;
						
						if(j-1 >= 0) //left
							if(win(grid[i][j-1], grid[i][j])) w = true;
						
						if(j+1 < grid[i].length) // right
							if(win(grid[i][j+1], grid[i][j])) w = true;
						
						if(w)
							copy[i][j] = winner(grid[i][j]);
						else
							copy[i][j] = grid[i][j];
					}
				}
				grid = copy;
				copy = new char[r][c];
			}
			for(int i = 0; i < grid.length; i++){
				for(int j = 0; j <grid[i].length; j++)
					System.out.print(grid[i][j]);
				System.out.println();
			}
			if(t >= 1) System.out.println("");
		}
	}
	
	static char winner(char a){
		if(a == 'R') return 'P';
		if(a == 'S') return 'R';
		if(a == 'P') return 'S';
		return 0;
	}

	static boolean win(char a, char b){
		if(a == 'R')
			if(b == 'S') return true;
			else return false;
		if(a == 'S')
			if(b == 'P') return true;
			else return false;
		if(a == 'P')
			if(b == 'R') return true;
			else return false;
		return false;
	}	
	
}
