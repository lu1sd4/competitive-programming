import java.io.*;
import java.util.*;

// UVa 259 - Software Allocation
public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        int source = 36, sink = 37;
        
        int[][] residual;
        int maxFlow, alloc;
        String line, order1;
        char[] order2;
        
        while((line = br.readLine()) != null){
        	//0 - 9 are computers, 10 - 35 are letters, 36 and 37 are source and sink
        	residual = new int[38][38];
        	maxFlow = 0;
        	alloc = 0;
        	do {
	        	tokenize(line);
	        	order1 = next();
	        	int inLetter = Character.getNumericValue(order1.charAt(0));
	        	int flowLetter = Character.getNumericValue(order1.charAt(1));
	        	alloc += flowLetter;
	        	residual[source][inLetter] += flowLetter;
	        	order2 = next().toCharArray();
	        	for(int i = 0; i < order2.length; i++){
	        		if(order2[i] == ';') break;
	        		int computer = Character.getNumericValue(order2[i]);
	        		residual[inLetter][computer] = 1;
	        	}
	        	line = br.readLine();
        	} while(line != null && !line.isEmpty());
        	
        	for(int i = 0; i < 10; i++)
        		residual[i][sink] = 1;
        		
    		boolean done = false;
            while (!done) {
                done = true;
                Queue<Integer> Q = new LinkedList<Integer>();
                int[] parent = new int[38];
                parent[source] = source;
                Q.add(source);
                boolean[] visited = new boolean[38];
                visited[source] = true;
                while (!Q.isEmpty()) {
                    int n = Q.poll();
                    if (n == sink) {
                        done = false;
                        break;
                    } else
                        for (int i = 0; i < residual[n].length; i++)
                            if (residual[n][i] > 0 && !visited[i]) {
                                parent[i] = n;
                                visited[i] = true;
                                Q.add(i);
                            }
                }
                if (!done) {
                    maxFlow++;
                    int node = sink;
                    while (parent[node] != node) {
                        residual[parent[node]][node]--;
                        residual[node][parent[node]]++;
                        node = parent[node];
                    }
                }
            }
            
            
        	        	
        	if(maxFlow == alloc){
        		
        		for(int i = 0; i < 10; i++){
        			int j = 'A';
        			for(; j <= 'Z'; j++){
        				if(residual[i][Character.getNumericValue(j)] == 1){
        					pw.print((char)j);
        					break;
        				}
        			}
        			if(j > 'Z')
        				pw.print('_');
        		}
        		pw.println();
        		
        	} else
        		pw.println("!");
        	
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
