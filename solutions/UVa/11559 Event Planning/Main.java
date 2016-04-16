import java.io.*;
import java.util.*;

// UVa 11559 - Event Planning
class Main {
    
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        String line;
        int n, b, h, w, ans, p, av;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            while(h-- > 0){
                st = new StringTokenizer(br.readLine());
                p = Integer.parseInt(st.nextToken());
                st = new StringTokenizer(br.readLine());
                if(p * n <= b){
                    for(int i = 0; i < w; i++){
                        av = Integer.parseInt(st.nextToken());
                        if(av >= n)
                            if(n * p < ans)
                                ans = n * p;
                    }
                }
            }
            System.out.println(ans != Integer.MAX_VALUE ? ans : "stay home");
        }
    }
    
    static double readDouble() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Double.parseDouble(st.nextToken());
    }
    
}
