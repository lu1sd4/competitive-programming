import java.io.*;
import java.util.*;

// UVa 1203 - Argus
public class Main {

	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	PriorityQueue<Task> pq;
	int k;
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		while((line = br.readLine()) != null){
			pq = new PriorityQueue<>();
			while(!line.equals("#")){
				tokenize(line);
				next();
				pq.add(new Task(nextInt(), nextInt()));
				line = br.readLine();
			}
			k = lineAsInt();
			Task currentTask;
			while(k-- > 0){
				currentTask = pq.poll();
				pw.println(currentTask);
				currentTask.nextPeriod();
				pq.offer(currentTask);
			}
		}
		
		br.close();
		pw.close();
	}
	
	static class Task implements Comparable<Task>{
		int nt, p, st;
		public Task(int nt, int p){
			this.nt = nt;
			this.p = p;
			this.st = p;
		}
		public void nextPeriod(){
			this.st += p;
		}
		@Override
		public int compareTo(Task t) {
			if(t.st != this.st)
				return this.st - t.st;
			return this.nt - t.nt;
		}
		@Override
		public String toString() {
			return this.nt+"";
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
