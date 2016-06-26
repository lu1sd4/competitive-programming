import java.io.*;
import java.util.*;

//LiveArchive 7370 - Classy
class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static PrintWriter pw;
	
	static class NameStatus{	
		public NameStatus(String n){
			this.name = n;
		}
		public String name;
		public String status;		
	}
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		pw = new PrintWriter(System.out);
		
		Comparator<NameStatus> cc = new Comparator<NameStatus>(){
			@Override
			public int compare(NameStatus ns1, NameStatus ns2){
				int ct = ns1.status.compareTo(ns2.status);
				if(ct == 0)
					return ns1.name.compareTo(ns2.name);
				else
					return ct;
			}
		};
		
		HashMap<String, String> vals = new HashMap<String, String>();
		vals.put("upper", "1");
		vals.put("middle", "2");
		vals.put("lower", "3");
		
		NameStatus[] namestatuses;
		ArrayList<String> statusnumbers;
		String word, status;
		
		String line;
		while((line = br.readLine()) != null){
			namestatuses = new NameStatus[Integer.parseInt(line)];
			for(int i = 0; i < namestatuses.length; i++){
				st = new StringTokenizer(br.readLine());
				statusnumbers = new ArrayList<>();
				word = next();
				NameStatus ns = new NameStatus(word.substring(0, word.length()-1));
				word = next();
				while(!word.equals("class")){
					statusnumbers.add(vals.get(word));
					word = next();
				}
				status = "";
				for(int j = statusnumbers.size() - 1; j >= 0; j--)
					status += statusnumbers.get(j);
				for(int j = statusnumbers.size(); j <= 42; j++)
					status += "2";
				ns.status = status;
				namestatuses[i] = ns;
			}
			Arrays.sort(namestatuses, cc);
			for(int i = 0; i < namestatuses.length; i++)
				pw.println(namestatuses[i].name);
		}
		
		br.close();
		pw.close();
		
		
	}
	
	static String next() throws IOException{
		return st.nextToken();
	}
	
	static int nextInt() throws IOException{
		return Integer.parseInt(next());
	}
	
	static void println(Object a){
		System.out.println(a);
	}
		
}
