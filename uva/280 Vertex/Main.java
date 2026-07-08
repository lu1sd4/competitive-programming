import java.io.*;
import java.util.*;
import java.math.*;

// UVa 280 - Vertex
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
		
		int n;
		Map<Integer, ArrayList<Integer>> v;
		String line;
		while((n = lineToInt()) != 0){
			v =  new HashMap<>();
			for(int i = 1; i <= n; i++)
				v.put(i, new ArrayList<>());
			while(!(line = br.readLine()).equals("0")){
				tokenize(line);
				ArrayList<Integer> ts = v.get(readInt());
				int t;
				while((t = readInt()) != 0)
					ts.add(t);
			}
			nextLine();
			int qs = readInt();
			for(int i = 0; i < qs; i++)
				inaccessible(readInt(), v, n);
		}
		
		br.close();
		pw.close();
		
	}
	
	void inaccessible(int s, Map<Integer, ArrayList<Integer>> v, int n){
		boolean visited[] = new boolean[n+1];
		int av = n;
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		while(!q.isEmpty()){
			int c = q.poll();
			for(Integer t : v.get(c))
				if(!visited[t]){
					visited[t] = true;
					q.add(t);
					av--;
				}
		}
		pw.print(av);
		for(int i = 1; i <= n; i++)
			if(!visited[i])
				pw.print(" "+i);
		pw.println();;
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
