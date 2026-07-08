import java.io.*;
import java.util.*;

//UVa 11926 - Multitasking
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		int n = nextInt(), m = nextInt();
		BitSet minutes;
		boolean possible;
		while(n != 0 || m != 0){
			minutes = new BitSet();
			possible = true;
			while(n-- > 0){
				int startTask = nextInt();
				int endTask = nextInt();
				if(possible)
					while(startTask < endTask){
						if(minutes.get(startTask)){
							possible = false;
							break;
						} else 
							minutes.set(startTask);
						startTask++;
					}
			}
			while(m-- > 0){
				int startTask = nextInt();
				int endTask = nextInt();
				int period = nextInt();
				if(possible){
					int duration = endTask - startTask;
					while(startTask < 1000001 && startTask < endTask){
						if(minutes.get(startTask)){
							possible = false;
							break;
						} else 
							minutes.set(startTask);
						startTask++;
						if(startTask >= endTask){
							endTask += period;
							startTask = endTask - duration;
						}
					}
				}
			}
			pw.println(possible ? "NO CONFLICT" : "CONFLICT");
			n = nextInt(); m = nextInt();
		}
		
		br.close();
		pw.close();
		
	}
	
	static String next() throws IOException{
        while(st == null || !st.hasMoreTokens()){
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return st.nextToken();
    }
	
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
	
}

