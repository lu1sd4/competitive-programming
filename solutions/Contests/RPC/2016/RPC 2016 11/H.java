package Solved;
import java.io.*;
import java.util.*;

public class H {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        String line;
        char[] word;
        boolean valid;
        String vowels;
        while((line = br.readLine()) != null){
        	word = line.toCharArray();
        	vowels = "";
        	valid = true;
        	for(char c: word)
        		if(isVowel(c))
        			vowels += c;
        	for(int i = 0; i < vowels.length() / 2 && valid; i++)
        		if(vowels.charAt(i) != vowels.charAt(vowels.length() - 1 - i))
        			valid = false;
        	pw.println(valid ? "S" : "N");
        }
        
        br.close();
        pw.close();
        
    }
    
    static boolean isVowel(char a){ 
    	return (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u');
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