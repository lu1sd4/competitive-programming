import java.io.*;
import java.util.*;

// UVa 230 - Borrowers
public class Main{
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	
	static class Book implements Comparable<Book>{
		String title;
		String author;
		public Book(String _name, String _author){
			title = _name;
			author = _author;
		}
		@Override
		public boolean equals(Object o){
			Book other = (Book)o;
			return title.equals(other.title) && author.equals(other.author); 
		}
		@Override
		public int compareTo(Book other){
			if(!author.equals(other.author))
				return author.compareTo(other.author);
			return title.compareTo(other.title);
		}
		@Override
		public String toString(){
			return title;
		}
	}

	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		ArrayList<Book> stored;
		ArrayList<Book> borrowed;
		ArrayList<Book> returned;
		HashMap<String, String> titleAuthor;
		Book currentBook;
		String line, title, author;
		int ib;
		char command;
		int endNameIndex;
		while((line = br.readLine()) != null){
			stored = new ArrayList<>();
			borrowed = new ArrayList<>();
			returned = new ArrayList<>();
			titleAuthor = new HashMap<>();
			while(!line.equals("END")){
				endNameIndex = line.indexOf('"', 1)+1;
				title = line.substring(0, endNameIndex);
				author = line.substring(line.indexOf("by", endNameIndex)+3);
				stored.add(new Book(title, author));
				titleAuthor.put(title, author);
				line = br.readLine();
			}
			Collections.sort(stored);
			while(!(line = br.readLine()).equals("END")){
				command = line.charAt(0);
				title = line.substring(line.indexOf(' ')+1, line.lastIndexOf('"')+1);
				currentBook = new Book(title, titleAuthor.get(title));
				if(command == 'B'){
					ib = stored.indexOf(currentBook);
					if(ib == -1) continue;
					borrowed.add(currentBook);
					stored.remove(currentBook);
				} else if(command == 'R'){
					ib = borrowed.indexOf(currentBook);
					if(ib == -1) continue;
					borrowed.remove(ib);
					returned.add(currentBook);
				} else if(command == 'S'){
					if(!returned.isEmpty()){
						Collections.sort(returned);
						int j = -1; 
						for(int i = 0; i < returned.size(); i++){
							currentBook = returned.get(i);
							while(j < stored.size()-1 && stored.get(j+1).compareTo(currentBook) < 0)
								j++;
							if(stored.size() == 0 || j == -1)
								pw.println("Put "+currentBook+" first");
							else 
								pw.println("Put "+currentBook+" after "+stored.get(j));
							stored.add(j+1, currentBook);
						}
						returned = new ArrayList<>();
					}
					pw.println("END");
				}
			}
		}
		
		br.close();
		pw.close();
		
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
