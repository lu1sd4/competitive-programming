package LeapYearOrNotLeapYearAnd1279;

import java.io.*;
import java.util.*;

public class Main {
    
    public static int sumdigits(String line){
        int sum = 0;
        for(char c : line.toCharArray()){
            sum += Character.getNumericValue(c);
        }
        return sum;
    }
    
    public static int alternsum(String line){
        int sum = 0;
        boolean substract = true;
        int v;
        for(char c: line.toCharArray()){
            v = Character.getNumericValue(c);
            sum = substract ? sum - v : sum + v;
            substract = !substract;
        }
        return sum;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int year;
        boolean any, leap, by5;
        line = br.readLine();
        while(line != null){
            any = false;
            leap = false;
            by5 = false;
            
            //Leap
            year = Integer.parseInt(line.substring(line.length()-4));
            if((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year/100 % 4 == 0)){
                System.out.println("This is leap year.");
                any = true;
                leap = true;
            }
            
            //Hulculu
            //Year % 15 == 0
            if(line.substring(line.length()-1).matches("5|0")){
                by5 = true;
                if(sumdigits(line) % 3 == 0){
                    System.out.println("This is huluculu festival year.");
                    any = true;
                }
            }
            
            //Bulkulu
            if(leap && by5){
                if(alternsum(line) % 11 == 0)
                    System.out.println("This is bulukulu festival year.");
            }
            
            //None
            if(!any){
                System.out.println("This is an ordinary year.");
            }
            
            line = br.readLine();
            if(line != null)
                System.out.println("");
        }
        
    }
    
}
