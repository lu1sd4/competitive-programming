import java.io.*;
import java.util.*;

// UVa 327 - Evaluating Simple C Expressions
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
		
		String line;
		while((line = br.readLine()) != null){
			int[] vars = new int[26];
			for(int i = 0; i < vars.length; i++)
				vars[i] = i+1;
			ArrayList<Integer> postSum = new ArrayList<Integer>();
			ArrayList<Integer> postSub = new ArrayList<Integer>();
			String stripped = line.replaceAll("\\s+", "");
			char[] expression = stripped.toCharArray();
			for(int i = 0; i < expression.length; i++){
				if(Character.isLetter(expression[i])){
					if(i > 1)
						if(expression[i-1] == expression[i-2]){
							if(expression[i-1] == '-')
								vars[expression[i]-'a']--;
							else
								vars[expression[i]-'a']++;
						}
					if(i < expression.length - 2)
						if(expression[i+1] == expression[i+2]){
							if(expression[i+1] == '+')
								postSum.add(expression[i]-'a');
							else
								postSub.add(expression[i]-'a');
							i++;
						}
				}	
			}
			expression = stripped.replace("--", "").replace("++", "").toCharArray();
			int sum = 0;
			int op = 1;
			boolean[] marked = new boolean[26];
			for(int i = 0; i < expression.length; i++){
				char c = expression[i];
				if(Character.isLetter(c)){
					sum += op * vars[c-'a'];
					marked[c-'a'] = true;
				}
				else
					op = c == '+' ? 1 : -1;
			}
			for(int i : postSum)
				vars[i]++;
			for(int i : postSub)
				vars[i]--;
			
			pw.println("Expression: "+line);
			pw.println("    value = "+sum);
			for(int i = 0; i < marked.length; i++)
				if(marked[i])
					pw.println("    "+(char)(i+'a')+" = "+vars[i]);
		}
				
		br.close();
		pw.close();
		
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