import java.io.*;
import java.util.*;
import java.math.*;
import java.text.SimpleDateFormat;

//UVa 458 - The Decoder
class Main {

    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static PrintWriter pw;
    
    public static void main(String[] args) throws Exception{
        
        br = new BufferedReader(new InputStreamReader(System.in));
        pw = new PrintWriter(System.out);
        //br = new BufferedReader(new FileReader("src/input"));
        
        String line;
        
        while((line = br.readLine()) != null){
        	
        	for(int i = 0; i < line.length(); i++)
        		pw.print((char)(line.charAt(i)-7));
        	pw.println();
        	
        }
        
        br.close();
        pw.close();
        
    }
    
}

/*

El código arriba da wrong answer.

El siguente en C++ si lo recibe el UVa.

#include <cstdio>

using namespace std;

int main()
{
    char c;
    while(scanf("%c",&c) != EOF){
        if(c!='\n')
            printf("%c",c-7);
        else
            printf("\n");
    }
   
   return 0;
   
}


*/