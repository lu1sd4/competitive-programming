import java.io.*;
import java.util.*;

// UVa 12356 - Army buddies
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int s, b, l, r;
        int[] ls, rs;
        String line;
        
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            s = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(s == 0 && b == 0) break;
            ls = new int[s+2];
            rs = new int[s+2];
            for(int i = 0; i <= s + 1; i++){
                ls[i] = i - 1;
                rs[i] = i + 1;
            }
            rs[s] = 0;
            while(b-- > 0){
                st = new StringTokenizer(br.readLine());
                l = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                if(ls[l] == 0) out.print("* ");
                else out.print(ls[l]+" ");
                if(rs[r] == 0) out.println("*");
                else out.println(rs[r]);
                ls[rs[r]] = ls[l];
                rs[ls[l]] = rs[r];
            }
            out.println("-");
        }
        
        br.close();
        out.close();
        
    }
        
}

