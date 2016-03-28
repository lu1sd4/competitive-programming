import java.io.*;
import java.util.*;

public class SumOfConsecutiveOddNumbers {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int X, Y;
        int sum;
        while(N-- > 0){
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            if(X % 2 == 0)
                X++;
            sum = 0;
            while(Y-- > 0){
                sum += X;
                X += 2;
            }
            System.out.println(sum);
        }
    }
    
}
