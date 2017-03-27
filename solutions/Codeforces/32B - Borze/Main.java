import java.io.*;
import java.util.*;

public class Main {
	
	InputReader in;
	PrintWriter pw;
	
	public static void main(String[] args) {
		boolean file = false;
		new Main(file);
	}
	
	public Main(boolean file){
		in = new InputReader(file);
		pw = new PrintWriter(System.out);
		solve();
		pw.close();
	}
	
	void solve(){
		char[] s = in.next().toCharArray();
		int st = 0;
		for(char c : s){
			switch(c){
				case '.':
					if(st == 0) pw.print('0');
					if(st == 1) {
						pw.print('1');
						st = 0;
					}
					break;
				case '-':
					if(st == 0) st = 1;
					else if(st == 1){
						pw.print('2');
						st = 0;
					}
					break;
			}
		}
	}
	
	static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(boolean file){
        	if(file){
        		try{
        			reader = new BufferedReader(new FileReader("src/input"), 32768);
        		}catch(FileNotFoundException e){
        			e.printStackTrace();
        		}
        	}
        	else
        		reader = new BufferedReader(new InputStreamReader(System.in), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
	
}
