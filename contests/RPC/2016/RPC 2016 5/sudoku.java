import java.io.*;
import java.util.*;

public class sudoku {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int t = Integer.parseInt(br.readLine());
        int n, grid[][], row[];
        while(t-- > 0){
            n = Integer.parseInt(br.readLine());
            grid = new int[n][n];
            boolean wrong = false;
            for(int i = 0; i < grid.length; i++){
                row = new int[n+1];
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < grid[i].length && !wrong; j++){
                    int x = Integer.parseInt(st.nextToken());
                    if(row[x] == 1)
                        wrong = true;
                    row[x] = 1;
                    grid[i][j] = x;
                }
            }
            if(wrong)
                System.out.println("no");
            else {
                int[] col;
                for(int i = 0; i < grid.length && !wrong; i++){
                    col = new int[n+1];
                    for(int j = 0; j < grid[i].length && !wrong; j++){
                        if(col[grid[j][i]] == 1)
                            wrong = true;
                        col[grid[j][i]] = 1;
                    }
                }
                if(wrong)
                    System.out.println("no");
                else {
                    int dim = (int)Math.sqrt(n);
                    int[] square;
                    for(int i = 0; i < n && !wrong; i+=dim)
                        for(int j = 0; j < n && !wrong; j+=dim){
                            square = new int[n+1]; 
                            for(int k = i; k < i + dim && !wrong; k++)
                                for(int l = j; l < j + dim && !wrong; l++){
                                    if(square[grid[k][l]] == 1)
                                        wrong = true;
                                    square[grid[k][l]] = 1;
                                }
                        }
                    if(wrong)
                        System.out.println("no");
                    else
                        System.out.println("yes");
                }
            }
        }
        
    }
    
}
