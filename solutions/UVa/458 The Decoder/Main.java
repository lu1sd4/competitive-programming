import java.io.*;
import java.util.*;

//UVA 458 - The Decoder
public class Main {

	static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	 
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		while((line = br.readLine()) != null){
			char[] chars = line.toCharArray();
			for(char c : chars)
				pw.print((char)(c - 7));
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
