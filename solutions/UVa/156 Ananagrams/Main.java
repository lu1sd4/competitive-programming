import java.io.*;
import java.util.*;

//UVa 156 - Ananagrams
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		ArrayList<String> originalWords = new ArrayList<>();
		ArrayList<char[]> lowerCaseWords = new ArrayList<>();
		ArrayList<String> anansWords = new ArrayList<>();
		boolean anan[];
		
		String line, word;
		
		while(!(line = br.readLine()).equals("#")){
			st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				word = st.nextToken();
				originalWords.add(word);
				lowerCaseWords.add(word.toLowerCase().toCharArray());
			}
		}
		
		anan = new boolean[lowerCaseWords.size()];
		boolean isan;
		for(int i = 0; i < anan.length; i++){
			if(!anan[i]){
				isan = true;
				for(int j = i+1; j < anan.length; j++){
					if(!anan[j])
						if(isAnagram(lowerCaseWords.get(i), lowerCaseWords.get(j))){
							isan = false;
							anan[j] = true;
						}
				}
				if(isan)
					anansWords.add(originalWords.get(i));
				anan[i] = isan;
			}
		}
		Collections.sort(anansWords);
		for(String w : anansWords)
			System.out.println(w);
	}
	
	public static boolean isAnagram(char[] s1, char[] s2){
		if(s1.length != s2.length) return false;
		Arrays.sort(s1);
		Arrays.sort(s2);
		for(int i = 0; i < s1.length; i++)
			if(s2[i] != s1[i])
				return false;
		return true;
	}
	
}
