import java.io.*;
import java.util.*;

// UVa 927 - Integer Sequences from Addition of Terms
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        //read number of test cases
        int c = lineToInt();
        
        int degree, d0, d1, k, n;
        long coefs[], res;
        while(c-- > 0){
        	nextLine();
        	// read degree of polynomial
        	degree = readInt();
        	coefs = new long[degree+1];
        	// read polynomial
        	for(int i = 0; i <= degree; i++)
        		coefs[i] = readLong();
        	// read d
        	d0 = lineToInt();
        	// read k
        	k = lineToInt();
        	d1 = 0;
        	// find out n for which n*d >= k >= (n-1)*d
        	for(n = 1; k > d1; n++){
        		d1 += d0 * n;
        		//pw.println("d1: "+d1+" n: "+n+" - k: "+k+"\n");
        	}
        	n--;
        	res = 0;
        	// compute polynomial for n
        	for(int i = 0; i <= degree; i++)
        		res += coefs[i] * Math.pow(n, i);
        	pw.println(res);
        }
        
        br.close();
        pw.close();
        
    }
    
    static int lineToInt() throws IOException{
    	return Integer.parseInt(br.readLine());
    }
    
    static long readLong() throws Exception{
    	return Long.parseLong(st.nextToken());
    }
    
    static int readInt() throws Exception{
    	return Integer.parseInt(st.nextToken());
    }
    
    static void nextLine() throws Exception{
    	st = new StringTokenizer(br.readLine());
    }
    
}