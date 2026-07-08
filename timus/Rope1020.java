
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Rope1020 {

    BufferedReader br;
    StringTokenizer st;
    
    public Rope1020(){
        
    }
    
    private void resolver() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        //br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        double r = Double.parseDouble(st.nextToken());
        double circunferencia = Math.PI * r * 2;
        double perimetro = 0;
        double cat1, cat2, hip;
        double[][] puntos = new double[n][2];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            puntos[i][0] = Double.parseDouble(st.nextToken());
            puntos[i][1] = Double.parseDouble(st.nextToken());
        }
        for(int i = 1; i < n; i++){
            cat1 = puntos[i][0] - puntos[i-1][0];
            cat2 = puntos[i][1] - puntos[i-1][1];
            hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
            perimetro+=hip;
        }
        cat1 = puntos[n-1][0] - puntos[0][0];
        cat2 = puntos[n-1][1] - puntos[0][1];
        hip = Math.sqrt(Math.pow(cat1, 2) + Math.pow(cat2, 2));
        perimetro+=hip;
        System.out.println(String.format("%.2f", (circunferencia+perimetro)));
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new Rope1020().correr();
    }
}