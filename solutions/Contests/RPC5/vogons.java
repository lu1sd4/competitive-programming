import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class vogons {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("vogonsInput.txt"));
		StringTokenizer st;
		String line;
		int n;
		int m;
		int[][] gold;
		int[] resultsColumns;
		int mayor;
		while((line = br.readLine()) != null){
			st = new StringTokenizer(line);
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			gold = new int[m][n];
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				st = new StringTokenizer(line);
				for (int j = 0; j < m; j++) {
					gold [j][i] = Integer.parseInt(st.nextToken());
				}
			}
			resultsColumns = new int[m];
			mayor = 0;
			
			for (int i = 0; i < m; i++) {
				resultsColumns[i] = maxPosibleSum(gold[i]);
			}
			
			mayor = maxPosibleSum(resultsColumns);
			
			System.out.println(""+mayor);
		}
		
	}
	
	public static int maxPosibleSum(int[] values){
		int[] tabla = new int[values.length];
		for (int j = 0; j < values.length; j++) {
			if(j==0){
				tabla[j] = values[j];
			}else if(j==1){
				if(values[j]>tabla[j-1]){
					tabla[j] = values[j];
				}else{
					tabla[j] = tabla[j-1];
				}
			}else{
				if(values[j]+tabla[j-2] > tabla[j-1]){
					tabla[j] = values[j] + tabla[j-2];
				}else{
					tabla[j] = tabla[j-1];
				}
			}
		}
		
		return tabla[values.length -1];
	}
}
