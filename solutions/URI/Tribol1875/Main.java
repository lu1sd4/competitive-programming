package Tribol1875;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());
        int B, G, R;
        StringTokenizer st;
        String a, b;
        while(C-- > 0){
            B = 0;
            G = 0;
            R = 0;
            int P = Integer.parseInt(br.readLine());
            while(P-- > 0){
                st = new StringTokenizer(br.readLine());
                a = st.nextToken();
                b = st.nextToken();
                if(a.equals("B")){
                    if(b.equals("R"))
                        B+=2;
                    if(b.equals("G"))
                        B++;
                }
                if(a.equals("R")){
                    if(b.equals("G"))
                        R+=2;
                    if(b.equals("B"))
                        R++;
                }
                if(a.equals("G")){
                    if(b.equals("B"))
                        G+=2;
                    if(b.equals("R"))
                        G++;
                }
            }
            if(G==B && B==R)
                System.out.println("trempate");
            else if(G>B && G>R)
                System.out.println("green");
            else if(B>G && B>R)
                System.out.println("blue");
            else if(R>B && R>G)
                System.out.println("red");
            else if(G==B || B==R || G==R)
                System.out.println("empate");
        }
        
    }
    
}

