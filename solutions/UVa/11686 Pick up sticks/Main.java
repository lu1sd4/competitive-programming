import java.io.*;
import java.util.*;
import java.math.*;

// UVa 11686 - Pick up sticks
public class Main {

	BufferedReader br;
    StringTokenizer st;
    PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int n, m;
	boolean temp[], perm[];
	Map<Integer, ArrayList<Integer>> g;
	Stack<Integer> ans;
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		readLine();
		n = nextInt();
		m = nextInt();
		
		int a, b;
		while(m != 0 || n != 0){
			
			temp = new boolean[n];
			perm = new boolean[n];
			g = new HashMap<>();
			for(int i = 0; i < n; i++)
				g.put(i, new ArrayList<>());
			ans = new Stack<>();
			
			while(m-- > 0){
				readLine();
				a = nextInt()-1;
				b = nextInt()-1;
				g.get(a).add(b);
			}
			
			boolean cont = true;
			for(int i = 0; cont && i < n; i++)
				if(!perm[i])
					cont = visit(i);
			
			if(!cont)
				pw.println("IMPOSSIBLE");
			else
				while(!ans.isEmpty())
					pw.println(ans.pop());
				
			readLine();
			n = nextInt();
			m = nextInt();
		}
		
		br.close();
		pw.close();
	}
	
	boolean visit(int s){
		if(temp[s])
			return false;
		else if(!perm[s]){
			temp[s] = true;
			boolean cont = true;
			ArrayList<Integer> nbs = g.get(s);
			for(int i = 0; cont && i < nbs.size(); i++)
				cont &= visit(nbs.get(i));
			temp[s] = false;
			perm[s] = true;
			ans.push(s+1);
			return cont;
		}
		return true;
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
