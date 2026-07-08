import java.io.*;
import java.util.*;

//UVa 10189 - Minesweeper
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int[][] grid;
        String line;
        int n, m, t = 1;
        n = readInt();
        m = readInt();
        while(n != 0 && m != 0){
            if(t != 1) System.out.println("");
            grid = new int[n][m];
            for(int i = 0; i < n; i++){
                line = br.readLine();
                for(int j = 0; j < m; j++)
                    if(line.charAt(j) == '*'){
                        grid[i][j] = -1;
                        fillFields(i,j,grid);
                    }
            }
            System.out.println("Field #"+t+++":");
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++)
                    System.out.print(grid[i][j] == -1 ? "*":grid[i][j]);
                System.out.println("");
            }
            n = readInt();
            m = readInt();
        }
    }
    
    static void fillFields(int i, int j, int[][] grid){
        int ns[] = {-1,0,1};
        for(int k = 0; k < ns.length; k++)
            if(i+ns[k] >= 0 && i+ns[k] < grid.length)
                for(int l = 0; l < ns.length; l++)
                    if(j+ns[l] >= 0 && j+ns[l] < grid[i].length)
                        if(grid[i+ns[k]][j+ns[l]] != -1)
                            grid[i+ns[k]][j+ns[l]]++;
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
