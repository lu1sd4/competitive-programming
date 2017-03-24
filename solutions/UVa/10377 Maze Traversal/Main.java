import java.io.*;
import java.util.*;
import java.math.*;

// UVa 10377 - Maze Traversal
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
		
		
		nextOrientation.put("NL", 'W');
		nextOrientation.put("NR", 'E');
		nextOrientation.put("WL", 'S');
		nextOrientation.put("WR", 'N');
		nextOrientation.put("SL", 'E');
		nextOrientation.put("SR", 'W');
		nextOrientation.put("EL", 'N');
		nextOrientation.put("ER", 'S');		
		nextMove.put('N', new Integer[]{-1,0});
		nextMove.put('S', new Integer[]{1,0});
		nextMove.put('W', new Integer[]{0,-1});
		nextMove.put('E', new Integer[]{0,1});
		
		char maze[][], dir;
		int x, y, nxx, nxy;
		int tcs = lineToInt();
		char[] comms;
		boolean quit;
		for(int tc = 1; tc <= tcs; tc++){
			br.readLine();
			nextLine();
			maze = new char[readInt()][readInt()];
			for(int i = 0; i < maze.length; i++)
				maze[i] = lineAsCharArray();
			nextLine();
			dir = 'N';
			x = readInt()-1;
			y = readInt()-1;
			quit = false;
			while(!quit){
				comms = lineAsCharArray();
				for(int i = 0; i < comms.length; i++){
					char c = comms[i];
					if(c == ' ') continue;
					if(c == 'Q'){
						quit = true;
						break;
					}
					if(c == 'F'){
						Integer[] move = nextMove.get(dir);
						nxx = x + move[0];
						nxy = y + move[1];
						if(maze[nxx][nxy] == ' '){
							x = nxx;
							y = nxy;
						}
					} else
						dir = nextDir(dir, c);
				}
			}
			pw.println((x+1)+" "+(y+1)+" "+dir);
			if(tc != tcs) pw.println();
		}
		
		br.close();
		pw.close();
		
	}
	
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
