import java.io.*;
import java.util.*;

// UVa 10858 - Unique Factorization
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output"));
				
		int n;
		ArrayList<Integer> factors;
		ArrayList<ArrayList<Integer>> factorizations;
		while((n = lineAsInt()) != 0){
			factors = new ArrayList<>();
			factorizations = new ArrayList<>();
			uniqueFactorization(2, n, factors, factorizations);
			pw.println(factorizations.size());
			for(ArrayList<Integer> f : factorizations){
				for(int i = 0; i < f.size(); i++){
					if(i > 0) pw.print(" ");
					pw.print(f.get(i));
				}
				pw.println();
			}
		}
		
		br.close();
		pw.close();		
	}
	
	void uniqueFactorization(int m, int n, ArrayList<Integer> factors, ArrayList<ArrayList<Integer>> factorizations){
		int sqrt = (int)Math.sqrt(n);
		for(int i = m; i <= sqrt; i++)
			if(n % i == 0){
				factors.add(i);
				uniqueFactorization(i, n/i, new ArrayList<>(factors), factorizations);
				factors.remove((int)factors.size()-1);
			}
		if(!factors.isEmpty()){
			factors.add(n);
			factorizations.add(factors);
		}
	}
	
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineAsInt() throws IOException{
    	return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }
    
    char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }

    long nextLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int nextInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void readLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
    
    void skipLine() throws Exception{
    	br.readLine();
    }
	
}
