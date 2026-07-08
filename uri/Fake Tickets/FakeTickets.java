import java.io.*;
import java.util.*;

public class FakeTickets {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        int N, M, cur, F;
        int[] app;
        while(!(line = br.readLine()).equals("0 0")){
            F = 0;
            st = new StringTokenizer(line);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            app = new int[N];
            st = new StringTokenizer(br.readLine());
            while(M-- > 0){
                cur = Integer.parseInt(st.nextToken()) - 1;
                app[cur]++;
                if(app[cur] == 2)
                    F++;
            }
            System.out.println(F);
        }
    }
    
}
