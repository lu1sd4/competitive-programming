import java.io.*;
import java.util.*;

// UVa 10038 - Jolly Jumpers
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        
        String line;
        int items, n, a, b, abs;
        BitSet bs;
        boolean jolly;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            
            jolly = true;
            n = nextInt() - 1;
            items = n;
            bs = new BitSet(n + 1);
            a = nextInt();
            while(n-- > 0){
                b = nextInt();
                abs = Math.abs(a - b);
                if(abs > items || bs.get(abs)){
                    jolly = false;
                    break;
                } 
                bs.set(abs);
                a = b;
            }
            if(bs.cardinality() == items || items == 0)
                jolly = true;
            System.out.println(jolly ? "Jolly" : "Not jolly");
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

