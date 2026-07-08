import java.io.*;
import java.util.*;
public class D {

    static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;

    public static void main(String[] args) throws Exception{

        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
        pw = new PrintWriter(System.out);

        String line;
        int a, b, c, d;
        while((line = br.readLine()) != null){
            tokenize(line);
            a = readInt();
            b = readInt();
            c = readInt();
            d = readInt();
            long ans = Long.MAX_VALUE;
            for(int i = 2; i * i <= c; i++){
                if(c % i == 0){
                    if(i % a == 0 && i % b != 0 && d % i != 0)
                        if(i < ans) ans = i;
                    long x = c / i;
                    if(x % a == 0 && x % b != 0 && d % x != 0)
                        if(x < ans) ans = x;
                }
            }
            pw.println(ans < Long.MAX_VALUE ? ans : "-1");
        }

        br.close();
        pw.close();

    }

    static void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    static int lineToInt() throws IOException{
        return Integer.parseInt(br.readLine());
    }

    static long readLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    static int readInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }

    static void nextLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }

}
