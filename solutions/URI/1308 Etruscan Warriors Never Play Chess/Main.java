import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0){
			long x = Long.parseLong(br.readLine());
			System.out.println((int)((Math.sqrt(8*x + 1) - 1)/2));
		}
		
	}
		
}
