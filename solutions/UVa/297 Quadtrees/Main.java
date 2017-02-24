import java.io.*;
import java.util.*;

// UVa 297 - Quadtrees
public class Main {
	
	static class Node{
		int value;
		char v;
		Node[] children;
		public Node(char v, int value){
			this.v = v;
			this.value = value;
			children = new Node[4];
		}
	}
	
	static class intWrapper{
		int n;
		public intWrapper(int n){
			this.n = n;
		}
	}

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
		
		int n = lineToInt();
		for(int tc = 0; tc < n; tc++){	
			Node a = makeNode(lineAsCharArray(), new intWrapper(0), 1024);
			Node b = makeNode(lineAsCharArray(), new intWrapper(0), 1024);
			pw.println("There are "+merge(a,b)+" black pixels.");
		}
		
		br.close();
		pw.close();
		
	}
	
	int merge(Node a, Node b){
		if(a.v != 'p' || b.v != 'p')
			return Math.max(a.value, b.value);
		int sum = 0;
		for(int i = 0; i < 4; i++)
			sum += merge(a.children[i], b.children[i]);
		return sum;
	}
		
	Node makeNode(char[] pre, intWrapper index, int value){
		char current = pre[index.n];
		if(current == 'e')
			return new Node(current, 0);
		if(current == 'f')
			return new Node(current, value);
		else {
			Node n = new Node(current, 0);
			int sum = 0;
			for(int i = 0; i < 4; i++){
				index.n++;
				n.children[i] = makeNode(pre, index, value/4);
				sum += n.children[i].value;
			}
			n.value = sum;
			return n;
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