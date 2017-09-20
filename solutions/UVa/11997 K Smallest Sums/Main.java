import java.io.*;
import java.util.*;

// UVa 11997 - K Smallest Sums
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	PriorityQueue<Integer> q;
	int k;

	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		int k;
		PriorityQueue<Integer> q; 
		int a[], result[];
		while((line = br.readLine()) != null){
			k = Integer.parseInt(line);
			result = new int[k];
			readLine();
			for(int i = 0; i < k; i++)
				result[i] = nextInt();
			Arrays.sort(result);
			for(int i = 1; i < k; i++){			
				a = new int[k];
				readLine();
				for(int j = 0; j < k; j++)
					a[j] = nextInt();
				Arrays.sort(a);
				q = new PriorityQueue<>(Comparator.reverseOrder());
				for(int j = 0; j < k; j++)
					q.offer(result[j] + a[0]);
				for(int j = 0; j < k; j++)
					for(int l = 1; l < k; l++){
						if(result[j] + a[l] < q.peek()){
							q.poll();
							q.offer(result[j] + a[l]);
						} else {
							break;
						}
					}
				for(int j = k-1; j >= 0; j--)
					result[j] = q.poll();
			}
			pw.print(result[0]);
			for(int i = 1; i < k; i++)
				pw.print(" "+result[i]);
			pw.println();
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
