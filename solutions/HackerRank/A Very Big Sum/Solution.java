import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long sum = 0L;
        while(N-- > 0){
            sum += Long.parseLong(st.nextToken(" "));
        }
        System.out.println(sum);
    }
}