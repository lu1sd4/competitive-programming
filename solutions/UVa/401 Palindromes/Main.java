import java.io.*;
import java.util.*;

//UVa 401 - Palindromes
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		String line;
		boolean pal, mir;
		char[] reversables = {'A', 'E', 'H', 'I', 'J', 'L', 'M', 'O', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '5', '8'};
		char[] reverses    = {'A', '3', 'H', 'I', 'L', 'J', 'M', 'O', '2', 'T', 'U', 'V', 'W', 'X', 'Y', '5', '1', 'S', 'E', 'Z', '8'};
		int[] r = new int[1000];
		for(int i = 0; i < reversables.length; i++)
			r[(int)reversables[i]] = (int)reverses[i];
		
		String[] anss = {" -- is a mirrored palindrome.", " -- is a mirrored string.", " -- is a regular palindrome.", " -- is not a palindrome."};
		
		while((line = br.readLine()) != null){
			char[] chars = line.toCharArray();
			if(line.length() > 1){
				pal = true;
				mir = true;
				
				for(int i = 0; i < chars.length/2; i++){
					if(chars[i] != chars[chars.length-1-i]){
						pal = false;
						if(r[(int)chars[i]] != (int)chars[chars.length-i-1])
							mir = false;
					}
					else if(r[(int)chars[i]] == 0 || r[(int)chars[i]] != (int)chars[i])
						mir = false;
				}
				
				if(chars.length % 2 == 1){
					if(r[(int)chars[chars.length/2]] != (int)chars[chars.length/2]) 
						mir = false;
				}
				else
					if(r[(int)chars[chars.length/2]] != (int)chars[chars.length/2 - 1])
						mir = false;
				
				if(mir && pal)
					line += anss[0];
				else if(mir)
					line += anss[1];
				else if(pal)
					line += anss[2];
				else
					line += anss[3];
				
			} else {
				if(r[(int)chars[0]] != (int)chars[0])
					line += anss[2];
				else
					line += anss[0];
			}

			System.out.println(line+"\n");
		}
	}
	
}
