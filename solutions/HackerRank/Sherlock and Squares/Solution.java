import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();        
        long A, B;
        double n;
        while(T-- > 0){
            A = sc.nextLong();
            B = sc.nextLong();
            n = Math.floor(Math.sqrt(B))-Math.ceil(Math.sqrt(A))+1;
            System.out.println((int)n);
        }
        
    }
}