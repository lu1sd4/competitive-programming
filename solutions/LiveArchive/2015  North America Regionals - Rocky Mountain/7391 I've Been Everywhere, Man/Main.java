import java.io.*;
import java.util.*;

//LiveArchive 7391 - I've Been Everywhere, Man
class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		pw = new PrintWriter(System.out);
		
		int t = nextInt();
		HashMap<String, Integer> places;
		while(t-- > 0){
			places = new HashMap<>();
			int n = nextInt();
			int unique = 0;
			while(n-- > 0){
				String place = next();
				if(!places.containsKey(place)){
					unique++;
					places.put(place, 1);
				}
			}
			pw.println(unique);
		}
		
		br.close();
		pw.close();
		
		
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
	
	static void println(Object a){
		System.out.println(a);
	}
		
}
