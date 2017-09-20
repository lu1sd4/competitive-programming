import java.io.*;
import java.util.*;

// UVa 924 - Spreading The News
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
		int e, t, source, day, size, nxsize, max_day, max_size, f;
		boolean used[];
		Map<Integer, ArrayList<Integer>> g;
		ArrayList<Integer> cfs;
		Queue<Integer> fs;
		while((line = br.readLine()) != null){
			e = Integer.parseInt(line);
			g = new HashMap<>();
			int n;
			for(int i = 0; i < e; i++){
				cfs = new ArrayList<Integer>();
				readLine();
				n = nextInt();
				while(n-- > 0)
					cfs.add(nextInt());
				g.put(i, cfs);
			}
			t = lineAsInt();
			while(t-- > 0){
				source = lineAsInt();
				cfs = g.get(source);
				if(cfs.isEmpty())
					pw.println("0");
				else{
					used = new boolean[e];
					used[source] = true;
					day = max_day = 1;
					size = max_size = cfs.size();
					for(int cf : cfs)
						used[cf] = true;
					fs = new LinkedList<>();
					fs.addAll(cfs);
					while(!fs.isEmpty()){
						nxsize = 0;
						for(int i = 0; i < size; i++){
							f = fs.poll();
							cfs = g.get(f);
							for(int cf : cfs)
								if(!used[cf]){
									nxsize++;
									fs.offer(cf);
									used[cf] = true;
								}
						}
						size = nxsize;
						day++;
						if(size > max_size){
							max_day = day;
							max_size = size;
						}
					}
					pw.println(max_size+" "+max_day);
				}
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
