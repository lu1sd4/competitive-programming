package AngryProffessor;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(T-- > 0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken()); 
            st = new StringTokenizer(br.readLine());
            while(N-- > 0){
                if(Integer.parseInt(st.nextToken())<= 0)
                    K--;                
            }
            if(K <= 0)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
        
    }
}