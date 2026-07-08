import java.io.*;
import java.util.*;

// UVa 1237 - Expert Enough?
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        int t = lineToInt();
        int[] prices;
        String[] fabs;
        while(t-- > 0){
        	prices = new int[1000001];
        	int f = lineToInt();
        	fabs = new String[10002];
        	fabs[0] = "UNDETERMINED";
        	fabs[10001] = "UNDETERMINED";
        	for(int i = 1; i <= f; i++){
        		nextLine();
        		fabs[i] = next();
        		int l = readInt(), r = readInt();
        		for(int j = l; j <= r; j++){
        			if(prices[j] == 0)
        				prices[j] = i;
        			else
        				prices[j] = 10001;
        		}
        	}
        	int q = lineToInt();
        	while(q-- > 0)
        		pw.println(fabs[prices[lineToInt()]]);
        	if(t > 0) pw.println();
        }
        
       
        br.close();
        pw.close();

    }
    
    static String next() throws Exception{
    	return st.nextToken();
    }
    
    static void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    static int lineToInt() throws IOException{
        return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
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
