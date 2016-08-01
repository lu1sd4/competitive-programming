import java.io.*;
import java.util.*;

//UVa 11988 - Broken Keyboard (a.k.a. Beiju Text)
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	static class Beiju{
		
		static class Node{
			Node next;
			char letter;
			public Node(Node next){
				this.next = next;
			}
			public Node(Node next, char letter){
				this.next = next;
				this.letter = letter;
			}
		}
		
		Node first;
		Node current;
		Node last;
		public Beiju(){
			first = new Node(null);
			current = first;
			last = first;
		}
		public void insert(char letter){
			Node n = new Node(current.next, letter);
			current.next = n;
			current = n;
			if(current.next == null)
				last = current;
		}
		public void backToStart(){
			current = first;
		}
		public void backToEnd(){
			current = last;
		}
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			Node n = first.next;
			while(n != null){
				sb.append(n.letter);
				n = n.next;
			}
			return sb.toString();
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		String line;
		char text[], currentCharacter;
		Beiju beiju;
		while((line = br.readLine()) != null){
			text = line.toCharArray();
			beiju = new Beiju();
			for(int i = 0; i < text.length; i++){
				currentCharacter = text[i];
				if(currentCharacter == '[')
					beiju.backToStart();
				else if(currentCharacter == ']')
					beiju.backToEnd();
				else
					beiju.insert(currentCharacter);
			}
			pw.println(beiju);
		}
		
		br.close();
		pw.close();
		
	}
	
}

