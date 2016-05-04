import java.io.*;
import java.util.*;

// UVa 584 - Bowling
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        String line;
        int gamescore, frames[][];
        String sym;
        
        while(true){
            gamescore = 0;
            line = br.readLine();
            st = new StringTokenizer(line);
            if(st.countTokens() < 3) break;
            frames = new int[13][3];
            int games = 10;
            for(int i = 1; i <= games; i++){
                sym = st.nextToken();
                if(sym.equals("X")){
                    frames[i][0] = 10;
                    if(i == 10) {
                        sym = st.nextToken();
                        frames[11][0] = sym.equals("X") ? 10 : Integer.parseInt(sym);
                        sym = st.nextToken();
                        frames[11][1] = sym.equals("X") ? 10 : sym.equals("/") ? 10-frames[11][0] : Integer.parseInt(sym);
                        frames[11][2] = frames[11][0] + frames[11][1];
                    }
                } else {
                    frames[i][0] = Integer.parseInt(sym);
                    sym = st.nextToken();
                    if(sym.equals("/")) {
                        frames[i][1] = 10-frames[i][0];
                        if(i == 10){
                            sym = st.nextToken();
                            if(sym.equals("X")) frames[11][2] = 10;
                            else frames[11][2] = Integer.parseInt(sym);
                        }
                    }
                    else frames[i][1] = Integer.parseInt(sym);
                }
            }
            for(int i = 1; i <= 9; i++){
                frames[i][2] = frames[i][0]+frames[i][1];
                if(frames[i][0] == 10){
                    frames[i][2] += frames[i+1][0];
                    if(frames[i+1][0] == 10)
                        frames[i][2] += frames[i+2][0];
                    else
                        frames[i][2] += frames[i+1][1];
                }
                else if (frames[i][0]+frames[i][1] == 10)
                    frames[i][2] += frames[i+1][0];
                else
                    frames[i][2] = frames[i][0] + frames[i][1];
            }
            frames[10][2] = frames[10][0]+frames[10][1]+frames[11][2];
            
            for(int i = 1; i <= 10; i++)
                gamescore += frames[i][2];
            
            System.out.println(gamescore);
        }
        
    }
    
}

