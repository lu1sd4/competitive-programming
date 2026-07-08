import java.io.*;
import java.util.*;

// UVa 548 - Tree
public class Main {
	
	static class Node{
		Integer value;
		Integer sumTilNow;
		Node leftChild;
		Node rightChild;
		public Node(Integer value){
			this.value=value;
			leftChild=null;
			rightChild=null;
		}
		public String inorder(){
			String result = "";
			if(this.leftChild != null) result += this.leftChild.inorder()+" ";
			result += value;
			if(this.rightChild != null) result += " "+this.rightChild.inorder();
			return result;
		}
		public String postorder(){
			String result = "";
			if(this.leftChild != null) result += this.leftChild.postorder()+" ";
			if(this.rightChild != null) result += this.rightChild.postorder()+" ";
			result += value;
			return result;
		}
		public String preorder(){
			String result = value+"";
			if(this.leftChild != null) result += " "+this.leftChild.preorder();
			if(this.rightChild != null) result += " "+this.rightChild.preorder();
			return result;
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
		
		ArrayList<Integer> in;
		ArrayList<Integer> pos;
		
		String line;
		while((line = br.readLine()) != null){
		
			in = new ArrayList<>();
			pos = new ArrayList<>();
			
			st = new StringTokenizer(line);
			while(st.hasMoreTokens())
				in.add(Integer.parseInt(st.nextToken()));
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens())
				pos.add(Integer.parseInt(st.nextToken()));
			
			minSum = Integer.MAX_VALUE;
			minLeafValue = 0;
			
			//Node root = 
			makeNode(in, pos, 0, in.size()-1, pos.size()-1, 0);
			
			pw.println(minLeafValue);
			
			//pw.println(root.inorder()+"\n"+root.postorder()+"\n"+root.preorder()+"\n");
			
		}
		
		br.close();
		pw.close();
		
	}
	
	int minSum;
	int minLeafValue;
	
	Node makeNode(ArrayList<Integer> in, ArrayList<Integer> pos, int iLeft, int iRight, int iPos, int sum){
		if(iLeft > iRight)
			return null;
		Integer value = pos.get(iPos);
		Node thisNode = new Node(value);
		thisNode.sumTilNow = value+sum;
		if(iLeft == iRight){
			if(thisNode.sumTilNow < minSum){
				minSum=thisNode.sumTilNow;
				minLeafValue=value;
			} else if(thisNode.sumTilNow == minSum)
				minLeafValue = Math.min(value, minLeafValue);
			return thisNode;
		}
		int iNode;
		for(iNode = iLeft; iNode <= iRight; iNode++){
			if(in.get(iNode).equals(value))
				break;
		}
		thisNode.rightChild = makeNode(in, pos, iNode+1, iRight, iPos-1, value+sum);
		thisNode.leftChild = makeNode(in, pos, iLeft, iNode-1, iPos-(iRight-iNode+1), value+sum);
		return thisNode;
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