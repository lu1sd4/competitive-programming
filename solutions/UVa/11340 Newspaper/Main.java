import java.io.*;
import java.util.*;

// UVa 11340 - Newspaper
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        int t, k, lines;
        double prices[];
        String line;
        double sum;
        t = nextInt();
        while(t-- > 0){
            prices = new double[1000];
            sum = 0;
            k = nextInt();
            while(k-- > 0){
                line = br.readLine();
                st = new StringTokenizer(line);
                st.nextToken();
                prices[(int)line.charAt(0)] = Double.parseDouble(st.nextToken()) / 100;
            }
            lines = nextInt();
            while(lines-- > 0){
                line = br.readLine();
                for(int i = 0; i < line.length(); i++)
                    sum += prices[(int)line.charAt(i)];
            }
            System.out.printf("%.2f$\n", sum);
        }
        
    }
    
    static String next() throws IOException{
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }

    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    
}

