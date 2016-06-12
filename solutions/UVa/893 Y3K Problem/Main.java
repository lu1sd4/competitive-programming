import java.io.*;
import java.util.*;

//UVa 893 - Y3K Problem
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int d = nextInt(), D = nextInt(), M = nextInt(), Y = nextInt();
		GregorianCalendar gc;
		int DD = GregorianCalendar.DATE;
		int MM = GregorianCalendar.MONTH;
		int YYYY = GregorianCalendar.YEAR;
		
		while(d != 0 && D != 0 && M != 0 && Y != 0){
			
			gc = new GregorianCalendar(Y, M - 1, D);
			gc.add(GregorianCalendar.DATE, d);
			System.out.println(gc.get(DD) + " " + (gc.get(MM) + 1) + " " + gc.get(YYYY));
			
			d = nextInt(); 
			D = nextInt(); 
			M = nextInt(); 
			Y = nextInt();
			
		}
		
	}
	
	static String next() throws IOException{
		while(st == null || !st.hasMoreTokens()){
			try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
		}
		return st.nextToken();
	}
	
	static int nextInt() throws IOException{
		return Integer.parseInt(next());
	}
	
}
