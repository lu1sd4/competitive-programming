import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] mat = new int[N][N];
        StringTokenizer st;
        int pd = 0;
        int sd = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int a = Integer.parseInt(st.nextToken(" "));
                if(i==j)
                    pd+=a;
                if(i==N-j-1)
                    sd+=a;
            }
        }
        System.out.println(Math.abs(pd-sd));
        
    }
}