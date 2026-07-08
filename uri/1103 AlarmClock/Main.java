package AlarmClock1103;

import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        String line;
        int h1, m1, h2, m2;
        int hours, ans;
        while(!(line = br.readLine()).equals("0 0 0 0")){
            st = new StringTokenizer(line);
            h1 = Integer.parseInt(st.nextToken());
            m1 = Integer.parseInt(st.nextToken());
            h2 = Integer.parseInt(st.nextToken());
            m2 = Integer.parseInt(st.nextToken());
            if(h2 > h1){
                hours = h2-h1;
                ans = hours*60 - m1 + m2;
            } else if(h1 > h2){
                hours = 23 - h1 + h2;
                ans = hours*60 + (60-m1) + m2;
            } else {
                if(m2 > m1)
                    ans = m2-m1;
                else
                    ans = 24*60 - m1 + m2;
            }
            System.out.println(ans);
        }
    }
    
}