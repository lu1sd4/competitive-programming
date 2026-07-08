import java.io.*;
import java.util.*;
import java.math.*;

// UVa 776 - Monkeys in a Regular Forest
public class Main {

	static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int[] nbs = {-1,0,1};
	ArrayList<ArrayList<Integer>> grid;
	int result[][];
	int pad[];
	int count;
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		while((line = br.readLine()) != null){
			grid = new ArrayList<>();
			while(line != null && !(line.equals("%"))){
				tokenize(line);
				ArrayList<Integer> cl = new ArrayList<>();
				while(st.hasMoreTokens())
					cl.add((int)(next().charAt(0)));
				grid.add(cl);
				line = br.readLine();
			}
			result = new int[grid.size()][grid.get(0).size()];
			pad = new int[grid.get(0).size()];
			Arrays.fill(pad, 1);
			count = 1;
			for(int i = 0; i < grid.size(); i++){
				ArrayList<Integer> cl = grid.get(i);
				for(int j = 0; j < cl.size(); j++)
					if(cl.get(j) != -1)
						explore(i, j, cl.get(j));
			}
			for(int i = 0; i < result.length; i++){
				for(int j = 0; j < result[i].length; j++){
					pw.printf("%"+pad[j]+"s", result[i][j]);
					if(j < result[i].length-1) pw.print(" ");
				}
				pw.println();
			}
			pw.println("%");
		}
		
		br.close();
		pw.close();
	}
	
	void explore(int x, int y, int sp){
		Queue<Integer[]> q = new LinkedList<>();
		q.add(new Integer[]{x,y});
		grid.get(x).set(y, -1);
		result[x][y] = count;
		while(!q.isEmpty()){
			Integer[] cp = q.poll();
			pad[cp[1]] = Math.max(pad[cp[1]], (int)Math.floor(Math.log10(count)+1));
			for(int nbx : nbs)
				for(int nby : nbs){
					if(nbx == 0 && nby == 0) continue;
					int nxx = cp[0] + nbx;
					int nxy = cp[1] + nby;
					if(withinBoundaries(nxx, nxy) && grid.get(nxx).get(nxy) == sp){
						grid.get(nxx).set(nxy, -1);
						result[nxx][nxy] = count;
						q.add(new Integer[]{nxx, nxy});
					}
				}
		}
		count++;
	}
	
	boolean withinBoundaries(int i, int j){
		return i >= 0 && i < grid.size() && j >= 0 && j < grid.get(i).size(); 
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
