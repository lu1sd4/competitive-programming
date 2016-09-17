import java.io.*;
import java.util.*;

// UVa 599 - The Forrest for the Trees
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        boolean[][] f;
        boolean[] check;
        int t = lineToInt();
        while(t-- > 0){
        	f = new boolean[26][26];
        	check = new boolean[26];
        	char[] line = lineAsCharArray();
        	        	
        	while(line[0] != '*'){
        		int from = line[1] - 'A';
        		int to = line[3] - 'A';
        		f[from][to] = true;
        		f[to][from] = true;
        		check[from] = true;
        		check[to] = true;
        		line = lineAsCharArray();
        	}
        	line = lineAsCharArray();
        	int acorns = 0, toCheck = 0;
        	for(int i = 0; i < line.length; i++)
        		if(line[i] != ','){
        			toCheck++;
        			int pIn = line[i] - 'A';
        			check[pIn] = true;
        		}
        	
        	int checked = 0, comps = 0;
        	boolean[] visited = new boolean[26];
        	
        	while(checked < toCheck){
        		Stack<Integer> st = new Stack<>();
        		int start = -1;
        		for(int i = 0; i < check.length; i++)
        			if(check[i] && !visited[i]){
        				start = i;
        				break;
        			}
        		if(start == -1) break;
        		st.push(start);
        		visited[start] = true;
        		int size = 1;
        		while(!st.isEmpty()){
        			int current = st.pop();
        			checked++;
        			for(int i = 0; i < f[current].length; i++){
        				if(!visited[i] && f[current][i]){
        					size++;
        					st.push(i);
        					visited[i] = true;
        				}
        			}
        		}
        		if(size == 1) acorns++;
        		else comps++;
        	}
        	pw.println("There are "+comps+" tree(s) and "+acorns+" acorn(s).");
        }
        
        br.close();
        pw.close();

    }
    
    static char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
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
