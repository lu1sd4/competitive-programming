import java.io.*;
import java.util.*;
import java.math.*;


//UVa 100 - The 3n + 1 problem
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        //Codigo aca
        
        String line;
        int a, b, max, cyclelength, c;
        
        while((line = br.readLine()) != null){
        	
        	st = new StringTokenizer(line);
        	a = nextInt();
        	b = nextInt();
        	boolean inv = a > b;
        	if(inv){
        		int aux = a;
            	a = b;
            	b = aux;
        	}	
        	max = Integer.MIN_VALUE;
        	for(int i = a; i <= b; i++){
        		cyclelength = 1;
        		c = i;
        		while(c != 1){
        			while(c % 2 != 0) {
        				c = 3 * c + 1;
        				cyclelength++;
        			}
    				c /= 2;
    				cyclelength += 1;
        		}
        		if(cyclelength > max)
        			max = cyclelength;
        	}
        	if(inv)
        		pw.println(b+" "+a+" "+max);
        	else
        		pw.println(a+" "+b+" "+max);
        	
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