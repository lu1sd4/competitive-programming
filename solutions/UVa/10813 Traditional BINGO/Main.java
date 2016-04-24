import java.io.*;
import java.util.*;

//UVa 10813 - Traditional BINGO
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int n = readInt();
        while(n-- > 0)
            System.out.println("BINGO after "+solve()+" numbers announced");
    }
    
    static int solve() throws IOException{
        int[][] card;
        card = new int[5][5];
        card[2][2] = -1;
        for(int i = 0; i < card.length; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < card.length; j++){
              if(!(i==2 && j==2))
                card[i][j] = readInt();
            }
        }
        int winTurn = 0;
        int c;
        for(int k = 1; k <= 75; k++){
            c = readInt();
            int i, j;
            if(c >= 1 && c <= 15) j = 0;
            else if(c >= 16 && c <= 30) j = 1;
            else if(c >= 31 && c <= 45) j = 2;
            else if(c >= 46 && c <= 60) j = 3;
            else j = 4;
            for(i = 0; i < card.length; i++)
                if(card[i][j] == c){
                    card[i][j] = -1;
                    break;
                }
            if (checkWin(i, j, card) && winTurn == 0)
                winTurn = k;
        }
        return winTurn;
    }
    
    static boolean checkWin(int i, int j, int[][] card){
        if(i==5) return false;
        boolean winRow = true;
        for(int col = 0; col < card[i].length; col++)
            if(card[i][col] != -1){ winRow = false; break; }
        boolean winCol = true;
        for(int row = 0; row < card.length; row++)
            if(card[row][j] != -1){ winCol = false; break; }
        boolean winDiag = false;
        if(i == j || i+j == card.length-1){
            boolean winDiagP = true;
            for(int k = 0; k < card.length; k++)
                if(card[k][k] != -1){winDiagP = false; break;}
            boolean winDiagS = true;
            for(int k = card.length-1; k >= 0; k--)
                if(card[card.length-1-k][k] != -1){winDiagS = false; break;}
            winDiag = winDiagP || winDiagS;
        }
        return winRow || winCol || winDiag;
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
