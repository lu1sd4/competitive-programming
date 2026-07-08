import java.io.*;
import java.util.*;

public class elephants {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int[] el = new int[m];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++)
                el[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(el);
            int wa = 0;
            int ans = 0;
            while(wa < w && ans < m){
                wa += el[ans];
                if(wa >= w)
                    break;
                ans++;
            }
            System.out.println(ans);
        }
        
    }
    
}
