import java.io.*;
import java.util.*;

//LiveArchive 7390 - Matrix Keypad
class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		pw = new PrintWriter(System.out);
		
		char[][] ans;
		char[][] grid;
		int t, r, c;
		boolean hor, ver, possible;
		
		t = nextInt();
		while(t-- > 0){
			r = nextInt();
			c = nextInt();
			grid = new char[r][c];
			ans = new char[r][c];
			for(int i = 0; i < r; i++)
				grid[i] = br.readLine().toCharArray();
			
			possible = true;
			for(int i = 0; i < r && possible; i++)
				for(int j = 0; j < c && possible; j++){
					ver = false;
					for(int k = 0; k < r; k++)
						if(k != i && grid[k][j] != '0'){
							ver = true;
							break;
						}
					hor = false;
					for(int k = 0; k < c; k++)
						if(k != j && grid[i][k] != '0'){
							hor = true;
							break;
						}
					if(grid[i][j] == '0'){
						possible = !(hor && ver);
						ans[i][j] = 'N';
					} else {
						if(hor && ver)
							ans[i][j] = 'I';
						else
							ans[i][j] = 'P';
					}
				}
			
			if(!possible)
				pw.println("impossible");
			else{
				for(int i = 0; i < r; i++){
					for(int j = 0; j < c; j++)
						pw.print(ans[i][j]);
					pw.print("\n");
				}
			}
			pw.println("----------");
		}
		
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
	
	static void println(Object a){
		System.out.println(a);
	}
		
}
