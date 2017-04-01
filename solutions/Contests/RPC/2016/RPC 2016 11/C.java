import java.io.*;
import java.util.*;

public class C {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    static Map<Integer, Integer> signif;
    static Map<Integer, Integer> original;
    static Map<Integer, Integer> costs;
    
    static int generateNumber(int[] board){
    	int n = 0;
    	for(int i = 0; i < 8; i++){
    		n *= 10;
    		n += signif.get(board[i]);
    	}
    	return n;
    }
    
    static int[] generateBoard(int number){
    	int[] board = new int[8];
    	for(int i = 7; i >= 0; i--){
    		board[i] = original.get(number % 10);
    		number /= 10;
    	}
    	return board;
    }
    
    static class Node implements Comparable<Node>{
    	public int id;
    	public int cost;
    	public Node(int id, int cost){
    		this.id = id;
    		this.cost = cost;
    	}
    	public int compareTo(Node n){
    		return Integer.compare(cost, n.cost); 
    	}
    }
    
    static boolean lessCost(int id, int newCost){
    	if(!costs.containsKey(id) || costs.get(id) > newCost){
    		costs.put(id, newCost);
    		return true;
    	}
    	return false;
    }
    
    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
        pw = new PrintWriter(System.out);

        String line;
        int[] start;
        int[] target;
        int targetNumber, startNumber;
        PriorityQueue<Node> pq;
        
        int[][] swaps = new int[3][8];
        swaps[0] = new int[]{4, 0, 1, 2, 0, 1, 2, 3};
        swaps[1] = new int[]{1, 2, 6, 7, 5, 4, 5, 6};
        swaps[2] = new int[]{-1, 5, 3, -1, -1, 6, 7, -1}; 
        
        while((line = br.readLine()) != null){
        	tokenize(line);
        	start = new int[8];
        	target = new int[8];
        	signif = new HashMap<Integer, Integer>(); 
        	original = new HashMap<Integer, Integer>();
        	costs = new HashMap<Integer, Integer>();
        	for(int i = 0; i < 4; i++)
        		start[i] = readInt();
        	nextLine();
        	for(int i = 4; i < 8; i++)
        		start[i] = readInt();
        	nextLine();
        	for(int i = 0; i < 4; i++)
        		target[i] = readInt();
        	nextLine();
        	for(int i = 4; i < 8; i++)
        		target[i] = readInt();
        	
        	for(int i = 1; i < 9; i++){
        		signif.put(start[i-1], i);
        		original.put(i, start[i-1]);
        	}
        	
        	startNumber = generateNumber(start);
        	targetNumber = generateNumber(target);
        	
        	pq = new PriorityQueue<>();
        	pq.add(new Node(startNumber, 0));
        	costs.put(startNumber, 0);
        	
        	while(!pq.isEmpty()){
        		Node cNode = pq.poll();
        		int currentState = cNode.id;
        		if(currentState == targetNumber) break;
        		//Generate all neighbors of current state with costs
        		int[] currentBoard = generateBoard(currentState);
        		for(int i = 0; i < 8; i++)
        			for(int j = 0; j < 3; j++){
        				if(swaps[j][i] == -1) continue;
        				int x = swaps[j][i];
        				int[] nextBoard = Arrays.copyOf(currentBoard, 8);
        				int aux = nextBoard[i];
        				nextBoard[i] = nextBoard[x];
        				nextBoard[x] = aux;
        				int opCost = nextBoard[i] + nextBoard[x];
        				int nextState = generateNumber(nextBoard);
        				if(lessCost(nextState, cNode.cost + opCost))
        					pq.add(new Node(nextState, cNode.cost + opCost));
        			}
        	}
        	
        	pw.println(costs.get(targetNumber));
        	
        }

        br.close();
        pw.close();

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
