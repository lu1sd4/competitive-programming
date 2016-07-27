import java.io.*;
import java.util.*;

//UVa 11933 - Splitting Numbers
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n, a, b;
		boolean f;
		while((n = lineAsInt()) != 0){
			a = b = 0;
			f = true;
			for(int i = 0; i < Integer.SIZE; i++){
				if((n & (1 << i)) != 0){
					if(f) a |= (1 << i);
					else b |= (1 << i);
					f = !f;
				}
			}
			pw.println(a + " " + b);
		}
		
		br.close();
		pw.close();
		
	}
	
	static int lineAsInt() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	
}