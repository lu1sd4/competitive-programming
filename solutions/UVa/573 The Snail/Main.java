import java.io.*;
import java.util.*;

//UVa 573 - The Snail
public class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		double h, u, d, f, c;
		int days;
		h = readDouble();
		while(h != 0){
			u = readDouble();
			d = readDouble();
			f = u * (readDouble()/100);
			days = 0;
			c = 0;
			do{
				c += u > 0 ? u : 0;
				days++;
				if(c < 0 || c > h)
					break;
				c -= d;
				if(c < 0 || c > h)
					break;
				u -= f;
			} while(c >= 0  && c <= h);
			System.out.println((c < 0 ? "failure " : "success ") + "on day "+days);
			h = readDouble();
		}
		
	}

	static double readDouble() throws IOException{
		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return Double.parseDouble(st.nextToken());
	}
	
}