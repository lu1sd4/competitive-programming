import java.io.*;
import java.util.*;

// UVa 839 - Not so Mobile
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
		
		int tc = lineToInt();
		br.readLine();
		while(tc-- > 0){
			String line;
			ArrayList<String> lines = new ArrayList<>();
			while((line = br.readLine()) != null && !line.isEmpty())
				lines.add(line);
			boolean balanced = makeTree(lines, new int[]{0}).balanced;
			pw.println(balanced?"YES":"NO");
			if(tc >= 1) pw.println();
		}
		
		br.close();
		pw.close();
		
	}
	
	Mobile makeTree(ArrayList<String> lines, int[] index) throws Exception{
		tokenize(lines.get(index[0]));
		Mobile m = new Mobile(readInt(), readInt(), readInt(), readInt());
		int wl = m.wl;
		int wr = m.wr;
		if(m.wl == 0){
			index[0]++;
			m.leftChild = makeTree(lines, index);
			wl = m.leftChild.tw;
		}
		if(m.wr == 0){
			index[0]++;
			m.rightChild = makeTree(lines, index);
			wr = m.rightChild.tw;
		}
		m.balanced = wl * m.dl == wr * m.dr;
		if(m.wl == 0)	m.balanced &= m.leftChild.balanced;
		if(m.wr == 0)	m.balanced &= m.rightChild.balanced;
		m.tw = wl + wr;
		return m;
	}
	
	static class Mobile{
		int wl, dl, wr, dr, tw;
		boolean balanced;
		Mobile leftChild, rightChild;
		public Mobile(int wl, int dl, int wr, int dr){
			this.wl = wl;
			this.dl = dl;
			this.wr = wr;
			this.dr = dr;
			this.tw = wl + wr;
		}
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