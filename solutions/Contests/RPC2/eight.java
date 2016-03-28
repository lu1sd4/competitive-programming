import java.io.*;
import java.util.*;

public class eight {

    public static void main(String[] args) throws IOException{
   
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int K;
        while((line=br.readLine()) != null){
            K = Integer.parseInt(line);
            printStar(K);
        }
        
    }
    
    public static void printStar(int K){
        boolean star[][] = new boolean[4*K + 1][4*K + 1];
        fillHorizontals(star, K);
        fillVerticals(star, K);
        fillDiagonals(star, K);
        printS(star);
    }
    
    public static void fillDiagonals(boolean[][] star, int K){
        for(int i = 0, j = K; j < star[i].length; i++, j++){
            star[i][j] = true;
            star[i+K][j-K] = true;
            star[i][4*K - j] = true;
            star[i+K][5*K - j] = true;
        }
    }
    
    public static void fillHorizontals(boolean[][] star, int K){
        for(int i = 0; i < star[K].length; i++){
            star[K][i] = true;
            star[3*K][i] = true;
        }
    }
    
    public static void fillVerticals(boolean[][] star, int K){
        for(int i = 0; i < star.length; i++){
            star[i][K] = true;
            star[i][3*K] = true;
        }
    }
    
    public static void printS(boolean[][] star){
        String line;
        for(int i = 0; i < star.length; i++){
            line = "";
            for(int j = 0; j < star[i].length; j++)
                line += star[i][j]?"*":" ";
            line = line.substring(0, line.lastIndexOf("*")+1);
            System.out.println(line);
        }
    }
    
}
