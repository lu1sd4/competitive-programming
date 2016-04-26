import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int N;
		int M;
		int S;
		int x, y, r;
		int[][] cost;
		boolean[] visited;
		int[] dist;
		while(T-- > 0){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cost = new int[N][N];
			for(int[] c : cost)
				Arrays.fill(c, -1);
			while(M-- > 0){
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken()) - 1;
				y = Integer.parseInt(st.nextToken()) - 1;
				r = Integer.parseInt(st.nextToken());
				if(cost[x][y] == -1 || r < cost[x][y]){
					cost[x][y] = r;
					cost[y][x] = r;
				}
			}
			S = Integer.parseInt(br.readLine());
			visited = new boolean[N];
			dist = new int[N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[S-1] = 0;
			for(int i = 0; i < N; i++){
				//O(V^2) - Encontrar el mínimo cada vez
				int cur = -1;
				for(int j = 0; j < N; j++){
					if(visited[j]) continue;
					if(cur == -1 || dist[j] < dist[cur])
						cur = j;
				}
				visited[cur] = true;
				for(int j = 0; j < N; j++){
					if(cost[cur][j] != -1){
						int path = dist[cur] + cost[cur][j];
						if(path > 0)
							if(path < dist[j])
								dist[j] = path;
					}
				}
			}
			String d = "";
			for(int i = 0; i < dist.length; i++){
				if(i == S-1) continue;
				d += dist[i] == Integer.MAX_VALUE ? "-1 " : Integer.toString(dist[i])+" ";
			}
			System.out.println(d.substring(0, d.length()-1));
		}

	}

}