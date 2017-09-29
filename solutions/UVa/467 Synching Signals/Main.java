import java.io.*;
import java.util.*;

// UVa 467 - Synching Signals
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
        pw = new PrintWriter(System.out);
        //pw = new PrintWriter(new File("src/output"));
        
        String line;
        ArrayList<BitSet> sems;
        BitSet cur, res;
        int n, max = 3601, min, secsRes, tc = 1;
        while((line = br.readLine()) != null){
        	tokenize(line);
        	sems = new ArrayList<>();
        	min = Integer.MAX_VALUE;
        	while(st.hasMoreTokens()){
        		cur = new BitSet(max);
        		n = readInt();
        		min = Math.min(min, n);
        		int i = 0;
        		while(i < max){
        			int j = i;
        			for(; j < max && j < i+(n-5); j++)
        				cur.set(j);
        			i = j+n+5;
        		}
        		sems.add(cur);
        	}
        	res = sems.get(0);
        	for(int i = 1; i < sems.size(); i++)
        		res.and(sems.get(i));
        	secsRes = res.nextSetBit(min-5);
        	if(secsRes == -1)
        		pw.println("Set "+(tc++)+" is unable to synch after one hour.");
        	else
        		pw.println("Set "+(tc++)+" synchs again at "+(secsRes/60)+" minute(s) and "+(secsRes%60)+" second(s) after all turning green.");
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