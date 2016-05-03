import java.io.*;
import java.util.*;

public class PerfectChoir {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;
        int N, sum, ans;
        int[] arr;
        String line;
        while((line = br.readLine())!=null){
            st = new StringTokenizer(line);
            arr = new int[10000];
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            sum = 0;
            for(int i = 0; i < N; i++)
                sum+=arr[i];
            ans = -1;
            if(sum % N == 0){
                int x = sum / N;
                ans = 1;
                for(int i = 0; i < N && arr[i] < x;++i)
                    ans += x - arr[i];
            }
            System.out.println(ans);
        }       
    }
    
}
