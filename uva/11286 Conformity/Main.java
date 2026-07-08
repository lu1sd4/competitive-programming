import java.io.*;
import java.util.*;

//UVa 11286 - Conformity
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n = nextInt();
		HashMap<String, Integer> combs;
		int[] courses;
		int max, currentTaken, ans;
		String key;
		while(n != 0){
			combs = new HashMap<>();
			max = 0;
			ans = 0;
			while(n-- > 0){
				courses = new int[5];
				for(int i = 0; i < 5; i++)
					courses[i] = nextInt();
				Arrays.sort(courses);
				key = "";
				for(int course : courses)
					key += course;
				if(combs.containsKey(key))
					currentTaken = combs.get(key) + 1;
				else
					currentTaken = 1;
				combs.put(key, currentTaken);
				if(currentTaken > max){
					max = currentTaken;
					ans = currentTaken;
				}
				else if(currentTaken == max)
					ans += currentTaken;
			}
			pw.println(ans);
			n = nextInt();
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
	
}
