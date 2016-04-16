import java.io.*;
import java.util.*;

// UVa 11727 - Cost Cutting
class Main {
    
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int sals[] = new int[3];
        for(int i = 0; i < t; i++){
            sals[0] = readInt();
            sals[1] = readInt();
            sals[2] = readInt();
            Arrays.sort(sals);
            System.out.println("Case "+(i+1)+": "+sals[1]);        
        }
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
}
