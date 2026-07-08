import java.io.*;
import java.util.*;

// UVa 12207 - That is Your Queue
public class Main{
	
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
				
		LinkedList<Long> q = new LinkedList<>();
		Long p;
		int c, tc = 1;
		char comm;
		readLine();
		p = nextLong(); c = nextInt();
		while(p != 0 || c != 0){
			
			p = Math.min(p, 1000);
			q = new LinkedList<>();
			for(long i = 1; i <= p; i++)
				q.offer(i);
			pw.println("Case "+(tc++)+":");
			while(c-- > 0){
				readLine();
				comm = next().charAt(0);
				if(comm == 'N'){
					p = q.poll();
					pw.println(p);
					q.offer(p);
				}
				else{
					p = nextLong();
					q.remove(p);
					q.offerFirst(p);
				}
			}
			
			readLine();
			p = nextLong(); c = nextInt();
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
