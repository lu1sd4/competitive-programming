import java.io.*;
import java.util.*;
import java.math.*;

// UVa 200 - Rare Order
public class Main {

	BufferedReader br;
    StringTokenizer st;
    PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	int NC = 26;
	
	Stack<Character> topsort;
	boolean g[][];
	boolean used[];
	ArrayList<String> words;
	
	boolean[] perm;
	boolean[] temp;

	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		String line;
		while((line = br.readLine()) != null){
			
			words = new ArrayList<>();
			used = new boolean[NC];
			g = new boolean[NC][NC];
			topsort = new Stack<>();
			perm = new boolean[NC];
			temp = new boolean[NC];
			
			while(!line.equals("#")){
				words.add(line);
				for(int i = 0; i < line.length(); i++)
					used[line.charAt(i)-'A'] = true;
				line = br.readLine();
			}
			
			int i1, i2;
			char c1, c2;
			String w1, w2;
			for(int i = 0; i < words.size()-1; i++){
				w1 = words.get(i);
				w2 = words.get(i+1);
				i1 = 0;
				i2 = 0;
				while(i1 < w1.length() && i2 < w2.length()){
					c1 = w1.charAt(i1);
					c2 = w2.charAt(i2);
					if(c1 != c2){
						g[c1-'A'][c2-'A'] = true;
						break;
					} else {
						i1++;
						i2++;
					}
				}
			}
			
			for(int k = 0; k < NC; k++)
				for(int i = 0; i < NC; i++)
					for(int j = 0; j < NC; j++)
						g[i][j] |= (g[i][k] && g[k][j]);
			
			for(int i = 0; i < NC; i++)
				if(used[i])
					visit(i);
			
			while(!topsort.isEmpty())
				pw.print(topsort.pop());
			pw.println();
			
		}
		
		br.close();
		pw.close();
	}
	
	void visit(int s){
		if(temp[s])
			return;
		else if(!perm[s]){
			temp[s] = true;
			for(int i = 0; i < NC; i++)
				if(g[s][i])
					visit(i);
			temp[s] = false;
			perm[s] = true;
			topsort.push((char)(s+'A'));
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
