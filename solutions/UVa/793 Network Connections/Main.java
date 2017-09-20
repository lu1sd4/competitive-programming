import java.io.*;
import java.util.*;

// UVa 793 - Network Connections
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}

	static class UnionFind {
		private ArrayList<Integer> p, rank, setSize;
		private int numSets;

		public UnionFind(int N) {
			p = new ArrayList<Integer>(N);
			rank = new ArrayList<Integer>(N);
			setSize = new ArrayList<Integer>(N);
			numSets = N;
			for (int i = 0; i < N; i++) {
				p.add(i);
				rank.add(0);
				setSize.add(1);
			}
		}

		public int findSet(int i) { 
			if (p.get(i) == i) return i;
			else {
				int ret = findSet(p.get(i)); 
				p.set(i, ret);
				return ret;
			} 
		}

		public Boolean isSameSet(int i, int j) { 
			return findSet(i) == findSet(j); 
		}

		public void unionSet(int i, int j) { 
			if (!isSameSet(i, j)) { 
				numSets--;
				int x = findSet(i), y = findSet(j);
				if (rank.get(x) > rank.get(y)) {
					p.set(y, x);
					setSize.set(x, setSize.get(x) + setSize.get(y)); 
				} else {
					p.set(x, y);
					setSize.set(y, setSize.get(y) + setSize.get(x));
					if (rank.get(x) == rank.get(y)) 
						rank.set(y, rank.get(y) + 1);
				} 
			} 
		}
		
		public int numDisjointSets() {
			return numSets; 
		}
		
		public int sizeOfSet(int i) { 
			return setSize.get(findSet(i)); 
		}
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int tcs = lineAsInt(), n, suc, uns, ci, cj;
		char c;
		String line;
		skipLine();
		for(int tc = 0; tc < tcs; tc++){
			suc = 0;
			uns = 0;
			n = lineAsInt();
			UnionFind uf = new UnionFind(n);
			line = br.readLine();
			while(line != null && !line.isEmpty()){
				tokenize(line);
				c = next().charAt(0);
				ci = nextInt() - 1;
				cj = nextInt() - 1;
				if(c == 'c')
					uf.unionSet(ci, cj);
				else{
					if(uf.isSameSet(ci, cj))
						suc++;
					else
						uns++;
				}
				line = br.readLine();
			}
			if(tc>0) pw.println();
			pw.println(suc+","+uns);
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
