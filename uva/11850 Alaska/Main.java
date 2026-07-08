import java.io.*;
import java.util.*;

// UVa 11850 - Alaska
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
		
		int n;
		TreeSet<Integer> stationsGo, stationsBack;
		while((n = lineAsInt()) != 0){
			stationsGo = new TreeSet<>();
			stationsBack = new TreeSet<>();
			int current = 0, next, station, max;
			while(n-- > 0){
				station = lineAsInt();
				if(station > 0)
					stationsGo.add(station);
				stationsBack.add(1422 - station);
			}			
			boolean can = true;
			while(can){
				max = current + 200;
				if(max >= 1422)
					break;
				if(stationsGo.isEmpty()){
					can = false;
					break;
				}
				next = stationsGo.pollFirst();				
				if(max < next)
					can = false;
				else
					current = next;				
			}
			if(can){
				while(!stationsGo.isEmpty())
					current = stationsGo.pollFirst();				
				current = - (1422 - current);
				while(can){
					max = current + 200;
					if(max >= 1422)
						break;
					if(stationsBack.isEmpty()){
						can = false;
						break;
					}
					next = stationsBack.pollFirst();					
					if(max < next)
						can = false;
					else
						current = next;
				}
			}
			pw.println(can ? "POSSIBLE" : "IMPOSSIBLE");
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
