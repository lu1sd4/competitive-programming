import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());
            int n0 = N;
            while(n0%3!=0)
                n0-=5;
            if(n0<0)
                System.out.print("-1");
            else{
                for(int i = 0; i < n0; i++)
                    System.out.print("5");
                for(int i = 0; i < (N-n0); i++)
                    System.out.print("3");
            }
            System.out.print("\n");
        }
    }
}