package Encryption;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.*;

public class Solution {
    
    public Solution(){
        
    }
    
    public void resolver(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String m = br.readLine();
            int rows = (int)Math.floor(Math.sqrt(m.length()));
            int cols = (int)Math.ceil(Math.sqrt(m.length()));
            while(rows*cols < m.length()){
                if(rows < cols)
                    rows++;
                else
                    cols++;
            }
            char[][] enc = new char[rows][cols];
            int r = 0;
            int c = 0;
            for(int i = 0; i < m.length(); i++){
                enc[r][c] = m.charAt(i);
                c++;
                if(c==cols){
                    c=0;
                    r++;
                }
            }
            for(int i = 0; i < enc[0].length; i++){
                for(int j = 0; j < enc.length; j++){
                    if(enc[j][i]!=0)
                        System.out.print(enc[j][i]);
                }
                if(i < enc[0].length-1) System.out.print(" ");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        new Solution().resolver();
    }
    
}
