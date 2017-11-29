import java.io.*;
import java.util.*;

public class tribune{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new tribune().solve();
	}
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output"));
		
		int trib[][], n, sumRows[], sumCols[];
		String line;
		while((line = br.readLine()) != null){
			n = Integer.parseInt(line);
			trib = new int[n][n];
			sumRows = new int[n];
			sumCols = new int[n];
			Arrays.fill(sumCols, 0);
			for(int i = 0; i < n; i++){
				char[] linearr = lineAsCharArray();
				sumRows[i] = 0;
				for(int j = 0; j < n; j++){
					int val = linearr[j];
					trib[i][j] = val;
					sumRows[i] += val;
					sumCols[j] += val;
				}
			}
			int rightSum, wrongC = -1, wrongR = -1;
			if(sumRows[0] != sumRows[1])
				rightSum = sumRows[2];
			else
				rightSum = sumRows[0];
			for(int i = 0; i < n; i++)
				if(sumRows[i] != rightSum){
					wrongR = i;
					break;
				}
			for(int i = 0; i < n; i++)
				if(sumCols[i] != rightSum){
					wrongC = i;
					break;
				}
			int ans = rightSum - (sumRows[wrongR] - trib[wrongR][wrongC]);
			pw.println((wrongR+1)+" "+(wrongC+1)+" "+(char)ans);
		}
		
		br.close();
		pw.close();		
	}
	
	void printGrid(int a[][]){
		for(int i = 0; i < a.length; i++){
			System.out.println(Arrays.toString(a[i]));
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
