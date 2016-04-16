import java.io.*;
import java.util.*;

//UVa 11172 - Relational Operator
class Main {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int a, b;
        StringTokenizer st;
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(a >= b ? (a == b ? "=" : ">") : "<");
        }
        
    }
    
}
