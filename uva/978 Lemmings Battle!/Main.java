import java.io.*;
import java.util.*;

//UVa 978 - Lemmings Battle!
class Main {

	static BufferedReader br;
	static PrintWriter pw;
	static StringTokenizer st = new StringTokenizer("");
	
	public static void main(String[] args) throws IOException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		pw = new PrintWriter(System.out);
		//pw = new PrintWriter(new File("src/output.txt"));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		TreeMap<Integer, Integer> green, blue;
		int N = nextInt();
		int B, SG, SB;
		while(N-- > 0){
			B = nextInt(); SG = nextInt(); SB = nextInt();
			green = new TreeMap<>();
			blue = new TreeMap<>();
			int power;
			while(SG-- > 0){
				power = nextInt();
				if(green.containsKey(power))
					green.put(power, green.get(power) + 1);
				else
					green.put(power, 1);
			}
			while(SB-- > 0){
				power = nextInt();
				if(blue.containsKey(power))
					blue.put(power, blue.get(power) + 1);
				else
					blue.put(power, 1);
			}
			Map.Entry<Integer, Integer> b, g;
			Integer[] bs;
			Integer[] gs;
			while(green.size() > 0 && blue.size() > 0){
				bs = new Integer[B];
				gs = new Integer[B];
				for(int i = 0; i < B; i++){
					b = blue.pollLastEntry();
					if(b != null){
						bs[i] = b.getKey();
						int nb = b.getValue();
						if(nb > 1)
							blue.put(b.getKey(), nb - 1);
					} else
						bs[i] = null;
					g = green.pollLastEntry();
					if(g != null){
						gs[i] = g.getKey();
						int ng = g.getValue();
						if(ng > 1)
							green.put(g.getKey(), ng - 1);
					} else
						gs[i] = null;
				}
				for(int i = 0; i < B; i++){
					if(bs[i] == null || gs[i] == null) break;
					if(bs[i] > gs[i]){
						bs[i] -= gs[i];
						gs[i] = 0;
					} else if(bs[i] < gs[i]){
						gs[i] -= bs[i];
						bs[i] = 0;
					} else {
						bs[i] = 0;
						gs[i] = 0;
					}
				}
				for(int i = 0; i < B; i++){
					if(bs[i] != null && bs[i] > 0) {
						Integer c = blue.get(bs[i]);
						if(c == null) blue.put(bs[i], 1);
						else		  blue.put(bs[i], c + 1);
					}
					if(gs[i] != null && gs[i] > 0){
						Integer c = green.get(gs[i]);
						if(c == null) green.put(gs[i], 1);
						else		  green.put(gs[i], c + 1);
					}
				}
			}
			TreeMap<Integer, Integer> w = new TreeMap<>();
			if(green.isEmpty() && blue.isEmpty())
				pw.println("green and blue died");
			else if(blue.isEmpty()){
				w = green;
				pw.println("green wins");
			} else {
				w = blue;
				pw.println("blue wins");
			}
			while(!w.isEmpty()){
				power = w.lastEntry().getKey();
				int times = w.get(power);
				for(int i = 0; i < times; i++)
					pw.println(power);
				w.remove(power);
			}
			if(N >= 1) pw.println();
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
