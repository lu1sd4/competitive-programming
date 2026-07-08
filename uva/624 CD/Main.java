import java.io.*;
import java.util.*;

// UVa 624 - CD
public class Main {
	
	BufferedReader br;
	StringTokenizer st = new StringTokenizer("");
	PrintWriter pw;
	
	public static void main(String[] args) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		while((line = br.readLine()) != null){
			tokenize(line);
			int n = readInt(), tr = readInt();
			int lengths[] = new int[tr];
			//System.out.println(tr);
			for(int i = 0; i < lengths.length; i++)
				lengths[i] = readInt();
			int ans = 0, maxSum = Integer.MIN_VALUE;
			for(int i = 1; i < Math.pow(2,  tr); i++){
				int csum = 0;
				for(int j = 0; j < tr; j++)
					if(i << ~j < 0)
						csum += lengths[j];
				if(csum <= n && csum > maxSum){
					ans = i;
					maxSum = csum;
				}		   
			}
			
			for(int i = 0; i < tr; i++)
				if(ans << ~i < 0)
					pw.print(lengths[i]+" ");
			pw.println("sum:"+maxSum);
						
		}
		
		br.close();
		pw.close();
		
	}
	
	char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineToInt() throws IOException{
        return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }

    long readLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int readInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    void nextLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
	
}