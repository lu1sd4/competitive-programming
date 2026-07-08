import java.io.*;
import java.util.*;

// UVa 10114 - Lonesome Car Buyer
class Main {
    
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input.txt"));
        int duration = readInt();
        int start, ans;
        double[] perc;
        double car;
        double payment;
        while(duration > 0){
            double downpayment = readDouble();
            double debt = readDouble();
            int records = readInt();
            perc = new double[duration+1];
            
            start = readInt();
            perc[start] = readDouble();
            records--;
            while(records-- > 0){
                int m = readInt();
                while(start != (m-1) && start < duration){
                    start++;
                    perc[start] = perc[start-1];
                }
                perc[m] = readDouble();
                start = m;
            }
            start++;
            while(start < perc.length){
                perc[start] = perc[start-1];
                start++;
            }
            ans = 0;
            payment = debt / duration;
            car = (debt+downpayment) * (1-perc[0]);
            while(debt > car){
                ans++;
                car = car * (1-perc[ans]);
                debt -= payment;
            }
            System.out.println(ans+" month"+(ans!=1?"s":""));
            
            duration = readInt();
        }
    }
    
    static int readInt() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Integer.parseInt(st.nextToken());
    }
    
    static double readDouble() throws IOException{
        if(!st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return Double.parseDouble(st.nextToken());
    }
    
}
