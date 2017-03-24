import java.io.*;
import java.util.*;

// UVa 1262 - Password
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
		
		int t = lineToInt();
		while(t-- > 0){
			int k = lineToInt(), prod = 1;
			char[][] g1 = new char[6][5], g2 = new char[6][5];
			for(int i = 0; i < 6; i++)
				g1[i] = br.readLine().toCharArray();
			for(int i = 0; i < 6; i++)
				g2[i] = br.readLine().toCharArray();
			ArrayList<ArrayList<Character>> commons = new ArrayList<>();
			HashSet<Character> set1;
			HashSet<Character> set2;
			for(int i = 0; i < 5; i++){
				set1 = new HashSet<>();
				set2 = new HashSet<>();
				for(int j = 0; j < 6; j++)
					set1.add(g1[j][i]);
				for(int j = 0; j < 6; j++)
					if(set1.contains(g2[j][i]))
						set2.add(g2[j][i]);
				ArrayList<Character> l = new ArrayList<>(set2);
				Collections.sort(l);
				commons.add(l);
				prod *= l.size();
			}
			if(k > prod)
				pw.println("NO");
			else{
				k--;
				String result = "";
				int ci;
				for(int i = 4; i >= 0; i--){
					ci = k % commons.get(i).size();
					result = commons.get(i).get(ci) + result;
					k /= commons.get(i).size();
				}	
				pw.println(result);
			}
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