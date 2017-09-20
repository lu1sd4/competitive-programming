import java.io.*;
import java.util.*;

// UVa 10919 - Prerequisites?
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
		
		String line;
		int k, m, c, cid;
		Set<Integer> taken;
		int[] required;
		while(!(line = br.readLine()).equals("0")){			
			tokenize(line);
			k = nextInt();
			m = nextInt();
			taken = new HashSet<>();
			required = new int[m];
			readLine();
			while(k-- > 0)
				taken.add(nextInt());
			for(int cat = 0; cat < m; cat++){
				readLine();
				c = nextInt();
				required[cat] = nextInt();
				while(c-- > 0){
					cid = nextInt();
					if(taken.contains(cid))
						required[cat]--;
				}
				if(required[cat] < 0) required[cat] = 0;
			}
			int sum = 0;
			for(int r : required)
				sum += r;
			pw.println(sum > 0 ? "no" : "yes");
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
