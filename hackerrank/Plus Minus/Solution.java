import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int x = N;
        int p=0, n=0, z=0;        
        StringTokenizer st = new StringTokenizer(br.readLine());
        while(N-- > 0){
            int i = Integer.parseInt(st.nextToken(" "));
            if(i>0) p++;
            else if(i<0) n++;
            else z++;
        }
        System.out.printf("%.6f\n", (double)p/(double)x);
        System.out.printf("%.6f\n", (double)n/(double)x);
        System.out.printf("%.6f\n", (double)z/(double)x);
    }
    
}