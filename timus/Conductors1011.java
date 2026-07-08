
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Conductors1011 {
    
    
    BufferedReader br;
    
    public Conductors1011(){
        
    }
    
    private void resolver() throws IOException{
        //br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new InputStreamReader(new FileInputStream("src/input.txt")));
        double P = Double.parseDouble(br.readLine());
        double Q = Double.parseDouble(br.readLine());
        boolean encontrado = false;
        int valor = 1;
        while(!encontrado){
            valor++;
            if(Math.ceil(valor*(P/100))/valor < Q/100)
                encontrado = true;
        }
        System.out.println(valor);
    }
    
    private void correr() throws IOException{
        resolver();
    }
    
    public static void main(String args[]) throws IOException{
        new Conductors1011().correr();
    }
    
}
