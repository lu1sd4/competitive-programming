import java.io.*;
import java.util.*;

//UVa 11572 - Unique Snowflakes
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int t = nextInt();
		int n, currentLength, start, maxLength, snow;
		HashMap<Integer, Integer> lastTimeSeen;
		for(int ct = 1; ct <= t; ct++){
			n = nextInt();
			lastTimeSeen = new HashMap<>();
			start = 0;
			maxLength = Integer.MIN_VALUE;
			for(int i = 1; i <= n; i++){
				snow = nextInt();
				if(lastTimeSeen.containsKey(snow) && lastTimeSeen.get(snow) > start){
					start = lastTimeSeen.get(snow);
				}
				else{
					currentLength = i - start;
					maxLength = Math.max(currentLength, maxLength);
				}
				lastTimeSeen.put(snow, i);
			}
			pw.println(maxLength);
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
