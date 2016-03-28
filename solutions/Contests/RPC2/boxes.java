import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boxes {
    
    public static void printM(char[][] M){
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M[i].length; j++)
                System.out.print(M[i][j]);
            System.out.println("");
        }
    }

    public static void main(String[] args) throws IOException{
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int M, N, K;
        int[][] grid;
        String line;
        int math, music;
        int x1, x2, y1, y2;
        while(T-- > 0){
            math = 0;
            music = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            grid = new int[M][N];
            for(int i = 0; i < M; i++){
                line = br.readLine();
                for(int j = 0; j < N; j++){
                    grid[i][j] = line.charAt(j) == 'B'?-1:-2;
                }
            }
            while(K-- > 0){
                st = new StringTokenizer(br.readLine());
                x1 = Integer.parseInt(st.nextToken());
                y2 = Integer.parseInt(st.nextToken());
                x2 = Integer.parseInt(st.nextToken());
                y1 = Integer.parseInt(st.nextToken());
                for(int i = M - y1; i <= M - y2; i++){
                    for(int j = x1 - 1; j <= x2 - 1; j++){
                        if(grid[i][j] > 0){
                            if(grid[i][j] > x2 - 1)
                                break;
                            else
                                j = grid[i][j];
                        } else {
                            if(grid[i][j] == -1)
                                math++;
                            if(grid[i][j] == -2)
                                music++;
                            grid[i][j] = x2 - 1;
                        }
                    }
                }
            }
            System.out.println(Integer.toString(math)+ " " + Integer.toString(music));
        }
        
    }
    
}
