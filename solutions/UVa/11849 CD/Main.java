import java.io.*;
import java.util.*;

//UVa 11849 - CD
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		st = new StringTokenizer(br.readLine());
		int a = nextInt();
		int b = nextInt();
		int ans;
		TreeSet<Integer> cds;
		while(a != 0 && b != 0){
			cds = new TreeSet<>();
			ans = 0;
			while(a-- > 0)
				cds.add(lineAsInt());
			while(b-- > 0)
				if(cds.contains(lineAsInt())) ans++;
			pw.println(ans);
			st = new StringTokenizer(br.readLine());
			a = nextInt();
			b = nextInt();
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
