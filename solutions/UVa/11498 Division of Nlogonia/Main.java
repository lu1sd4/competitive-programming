import java.io.*;
import java.util.*;

//UVa 11498 - Division of Nlogonia
class Main {
    
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        StringTokenizer st;
        int k, n, m, x, y;
        while(!(line = br.readLine()).equals("0")){
            k = Integer.parseInt(line);
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            while(k-- > 0){
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                if(x == n || y == m)
                    System.out.println("divisa");
                else if(x > n && y > m)
                    System.out.println("NE");
                else if(x < n && y > m)
                    System.out.println("NO");
                else if(x < n && y < m)
                    System.out.println("SO");
                else if(x > n && y < m)
                    System.out.println("SE");
            }
        }
                
    }
    
}
