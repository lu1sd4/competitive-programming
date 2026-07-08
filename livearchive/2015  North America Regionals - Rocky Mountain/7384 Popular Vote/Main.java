import java.io.*;
import java.util.*;

//LiveArchive 7384 - Popular Vote
class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		pw = new PrintWriter(System.out);
		
		int t = nextInt();
		int n, winner;
		double total, maxVotes, votes;
		boolean nowinner;
		while(t-- > 0){
			n = nextInt();
			maxVotes = 0;
			total = 0;
			winner = 0;
			nowinner = true;
			for(int i = 1; i <= n; i++){
				votes = Double.parseDouble(next());
				total += votes;
				if(votes > maxVotes){
					winner = i;
					maxVotes = votes;
					nowinner = false;
				} else if(votes == maxVotes)
					nowinner = true;
			}
			if(nowinner)
				pw.println("no winner");
			else {
				double perc = maxVotes/total;
				if(perc > 0.5)
					pw.println("majority winner "+winner);
				else
					pw.println("minority winner "+winner);
			}
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
