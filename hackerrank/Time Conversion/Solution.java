import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String time = br.readLine();
        int h = Integer.parseInt(time.substring(0,2));
        if(h < 12 && time.charAt(8) == 'P')
            h+=12;
        if(h == 12 && time.charAt(8) == 'A')
            h = 0;
        String nh = String.format("%02d", h);
        System.out.println(nh+time.substring(2,time.length()-2));
                
    }
}