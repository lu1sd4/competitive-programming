import java.io.*;
import java.util.*;

//UVa 10945 - Mother Bear
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		String line;
		boolean pal;
		
		while(true){
			line = br.readLine();
			if(line.equals("DONE")) break;
			line = line.replaceAll("[^a-zA-Z]", "").toLowerCase();
			pal = true;
			for(int i = 0; i < line.length()/2 && pal; i++)
				if(!(line.charAt(i) == line.charAt(line.length()-1-i))) pal = false;
			System.out.println(pal?"You won't be eaten!":"Uh oh..");
		}
	}
	
}
