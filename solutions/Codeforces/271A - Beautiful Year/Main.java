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
		int y = in.nextInt(), dig, d, ans = -1;
		boolean dif, used[];
		while(ans == -1){
			y++;
			dif = true;
			used = new boolean[10];
			dig = y;
			while(dif && dig >= 1){
				d = dig % 10;
				if(used[d])
					dif = false;
				else
					used[d] = true;
				dig /= 10;
			}
			if(dif) ans = y;
		}
		pw.print(ans);
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
