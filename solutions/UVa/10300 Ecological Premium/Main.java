import java.io.*;
import java.util.*;
import java.math.*;
import java.text.SimpleDateFormat;

//UVa 10300 - Ecological Premium
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        int n = nextInt();
        while(n-- > 0){
        	int sum = 0, s, a, ef;
        	int f = nextInt();
        	while(f-- > 0){
        		s = nextInt();
        		a = nextInt();
        		ef = nextInt();
        		sum += (s*ef);
        	}
        	pw.println(sum);
        }
        
        br.close();
        pw.close();
        
    }
    
    static String next() throws Exception{
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
    
    static int nextInt() throws Exception{
        return Integer.parseInt(next());
    }
    
}