import java.io.*;
import java.util.*;

// UVa 146 - ID Codes
public class Main {
    
    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
    
    public static void main(String[] args) throws IOException{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        pw = new PrintWriter(System.out);
        
        String line;
        Character[] code;
        Comparable[] newcode;
        while(!(line = br.readLine()).equals("#")){
            code = new Character[line.length()];
            for(int i = 0; i < line.length(); i++)
                code[i] = line.charAt(i);
            newcode = nextPermutation(code);
            if(newcode == null)
                pw.println("No Successor");
            else{
                for(Comparable c : newcode)
                    pw.print((Character)c);
                pw.print("\n");
            }
        }
        
        br.close();
        pw.close();
        
    }
    
    static Comparable[] nextPermutation(Comparable[] c) {
        int first = getFirst(c);
        if (first == -1) return null;
        int toSwap = c.length - 1;
        while(c[first].compareTo(c[toSwap]) >= 0)
            toSwap--;
        swap(c, first++, toSwap);
        toSwap = c.length - 1;
        while(first < toSwap)
            swap(c, first++, toSwap--);
        return c;
    }

    static int getFirst(Comparable[] c){
        for (int i = c.length - 2; i >= 0; i--)
            if (c[i].compareTo(c[i + 1]) < 0)
                return i;
        return -1;
    }

    static void swap(Comparable[] c, int i, int j){
        Comparable tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
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