import java.io.*;
import java.util.*;

// UVa 10895 - Matrix Transpose
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		Map<Integer, ArrayList<ArrayList<Integer>>> g;
		String line;
		int m, n, r, indexes[];
		while((line = br.readLine()) != null){
			tokenize(line);
			m = nextInt();
			n = nextInt();
			g = new HashMap<>();
			ArrayList<Integer> ixs;
			ArrayList<Integer> vs;
			ArrayList<ArrayList<Integer>> adj;
			for(int i = 1; i <= n; i++){
				ixs = new ArrayList<>();
				vs = new ArrayList<>();
				adj = new ArrayList<>();
				adj.add(ixs);
				adj.add(vs);
				g.put(i,  adj);
			}
			for(int i = 1; i <= m; i++){
				readLine();
				r = nextInt();
				if(r > 0){
					indexes = new int[r];
					for(int j = 0; j < r; j++)
						indexes[j] = nextInt();
					readLine();
					for(int j : indexes){
						g.get(j).get(0).add(i);
						g.get(j).get(1).add(nextInt());
					}
				} else
					skipLine();
			}
			pw.println(n+" "+m);
			for(int i = 1; i <= n; i++){
				ixs = g.get(i).get(0);
				if(ixs.size() == 0){
					pw.println("0\n");
				} else {
					vs = g.get(i).get(1);
					pw.print(ixs.size());
					for(int ix : ixs)
						pw.print(" "+ix);
					pw.println();
					for(int j = 0; j < vs.size(); j++){
						if(j > 0) pw.print(" ");
						pw.print(vs.get(j));
					}
					pw.println();
				}
			}
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
