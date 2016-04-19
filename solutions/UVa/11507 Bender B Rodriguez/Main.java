import java.io.*;
import java.util.*;

//UVa 11507 - Bender B. Rodriguez 
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		int l = readInt()-1;
		while(l != -1){
			st = new StringTokenizer(br.readLine());
			String pos = "+x";
			String move;
			while(l-- > 0){
				move = st.nextToken();
				if(!move.equals("No")){
					if(pos.charAt(1) == 'x')
						if(pos.charAt(0) == '+')
							pos = move;
						else
							pos = new String(new char[]{move.charAt(0) == '+' ? '-' : '+', move.charAt(1)});
					else if(pos.charAt(1) != move.charAt(1)) continue;
					else if(pos.charAt(0) == move.charAt(0))
						pos = "-x";
					else
						pos = "+x";
				}
			}
			System.out.println(pos);
			l = readInt()-1;
		}
	}
	
	static int readInt() throws IOException{
		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return Integer.parseInt(st.nextToken());
	}
	
}
