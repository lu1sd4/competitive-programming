import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int N;
        int M;
        int S;
        int[][] vs;
        int[] ds;
        //DFS
        Queue<Integer> q;
        boolean[] visited;
        while(T-- > 0){
            N = sc.nextInt();
            M = sc.nextInt();
            vs = new int[N][N];
            ds = new int[N];
            while(M-- > 0){
                int i = sc.nextInt()-1;
                int j = sc.nextInt()-1;
                vs[i][j] = 6;
                vs[j][i] = 6;
            }
            S = sc.nextInt();
            //BFS
            q = new LinkedList<>();
            visited = new boolean[N];
            visited[S-1] = true;            
            q.add(S-1);
            int a;
            while(!q.isEmpty()){
                a = q.poll();
                for(int i = 0; i < N; i++){
                    if(vs[a][i] != 0){
                        if(!visited[i]){
                            ds[i] = ds[a]+vs[a][i];                            
                            visited[i] = true;
                            q.add(i);
                        }
                    }
                }     
            }
            String tp;
            for(int i = 0; i < ds.length; i++){
                if(i!=S-1){
                    if(ds[i]==0)
                        tp = "-1";
                    else
                        tp = Integer.toString(ds[i]);
                    if(i < ds.length-1)
                        System.out.print(tp+" ");
                    else
                        System.out.print(tp);
                }
            }
            System.out.print("\n");
        }
    }
}