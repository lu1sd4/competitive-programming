import java.io.*;
import java.util.*;
import java.math.*;

// UVa 118 - Mutant Flatworld Explorers
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
		
		Map<String, Character> nextOrientation = new HashMap<>();
		nextOrientation.put("NL", 'W');
		nextOrientation.put("NR", 'E');
		nextOrientation.put("WL", 'S');
		nextOrientation.put("WR", 'N');
		nextOrientation.put("SL", 'E');
		nextOrientation.put("SR", 'W');
		nextOrientation.put("EL", 'N');
		nextOrientation.put("ER", 'S');
		Map<Character, Integer[]> nextMove = new HashMap<>();
		nextMove.put('N', new Integer[]{0,1});
		nextMove.put('S', new Integer[]{0,-1});
		nextMove.put('W', new Integer[]{-1,0});
		nextMove.put('E', new Integer[]{1,0});
		
		nextLine();
		int maxx = readInt(), maxy = readInt();
		boolean grid[][] = new boolean[maxx+1][maxy+1], lost;
		int x, y;
		char orientation, instructions[];
		String line;
		while((line = br.readLine()) != null){
			tokenize(line);
			x = readInt();
			y = readInt();
			orientation = next().charAt(0);
			instructions = lineAsCharArray();
			lost = false;
			char c;
			for(int i = 0; !lost && i < instructions.length; i++){
				c = instructions[i];
				if(c == 'F'){
					Integer[] move = nextMove.get(orientation);
					int nxx = x + move[0];
					int nxy = y + move[1];
					if(nxx > maxx || nxy > maxy || nxx < 0 || nxy < 0){
						if(grid[x][y])
							continue;
						else{
							grid[x][y] = true;
							lost = true;
						}
					} else {
						x = nxx;
						y = nxy;
					}
				} else
					orientation = nextOrientation.get(""+orientation+c);
			}
			pw.println(x+" "+y+" "+orientation+(lost?" LOST":""));
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
