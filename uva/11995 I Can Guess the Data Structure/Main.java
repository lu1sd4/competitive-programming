import java.io.*;
import java.util.*;

// UVa 11995 - I Can Guess the Data Structure!
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
		
		PriorityQueue<Integer> pq;
		Queue<Integer> q;
		Stack<Integer> s;
		String line;
		int n, comm, value, tc;
		boolean ispq, isq, iss;
		while((line = br.readLine()) != null){
			pq = new PriorityQueue<>(Collections.reverseOrder());
			q = new LinkedList<>();
			s = new Stack<>();
			ispq = true;
			isq = true;
			iss = true;
			tc = 3;
			n = Integer.parseInt(line);
			while(n-- > 0){
				readLine();
				comm = nextInt();
				value = nextInt();
				if(comm == 1){
					if(ispq) pq.offer(value);
					if(isq)	 q.add(value);
					if(iss)	 s.push(value);
				} else {
					if(ispq || isq || iss){
						if(ispq)
							if(pq.size() == 0 || pq.poll() != value){
								ispq = false;
								tc--;
							}
						if(isq)
							if(q.size() == 0 || q.poll() != value){
								isq = false;
								tc--;
							}
						if(iss)
							if(s.size() == 0 || s.pop() != value){
								iss = false;
								tc--;
							}
					}
				}
			}
			if(tc > 1)
				pw.println("not sure");
			else if(tc == 0)
				pw.println("impossible");
			else if(ispq)
				pw.println("priority queue");
			else if(isq)
				pw.println("queue");
			else if(iss)
				pw.println("stack");
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
