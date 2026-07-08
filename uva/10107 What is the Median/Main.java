import java.io.*;
import java.util.*;

// UVa 10107 - What is the Median?
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        ArrayList<Long> list = new ArrayList<>();
        String line;
        boolean odd = true;
        while((line = br.readLine()) != null){
            st = new StringTokenizer(line);
            list.add(Long.parseLong(next()));
            Collections.sort(list);
            if(odd)
                pw.println(list.get(list.size()/2));
            else{
                long a = list.get(list.size()/2);
                long b = list.get(list.size()/2 - 1);
                long ans = (a+b)/2;
                pw.println(ans);
            }
            odd = !odd;
        }
        
        br.close();
        pw.close();
        
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