import java.io.*;
import java.util.*;

// UVa 10855 - Rotated square
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        int N = nextInt(), n = nextInt(), times[];
        char[][] container;
        char[][][] contained;
        while(N != 0 && n != 0){
            times = new int[4];
            container = new char[N][N];
            contained = new char[4][n][n];
            
            for(int i = 0; i < N; i++)
                container[i] = br.readLine().toCharArray();
            for(int i = 0; i < n; i++)
                contained[0][i] = br.readLine().toCharArray();
            
            for(int i = 1; i < contained.length; i++)
                contained[i] = rotate90(contained[i-1]);
            
            // See if nxn square with i,j as top left corner
            // is what we're looking for
            for(int i = 0; i <= N-n; i++)
                for(int j = 0; j <= N-n; j++){
                    for(int k = 0; k < contained.length; k++){
                        if(check(container, contained[k], i, j))
                            times[k]++;
                    }
                }
            
            for(int i = 0; i < times.length; i++)
                pw.print(times[i] + (i == times.length-1 ? "" : " "));
            pw.print("\n");
                    
            N = nextInt();
            n = nextInt();
        }
        
        br.close();
        pw.close();
        
    }
    
    static boolean check(char[][] container, char[][] contained, int i, int j){
        for(int k = i; k < i + contained.length; k++)
            for(int l = j; l < j + contained.length; l++){
                if(container[k][l] != contained[k-i][l-j])
                    return false;
            }
        return true;
    }
    
    static char[][] rotate90(char[][] mat){
        char[][] ans = new char[mat.length][mat[0].length];
        for(int i = 0; i < mat.length; i++)
            for(int j = 0; j < mat[i].length; j++)
                ans[j][mat.length-1-i] = mat[i][j];
        return ans;
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

