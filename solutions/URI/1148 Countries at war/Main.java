import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		
		int n, e;
		int cost[][];
		int max = 10000000;
		
		while(true){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			if(n == 0 && e == 0)
				break;
			cost = new int[n][n];
			for(int[] x : cost)
				Arrays.fill(x, max);
			for(int i = 0; i < cost.length; i++)
				cost[i][i] = 0;
			
			while(e-- > 0){
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken())-1;
				int j = Integer.parseInt(st.nextToken())-1;
				int h = Integer.parseInt(st.nextToken());
				cost[i][j] = h;
				if(cost[i][j] != max && cost[j][i] != max){
					cost[i][j] = cost[j][i] = 0;
				}
			}
				
			//Floyd Warshall
			for(int k=0;k<cost.length;k++){
		      for(int i=0;i<cost.length;i++){
		        for(int j=0;j<cost.length;j++){
		        	cost[i][j]=Math.min(cost[i][j],cost[i][k]+cost[k][j]);
		        }
		      }
		    }
			
			int q = Integer.parseInt(br.readLine());
			while(q-- > 0){
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken())-1;
				int j = Integer.parseInt(st.nextToken())-1;
				if(cost[i][j] > 9000000)
					System.out.println("Nao e possivel entregar a carta");
				else
					System.out.println(cost[i][j]);
			}
			System.out.println();
		}
		
	}
	
	static int lineasint() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	
	static int tokenasint() throws IOException{
		return Integer.parseInt(st.nextToken());
	}
	
	static String readLine() throws IOException{
		return br.readLine();
	}
	
	static void nextLine() throws IOException{
		st = new StringTokenizer(br.readLine());
	}
	
}
