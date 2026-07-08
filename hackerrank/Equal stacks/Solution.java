import java.io.*;
import java.util.*;

//Hackerrank - Equal Stacks

public class Solution {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n1 = nextInt();
		int n2 = nextInt();
		int n3 = nextInt();
		
		int[] s1 = new int[n1];
		int[] s2 = new int[n2];
		int[] s3 = new int[n3];
		
		s1[n1 - 1] = nextInt();
		for(int i = n1 - 2; i >= 0; i--) s1[i] = nextInt();
		for(int i = 1; i < n1; i++) s1[i] += s1[i - 1];
		s2[n2 - 1] = nextInt();
		for(int i = n2 - 2; i >= 0; i--) s2[i] = nextInt();
		for(int i = 1; i < n2; i++) s2[i] += s2[i - 1];
		s3[n3 - 1] = nextInt();
		for(int i = n3 - 2; i >= 0; i--) s3[i] = nextInt();
		for(int i = 1; i < n3; i++) s3[i] += s3[i - 1];
		
		int ans = -1;
		for(int i = n1 - 1; i >= 0 && ans == -1; i--){
			int desired = s1[i];
			for(int j = n2 - 1; j >= 0 && ans == -1; j--){
				if(s2[j] < desired)	break;
				if(s2[j] == desired)
					for(int k = n3 - 1; k >= 0; k--){
						if(s3[k] < desired) break;
						if(s3[k] == desired){
							ans = desired;
							break;
						}
					}
			}
		}
		
		pw.println(ans == -1 ? 0 : ans);
		
		br.close();
		pw.close();
		
	}
	
	static String next() throws IOException{
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
	
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
	
}
