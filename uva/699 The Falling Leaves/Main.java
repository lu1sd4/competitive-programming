import java.io.*;
import java.util.*;

// UVa 699 - The Falling Leaves
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
		
		int tc = 1;
		String line;
		while(!(line = br.readLine()).equals("-1") ){
			tokenize(line);
			ArrayList<Integer> nodes = new ArrayList<>();
			while(st.hasMoreTokens())
				nodes.add(readInt());
			
			int[] piles = new int[100];
			makeTree(nodes, new intWrapper(0), piles, 50);
			
			pw.println("Case "+tc+":");
			String s = "";
			for(int i = 0; i < piles.length-1; i++)
				if(piles[i]>0)
					s += piles[i] + " ";
			pw.println(s.trim()+"\n");
			
			tc++;
			
		}
		
		br.close();
		pw.close();
		
	}
	
	static class intWrapper{
		int v;
		public intWrapper(int v){
			this.v = v;
		}
	}
	
	void makeTree(ArrayList<Integer> nodes, intWrapper currentPosition, int[] piles,  int currentPile){
		if(nodes.get(currentPosition.v) == -1)
			return;
		piles[currentPile] += nodes.get(currentPosition.v);
		currentPosition.v++;
		makeTree(nodes, currentPosition, piles, currentPile-1);
		currentPosition.v++;
		makeTree(nodes, currentPosition, piles, currentPile+1);
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