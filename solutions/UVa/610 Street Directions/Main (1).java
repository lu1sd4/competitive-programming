import java.io.*;
import java.util.*;

// UVa 610 - Street Directions
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int n, m;
	Map<Integer, ArrayList<Integer>> g;
	Map<Integer, ArrayList<Integer>> ans;
	int low[], pre[], parent[], count;

	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int tc = 1;
		
		readLine();
		n = nextInt();
		m = nextInt();
		
		while(n != 0 || m != 0){
			count = 0;
			low = new int[n]; Arrays.fill(low, -1);
			pre = new int[n]; Arrays.fill(pre, -1);
			parent = new int[n];
			g = new HashMap<>();
			ans = new HashMap<>();
			for(int i = 0; i < n; i++){
				g.put(i, new ArrayList<>());
				ans.put(i, new ArrayList<>());
			}
			
			int a, b;
			while(m-- > 0){
				readLine();
				a = nextInt()-1;
				b = nextInt()-1;
				g.get(a).add(b);
				g.get(b).add(a);
			}
			
			for(int i = 0; i < n; i++)
				if(pre[i] == -1)
					dfs(i);
			
			pw.println((tc++)+"\n");
			for(int i = 0; i < n; i++){
				ArrayList<Integer> cl = ans.get(i);
				for(int j : cl)
					pw.println((i+1)+" "+(j+1));
			}
			pw.println("#");
			
			readLine();
			n = nextInt();
			m = nextInt();
		}
		
		br.close();
		pw.close();
	}
	
	void dfs(int u){ // Turn all non-bridge edges into directional edges
		pre[u] = count++;
		low[u] = pre[u];
		for(int v : g.get(u)){
			if(pre[v] == -1){
				parent[v] = u;
				dfs(v);
				low[u] = Math.min(low[u], low[v]);
				if(low[v] > pre[u]){ //bridge
					ans.get(u).add(v);
					ans.get(v).add(u);
				} else { // not bridge
					if(!ans.get(v).contains(u))
						ans.get(u).add(v);
				}
			} else if(parent[u] != v){ // not bridge
				low[u] = Math.min(low[u], pre[v]);
				if(!ans.get(v).contains(u))
					ans.get(u).add(v);
			}
		}
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
