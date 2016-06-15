import java.io.*;
import java.util.*;

//UVa 10812 - Beat the spread
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n = Integer.parseInt(br.readLine());
		double a, b, h1, h2;
		while(n-- > 0){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(b>a)
				System.out.println("impossible");
			else{
				if(a != b){
					h1 = (a-b)/2;
					if(h1 % 1 != 0)
						System.out.println("impossible");
					else {
						h2 = a - h1;
						if(h2 > h1)
							System.out.println((int)h2+" "+(int)h1);
						else
							System.out.println((int)h1+" "+(int)h2);
					}
				} else {
					System.out.println((int)a+" 0");
				}
			}
		}
		
	}
	
}
