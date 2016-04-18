import java.io.*;
import java.util.*;

//UVa 10141 - Request For Proposal
class Main {

	static BufferedReader br;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String args[]) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		String line, bestproposal, propname;
		int n, p, r i;
		double bestcompliance, bestprice, d;		
		i = 1;
		while(!(line = br.readLine()).equals("0 0")){
			st = new StringTokenizer(line);
			n = readInt();
			p = readInt();
			for(int j = 0; j < n; j++) br.readLine();
			bestcompliance = Double.MIN_VALUE;
			bestprice = Double.MAX_VALUE;
			bestproposal = "";
			while(p-- > 0){
				propname = br.readLine();
				st = new StringTokenizer(br.readLine());
				d = readDouble();
				r = readInt();
				if((double)r/(double)n > bestcompliance){
					bestcompliance = (double)r/(double)n;
					bestproposal = propname;
					bestprice = d;
				} else if ((double)r/(double)n == bestcompliance){
					if(d < bestprice){
						bestproposal = propname;
						bestprice = d;
					}
				}
				while(r-- > 0) br.readLine();
			}
			System.out.print((i!=1?"\n":"")+"RFP #"+i+"\n"+bestproposal+"\n");
			i++;
		}
	}
	
	static int readInt() throws IOException{
		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return Integer.parseInt(st.nextToken());
	}
	static double readDouble() throws IOException{
		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		return Double.parseDouble(st.nextToken());
	}
	
}
