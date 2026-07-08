import java.io.*;
import java.util.*;

//UVa 11459 - Snakes and Ladders
public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static void solve() throws IOException{
        st = new StringTokenizer(br.readLine());
        int a, b, c;
        int[] board = new int[101];
        for(int i = 1; i < board.length-1; i++)
            board[i] = i;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int[] positions = new int[a+1];
        Arrays.fill(positions, 1);
        while(b-- > 0){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if(i!=100) board[i] = j;
        }
        boolean won = false;
        int player = 1;
        while(c-- > 0){
            int i = Integer.parseInt(br.readLine());
            if(!won){
                positions[player] = positions[player] + i >= 100 ? 100 : board[positions[player]+i];
                if(positions[player] == 100)
                    won = true;
                player++;
                if(player >= positions.length) player = 1;
            }
        }
        for(int i = 1; i < positions.length; i++)
            System.out.println("Position of player "+i+" is "+positions[i]+".");
    }
    
    public static void main(String[] args) throws IOException{
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            solve();
        }
        
    }
    
}
