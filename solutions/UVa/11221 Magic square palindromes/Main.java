import java.io.*;
import java.util.*;

// UVa 11221 - Magic Square Palindromes
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        String line;
        boolean magic;
        char[][] mat;
        int sqrt, len;
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            line = "";
            len = 0;
            for(char x : br.readLine().toCharArray())
                if(Character.isLetter(x)) { line += x; len++; }
            
            sqrt = (int) Math.round(Math.sqrt(len));
            
            if(sqrt*sqrt != len)
                magic = false;
            else {
                mat = new char[sqrt][sqrt];
                int x = 0;
                for(int i = 0; i < sqrt; i++)
                    for(int j = 0; j < sqrt; j++){
                        mat[i][j] = line.charAt(x++);
                    }
                magic = true;
                for(int i = 0; i < sqrt && magic; i++)
                    for(int j = 0; j < sqrt && magic; j++){
                        if(mat[i][j] != mat[j][i] ||
                           mat[i][j] != mat[sqrt-1-i][sqrt-1-j] ||
                           mat[i][j] != mat[sqrt-1-j][sqrt-1-i])
                            magic = false;
                    }
            }
            System.out.println("Case #"+t+":");
            System.out.println(magic?sqrt:"No magic :(");
        }
        
    }
        
}

