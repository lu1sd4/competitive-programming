import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException{
	
		br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new FileReader("src/input.txt"));
		
		//Code here
		
		int n = Integer.parseInt(br.readLine());
		while(n!=0){
			if(n==2){
				br.readLine();
				System.out.println(2);
			} else {
			
				st = new StringTokenizer(br.readLine());
				
				int peaks = 0;
				boolean firstasc;
				
				int x1 = Integer.parseInt(st.nextToken());
				n--;
				int x2 = Integer.parseInt(st.nextToken());
				n--;
				
				if(x1 > x2)
					firstasc = false;
				else
					firstasc = true;
				
				int x = 0, xbef = x2;
				
				boolean asc = firstasc;
				
				while(n-- > 0){
					
					x = Integer.parseInt(st.nextToken());
					if(x < xbef){
						if(asc)
							peaks++;
						asc = false;
					} else {
						if(!asc)
							peaks++;
						asc = true;
					}
					xbef = x;
					
				}
				
				if(x1 < x && asc)
					peaks++;
				if(x1 > x && !asc)
					peaks++;
				
				if(x1 < x)
					asc = false;
				else
					asc = true;
				
				if(firstasc != asc)
					peaks++;
				
				System.out.println(peaks);
				
			}
			
			
			n = Integer.parseInt(br.readLine());
		}
		
		
	}	
		
}
