import java.io.*;
import java.util.*;

//UVa 10646 - What Is the Card
class Main {
    
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        //br = new BufferedReader(new FileReader("src/input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int n, y, ind, x;
        String[] cards = new String[52];
        n = readInt();
        for(int k = 1; k <= n; k++){
            st = new StringTokenizer(br.readLine());
            y = 0;
            ind = 24;
            for(int i = 0; i < 52; i++)
                cards[i] = readString();
            for(int i = 0; i < 3; i++){
                if(Character.isDigit(cards[ind].charAt(0)))
                    x = Character.getNumericValue(cards[ind].charAt(0));
                else 
                    x = 10;
                y += x;
                ind = ind - (10-x) -1;
            }
            System.out.print("Case "+k+": ");
            if(ind < y)
                System.out.print(cards[24+(y-(ind+1))]);
            else
                System.out.print(cards[y]);
            System.out.println("");
        }
    }
    
    static String readString() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
