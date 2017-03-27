import java.io.*;
import java.util.*;

// UVa 11991 - Easy Problem from Rujia Liu?
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
		
		Map<Integer, ArrayList<Integer>> ocs;
		ArrayList<Integer> cl;
		int n, m, k, v, x;
		String line;
		while((line = br.readLine()) != null){
			tokenize(line);
			n = nextInt();
			m = nextInt();
			ocs = new HashMap<>();
			readLine();
			for(int i = 1; i <= n; i++){
				x = nextInt();
				cl = ocs.get(x);
				if(cl == null){
					cl = new ArrayList<>();
					cl.add(i);
					ocs.put(x, cl);
				} else
					cl.add(i);
			}
			while(m-- > 0){
				readLine();
				k = nextInt() - 1;
				v = nextInt();
				cl = ocs.get(v);
				if(cl == null || k >= cl.size())
					pw.println("0");
				else
					pw.println(cl.get(k));
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
