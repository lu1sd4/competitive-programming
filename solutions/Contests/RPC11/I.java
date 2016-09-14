import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class I {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader br = new BufferedReader(new FileReader("InputI.txt"));
		PrintWriter pr = new PrintWriter(System.out);

		String line;
		StringTokenizer st;

		int N;
		int num;
		int[] columns;
		int height;
		while ((line = br.readLine()) != null) {
			N = Integer.parseInt(line);
			line = br.readLine();
			st = new StringTokenizer(line);
			columns = new int[N];
			for (int i = 0; i < N; i++) {
				columns[i] = Integer.parseInt(st.nextToken());
			}
			pr.println(maxH(columns));
		}
		br.close();
		pr.close();

	}

	public static int maxH(int columns[]) {

		int maxH;
		int len = columns.length;
		int maxT = 0;
		boolean par = len % 2 == 0;
		int indice;
		int H;
		indice = 0;
		
		if (par) {
			maxH = len / 2;
			for (int i = 0; i < columns.length; i++) {
				if (i > maxH) {
					indice--;

				} else if (i < maxH) {
					indice++;
				}
				if (indice < columns[i]) {
					columns[i] = indice;
				}
			}
		} else {
			maxH = len / 2;
			for (int i = 0; i < columns.length; i++) {
				if (i > maxH) {
					indice--;

				} else {
					indice++;
				}

				if (indice < columns[i]) {
					columns[i] = indice;
				}
			}
		}
		
		boolean cambio = true;
		while (cambio) {
			cambio = false;
			for (int i = 1; i < columns.length; i++) {
				if (columns[i] > columns[i - 1]) {
					if ((columns[i] - columns[i - 1]) > 1) {
						columns[i] = columns[i - 1] + 1;
						cambio = true;
					}
				} else {
					if ((columns[i - 1] - columns[i]) > 1) {
						columns[i - 1] = columns[i] + 1;
						cambio = true;
					}
				}
			}
		}

		for (int i = 0; i < columns.length; i++) {
			if(maxT < columns[i]){
				maxT = columns[i];
			}
		}
		
		return maxT;
	}

}
