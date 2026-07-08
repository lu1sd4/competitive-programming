package Solved;
import java.io.*;
import java.util.*;

public class A {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        String line;
        int[] ns;
        while((line = br.readLine()) != null){
        	st = new StringTokenizer(line);
        	ns = new int[3];
        	for(int i = 0; i < ns.length; i++)
        		ns[i] = readInt();
        	if(ns[0] + ns[1] == ns[2])
        		pw.println("S");
        	else if(ns[0] + ns[2] == ns[1])
        		pw.println("S");
        	else if(ns[1] + ns[2] == ns[0])
        		pw.println("S");
        	else if(ns[0] == ns[1] || ns[1] == ns[2] || ns[0] == ns[2])
        		pw.println("S");
        	else
        		pw.println("N");
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