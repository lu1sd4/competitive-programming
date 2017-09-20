import java.io.*;
import java.util.*;

// UVa 12541 - Birthdates
public class Main {
	
	static class Person implements Comparable<Person>{
		String name;
		int time;
		public Person(String name, int day, int month, int year){
			this.name = name;
			this.time = year*10000+month*100+day;
		}
		@Override
		public int compareTo(Person p){
			return Integer.compare(this.time, p.time);
		}		
	}
	
	BufferedReader br;
	StringTokenizer st;
	PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}

	void solve() throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		
		TreeSet<Person> people = new TreeSet<>();
		int n = lineAsInt();
		while(n-- > 0){
			readLine();
			people.add(new Person(next(), nextInt(), nextInt(), nextInt()));
		}
		pw.println(people.last().name);
		pw.println(people.first().name);
		
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
