import java.io.*;
import java.util.*;

public class DancingSentence {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            int a = 0;
            for(char c : line.toCharArray()){
                if(c != ' '){
                    if(a%2 == 0)
                        System.out.print(Character.toUpperCase(c));
                    else
                        System.out.print(Character.toLowerCase(c));
                    a++;
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println("");
        }
    }
    
}
