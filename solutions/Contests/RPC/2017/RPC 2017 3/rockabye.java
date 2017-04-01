import java.io.*;
import java.util.*;

public class rockabye {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new rockabye().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		int t = lineAsInt(), n, k, f;
		PriorityQueue<Drug> pq;
		Drug cd;
		String name;
		while(t-- > 0){
			readLine();
			n = nextInt();
			k = nextInt();
			pq = new PriorityQueue<>(Comparator.reverseOrder());
			for(int i = 1; i <= n; i++){
				readLine();
				name = next();
				f = nextInt();
				cd = new Drug(name, f, i);
				pq.offer(cd);
			}
			while(k-- > 0){
				cd = pq.poll();
				pw.println(cd.nextTime+" "+cd.name);
				cd.nextDose();
				pq.offer(cd);
			}
		}
		
		br.close();
		pw.close();
	}
	
	static class Drug implements Comparable<Drug>{
		String name;
		int period;
		int nextTime;
		int priority;
		public Drug(String name,int period,int priority){
			this.name = name;
			this.period = period;
			this.priority = priority;
			this.nextTime = period;
		}
		public void nextDose(){
			this.nextTime += period;
		}
		@Override
		public int compareTo(Drug o) {
			if(this.nextTime != o.nextTime)
				return o.nextTime - this.nextTime;
			return o.priority - this.priority;
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
