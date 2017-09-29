import java.io.*;
import java.util.*;

// UVa 10507 - Waking up brain
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
		
		Map<Character, Set<Character>> connections;
		Set<Character> awake, nextAwake;		
		int N, M, ans, conns;
		char direct[], conn[], current;
		
		String line;
		while((line = br.readLine()) != null){
			connections = new HashMap<>();
			awake = new HashSet<>();
			N = Integer.parseInt(line);
			M = lineAsInt();
			direct = lineAsCharArray();
			for(int i = 0; i < direct.length; i++){
				current = direct[i];
				connections.put(current, new HashSet<>());
				awake.add(current);
			}
			for(int i = 0; i < M; i++){
				conn = lineAsCharArray();
				if(!connections.containsKey(conn[0]))
					connections.put(conn[0], new HashSet<>());
				if(!connections.containsKey(conn[1]))
					connections.put(conn[1], new HashSet<>());
				connections.get(conn[0]).add(conn[1]);
				connections.get(conn[1]).add(conn[0]);
			}
			ans = 0;
			while(true){
				nextAwake = new HashSet<>(awake);
				for(Character key : connections.keySet()){
					if(!awake.contains(key)){
						conns = 0;
						for(Character tar : connections.get(key))
							if(awake.contains(tar))
								conns++;
						if(conns >= 3)
							nextAwake.add(key);
					}
				}
				ans++;
				if(nextAwake.size() == N)
					break;				
				if(nextAwake.size() == awake.size()){
					ans = -1;
					break;
				}
				awake = nextAwake;
			}
			if(N == 3) ans = 0;
			pw.println(ans < 0 ? "THIS BRAIN NEVER WAKES UP" : "WAKE UP IN, "+ans+", YEARS");
			if(br.readLine() == null) break;
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
