import java.io.*;
import java.util.*;

// UVa 112 - Tree Summing
public class Main {
	
	BufferedReader br;
	StringTokenizer st = new StringTokenizer("");
	PrintWriter pw;
	
	public static void main(String[] args) throws Exception{
		new Main().solve();
	}
	
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		StringBuilder wholeInput = new StringBuilder();
		while(br.ready())
			wholeInput.append((char) br.read());
		st = new StringTokenizer(wholeInput.toString());
		
		while(st.hasMoreTokens()){
			long n = readLong();
			StringBuilder tree = new StringBuilder();
			int cp = 0;
			while(tree.length() == 0 || (cp > 0 && st.hasMoreTokens())){
				String token = st.nextToken();
				tree.append(' ');
				tree.append(token);
				cp += countp(token);
			}
			String treeStr = tree.toString();
			treeStr = treeStr.replace("(", " ( ");
			treeStr = treeStr.replace(")", " ) ");
			StringTokenizer thisTree = new StringTokenizer(treeStr);
			String ans = findSum(thisTree, n, 0);
			if(ans == "yes") pw.println("yes");
			else			 pw.println("no");
		}
				
		br.close();
		pw.close();
		
	}
	
	String findSum(StringTokenizer tt, long target, long current){
		tt.nextToken();
		String token = tt.nextToken();
		if(token.equals(")"))
			return "empty";
		long nbr = Long.parseLong(token);
		long sum = current + nbr;
		String left = findSum(tt, target, sum);
		if(left.equals("yes"))
			return "yes";
		String right = findSum(tt, target, sum);
		if(right.equals("yes"))
			return "yes";
		tt.nextToken();
		if(left.equals("empty") && right.equals("empty") && sum == target)
			return "yes";
		else
			return "no";
	}
	
	int countp(String token){
		int cp = 0;
		for(int i = 0; i < token.length(); i++){
			if(token.charAt(i) == '(') cp++;
			if(token.charAt(i) == ')') cp--;
		}
		return cp;
	}
	
	char[] lineAsCharArray() throws IOException{
    	return br.readLine().toCharArray();
    }
    
    String next() throws Exception{
    	return st.nextToken();
    }
    
    void tokenize(String s) throws Exception{
        st = new StringTokenizer(s);
    }

    int lineToInt() throws IOException{
        return Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
    }

    long readLong() throws Exception{
        return Long.parseLong(st.nextToken());
    }

    int readInt() throws Exception{
        return Integer.parseInt(st.nextToken());
    }
    
    void nextLine() throws Exception{
        st = new StringTokenizer(br.readLine());
    }
	
}