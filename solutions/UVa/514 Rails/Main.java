import java.io.*;
import java.util.*;

//UVa 514 - Rails
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		int nline = 1;
		Stack<Integer> storage;
		int c = lineAsInt(), next, cc;
		String line;
		boolean possible;
		while(c != 0){
			while(!(line = br.readLine()).equals("0")){
				storage = new Stack<>();
				storage.push(0);
				if(nline == 26) pw.println(line);
				possible = true;
				st = new StringTokenizer(line);
				cc = 1;
				for(int i = 0; i < c && possible; i++){
					next = nextInt();
					if(next < storage.peek() && next < cc)
						possible = false;
					else if(next == storage.peek())
						storage.pop();
					else {
						while(next > storage.peek() && cc <= c){
							storage.push(cc);
							cc++;
						}
						if(next == storage.peek())
							storage.pop();
					}	
				}
				pw.println(possible?"Yes":"No");
			}
			pw.println();
			c = lineAsInt();
		}
		
		br.close();
		pw.close();
		
	}
	
	static int lineAsInt() throws IOException{
		return Integer.parseInt(br.readLine());
	}
	
	static int nextInt() throws IOException{
		return Integer.parseInt(st.nextToken());
	}
	
}
