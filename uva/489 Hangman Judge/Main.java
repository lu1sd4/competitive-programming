import java.io.*;
import java.util.*;

//UVa 489 - Hangman Judge
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int letters[], w, r, t, c = readInt();
        String line;
        while(c != -1){
            letters = new int[27];
            w = 0;
            r = 0;
            t = 0;
            line = br.readLine();
            for(char y : line.toCharArray()){
                int x = (int)y-97;
                if(letters[x] != 1){
                    letters[x] = 1;
                    t++;
                }
            }
            line = br.readLine();
            for(char y : line.toCharArray()){
                int x = (int)y-97;
                if(letters[x] == 1){
                    letters[x]++;
                    r++;
                    if(r == t) break;
                }
                else if(letters[x] == 0){
                    letters[x]--;
                    w++;
                    if(w == 7) break;
                }
            }
            System.out.println("Round "+c);
            if(w >= 7)
                System.out.println("You lose.");
            else if(r == t)
                System.out.println("You win.");
            else
                System.out.println("You chickened out.");
            c = readInt();
        }
        
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
