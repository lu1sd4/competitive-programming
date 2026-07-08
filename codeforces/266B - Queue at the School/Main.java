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
		int n = in.nextInt(), t = in.nextInt();
		char arr[] = in.next().toCharArray(), aux;
		while(t-- > 0){
			for(int i = 0; i < n-1; i++){
				if(arr[i] == 'B' && arr[i+1] == 'G'){
					aux = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = aux;
					i++;
				}
			}
		}
		for(char p : arr)
			pw.print(p);
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
