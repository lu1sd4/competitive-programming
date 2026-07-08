import java.io.*;
import java.util.*;
import java.math.*;

//UVa 11136 - Hoax or what
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n = nextInt();
		BigInteger ans;
		TreeMap<Long, Integer> bills;
		Map.Entry<Long, Integer> highestBill, lowestBill;
		while(n != 0){
			ans = BigInteger.valueOf(0);
			bills = new TreeMap<>();
			while(n-- > 0){
				int k = nextInt();
				while(k-- > 0){
					long value = nextLong();
					Integer count = bills.get(value);
					if(count != null) bills.put(value, count + 1);
					else			  bills.put(value, 1);
				}
				highestBill = bills.pollLastEntry();
				if(highestBill.getValue() > 1) bills.put(highestBill.getKey(), highestBill.getValue() - 1);
				lowestBill = bills.pollFirstEntry();
				if(lowestBill.getValue() > 1) bills.put(lowestBill.getKey(), lowestBill.getValue() - 1);
				long diff = highestBill.getKey() - lowestBill.getKey();
				ans = ans.add(BigInteger.valueOf(diff));
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
    
    static long nextLong() throws IOException{
    	return Long.parseLong(next());
    }
	
}
