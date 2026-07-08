import java.io.*;
import java.util.*;

public class samples{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new samples().solve();
	}
	
	static class Sample implements Comparable<Sample>{
		int v;
		int t;
		public Sample(int _v, int _t){
			v = _v;
			t = _t;
		}
		@Override
		public int compareTo(Sample other){
			return Integer.compare(v, other.v);
		}
	}
	
	int GT = 0, LT = 1;
	Sample samples[];
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output"));
		
		String line;
		int n, cv, ct, q;
		while((line = br.readLine()) != null){
			n = Integer.parseInt(line);
			samples = new Sample[n];
			for(int i = 0; i < n; i++){
				readLine();
				ct = nextInt();
				cv = nextInt();
				samples[i] = new Sample(cv, ct);
			}
			q = lineAsInt();
			for(int i = 0; i < q; i++){
				readLine();
				int operator, timespan, ans = 0;
				String queryType;
				if(next().equals("gt")) operator = GT;
				else					operator = LT;
				queryType = next();
				timespan = nextInt();
				if(queryType.equals("min")) ans = minQ(timespan, operator);
				else if(queryType.equals("max")) ans = maxQ(timespan, operator);
				else if(queryType.equals("avg")) ans = avgQ(timespan, operator);
				pw.println(ans);
			}
		}
		
		br.close();
		pw.close();		
	}
	
	int maxQ(int timespan, int Op){
		int ans = 0;
		PriorityQueue<Sample> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for(int j = 0; j < samples.length; j++){
			int minTime = samples[j].t - timespan;
			while(!pq.isEmpty() && pq.peek().t < minTime)
				pq.poll();
			if(!pq.isEmpty()){
				if(Op == GT){
					if(samples[j].v > pq.peek().v)
						ans++;
				} else {
					if(samples[j].v < pq.peek().v)
						ans++;
				}
			}
			pq.offer(samples[j]);
		}
		return ans;
	}
	
	int minQ(int timespan, int Op){
		int ans = 0;
		PriorityQueue<Sample> pq = new PriorityQueue<>();
		for(int j = 0; j < samples.length; j++){
			int minTime = samples[j].t - timespan;
			while(!pq.isEmpty() && pq.peek().t < minTime)
				pq.poll();
			if(!pq.isEmpty()){
				if(Op == GT){
					if(samples[j].v > pq.peek().v)
						ans++;
				} else {
					if(samples[j].v < pq.peek().v)
						ans++;
				}
			}
			pq.offer(samples[j]);
		}
		return ans;
	}
	
	int avgQ(int timespan, int Op){
		int ans = 0, sum = 0;
		for(int i = 0, j = 0; j < samples.length; j++){
			int mintime = samples[j].t - timespan;
			while(i < j && samples[i].t < mintime) 
				sum -= samples[i++].v;
			if(i < j){
				int val = samples[j].v * (j-i); 
				if(Op == GT){
					if(val > sum) ans++;
				} else {
					if(val < sum) ans++;
				}
			}
			sum += samples[j].v;
		}
		return ans;
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
