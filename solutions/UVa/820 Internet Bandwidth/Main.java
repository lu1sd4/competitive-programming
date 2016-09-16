import java.io.*;
import java.util.*;

// UVa 820 - Internet Bandwidth
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        int n = lineToInt();
        int source, sink, t, tc = 1;
        int[][] cap;
        
        while(n != 0){
        	cap = new int[n][n];
        	nextLine();
        	source = readInt() - 1;
        	sink = readInt() - 1;
        	t = readInt();
        	while(t-- > 0){
        		nextLine();
        		int i = readInt() - 1, j = readInt() - 1, b = readInt();
        		cap[i][j] += b;
        		cap[j][i] += b;
        	}
        	//Edmonds Karp
        	int maxFlow = 0;
        	boolean done = false;
        	while(!done){
        		done = true;
        		Queue<Integer> q = new LinkedList<>();
        		q.offer(source);
        		int[] parent = new int[n];
        		boolean[] visited = new boolean[n];
        		parent[source] = source;
        		visited[source] = true;
        		int flow = Integer.MAX_VALUE;
	        	while(!q.isEmpty()){
	        		int current = q.poll();
	        		if(current == sink){
	        			done = false;
	        			break;
	        		}
	        		for(int i = 0; i < cap[current].length; i++){
	        			if(cap[current][i] > 0 && !visited[i]){
	        				flow = Integer.min(flow, cap[current][i]);
	        				parent[i] = current;
	        				visited[i] = true;
	        				q.offer(i);
	        			}
	        		}
	        	}
	        	if(!done){
	        		maxFlow += flow;
	        		int node = sink;
	        		while(parent[node] != node){
	        			cap[node][parent[node]] += flow;
	        			cap[parent[node]][node] -= flow;
	        			node = parent[node];
	        		}
	        	}
        	}
        	pw.println("Network "+(tc++));
        	pw.println("The bandwidth is "+maxFlow+".\n");
        	
        	n = lineToInt();
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
