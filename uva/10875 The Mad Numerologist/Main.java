import java.io.*;
import java.util.*;
import java.math.*;

//UVA 10785 - The Mad Numerologist
public class Main {

	static BufferedReader br;
    static StringTokenizer st;
    static PrintWriter pw;
	
	public static void main(String args[]) throws Exception{
		new Main().solve();
	}
	 
	void solve() throws Exception{
		
		br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new FileReader("src/input"));
		pw = new PrintWriter(System.out);
		char[] vs = {'A','U','E','O','I'};
		char[] cs = {'J','S','B','K','T','C','L','D','M','V','N','W','F','X','G','P','Y','H','Q','Z','R'};
		int tc=lineToInt();
		for(int c=1; c<=tc; c++){
			pw.print("Case "+c+": ");
			int l=lineToInt();
			int nvs = (int)Math.ceil((double)l/(double)42);
			int ncs = (int)Math.ceil((double)(l-1)/(double)10);
			char[] cvowels = Arrays.copyOf(vs, nvs);
			char[] cconsonants = Arrays.copyOf(cs, ncs);
			ArrayList<Character> availableVowels = new ArrayList<>();
			ArrayList<Character> availableConsonants = new ArrayList<>();
			int iv=0, ic=0, cv=0, cc=0;
			for(int i=1;i<=l;i++){
				if(i%2==0){
					availableConsonants.add(cconsonants[ic]);
					if(++cc%5==0) ic++;
				} else {
					availableVowels.add(cvowels[iv]);
					if(++cv%21==0) iv++;
				}
			}
			Collections.sort(availableVowels);
			Collections.sort(availableConsonants);
			iv=0; ic=0;
			for(int i=1;i<=l;i++){
				if(i%2==0)
					pw.print(availableConsonants.get(ic++));
				else
					pw.print(availableVowels.get(iv++));
			}
			pw.println();
			
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
